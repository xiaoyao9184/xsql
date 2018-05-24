package com.xy.xsql.tsql.builder.chain.statements;

import com.xy.xsql.tsql.model.datatypes.table.TableName;
import com.xy.xsql.tsql.model.statements.ReName;
import com.xy.xsql.tsql.model.statements.TruncateTable;

import java.util.Arrays;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.TableNameFactory.t;

/**
 * Statement Factory
 * Created by xiaoyao9184 on 2017/3/23.
 */
@SuppressWarnings("unused")
public interface Statements {


    /**
     * Quick in
     * @return ReNameBuilder
     */
    static ReNameBuilder $Rename(){
        return new ReNameBuilder();
    }

    /**
     * Quick build ReName Table
     * @param old_new the last is new name
     * @return ReName
     */
    static ReName $RenameTable(String... old_new){
        String[] tableNames = Arrays.copyOfRange(old_new,0,old_new.length - 1);

        return new ReNameBuilder()
                .withTableName(t(tableNames))
                .withNewName(old_new[old_new.length-1])
                .build();
    }

    /**
     * Quick build ReName Table
     * @param tableName table name
     * @param newName new name
     * @return ReName
     */
    static ReName $RenameTable(String tableName, String newName){
        return new ReNameBuilder()
                .withTableName(t(tableName))
                .withNewName(newName)
                .build();
    }

    /**
     * Quick build ReName Table
     * @param tableName table name
     * @param newName new name
     * @return ReName
     */
    static ReName $RenameTable(TableName tableName, String newName){
        return new ReNameBuilder()
                .withTableName(tableName)
                .withNewName(newName)
                .build();
    }

    /**
     * Quick build ReName DataBase
     * @param dbName db name
     * @param newName new name
     * @return ReName
     */
    static ReName $RenameDatabase(String dbName, String newName){
        return new ReNameBuilder()
                .withDBName(dbName)
                .withNewName(newName)
                .build();
    }

    /**
     * Quick in
     * @return TruncateTableBuilder
     */
    static TruncateTableBuilder $TruncateTable(){
        return new TruncateTableBuilder();
    }

    /**
     * Quick build TruncateTable
     * @param tableName TableName
     * @return TruncateTable
     */
    static TruncateTable $TruncateTable(TableName tableName){
        return new TruncateTableBuilder().withTableName(tableName).build();
    }

    /**
     * Quick in
     * @return BulkInsertBuilder
     */
    static BulkInsertBuilder $BulkInsert(){
        return new BulkInsertBuilder();
    }

    /**
     * Quick in
     * @return DeleteBuilder
     */
    static DeleteBuilder $Delete(){
        return new DeleteBuilder();
    }

    /**
     * Quick in
     * @return InsertBuilder
     */
    static InsertBuilder $Insert(){
        return new InsertBuilder();
    }

    /**
     * Quick in
     * @return MergeBuilder
     */
    static MergeBuilder $Merge(){
        return new MergeBuilder();
    }

}
