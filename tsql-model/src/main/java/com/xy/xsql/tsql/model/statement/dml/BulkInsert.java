package com.xy.xsql.tsql.model.statement.dml;

import com.xy.xsql.core.lambda.Setter;
import com.xy.xsql.core.target.TargetSetter;
import com.xy.xsql.core.target.TargetBooleanSetter;
import com.xy.xsql.core.target.TargetValueSetter;
import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.datatype.NumberConstant;
import com.xy.xsql.tsql.model.datatype.StringConstant;
import com.xy.xsql.tsql.model.element.ColumnName;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.element.TableName;
import com.xy.xsql.tsql.model.operator.Comparison;
import com.xy.xsql.tsql.model.statement.Statement;
import com.xy.xsql.tsql.util.ListBlockBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.xy.xsql.core.ListBuilder.initAdd;

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


    @Override
    public List<Block> toBlockList() {
        ListBlockBuilder b = new ListBlockBuilder()
                .append(Keywords.WITH)
                .append(Other.GROUP_START);

        if(batchSize != null){
            b.append(WithEnum.BATCHSIZE)
                    .append(Comparison.EQUAL)
                    .append(batchSize);
        }

        if(checkConstraints){
            b.append(WithEnum.CHECK_CONSTRAINTS);
        }

        if(codePage != null){
            b.append(WithEnum.CODEPAGE)
                    .append(Comparison.EQUAL)
                    .append(codePage);
        }

        if(dataFileType != null){
            b.append(WithEnum.DATAFILETYPE)
                    .append(Comparison.EQUAL)
                    .append(dataFileType);
        }

        if(dataSource != null){
            b.append(WithEnum.DATASOURCE)
                    .append(Comparison.EQUAL)
                    .append(dataSource);
        }

        if(errorFile != null){
            b.append(WithEnum.ERRORFILE)
                    .append(Comparison.EQUAL)
                    .append(errorFile);
        }

        if(errorFileDataSource != null){
            b.append(WithEnum.ERRORFILE_DATASOURCE)
                    .append(Comparison.EQUAL)
                    .append(errorFileDataSource);
        }

        if(firstRow != null){
            b.append(WithEnum.FIRSTROW)
                    .append(Comparison.EQUAL)
                    .append(firstRow);
        }

        if(fireTriggers){
            b.append(WithEnum.FIRE_TRIGGERS);
        }

        if(formatFileDataSource != null){
            b.append(WithEnum.FORMATFILE_DATASOURCE)
                    .append(Comparison.EQUAL)
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
                    .append(Comparison.EQUAL)
                    .append(kilobytesPerBatch);
        }

        if(lastRow != null){
            b.append(WithEnum.LASTROW)
                    .append(Comparison.EQUAL)
                    .append(lastRow);
        }

        if(maxErrors != null){
            b.append(WithEnum.MAXERRORS)
                    .append(Comparison.EQUAL)
                    .append(maxErrors);
        }

        if(orderList != null){
            b.append(WithEnum.ORDER)
                    .append(Comparison.EQUAL)
                    .append(orderList);
        }

        if(rowsPerBatch != null){
            b.append(WithEnum.ROWS_PER_BATCH)
                    .append(Comparison.EQUAL)
                    .append(rowsPerBatch);
        }

        if(rowTerminator != null){
            b.append(WithEnum.ROWTERMINATOR)
                    .append(Comparison.EQUAL)
                    .append(rowTerminator);
        }

        if(tabLock){
            b.append(WithEnum.TABLOCK);
        }

        if(format != null){
            b.append(WithEnum.FORMAT)
                    .append(Comparison.EQUAL)
                    .append(format);
        }

        if(fieldQuote != null){
            b.append(WithEnum.FIELDQUOTE)
                    .append(Comparison.EQUAL)
                    .append(fieldQuote);
        }

        if(formatFile != null){
            b.append(WithEnum.FORMATFILE)
                    .append(Comparison.EQUAL)
                    .append(formatFile);
        }

        if(fieldTerminator != null){
            b.append(WithEnum.FIELDTERMINATOR)
                    .append(Comparison.EQUAL)
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
     * TODO maybe same as OrderBy.Item
     */
    public static class OrderColumn implements Block {

        private ColumnName column;
        private boolean useAsc;
        private boolean useDesc;

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

        public boolean isUseDesc() {
            return useDesc;
        }

        public void setUseDesc(boolean useDesc) {
            this.useDesc = useDesc;
        }

        @Override
        public List<Block> toBlockList() {
            ListBlockBuilder b = new ListBlockBuilder()
                    .append(column);
            if(useAsc){
                b.append(Keywords.ASC);
            } else if(useDesc){
                b.append(Keywords.DESC);
            }
            return b.build();
        }
    }



    /*
    For quick build

    BULK INSERT statement's WITH part has too many attributes, and quite regular,
    most of which is a flag field, or a simple value.
    Create a set of lambda collections from the outside as parameters for the Builder,
    And then call in the Builder to achieve the purpose of setting attributes

     */


    /**
     * Just create to block Generic parameters
     * for 'unchecked generic array creation for varargs parameter'
     */
    public interface WithSetter extends TargetSetter<BulkInsert> {

    }

    /**
     * Just create to block Generic parameters
     * for 'unchecked generic array creation for varargs parameter'
     */
    public static abstract class KeyWithSetter
            extends TargetBooleanSetter<BulkInsert>
            implements WithSetter {

        //TODO not use
        private WithEnum withKey;

        KeyWithSetter(WithEnum withKey){
            this.withKey = withKey;
        }

    }

    /**
     * Just create to block Generic parameters
     * for 'unchecked generic array creation for varargs parameter'
     */
    public static abstract class StringWithSetter
            extends TargetValueSetter<BulkInsert,StringConstant>
            implements WithSetter {

        //TODO not use
        private WithEnum withKey;

        public StringWithSetter(WithEnum withKey, StringConstant value) {
            super(value);
            this.withKey = withKey;
        }

    }

    /**
     * Just create to block Generic parameters
     * for 'unchecked generic array creation for varargs parameter'
     */
    public static abstract class NumberWithSetter
            extends TargetValueSetter<BulkInsert,NumberConstant>
            implements WithSetter {

        //TODO not use
        private WithEnum withKey;

        public NumberWithSetter(WithEnum withKey, NumberConstant value) {
            super(value);
            this.withKey = withKey;
        }

    }


    public static CodePage ACP = new CodePage("ACP");
    public static CodePage OEM = new CodePage("OEM");
    public static CodePage RAW = new CodePage("RAW");

    public static DataFileType Char = new DataFileType("char");
    public static DataFileType Native = new DataFileType("native");
    public static DataFileType WideChar = new DataFileType("widechar");
    public static DataFileType WideNative = new DataFileType("widenative");


    public static class BatchSize extends NumberWithSetter {

        public BatchSize(Integer batchSize) {
            super(WithEnum.BATCHSIZE,
                    new NumberConstant(batchSize));
        }

        @Override
        public Setter<NumberConstant> getSetter(BulkInsert bulkInsert) {
            return bulkInsert::setBatchSize;
        }
    }
    public static class CheckConstraints extends KeyWithSetter {

        public CheckConstraints() {
            super(WithEnum.CHECK_CONSTRAINTS);
        }

        @Override
        public Setter<Boolean> getSetter(BulkInsert bulkInsert) {
            return bulkInsert::setCheckConstraints;
        }
    }
    public static class CodePage extends StringWithSetter {

        public CodePage(String codePage) {
            super(WithEnum.CODEPAGE,
                    new StringConstant(codePage).withQuote());
        }

        public CodePage(StringConstant codePage) {
            super(WithEnum.CODEPAGE,
                    codePage);
        }

        @Override
        public Setter<StringConstant> getSetter(BulkInsert bulkInsert) {
            return bulkInsert::setCodePage;
        }
    }
    public static class DataFileType extends StringWithSetter {

        public DataFileType(String dataType) {
            super(WithEnum.DATAFILETYPE,
                    new StringConstant(dataType).withQuote());
        }

        public DataFileType(StringConstant dataFileType) {
            super(WithEnum.DATAFILETYPE,
                    dataFileType);
        }

        @Override
        public Setter<StringConstant> getSetter(BulkInsert bulkInsert) {
            return bulkInsert::setDataFileType;
        }
    }
    public static class DataSource extends StringWithSetter {

        public DataSource(String dataSource) {
            super(WithEnum.DATASOURCE,
                    new StringConstant(dataSource).withQuote());
        }

        @Override
        public Setter<StringConstant> getSetter(BulkInsert bulkInsert) {
            return bulkInsert::setDataSource;
        }
    }
    public static class ErrorFile extends StringWithSetter {

        public ErrorFile(String errorFile) {
            super(WithEnum.ERRORFILE,
                    new StringConstant(errorFile).withQuote());
        }

        @Override
        public Setter<StringConstant> getSetter(BulkInsert bulkInsert) {
            return bulkInsert::setErrorFile;
        }
    }
    public static class ErrorFileDataSource extends StringWithSetter {

        public ErrorFileDataSource(String errorFileDataSource) {
            super(WithEnum.ERRORFILE_DATASOURCE,
                    new StringConstant(errorFileDataSource).withQuote());
        }

        @Override
        public Setter<StringConstant> getSetter(BulkInsert bulkInsert) {
            return bulkInsert::setErrorFileDataSource;
        }
    }
    public static class FirstRow extends NumberWithSetter {

        public FirstRow(Number firstRow) {
            super(WithEnum.FIRSTROW,
                    new NumberConstant(firstRow));
        }

        @Override
        public Setter<NumberConstant> getSetter(BulkInsert bulkInsert) {
            return bulkInsert::setFirstRow;
        }
    }
    public static class FireTriggers extends KeyWithSetter {

        public FireTriggers() {
            super(WithEnum.FIRE_TRIGGERS);
        }

        @Override
        public Setter<Boolean> getSetter(BulkInsert bulkInsert) {
            return bulkInsert::setFireTriggers;
        }
    }
    public static class FormatFileDataSource extends StringWithSetter {

        public FormatFileDataSource(String formatFileDataSource) {
            super(WithEnum.FORMATFILE_DATASOURCE,
                    new StringConstant(formatFileDataSource).withQuote());
        }

        @Override
        public Setter<StringConstant> getSetter(BulkInsert bulkInsert) {
            return bulkInsert::setFormatFileDataSource;
        }
    }
    public static class KeepIdentity extends KeyWithSetter {

        public KeepIdentity() {
            super(WithEnum.KEEPIDENTITY);
        }

        @Override
        public Setter<Boolean> getSetter(BulkInsert bulkInsert) {
            return bulkInsert::setKeepIdentity;
        }
    }
    public static class KeepNulls extends KeyWithSetter {

        public KeepNulls() {
            super(WithEnum.KEEPNULLS);
        }

        @Override
        public Setter<Boolean> getSetter(BulkInsert bulkInsert) {
            return bulkInsert::setKeepNulls;
        }
    }
    public static class KiloBytesPerBatch extends NumberWithSetter {

        public KiloBytesPerBatch(Number kiloBytesPerBatch) {
            super(WithEnum.KILOBYTES_PER_BATCH,
                    new NumberConstant(kiloBytesPerBatch));
        }

        @Override
        public Setter<NumberConstant> getSetter(BulkInsert bulkInsert) {
            return bulkInsert::setKilobytesPerBatch;
        }
    }
    public static class LastRow extends NumberWithSetter {

        public LastRow(Number lastRow) {
            super(WithEnum.LASTROW,
                    new NumberConstant(lastRow));
        }

        @Override
        public Setter<NumberConstant> getSetter(BulkInsert bulkInsert) {
            return bulkInsert::setLastRow;
        }
    }
    public static class MaxErrors extends NumberWithSetter {

        public MaxErrors(Number maxErrors) {
            super(WithEnum.MAXERRORS,
                    new NumberConstant(maxErrors));
        }

        @Override
        public Setter<NumberConstant> getSetter(BulkInsert bulkInsert) {
            return bulkInsert::setMaxErrors;
        }
    }
    public static class Order implements WithSetter {

        private List<OrderColumn> columnList;

        public Order(OrderColumn... columns){
            this.columnList = Arrays.asList(columns);
        }

        public Order(ColumnName... columns){
            this.columnList = Arrays.stream(columns)
                    .map(column -> {
                        OrderColumn orderColumn = new OrderColumn();
                        orderColumn.setColumn(column);
                        return orderColumn;
                    })
                    .collect(Collectors.toList());
        }

        public Order(boolean aes, boolean desc, ColumnName... columns){
            this.columnList = Arrays.stream(columns)
                    .map(column -> {
                        OrderColumn orderColumn = new OrderColumn();
                        orderColumn.setColumn(column);
                        orderColumn.setUseAsc(aes);
                        return orderColumn;
                    })
                    .collect(Collectors.toList());
        }

//        public Order(OrderBy.Item... columns) {
//            this.columnList = Arrays.asList(columns);
//        }

        @Override
        public void set(BulkInsert bulkInsert) {
            initAdd(columnList,
                    bulkInsert::getOrderList,
                    bulkInsert::setOrderList);
        }
    }
    public static class RowsPerBatch extends NumberWithSetter {

        public RowsPerBatch(Number rowsPerBatch) {
            super(WithEnum.ROWS_PER_BATCH,
                    new NumberConstant(rowsPerBatch));
        }

        @Override
        public Setter<NumberConstant> getSetter(BulkInsert bulkInsert) {
            return bulkInsert::setRowsPerBatch;
        }
    }
    public static class RowTerminator extends StringWithSetter {

        public RowTerminator(String rowTerminator) {
            super(WithEnum.ROWTERMINATOR,
                    new StringConstant(rowTerminator).withQuote());
        }

        @Override
        public Setter<StringConstant> getSetter(BulkInsert bulkInsert) {
            return bulkInsert::setRowTerminator;
        }
    }
    public static class TabLock extends KeyWithSetter {

        public TabLock() {
            super(WithEnum.TABLOCK);
        }

        @Override
        public Setter<Boolean> getSetter(BulkInsert bulkInsert) {
            return bulkInsert::setTabLock;
        }
    }
    public static class Format extends StringWithSetter {

        public Format(String format) {
            super(WithEnum.FORMAT,
                    new StringConstant(format).withQuote());
        }

        @Override
        public Setter<StringConstant> getSetter(BulkInsert bulkInsert) {
            return bulkInsert::setFormat;
        }
    }
    public static class FieldQuote extends StringWithSetter {

        public FieldQuote(String fieldQuote) {
            super(WithEnum.FIELDQUOTE,
                    new StringConstant(fieldQuote).withQuote());
        }

        @Override
        public Setter<StringConstant> getSetter(BulkInsert bulkInsert) {
            return null;
        }
    }
    public static class FormatFile extends StringWithSetter {

        public FormatFile(String formatFile) {
            super(WithEnum.FORMATFILE,
                    new StringConstant(formatFile).withQuote());
        }

        @Override
        public Setter<StringConstant> getSetter(BulkInsert bulkInsert) {
            return bulkInsert::setFormatFile;
        }
    }
    public static class FieldTerminator extends StringWithSetter {

        public FieldTerminator(String fieldTerminator) {
            super(WithEnum.FIELDTERMINATOR,
                    new StringConstant(fieldTerminator).withQuote());
        }

        @Override
        public Setter<StringConstant> getSetter(BulkInsert bulkInsert) {
            return bulkInsert::setFieldTerminator;
        }

    }

}
