package com.xy.xsql.tsql.core.statement.ddl;

import com.xy.xsql.core.BaseBuilder;
import com.xy.xsql.tsql.model.element.TableName;
import com.xy.xsql.tsql.model.statement.ddl.rename.ReName;

import static com.xy.xsql.tsql.core.element.TableNameBuilder.t;

/**
 * Created by xiaoyao9184 on 2017/3/16.
 */
public class ReNameBuilder implements BaseBuilder<Void,ReName> {


    private ReName reName = new ReName();

    public ReNameBuilder withDBName(String dbName){
        reName.setDbName(dbName);
        return this;
    }

    public ReNameBuilder withTableName(TableName tableName){
        reName.setTableName(tableName);
        return this;
    }

    public ReNameBuilder withNewName(String tableName){
        reName.setNewName(tableName);
        return this;
    }

    public ReName build() {
        return reName;
    }

    @Override
    public ReName build(Void aVoid) {
        return build();
    }

    public static ReName rename_t(String tableName, String newName){
        return new ReNameBuilder()
                .withTableName(t(tableName))
                .withNewName(newName)
                .build();
    }

    public static ReName rename_t(TableName tableName, String newName){
        return new ReNameBuilder()
                .withTableName(tableName)
                .withNewName(newName)
                .build();
    }

    public static ReName rename_db(String dbName, String newName){
        return new ReNameBuilder()
                .withDBName(dbName)
                .withNewName(newName)
                .build();
    }
}
