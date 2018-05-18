package com.xy.xsql.tsql.model.statements;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.datatypes.table.TableName;
import com.xy.xsql.tsql.model.queries.select.OrderBy;

import java.util.List;

/**
 * https://msdn.microsoft.com/en-us/library/ms188365.aspx
 *
 BULK INSERT
 [ database_name . [ schema_name ] . | schema_name . ] [ table_name | view_name ]
 FROM 'data_file'
 [ WITH
 (
 [ [ , ] BATCHSIZE = batch_size ]
 [ [ , ] CHECK_CONSTRAINTS ]
 [ [ , ] CODEPAGE = { 'ACP' | 'OEM' | 'RAW' | 'code_page' } ]
 [ [ , ] DATAFILETYPE =
 { 'char' | 'native'| 'widechar' | 'widenative' } ]
 [ [ , ] DATASOURCE = 'data_source_name' ]
 [ [ , ] ERRORFILE = 'file_name' ]
 [ [ , ] ERRORFILE_DATASOURCE = 'data_source_name' ]
 [ [ , ] FIRSTROW = first_row ]
 [ [ , ] FIRE_TRIGGERS ]
 [ [ , ] FORMATFILE_DATASOURCE = 'data_source_name' ]
 [ [ , ] KEEPIDENTITY ]
 [ [ , ] KEEPNULLS ]
 [ [ , ] KILOBYTES_PER_BATCH = kilobytes_per_batch ]
 [ [ , ] LASTROW = last_row ]
 [ [ , ] MAXERRORS = max_errors ]
 [ [ , ] ORDER ( { column [ ASC | DESC ] } [ ,...n ] ) ]
 [ [ , ] ROWS_PER_BATCH = rows_per_batch ]
 [ [ , ] ROWTERMINATOR = 'row_terminator' ]
 [ [ , ] TABLOCK ]

 -- input file format options
 [ [ , ] FORMAT = 'CSV' ]
 [ [ , ] FIELDQUOTE = 'quote_characters']
 [ [ , ] FORMATFILE = 'format_file_path' ]
 [ [ , ] FIELDTERMINATOR = 'field_terminator' ]
 [ [ , ] ROWTERMINATOR = 'row_terminator' ]
 )]

 * Created by xiaoyao9184 on 2016/12/19.
 */
public class BulkInsert implements Statement {

    //
    private TableName tableOrView;
    //FROM 'data_file'
    private StringConstant formDataFile;

    //[ WITH (...)]
    //[ [ , ] BATCHSIZE = batch_size ]
    private NumberConstant batchSize;
    //[ [ , ] CHECK_CONSTRAINTS ]
    private boolean checkConstraints;
    //[ [ , ] CODEPAGE = { 'ACP' | 'OEM' | 'RAW' | 'code_page' } ]
    private StringConstant codePage;
    //[ [ , ] DATAFILETYPE =
    //{ 'char' | 'native'| 'widechar' | 'widenative' } ]
    private StringConstant dataFileType;
    //[ [ , ] DATASOURCE = 'data_source_name' ]
    private StringConstant dataSource;
    //[ [ , ] ERRORFILE = 'file_name' ]
    private StringConstant errorFile;
    //[ [ , ] ERRORFILE_DATASOURCE = 'data_source_name' ]
    private StringConstant errorFileDataSource;
    //[ [ , ] FIRSTROW = first_row ]
    private NumberConstant firstRow;
    //[ [ , ] FIRE_TRIGGERS ]
    private boolean fireTriggers;
    //[ [ , ] FORMATFILE_DATASOURCE = 'data_source_name' ]
    private StringConstant formatFileDataSource;
    //[ [ , ] KEEPIDENTITY ]
    private boolean keepIdentity;
    //[ [ , ] KEEPNULLS ]
    private boolean keepNulls;
    //[ [ , ] KILOBYTES_PER_BATCH = kilobytes_per_batch ]
    private NumberConstant kilobytesPerBatch;
    //[ [ , ] LASTROW = last_row ]
    private NumberConstant lastRow;
    //[ [ , ] MAXERRORS = max_errors ]
    private NumberConstant maxErrors;
    //[ [ , ] ORDER ( { column [ ASC | DESC ] } [ ,...n ] ) ]
    private List<OrderColumn> orderList;
    //[ [ , ] ROWS_PER_BATCH = rows_per_batch ]
    private NumberConstant rowsPerBatch;
    //[ [ , ] ROWTERMINATOR = 'row_terminator' ]
    private StringConstant rowTerminator;
    //[ [ , ] TABLOCK ]
    private boolean tabLock;

    //-- input file format options
    //[ [ , ] FORMAT = 'CSV' ]
    private StringConstant format;
    //[ [ , ] FIELDQUOTE = 'quote_characters']
    private StringConstant fieldQuote;
    //[ [ , ] FORMATFILE = 'format_file_path' ]
    private StringConstant formatFile;
    //[ [ , ] FIELDTERMINATOR = 'field_terminator' ]
    private StringConstant fieldTerminator;
    //may be repeat
    //[ [ , ] ROWTERMINATOR = 'row_terminator' ]
//    private StringConstant rowTerminator;


    public TableName getTableOrView() {
        return tableOrView;
    }

    public void setTableOrView(TableName tableOrView) {
        this.tableOrView = tableOrView;
    }

    public StringConstant getFormDataFile() {
        return formDataFile;
    }

    public void setFormDataFile(StringConstant formDataFile) {
        this.formDataFile = formDataFile;
    }

    public NumberConstant getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(NumberConstant batchSize) {
        this.batchSize = batchSize;
    }

    public boolean isCheckConstraints() {
        return checkConstraints;
    }

    public void setCheckConstraints(boolean checkConstraints) {
        this.checkConstraints = checkConstraints;
    }

    public StringConstant getCodePage() {
        return codePage;
    }

    public void setCodePage(StringConstant codePage) {
        this.codePage = codePage;
    }

    public StringConstant getDataFileType() {
        return dataFileType;
    }

    public void setDataFileType(StringConstant dataFileType) {
        this.dataFileType = dataFileType;
    }

    public StringConstant getDataSource() {
        return dataSource;
    }

    public void setDataSource(StringConstant dataSource) {
        this.dataSource = dataSource;
    }

    public StringConstant getErrorFile() {
        return errorFile;
    }

    public void setErrorFile(StringConstant errorFile) {
        this.errorFile = errorFile;
    }

    public StringConstant getErrorFileDataSource() {
        return errorFileDataSource;
    }

    public void setErrorFileDataSource(StringConstant errorFileDataSource) {
        this.errorFileDataSource = errorFileDataSource;
    }

    public NumberConstant getFirstRow() {
        return firstRow;
    }

    public void setFirstRow(NumberConstant firstRow) {
        this.firstRow = firstRow;
    }

    public boolean isFireTriggers() {
        return fireTriggers;
    }

    public void setFireTriggers(boolean fireTriggers) {
        this.fireTriggers = fireTriggers;
    }

    public StringConstant getFormatFileDataSource() {
        return formatFileDataSource;
    }

    public void setFormatFileDataSource(StringConstant formatFileDataSource) {
        this.formatFileDataSource = formatFileDataSource;
    }

    public boolean isKeepIdentity() {
        return keepIdentity;
    }

    public void setKeepIdentity(boolean keepIdentity) {
        this.keepIdentity = keepIdentity;
    }

    public boolean isKeepNulls() {
        return keepNulls;
    }

    public void setKeepNulls(boolean keepNulls) {
        this.keepNulls = keepNulls;
    }

    public NumberConstant getKilobytesPerBatch() {
        return kilobytesPerBatch;
    }

    public void setKilobytesPerBatch(NumberConstant kilobytesPerBatch) {
        this.kilobytesPerBatch = kilobytesPerBatch;
    }

    public NumberConstant getLastRow() {
        return lastRow;
    }

    public void setLastRow(NumberConstant lastRow) {
        this.lastRow = lastRow;
    }

    public NumberConstant getMaxErrors() {
        return maxErrors;
    }

    public void setMaxErrors(NumberConstant maxErrors) {
        this.maxErrors = maxErrors;
    }

    public List<OrderColumn> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderColumn> orderList) {
        this.orderList = orderList;
    }

    public NumberConstant getRowsPerBatch() {
        return rowsPerBatch;
    }

    public void setRowsPerBatch(NumberConstant rowsPerBatch) {
        this.rowsPerBatch = rowsPerBatch;
    }

    public StringConstant getRowTerminator() {
        return rowTerminator;
    }

    public void setRowTerminator(StringConstant rowTerminator) {
        this.rowTerminator = rowTerminator;
    }

    public boolean isTabLock() {
        return tabLock;
    }

    public void setTabLock(boolean tabLock) {
        this.tabLock = tabLock;
    }

    public StringConstant getFormat() {
        return format;
    }

    public void setFormat(StringConstant format) {
        this.format = format;
    }

    public StringConstant getFieldQuote() {
        return fieldQuote;
    }

    public void setFieldQuote(StringConstant fieldQuote) {
        this.fieldQuote = fieldQuote;
    }

    public StringConstant getFormatFile() {
        return formatFile;
    }

    public void setFormatFile(StringConstant formatFile) {
        this.formatFile = formatFile;
    }

    public StringConstant getFieldTerminator() {
        return fieldTerminator;
    }

    public void setFieldTerminator(StringConstant fieldTerminator) {
        this.fieldTerminator = fieldTerminator;
    }


    /**
     * WITH keywords
     */
    public enum WithEnum {
        BATCHSIZE,
        CHECK_CONSTRAINTS,
        CODEPAGE,
        DATAFILETYPE,
        DATASOURCE,
        ERRORFILE,
        ERRORFILE_DATASOURCE,
        FIRSTROW,
        FIRE_TRIGGERS,
        FORMATFILE_DATASOURCE,
        KEEPIDENTITY,
        KEEPNULLS,
        KILOBYTES_PER_BATCH,
        LASTROW,
        MAXERRORS,
        ORDER,
        ROWS_PER_BATCH,
        ROWTERMINATOR,
        TABLOCK,
        FORMAT,
        FIELDQUOTE,
        FORMATFILE,
        FIELDTERMINATOR;
//        ROWTERMINATOR;

        @Override
        public String toString(){
            return this.name();
        }
    }


    /**
     * ORDER
     * TODO maybe use OrderBy.Item, limit with builder
     * @see OrderBy.Item
     */
    public static class OrderColumn {

        private ColumnName column;
        private boolean useAsc;
        private boolean useDesc;

        public OrderColumn(){}

        public ColumnName getColumn() {
            return column;
        }

        public void setColumn(ColumnName column) {
            this.column = column;
        }

        public boolean isUseAsc() {
            return useAsc;
        }

        public void setUseAsc(boolean useAsc) {
            this.useAsc = useAsc;
        }

        public boolean isUseDesc() {
            return useDesc;
        }

        public void setUseDesc(boolean useDesc) {
            this.useDesc = useDesc;
        }

    }

}
