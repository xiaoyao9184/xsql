package com.xy.xsql.tsql.builder.chain.statements.create.table;

import com.xy.xsql.core.builder.CodeBuilder;
import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.core.lambda.Setter;
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

import java.util.Arrays;
import java.util.List;

import static com.xy.xsql.core.ListBuilder.initAdd;
import static com.xy.xsql.core.ListBuilder.initList;

/**
 * MemoryOptimizedCreateTableBuilder
 * Created by xiaoyao9184 on 2017/8/18.
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class MemoryOptimizedCreateTableBuilder extends CodeBuilder<MemoryOptimizedCreateTable> {

    public MemoryOptimizedCreateTableBuilder(MemoryOptimizedCreateTable tar) {
        super(tar);
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
        initAdd(Arrays.asList(memoryItems),
                this.target::getItems,
                this.target::setItems);
        return this;
    }

    /**
     * Quick in
     * @return ItemBuilder
     */
    public MemoryItemBuilder<MemoryOptimizedCreateTableBuilder> $(){
        initList(target::getItems,target::setItems);
        return new MemoryItemBuilder<MemoryOptimizedCreateTableBuilder>
                (target.getItems()::add)
                .in(this);
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
        initAdd(Arrays.asList(tableOptions),
                target::getTableOptions,
                target::setTableOptions);
        return this;
    }


    /**
     * MemoryItemBuilder
     * @param <ParentBuilder>
     */
    public class MemoryItemBuilder<ParentBuilder>
            extends CodeTreeBuilder<MemoryItemBuilder<ParentBuilder>,ParentBuilder,Setter<MemoryOptimizedCreateTable.Item>> {

        public MemoryItemBuilder(Setter<MemoryOptimizedCreateTable.Item> itemSetter) {
            super(itemSetter);
        }

        /**
         * Confirm type of Item
         * @return MemoryOptimizedColumnDefinitionBuilder
         */
        public MemoryOptimizedColumnDefinitionBuilder<ParentBuilder> _ColumnDefinition(){
            ColumnDefinition diskBasedColumn = new ColumnDefinition();
            target.set(diskBasedColumn);
            return new MemoryOptimizedColumnDefinitionBuilder<ParentBuilder>
                    (diskBasedColumn)
                    .in(out());
        }

        /**
         * Confirm type of Item
         * @return TableConstraintBuilder
         */
        public TableConstraintBuilder<ParentBuilder> _TableConstraint(){
            TableConstraint diskBasedColumn = new TableConstraint();
            target.set(diskBasedColumn);
            return new TableConstraintBuilder<ParentBuilder>
                    (diskBasedColumn)
                    .in(out());
        }

        /**
         * Confirm type of Item
         * @return TableIndexBuilder
         */
        public TableIndexBuilder<ParentBuilder> _TableIndex(){
            TableIndex diskBasedColumn = new TableIndex();
            target.set(diskBasedColumn);
            return new TableIndexBuilder<ParentBuilder>
                    (diskBasedColumn)
                    .in(out());
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
                    ()
                    .enter(this.out(),cd -> target.set(cd))
                    .withColumnName(columnName);
        }

        /**
         * Transform to TableConstraint
         * @param constraintName constraint name
         * @return TableConstraintBuilder
         */
        public MemoryOptimizedTableConstraintBuilder<ParentBuilder> $CONSTRAINT(String constraintName){
            return new MemoryOptimizedTableConstraintBuilder<ParentBuilder>
                    ()
                    .enter(this.out(),cd -> target.set(cd))
                    .withConstraintName(constraintName);
        }

        /**
         * Transform to TableConstraint
         * @return HashBucketCountTablePrimaryUniqueBuilder
         */
        public PrimaryUniques.HashBucketCountTablePrimaryUniqueBuilder<ParentBuilder> $PRIMARY_KEY(){
            return new MemoryOptimizedTableConstraintBuilder<ParentBuilder>
                    ()
                    .enter(this.out(),cd -> target.set(cd))
                    .$PrimaryKey();
        }

        /**
         * Transform to TableConstraint
         * @return HashBucketCountTablePrimaryUniqueBuilder
         */
        public PrimaryUniques.HashBucketCountTablePrimaryUniqueBuilder<ParentBuilder> $UNIQUE(){
            return new MemoryOptimizedTableConstraintBuilder<ParentBuilder>
                    ()
                    .enter(this.out(),cd -> target.set(cd))
                    .$Unique();
        }

        /**
         * Transform to TableConstraint
         * @return ReferencesTableForeignBuilder
         */
        public Foreigns.ReferencesTableForeignBuilder<ParentBuilder> $FOREIGN_KEY(){
            return new MemoryOptimizedTableConstraintBuilder<ParentBuilder>
                    ()
                    .enter(this.out(),cd -> target.set(cd))
                    .$ForeignKey();
        }

        /**
         * Transform to TableConstraint
         * @return PARENT
         */
        public ParentBuilder $CHECK(Expression logicalExpression){
            return new MemoryOptimizedTableConstraintBuilder<ParentBuilder>
                    ()
                    .enter(this.out(),cd -> target.set(cd))
                    .$Check(logicalExpression);
        }

        /**
         * Transform to TableIndex
         * @param indexName indexName
         * @return TableIndexBuilder
         */
        public MemoryOptimizedTableIndexBuilder<ParentBuilder> $INDEX(String indexName){
            return new MemoryOptimizedTableIndexBuilder<ParentBuilder>
                    ()
                    .enter(this.out(),cd -> target.set(cd))
                    .withIndexName(indexName);
        }

    }

}
