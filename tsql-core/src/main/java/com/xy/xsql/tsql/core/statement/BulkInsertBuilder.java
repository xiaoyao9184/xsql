package com.xy.xsql.tsql.core.statement;

import com.xy.xsql.core.BaseBuilder;
import com.xy.xsql.tsql.model.datatype.StringConstant;
import com.xy.xsql.tsql.model.element.TableName;
import com.xy.xsql.tsql.model.statement.dml.BulkInsert;

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
    public BulkInsertBuilder withCodePage(StringConstant codePage){
        bulkInsert.setCodePage(codePage);
        return this;
    }

    /**
     * [ [ , ] DATAFILETYPE =
     * { 'char' | 'native'| 'widechar' | 'widenative' } ]
     * @param dataFileType
     * @return This
     */
    public BulkInsertBuilder withDataFileType(StringConstant dataFileType){
        bulkInsert.setDataFileType(dataFileType);
        return this;
    }

    /**
     * [ [ , ] DATASOURCE = 'data_source_name' ]
     * @param dataSourceName
     * @return This
     */
    public BulkInsertBuilder withDataSource(String dataSourceName){
        bulkInsert.setDataSource(new StringConstant(dataSourceName));
        return this;
    }

    /**
     * [ [ , ] ERRORFILE = 'file_name' ]
     * @param fileName
     * @return This
     */
    public BulkInsertBuilder withErrorFile(String fileName){
        bulkInsert.setErrorFile(new StringConstant(fileName));
        return this;
    }

    /**
     * [ [ , ] ERRORFILE_DATASOURCE = 'data_source_name' ]
     * @param dataSourceName
     * @return This
     */
    public BulkInsertBuilder withErrorFileDataSource(String dataSourceName){
        bulkInsert.setErrorFileDataSource(new StringConstant(dataSourceName));
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
        bulkInsert.setFormatFileDataSource(new StringConstant(dataSourceName));
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
    public BulkInsertBuilder withOrderList(List<BulkInsert.OrderColumn> orderList){
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
        bulkInsert.setRowTerminator(new StringConstant(rowTerminator));
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
        bulkInsert.setFormat(new StringConstant(format));
        return this;
    }

    /**
     * [ [ , ] FIELDQUOTE = 'quote_characters']
     * @param quoteCharacters
     * @return This
     */
    public BulkInsertBuilder withFieldQuote(String quoteCharacters){
        bulkInsert.setFieldQuote(new StringConstant(quoteCharacters));
        return this;
    }

    /**
     * [ [ , ] FORMATFILE = 'format_file_path' ]
     * @param formatFilePath
     * @return This
     */
    public BulkInsertBuilder withFormatFile(String formatFilePath){
        bulkInsert.setFormatFile(new StringConstant(formatFilePath));
        return this;
    }

    /**
     * [ [ , ] FIELDTERMINATOR = 'field_terminator' ]
     * @param fieldTerminator
     * @return This
     */
    public BulkInsertBuilder withFieldTerminator(String fieldTerminator){
        bulkInsert.setFieldTerminator(new StringConstant(fieldTerminator));
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
    public static StringConstant _code_page(String codePage){
        return new StringConstant(codePage);
    }
    public static StringConstant _ACP(){
        return BulkInsert.CodePage.ACP.toExpression();
    }
    public static StringConstant _OEM(){
        return BulkInsert.CodePage.OEM.toExpression();
    }
    public static StringConstant _RAW(){
        return BulkInsert.CodePage.RAW.toExpression();
    }

    //DATAFILETYPE
    public static StringConstant _char(){
        return BulkInsert.DataFileType.Char.toExpression();
    }
    public static StringConstant _native(){
        return BulkInsert.DataFileType.Native.toExpression();
    }
    public static StringConstant _wideChar(){
        return BulkInsert.DataFileType.WideChar.toExpression();
    }
    public static StringConstant _wideNative(){
        return BulkInsert.DataFileType.WideNative.toExpression();
    }
}
