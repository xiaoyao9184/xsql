package com.xy.xsql.tsql.core.statement.ddl.alter.table;

import com.xy.xsql.core.builder.CodeBuilder;
import com.xy.xsql.tsql.model.element.TableName;
import com.xy.xsql.tsql.model.statement.ddl.alter.table.AlterTable;
import com.xy.xsql.tsql.model.statement.ddl.alter.table.Item;

/**
 * Created by xiaoyao9184 on 2017/9/16.
 */
public class AlterTableBuilder extends CodeBuilder<AlterTable> {

    public AlterTableBuilder(AlterTable target) {
        super(target);
    }

    public AlterTableBuilder withTableName(TableName tableName){
        target.setTableName(tableName);
        return this;
    }

    public AlterTableBuilder withItem(Item item){
        target.setItem(item);
        return this;
    }

    /*
    Quick
     */

    public AlterTableBuilder $(TableName tableName){
        return withTableName(tableName);
    }

    public AlterTableBuilder $(Item item){
        return withItem(item);
    }

    public ItemBuilder<AlterTableBuilder> $(){
        return new ItemBuilder<AlterTableBuilder>
                (target::setItem)
                .in(this);
    }

    /**
     * Quick in AlterColumn
     * @param columnName columnName
     * @return AlterColumnBuilder
     */
    public AlterColumnBuilder<AlterTableBuilder> $ALTER_COLUMN(String columnName){
        return $()
                ._AlterColumn()
                .withColumnName(columnName);
    }

    /**
     * Quick in AlterColumn
     * @return AddBuilder
     */
    public AddBuilder<AlterTableBuilder> $ADD(){
        return $()
                .$ADD();
    }

    /**
     * Quick in AlterColumn
     * @return DropBuilder
     */
    public DropBuilder<AlterTableBuilder> $DROP(){
        return $()
                .$DROP();
    }
    
    /**
     * Temp
     * @return THIS
     */
    public ItemBuilder<AlterTableBuilder> $WITH(){
        return $()
                .$WITH();
    }

    /**
     * Quick in AlterColumn
     * @return AlterConstraintBuilder
     */
    public AlterConstraintBuilder<AlterTableBuilder> $CHECK(){
        return $()
                .$CHECK();
    }

    /**
     * Quick in AlterColumn
     * @return AlterConstraintBuilder
     */
    public AlterConstraintBuilder<AlterTableBuilder> $NOCHECK(){
        return $()
                .$NOCHECK();
    }

    /**
     * Quick in AlterPartition
     * @return AlterPartitionBuilder
     */
    public AlterPartitionBuilder<AlterTableBuilder> $SWITCH(){
        return $()
                .$SWITCH();
    }

    /**
     * Quick in Rebuild
     * @return RebuildBuilder
     */
    public RebuildBuilder<AlterTableBuilder> $REBUILD(){
        return $()
                .$REBUILD();
    }

    /**
     * Quick in ?
     * @return EnableTransformBuilder
     */
    public ItemBuilder.EnableTransformBuilder<AlterTableBuilder> $ENABLE(){
        return $()
                .$ENABLE();
    }

    /**
     * Quick in ?
     * @return EnableTransformBuilder
     */
    public ItemBuilder.EnableTransformBuilder<AlterTableBuilder> $DISABLE(){
        return $()
                .$DISABLE();
    }

    /**
     * Quick in ?
     * @return SetTransformBuilder
     */
    public ItemBuilder.SetTransformBuilder<AlterTableBuilder> $SET(){
        return $()
                .$SET();
    }
    
}
