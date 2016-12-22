package com.xy.xsql.orm.data.sql.statements.dml;

import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;
import com.xy.xsql.orm.data.sql.element.OtherEnum;
import com.xy.xsql.orm.data.sql.element.info.Table;
import com.xy.xsql.orm.data.sql.sentence.BaseElementsSentence;
import com.xy.xsql.orm.data.sql.sentence.CustomizeSentence;

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
    private Table tableOrView;
    //FROM
    private String formDataFile;

//    private WithItem withItem;

    public Table getTableOrView() {
        return tableOrView;
    }

    public void setTableOrView(Table tableOrView) {
        this.tableOrView = tableOrView;
    }

    public String getFormDataFile() {
        return formDataFile;
    }

    public void setFormDataFile(String formDataFile) {
        this.formDataFile = formDataFile;
    }


    @Override
    public BaseElementsSentence toBaseElementsSentence() {
        ListElementBuilder builder = new ListElementBuilder()
                .append(GrammarEnum.BULK)
                .append(OtherEnum.SPACE)
                .append(GrammarEnum.INSERT);


        return new BaseElementsSentence(builder.build(null));
    }
}
