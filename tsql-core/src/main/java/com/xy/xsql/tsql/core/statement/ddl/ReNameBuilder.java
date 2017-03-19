package com.xy.xsql.tsql.core.statement.ddl;

import com.xy.xsql.core.builder.CodeBuilder;
import com.xy.xsql.tsql.model.element.TableName;
import com.xy.xsql.tsql.model.statement.ddl.rename.ReName;

import java.util.Arrays;

import static com.xy.xsql.tsql.core.element.TableNameBuilder.t;

/**
 * Created by xiaoyao9184 on 2017/3/16.
 */
public class ReNameBuilder extends CodeBuilder<ReName> {

    public ReNameBuilder(ReName tar) {
        super(tar);
    }

    public ReNameBuilder() {
        super(new ReName());
    }

    public ReNameBuilder withDBName(String dbName){
        tar.setDbName(dbName);
        return this;
    }

    public ReNameBuilder withTableName(TableName tableName){
        tar.setTableName(tableName);
        return this;
    }

    public ReNameBuilder withNewName(String tableName){
        tar.setNewName(tableName);
        return this;
    }


    /**
     * Quick build ReName Table
     * @param old_new the last is new name
     * @return
     */
    public static ReName RENAME_TABLE(String... old_new){
        String[] tableNames = Arrays.copyOfRange(old_new,0,old_new.length - 1);

        return new ReNameBuilder()
                .withTableName(t(tableNames))
                .withNewName(old_new[old_new.length-1])
                .build();
    }

    /**
     * Quick build ReName Table
     * @param tableName
     * @param newName
     * @return
     */
    public static ReName RENAME_TABLE(String tableName, String newName){
        return new ReNameBuilder()
                .withTableName(t(tableName))
                .withNewName(newName)
                .build();
    }

    /**
     * Quick build ReName Table
     * @param tableName
     * @param newName
     * @return
     */
    public static ReName RENAME_TABLE(TableName tableName, String newName){
        return new ReNameBuilder()
                .withTableName(tableName)
                .withNewName(newName)
                .build();
    }

    /**
     * Quick build ReName DataBase
     * @param dbName
     * @param newName
     * @return
     */
    public static ReName RENAME_DATABASE(String dbName, String newName){
        return new ReNameBuilder()
                .withDBName(dbName)
                .withNewName(newName)
                .build();
    }
}
