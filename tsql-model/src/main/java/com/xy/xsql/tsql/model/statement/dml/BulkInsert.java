package com.xy.xsql.tsql.model.statement.dml;


import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.datatype.StringConstant;
import com.xy.xsql.tsql.model.element.ColumnName;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.element.TableName;
import com.xy.xsql.tsql.model.operator.Operators;
import com.xy.xsql.tsql.model.statement.Statement;
import com.xy.xsql.tsql.util.ListBlockBuilder;

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
    private String formDataFile;

    //[ WITH (...)]
    //[ [ , ] BATCHSIZE = batch_size ]
    private Integer batchSize;
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
    private Integer firstRow;
    //[ [ , ] FIRE_TRIGGERS ]
    private boolean fireTriggers;
    //[ [ , ] FORMATFILE_DATASOURCE = 'data_source_name' ]
    private StringConstant formatFileDataSource;
    //[ [ , ] KEEPIDENTITY ]
    private boolean keepIdentity;
    //[ [ , ] KEEPNULLS ]
    private boolean keepNulls;
    //[ [ , ] KILOBYTES_PER_BATCH = kilobytes_per_batch ]
    private Integer kilobytesPerBatch;
    //[ [ , ] LASTROW = last_row ]
    private Integer lastRow;
    //[ [ , ] MAXERRORS = max_errors ]
    private Integer maxErrors;
    //[ [ , ] ORDER ( { column [ ASC | DESC ] } [ ,...n ] ) ]
    private List<OrderColumn> orderList;
    //[ [ , ] ROWS_PER_BATCH = rows_per_batch ]
    private Integer rowsPerBatch;
    //[ [ , ] ROWTERMINATOR = 'row_terminator' ]
    private StringConstant rowTerminator;
    //[ [ , ] TABLOCK ]
    private boolean tabLock;

    //-- input file format options
    //[ [ , ] FORMAT = 'CSV' ]
    private StringConstant format = new StringConstant().withQuote();
    //[ [ , ] FIELDQUOTE = 'quote_characters']
    private StringConstant fieldQuote = new StringConstant().withQuote();
    //[ [ , ] FORMATFILE = 'format_file_path' ]
    private StringConstant formatFile = new StringConstant().withQuote();
    //[ [ , ] FIELDTERMINATOR = 'field_terminator' ]
    private StringConstant fieldTerminator = new StringConstant().withQuote();
    //may be repeat
    //[ [ , ] ROWTERMINATOR = 'row_terminator' ]
//    private StringConstant rowTerminator = new StringConstant().withQuote(true);


    public TableName getTableOrView() {
        return tableOrView;
    }

    public void setTableOrView(TableName tableOrView) {
        this.tableOrView = tableOrView;
    }

    public String getFormDataFile() {
        return formDataFile;
    }

    public void setFormDataFile(String formDataFile) {
        this.formDataFile = formDataFile;
    }

    public Integer getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(Integer batchSize) {
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

    public Integer getFirstRow() {
        return firstRow;
    }

    public void setFirstRow(Integer firstRow) {
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

    public Integer getKilobytesPerBatch() {
        return kilobytesPerBatch;
    }

    public void setKilobytesPerBatch(Integer kilobytesPerBatch) {
        this.kilobytesPerBatch = kilobytesPerBatch;
    }

    public Integer getLastRow() {
        return lastRow;
    }

    public void setLastRow(Integer lastRow) {
        this.lastRow = lastRow;
    }

    public Integer getMaxErrors() {
        return maxErrors;
    }

    public void setMaxErrors(Integer maxErrors) {
        this.maxErrors = maxErrors;
    }

    public List<OrderColumn> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderColumn> orderList) {
        this.orderList = orderList;
    }

    public Integer getRowsPerBatch() {
        return rowsPerBatch;
    }

    public void setRowsPerBatch(Integer rowsPerBatch) {
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


    @Override
    public List<Block> toBlockList() {
        ListBlockBuilder b = new ListBlockBuilder()
                .append(Keywords.WITH)
                .append(Other.GROUP_START);

        if(batchSize > 0){
            b.append(WithEnum.BATCHSIZE)
                    .append(Operators.EQUAL)
                    .append(batchSize);
        }

        if(checkConstraints){
            b.append(WithEnum.CHECK_CONSTRAINTS);
        }

        if(codePage != null){
            b.append(WithEnum.CODEPAGE)
                    .append(Operators.EQUAL)
                    .append(codePage);
        }

        if(dataFileType != null){
            b.append(WithEnum.DATAFILETYPE)
                    .append(Operators.EQUAL)
                    .append(dataFileType);
        }

        if(dataSource != null){
            b.append(WithEnum.DATASOURCE)
                    .append(Operators.EQUAL)
                    .append(dataSource);
        }

        if(errorFile != null){
            b.append(WithEnum.ERRORFILE)
                    .append(Operators.EQUAL)
                    .append(errorFile);
        }

        if(errorFileDataSource != null){
            b.append(WithEnum.ERRORFILE_DATASOURCE)
                    .append(Operators.EQUAL)
                    .append(errorFileDataSource);
        }

        if(firstRow != null){
            b.append(WithEnum.FIRSTROW)
                    .append(Operators.EQUAL)
                    .append(firstRow);
        }

        if(fireTriggers){
            b.append(WithEnum.FIRE_TRIGGERS);
        }

        if(formatFileDataSource != null){
            b.append(WithEnum.FORMATFILE_DATASOURCE)
                    .append(Operators.EQUAL)
                    .append(formatFileDataSource);
        }

        if(keepIdentity){
            b.append(WithEnum.KEEPIDENTITY);
        }

        if(keepNulls){
            b.append(WithEnum.KEEPNULLS);
        }

        if(kilobytesPerBatch != null){
            b.append(WithEnum.KILOBYTES_PER_BATCH)
                    .append(Operators.EQUAL)
                    .append(kilobytesPerBatch);
        }

        if(lastRow != null){
            b.append(WithEnum.LASTROW)
                    .append(Operators.EQUAL)
                    .append(lastRow);
        }

        if(maxErrors != null){
            b.append(WithEnum.MAXERRORS)
                    .append(Operators.EQUAL)
                    .append(maxErrors);
        }

        if(orderList != null){
            b.append(WithEnum.ORDER)
                    .append(Operators.EQUAL)
                    .append(orderList);
        }

        if(rowsPerBatch != null){
            b.append(WithEnum.ROWS_PER_BATCH)
                    .append(Operators.EQUAL)
                    .append(rowsPerBatch);
        }

        if(rowTerminator != null){
            b.append(WithEnum.ROWTERMINATOR)
                    .append(Operators.EQUAL)
                    .append(rowTerminator);
        }

        if(tabLock){
            b.append(WithEnum.TABLOCK);
        }

        if(format != null){
            b.append(WithEnum.FORMAT)
                    .append(Operators.EQUAL)
                    .append(format);
        }

        if(fieldQuote != null){
            b.append(WithEnum.FIELDQUOTE)
                    .append(Operators.EQUAL)
                    .append(fieldQuote);
        }

        if(formatFile != null){
            b.append(WithEnum.FORMATFILE)
                    .append(Operators.EQUAL)
                    .append(formatFile);
        }

        if(fieldTerminator != null){
            b.append(WithEnum.FIELDTERMINATOR)
                    .append(Operators.EQUAL)
                    .append(fieldTerminator);
        }

        b.append(Other.GROUP_END);


        return new ListBlockBuilder()
                .append(Keywords.BULK)
                .append(Keywords.INSERT)
                .append(tableOrView)
                .append(Keywords.FROM)
                .append(formDataFile)
                .append(b)
                .build();
    }

    /**
     * CODEPAGE enumerate string expression
     */
    public enum CodePage {
        ACP,
        OEM,
        RAW;

        private StringConstant expression;

        CodePage(){
            this.expression = new StringConstant(this.name());
        }

        public StringConstant toExpression() {
            return this.expression;
        }

        @Override
        public String toString() {
            return this.name();
        }
    }

    /**
     * DATAFILETYPE enumerate string expression
     */
    public enum DataFileType {
        Char,
        Native,
        WideChar,
        WideNative;

        private StringConstant expression;

        DataFileType(){
            this.expression = new StringConstant(this.name());
        }

        public StringConstant toExpression() {
            return this.expression;
        }

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }

    /**
     * WITH keywords
     */
    public enum WithEnum implements Block {
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
     */
    private class OrderColumn implements Block {

        private ColumnName column;
        private boolean useAsc;

        public OrderColumn(){

        }

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

        @Override
        public List<Block> toBlockList() {
            return new ListBlockBuilder()
                    .append(column)
                    .append(useAsc ? Keywords.ASC : Keywords.DESC)
                    .build();
        }
    }
}
