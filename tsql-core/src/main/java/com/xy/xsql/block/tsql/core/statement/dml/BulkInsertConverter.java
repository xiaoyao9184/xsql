package com.xy.xsql.block.tsql.core.statement.dml;

import com.xy.xsql.block.core.BlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.statement.dml.BulkInsert;
import com.xy.xsql.tsql.model.statement.dml.Insert;

/**
 * Created by xiaoyao9184 on 2017/6/17.
 */
public class BulkInsertConverter
        implements BlockConverter<BulkInsert> {

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
                    //TODO
                    //.sub()
                    .sub_keyword(Other.GROUP_END)
                    .startNewline()
                    .and();
    // @formatter:on

    public static ReferenceBlock meta() {
        return builder.build();
    }

    @Override
    public Block convert(BulkInsert bulkInsert) {
        return builder
                .data(bulkInsert)
                .build();
    }
}
