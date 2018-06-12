package com.xy.xsql.tsql.converter.functions.rowset;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.converter.EnumConverterUtil;
import com.xy.xsql.tsql.converter.datatypes.constants.StringConstantConverter;
import com.xy.xsql.tsql.converter.statements.BulkInsertConverter;
import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.elements.operators.Assignment;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.rowset.OpenRowSet;
import com.xy.xsql.tsql.model.statements.BulkInsert;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.xy.xsql.tsql.builder.chain.statements.BulkInsertBuilder.WithSetter.*;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class OpenRowSetConverter
        implements ModelMetaBlockConverter<OpenRowSet> {


    // @formatter:off
    public static BlockMeta metaBase =
            new BlockMetaBuilder<Void,OpenRowSet>()
                    .description("base")
                    .sub()
                        .sub("'provider_name'")
                            .scope(d -> d.getProviderName())
                            .and()
                        .sub_keyword(Other.DELIMITER)
                        .sub()
                            .czse(d -> d.getDatasource() != null
                                    && d.getUserId() != null
                                    && d.getPassword() != null)
                                .sub("'datasource'")
                                    .scope(d -> d.getDatasource())
                                    .and()
                                .sub_keyword(Other.SEMICOLON)
                                .sub("'user_id'")
                                    .scope(d -> d.getUserId())
                                    .and()
                                .sub_keyword(Other.SEMICOLON)
                                .sub("'password'")
                                    .scope(d -> d.getPassword())
                                    .and()
                                .and()
                            .czse(d -> d.getProviderString() != null,"'provider_string'")
                                .scope(d -> d.getProviderString())
                                .and()
                            .syntax_required()
                            .syntax_sub_indentation_right()
                            .and()
                        .syntax_required()
                        .syntax_indentation_right()
                        .and()
                    .sub()
                        .keyword(Other.DELIMITER)
                        .syntax_indentation_right()
                        .and()
                    .sub()
                        .czse(d -> d.getObject() != null,"[ catalog. ] [ schema. ] object")
                            .scope(d -> d.getObject().stream().collect(Collectors.joining(".")))
                            .and()
                        .czse(d -> d.getQuery() != null,"'query'")
                            .scope(d -> d.getQuery())
                            .and()
                        .syntax_required()
                        .syntax_sub_indentation_right()
                        .and()
                    .build();
    // @formatter:on

    // @formatter:off
    public static BlockMeta metaBulkOptions =
            new BlockMetaBuilder<Void,BulkInsert>()
                    .overall("bulk_options")

                    .sub()
                        .description("format_file")
                        .optional(d -> d.getFormatFile() == null)
                        .sub()
                            .optional(d -> checkIsStart(1,d))
                            .keyword(Other.DELIMITER)
                            .and()
                        .sub_keyword(BulkInsert.WithEnum.FORMATFILE)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub("'format_file_path'")
                            .scope(BulkInsert::getFormatFile)
                            .and()
                        .and()

                    .sub()
                        .description("code_page")
                        .optional(d -> d.getCodePage() == null)
                        .sub()
                            .optional(d -> checkIsStart(2,d))
                            .keyword(Other.DELIMITER)
                            .and()
                        .sub_keyword(BulkInsert.WithEnum.CODEPAGE)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub()
                            .syntax_required()
                            .czse(d -> $ACP().equals(d.getCodePage()),"'ACP'")
                                .ref(StringConstantConverter.meta)
                                .scope(d -> d.getCodePage())
                                .syntax_reference_remove()
                                .and()
                            .czse(d -> $OEM().equals(d.getCodePage()),"'OEM'")
                                .ref(StringConstantConverter.meta)
                                .scope(d -> d.getCodePage())
                                .syntax_reference_remove()
                                .and()
                            .czse(d -> $RAW().equals(d.getCodePage()),"'RAW'")
                                .ref(StringConstantConverter.meta)
                                .scope(d -> d.getCodePage())
                                .syntax_reference_remove()
                                .and()
                            .czse(d -> true,"'code_page'")
                                .ref(StringConstantConverter.meta)
                                .scope(d -> d.getCodePage())
                                .syntax_reference_remove()
                                .and()
                            .and()
                        .and()

                    .sub()
                        .description("data_source")
                        .optional(d -> d.getDataSource() == null)
                        .sub()
                            .optional(d -> checkIsStart(3,d))
                            .keyword(Other.DELIMITER)
                            .and()
                        .sub_keyword(BulkInsert.WithEnum.DATA_SOURCE)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub("'data_source_name'")
                            .scope(BulkInsert::getDataSource)
                            .and()
                        .and()

                    .sub()
                        .description("error_file")
                        .optional(d -> d.getErrorFile() == null)
                        .sub()
                            .optional(d -> checkIsStart(4,d))
                            .keyword(Other.DELIMITER)
                            .and()
                        .sub_keyword(BulkInsert.WithEnum.ERRORFILE)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub("'file_name'")
                            .scope(BulkInsert::getErrorFile)
                            .and()
                        .and()

                    .sub()
                        .description("error_file_data_source")
                        .optional(d -> d.getErrorFileDataSource() == null)
                        .sub()
                            .optional(d -> checkIsStart(5,d))
                            .keyword(Other.DELIMITER)
                            .and()
                        .sub_keyword(BulkInsert.WithEnum.ERRORFILE_DATASOURCE)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub("'data_source_name'")
                            .scope(BulkInsert::getErrorFileDataSource)
                            .and()
                        .and()

                    .sub()
                        .description("first_row")
                        .optional(d -> d.getFirstRow() == null)
                        .sub()
                            .optional(d -> checkIsStart(6,d))
                            .keyword(Other.DELIMITER)
                            .and()
                        .sub_keyword(BulkInsert.WithEnum.FIRSTROW)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub("first_row")
                            .scope(BulkInsert::getFirstRow)
                            .and()
                        .and()

                    .sub()
                        .description("last_row")
                        .optional(d -> d.getLastRow() == null)
                        .sub()
                            .optional(d -> checkIsStart(7,d))
                            .keyword(Other.DELIMITER)
                            .and()
                        .sub_keyword(BulkInsert.WithEnum.LASTROW)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub("last_row")
                            .scope(BulkInsert::getLastRow)
                            .and()
                        .and()

                    .sub()
                        .description("max_errors")
                        .optional(d -> d.getMaxErrors() == null)
                        .sub()
                            .optional(d -> checkIsStart(8,d))
                            .keyword(Other.DELIMITER)
                            .and()
                        .sub_keyword(BulkInsert.WithEnum.MAXERRORS)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub("max_errors")
                            .scope(BulkInsert::getMaxErrors)
                            .and()
                        .and()

                    .sub()
                        .description("rows_per_batch")
                        .optional(d -> d.getRowsPerBatch() == null)
                        .sub()
                            .optional(d -> checkIsStart(9,d))
                            .keyword(Other.DELIMITER)
                            .and()
                        .sub_keyword(BulkInsert.WithEnum.ROWS_PER_BATCH)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub("rows_per_batch")
                            .scope(BulkInsert::getRowsPerBatch)
                            .and()
                        .and()

                    .sub()
                        .description("order")
                        .optional(d -> d.getOrderList() == null)
                        .sub()
                            .optional(d -> checkIsStart(10,d))
                            .keyword(Other.DELIMITER)
                            .and()
                        .sub_keyword(BulkInsert.WithEnum.ORDER)
                        .sub_keyword(Other.GROUP_START)
                        .sub()
                            .list()
                            .ref(BulkInsertConverter.OrderColumnConverter.meta)
                            .scope(BulkInsert::getOrderList)
                            .and()
                        .sub_keyword(Other.GROUP_END)
                        .and()

                    .sub()
                        .description("format")
                        .optional(d -> d.getFormat() == null)
                        .sub()
                            .optional(d -> checkIsStart(11,d))
                            .keyword(Other.DELIMITER)
                            .and()
                        .sub_keyword(BulkInsert.WithEnum.FORMAT)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub("'CSV'")
                            .scope(BulkInsert::getFormat)
                            .and()
                        .and()

                    .sub()
                        .description("field_quote")
                        .optional(d -> d.getFieldQuote() == null)
                        .sub()
                            .optional(d -> checkIsStart(12,d))
                            .keyword(Other.DELIMITER)
                            .and()
                        .sub_keyword(BulkInsert.WithEnum.FIELDQUOTE)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub("'quote_characters'")
                            .scope(BulkInsert::getFieldQuote)
                            .and()
                        .and()

                    .build();
    // @formatter:on

    // @formatter:off
    public static BlockMeta metaBulk =
            new BlockMetaBuilder<Void,OpenRowSet>()
                    .description("bulk")
                    .sub_keyword(Keywords.BULK)
                    .sub("'data_file'")
                        .scope(d -> d.getBulkInsert().getFormDataFile())
                        .and()
                    .sub()
                        .sub("FORMATFILE = 'format_file_path' [ <bulk_options> ]")
                            .ref(metaBulkOptions)
                            .scope(d -> d.getBulkInsert())
                            .syntax_reference_remove()
                            .and()
                        .sub()
                            .optional(d -> d.getSingle() == null)
                            .sub_keyword(Other.DELIMITER)
                            .sub(EnumConverterUtil.getSyntaxString(OpenRowSet.Single.class))
                                .scope(d -> d.getSingle().name())
                                .and()
                            .syntax_indentation_right()
                            .and()
                        .syntax_indentation_right()
                        .and()
                    .build();
    // @formatter:on





    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,OpenRowSet>()
                    .overall("OPENROWSET")
                    .sub_keyword(Function.Keywords.OPENROWSET)
                    .sub_keyword(Other.GROUP_START)
                    .sub()
                        .czse_ref(d -> d.getBulkInsert() == null, metaBase)
                        .czse_ref(d -> d.getBulkInsert() != null, metaBulk)
                        .syntax_line()
                        .syntax_sub_line()
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }


    /*
    Simplified code
    Attributes use ',' split, only the first item is not needed.
    Each Attribute must determine whether it is the first item.
     */

    public static List<Predicate<BulkInsert>> withAttributeOptionalPredicate;

    static {
        withAttributeOptionalPredicate = new ArrayList<>();
        withAttributeOptionalPredicate.add(d -> d.getFormatFile() == null);
        withAttributeOptionalPredicate.add(d -> d.getCodePage() == null);
        withAttributeOptionalPredicate.add(d -> d.getDataSource() == null);
        withAttributeOptionalPredicate.add(d -> d.getErrorFile() == null);
        withAttributeOptionalPredicate.add(d -> d.getErrorFileDataSource() == null);
        withAttributeOptionalPredicate.add(d -> d.getFirstRow() == null);
        withAttributeOptionalPredicate.add(d -> d.getLastRow() == null);
        withAttributeOptionalPredicate.add(d -> d.getMaxErrors() == null);
        withAttributeOptionalPredicate.add(d -> d.getRowsPerBatch() == null);
        withAttributeOptionalPredicate.add(d -> d.getOrderList() == null);
        withAttributeOptionalPredicate.add(d -> d.getFormat() == null);
        withAttributeOptionalPredicate.add(d -> d.getFieldQuote() == null);
    }

    /**
     * Check 0 to (index-1) Predicate,
     * If all optional, prove index is start;
     * If any one not optional, prove index is not start
     * @param index
     * @param bulkInsert
     * @return
     */
    public static boolean checkIsStart(int index,BulkInsert bulkInsert){
        final Boolean[] result = {true};
        withAttributeOptionalPredicate.subList(0,index)
                .stream()
                .filter(p -> !p.test(bulkInsert))
                .findAny()
                .ifPresent(p -> {
                    result[0] = false;
                });
        return result[0];
    }
}
