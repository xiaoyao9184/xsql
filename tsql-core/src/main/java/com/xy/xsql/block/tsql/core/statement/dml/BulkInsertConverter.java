package com.xy.xsql.block.tsql.core.statement.dml;

import com.xy.xsql.block.core.ReferenceBlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.operator.Assignment;
import com.xy.xsql.tsql.model.statement.dml.BulkInsert;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by xiaoyao9184 on 2017/6/17.
 */
public class BulkInsertConverter
        implements ReferenceBlockConverter<BulkInsert> {

    // @formatter:off
    private static ReferenceBlockBuilder<Void,BulkInsert> builder =
            new ReferenceBlockBuilder<Void,BulkInsert>()
                .sub_keyword(Keywords.BULK)
                .sub_keyword(Keywords.INSERT)
                .sub("[ database_name . [ schema_name ] . | schema_name . ][ table_name | view_name ] ")
                    .data(BulkInsert::getTableOrView)
                    .startNewline()
                    .and()
                .sub()
                    .keyword(Keywords.FROM)
                    .startNewline()
                    .and()
                .sub("'data_file'")
                    .data(BulkInsert::getFormDataFile)
                    .and()
                .sub()
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
                        .optional(d -> d.getBatchSize() == null)
                        .sub_keyword(BulkInsert.WithEnum.BATCHSIZE)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub("batch_size")
                            .data(BulkInsert::getBatchSize)
                            .and()
                        .and()
                    .sub()
                        .optional(BulkInsert::isCheckConstraints)
                        .sub()
                            .optional(d -> checkIsStart(1,d))
                            .keyword(Other.DELIMITER)
                            .and()
                        .sub_keyword(BulkInsert.WithEnum.CHECK_CONSTRAINTS)
                        .and()
                    .sub()
                        .optional(d -> d.getCodePage() == null)
                        .sub()
                            .optional(d -> checkIsStart(2,d))
                            .keyword(Other.DELIMITER)
                            .and()
                        .sub_keyword(BulkInsert.WithEnum.CODEPAGE)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub()
                            .required()
                            .czse(d -> "'ACP'".equals(d.getCodePage().toString()),"'ACP'")
                                .and()
                            .czse(d -> "'OEM'".equals(d.getCodePage().toString()),"'OEM'")
                                .and()
                            .czse(d -> "'RAW'".equals(d.getCodePage().toString()),"'RAW'")
                                .and()
                            .czse(d -> true,"'code_page'")
                                .and()
                            .and()
                        .and()
                    .sub()
                        .optional(d -> d.getDataFileType() == null)
                        .sub()
                            .optional(d -> checkIsStart(3,d))
                            .keyword(Other.DELIMITER)
                            .and()
                        .sub_keyword(BulkInsert.WithEnum.DATAFILETYPE)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub()
                            .required()
                            .czse(d -> "'char'".equals(d.getDataFileType().toString()),"'char'")
                                .and()
                            .czse(d -> "'native'".equals(d.getDataFileType().toString()),"'native'")
                                .and()
                            .czse(d -> "'widechar'".equals(d.getDataFileType().toString()),"'widechar'")
                                .and()
                            .czse(d -> "'widenative'".equals(d.getDataFileType().toString()),"'widenative'")
                                .and()
                            .and()
                        .and()
                    .sub()
                        .optional(d -> d.getDataSource() == null)
                        .sub()
                            .optional(d -> checkIsStart(4,d))
                            .keyword(Other.DELIMITER)
                            .and()
                        .sub_keyword(BulkInsert.WithEnum.DATASOURCE)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub("'data_source_name'")
                            .data(BulkInsert::getDataSource)
                            .and()
                        .and()
                    .sub()
                        .optional(d -> d.getErrorFile() == null)
                        .sub()
                            .optional(d -> checkIsStart(5,d))
                            .keyword(Other.DELIMITER)
                            .and()
                        .sub_keyword(BulkInsert.WithEnum.ERRORFILE)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub("'file_name'")
                            .data(BulkInsert::getErrorFile)
                            .and()
                        .and()
                    .sub()
                        .optional(d -> d.getErrorFileDataSource() == null)
                        .sub()
                            .optional(d -> checkIsStart(6,d))
                            .keyword(Other.DELIMITER)
                            .and()
                        .sub_keyword(BulkInsert.WithEnum.ERRORFILE_DATASOURCE)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub("'data_source_name'")
                            .data(BulkInsert::getErrorFileDataSource)
                            .and()
                        .and()
                    .sub()
                        .optional(d -> d.getFirstRow() == null)
                        .sub()
                            .optional(d -> checkIsStart(7,d))
                            .keyword(Other.DELIMITER)
                            .and()
                        .sub_keyword(BulkInsert.WithEnum.FIRSTROW)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub("first_row")
                            .data(BulkInsert::getFirstRow)
                            .and()
                        .and()
                    .sub()
                        .optional(BulkInsert::isFireTriggers)
                        .sub()
                            .optional(d -> checkIsStart(8,d))
                            .keyword(Other.DELIMITER)
                            .and()
                        .sub_keyword(BulkInsert.WithEnum.FIRE_TRIGGERS)
                        .and()
                    .sub()
                        .optional(d -> d.getFormatFileDataSource() == null)
                        .sub()
                            .optional(d -> checkIsStart(9,d))
                            .keyword(Other.DELIMITER)
                            .and()
                        .sub_keyword(BulkInsert.WithEnum.FORMATFILE_DATASOURCE)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub("data_source_name")
                            .data(BulkInsert::getFormatFileDataSource)
                            .and()
                        .and()
                    .sub()
                        .optional(BulkInsert::isKeepIdentity)
                        .sub()
                            .optional(d -> checkIsStart(10,d))
                            .keyword(Other.DELIMITER)
                            .and()
                        .sub_keyword(BulkInsert.WithEnum.KEEPIDENTITY)
                        .and()
                    .sub()
                        .optional(BulkInsert::isKeepNulls)
                        .sub()
                            .optional(d -> checkIsStart(11,d))
                            .keyword(Other.DELIMITER)
                            .and()
                        .sub_keyword(BulkInsert.WithEnum.KEEPNULLS)
                        .and()
                    .sub()
                        .optional(d -> d.getKilobytesPerBatch() == null)
                        .sub()
                            .optional(d -> checkIsStart(12,d))
                            .keyword(Other.DELIMITER)
                            .and()
                        .sub_keyword(BulkInsert.WithEnum.KILOBYTES_PER_BATCH)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub("kilobytes_per_batch")
                            .data(BulkInsert::getKilobytesPerBatch)
                            .and()
                        .and()
                    .sub()
                        .optional(d -> d.getLastRow() == null)
                        .sub()
                            .optional(d -> checkIsStart(13,d))
                            .keyword(Other.DELIMITER)
                            .and()
                        .sub_keyword(BulkInsert.WithEnum.LASTROW)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub("last_row")
                            .data(BulkInsert::getLastRow)
                            .and()
                        .and()
                    .sub()
                        .optional(d -> d.getMaxErrors() == null)
                        .sub()
                            .optional(d -> checkIsStart(14,d))
                            .keyword(Other.DELIMITER)
                            .and()
                        .sub_keyword(BulkInsert.WithEnum.MAXERRORS)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub("max_errors")
                            .data(BulkInsert::getMaxErrors)
                            .and()
                        .and()
                    .sub()
                        .optional(d -> d.getOrderList() == null)
                        .sub()
                            .optional(d -> checkIsStart(15,d))
                            .keyword(Other.DELIMITER)
                            .and()
                        .sub_keyword(BulkInsert.WithEnum.ORDER)
                        .sub_keyword(Other.GROUP_START)
                        .sub()
                            .list()
                            .ref(OrderColumnConverter.meta())
                            .data(BulkInsert::getOrderList)
                            .and()
                        .sub_keyword(Other.GROUP_END)
                        .and()
                    .sub()
                        .optional(d -> d.getRowsPerBatch() == null)
                        .sub()
                            .optional(d -> checkIsStart(16,d))
                            .keyword(Other.DELIMITER)
                            .and()
                        .sub_keyword(BulkInsert.WithEnum.ROWS_PER_BATCH)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub("rows_per_batch")
                            .data(BulkInsert::getRowsPerBatch)
                            .and()
                        .and()
                    .sub()
                        .optional(d -> d.getRowTerminator() == null)
                        .sub()
                            .optional(d -> checkIsStart(7,d))
                            .keyword(Other.DELIMITER)
                            .and()
                        .sub_keyword(BulkInsert.WithEnum.ROWTERMINATOR)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub("'row_terminator'")
                            .data(BulkInsert::getRowTerminator)
                            .and()
                        .and()
                    .sub()
                        .optional(BulkInsert::isTabLock)
                        .sub()
                            .optional(d -> checkIsStart(18,d))
                            .keyword(Other.DELIMITER)
                            .and()
                        .sub_keyword(BulkInsert.WithEnum.TABLOCK)
                        .and()
                    .sub()
                        .optional(d -> d.getFormat() == null)
                        .sub()
                            .optional(d -> checkIsStart(19,d))
                            .keyword(Other.DELIMITER)
                            .and()
                        .sub_keyword(BulkInsert.WithEnum.FORMAT)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub("'CSV'")
                            .data(BulkInsert::getFormat)
                            .and()
                        .and()
                    .sub()
                        .optional(d -> d.getFieldQuote() == null)
                        .sub()
                            .optional(d -> checkIsStart(20,d))
                            .keyword(Other.DELIMITER)
                            .and()
                        .sub_keyword(BulkInsert.WithEnum.FIELDQUOTE)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub("'quote_characters'")
                            .data(BulkInsert::getFieldQuote)
                            .and()
                        .and()
                    .sub()
                        .optional(d -> d.getFormatFile() == null)
                        .sub()
                            .optional(d -> checkIsStart(21,d))
                            .keyword(Other.DELIMITER)
                            .and()
                        .sub_keyword(BulkInsert.WithEnum.FORMATFILE)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub("'format_file_path'")
                            .data(BulkInsert::getFormatFile)
                            .and()
                        .and()
                    .sub()
                        .optional(d -> d.getFieldTerminator() == null)
                        .sub()
                            .optional(d -> checkIsStart(22,d))
                            .keyword(Other.DELIMITER)
                            .and()
                        .sub_keyword(BulkInsert.WithEnum.FIELDTERMINATOR)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub("'field_terminator'")
                            .data(BulkInsert::getFieldTerminator)
                            .and()
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .startNewline()
                    .subTakeLine()
                    .and();
    // @formatter:on

    public static ReferenceBlock meta() {
        return builder.build();
    }

    @Override
    public ReferenceBlock convert(BulkInsert bulkInsert) {
        return builder
                .data(bulkInsert)
                .build();
    }


    public static class OrderColumnConverter
            implements ReferenceBlockConverter<BulkInsert.OrderColumn> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,BulkInsert.OrderColumn> builder =
                new ReferenceBlockBuilder<Void,BulkInsert.OrderColumn>()
                        .required()
                        .sub("column")
                            .data(BulkInsert.OrderColumn::getColumn)
                            .and()
                        .sub()
                            .optional(d -> !d.isUseAsc() && !d.isUseDesc())
                            .czse_keyword(BulkInsert.OrderColumn::isUseAsc, Keywords.ASC)
                            .czse_keyword(BulkInsert.OrderColumn::isUseDesc, Keywords.DESC)
                            .and();
        // @formatter:on

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public ReferenceBlock convert(BulkInsert.OrderColumn column) {
            return builder
                    .data(column)
                    .build();
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
        withAttributeOptionalPredicate.subList(0,index-1)
                .stream()
                .filter(p -> !p.test(bulkInsert))
                .findAny()
                .ifPresent(p -> {
                    result[0] = false;
                });
        return result[0];
    }

}
