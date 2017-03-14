package com.xy.xsql.orm.core.sql.statements;

import com.xy.xsql.orm.core.BaseBuilder;
import com.xy.xsql.orm.core.SubBuilder;
import com.xy.xsql.orm.data.sql.element.UnknownString;
import com.xy.xsql.orm.data.sql.element.info.Order;
import com.xy.xsql.orm.data.sql.element.info.TableName;
import com.xy.xsql.orm.data.sql.expression.StringExpression;
import com.xy.xsql.orm.data.sql.statements.dml.BulkInsert;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2017/1/9.
 */
public class BulkInsertBuilder implements BaseBuilder<Void,BulkInsert> {

    protected BulkInsert bulkInsert;

    public BulkInsertBuilder(){
        this.bulkInsert = new BulkInsert();
    }


    @Override
    public BulkInsert build(Void aVoid) {
        return bulkInsert;
    }


    /**
     * [ database_name . [ schema_name ] . | schema_name . ] [ table_name | view_name ]
     * @return This
     */
    public BulkInsertBuilder withTableViewName(TableName tableViewName){
        bulkInsert.setTableOrView(tableViewName);
        return this;
    }

    /**
     * FROM 'data_file'
     * @param dataFile
     * @return This
     */
    public BulkInsertBuilder withFrom(String dataFile){
        bulkInsert.setFormDataFile(dataFile);
        return this;
    }

    /**
     * [ [ , ] BATCHSIZE = batch_size ]
     * @param batchSize
     * @return This
     */
    public BulkInsertBuilder withBatchSize(Integer batchSize){
        bulkInsert.setBatchSize(batchSize);
        return this;
    }

    /**
     * [ [ , ] CHECK_CONSTRAINTS ]
     * @return This
     */
    public BulkInsertBuilder withCheckConstraints(){
        bulkInsert.setCheckConstraints(true);
        return this;
    }

    /**
     * [ [ , ] CODEPAGE = { 'ACP' | 'OEM' | 'RAW' | 'code_page' } ]
     * @param codePage
     * @return This
     */
    public BulkInsertBuilder withCodePage(StringExpression codePage){
        bulkInsert.setCodePage(codePage);
        return this;
    }

    /**
     * [ [ , ] DATAFILETYPE =
     * { 'char' | 'native'| 'widechar' | 'widenative' } ]
     * @param dataFileType
     * @return This
     */
    public BulkInsertBuilder withDataFileType(StringExpression dataFileType){
        bulkInsert.setDataFileType(dataFileType);
        return this;
    }

    /**
     * [ [ , ] DATASOURCE = 'data_source_name' ]
     * @param dataSourceName
     * @return This
     */
    public BulkInsertBuilder withDataSource(String dataSourceName){
        bulkInsert.setDataSource(new UnknownString(dataSourceName));
        return this;
    }

    /**
     * [ [ , ] ERRORFILE = 'file_name' ]
     * @param fileName
     * @return This
     */
    public BulkInsertBuilder withErrorFile(String fileName){
        bulkInsert.setErrorFile(new UnknownString(fileName));
        return this;
    }

    /**
     * [ [ , ] ERRORFILE_DATASOURCE = 'data_source_name' ]
     * @param dataSourceName
     * @return This
     */
    public BulkInsertBuilder withErrorFileDataSource(String dataSourceName){
        bulkInsert.setErrorFileDataSource(new UnknownString(dataSourceName));
        return this;
    }

    /**
     * [ [ , ] FIRSTROW = first_row ]
     * @param firstRow
     * @return This
     */
    public BulkInsertBuilder withFirstRow(Integer firstRow){
        bulkInsert.setFirstRow(firstRow);
        return this;
    }

    /**
     * [ [ , ] FIRE_TRIGGERS ]
     * @return This
     */
    public BulkInsertBuilder withFireTriggers(){
        bulkInsert.setFireTriggers(true);
        return this;
    }

    /**
     * [ [ , ] FORMATFILE_DATASOURCE = 'data_source_name' ]
     * @param dataSourceName
     * @return This
     */
    public BulkInsertBuilder withFireTriggers(String dataSourceName){
        bulkInsert.setFormatFileDataSource(new UnknownString(dataSourceName));
        return this;
    }

    /**
     * [ [ , ] KEEPIDENTITY ]
     * @return This
     */
    public BulkInsertBuilder withKeepIdentity(){
        bulkInsert.setKeepIdentity(true);
        return this;
    }

    /**
     * [ [ , ] KEEPNULLS ]
     * @return This
     */
    public BulkInsertBuilder withKeepNulls(){
        bulkInsert.setKeepNulls(true);
        return this;
    }

    /**
     * [ [ , ] KILOBYTES_PER_BATCH = kilobytes_per_batch ]
     * @param kilobytesPerBatch
     * @return This
     */
    public BulkInsertBuilder withKilobytesPerBatch(Integer kilobytesPerBatch){
        bulkInsert.setKilobytesPerBatch(kilobytesPerBatch);
        return this;
    }

    /**
     * [ [ , ] LASTROW = last_row ]
     * @param lastRow
     * @return This
     */
    public BulkInsertBuilder withLastRow(Integer lastRow){
        bulkInsert.setLastRow(lastRow);
        return this;
    }

    /**
     * [ [ , ] MAXERRORS = max_errors ]
     * @param maxErrors
     * @return This
     */
    public BulkInsertBuilder withMaxErrors(Integer maxErrors){
        bulkInsert.setMaxErrors(maxErrors);
        return this;
    }

    /**
     * [ [ , ] ORDER ( { column [ ASC | DESC ] } [ ,...n ] ) ]
     * @param orderList
     * @return This
     */
    public BulkInsertBuilder withOrderList(List<Order> orderList){
        bulkInsert.setOrderList(orderList);
        return this;
    }

    /**
     * [ [ , ] ROWS_PER_BATCH = rows_per_batch ]
     * @param rowsPerBatch
     * @return This
     */
    public BulkInsertBuilder withRowsPerBatch(Integer rowsPerBatch){
        bulkInsert.setRowsPerBatch(rowsPerBatch);
        return this;
    }

    /**
     * [ [ , ] ROWTERMINATOR = 'row_terminator' ]
     * @param rowTerminator
     * @return This
     */
    public BulkInsertBuilder withRowTerminator(String rowTerminator){
        bulkInsert.setRowTerminator(new UnknownString(rowTerminator));
        return this;
    }
    /**
     * [ [ , ] TABLOCK ]
     * @return This
     */
    public BulkInsertBuilder withTabLock(){
        bulkInsert.setTabLock(true);
        return this;
    }

    //-- input file format options
    /**
     * [ [ , ] FORMAT = 'CSV' ]
     * @param format
     * @return This
     */
    public BulkInsertBuilder withFormat(String format){
        bulkInsert.setFormat(new UnknownString(format));
        return this;
    }

    /**
     * [ [ , ] FIELDQUOTE = 'quote_characters']
     * @param quoteCharacters
     * @return This
     */
    public BulkInsertBuilder withFieldQuote(String quoteCharacters){
        bulkInsert.setFieldQuote(new UnknownString(quoteCharacters));
        return this;
    }

    /**
     * [ [ , ] FORMATFILE = 'format_file_path' ]
     * @param formatFilePath
     * @return This
     */
    public BulkInsertBuilder withFormatFile(String formatFilePath){
        bulkInsert.setFormatFile(new UnknownString(formatFilePath));
        return this;
    }

    /**
     * [ [ , ] FIELDTERMINATOR = 'field_terminator' ]
     * @param fieldTerminator
     * @return This
     */
    public BulkInsertBuilder withFieldTerminator(String fieldTerminator){
        bulkInsert.setFieldTerminator(new UnknownString(fieldTerminator));
        return this;
    }

    //may be repeat
//    /**
//     * [ [ , ] ROWTERMINATOR = 'row_terminator' ]
//     * @param rowTerminator
//     * @return
//     */
//    public BulkInsertBuilder withRowTerminator(String rowTerminator){
//        bulkInsert.setRowTerminator(new UnknownString(rowTerminator));
//        return this;
//    }

    //CODEPAGE
    public static StringExpression _code_page(String codePage){
        return new StringExpression(codePage);
    }
    public static StringExpression _ACP(){
        return BulkInsert.CodePage.ACP.toExpression();
    }
    public static StringExpression _OEM(){
        return BulkInsert.CodePage.OEM.toExpression();
    }
    public static StringExpression _RAW(){
        return BulkInsert.CodePage.RAW.toExpression();
    }

    //DATAFILETYPE
    public static StringExpression _char(){
        return BulkInsert.DataFileType.Char.toExpression();
    }
    public static StringExpression _native(){
        return BulkInsert.DataFileType.Native.toExpression();
    }
    public static StringExpression _wideChar(){
        return BulkInsert.DataFileType.WideChar.toExpression();
    }
    public static StringExpression _wideNative(){
        return BulkInsert.DataFileType.WideNative.toExpression();
    }
}
