package com.xy.xsql.tsql.core.statement.dml;

import com.xy.xsql.core.builder.CodeBuilder;
import com.xy.xsql.core.configurator.BaseConfigurator;
import com.xy.xsql.core.configurator.BooleanValueConfigurator;
import com.xy.xsql.core.configurator.ValueConfigurator;
import com.xy.xsql.core.lambda.Setter;
import com.xy.xsql.tsql.model.datatype.NumberConstant;
import com.xy.xsql.tsql.model.datatype.StringConstant;
import com.xy.xsql.tsql.model.element.ColumnName;
import com.xy.xsql.tsql.model.element.TableName;
import com.xy.xsql.tsql.model.statement.dml.BulkInsert;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.xy.xsql.core.ListBuilder.initAdd;
import static com.xy.xsql.tsql.core.expression.Expressions.e_number;
import static com.xy.xsql.tsql.core.expression.Expressions.e_string;

/**
 * Created by xiaoyao9184 on 2017/1/9.
 */
public class BulkInsertBuilder extends CodeBuilder<BulkInsert> {

    public BulkInsertBuilder(BulkInsert tar) {
        super(tar);
    }

    public BulkInsertBuilder(){
        super(new BulkInsert());
    }


    /**
     * [ database_name . [ schema_name ] . | schema_name . ] [ table_name | view_name ]
     * @return This
     */
    public BulkInsertBuilder withTableViewName(TableName tableViewName){
        target.setTableOrView(tableViewName);
        return this;
    }

    /**
     * FROM 'data_file'
     * @param dataFile
     * @return This
     */
    public BulkInsertBuilder withFrom(String dataFile){
        target.setFormDataFile(e_string(dataFile));
        return this;
    }

    /**
     * [ [ , ] BATCHSIZE = batch_size ]
     * @param batchSize
     * @return This
     */
    public BulkInsertBuilder withBatchSize(Integer batchSize){
        target.setBatchSize(e_number(batchSize));
        return this;
    }

    /**
     * [ [ , ] CHECK_CONSTRAINTS ]
     * @return This
     */
    public BulkInsertBuilder withCheckConstraints(){
        target.setCheckConstraints(true);
        return this;
    }

    /**
     * [ [ , ] CODEPAGE = { 'ACP' | 'OEM' | 'RAW' | 'code_page' } ]
     * @param codePage
     * @return This
     */
    public BulkInsertBuilder withCodePage(String codePage){
        target.setCodePage(e_string(codePage));
        return this;
    }

    /**
     * [ [ , ] CODEPAGE = { 'ACP' | 'OEM' | 'RAW' | 'code_page' } ]
     * @param codePage
     * @return This
     */
    public BulkInsertBuilder withCodePage(QuickConfigurator.CodePage codePage){
        target.setCodePage(codePage.getValue());
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
        target.setDataFileType(dataFileType);
        return this;
    }

    /**
     * [ [ , ] DATAFILETYPE =
     * { 'char' | 'native'| 'widechar' | 'widenative' } ]
     * @param dataFileType
     * @return
     */
    public BulkInsertBuilder withDataFileType(QuickConfigurator.DataFileType dataFileType){
        target.setDataFileType(dataFileType.getValue());
        return this;
    }

    /**
     * [ [ , ] DATASOURCE = 'data_source_name' ]
     * @param dataSourceName
     * @return This
     */
    public BulkInsertBuilder withDataSource(String dataSourceName){
        target.setDataSource(e_string(dataSourceName));
        return this;
    }

    /**
     * [ [ , ] ERRORFILE = 'file_name' ]
     * @param fileName
     * @return This
     */
    public BulkInsertBuilder withErrorFile(String fileName){
        target.setErrorFile(e_string(fileName));
        return this;
    }

    /**
     * [ [ , ] ERRORFILE_DATASOURCE = 'data_source_name' ]
     * @param dataSourceName
     * @return This
     */
    public BulkInsertBuilder withErrorFileDataSource(String dataSourceName){
        target.setErrorFileDataSource(e_string(dataSourceName));
        return this;
    }

    /**
     * [ [ , ] FIRSTROW = first_row ]
     * @param firstRow
     * @return This
     */
    public BulkInsertBuilder withFirstRow(Integer firstRow){
        target.setFirstRow(e_number(firstRow));
        return this;
    }

    /**
     * [ [ , ] FIRE_TRIGGERS ]
     * @return This
     */
    public BulkInsertBuilder withFireTriggers(){
        target.setFireTriggers(true);
        return this;
    }

    /**
     * [ [ , ] FORMATFILE_DATASOURCE = 'data_source_name' ]
     * @param dataSourceName
     * @return This
     */
    public BulkInsertBuilder withFireTriggers(String dataSourceName){
        target.setFormatFileDataSource(e_string(dataSourceName));
        return this;
    }

    /**
     * [ [ , ] KEEPIDENTITY ]
     * @return This
     */
    public BulkInsertBuilder withKeepIdentity(){
        target.setKeepIdentity(true);
        return this;
    }

    /**
     * [ [ , ] KEEPNULLS ]
     * @return This
     */
    public BulkInsertBuilder withKeepNulls(){
        target.setKeepNulls(true);
        return this;
    }

    /**
     * [ [ , ] KILOBYTES_PER_BATCH = kilobytes_per_batch ]
     * @param kilobytesPerBatch
     * @return This
     */
    public BulkInsertBuilder withKilobytesPerBatch(Integer kilobytesPerBatch){
        target.setKilobytesPerBatch(e_number(kilobytesPerBatch));
        return this;
    }

    /**
     * [ [ , ] LASTROW = last_row ]
     * @param lastRow
     * @return This
     */
    public BulkInsertBuilder withLastRow(Integer lastRow){
        target.setLastRow(e_number(lastRow));
        return this;
    }

    /**
     * [ [ , ] MAXERRORS = max_errors ]
     * @param maxErrors
     * @return This
     */
    public BulkInsertBuilder withMaxErrors(Integer maxErrors){
        target.setMaxErrors(e_number(maxErrors));
        return this;
    }

    /**
     * [ [ , ] ORDER ( { column [ ASC | DESC ] } [ ,...n ] ) ]
     * @param orderList
     * @return This
     */
    public BulkInsertBuilder withOrderList(List<BulkInsert.OrderColumn> orderList){
        target.setOrderList(orderList);
        return this;
    }

    /**
     * [ [ , ] ROWS_PER_BATCH = rows_per_batch ]
     * @param rowsPerBatch
     * @return This
     */
    public BulkInsertBuilder withRowsPerBatch(Integer rowsPerBatch){
        target.setRowsPerBatch(e_number(rowsPerBatch));
        return this;
    }

    /**
     * [ [ , ] ROWTERMINATOR = 'row_terminator' ]
     * @param rowTerminator
     * @return This
     */
    public BulkInsertBuilder withRowTerminator(String rowTerminator){
        target.setRowTerminator(e_string(rowTerminator));
        return this;
    }
    /**
     * [ [ , ] TABLOCK ]
     * @return This
     */
    public BulkInsertBuilder withTabLock(){
        target.setTabLock(true);
        return this;
    }

    //-- input file format options
    /**
     * [ [ , ] FORMAT = 'CSV' ]
     * @param format
     * @return This
     */
    public BulkInsertBuilder withFormat(String format){
        target.setFormat(e_string(format));
        return this;
    }

    /**
     * [ [ , ] FIELDQUOTE = 'quote_characters']
     * @param quoteCharacters
     * @return This
     */
    public BulkInsertBuilder withFieldQuote(String quoteCharacters){
        target.setFieldQuote(e_string(quoteCharacters));
        return this;
    }

    /**
     * [ [ , ] FORMATFILE = 'format_file_path' ]
     * @param formatFilePath
     * @return This
     */
    public BulkInsertBuilder withFormatFile(String formatFilePath){
        target.setFormatFile(e_string(formatFilePath));
        return this;
    }

    /**
     * [ [ , ] FIELDTERMINATOR = 'field_terminator' ]
     * @param fieldTerminator
     * @return This
     */
    public BulkInsertBuilder withFieldTerminator(String fieldTerminator){
        target.setFieldTerminator(e_string(fieldTerminator));
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
    public BulkInsertBuilder $With(QuickConfigurator.WithSetter... items){
        Arrays.stream(items)
                .forEach(item -> item.config(target));
        return this;
    }

    //CODEPAGE
    public static QuickConfigurator.CodePage _code_page(String codePage){
        return new QuickConfigurator.CodePage(codePage);
    }
    public static QuickConfigurator.CodePage _ACP(){
        return QuickConfigurator.ACP;
    }
    public static QuickConfigurator.CodePage _OEM(){
        return QuickConfigurator.OEM;
    }
    public static QuickConfigurator.CodePage _RAW(){
        return QuickConfigurator.RAW;
    }

    //DATAFILETYPE
    public static QuickConfigurator.DataFileType _char(){
        return QuickConfigurator.Char;
    }
    public static QuickConfigurator.DataFileType _native(){
        return QuickConfigurator.Native;
    }
    public static QuickConfigurator.DataFileType _wideChar(){
        return QuickConfigurator.WideChar;
    }
    public static QuickConfigurator.DataFileType _wideNative(){
        return QuickConfigurator.WideNative;
    }






    /*
    For quick build

    BULK INSERT statement's WITH part has too many attributes, and quite regular,
    most of which is a flag field, or a simple value.
    Create a set of lambda collections from the outside as parameters for the Builder,
    And then call in the Builder to achieve the purpose of setting attributes

     */
    public static class QuickConfigurator {
        
        /**
         * Just create to block Generic parameters
         * for 'unchecked generic array creation for varargs parameter'
         */
        public interface WithSetter extends BaseConfigurator<BulkInsert> {

        }

        /**
         * Just create to block Generic parameters
         * for 'unchecked generic array creation for varargs parameter'
         */
        public static abstract class KeyWithValueConfigurator
                extends BooleanValueConfigurator<BulkInsert>
                implements WithSetter {

            //TODO not use
            private BulkInsert.WithEnum withKey;

            KeyWithValueConfigurator(BulkInsert.WithEnum withKey){
                this.withKey = withKey;
            }

        }

        /**
         * Just create to block Generic parameters
         * for 'unchecked generic array creation for varargs parameter'
         */
        public static abstract class StringWithConfigurator
                extends ValueConfigurator<BulkInsert,StringConstant>
                implements WithSetter {

            //TODO not use
            private BulkInsert.WithEnum withKey;

            public StringWithConfigurator(BulkInsert.WithEnum withKey, StringConstant value) {
                super(value);
                this.withKey = withKey;
            }

        }

        /**
         * Just create to block Generic parameters
         * for 'unchecked generic array creation for varargs parameter'
         */
        public static abstract class NumberWithConfigurator
                extends ValueConfigurator<BulkInsert,NumberConstant>
                implements WithSetter {

            //TODO not use
            private BulkInsert.WithEnum withKey;

            public NumberWithConfigurator(BulkInsert.WithEnum withKey, NumberConstant value) {
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


        public static class BatchSize extends NumberWithConfigurator {

            public BatchSize(Integer batchSize) {
                super(BulkInsert.WithEnum.BATCHSIZE,
                        new NumberConstant(batchSize));
            }

            @Override
            public Setter<NumberConstant> getSetter(BulkInsert bulkInsert) {
                return bulkInsert::setBatchSize;
            }
        }
        public static class CheckConstraints extends KeyWithValueConfigurator {

            public CheckConstraints() {
                super(BulkInsert.WithEnum.CHECK_CONSTRAINTS);
            }

            @Override
            public Setter<Boolean> getSetter(BulkInsert bulkInsert) {
                return bulkInsert::setCheckConstraints;
            }
        }
        public static class CodePage extends StringWithConfigurator {

            public CodePage(String codePage) {
                super(BulkInsert.WithEnum.CODEPAGE,
                        new StringConstant(codePage).withQuote());
            }

            public CodePage(StringConstant codePage) {
                super(BulkInsert.WithEnum.CODEPAGE,
                        codePage);
            }

            @Override
            public Setter<StringConstant> getSetter(BulkInsert bulkInsert) {
                return bulkInsert::setCodePage;
            }
        }
        public static class DataFileType extends StringWithConfigurator {

            public DataFileType(String dataType) {
                super(BulkInsert.WithEnum.DATAFILETYPE,
                        new StringConstant(dataType).withQuote());
            }

            public DataFileType(StringConstant dataFileType) {
                super(BulkInsert.WithEnum.DATAFILETYPE,
                        dataFileType);
            }

            @Override
            public Setter<StringConstant> getSetter(BulkInsert bulkInsert) {
                return bulkInsert::setDataFileType;
            }
        }
        public static class DataSource extends StringWithConfigurator {

            public DataSource(String dataSource) {
                super(BulkInsert.WithEnum.DATASOURCE,
                        new StringConstant(dataSource).withQuote());
            }

            @Override
            public Setter<StringConstant> getSetter(BulkInsert bulkInsert) {
                return bulkInsert::setDataSource;
            }
        }
        public static class ErrorFile extends StringWithConfigurator {

            public ErrorFile(String errorFile) {
                super(BulkInsert.WithEnum.ERRORFILE,
                        new StringConstant(errorFile).withQuote());
            }

            @Override
            public Setter<StringConstant> getSetter(BulkInsert bulkInsert) {
                return bulkInsert::setErrorFile;
            }
        }
        public static class ErrorFileDataSource extends StringWithConfigurator {

            public ErrorFileDataSource(String errorFileDataSource) {
                super(BulkInsert.WithEnum.ERRORFILE_DATASOURCE,
                        new StringConstant(errorFileDataSource).withQuote());
            }

            @Override
            public Setter<StringConstant> getSetter(BulkInsert bulkInsert) {
                return bulkInsert::setErrorFileDataSource;
            }
        }
        public static class FirstRow extends NumberWithConfigurator {

            public FirstRow(Number firstRow) {
                super(BulkInsert.WithEnum.FIRSTROW,
                        new NumberConstant(firstRow));
            }

            @Override
            public Setter<NumberConstant> getSetter(BulkInsert bulkInsert) {
                return bulkInsert::setFirstRow;
            }
        }
        public static class FireTriggers extends KeyWithValueConfigurator {

            public FireTriggers() {
                super(BulkInsert.WithEnum.FIRE_TRIGGERS);
            }

            @Override
            public Setter<Boolean> getSetter(BulkInsert bulkInsert) {
                return bulkInsert::setFireTriggers;
            }
        }
        public static class FormatFileDataSource extends StringWithConfigurator {

            public FormatFileDataSource(String formatFileDataSource) {
                super(BulkInsert.WithEnum.FORMATFILE_DATASOURCE,
                        new StringConstant(formatFileDataSource).withQuote());
            }

            @Override
            public Setter<StringConstant> getSetter(BulkInsert bulkInsert) {
                return bulkInsert::setFormatFileDataSource;
            }
        }
        public static class KeepIdentity extends KeyWithValueConfigurator {

            public KeepIdentity() {
                super(BulkInsert.WithEnum.KEEPIDENTITY);
            }

            @Override
            public Setter<Boolean> getSetter(BulkInsert bulkInsert) {
                return bulkInsert::setKeepIdentity;
            }
        }
        public static class KeepNulls extends KeyWithValueConfigurator {

            public KeepNulls() {
                super(BulkInsert.WithEnum.KEEPNULLS);
            }

            @Override
            public Setter<Boolean> getSetter(BulkInsert bulkInsert) {
                return bulkInsert::setKeepNulls;
            }
        }
        public static class KiloBytesPerBatch extends NumberWithConfigurator {

            public KiloBytesPerBatch(Number kiloBytesPerBatch) {
                super(BulkInsert.WithEnum.KILOBYTES_PER_BATCH,
                        new NumberConstant(kiloBytesPerBatch));
            }

            @Override
            public Setter<NumberConstant> getSetter(BulkInsert bulkInsert) {
                return bulkInsert::setKilobytesPerBatch;
            }
        }
        public static class LastRow extends NumberWithConfigurator {

            public LastRow(Number lastRow) {
                super(BulkInsert.WithEnum.LASTROW,
                        new NumberConstant(lastRow));
            }

            @Override
            public Setter<NumberConstant> getSetter(BulkInsert bulkInsert) {
                return bulkInsert::setLastRow;
            }
        }
        public static class MaxErrors extends NumberWithConfigurator {

            public MaxErrors(Number maxErrors) {
                super(BulkInsert.WithEnum.MAXERRORS,
                        new NumberConstant(maxErrors));
            }

            @Override
            public Setter<NumberConstant> getSetter(BulkInsert bulkInsert) {
                return bulkInsert::setMaxErrors;
            }
        }
        public static class Order implements WithSetter {

            private List<BulkInsert.OrderColumn> columnList;

            public Order(BulkInsert.OrderColumn... columns){
                this.columnList = Arrays.asList(columns);
            }

            public Order(ColumnName... columns){
                this.columnList = Arrays.stream(columns)
                        .map(column -> {
                            BulkInsert.OrderColumn orderColumn = new BulkInsert.OrderColumn();
                            orderColumn.setColumn(column);
                            return orderColumn;
                        })
                        .collect(Collectors.toList());
            }

            public Order(boolean aes, boolean desc, ColumnName... columns){
                this.columnList = Arrays.stream(columns)
                        .map(column -> {
                            BulkInsert.OrderColumn orderColumn = new BulkInsert.OrderColumn();
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
            public void config(BulkInsert bulkInsert) {
                initAdd(columnList,
                        bulkInsert::getOrderList,
                        bulkInsert::setOrderList);
            }
        }
        public static class RowsPerBatch extends NumberWithConfigurator {

            public RowsPerBatch(Number rowsPerBatch) {
                super(BulkInsert.WithEnum.ROWS_PER_BATCH,
                        new NumberConstant(rowsPerBatch));
            }

            @Override
            public Setter<NumberConstant> getSetter(BulkInsert bulkInsert) {
                return bulkInsert::setRowsPerBatch;
            }
        }
        public static class RowTerminator extends StringWithConfigurator {

            public RowTerminator(String rowTerminator) {
                super(BulkInsert.WithEnum.ROWTERMINATOR,
                        new StringConstant(rowTerminator).withQuote());
            }

            @Override
            public Setter<StringConstant> getSetter(BulkInsert bulkInsert) {
                return bulkInsert::setRowTerminator;
            }
        }
        public static class TabLock extends KeyWithValueConfigurator {

            public TabLock() {
                super(BulkInsert.WithEnum.TABLOCK);
            }

            @Override
            public Setter<Boolean> getSetter(BulkInsert bulkInsert) {
                return bulkInsert::setTabLock;
            }
        }
        public static class Format extends StringWithConfigurator {

            public Format(String format) {
                super(BulkInsert.WithEnum.FORMAT,
                        new StringConstant(format).withQuote());
            }

            @Override
            public Setter<StringConstant> getSetter(BulkInsert bulkInsert) {
                return bulkInsert::setFormat;
            }
        }
        public static class FieldQuote extends StringWithConfigurator {

            public FieldQuote(String fieldQuote) {
                super(BulkInsert.WithEnum.FIELDQUOTE,
                        new StringConstant(fieldQuote).withQuote());
            }

            @Override
            public Setter<StringConstant> getSetter(BulkInsert bulkInsert) {
                return null;
            }
        }
        public static class FormatFile extends StringWithConfigurator {

            public FormatFile(String formatFile) {
                super(BulkInsert.WithEnum.FORMATFILE,
                        new StringConstant(formatFile).withQuote());
            }

            @Override
            public Setter<StringConstant> getSetter(BulkInsert bulkInsert) {
                return bulkInsert::setFormatFile;
            }
        }
        public static class FieldTerminator extends StringWithConfigurator {

            public FieldTerminator(String fieldTerminator) {
                super(BulkInsert.WithEnum.FIELDTERMINATOR,
                        new StringConstant(fieldTerminator).withQuote());
            }

            @Override
            public Setter<StringConstant> getSetter(BulkInsert bulkInsert) {
                return bulkInsert::setFieldTerminator;
            }

        }
    }



    public static QuickConfigurator.WithSetter BATCHSIZE(Integer batchSize){
        return new QuickConfigurator.BatchSize(batchSize);
    }

    public static QuickConfigurator.WithSetter CHECK_CONSTRAINTS(){
        return new QuickConfigurator.CheckConstraints();
    }

    public static QuickConfigurator.WithSetter CODEPAGE(String codePage){
        return new QuickConfigurator.CodePage(codePage);
    }

    @Deprecated
    public static QuickConfigurator.WithSetter CODEPAGE(StringConstant codePage){
        return new QuickConfigurator.CodePage(codePage);
    }

    public static QuickConfigurator.WithSetter CODEPAGE(QuickConfigurator.CodePage codePage){
        return codePage;
    }

    @Deprecated
    public static QuickConfigurator.WithSetter DATAFILETYPE(String dataFileType){
        return new QuickConfigurator.DataFileType(dataFileType);
    }

    @Deprecated
    public static QuickConfigurator.WithSetter DATAFILETYPE(StringConstant dataFileType){
        return new QuickConfigurator.DataFileType(dataFileType);
    }

    public static QuickConfigurator.WithSetter DATAFILETYPE(QuickConfigurator.DataFileType dataFileType){
        return dataFileType;
    }

    public static QuickConfigurator.WithSetter DATASOURCE(String dataSource){
        return new QuickConfigurator.DataSource(dataSource);
    }

    public static QuickConfigurator.WithSetter ERRORFILE(String errorFile){
        return new QuickConfigurator.ErrorFile(errorFile);
    }

    public static QuickConfigurator.WithSetter ERRORFILE_DATASOURCE(String errorFileDataSource){
        return new QuickConfigurator.ErrorFileDataSource(errorFileDataSource);
    }

    public static QuickConfigurator.WithSetter FIRSTROW(Integer firstRow){
        return new QuickConfigurator.FirstRow(firstRow);
    }

    public static QuickConfigurator.WithSetter FIRE_TRIGGERS(){
        return new QuickConfigurator.FireTriggers();
    }

    public static QuickConfigurator.WithSetter FORMATFILE_DATASOURCE(String dataSourceName){
        return new QuickConfigurator.FormatFileDataSource(dataSourceName);
    }

    public static QuickConfigurator.WithSetter KEEPIDENTITY(){
        return new QuickConfigurator.KeepIdentity();
    }

    public static QuickConfigurator.WithSetter KEEPNULLS(){
        return new QuickConfigurator.KeepNulls();
    }

    public static QuickConfigurator.WithSetter KILOBYTES_PER_BATCH(Integer kilobytesPerBatch){
        return new QuickConfigurator.KiloBytesPerBatch(kilobytesPerBatch);
    }

    public static QuickConfigurator.WithSetter LASTROW(Integer lastRow){
        return new QuickConfigurator.LastRow(lastRow);
    }

    public static QuickConfigurator.WithSetter MAXERRORS(Integer maxErrors){
        return new QuickConfigurator.MaxErrors(maxErrors);
    }

    public static QuickConfigurator.WithSetter ORDER(ColumnName... columns){
        return new QuickConfigurator.Order(columns);
    }

    public static QuickConfigurator.WithSetter ORDER_ASC(ColumnName... columns){
        return new QuickConfigurator.Order(true,false,columns);
    }

    public static QuickConfigurator.WithSetter ORDER_DESC(ColumnName... columns){
        return new QuickConfigurator.Order(false,true,columns);
    }

    public static QuickConfigurator.WithSetter ROWS_PER_BATCH(Integer rowsPerBatch){
        return new QuickConfigurator.RowsPerBatch(rowsPerBatch);
    }

    public static QuickConfigurator.WithSetter ROWTERMINATOR(String rowTerminator){
        return new QuickConfigurator.RowTerminator(rowTerminator);
    }

    public static QuickConfigurator.WithSetter TABLOCK (){
        return new QuickConfigurator.TabLock();
    }

    public static QuickConfigurator.WithSetter FORMAT(String format){
        return new QuickConfigurator.Format(format);
    }

    public static QuickConfigurator.WithSetter FIELDQUOTE(String fieldQuote){
        return new QuickConfigurator.FieldQuote(fieldQuote);
    }

    public static QuickConfigurator.WithSetter FORMATFILE(String formatFile){
        return new QuickConfigurator.FormatFile(formatFile);
    }

    public static QuickConfigurator.WithSetter FIELDTERMINATOR(String fieldTerminator){
        return new QuickConfigurator.FieldTerminator(fieldTerminator);
    }

}
