package com.xy.xsql.block.core.printer;

import com.xy.xsql.block.exception.MetaException;
import com.xy.xsql.block.meta.MetaManager;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.model.ModelMetaBlock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringWriter;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.xy.xsql.block.exception.MetaException.*;
import static com.xy.xsql.block.model.BlockMeta.BlockConvention.EMPTY;
import static com.xy.xsql.block.model.BlockMeta.BlockConvention.LINE;

/**
 *
 * Print Model
 * Must go deep into the bottom,
 * Even if the 'Reference meta' is used,
 * Or a 'Data meta' that contains a subset
 *
 * Created by xiaoyao9184 on 2017/6/9.
 */
@SuppressWarnings({"WeakerAccess", "unused", "SameParameterValue"})
public class ModelMetaBlockPrinter
        implements BlockPrinter<ModelMetaBlock,StringWriter> {

    private final Logger logger;

    public ModelMetaBlockPrinter(){
        logger = LoggerFactory.getLogger(ModelMetaBlockPrinter.class);
    }

    /**
     * print context
     * @param model model
     * @return writer
     */
    @SuppressWarnings("unchecked")
    public StringWriter printModel(Object model){
        BlockMeta hiddenMeta = MetaManager
                .byModel(model.getClass())
                .meta()
                .orElseThrow(MetaException::miss_meta);
        return printModel(hiddenMeta,model);
    }

    /**
     * print model
     * @param meta meta
     * @param model model
     * @return writer
     */
    public StringWriter printModel(BlockMeta meta, Object model){
        StringWriter writer = new StringWriter();
        printModel(meta, model, writer);
        return writer;
    }

    /**
     * print context
     * @param meta meta
     * @param model model
     * @param writer writer
     */
    @SuppressWarnings({"Duplicates", "unchecked"})
    public void printModel(BlockMeta meta, Object model, StringWriter writer){

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

        //format style
        writer.append(meta.format()
                .filter(BlockMeta.Format::isNewLine)
                .map(style -> LINE.toString())
                .orElse(EMPTY.toString()));

        //data
        if(meta.isExclusive()){
            //Exclusive
            int index = 0;
            for(Predicate p : meta.getExclusivePredicate()){
                if(p.test(model)){
                    BlockMeta exclusiveMeta = meta.getSub().get(index);
                    printModel(exclusiveMeta, model, writer);
                    index = -1;
                    break;
                }
                index++;
            }
            if(index != -1){
                //TODO
                logger.warn("Exclusive Meta maybe hide default predicate!");
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
                    if(optional.get().equals(meta)){
                        throw MetaException.hide_exclusive_cycle(meta);
                    }

                    printModel(optional.get(), model, writer);
                }
            }
        }else if(meta.isReference()){
            //Reference child
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
            Object referenceModel = meta.getScope(model);

            if(meta.isList() &&
                    referenceModel instanceof List){
                //List
                printModel(refMeta, (List) referenceModel,", ",writer);
            }else if(meta.isRepeat() &&
                    referenceModel instanceof List){
                //Repeat
                printModel(refMeta, (List) referenceModel," ",writer);
            }else{
                printModel(refMeta, referenceModel, writer);
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
                String delimiter = "";
                if(meta.isList()){
                    delimiter = ", ";
                }else if(meta.isRepeat()) {
                    delimiter = " ";
                }
                printModel(itemMeta, (List)collectionModel, delimiter, writer);
            }else{
                printModel(meta.getSub(),model," ",writer);
            }
        }else if(meta.isKeyword()){
            //Keyword
            writer.append(meta.getName());
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
                printModel(hiddenMeta,data,writer);
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

                List<StringWriter> collectionWriter = collectionModel
                        .stream()
                        .map(itemModel -> {
                            StringWriter itemWriter = new StringWriter();
                            Optional<BlockMeta> hideMeta = MetaManager
                                    .byModel(itemModel.getClass())
                                    .meta();
                            if(hideMeta.isPresent()){
                                //item meta
                                BlockMeta itemMeta = hideMeta.get();
                                printModel(itemMeta, itemModel, itemWriter);
                            }else{
                                itemWriter.append(itemModel.toString());
                            }
                            return itemWriter;
                        })
                        .collect(Collectors.toList());

                String finalDelimiter = delimiter;
                String stringTemp = collectionWriter
                        .stream()
                        //joining
                        .flatMap(sw -> Stream.concat(
                                Stream.of(finalDelimiter),
                                Stream.of(sw.toString())
                        ))
                        .skip(1)
                        .collect(Collectors.joining(""));
                writer.append(stringTemp);
            }else{
                //Unknown
                writer.append(data.toString());
            }
        }
    }

    /**
     * print context list
     * @param itemMeta meta
     * @param listModel context model
     * @param delimiter delimiter
     * @param writer writer
     */
    public void printModel(BlockMeta itemMeta, List<Object> listModel, String delimiter, StringWriter writer) {
        writer.append(
                listModel
                        .stream()
                        .map(context -> {
                            StringWriter stringWriter = new StringWriter();
                            printModel(itemMeta, context, stringWriter);
                            return stringWriter.toString();
                        })
                        .filter(s -> !s.isEmpty())
                        .collect(Collectors.joining(delimiter))
        );
    }

    /**
     * print meta list
     * @param listMeta meta list
     * @param itemModel model
     * @param delimiter delimiter
     * @param writer writer
     */
    public void printModel(List<BlockMeta> listMeta, Object itemModel, String delimiter, StringWriter writer) {
        writer.append(
                listMeta
                        .stream()
                        .map(meta -> {
                            StringWriter stringWriter = new StringWriter();
                            printModel(meta, itemModel, stringWriter);
                            return stringWriter.toString();
                        })
                        .filter(s -> !s.isEmpty())
                        .collect(Collectors.joining(delimiter))
        );
    }


    /**
     * print block
     * @param block block
     * @return writer
     */
    @Override
    public StringWriter print(ModelMetaBlock block) {
        if(block.getModel() == null){
            return MetaBlockPrinter.print(block.getMeta());
        }else if(block.getMeta() == null){
            return printModel(block.getModel());
        }else{
            return printModel(block.getMeta(),block.getModel());
        }
    }


    /**
     * print model
     * @param model model
     * @return writer
     */
    @SuppressWarnings("unchecked")
    public static StringWriter print(Object model){
        return new ModelMetaBlockPrinter()
                .printModel(model);
    }


}
