package com.xy.xsql.tsql.builder.chain.statements;

import com.xy.xsql.core.builder.parent.ParentHoldBuilder;
import com.xy.xsql.core.builder.simple.CodeBuilder;
import com.xy.xsql.core.configurator.BaseConfigurator;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.datatypes.table.TableName;
import com.xy.xsql.tsql.model.statements.BulkInsert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.xy.xsql.core.ListBuilder.getLastItem;
import static com.xy.xsql.core.handler.list.ListHandler.list;
import static com.xy.xsql.core.handler.object.SupplierObjectHandler.object;
import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_number;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_string;

/**
 * BulkInsertBuilder
 * Created by xiaoyao9184 on 2017/1/9.
 */
@SuppressWarnings({"unused","WeakerAccess"})
public class BulkInsertBuilder extends CodeBuilder<BulkInsert> {

    public BulkInsertBuilder(BulkInsert target) {
        super(target);
    }

    public BulkInsertBuilder(){
        super(new BulkInsert());
    }


    /**
     * set
     * [ database_name . [ schema_name ] . | schema_name . ] [ table_name | view_name ]
     * @return THIS
     */
    public BulkInsertBuilder withTableViewName(TableName tableViewName){
        target.setTableOrView(tableViewName);
        return this;
    }

    /**
     * set
     * FROM 'data_file'
     * @param dataFile data file
     * @return THIS
     */
    public BulkInsertBuilder withFrom(String dataFile){
        target.setFormDataFile(e_string(dataFile));
        return this;
    }

    /**
     * set
     * [ [ , ] BATCHSIZE = batch_size ]
     * @param batchSize batch size
     * @return THIS
     */
    public BulkInsertBuilder withBatchSize(Integer batchSize){
        target.setBatchSize(e_number(batchSize));
        return this;
    }

    /**
     * set
     * [ [ , ] CHECK_CONSTRAINTS ]
     * @return THIS
     */
    public BulkInsertBuilder withCheckConstraints(){
        target.setCheckConstraints(true);
        return this;
    }

    /**
     * set
     * [ [ , ] CODEPAGE = { 'ACP' | 'OEM' | 'RAW' | 'code_page' } ]
     * @param codePage code page
     * @return THIS
     */
    public BulkInsertBuilder withCodePage(String codePage){
        target.setCodePage(e_string(codePage));
        return this;
    }

    /**
     * set
     * [ [ , ] CODEPAGE = { 'ACP' | 'OEM' | 'RAW' | 'code_page' } ]
     * @param codePage code page
     * @return THIS
     */
    public BulkInsertBuilder withCodePage(CodePage codePage){
        target.setCodePage(codePage);
        return this;
    }

    /**
     * set
     * [ [ , ] DATAFILETYPE =
     * { 'char' | 'native'| 'widechar' | 'widenative' } ]
     * @deprecated can't set other value
     * @param dataFileType data file type
     * @return THIS
     */
    @Deprecated
    public BulkInsertBuilder withDataFileType(StringConstant dataFileType){
        target.setDataFileType(dataFileType);
        return this;
    }

    /**
     * set
     * [ [ , ] DATAFILETYPE =
     * { 'char' | 'native'| 'widechar' | 'widenative' } ]
     * @param dataFileType data file type
     * @return THIS
     */
    public BulkInsertBuilder withDataFileType(DataFileType dataFileType){
        target.setDataFileType(dataFileType);
        return this;
    }

    /**
     * set
     * [ [ , ] DATASOURCE = 'data_source_name' ]
     * @param dataSourceName data source name
     * @return THIS
     */
    public BulkInsertBuilder withDataSource(String dataSourceName){
        target.setDataSource(e_string(dataSourceName));
        return this;
    }

    /**
     * set
     * [ [ , ] ERRORFILE = 'file_name' ]
     * @param fileName file name
     * @return THIS
     */
    public BulkInsertBuilder withErrorFile(String fileName){
        target.setErrorFile(e_string(fileName));
        return this;
    }

    /**
     * set
     * [ [ , ] ERRORFILE_DATASOURCE = 'data_source_name' ]
     * @param dataSourceName data source name
     * @return THIS
     */
    public BulkInsertBuilder withErrorFileDataSource(String dataSourceName){
        target.setErrorFileDataSource(e_string(dataSourceName));
        return this;
    }

    /**
     * set
     * [ [ , ] FIRSTROW = first_row ]
     * @param firstRow first row
     * @return THIS
     */
    public BulkInsertBuilder withFirstRow(Integer firstRow){
        target.setFirstRow(e_number(firstRow));
        return this;
    }

    /**
     * set
     * [ [ , ] FIRE_TRIGGERS ]
     * @return THIS
     */
    public BulkInsertBuilder withFireTriggers(){
        target.setFireTriggers(true);
        return this;
    }

    /**
     * set
     * [ [ , ] FORMATFILE_DATASOURCE = 'data_source_name' ]
     * @param dataSourceName data source name
     * @return THIS
     */
    public BulkInsertBuilder withFireTriggers(String dataSourceName){
        target.setFormatFileDataSource(e_string(dataSourceName));
        return this;
    }

    /**
     * set
     * [ [ , ] KEEPIDENTITY ]
     * @return THIS
     */
    public BulkInsertBuilder withKeepIdentity(){
        target.setKeepIdentity(true);
        return this;
    }

    /**
     * set
     * [ [ , ] KEEPNULLS ]
     * @return THIS
     */
    public BulkInsertBuilder withKeepNulls(){
        target.setKeepNulls(true);
        return this;
    }

    /**
     * set
     * [ [ , ] KILOBYTES_PER_BATCH = kilobytes_per_batch ]
     * @param kilobytesPerBatch kilobytes per batch
     * @return THIS
     */
    public BulkInsertBuilder withKilobytesPerBatch(Integer kilobytesPerBatch){
        target.setKilobytesPerBatch(e_number(kilobytesPerBatch));
        return this;
    }

    /**
     * set
     * [ [ , ] LASTROW = last_row ]
     * @param lastRow last row
     * @return THIS
     */
    public BulkInsertBuilder withLastRow(Integer lastRow){
        target.setLastRow(e_number(lastRow));
        return this;
    }

    /**
     * set
     * [ [ , ] MAXERRORS = max_errors ]
     * @param maxErrors max errors
     * @return THIS
     */
    public BulkInsertBuilder withMaxErrors(Integer maxErrors){
        target.setMaxErrors(e_number(maxErrors));
        return this;
    }

    /**
     * set
     * [ [ , ] ORDER ( { column [ ASC | DESC ] } [ ,...n ] ) ]
     * @param orderList column
     * @return THIS
     */
    public BulkInsertBuilder withOrderList(List<BulkInsert.OrderColumn> orderList){
        target.setOrderList(orderList);
        return this;
    }

    /**
     * set
     * [ [ , ] ROWS_PER_BATCH = rows_per_batch ]
     * @param rowsPerBatch rows per batch
     * @return THIS
     */
    public BulkInsertBuilder withRowsPerBatch(Integer rowsPerBatch){
        target.setRowsPerBatch(e_number(rowsPerBatch));
        return this;
    }

    /**
     * set
     * [ [ , ] ROWTERMINATOR = 'row_terminator' ]
     * @param rowTerminator row terminator
     * @return THIS
     */
    public BulkInsertBuilder withRowTerminator(String rowTerminator){
        target.setRowTerminator(e_string(rowTerminator));
        return this;
    }

    /**
     * set
     * [ [ , ] TABLOCK ]
     * @return THIS
     */
    public BulkInsertBuilder withTabLock(){
        target.setTabLock(true);
        return this;
    }

    //-- input file format options
    /**
     * set
     * [ [ , ] FORMAT = 'CSV' ]
     * @param format format
     * @return THIS
     */
    public BulkInsertBuilder withFormat(String format){
        target.setFormat(e_string(format));
        return this;
    }

    /**
     * set
     * [ [ , ] FIELDQUOTE = 'quote_characters']
     * @param quoteCharacters quote characters
     * @return THIS
     */
    public BulkInsertBuilder withFieldQuote(String quoteCharacters){
        target.setFieldQuote(e_string(quoteCharacters));
        return this;
    }

    /**
     * set
     * [ [ , ] FORMATFILE = 'format_file_path' ]
     * @param formatFilePath format file path
     * @return THIS
     */
    public BulkInsertBuilder withFormatFile(String formatFilePath){
        target.setFormatFile(e_string(formatFilePath));
        return this;
    }

    /**
     * set
     * [ [ , ] FIELDTERMINATOR = 'field_terminator' ]
     * @param fieldTerminator field terminator
     * @return THIS
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




    /*
    Quick
     */

    /**
     * Quick set
     * @param tableName TableName
     * @return THIS
     */
    public BulkInsertBuilder $(TableName tableName){
        return withTableViewName(tableName);
    }

    /**
     * Quick set
     * @param dataFile data file
     * @return THIS
     */
    public BulkInsertBuilder $From(String dataFile){
        return withFrom(dataFile);
    }

    /**
     * Quick set
     * @param items WithSetter
     * @return THIS
     */
    public BulkInsertBuilder $With(WithSetter... items){
        Arrays.stream(items)
                .forEach(item -> item.config(target));
        return this;
    }


    /**
     * OrderColumnBuilder
     * @param <ParentBuilder>
     */
    public static class OrderColumnBuilder<ParentBuilder>
            extends ParentHoldBuilder<OrderColumnBuilder<ParentBuilder>,ParentBuilder,BulkInsert.OrderColumn> {

        public OrderColumnBuilder() {
            super(new BulkInsert.OrderColumn());
        }

        public OrderColumnBuilder(BulkInsert.OrderColumn target) {
            super(target);
        }

        /**
         * set
         * @return THIS
         */
        public OrderColumnBuilder<ParentBuilder> withAsc(){
            target.setUseAsc(true);
            return this;
        }

        /**
         * set
         * @param useAsc asc
         * @return THIS
         */
        @Deprecated
        public OrderColumnBuilder<ParentBuilder> withAsc(boolean useAsc){
            target.setUseAsc(useAsc);
            return this;
        }

        /**
         * set
         * @return THIS
         */
        public OrderColumnBuilder<ParentBuilder> withDesc(){
            target.setUseDesc(true);
            return this;
        }

        /**
         * set
         * @param useDesc desc
         * @return THIS
         */
        @Deprecated
        public OrderColumnBuilder<ParentBuilder> withDesc(boolean useDesc){
            target.setUseDesc(useDesc);
            return this;
        }

        /**
         * set
         * @param columnName ColumnName
         * @return THIS
         */
        public OrderColumnBuilder<ParentBuilder> withColumnName(ColumnName columnName){
            target.setColumn(columnName);
            return this;
        }

    }

    /**
     * OrderColumnListBuilder
     * @param <ParentBuilder>
     */
    public static class OrderColumnListBuilder<ParentBuilder>
            extends ParentHoldBuilder<OrderColumnListBuilder<ParentBuilder>,ParentBuilder,List<BulkInsert.OrderColumn>>
            implements WithSetter {

        public OrderColumnListBuilder(){
            super(new ArrayList<>());
        }

        public OrderColumnListBuilder(BulkInsert.OrderColumn... columns){
            super(Arrays.asList(columns));
        }

        @Override
        public void config(BulkInsert bulkInsert) {
            list(bulkInsert::getOrderList, bulkInsert::setOrderList)
                    .addAll(target);
        }

        /**
         * in
         * @return OrderColumnBuilder
         */
        public OrderColumnBuilder<OrderColumnListBuilder<ParentBuilder>> withItem(){
            return new OrderColumnBuilder<OrderColumnListBuilder<ParentBuilder>>
                    (object(BulkInsert.OrderColumn::new).set(target::add))
                    .in(this);
        }




        /*
        Quick
         */

        /**
         * Quick set
         * @param columnName ColumnName
         * @return THIS
         */
        public OrderColumnListBuilder<ParentBuilder> $(ColumnName columnName) {
            return withItem()
                    .withColumnName(columnName)
                    .and();
        }

        /**
         * Quick set
         * @return THIS
         */
        public OrderColumnListBuilder<ParentBuilder> $Aes(){
            BulkInsert.OrderColumn last = getLastItem(target);
            return new OrderColumnBuilder<OrderColumnListBuilder<ParentBuilder>>
                    (last)
                    .in(this)
                    .withAsc()
                    .and();
        }

        /**
         * Quick set
         * @return THIS
         */
        public OrderColumnListBuilder<ParentBuilder> $Desc(){
            BulkInsert.OrderColumn last = getLastItem(target);
            return new OrderColumnBuilder<OrderColumnListBuilder<ParentBuilder>>
                    (last)
                    .in(this)
                    .withDesc()
                    .and();
        }
    }

    /*
    For quick build

    BULK INSERT statement's WITH part has too many attributes, and quite regular,
    most of which is a flag field, or a simple value.
    Create a set of lambda collections from the outside as parameters for the Builder,
    And then call in the Builder to achieve the purpose of setting attributes

     */

    public static class CodePage extends StringConstant {
        public CodePage(String string) {
            super(string);
        }
    }
    public static class DataFileType extends StringConstant {
        public DataFileType(String string) {
            super(string);
        }
    }


    /**
     * Just create to block Generic parameters
     * for 'unchecked generic array creation for varargs parameter'
     */
    public interface WithSetter
            extends BaseConfigurator<BulkInsert> {

        /**
         * Quick build
         * @param batchSize batch size
         * @return WithSetter
         */
        static WithSetter $Batchsize(Integer batchSize){
            return bulkInsert -> bulkInsert.setBatchSize(c_number(batchSize));
        }

        /**
         * Quick build
         * @return WithSetter
         */
        static WithSetter $CheckConstraints(){
            return bulkInsert -> bulkInsert.setCheckConstraints(true);
        }

        /**
         * Quick build
         * @param codePage code page
         * @return WithSetter
         */
        static WithSetter $Codepage(String codePage){
            return bulkInsert -> bulkInsert.setCodePage(c_string(codePage));
        }

        /**
         * Quick build
         * @param codePage code page
         * @return WithSetter
         */
        static WithSetter $Codepage(CodePage codePage){
            return bulkInsert -> bulkInsert.setCodePage(codePage);
        }

        /**
         * Quick build
         * @param dataFileType data file type
         * @return WithSetter
         */
        static WithSetter $Datafiletype(DataFileType dataFileType){
            return bulkInsert -> bulkInsert.setDataFileType(dataFileType);
        }

        /**
         * Quick build
         * @return CodePage
         */
        static CodePage $ACP(){
            return ACP;
        }

        /**
         * Quick build
         * @return CodePage
         */
        static CodePage $OEM(){
            return OEM;
        }

        /**
         * Quick build
         * @return CodePage
         */
        static CodePage $RAW(){
            return RAW;
        }
        CodePage ACP = new CodePage("ACP");
        CodePage OEM = new CodePage("OEM");
        CodePage RAW = new CodePage("RAW");

        /**
         * Quick build
         * @return DataFileType
         */
        static DataFileType $char(){
            return char_;
        }

        /**
         * Quick build
         * @return DataFileType
         */
        static DataFileType $native(){
            return native_;
        }

        /**
         * Quick build
         * @return DataFileType
         */
        static DataFileType $widechar(){
            return widechar_;
        }

        /**
         * Quick build
         * @return DataFileType
         */
        static DataFileType $widenative(){
            return widenative_;
        }
        DataFileType char_ = new DataFileType("char");
        DataFileType native_ = new DataFileType("native");
        DataFileType widechar_ = new DataFileType("widechar");
        DataFileType widenative_ = new DataFileType("widenative");


        /**
         * Quick build
         * @param dataSource data source
         * @return WithSetter
         */
        static WithSetter $Datasource(String dataSource){
            return bulkInsert -> bulkInsert.setDataSource(c_string(dataSource));
        }

        /**
         * Quick build
         * @param errorFile error file
         * @return WithSetter
         */
        static WithSetter $Errorfile(String errorFile){
            return bulkInsert -> bulkInsert.setErrorFile(c_string(errorFile));
        }

        /**
         * Quick build
         * @param errorFileDataSource error file data source
         * @return WithSetter
         */
        static WithSetter $ErrorfileDatasource(String errorFileDataSource){
            return bulkInsert -> bulkInsert.setErrorFileDataSource(c_string(errorFileDataSource));
        }

        /**
         * Quick build
         * @param firstRow first row
         * @return WithSetter
         */
        static WithSetter $Firstrow(Integer firstRow){
            return bulkInsert -> bulkInsert.setFirstRow(c_number(firstRow));
        }

        /**
         * Quick build
         * @return WithSetter
         */
        static WithSetter $FireTriggers(){
            return bulkInsert -> bulkInsert.setFireTriggers(true);
        }

        /**
         * Quick build
         * @param dataSourceName data source name
         * @return WithSetter
         */
        static WithSetter $FormatfileDatasource(String dataSourceName){
            return bulkInsert -> bulkInsert.setFormatFile(c_string(dataSourceName));
        }

        /**
         * Quick build
         * @return WithSetter
         */
        static WithSetter $Keepidentity(){
            return bulkInsert -> bulkInsert.setKeepIdentity(true);
        }

        /**
         * Quick build
         * @return WithSetter
         */
        static WithSetter $Keepnulls(){
            return bulkInsert -> bulkInsert.setKeepNulls(true);
        }

        /**
         * Quick build
         * @param kilobytesPerBatch kilobytes per batch
         * @return WithSetter
         */
        static WithSetter $KilobytesPerBatch(Integer kilobytesPerBatch){
            return bulkInsert -> bulkInsert.setKilobytesPerBatch(c_number(kilobytesPerBatch));
        }

        /**
         * Quick build
         * @param lastRow last row
         * @return WithSetter
         */
        static WithSetter $Lastrow(Integer lastRow){
            return bulkInsert -> bulkInsert.setLastRow(c_number(lastRow));
        }

        /**
         * Quick build
         * @param maxErrors max errors
         * @return WithSetter
         */
        static WithSetter $Maxerrors(Integer maxErrors){
            return bulkInsert -> bulkInsert.setMaxErrors(c_number(maxErrors));
        }

        /**
         * Quick build
         * @return WithSetter
         */
        static OrderColumnListBuilder<Void> $Order(){
            return new OrderColumnListBuilder<>();
        }

        /**
         * Quick build
         * @param rowsPerBatch rows per batch
         * @return WithSetter
         */
        static WithSetter $RowsPerBatch(Integer rowsPerBatch){
            return bulkInsert -> bulkInsert.setRowsPerBatch(c_number(rowsPerBatch));
        }

        /**
         * Quick build
         * @param rowTerminator row terminator
         * @return WithSetter
         */
        static WithSetter $Rowterminator(String rowTerminator){
            return bulkInsert -> bulkInsert.setRowTerminator(c_string(rowTerminator));
        }

        /**
         * Quick build
         * @return WithSetter
         */
        static WithSetter $Tablock(){
            return bulkInsert -> bulkInsert.setTabLock(true);
        }

        /**
         * Quick build
         * @param format format
         * @return WithSetter
         */
        static WithSetter $Format(String format){
            return bulkInsert -> bulkInsert.setFormat(c_string(format));
        }

        /**
         * Quick build
         * @param fieldQuote field quote
         * @return WithSetter
         */
        static WithSetter $Fieldquote(String fieldQuote){
            return bulkInsert -> bulkInsert.setFieldQuote(c_string(fieldQuote));
        }

        /**
         * Quick build
         * @param formatFile format file
         * @return WithSetter
         */
        static WithSetter $Formatfile(String formatFile){
            return bulkInsert -> bulkInsert.setFormatFile(c_string(formatFile));
        }

        /**
         * Quick build
         * @param fieldTerminator field terminator
         * @return WithSetter
         */
        static WithSetter $Fieldterminator(String fieldTerminator){
            return bulkInsert -> bulkInsert.setFieldTerminator(c_string(fieldTerminator));
        }

    }

}
