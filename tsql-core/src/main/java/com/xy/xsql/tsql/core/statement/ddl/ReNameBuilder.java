package com.xy.xsql.tsql.core.statement.ddl;

import com.xy.xsql.core.builder.BaseBuilder;
import com.xy.xsql.tsql.model.element.TableName;
import com.xy.xsql.tsql.model.statement.ddl.rename.ReName;

import java.util.Arrays;

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


    /**
     * the last is new name
     * @param old_new
     * @return
     */
    public static ReName RENAME_TABLE(String... old_new){
        String[] tableNames = Arrays.copyOfRange(old_new,0,old_new.length - 1);

        return new ReNameBuilder()
                .withTableName(t(tableNames))
                .withNewName(old_new[old_new.length-1])
                .build();
    }

    public static ReName RENAME_TABLE(String tableName, String newName){
        return new ReNameBuilder()
                .withTableName(t(tableName))
                .withNewName(newName)
                .build();
    }

    public static ReName RENAME_TABLE(TableName tableName, String newName){
        return new ReNameBuilder()
                .withTableName(tableName)
                .withNewName(newName)
                .build();
    }

    public static ReName RENAME_DATABASE(String dbName, String newName){
        return new ReNameBuilder()
                .withDBName(dbName)
                .withNewName(newName)
                .build();
    }
}
