package com.xy.xsql.tsql.converter.statements;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.converter.datatypes.constants.StringConstantConverter;
import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.elements.operators.Assignment;
import com.xy.xsql.tsql.model.statements.BulkInsert;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static com.xy.xsql.tsql.builder.chain.statements.BulkInsertBuilder.WithSetter.*;

/**
 * Created by xiaoyao9184 on 2017/6/17.
 */
public class BulkInsertConverter
        implements ModelMetaBlockConverter<BulkInsert> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,BulkInsert>()
                    .overall("BULK INSERT")
                    .sub_keyword(Keywords.BULK)
                    .sub_keyword(Keywords.INSERT)
                    .sub("[ database_name . [ schema_name ] . | schema_name . ][ table_name | view_name ]")
                        .scope(BulkInsert::getTableOrView)
                        .syntax_line()
                        .format_indentation_right()
                        .and()
                    .sub()
                        .keyword(Keywords.FROM)
                        .syntax_line()
                        .format_line(true)
                        .and()
                    .sub("'data_file'")
                        .scope(BulkInsert::getFormDataFile)
                        .format_indentation_right()
                        .and()
                    .sub()
                        .description("with attributes")
                        .optional(d -> {
                            return
                                d.getBatchSize() == null &&
                                d.isCheckConstraints() &&
                                d.getCodePage() == null &&
                                d.getDataFileType() == null &&
                                d.getDataSource() == null &&
                                d.getErrorFile() == null &&
                                d.getErrorFileDataSource() == null &&
                                d.getFirstRow() == null &&
                                d.isFireTriggers() &&
                                d.getFormatFileDataSource() == null &&
                                d.isKeepIdentity() &&
                                d.isKeepNulls() &&
                                d.getKilobytesPerBatch() == null &&
                                d.getLastRow() == null &&
                                d.getMaxErrors() == null &&
                                d.getOrderList() == null &&
                                d.getRowsPerBatch() == null &&
                                d.getRowTerminator() == null &&
                                d.getFormat() == null &&
                                d.getFieldQuote() == null &&
                                d.getFormatFile() == null &&
                                d.getFieldTerminator() == null;
                        })
                        .sub_keyword(Keywords.WITH)
                        .sub_keyword(Other.GROUP_START)
                        .sub()
                            .sub()
                                .description("batch_size")
                                .optional(d -> d.getBatchSize() == null)
                                .sub_keyword(BulkInsert.WithEnum.BATCHSIZE)
                                .sub_keyword(Assignment.ASSIGNMENT)
                                .sub("batch_size")
                                    .scope(BulkInsert::getBatchSize)
                                    .and()
                                .and()
                            .sub()
                                .description("check_constraints")
                                .optional(d -> !d.isCheckConstraints())
                                .sub()
                                    .optional(d -> checkIsStart(1,d))
                                    .keyword(Other.DELIMITER)
                                    .and()
                                .sub_keyword(BulkInsert.WithEnum.CHECK_CONSTRAINTS)
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
                                .description("data_file_type")
                                .optional(d -> d.getDataFileType() == null)
                                .sub()
                                    .optional(d -> checkIsStart(3,d))
                                    .keyword(Other.DELIMITER)
                                    .and()
                                .sub_keyword(BulkInsert.WithEnum.DATAFILETYPE)
                                .sub_keyword(Assignment.ASSIGNMENT)
                                .sub()
                                    .syntax_required()
                                    .czse(d -> $char().equals(d.getDataFileType()),"'char'")
                                        .ref(StringConstantConverter.class)
                                        .scope(d -> d.getDataFileType())
                                        .syntax_reference_remove()
                                        .and()
                                    .czse(d -> $native().equals(d.getDataFileType()),"'native'")
                                        .ref(StringConstantConverter.class)
                                        .scope(d -> d.getDataFileType())
                                        .syntax_reference_remove()
                                        .and()
                                    .czse(d -> $widechar().equals(d.getDataFileType()),"'widechar'")
                                        .ref(StringConstantConverter.class)
                                        .scope(d -> d.getDataFileType())
                                        .syntax_reference_remove()
                                        .and()
                                    .czse(d -> $widenative().equals(d.getDataFileType()),"'widenative'")
                                        .ref(StringConstantConverter.class)
                                        .scope(d -> d.getDataFileType())
                                        .syntax_reference_remove()
                                        .and()
                                    .and()
                                .and()
                            .sub()
                                .description("data_source")
                                .optional(d -> d.getDataSource() == null)
                                .sub()
                                    .optional(d -> checkIsStart(4,d))
                                    .keyword(Other.DELIMITER)
                                    .and()
                                .sub_keyword(BulkInsert.WithEnum.DATASOURCE)
                                .sub_keyword(Assignment.ASSIGNMENT)
                                .sub("'data_source_name'")
                                    .scope(BulkInsert::getDataSource)
                                    .and()
                                .and()
                            .sub()
                                .description("error_file")
                                .optional(d -> d.getErrorFile() == null)
                                .sub()
                                    .optional(d -> checkIsStart(5,d))
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
                                    .optional(d -> checkIsStart(6,d))
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
                                    .optional(d -> checkIsStart(7,d))
                                    .keyword(Other.DELIMITER)
                                    .and()
                                .sub_keyword(BulkInsert.WithEnum.FIRSTROW)
                                .sub_keyword(Assignment.ASSIGNMENT)
                                .sub("first_row")
                                    .scope(BulkInsert::getFirstRow)
                                    .and()
                                .and()
                            .sub()
                                .description("fire_triggers")
                                .optional(d -> !d.isFireTriggers())
                                .sub()
                                    .optional(d -> checkIsStart(8,d))
                                    .keyword(Other.DELIMITER)
                                    .and()
                                .sub_keyword(BulkInsert.WithEnum.FIRE_TRIGGERS)
                                .and()
                            .sub()
                                .description("format_file_data_source")
                                .optional(d -> d.getFormatFileDataSource() == null)
                                .sub()
                                    .optional(d -> checkIsStart(9,d))
                                    .keyword(Other.DELIMITER)
                                    .and()
                                .sub_keyword(BulkInsert.WithEnum.FORMATFILE_DATASOURCE)
                                .sub_keyword(Assignment.ASSIGNMENT)
                                .sub("data_source_name")
                                    .scope(BulkInsert::getFormatFileDataSource)
                                    .and()
                                .and()
                            .sub()
                                .description("keep_identity")
                                .optional(d -> !d.isKeepIdentity())
                                .sub()
                                    .optional(d -> checkIsStart(10,d))
                                    .keyword(Other.DELIMITER)
                                    .and()
                                .sub_keyword(BulkInsert.WithEnum.KEEPIDENTITY)
                                .and()
                            .sub()
                                .description("keep_nulls")
                                .optional(d -> !d.isKeepNulls())
                                .sub()
                                    .optional(d -> checkIsStart(11,d))
                                    .keyword(Other.DELIMITER)
                                    .and()
                                .sub_keyword(BulkInsert.WithEnum.KEEPNULLS)
                                .and()
                            .sub()
                                .description("kilobytes_per_batch")
                                .optional(d -> d.getKilobytesPerBatch() == null)
                                .sub()
                                    .optional(d -> checkIsStart(12,d))
                                    .keyword(Other.DELIMITER)
                                    .and()
                                .sub_keyword(BulkInsert.WithEnum.KILOBYTES_PER_BATCH)
                                .sub_keyword(Assignment.ASSIGNMENT)
                                .sub("kilobytes_per_batch")
                                    .scope(BulkInsert::getKilobytesPerBatch)
                                    .and()
                                .and()
                            .sub()
                                .description("last_row")
                                .optional(d -> d.getLastRow() == null)
                                .sub()
                                    .optional(d -> checkIsStart(13,d))
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
                                    .optional(d -> checkIsStart(14,d))
                                    .keyword(Other.DELIMITER)
                                    .and()
                                .sub_keyword(BulkInsert.WithEnum.MAXERRORS)
                                .sub_keyword(Assignment.ASSIGNMENT)
                                .sub("max_errors")
                                    .scope(BulkInsert::getMaxErrors)
                                    .and()
                                .and()
                            .sub()
                                .description("order")
                                .optional(d -> d.getOrderList() == null)
                                .sub()
                                    .optional(d -> checkIsStart(15,d))
                                    .keyword(Other.DELIMITER)
                                    .and()
                                .sub_keyword(BulkInsert.WithEnum.ORDER)
                                .sub_keyword(Other.GROUP_START)
                                .sub()
                                    .list()
                                    .ref(OrderColumnConverter.meta)
                                    .scope(BulkInsert::getOrderList)
                                    .and()
                                .sub_keyword(Other.GROUP_END)
                                .and()
                            .sub()
                                .description("rows_per_batch")
                                .optional(d -> d.getRowsPerBatch() == null)
                                .sub()
                                    .optional(d -> checkIsStart(16,d))
                                    .keyword(Other.DELIMITER)
                                    .and()
                                .sub_keyword(BulkInsert.WithEnum.ROWS_PER_BATCH)
                                .sub_keyword(Assignment.ASSIGNMENT)
                                .sub("rows_per_batch")
                                    .scope(BulkInsert::getRowsPerBatch)
                                    .and()
                                .and()
                            .sub()
                                .description("row_terminator")
                                .optional(d -> d.getRowTerminator() == null)
                                .sub()
                                    .optional(d -> checkIsStart(17,d))
                                    .keyword(Other.DELIMITER)
                                    .and()
                                .sub_keyword(BulkInsert.WithEnum.ROWTERMINATOR)
                                .sub_keyword(Assignment.ASSIGNMENT)
                                .sub("'row_terminator'")
                                    .scope(BulkInsert::getRowTerminator)
                                    .and()
                                .and()
                            .sub()
                                .description("tab_lock")
                                .optional(d -> !d.isTabLock())
                                .sub()
                                    .optional(d -> checkIsStart(18,d))
                                    .keyword(Other.DELIMITER)
                                    .and()
                                .sub_keyword(BulkInsert.WithEnum.TABLOCK)
                                .and()
                            .sub()
                                .description("format")
                                .optional(d -> d.getFormat() == null)
                                .sub()
                                    .optional(d -> checkIsStart(19,d))
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
                                    .optional(d -> checkIsStart(20,d))
                                    .keyword(Other.DELIMITER)
                                    .and()
                                .sub_keyword(BulkInsert.WithEnum.FIELDQUOTE)
                                .sub_keyword(Assignment.ASSIGNMENT)
                                .sub("'quote_characters'")
                                    .scope(BulkInsert::getFieldQuote)
                                    .and()
                                .and()
                            .sub()
                                .description("format_file")
                                .optional(d -> d.getFormatFile() == null)
                                .sub()
                                    .optional(d -> checkIsStart(21,d))
                                    .keyword(Other.DELIMITER)
                                    .and()
                                .sub_keyword(BulkInsert.WithEnum.FORMATFILE)
                                .sub_keyword(Assignment.ASSIGNMENT)
                                .sub("'format_file_path'")
                                    .scope(BulkInsert::getFormatFile)
                                    .and()
                                .and()
                            .sub()
                                .description("field_terminator")
                                .optional(d -> d.getFieldTerminator() == null)
                                .sub()
                                    .optional(d -> checkIsStart(22,d))
                                    .keyword(Other.DELIMITER)
                                    .and()
                                .sub_keyword(BulkInsert.WithEnum.FIELDTERMINATOR)
                                .sub_keyword(Assignment.ASSIGNMENT)
                                .sub("'field_terminator'")
                                    .scope(BulkInsert::getFieldTerminator)
                                    .and()
                                .and()
                            .syntax_sub_line()
                            .format_indentation_right()
                            .sub_format_line(true)
                            .and()
                        .sub_keyword(Other.GROUP_END)
                        .syntax_line()
                        .syntax_sub_line()
                        .format_line(true)
                        .and()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }


    public static class OrderColumnConverter
            implements ModelMetaBlockConverter<BulkInsert.OrderColumn> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,BulkInsert.OrderColumn>()
                        .syntax_required()
                        .sub("column")
                            .scope(BulkInsert.OrderColumn::getColumn)
                            .and()
                        .sub()
                            .optional(d -> !d.isUseAsc() && !d.isUseDesc())
                            .czse_keyword(BulkInsert.OrderColumn::isUseAsc, Keywords.ASC)
                            .czse_keyword(BulkInsert.OrderColumn::isUseDesc, Keywords.DESC)
                            .and()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }



    /*
    Simplified code
    Attributes use ',' split, only the first item is not needed.
    Each Attribute must determine whether it is the first item.
     */

    public static List<Predicate<BulkInsert>> withAttributeOptionalPredicate;

    static {
        withAttributeOptionalPredicate = new ArrayList<>();
        withAttributeOptionalPredicate.add(d -> d.getBatchSize() == null);
        withAttributeOptionalPredicate.add(d -> !d.isCheckConstraints());
        withAttributeOptionalPredicate.add(d -> d.getCodePage() == null);
        withAttributeOptionalPredicate.add(d -> d.getDataFileType() == null);
        withAttributeOptionalPredicate.add(d -> d.getDataSource() == null);
        withAttributeOptionalPredicate.add(d -> d.getErrorFile() == null);
        withAttributeOptionalPredicate.add(d -> d.getErrorFileDataSource() == null);
        withAttributeOptionalPredicate.add(d -> d.getFirstRow() == null);
        withAttributeOptionalPredicate.add(d -> !d.isFireTriggers());
        withAttributeOptionalPredicate.add(d -> d.getFormatFileDataSource() == null);
        withAttributeOptionalPredicate.add(d -> !d.isKeepIdentity());
        withAttributeOptionalPredicate.add(d -> !d.isKeepNulls());
        withAttributeOptionalPredicate.add(d -> d.getKilobytesPerBatch() == null);
        withAttributeOptionalPredicate.add(d -> d.getLastRow() == null);
        withAttributeOptionalPredicate.add(d -> d.getMaxErrors() == null);
        withAttributeOptionalPredicate.add(d -> d.getOrderList() == null);
        withAttributeOptionalPredicate.add(d -> d.getRowsPerBatch() == null);
        withAttributeOptionalPredicate.add(d -> d.getRowTerminator() == null);
        withAttributeOptionalPredicate.add(d -> !d.isTabLock());
        withAttributeOptionalPredicate.add(d -> d.getFormat() == null);
        withAttributeOptionalPredicate.add(d -> d.getFieldQuote() == null);
        withAttributeOptionalPredicate.add(d -> d.getFormatFile() == null);
        withAttributeOptionalPredicate.add(d -> d.getFieldTerminator() == null);
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
