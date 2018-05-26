package com.xy.xsql.tsql.builder.chain.statements.create.table;

import com.xy.xsql.core.builder.parent.ParentHoldLazyConfigBuilder;
import com.xy.xsql.core.builder.simple.CodeBuilder;
import com.xy.xsql.core.lambda.Getter;
import com.xy.xsql.tsql.builder.chain.datatypes.table.column.MemoryOptimizedColumnDefinitionBuilder;
import com.xy.xsql.tsql.builder.chain.datatypes.table.constraint.Foreigns;
import com.xy.xsql.tsql.builder.chain.datatypes.table.constraint.PrimaryUniques;
import com.xy.xsql.tsql.builder.chain.datatypes.table.table.MemoryOptimizedTableConstraintBuilder;
import com.xy.xsql.tsql.builder.chain.datatypes.table.table.MemoryOptimizedTableIndexBuilder;
import com.xy.xsql.tsql.builder.chain.datatypes.table.table.TableConstraintBuilder;
import com.xy.xsql.tsql.builder.chain.datatypes.table.table.TableIndexBuilder;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.datatypes.table.TableName;
import com.xy.xsql.tsql.model.datatypes.table.column.ColumnDefinition;
import com.xy.xsql.tsql.model.datatypes.table.table.TableConstraint;
import com.xy.xsql.tsql.model.datatypes.table.table.TableIndex;
import com.xy.xsql.tsql.model.datatypes.table.table.TableOption;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.statements.create.table.MemoryOptimizedCreateTable;

import java.util.List;

import static com.xy.xsql.core.handler.list.ListHandler.list;
import static com.xy.xsql.core.handler.object.SupplierObjectHandler.object;

/**
 * MemoryOptimizedCreateTableBuilder
 * Created by xiaoyao9184 on 2017/8/18.
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class MemoryOptimizedCreateTableBuilder extends CodeBuilder<MemoryOptimizedCreateTable> {

    public MemoryOptimizedCreateTableBuilder(MemoryOptimizedCreateTable target) {
        super(target);
    }

    public MemoryOptimizedCreateTableBuilder() {
        super(new MemoryOptimizedCreateTable());
    }

    /**
     * set
     * @param tableName TableName
     * @return THIS
     */
    public MemoryOptimizedCreateTableBuilder withTableName(TableName tableName){
        target.setTableName(tableName);
        return this;
    }

    /**
     * set
     * @param memoryItemList MemoryOptimizedCreateTable
     * @return THIS
     */
    public MemoryOptimizedCreateTableBuilder withMemoryBasedColumn(List<MemoryOptimizedCreateTable.Item> memoryItemList){
        target.setItems(memoryItemList);
        return this;
    }

    /**
     * set
     * @param systemStartTimeColumnName system start time column name
     * @param systemEndTimeColumnName system end time column name
     * @return THIS
     */
    public MemoryOptimizedCreateTableBuilder withPeriodForSystemTime(String systemStartTimeColumnName, String systemEndTimeColumnName){
        target.setSystemStartTimeColumnName(systemStartTimeColumnName);
        target.setSystemEndTimeColumnName(systemEndTimeColumnName);
        return this;
    }

    /**
     * set
     * @param tableOptionsList TableOption
     * @return THIS
     */
    public MemoryOptimizedCreateTableBuilder withTableOption(List<TableOption> tableOptionsList){
        target.setTableOptions(tableOptionsList);
        return this;
    }




    /*
    Quick build
     */

    /**
     * Quick set
     * @param tableName tableName
     * @return THIS
     */
    public MemoryOptimizedCreateTableBuilder $(TableName tableName) {
        return withTableName(tableName);
    }

    /**
     * Quick set
     * @param memoryItems memoryItems
     * @return THIS
     */
    public MemoryOptimizedCreateTableBuilder $(MemoryOptimizedCreateTable.Item... memoryItems) {
        list(target::getItems, target::setItems)
                .addAll(memoryItems);
        return this;
    }

    /**
     * Quick in
     * @return ItemBuilder
     */
    public MemoryItemBuilder<MemoryOptimizedCreateTableBuilder> $(){
        list(target::getItems,target::setItems).init();
        return new MemoryItemBuilder<MemoryOptimizedCreateTableBuilder>()
                .enter(this, Getter.empty(), target.getItems()::add);
    }

    /**
     * Quick set
     * @param systemStartTimeColumnName systemStartTimeColumnName
     * @param systemEndTimeColumnName systemEndTimeColumnName
     * @return THIS
     */
    public MemoryOptimizedCreateTableBuilder $PeriodForSystemTime(String systemStartTimeColumnName, String systemEndTimeColumnName){
        return withPeriodForSystemTime(systemStartTimeColumnName, systemEndTimeColumnName);
    }

    /**
     * Quick set
     * @param tableOptions tableOptions
     * @return THIS
     */
    public MemoryOptimizedCreateTableBuilder $With(TableOption... tableOptions){
        list(target::getTableOptions, target::setTableOptions)
                .addAll(tableOptions);
        return this;
    }


    /**
     * MemoryItemBuilder
     * @param <ParentBuilder>
     */
    public class MemoryItemBuilder<ParentBuilder>
            extends ParentHoldLazyConfigBuilder<MemoryItemBuilder<ParentBuilder>,ParentBuilder,MemoryOptimizedCreateTable.Item> {

        public MemoryItemBuilder() {}

        /**
         * Confirm type of Item
         * @return MemoryOptimizedColumnDefinitionBuilder
         */
        public MemoryOptimizedColumnDefinitionBuilder<ParentBuilder> _ColumnDefinition(){
            return new MemoryOptimizedColumnDefinitionBuilder<ParentBuilder>
                    (object(ColumnDefinition::new).set(this::init))
                    .in(this.and());
        }

        /**
         * Confirm type of Item
         * @return TableConstraintBuilder
         */
        public TableConstraintBuilder<ParentBuilder> _TableConstraint(){
            return new TableConstraintBuilder<ParentBuilder>
                    (object(TableConstraint::new).set(this::init))
                    .in(this.and());
        }

        /**
         * Confirm type of Item
         * @return TableIndexBuilder
         */
        public TableIndexBuilder<ParentBuilder> _TableIndex(){
            return new TableIndexBuilder<ParentBuilder>
                    (object(TableIndex::new).set(this::init))
                    .in(this.and());
        }




        /*
        Transform
         */

        /**
         * Transform to ColumnDefinition
         * @param columnName columnName
         * @return MemoryOptimizedColumnDefinitionBuilder
         */
        public MemoryOptimizedColumnDefinitionBuilder<ParentBuilder> $(ColumnName columnName){
            return new MemoryOptimizedColumnDefinitionBuilder<ParentBuilder>
                    (object(ColumnDefinition::new).set(this::init))
                    .in(this.and())
                    .withColumnName(columnName);
        }

        /**
         * Transform to TableConstraint
         * @param constraintName constraint name
         * @return TableConstraintBuilder
         */
        public MemoryOptimizedTableConstraintBuilder<ParentBuilder> $CONSTRAINT(String constraintName){
            return new MemoryOptimizedTableConstraintBuilder<ParentBuilder>
                    (object(TableConstraint::new).set(this::init))
                    .in(this.and())
                    .withConstraintName(constraintName);
        }

        /**
         * Transform to TableConstraint
         * @return HashBucketCountTablePrimaryUniqueBuilder
         */
        public PrimaryUniques.HashBucketCountTablePrimaryUniqueBuilder<ParentBuilder> $PrimaryKey(){
            return new MemoryOptimizedTableConstraintBuilder<ParentBuilder>
                    (object(TableConstraint::new).set(this::init))
                    .in(this.and())
                    .$PrimaryKey();
        }

        /**
         * Transform to TableConstraint
         * @return HashBucketCountTablePrimaryUniqueBuilder
         */
        public PrimaryUniques.HashBucketCountTablePrimaryUniqueBuilder<ParentBuilder> $UNIQUE(){
            return new MemoryOptimizedTableConstraintBuilder<ParentBuilder>
                    (object(TableConstraint::new).set(this::init))
                    .in(this.and())
                    .$Unique();
        }

        /**
         * Transform to TableConstraint
         * @return ReferencesTableForeignBuilder
         */
        public Foreigns.ReferencesTableForeignBuilder<ParentBuilder> $ForeignKey(){
            return new MemoryOptimizedTableConstraintBuilder<ParentBuilder>
                    (object(TableConstraint::new).set(this::init))
                    .in(this.and())
                    .$ForeignKey();
        }

        /**
         * Transform to TableConstraint
         * @return PARENT
         */
        public ParentBuilder $CHECK(Expression logicalExpression){
            return new MemoryOptimizedTableConstraintBuilder<ParentBuilder>
                    (object(TableConstraint::new).set(this::init))
                    .in(this.and())
                    .$Check(logicalExpression);
        }

        /**
         * Transform to TableIndex
         * @param indexName indexName
         * @return TableIndexBuilder
         */
        public MemoryOptimizedTableIndexBuilder<ParentBuilder> $INDEX(String indexName){
            return new MemoryOptimizedTableIndexBuilder<ParentBuilder>
                    (object(TableIndex::new).set(this::init))
                    .in(this.and())
                    .withIndexName(indexName);
        }

    }

}
