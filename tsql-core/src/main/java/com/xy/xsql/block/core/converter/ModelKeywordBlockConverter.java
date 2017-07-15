package com.xy.xsql.block.core.converter;

import com.google.common.base.Strings;
import com.xy.xsql.block.core.meta.MetaManager;
import com.xy.xsql.block.core.printer.PrintAdapter;
import com.xy.xsql.block.exception.MetaException;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.model.KeywordListBlock;
import com.xy.xsql.core.builder.BaseBuilder;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.xy.xsql.block.core.printer.ModelMetaBlockPrinter.BlockConvention.EMPTY;
import static com.xy.xsql.block.core.printer.ModelMetaBlockPrinter.BlockConvention.LINE;
import static com.xy.xsql.block.exception.MetaException.*;

/**
 * Created by xiaoyao9184 on 2017/6/5.
 */
@SuppressWarnings({"Duplicates", "unchecked"})
public class ModelKeywordBlockConverter<MODEL>
        implements BaseBuilder<MODEL,KeywordListBlock> {


    @Override
    public KeywordListBlock build(MODEL model){
        BlockMeta meta = MetaManager
                .byModel(model.getClass())
                .get()
                .orElseThrow(MetaException::miss_meta);

        Context context = new Context();
        build(meta,model,context);
        return new KeywordListBlock(context.list);
    }

    /**
     * build
     * @param meta meta
     * @param model context
     */
    public void build(BlockMeta meta, Object model, Context context){

        //Optional
        if(meta.isOptional()){
            Predicate optionalPredicate = meta.getOptionalPredicate();
            if(optionalPredicate == null){
                throw miss_option_predicate(meta);
            }
            //noinspection unchecked
            if(optionalPredicate.test(model)){
                return;
            }
        }else if(model == null){
            throw miss_model(meta);
        }


        //format style: line indentation
        meta.format()
                .ifPresent(format -> {
                    context.setNewLine(format.isNewLine());
                    context.addLevel(format.getIndentation());
                });

        //data
        if(meta.isExclusive()){
            if(meta.getExclusivePredicate().size() != meta.getSub().size()){
                throw MetaException.not_same_exclusive_meta_and_predicate(meta);
            }

            //Exclusive
            int index = 0;
            for(Predicate p : meta.getExclusivePredicate()){
                //noinspection unchecked
                if(p.test(model)){
                    BlockMeta exclusiveMeta = meta.getSub().get(index);
                    build(exclusiveMeta, model, context);
                    index = -1;
                    break;
                }
                index++;
            }
            if(index != -1){
                //TODO
                //hide exclusive
                /**
                 * {@link com.xy.xsql.block.tsql.core.clause.select.GroupByConverter.ColumnNameItemConverter}
                 */
                Optional<BlockMeta> optional = MetaManager
                        .byModel(model.getClass())
                        .get();
                if(!optional.isPresent()){
                    throw MetaException.nothing_pass_exclusive(meta);
                }else{
                    build(optional.get(), model, context);
                }
            }
        }else if(meta.isReference()){
            //Reference
            BlockMeta refMeta;
            if(meta.isReferenceConverter()){
                Optional<BlockMeta> optional = MetaManager
                        .byConverter(meta.getReferenceConverter())
                        .get();
                if(!optional.isPresent()){
                    throw miss_reference_meta(meta);
                }else{
                    refMeta = optional.get();
                }
            }else{
                refMeta = meta.getReferenceMeta();
            }
            Object refModel = meta.getScope(model);

            if(meta.isList() &&
                    refModel instanceof List){
                //List
                build(refMeta, (List) refModel, ", ", context);
            }else if(meta.isRepeat() &&
                    refModel instanceof List){
                //Repeat
                build(refMeta, (List) refModel, " ", context);
            }else{
                build(refMeta, refModel, context);
            }
        }else if(meta.isOverall() ||
                meta.isVirtual() ||
                meta.getSub() != null){
            //Virtual
            if(meta.isList() ||
                    meta.isRepeat()){
                if(meta.getSub().size() != 1){
                    throw MetaException.collection_meta_not_single(meta);
                }
                //item meta
                BlockMeta itemMeta = meta.getSub().get(0);
                //collection model
                Object collectionModel = meta.getScope(model);
                if(!(collectionModel instanceof List)){
                    throw MetaException.collection_model_not_collection(meta);
                }
                //delimiter
                String delimiter = "";
                if(meta.isList()){
                    delimiter = ", ";
                }else if(meta.isRepeat()) {
                    delimiter = " ";
                }
                build(itemMeta, (List)collectionModel, delimiter, context);
            }else{
                build(meta.getSub(), model, " ", context);
            }
        }else if(meta.isKeyword()){
            //Keyword
            String blockString = meta.getName();
            context.add(blockString);
        }else{
            //Data
            Object data = meta.getScope(model);
            if(data == null){
                throw miss_model(meta);
            }

            Optional<BlockMeta> optional = MetaManager
                    .byModel(data.getClass())
                    .get();

            if(optional.isPresent()){
                BlockMeta hiddenMeta = optional.get();
                build(hiddenMeta,data,context);
            }else if(data instanceof List &&
                    meta.isCollection()){
                //collection model
                List<Object> collectionModel = (List)data;
                if(collectionModel.size() <= 0){
                    throw data_collection_empty(meta);
                }
                //delimiter
                String delimiter = "";
                if(meta.isList()){
                    delimiter = ", ";
                }else if(meta.isRepeat()) {
                    delimiter = " ";
                }

                List<Context> collectionContext = collectionModel
                        .stream()
                        .map(itemModel -> {
                            Context itemContext = new Context();
                            Optional<BlockMeta> hideMeta = MetaManager
                                    .byModel(itemModel.getClass())
                                    .get();
                            if(hideMeta.isPresent()){
                                //item meta
                                BlockMeta itemMeta = hideMeta.get();
                                build(itemMeta, itemModel, itemContext);
                            }else{
                                itemContext.add(itemModel.toString());
                            }
                            return itemContext;
                        })
                        .collect(Collectors.toList());

                String finalDelimiter = delimiter;
                List<String> listTemp = collectionContext
                        .stream()
                        //joining
                        .flatMap(context1 -> Stream.concat(
                                Stream.of(finalDelimiter),
                                context1.getList().stream()
                        ))
                        .skip(1)
                        .collect(Collectors.toList());
                context.addAll(listTemp);
            }else{
                //Unknown
                //TODO may be hide meta
                context.add(data.toString());
            }
        }
    }

    /**
     * build model collection
     * @param meta meta
     * @param collectionModel model collection
     * @param delimiter delimiter
     * @param context out
     */
    public void build(BlockMeta meta, Collection<Object> collectionModel, String delimiter, Context context) {
        List<String> listTemp = collectionModel
                .stream()
                .map(model -> {
                    Context cacheSub = new Context().withLevel(context.level);
                    build(meta, model, cacheSub);
                    return cacheSub;
                })
                .filter(cacheSub -> !cacheSub.isEmpty())
                //joining
                .flatMap(cacheSub -> {
                    String delimiterFormat = delimiter;
                    if(cacheSub.isNewLine()){
                        String start = Strings.repeat(cacheSub.getIndentation(),cacheSub.getSafeLevel());
                        delimiterFormat = "\n" + start + delimiterFormat;
                    }
                    return Stream.concat(
                            Stream.of(delimiterFormat),
                            cacheSub.getList().stream());
                })
                .skip(1)
                .collect(Collectors.toList());
        context.addAll(listTemp);
    }

    /**
     * build meta collection
     * @param collectionMeta meta collection
     * @param model model
     * @param delimiter delimiter
     * @param context out
     */
    public void build(Collection<BlockMeta> collectionMeta, Object model, String delimiter, Context context) {
        List<String> listTemp = collectionMeta
                .stream()
                .map(meta -> {
                    Context cacheSub = new Context().withLevel(context.level);
                    build(meta, model, cacheSub);
                    return cacheSub;
                })
                .filter(cacheSub -> !cacheSub.isEmpty())
                //joining
                .flatMap(cacheSub -> {
                    String delimiterFormat = delimiter;
                    if(cacheSub.isNewLine()){
                        String start = Strings.repeat(cacheSub.getIndentation(),cacheSub.getSafeLevel());
                        if(delimiterFormat.equals(" ")){
                            delimiterFormat = "\n" + start;
                        }else{
                            delimiterFormat = "\n" + start + delimiterFormat;
                        }
                    }
                    return Stream.concat(
                            Stream.of(delimiterFormat),
                            cacheSub.getList().stream());
                })
                .skip(1)
                .collect(Collectors.toList());
        context.addAll(listTemp);
    }



    public static PrintAdapter convert(Object model){
        KeywordListBlock block = new ModelKeywordBlockConverter<>()
                .build(model);

        return new PrintAdapter<KeywordListBlock,StringWriter>()
                .withBlock(block);
    }

    /**
     * Build Context
     */
    @SuppressWarnings({"WeakerAccess", "SameParameterValue", "unused"})
    public static class Context {

        private List<String> list;
        private boolean newLine;
        private int level;
        private String indentation = "\t";

        public Context(){
            list = new ArrayList<>();
        }

        public List<String> getList() {
            return list;
        }

        public void setList(List<String> list) {
            this.list = list;
        }

        public boolean isNewLine() {
            return newLine;
        }

        public void setNewLine(boolean newLine) {
            this.newLine = newLine;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getIndentation() {
            return indentation;
        }

        public void setIndentation(String indentation) {
            this.indentation = indentation;
        }

        public Context withLevel(int level){
            this.level = level;
            return this;
        }

        /*

         */

        public int getSafeLevel() {
            return level < 0 ? 0 : level;
        }

        public void addLevel(int formatLevel) {
            level = level + formatLevel;
        }

        public void subLevel(int formatLevel) {
            level = level - formatLevel;
        }

        public void add(String s) {
//            String start = Strings.repeat(indentation,this.level);
//            list.add(start + s);
            list.add(s);
        }

        public void addAll(List<String> listTemp) {
//            listTemp = listTemp
//                    .stream()
//                    .map(s -> {
//                        String start = Strings.repeat(indentation,this.level);
//                        return start + s;
//                    })
//                    .collect(Collectors.toList());
            list.addAll(listTemp);
        }

        public void addAll(List<String> listTemp, int level) {
            listTemp = listTemp
                    .stream()
                    .map(s -> {
                        String start = Strings.repeat(indentation,level);
                        return start + s;
                    })
                    .collect(Collectors.toList());
            list.addAll(listTemp);
        }

        public boolean isEmpty() {
            return list.isEmpty();
        }
    }

}
