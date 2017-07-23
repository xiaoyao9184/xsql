package com.xy.xsql.block.core.converter;

import com.google.common.base.Strings;
import com.xy.xsql.block.meta.MetaManager;
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

import static com.xy.xsql.block.exception.MetaException.*;
import static com.xy.xsql.block.model.BlockMeta.BlockConvention.BLANKS;
import static com.xy.xsql.block.model.BlockMeta.BlockConvention.EMPTY;
import static com.xy.xsql.block.model.BlockMeta.BlockConvention.LINE;
import static com.xy.xsql.block.model.BlockMeta.BlockDelimiterConvention.COMMA_;

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
                .meta()
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
        context.setMeta(meta);
        meta.format()
                .ifPresent(format -> {
                    context.setFormat(format);
                    context.addLevel(format.getIndentation());
                });
        meta.sub_format()
                .ifPresent(format -> {
                    //noinspection Convert2MethodRef
                    context.setSubFormat(format);
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
                        .meta();
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
                        .meta();
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
                build(refMeta, (List) refModel, COMMA_.toString(), context);
            }else if(meta.isRepeat() &&
                    refModel instanceof List){
                //Repeat
                build(refMeta, (List) refModel, BLANKS.toString(), context);
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
                String delimiter = EMPTY.toString();
                if(meta.isList()){
                    delimiter = COMMA_.toString();
                }else if(meta.isRepeat()) {
                    delimiter = BLANKS.toString();
                }
                build(itemMeta, (List)collectionModel, delimiter, context);
            }else{
                build(meta.getSub(), model, BLANKS.toString(), context);
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
                    .meta();

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
                String delimiter = EMPTY.toString();
                if(meta.isList()){
                    delimiter = COMMA_.toString();
                }else if(meta.isRepeat()) {
                    delimiter = BLANKS.toString();
                }
                build(collectionModel,delimiter,context);
            }else{
                //Unknown
                //TODO may be hide meta
                context.add(data.toString());
            }
        }
    }

    /**
     * build meta collection
     * with one model scope
     * @param collectionMeta meta collection
     * @param model model
     * @param delimiter delimiter
     * @param context out
     */
    public void build(Collection<BlockMeta> collectionMeta, Object model, String delimiter, Context context) {
        //The default format for the subset
        //line
        String lineDefault = context.sub_format()
                .filter(BlockMeta.Format::isNewLine)
                .map(f -> LINE.toString())
                .orElse(EMPTY.toString());
        //indentation
        String indentationDefault = context.sub_format()
                .filter(BlockMeta.Format::isNewLine)
                .map(f -> Strings.repeat(f.getIndentationChar(),
                        context.getAbsoluteLevel() + f.getIndentation()))
                .orElse(EMPTY.toString());
        //delimiter
        String delimiterDefault = context.sub_format()
                .filter(f -> !f.isUseDefaultDelimiter())
                .map(BlockMeta.Format::getDelimiterChar)
                .orElse(delimiter);

        List<String> listTemp = collectionMeta
                .stream()
                .map(meta -> {
                    Context itemContext = new Context().withLevel(context.level);
//                    Context itemContext = context.clone();
                    build(meta, model, itemContext);
                    return itemContext;
                })
                .filter(itemContext -> !itemContext.isEmpty())
                //joining
                .flatMap(itemContext -> {
                    //If the format is defined, the parent default format is ignored
                    String lineDefaultItem = itemContext.format()
                            .map(f -> EMPTY.toString())
                            .orElse(lineDefault);
                    String indentationDefaultItem = itemContext.format()
                            .map(f -> EMPTY.toString())
                            .orElse(indentationDefault);
                    String delimiterDefaultItem = itemContext.format()
                            .map(f -> delimiter)
                            .orElse(delimiterDefault);

                    //item format
                    //line
                    String lineCustomize = itemContext.format()
                            .filter(BlockMeta.Format::isNewLine)
                            .map(f -> LINE.toString())
                            .orElse(lineDefaultItem);
                    //indentation
                    String indentationCustomize = itemContext.format()
                            .filter(BlockMeta.Format::isNewLine)
                            .map(f -> Strings.repeat(f.getIndentationChar(),
                                    context.getAbsoluteLevel() + f.getIndentation()))
                            .orElse(indentationDefaultItem);
                    //delimiter
                    String delimiterCustomize = itemContext.format()
                            .filter(f -> !f.isUseDefaultDelimiter())
                            .map(BlockMeta.Format::getDelimiterChar)
                            .orElse(delimiterDefaultItem);

                    String delimiterFormat = lineCustomize +
                            indentationCustomize +
                            delimiterCustomize;

                    return Stream.concat(
                            Stream.of(delimiterFormat),
                            itemContext.getList().stream());
                })
                .skip(1)
                .collect(Collectors.toList());
        context.addAll(listTemp);
    }

    /**
     * build model collection
     * with many model scope
     * @param meta meta
     * @param collectionModel model collection
     * @param delimiter delimiter
     * @param context out
     */
    public void build(BlockMeta meta, Collection<Object> collectionModel, String delimiter, Context context) {
        //The default format for the subset
        //line
        String lineDefault = context.sub_format()
                .filter(BlockMeta.Format::isNewLine)
                .map(f -> LINE.toString())
                .orElse(EMPTY.toString());
        //indentation
        String indentationDefault = context.sub_format()
                .filter(BlockMeta.Format::isNewLine)
                .map(f -> Strings.repeat(f.getIndentationChar(),
                        context.getAbsoluteLevel() + f.getIndentation()))
                .orElse(EMPTY.toString());
        //delimiter
        String delimiterDefault = context.sub_format()
                .filter(f -> !f.isUseDefaultDelimiter())
                .map(BlockMeta.Format::getDelimiterChar)
                .orElse(delimiter);

        List<String> listTemp = collectionModel
                .stream()
                .map(model -> {
                    Context itemContext = new Context().withLevel(context.level);
                    build(meta, model, itemContext);
                    return itemContext;
                })
                .filter(itemContext -> !itemContext.isEmpty())
                //joining
                .flatMap(itemContext -> {
                    //If the format is defined, the parent default format is ignored
                    String lineDefaultItem = itemContext.format()
                            .map(f -> EMPTY.toString())
                            .orElse(lineDefault);
                    String indentationDefaultItem = itemContext.format()
                            .map(f -> EMPTY.toString())
                            .orElse(indentationDefault);
                    String delimiterDefaultItem = itemContext.format()
                            .map(f -> delimiter)
                            .orElse(delimiterDefault);

                    //item format
                    //line
                    String lineCustomize = itemContext.format()
                            .filter(BlockMeta.Format::isNewLine)
                            .map(f -> LINE.toString())
                            .orElse(lineDefaultItem);
                    //indentation
                    String indentationCustomize = itemContext.format()
                            .filter(BlockMeta.Format::isNewLine)
                            .map(f -> Strings.repeat(f.getIndentationChar(),
                                    context.getAbsoluteLevel() + f.getIndentation()))
                            .orElse(indentationDefaultItem);
                    //delimiter
                    String delimiterCustomize = itemContext.format()
                            .filter(f -> !f.isUseDefaultDelimiter())
                            .map(BlockMeta.Format::getDelimiterChar)
                            .orElse(delimiterDefaultItem);

                    String delimiterFormat = lineCustomize +
                            indentationCustomize +
                            delimiterCustomize;

                    return Stream.concat(
                            Stream.of(delimiterFormat),
                            itemContext.getList().stream());
                })
                .skip(1)
                .collect(Collectors.toList());
        context.addAll(listTemp);
    }

    /**
     * build model collection
     * without no meta
     * @param collectionModel model collection
     * @param delimiter delimiter
     * @param context out
     */
    public void build(Collection<Object> collectionModel, String delimiter, Context context) {
        List<String> listTemp = collectionModel
                .stream()
                .map(itemModel -> {
                    Context itemContext = new Context();
                    Optional<BlockMeta> hideMeta = MetaManager
                            .byModel(itemModel.getClass())
                            .meta();
                    if(hideMeta.isPresent()){
                        //item meta
                        BlockMeta itemMeta = hideMeta.get();
                        build(itemMeta, itemModel, itemContext);
                    }else{
                        itemContext.add(itemModel.toString());
                    }
                    return itemContext;
                })
                .filter(itemContext -> !itemContext.isEmpty())
                //joining
                .flatMap(itemContext -> Stream.concat(
                        Stream.of(delimiter),
                        itemContext.getList().stream()
                ))
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

        /**
         * Absolute level
         */
        private int level;


        private BlockMeta meta;
        private BlockMeta.Format format;
        private BlockMeta.Format subFormat;

        public Context(){
            list = new ArrayList<>();
        }


        @SuppressWarnings("MethodDoesntCallSuperMethod")
        public Context clone(){
            Context clone = new Context();
            clone.setLevel(this.level);
            clone.setFormat(this.format);
            return clone;
        }


        public List<String> getList() {
            return list;
        }

        public void setList(List<String> list) {
            this.list = list;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public BlockMeta getMeta() {
            return meta;
        }

        public void setMeta(BlockMeta meta) {
            this.meta = meta;
        }

        public BlockMeta.Format getFormat() {
            return format;
        }

        public void setFormat(BlockMeta.Format format) {
            this.format = format;
        }

        public BlockMeta.Format getSubFormat() {
            return subFormat;
        }

        public void setSubFormat(BlockMeta.Format subFormat) {
            this.subFormat = subFormat;
        }

        /**
         * current level
         * @param level current level
         * @return THIS
         */
        public Context withLevel(int level){
            this.level = level;
            return this;
        }

        /*

         */

        public Optional<BlockMeta.Format> format(){
            return Optional.ofNullable(format);
        }

        public Optional<BlockMeta.Format> sub_format(){
            return Optional.ofNullable(subFormat);
        }

        public int getAbsoluteLevel() {
            return level < 0 ? 0 : level;
        }

        public void addLevel(int formatLevel) {
            level = level + formatLevel;
        }

        public void add(String s) {
            list.add(s);
        }

        public void addAll(List<String> listTemp) {
            list.addAll(listTemp);
        }

        public boolean isEmpty() {
            return list.isEmpty();
        }

    }

}