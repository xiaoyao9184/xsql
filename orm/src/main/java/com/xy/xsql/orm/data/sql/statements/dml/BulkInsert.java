package com.xy.xsql.orm.data.sql.statements.dml;

import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;
import com.xy.xsql.orm.data.sql.element.OperatorEnum;
import com.xy.xsql.orm.data.sql.element.OtherEnum;
import com.xy.xsql.orm.data.sql.element.UnknownString;
import com.xy.xsql.orm.data.sql.element.info.Order;
import com.xy.xsql.orm.data.sql.element.info.TableName;
import com.xy.xsql.orm.data.sql.sentence.BaseElementsSentence;
import com.xy.xsql.orm.data.sql.sentence.CustomizeSentence;

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
public class BulkInsert extends CustomizeSentence {





    //
    private TableName tableOrView;
    //FROM
    private String formDataFile;

    //[ [ , ] BATCHSIZE = batch_size ]
    private Integer batchSize;
    //[ [ , ] CHECK_CONSTRAINTS ]
    private boolean checkConstraints;
    //[ [ , ] CODEPAGE = { 'ACP' | 'OEM' | 'RAW' | 'code_page' } ]
    private UnknownString codePage;

    //[ [ , ] DATAFILETYPE =
    //{ 'char' | 'native'| 'widechar' | 'widenative' } ]
    private UnknownString dataFileType;
    //[ [ , ] DATASOURCE = 'data_source_name' ]
    private UnknownString dataSource;
    //[ [ , ] ERRORFILE = 'file_name' ]
    private UnknownString errorFile;
    //[ [ , ] ERRORFILE_DATASOURCE = 'data_source_name' ]
    private UnknownString errorFileDataSource;
    //[ [ , ] FIRSTROW = first_row ]
    private Integer firstRow;
    //[ [ , ] FIRE_TRIGGERS ]
    private boolean fireTriggers;
    //[ [ , ] FORMATFILE_DATASOURCE = 'data_source_name' ]
    private UnknownString formatFileDataSource;
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
    private List<Order> orderList;
    //[ [ , ] ROWS_PER_BATCH = rows_per_batch ]
    private Integer rowsPerBatch;
    //[ [ , ] ROWTERMINATOR = 'row_terminator' ]
    private UnknownString rowTerminator;
    //[ [ , ] TABLOCK ]
    private boolean tabLock;

    //-- input file format options
    //[ [ , ] FORMAT = 'CSV' ]
    private UnknownString format = new UnknownString().withQuote(true);
    //[ [ , ] FIELDQUOTE = 'quote_characters']
    private UnknownString fieldQuote = new UnknownString().withQuote(true);
    //[ [ , ] FORMATFILE = 'format_file_path' ]
    private UnknownString formatFile = new UnknownString().withQuote(true);
    //[ [ , ] FIELDTERMINATOR = 'field_terminator' ]
    private UnknownString fieldTerminator = new UnknownString().withQuote(true);
    //[ [ , ] ROWTERMINATOR = 'row_terminator' ]
//    private UnknownString rowTerminator = new UnknownString().withQuote(true);


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

    public UnknownString getCodePage() {
        return codePage;
    }

    public void setCodePage(UnknownString codePage) {
        this.codePage = codePage;
    }

    public UnknownString getDataFileType() {
        return dataFileType;
    }

    public void setDataFileType(UnknownString dataFileType) {
        this.dataFileType = dataFileType;
    }

    public UnknownString getDataSource() {
        return dataSource;
    }

    public void setDataSource(UnknownString dataSource) {
        this.dataSource = dataSource;
    }

    public UnknownString getErrorFile() {
        return errorFile;
    }

    public void setErrorFile(UnknownString errorFile) {
        this.errorFile = errorFile;
    }

    public UnknownString getErrorFileDataSource() {
        return errorFileDataSource;
    }

    public void setErrorFileDataSource(UnknownString errorFileDataSource) {
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

    public UnknownString getFormatFileDataSource() {
        return formatFileDataSource;
    }

    public void setFormatFileDataSource(UnknownString formatFileDataSource) {
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

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public Integer getRowsPerBatch() {
        return rowsPerBatch;
    }

    public void setRowsPerBatch(Integer rowsPerBatch) {
        this.rowsPerBatch = rowsPerBatch;
    }

    public UnknownString getRowTerminator() {
        return rowTerminator;
    }

    public void setRowTerminator(UnknownString rowTerminator) {
        this.rowTerminator = rowTerminator;
    }

    public boolean isTabLock() {
        return tabLock;
    }

    public void setTabLock(boolean tabLock) {
        this.tabLock = tabLock;
    }

    public UnknownString getFormat() {
        return format;
    }

    public void setFormat(UnknownString format) {
        this.format = format;
    }

    public UnknownString getFieldQuote() {
        return fieldQuote;
    }

    public void setFieldQuote(UnknownString fieldQuote) {
        this.fieldQuote = fieldQuote;
    }

    public UnknownString getFormatFile() {
        return formatFile;
    }

    public void setFormatFile(UnknownString formatFile) {
        this.formatFile = formatFile;
    }

    public UnknownString getFieldTerminator() {
        return fieldTerminator;
    }

    public void setFieldTerminator(UnknownString fieldTerminator) {
        this.fieldTerminator = fieldTerminator;
    }




    @Override
    public BaseElementsSentence toBaseElementsSentence() {
        ListElementBuilder builder = new ListElementBuilder()
                .withDelimiter(OtherEnum.SPACE)
                .append(GrammarEnum.BULK)
                .append(GrammarEnum.INSERT);


        ListElementBuilder b = new ListElementBuilder()
                .withDelimiter(OtherEnum.SPACE)
                .append(GrammarEnum.WITH);

        if(batchSize > 0){
            b.append(WithEnum.BATCHSIZE)
                    .append(OperatorEnum.EQUAL)
                    .append(batchSize);
        }

        if(checkConstraints){
            b.append(WithEnum.CHECK_CONSTRAINTS);
        }

        if(codePage != null){
            b.append(WithEnum.CODEPAGE)
                    .append(OperatorEnum.EQUAL)
                    .append(codePage);
        }

        if(dataSource != null){
            b.append(WithEnum.DATASOURCE)
                    .append(OperatorEnum.EQUAL)
                    .append(dataSource);
        }

        if(errorFile != null){
            b.append(WithEnum.ERRORFILE)
                    .append(OperatorEnum.EQUAL)
                    .append(errorFile);
        }

        if(errorFileDataSource != null){
            b.append(WithEnum.ERRORFILE_DATASOURCE)
                    .append(OperatorEnum.EQUAL)
                    .append(errorFileDataSource);
        }

        if(firstRow != null){
            b.append(WithEnum.FIRSTROW)
                    .append(OperatorEnum.EQUAL)
                    .append(firstRow);
        }

        if(fireTriggers){
            b.append(WithEnum.FIRE_TRIGGERS);
        }

        if(formatFileDataSource != null){
            b.append(WithEnum.FORMATFILE_DATASOURCE)
                    .append(OperatorEnum.EQUAL)
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
                    .append(OperatorEnum.EQUAL)
                    .append(kilobytesPerBatch);
        }

        if(lastRow != null){
            b.append(WithEnum.LASTROW)
                    .append(OperatorEnum.EQUAL)
                    .append(lastRow);
        }

        if(maxErrors != null){
            b.append(WithEnum.MAXERRORS)
                    .append(OperatorEnum.EQUAL)
                    .append(maxErrors);
        }

        if(orderList != null){
            b.append(WithEnum.ORDER)
                    .append(OperatorEnum.EQUAL)
                    .append(orderList,OtherEnum.DELIMITER);
        }

        if(rowsPerBatch != null){
            b.append(WithEnum.ROWS_PER_BATCH)
                    .append(OperatorEnum.EQUAL)
                    .append(rowsPerBatch);
        }

        if(rowTerminator != null){
            b.append(WithEnum.ROWTERMINATOR)
                    .append(OperatorEnum.EQUAL)
                    .append(rowTerminator);
        }

        if(tabLock){
            b.append(WithEnum.TABLOCK);
        }

        if(format != null){
            b.append(WithEnum.FORMAT)
                    .append(OperatorEnum.EQUAL)
                    .append(format);
        }

        if(fieldQuote != null){
            b.append(WithEnum.FIELDQUOTE)
                    .append(OperatorEnum.EQUAL)
                    .append(fieldQuote);
        }

        if(formatFile != null){
            b.append(WithEnum.FORMATFILE)
                    .append(OperatorEnum.EQUAL)
                    .append(formatFile);
        }

        if(fieldTerminator != null){
            b.append(WithEnum.FIELDTERMINATOR)
                    .append(OperatorEnum.EQUAL)
                    .append(fieldTerminator);
        }

        builder.append(b.build(),OtherEnum.DELIMITER);

        return new BaseElementsSentence(builder.build(null));
    }

    /**
     *
     */
    public enum CodePage {
        ACP,
        OEM,
        RAW;

        @Override
        public String toString() {
            return this.name();
        }
    }

    public enum DataFileType {
        Char,
        Native,
        WideChar,
        WideNative;

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }


    public enum WithEnum implements Element {
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
}
