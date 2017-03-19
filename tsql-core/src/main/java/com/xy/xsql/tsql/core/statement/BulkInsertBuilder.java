package com.xy.xsql.tsql.core.statement;

import com.xy.xsql.core.builder.BaseBuilder;
import com.xy.xsql.tsql.model.datatype.StringConstant;
import com.xy.xsql.tsql.model.element.ColumnName;
import com.xy.xsql.tsql.model.element.TableName;
import com.xy.xsql.tsql.model.statement.dml.BulkInsert;

import java.util.Arrays;
import java.util.List;

import static com.xy.xsql.tsql.core.expression.ExpressionBuilder.e_number;
import static com.xy.xsql.tsql.core.expression.ExpressionBuilder.e_string;

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

    public BulkInsert done() {
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
        bulkInsert.setFormDataFile(e_string(dataFile));
        return this;
    }

    /**
     * [ [ , ] BATCHSIZE = batch_size ]
     * @param batchSize
     * @return This
     */
    public BulkInsertBuilder withBatchSize(Integer batchSize){
        bulkInsert.setBatchSize(e_number(batchSize));
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
    public BulkInsertBuilder withCodePage(String codePage){
        bulkInsert.setCodePage(e_string(codePage));
        return this;
    }

    /**
     * [ [ , ] CODEPAGE = { 'ACP' | 'OEM' | 'RAW' | 'code_page' } ]
     * @param codePage
     * @return This
     */
    public BulkInsertBuilder withCodePage(BulkInsert.CodePage codePage){
        bulkInsert.setCodePage(codePage.getValue());
        return this;
    }

    /**
     * [ [ , ] DATAFILETYPE =
     * { 'char' | 'native'| 'widechar' | 'widenative' } ]
     * @deprecated can't set other value
     * @param dataFileType
     * @return This
     */
    @Deprecated
    public BulkInsertBuilder withDataFileType(StringConstant dataFileType){
        bulkInsert.setDataFileType(dataFileType);
        return this;
    }

    /**
     * [ [ , ] DATAFILETYPE =
     * { 'char' | 'native'| 'widechar' | 'widenative' } ]
     * @param dataFileType
     * @return
     */
    public BulkInsertBuilder withDataFileType(BulkInsert.DataFileType dataFileType){
        bulkInsert.setDataFileType(dataFileType.getValue());
        return this;
    }

    /**
     * [ [ , ] DATASOURCE = 'data_source_name' ]
     * @param dataSourceName
     * @return This
     */
    public BulkInsertBuilder withDataSource(String dataSourceName){
        bulkInsert.setDataSource(e_string(dataSourceName));
        return this;
    }

    /**
     * [ [ , ] ERRORFILE = 'file_name' ]
     * @param fileName
     * @return This
     */
    public BulkInsertBuilder withErrorFile(String fileName){
        bulkInsert.setErrorFile(e_string(fileName));
        return this;
    }

    /**
     * [ [ , ] ERRORFILE_DATASOURCE = 'data_source_name' ]
     * @param dataSourceName
     * @return This
     */
    public BulkInsertBuilder withErrorFileDataSource(String dataSourceName){
        bulkInsert.setErrorFileDataSource(e_string(dataSourceName));
        return this;
    }

    /**
     * [ [ , ] FIRSTROW = first_row ]
     * @param firstRow
     * @return This
     */
    public BulkInsertBuilder withFirstRow(Integer firstRow){
        bulkInsert.setFirstRow(e_number(firstRow));
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
        bulkInsert.setFormatFileDataSource(e_string(dataSourceName));
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
        bulkInsert.setKilobytesPerBatch(e_number(kilobytesPerBatch));
        return this;
    }

    /**
     * [ [ , ] LASTROW = last_row ]
     * @param lastRow
     * @return This
     */
    public BulkInsertBuilder withLastRow(Integer lastRow){
        bulkInsert.setLastRow(e_number(lastRow));
        return this;
    }

    /**
     * [ [ , ] MAXERRORS = max_errors ]
     * @param maxErrors
     * @return This
     */
    public BulkInsertBuilder withMaxErrors(Integer maxErrors){
        bulkInsert.setMaxErrors(e_number(maxErrors));
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
        bulkInsert.setRowsPerBatch(e_number(rowsPerBatch));
        return this;
    }

    /**
     * [ [ , ] ROWTERMINATOR = 'row_terminator' ]
     * @param rowTerminator
     * @return This
     */
    public BulkInsertBuilder withRowTerminator(String rowTerminator){
        bulkInsert.setRowTerminator(e_string(rowTerminator));
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
        bulkInsert.setFormat(e_string(format));
        return this;
    }

    /**
     * [ [ , ] FIELDQUOTE = 'quote_characters']
     * @param quoteCharacters
     * @return This
     */
    public BulkInsertBuilder withFieldQuote(String quoteCharacters){
        bulkInsert.setFieldQuote(e_string(quoteCharacters));
        return this;
    }

    /**
     * [ [ , ] FORMATFILE = 'format_file_path' ]
     * @param formatFilePath
     * @return This
     */
    public BulkInsertBuilder withFormatFile(String formatFilePath){
        bulkInsert.setFormatFile(e_string(formatFilePath));
        return this;
    }

    /**
     * [ [ , ] FIELDTERMINATOR = 'field_terminator' ]
     * @param fieldTerminator
     * @return This
     */
    public BulkInsertBuilder withFieldTerminator(String fieldTerminator){
        bulkInsert.setFieldTerminator(e_string(fieldTerminator));
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


    /**
     * Quick set
     * @return
     */
    public static BulkInsertBuilder BULK_INSERT(){
        return new BulkInsertBuilder();
    }

    /**
     * Quick set
     * @param tableName
     * @return
     */
    public BulkInsertBuilder $(TableName tableName){
        return withTableViewName(tableName);
    }

    /**
     * Quick set
     * @param dataFile
     * @return
     */
    public BulkInsertBuilder $From(String dataFile){
        return withFrom(dataFile);
    }

    /**
     * Quick set
     * @param items
     * @return
     */
    public BulkInsertBuilder $With(BulkInsert.WithSetter... items){
        Arrays.stream(items)
                .forEach(item -> item.set(bulkInsert));
        return this;
    }

    //CODEPAGE
    public static BulkInsert.CodePage _code_page(String codePage){
        return new BulkInsert.CodePage(codePage);
    }
    public static BulkInsert.CodePage _ACP(){
        return BulkInsert.ACP;
    }
    public static BulkInsert.CodePage _OEM(){
        return BulkInsert.OEM;
    }
    public static BulkInsert.CodePage _RAW(){
        return BulkInsert.RAW;
    }

    //DATAFILETYPE
    public static BulkInsert.DataFileType _char(){
        return BulkInsert.Char;
    }
    public static BulkInsert.DataFileType _native(){
        return BulkInsert.Native;
    }
    public static BulkInsert.DataFileType _wideChar(){
        return BulkInsert.WideChar;
    }
    public static BulkInsert.DataFileType _wideNative(){
        return BulkInsert.WideNative;
    }



    public static BulkInsert.WithSetter BATCHSIZE(Integer batchSize){
        return new BulkInsert.BatchSize(batchSize);
    }

    public static BulkInsert.WithSetter CHECK_CONSTRAINTS(){
        return new BulkInsert.CheckConstraints();
    }

    public static BulkInsert.WithSetter CODEPAGE(String codePage){
        return new BulkInsert.CodePage(codePage);
    }

    @Deprecated
    public static BulkInsert.WithSetter CODEPAGE(StringConstant codePage){
        return new BulkInsert.CodePage(codePage);
    }

    public static BulkInsert.WithSetter CODEPAGE(BulkInsert.CodePage codePage){
        return codePage;
    }

    @Deprecated
    public static BulkInsert.WithSetter DATAFILETYPE(String dataFileType){
        return new BulkInsert.DataFileType(dataFileType);
    }

    @Deprecated
    public static BulkInsert.WithSetter DATAFILETYPE(StringConstant dataFileType){
        return new BulkInsert.DataFileType(dataFileType);
    }

    public static BulkInsert.WithSetter DATAFILETYPE(BulkInsert.DataFileType dataFileType){
        return dataFileType;
    }

    public static BulkInsert.WithSetter DATASOURCE(String dataSource){
        return new BulkInsert.DataSource(dataSource);
    }

    public static BulkInsert.WithSetter ERRORFILE(String errorFile){
        return new BulkInsert.ErrorFile(errorFile);
    }

    public static BulkInsert.WithSetter ERRORFILE_DATASOURCE(String errorFileDataSource){
        return new BulkInsert.ErrorFileDataSource(errorFileDataSource);
    }

    public static BulkInsert.WithSetter FIRSTROW(Integer firstRow){
        return new BulkInsert.FirstRow(firstRow);
    }

    public static BulkInsert.WithSetter FIRE_TRIGGERS(){
        return new BulkInsert.FireTriggers();
    }

    public static BulkInsert.WithSetter FORMATFILE_DATASOURCE(String dataSourceName){
        return new BulkInsert.FormatFileDataSource(dataSourceName);
    }

    public static BulkInsert.WithSetter KEEPIDENTITY(){
        return new BulkInsert.KeepIdentity();
    }

    public static BulkInsert.WithSetter KEEPNULLS(){
        return new BulkInsert.KeepNulls();
    }

    public static BulkInsert.WithSetter KILOBYTES_PER_BATCH(Integer kilobytesPerBatch){
        return new BulkInsert.KiloBytesPerBatch(kilobytesPerBatch);
    }

    public static BulkInsert.WithSetter LASTROW(Integer lastRow){
        return new BulkInsert.LastRow(lastRow);
    }

    public static BulkInsert.WithSetter MAXERRORS(Integer maxErrors){
        return new BulkInsert.MaxErrors(maxErrors);
    }

    public static BulkInsert.WithSetter ORDER(ColumnName... columns){
        return new BulkInsert.Order(columns);
    }

    public static BulkInsert.WithSetter ORDER_ASC(ColumnName... columns){
        return new BulkInsert.Order(true,false,columns);
    }

    public static BulkInsert.WithSetter ORDER_DESC(ColumnName... columns){
        return new BulkInsert.Order(false,true,columns);
    }

    public static BulkInsert.WithSetter ROWS_PER_BATCH(Integer rowsPerBatch){
        return new BulkInsert.RowsPerBatch(rowsPerBatch);
    }

    public static BulkInsert.WithSetter ROWTERMINATOR(String rowTerminator){
        return new BulkInsert.RowTerminator(rowTerminator);
    }

    public static BulkInsert.WithSetter TABLOCK (){
        return new BulkInsert.TabLock();
    }

    public static BulkInsert.WithSetter FORMAT(String format){
        return new BulkInsert.Format(format);
    }

    public static BulkInsert.WithSetter FIELDQUOTE(String fieldQuote){
        return new BulkInsert.FieldQuote(fieldQuote);
    }

    public static BulkInsert.WithSetter FORMATFILE(String formatFile){
        return new BulkInsert.FormatFile(formatFile);
    }

    public static BulkInsert.WithSetter FIELDTERMINATOR(String fieldTerminator){
        return new BulkInsert.FieldTerminator(fieldTerminator);
    }

}
