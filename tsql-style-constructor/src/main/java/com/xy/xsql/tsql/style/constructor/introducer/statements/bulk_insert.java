package com.xy.xsql.tsql.style.constructor.introducer.statements;

import com.xy.xsql.core.builder.CodeBuilder;
import com.xy.xsql.tsql.builder.chain.statements.BulkInsertBuilder;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.datatypes.table.TableName;
import com.xy.xsql.tsql.style.constructor.builder.queries.select.b_ORDER_BY;
import com.xy.xsql.tsql.style.constructor.builder.statements.b_BULK_INSERT;

import java.util.Arrays;

/**
 * Created by xiaoyao9184 on 2018/5/9.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public interface bulk_insert {

    //

    static b_BULK_INSERT BULK_INSERT(
            TableName table_name,
            b_BULK_INSERT.b_FROM from,
            b_BULK_INSERT.b_WITH with){
        return new b_BULK_INSERT(){
            {
                withFrom(from.build());
                withTableViewName(table_name);
                with.build().stream()
                        .map(CodeBuilder::build)
                        .forEach(setter -> setter.config(this.target));
            }
        };
    }
    static b_BULK_INSERT BULK_INSERT(
            TableName table_name,
            b_BULK_INSERT.b_FROM from){
        return new b_BULK_INSERT(){
            {
                withFrom(from.build());
                withTableViewName(table_name);
            }
        };
    }
    static b_BULK_INSERT BULK_INSERT(
            b_BULK_INSERT.b_FROM from){
        return new b_BULK_INSERT(){
            {
                withFrom(from.build());
            }
        };
    }


    //

    static b_BULK_INSERT.b_FROM FROM(String data_file){
        return new b_BULK_INSERT.b_FROM(data_file);
    }

    //

    static b_BULK_INSERT.b_WITH WITH(b_BULK_INSERT.b$with_item... with_item){
        return new b_BULK_INSERT.b_WITH(){
            {
                this.target.addAll(Arrays.asList(with_item));
            }
        };
    }

    //

    static b_BULK_INSERT.b$with_item BATCHSIZE(Integer batchSize){
        return new b_BULK_INSERT.b$with_item(
                BulkInsertBuilder.WithSetter.$Batchsize(batchSize)
        );
    }
    static b_BULK_INSERT.b$with_item CHECK_CONSTRAINTS(){
        return new b_BULK_INSERT.b$with_item(
                BulkInsertBuilder.WithSetter.$CheckConstraints()
        );
    }
    static b_BULK_INSERT.b$with_item CODEPAGE(String codePage){
        return new b_BULK_INSERT.b$with_item(
                BulkInsertBuilder.WithSetter.$Codepage(codePage)
        );
    }
    static b_BULK_INSERT.b$with_item CODEPAGE(BulkInsertBuilder.CodePage codePage){
        return new b_BULK_INSERT.b$with_item(
                BulkInsertBuilder.WithSetter.$Codepage(codePage)
        );
    }
    static b_BULK_INSERT.b$with_item CODEPAGE_ACP(){
        return new b_BULK_INSERT.b$with_item(
                BulkInsertBuilder.WithSetter.$Codepage(BulkInsertBuilder.WithSetter.$ACP())
        );
    }
    static b_BULK_INSERT.b$with_item CODEPAGE_OEM(){
        return new b_BULK_INSERT.b$with_item(
                BulkInsertBuilder.WithSetter.$Codepage(BulkInsertBuilder.WithSetter.$OEM())
        );
    }
    static b_BULK_INSERT.b$with_item CODEPAGE_RAW(){
        return new b_BULK_INSERT.b$with_item(
                BulkInsertBuilder.WithSetter.$Codepage(BulkInsertBuilder.WithSetter.$RAW())
        );
    }
    static b_BULK_INSERT.b$with_item DATAFILETYPE_char(){
        return new b_BULK_INSERT.b$with_item(
                BulkInsertBuilder.WithSetter.$Datafiletype(BulkInsertBuilder.WithSetter.$char())
        );
    }
    static b_BULK_INSERT.b$with_item DATAFILETYPE_native(){
        return new b_BULK_INSERT.b$with_item(
                BulkInsertBuilder.WithSetter.$Datafiletype(BulkInsertBuilder.WithSetter.$native())
        );
    }
    static b_BULK_INSERT.b$with_item DATAFILETYPE_widechar(){
        return new b_BULK_INSERT.b$with_item(
                BulkInsertBuilder.WithSetter.$Datafiletype(BulkInsertBuilder.WithSetter.$widechar())
        );
    }
    static b_BULK_INSERT.b$with_item DATAFILETYPE_widenative(){
        return new b_BULK_INSERT.b$with_item(
                BulkInsertBuilder.WithSetter.$Datafiletype(BulkInsertBuilder.WithSetter.$widenative())
        );
    }
    static b_BULK_INSERT.b$with_item DATASOURCE(String dataSource){
        return new b_BULK_INSERT.b$with_item(
                BulkInsertBuilder.WithSetter.$Datasource(dataSource)
        );
    }
    static b_BULK_INSERT.b$with_item ERRORFILE(String errorFile){
        return new b_BULK_INSERT.b$with_item(
                BulkInsertBuilder.WithSetter.$Errorfile(errorFile)
        );
    }
    static b_BULK_INSERT.b$with_item ERRORFILE_DATASOURCE(String errorFileDataSource){
        return new b_BULK_INSERT.b$with_item(
                BulkInsertBuilder.WithSetter.$ErrorfileDatasource(errorFileDataSource)
        );
    }
    static b_BULK_INSERT.b$with_item FIRSTROW(Integer firstRow){
        return new b_BULK_INSERT.b$with_item(
                BulkInsertBuilder.WithSetter.$Firstrow(firstRow)
        );
    }
    static b_BULK_INSERT.b$with_item FIRE_TRIGGERS(){
        return new b_BULK_INSERT.b$with_item(
                BulkInsertBuilder.WithSetter.$FireTriggers()
        );
    }
    static b_BULK_INSERT.b$with_item FORMATFILE_DATASOURCE(String dataSourceName){
        return new b_BULK_INSERT.b$with_item(
                BulkInsertBuilder.WithSetter.$FormatfileDatasource(dataSourceName)
        );
    }
    static b_BULK_INSERT.b$with_item KEEPIDENTITY(){
        return new b_BULK_INSERT.b$with_item(
                BulkInsertBuilder.WithSetter.$Keepidentity()
        );
    }
    static b_BULK_INSERT.b$with_item KEEPNULLS(){
        return new b_BULK_INSERT.b$with_item(
                BulkInsertBuilder.WithSetter.$Keepnulls()
        );
    }
    static b_BULK_INSERT.b$with_item KILOBYTES_PER_BATCH(Integer kilobytesPerBatch){
        return new b_BULK_INSERT.b$with_item(
                BulkInsertBuilder.WithSetter.$KilobytesPerBatch(kilobytesPerBatch)
        );
    }
    static b_BULK_INSERT.b$with_item LASTROW(Integer lastRow){
        return new b_BULK_INSERT.b$with_item(
                BulkInsertBuilder.WithSetter.$Lastrow(lastRow)
        );
    }
    static b_BULK_INSERT.b$with_item MAXERRORS(Integer maxErrors){
        return new b_BULK_INSERT.b$with_item(
                BulkInsertBuilder.WithSetter.$Maxerrors(maxErrors)
        );
    }
    static b_BULK_INSERT.b$with_item ORDER(ColumnName columns){
        //@formatter:off
        return new b_BULK_INSERT.b$with_order()
                .withItem()
                    .withColumnName(columns)
                    .and()
                .and();
        //@formatter:on
    }
    static b_BULK_INSERT.b$with_item ORDER(ColumnName columns, b_ORDER_BY.k_ASC asc){
        //@formatter:off
        return new b_BULK_INSERT.b$with_order()
                .withItem()
                    .withColumnName(columns)
                    .withAsc()
                    .and()
                .and();
        //@formatter:on
    }
    static b_BULK_INSERT.b$with_item ORDER(ColumnName columns, b_ORDER_BY.k_DESC desc){
        //@formatter:off
        return new b_BULK_INSERT.b$with_order()
                .withItem()
                    .withColumnName(columns)
                    .withDesc()
                    .and()
                .and();
        //@formatter:on
    }
    static b_BULK_INSERT.b$with_item ROWS_PER_BATCH(Integer rowsPerBatch){
        return new b_BULK_INSERT.b$with_item(
                BulkInsertBuilder.WithSetter.$RowsPerBatch(rowsPerBatch)
        );
    }
    static b_BULK_INSERT.b$with_item ROWTERMINATOR(String rowTerminator){
        return new b_BULK_INSERT.b$with_item(
                BulkInsertBuilder.WithSetter.$Rowterminator(rowTerminator)
        );
    }
    static b_BULK_INSERT.b$with_item TABLOCK(){
        return new b_BULK_INSERT.b$with_item(
                BulkInsertBuilder.WithSetter.$Tablock()
        );
    }
    static b_BULK_INSERT.b$with_item FORMAT(String format){
        return new b_BULK_INSERT.b$with_item(
                BulkInsertBuilder.WithSetter.$Format(format)
        );
    }
    static b_BULK_INSERT.b$with_item FIELDQUOTE(String fieldQuote){
        return new b_BULK_INSERT.b$with_item(
                BulkInsertBuilder.WithSetter.$Fieldquote(fieldQuote)
        );
    }
    static b_BULK_INSERT.b$with_item FORMATFILE(String formatFile){
        return new b_BULK_INSERT.b$with_item(
                BulkInsertBuilder.WithSetter.$Formatfile(formatFile)
        );
    }
    static b_BULK_INSERT.b$with_item FIELDTERMINATOR(String fieldTerminator){
        return new b_BULK_INSERT.b$with_item(
                BulkInsertBuilder.WithSetter.$Fieldterminator(fieldTerminator)
        );
    }

}
