package com.xy.xsql.orm.data.sql.statements.ddl.create;

import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;
import com.xy.xsql.orm.data.sql.element.OtherEnum;
import com.xy.xsql.orm.data.sql.element.info.Column;
import com.xy.xsql.orm.data.sql.element.info.Table;
import com.xy.xsql.orm.data.sql.sentence.BaseElementsSentence;
import com.xy.xsql.orm.data.sql.sentence.CustomizeSentence;

import java.util.List;

/**
 * https://msdn.microsoft.com/en-us/library/ms174979.aspx
 *

 --Simple CREATE TABLE Syntax (common if not using options)
 CREATE TABLE
 [ database_name . [ schema_name ] . | schema_name . ] table_name
 ( { <column_definition> } [ ,...n ] )
 [ ; ]

 *
 *
 * Created by xiaoyao9184 on 2016/12/23.
 */
public class CreateTable extends CustomizeSentence {

    private Table tableName;
    private List<Column> columnDefinitionList;


    public Table getTableName() {
        return tableName;
    }

    public void setTableName(Table tableName) {
        this.tableName = tableName;
    }

    public List<Column> getColumnDefinitionList() {
        return columnDefinitionList;
    }

    public void setColumnDefinitionList(List<Column> columnDefinitionList) {
        this.columnDefinitionList = columnDefinitionList;
    }


    @Override
    public BaseElementsSentence toBaseElementsSentence() {
        ListElementBuilder b = new ListElementBuilder()
                .withDelimiter(OtherEnum.SPACE)
                .append(GrammarEnum.CREATE)
                .append(GrammarEnum.TABLE)
                .append(tableName)
                .append(OtherEnum.GROUP_START)
                .append(columnDefinitionList,OtherEnum.DELIMITER)
                .append(OtherEnum.GROUP_END);

        return new BaseElementsSentence(b.build(null));
    }
}
