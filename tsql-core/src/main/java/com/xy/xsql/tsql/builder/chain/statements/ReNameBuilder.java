package com.xy.xsql.tsql.builder.chain.statements;

import com.xy.xsql.core.builder.CodeBuilder;
import com.xy.xsql.tsql.model.datatypes.table.TableName;
import com.xy.xsql.tsql.model.statements.ReName;

import java.util.Arrays;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.TableNameFactory.t;

/**
 * ReNameBuilder
 * Created by xiaoyao9184 on 2017/3/16.
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class ReNameBuilder extends CodeBuilder<ReName> {

    public ReNameBuilder(ReName tar) {
        super(tar);
    }

    public ReNameBuilder() {
        super(new ReName());
    }

    /**
     * set
     * @param dbName db name
     * @return THIS
     */
    public ReNameBuilder withDBName(String dbName){
        target.setDbName(dbName);
        return this;
    }

    /**
     * set
     * @param tableName TableName
     * @return THIS
     */
    public ReNameBuilder withTableName(TableName tableName){
        target.setTableName(tableName);
        return this;
    }

    /**
     * set
     * @param tableName table name
     * @return THIS
     */
    public ReNameBuilder withNewName(String tableName){
        target.setNewName(tableName);
        return this;
    }

}
