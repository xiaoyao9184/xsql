package com.xy.xsql.orm.data.sql.statements.ddl.drop;

import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;
import com.xy.xsql.orm.data.sql.element.OtherEnum;
import com.xy.xsql.orm.data.sql.element.info.Table;
import com.xy.xsql.orm.data.sql.sentence.BaseElementsSentence;
import com.xy.xsql.orm.data.sql.sentence.CustomizeSentence;

import java.util.List;

/**
 * https://msdn.microsoft.com/en-us/library/ms173790.aspx
 *

 -- Syntax for SQL Server and Azure SQL Database

 DROP TABLE [ IF EXISTS ] [ database_name . [ schema_name ] . | schema_name . ]
 table_name [ ,...n ]
 [ ; ]

 *

 -- Syntax for Azure SQL Data Warehouse and Parallel Data Warehouse

 DROP TABLE [ database_name . [ schema_name ] . | schema_name . ] table_name
 [;]

 *
 * Created by xiaoyao9184 on 2016/12/23.
 */
public class DropTable extends CustomizeSentence {

    private boolean useIfExists;
    private List<Table> tableNameList;


    public boolean isUseIfExists() {
        return useIfExists;
    }

    public void setUseIfExists(boolean useIfExists) {
        this.useIfExists = useIfExists;
    }

    public List<Table> getTableNameList() {
        return tableNameList;
    }

    public void setTableNameList(List<Table> tableNameList) {
        this.tableNameList = tableNameList;
    }


    @Override
    public BaseElementsSentence toBaseElementsSentence() {
        ListElementBuilder b = new ListElementBuilder()
                .withDelimiter(OtherEnum.SPACE)
                .append(GrammarEnum.DROP)
                .append(GrammarEnum.TABLE)
                .append(useIfExists ? GrammarEnum.IF : null)
                .append(useIfExists ? GrammarEnum.EXISTS : null);
        int i = 0;
        for (Table tableName: tableNameList) {
            b.append(i==0 ? null : OtherEnum.DELIMITER)
                    .append(tableName);
            i++;
        }

        return new BaseElementsSentence(b.build(null));
    }
}
