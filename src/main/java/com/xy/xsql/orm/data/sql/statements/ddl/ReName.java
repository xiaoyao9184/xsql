package com.xy.xsql.orm.data.sql.statements.ddl;

import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;
import com.xy.xsql.orm.data.sql.element.OtherEnum;
import com.xy.xsql.orm.data.sql.element.info.Table;
import com.xy.xsql.orm.data.sql.sentence.BaseElementsSentence;
import com.xy.xsql.orm.data.sql.sentence.CustomizeSentence;

/**
 *
 *

 -- Syntax for Azure SQL Data Warehouse

 -- Rename a table.
 RENAME OBJECT [ :: ]  [ [ database_name .  [schema_name ] ] . ] | [schema_name . ] ] table_name TO new_table_name
 [;]

 *

 -- Syntax for Parallel Data Warehouse

 -- Rename a table
 RENAME OBJECT [::] [ [ database_name . [ schema_name ] . ] | [ schema_name . ] ] table_name TO new_table_name
 [;]

 -- Rename a database
 RENAME DATABASE [::] database_name TO new_database_name
 [;]

 *
 * Created by xiaoyao9184 on 2016/12/23.
 */
public class ReName extends CustomizeSentence {

    private Table tableName;
    private String newTableName;

    public Table getTableName() {
        return tableName;
    }

    public void setTableName(Table tableName) {
        this.tableName = tableName;
    }

    public String getNewTableName() {
        return newTableName;
    }

    public void setNewTableName(String newTableName) {
        this.newTableName = newTableName;
    }


    @Override
    public BaseElementsSentence toBaseElementsSentence() {
        ListElementBuilder builder = new ListElementBuilder()
                .withDelimiter(OtherEnum.SPACE)
                .append(GrammarEnum.RENAME)
                .append(GrammarEnum.OBJECT)
                .append(tableName)
                .append(GrammarEnum.TO)
                .append(newTableName);

        return new BaseElementsSentence(builder.build(null));
    }
}
