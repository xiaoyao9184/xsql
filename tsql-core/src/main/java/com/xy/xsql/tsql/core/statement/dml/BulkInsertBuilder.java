package com.xy.xsql.tsql.core.statement.dml;

import com.xy.xsql.core.builder.CodeBuilder;
import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.core.configurator.BaseConfigurator;
import com.xy.xsql.tsql.model.datatype.NumberConstant;
import com.xy.xsql.tsql.model.datatype.StringConstant;
import com.xy.xsql.tsql.model.element.ColumnName;
import com.xy.xsql.tsql.model.element.TableName;
import com.xy.xsql.tsql.model.statement.dml.BulkInsert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.xy.xsql.core.FiledBuilder.initSet;
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
    public BulkInsertBuilder withCodePage(CodePage codePage){
        target.setCodePage(codePage);
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
    public BulkInsertBuilder withDataFileType(DataFileType dataFileType){
        target.setDataFileType(dataFileType);
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
    public BulkInsertBuilder $With(WithSetter... items){
        Arrays.stream(items)
                .forEach(item -> item.config(target));
        return this;
    }



    public static class OrderColumnBuilder<ParentBuilder>
            extends CodeTreeBuilder<OrderColumnBuilder<ParentBuilder>,ParentBuilder,BulkInsert.OrderColumn> {


        public OrderColumnBuilder(BulkInsert.OrderColumn orderColumn) {
            super(orderColumn);
        }

        public OrderColumnBuilder<ParentBuilder> withAsc(){
            target.setUseAsc(true);
            return this;
        }

        @Deprecated
        public OrderColumnBuilder<ParentBuilder> withAsc(boolean useAsc){
            target.setUseAsc(useAsc);
            return this;
        }

        public OrderColumnBuilder<ParentBuilder> withDesc(){
            target.setUseDesc(true);
            return this;
        }

        @Deprecated
        public OrderColumnBuilder<ParentBuilder> withDesc(boolean useDesc){
            target.setUseDesc(useDesc);
            return this;
        }

        public OrderColumnBuilder<ParentBuilder> withColumnName(ColumnName columnName){
            target.setColumn(columnName);
            return this;
        }

    }

    public static class OrderBuilder
            extends CodeBuilder<List<BulkInsert.OrderColumn>>
            implements WithSetter {

        public OrderBuilder(){
            super(new ArrayList<>());
        }

        public OrderBuilder(BulkInsert.OrderColumn... columns){
            super(Arrays.asList(columns));
        }

        @Override
        public void config(BulkInsert bulkInsert) {
            initAdd(target,
                    bulkInsert::getOrderList,
                    bulkInsert::setOrderList);
        }


        private BulkInsert.OrderColumn getLastItem(){
            if(target.size() > 0){
                int i = target.size() - 1;
                return target.get(i);
            }
            return null;
        }

        public OrderColumnBuilder<OrderBuilder> withItem(){
            return new OrderColumnBuilder<OrderBuilder>
                    (initSet(BulkInsert.OrderColumn::new,
                            () -> null,
                            (i) -> target.add(i)))
                    .in(this);
        }


        public OrderBuilder $(ColumnName columnName) {
            return withItem()
                    .withColumnName(columnName)
                    .and();
        }

        public OrderBuilder $Aes(){
            BulkInsert.OrderColumn last = getLastItem();
            return new OrderColumnBuilder<OrderBuilder>
                    (last)
                    .in(this)
                    .withAsc()
                    .and();
        }

        public OrderBuilder $Desc(){
            BulkInsert.OrderColumn last = getLastItem();
            return new OrderColumnBuilder<OrderBuilder>
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

        static WithSetter BATCHSIZE(Integer batchSize){
            return bulkInsert -> bulkInsert.setBatchSize(new NumberConstant(batchSize));
        }

        static WithSetter CHECK_CONSTRAINTS(){
            return bulkInsert -> bulkInsert.setCheckConstraints(true);
        }

        static WithSetter CODEPAGE(String codePage){
            return bulkInsert -> bulkInsert.setCodePage(new StringConstant(codePage));
        }

        static WithSetter CODEPAGE(CodePage codePage){
            return bulkInsert -> bulkInsert.setCodePage(codePage);
        }

        static WithSetter DATAFILETYPE(DataFileType dataFileType){
            return bulkInsert -> bulkInsert.setDataFileType(dataFileType);
        }

        static CodePage ACP(){
            return new CodePage("ACP");
        }
        static CodePage OEM(){
            return new CodePage("OEM");
        }
        static CodePage RAW(){
            return new CodePage("RAW");
        }
        CodePage ACP = new CodePage("ACP");
        CodePage OEM = new CodePage("OEM");
        CodePage RAW = new CodePage("RAW");

        static DataFileType char_(){
            return new DataFileType("char");
        }
        static DataFileType native_(){
            return new DataFileType("native");
        }
        static DataFileType widechar_(){
            return new DataFileType("widechar");
        }
        static DataFileType widenative_(){
            return new DataFileType("widenative");
        }
        DataFileType char_ = new DataFileType("char");
        DataFileType native_ = new DataFileType("native");
        DataFileType widechar_ = new DataFileType("widechar");
        DataFileType widenative_ = new DataFileType("widenative");


        static WithSetter DATASOURCE(String dataSource){
            return bulkInsert -> bulkInsert.setDataSource(new StringConstant(dataSource));
        }

        static WithSetter ERRORFILE(String errorFile){
            return bulkInsert -> bulkInsert.setErrorFile(new StringConstant(errorFile));
        }

        static WithSetter ERRORFILE_DATASOURCE(String errorFileDataSource){
            return bulkInsert -> bulkInsert.setErrorFileDataSource(new StringConstant(errorFileDataSource));
        }

        static WithSetter FIRSTROW(Integer firstRow){
            return bulkInsert -> bulkInsert.setFirstRow(new NumberConstant(firstRow));
        }

        static WithSetter FIRE_TRIGGERS(){
            return bulkInsert -> bulkInsert.setFireTriggers(true);
        }

        static WithSetter FORMATFILE_DATASOURCE(String dataSourceName){
            return bulkInsert -> bulkInsert.setFormatFile(new StringConstant(dataSourceName));
        }

        static WithSetter KEEPIDENTITY(){
            return bulkInsert -> bulkInsert.setKeepIdentity(true);
        }

        static WithSetter KEEPNULLS(){
            return bulkInsert -> bulkInsert.setKeepNulls(true);
        }

        static WithSetter KILOBYTES_PER_BATCH(Integer kilobytesPerBatch){
            return bulkInsert -> bulkInsert.setKilobytesPerBatch(new NumberConstant(kilobytesPerBatch));
        }

        static WithSetter LASTROW(Integer lastRow){
            return bulkInsert -> bulkInsert.setLastRow(new NumberConstant(lastRow));
        }

        static WithSetter MAXERRORS(Integer maxErrors){
            return bulkInsert -> bulkInsert.setMaxErrors(new NumberConstant(maxErrors));
        }

        static WithSetter ORDER(){
            return new OrderBuilder();
        }

        static WithSetter ROWS_PER_BATCH(Integer rowsPerBatch){
            return bulkInsert -> bulkInsert.setRowsPerBatch(new NumberConstant(rowsPerBatch));
        }

        static WithSetter ROWTERMINATOR(String rowTerminator){
            return bulkInsert -> bulkInsert.setRowTerminator(new StringConstant(rowTerminator));
        }

        static WithSetter TABLOCK (){
            return bulkInsert -> bulkInsert.setTabLock(true);
        }

        static WithSetter FORMAT(String format){
            return bulkInsert -> bulkInsert.setFormat(new StringConstant(format));
        }

        static WithSetter FIELDQUOTE(String fieldQuote){
            return bulkInsert -> bulkInsert.setFieldQuote(new StringConstant(fieldQuote));
        }

        static WithSetter FORMATFILE(String formatFile){
            return bulkInsert -> bulkInsert.setFormatFile(new StringConstant(formatFile));
        }

        static WithSetter FIELDTERMINATOR(String fieldTerminator){
            return bulkInsert -> bulkInsert.setFieldTerminator(new StringConstant(fieldTerminator));
        }
    }

}
