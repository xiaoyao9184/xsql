package com.xy.xsql.tsql.builder.chain.statements.alter.table;

import com.xy.xsql.core.builder.simple.CodeBuilder;
import com.xy.xsql.core.lambda.Getter;
import com.xy.xsql.tsql.model.datatypes.table.TableName;
import com.xy.xsql.tsql.model.statements.alter.table.AlterTable;
import com.xy.xsql.tsql.model.statements.alter.table.Item;

/**
 * AlterTableBuilder
 * Created by xiaoyao9184 on 2017/9/16.
 */
@SuppressWarnings("unused")
public class AlterTableBuilder extends CodeBuilder<AlterTable> {

    public AlterTableBuilder(AlterTable target) {
        super(target);
    }

    public AlterTableBuilder() {
        super(new AlterTable());
    }

    /**
     * set
     * @param tableName TableName
     * @return THIS
     */
    public AlterTableBuilder withTableName(TableName tableName){
        target.setTableName(tableName);
        return this;
    }

    /**
     * set
     * @param item Item
     * @return THIS
     */
    public AlterTableBuilder withItem(Item item){
        target.setItem(item);
        return this;
    }




    /*
    Quick
     */

    /**
     * Quick set
     * @param tableName TableName
     * @return THIS
     */
    public AlterTableBuilder $(TableName tableName){
        return withTableName(tableName);
    }

    /**
     * Quick set
     * @param item Item
     * @return THIS
     */
    public AlterTableBuilder $(Item item){
        return withItem(item);
    }

    /**
     * Quick set
     * @return THIS
     */
    public ItemBuilder<AlterTableBuilder> $(){
        return new ItemBuilder<AlterTableBuilder>()
                .enter(this, Getter.empty(), target::setItem);
    }

    /**
     * Quick in AlterColumn
     * @param columnName column name
     * @return AlterColumnBuilder
     */
    public AlterColumnBuilder<AlterTableBuilder> $AlterColumn(String columnName){
        return $()
                ._AlterColumn()
                .withColumnName(columnName);
    }

    /**
     * Quick in AlterColumn
     * @return AddBuilder
     */
    public AddBuilder<AlterTableBuilder> $Add(){
        return $()
                .$Add();
    }

    /**
     * Quick in AlterColumn
     * @return DropBuilder
     */
    public DropBuilder<AlterTableBuilder> $Drop(){
        return $()
                .$Drop();
    }
    
    /**
     * Quick in Item
     * @return ItemBuilder
     */
    public ItemBuilder<AlterTableBuilder> $With(){
        return $()
                .$With();
    }

    /**
     * Quick in AlterColumn
     * @return AlterConstraintBuilder
     */
    public AlterConstraintBuilder<AlterTableBuilder> $Check(){
        return $()
                .$Check();
    }

    /**
     * Quick in AlterColumn
     * @return AlterConstraintBuilder
     */
    public AlterConstraintBuilder<AlterTableBuilder> $Nocheck(){
        return $()
                .$Nocheck();
    }

    /**
     * Quick in AlterPartition
     * @return AlterPartitionBuilder
     */
    public AlterPartitionBuilder<AlterTableBuilder> $Switch(){
        return $()
                .$Switch();
    }

    /**
     * Quick in Rebuild
     * @return RebuildBuilder
     */
    public RebuildBuilder<AlterTableBuilder> $Rebuild(){
        return $()
                .$Rebuild();
    }

    /**
     * Quick in ?
     * @return EnableTransformBuilder
     */
    public ItemBuilder.EnableTransformBuilder<AlterTableBuilder> $Enable(){
        return $()
                .$Enable();
    }

    /**
     * Quick in ?
     * @return EnableTransformBuilder
     */
    public ItemBuilder.EnableTransformBuilder<AlterTableBuilder> $Disable(){
        return $()
                .$Disable();
    }

    /**
     * Quick in ?
     * @return SetTransformBuilder
     */
    public ItemBuilder.SetTransformBuilder<AlterTableBuilder> $Set(){
        return $()
                .$Set();
    }
    
}
