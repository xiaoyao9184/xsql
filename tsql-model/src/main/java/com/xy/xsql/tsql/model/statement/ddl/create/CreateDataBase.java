package com.xy.xsql.tsql.model.statement.ddl.create;

import com.xy.xsql.tsql.model.datatype.ColumnDefinition;
import com.xy.xsql.tsql.model.element.TableName;
import com.xy.xsql.tsql.model.statement.Statement;

import java.util.List;

/**
 * https://docs.microsoft.com/en-us/sql/t-sql/statements/create-database-sql-server-transact-sql#syntax
 *
 *
 * Created by xiaoyao9184 on 2017/8/3.
 */
public class CreateDataBase implements Statement {

    private String databaseName;
    private boolean useContainmentNone;
    private boolean useContainmentPartial;
    private boolean useOn;

    //TODO
//    [ ON
//      [ PRIMARY ] <filespec> [ ,...n ]
//            [ , <filegroup> [ ,...n ] ]
//            [ LOG ON <filespec> [ ,...n ] ]
//            ]
//            [ COLLATE collation_name ]
//            [ WITH  <option> [,...n ] ]

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public boolean isUseContainmentNone() {
        return useContainmentNone;
    }

    public void setUseContainmentNone(boolean useContainmentNone) {
        this.useContainmentNone = useContainmentNone;
    }

    public boolean isUseContainmentPartial() {
        return useContainmentPartial;
    }

    public void setUseContainmentPartial(boolean useContainmentPartial) {
        this.useContainmentPartial = useContainmentPartial;
    }

    public boolean isUseOn() {
        return useOn;
    }

    public void setUseOn(boolean useOn) {
        this.useOn = useOn;
    }

}
