package com.xy.xsql.orm.core.sql.statements;

import com.xy.xsql.orm.core.BaseBuilder;
import com.xy.xsql.orm.core.SubBuilder;
import com.xy.xsql.orm.core.sql.clause.TopBuilder;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.clause.Top;
import com.xy.xsql.orm.data.sql.element.UnknownString;
import com.xy.xsql.orm.data.sql.element.info.Column;
import com.xy.xsql.orm.data.sql.element.info.GroupList;
import com.xy.xsql.orm.data.sql.element.info.Order;
import com.xy.xsql.orm.data.sql.element.info.TableName;
import com.xy.xsql.orm.data.sql.statements.dml.BulkInsert;
import com.xy.xsql.orm.data.sql.statements.dml.Insert;

import java.util.ArrayList;
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
     * @return
     */
    public BulkInsertBuilder withFrom(String dataFile){
        bulkInsert.setFormDataFile(dataFile);
        return this;
    }



    /**
     * [ [ , ] BATCHSIZE = batch_size ]
     * @param batchSize
     * @return
     */
    public BulkInsertBuilder withBatchSize(Integer batchSize){
        bulkInsert.setBatchSize(batchSize);
        return this;
    }
    //[ [ , ] CHECK_CONSTRAINTS ]
    public BulkInsertBuilder withCheckConstraints(){
        bulkInsert.setCheckConstraints(true);
        return this;
    }
    //[ [ , ] CODEPAGE = { 'ACP' | 'OEM' | 'RAW' | 'code_page' } ]
    public BulkInsertBuilder withCodePage(String codePage){
        bulkInsert.setCodePage(new UnknownString(codePage));
        return this;
    }
    //[ [ , ] DATAFILETYPE =
    //{ 'char' | 'native'| 'widechar' | 'widenative' } ]
    public BulkInsertBuilder withDataFileType(String dataFileType){
        bulkInsert.setDataFileType(new UnknownString(dataFileType));
        return this;
    }
    //[ [ , ] DATASOURCE = 'data_source_name' ]
    public BulkInsertBuilder withDataSource(String dataSourceName){
        bulkInsert.setDataSource(new UnknownString(dataSourceName));
        return this;
    }
    /**
     * [ [ , ] ERRORFILE = 'file_name' ]
     * @param fileName
     * @return
     */
    public BulkInsertBuilder withErrorFile(String fileName){
        bulkInsert.setErrorFile(new UnknownString(fileName));
        return this;
    }
    /**
     * [ [ , ] ERRORFILE_DATASOURCE = 'data_source_name' ]
     * @param dataSourceName
     * @return
     */
    public BulkInsertBuilder withErrorFileDataSource(String dataSourceName){
        bulkInsert.setErrorFileDataSource(new UnknownString(dataSourceName));
        return this;
    }
    /**
     * [ [ , ] FIRSTROW = first_row ]
     * @param firstRow
     * @return
     */
    public BulkInsertBuilder withFirstRow(Integer firstRow){
        bulkInsert.setFirstRow(firstRow);
        return this;
    }
    /**
     * [ [ , ] FIRE_TRIGGERS ]
     * @return
     */
    public BulkInsertBuilder withFireTriggers(){
        bulkInsert.setFireTriggers(true);
        return this;
    }
    /**
     * [ [ , ] FORMATFILE_DATASOURCE = 'data_source_name' ]
     * @param dataSourceName
     * @return
     */
    public BulkInsertBuilder withFireTriggers(String dataSourceName){
        bulkInsert.setFormatFileDataSource(new UnknownString(dataSourceName));
        return this;
    }
    /**
     * [ [ , ] KEEPIDENTITY ]
     * @return
     */
    public BulkInsertBuilder withKeepIdentity(){
        bulkInsert.setKeepIdentity(true);
        return this;
    }
    /**
     * [ [ , ] KEEPNULLS ]
     * @return
     */
    public BulkInsertBuilder withKeepNulls(){
        bulkInsert.setKeepNulls(true);
        return this;
    }
    /**
     * [ [ , ] KILOBYTES_PER_BATCH = kilobytes_per_batch ]
     * @param kilobytesPerBatch
     * @return
     */
    public BulkInsertBuilder withKilobytesPerBatch(Integer kilobytesPerBatch){
        bulkInsert.setKilobytesPerBatch(kilobytesPerBatch);
        return this;
    }
    /**
     * [ [ , ] LASTROW = last_row ]
     * @param lastRow
     * @return
     */
    public BulkInsertBuilder withLastRow(Integer lastRow){
        bulkInsert.setLastRow(lastRow);
        return this;
    }
    /**
     * [ [ , ] MAXERRORS = max_errors ]
     * @param maxErrors
     * @return
     */
    public BulkInsertBuilder withMaxErrors(Integer maxErrors){
        bulkInsert.setMaxErrors(maxErrors);
        return this;
    }
    /**
     * [ [ , ] ORDER ( { column [ ASC | DESC ] } [ ,...n ] ) ]
     * @param orderList
     * @return
     */
    public BulkInsertBuilder withOrderList(List<Order> orderList){
        bulkInsert.setOrderList(orderList);
        return this;
    }

    /**
     * [ [ , ] ROWS_PER_BATCH = rows_per_batch ]
     * @param rowsPerBatch
     * @return
     */
    public BulkInsertBuilder withRowsPerBatch(Integer rowsPerBatch){
        bulkInsert.setRowsPerBatch(rowsPerBatch);
        return this;
    }
    //TODO may be repeat
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
     * [ [ , ] TABLOCK ]
     * @return
     */
    public BulkInsertBuilder withTabLock(){
        bulkInsert.setTabLock(true);
        return this;
    }

    //-- input file format options
    /**
     * [ [ , ] FORMAT = 'CSV' ]
     * @param format
     * @return
     */
    public BulkInsertBuilder withFormat(String format){
        bulkInsert.setFormat(new UnknownString(format));
        return this;
    }
    /**
     * [ [ , ] FIELDQUOTE = 'quote_characters']
     * @param quoteCharacters
     * @return
     */
    public BulkInsertBuilder withFieldQuote(String quoteCharacters){
        bulkInsert.setFieldQuote(new UnknownString(quoteCharacters));
        return this;
    }
    /**
     * [ [ , ] FORMATFILE = 'format_file_path' ]
     * @param formatFilePath
     * @return
     */
    public BulkInsertBuilder withFormatFile(String formatFilePath){
        bulkInsert.setFormatFile(new UnknownString(formatFilePath));
        return this;
    }
    /**
     * [ [ , ] FIELDTERMINATOR = 'field_terminator' ]
     * @param fieldTerminator
     * @return
     */
    public BulkInsertBuilder withFieldTerminator(String fieldTerminator){
        bulkInsert.setFieldTerminator(new UnknownString(fieldTerminator));
        return this;
    }
    /**
     * [ [ , ] ROWTERMINATOR = 'row_terminator' ]
     * @param rowTerminator
     * @return
     */
    public BulkInsertBuilder withRowTerminator(String rowTerminator){
        bulkInsert.setRowTerminator(new UnknownString(rowTerminator));
        return this;
    }
}
