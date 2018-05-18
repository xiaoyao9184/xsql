package com.xy.xsql.tsql.core.statement.ddl.create.table;

import com.xy.xsql.core.builder.CodeBuilder;
import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.core.lambda.Setter;
import com.xy.xsql.tsql.core.element.column.MemoryOptimizedColumnDefinitionBuilder;
import com.xy.xsql.tsql.core.element.constraint.Foreigns;
import com.xy.xsql.tsql.core.element.constraint.PrimaryUniques;
import com.xy.xsql.tsql.core.element.table.MemoryOptimizedTableConstraintBuilder;
import com.xy.xsql.tsql.core.element.table.MemoryOptimizedTableIndexBuilder;
import com.xy.xsql.tsql.core.element.table.TableConstraintBuilder;
import com.xy.xsql.tsql.core.element.table.TableIndexBuilder;
import com.xy.xsql.tsql.model.element.ColumnName;
import com.xy.xsql.tsql.model.element.TableName;
import com.xy.xsql.tsql.model.element.column.ColumnDefinition;
import com.xy.xsql.tsql.model.element.table.TableConstraint;
import com.xy.xsql.tsql.model.element.table.TableIndex;
import com.xy.xsql.tsql.model.element.table.TableOption;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.statements.create.table.MemoryOptimizedCreateTable;

import java.util.Arrays;
import java.util.List;

import static com.xy.xsql.core.ListBuilder.initAdd;
import static com.xy.xsql.core.ListBuilder.initList;

/**
 * Created by xiaoyao9184 on 2017/8/18.
 */
public class MemoryOptimizedCreateTableBuilder extends CodeBuilder<MemoryOptimizedCreateTable> {

    public MemoryOptimizedCreateTableBuilder(MemoryOptimizedCreateTable tar) {
        super(tar);
    }

    public MemoryOptimizedCreateTableBuilder() {
        super(new MemoryOptimizedCreateTable());
    }

    public MemoryOptimizedCreateTableBuilder withTableName(TableName tableName){
        target.setTableName(tableName);
        return this;
    }

    public MemoryOptimizedCreateTableBuilder withMemoryBasedColumn(List<MemoryOptimizedCreateTable.Item> memoryItemList){
        target.setItems(memoryItemList);
        return this;
    }

    public MemoryOptimizedCreateTableBuilder withPeriodForSystemTime(String systemStartTimeColumnName, String systemEndTimeColumnName){
        target.setSystemStartTimeColumnName(systemStartTimeColumnName);
        target.setSystemEndTimeColumnName(systemEndTimeColumnName);
        return this;
    }

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
    public MemoryOptimizedCreateTableBuilder $Period_For_System_Time(String systemStartTimeColumnName, String systemEndTimeColumnName){
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


        public MemoryOptimizedColumnDefinitionBuilder<ParentBuilder> _ColumnDefinition(){
            ColumnDefinition diskBasedColumn = new ColumnDefinition();
            target.set(diskBasedColumn);
            return new MemoryOptimizedColumnDefinitionBuilder<ParentBuilder>
                    (diskBasedColumn)
                    .in(out());
        }

        public TableConstraintBuilder<ParentBuilder> _TableConstraint(){
            TableConstraint diskBasedColumn = new TableConstraint();
            target.set(diskBasedColumn);
            return new TableConstraintBuilder<ParentBuilder>
                    (diskBasedColumn)
                    .in(out());
        }

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
         * @param constraintName constraintName
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
                    .$PRIMARY_KEY();
        }

        /**
         * Transform to TableConstraint
         * @return HashBucketCountTablePrimaryUniqueBuilder
         */
        public PrimaryUniques.HashBucketCountTablePrimaryUniqueBuilder<ParentBuilder> $UNIQUE(){
            return new MemoryOptimizedTableConstraintBuilder<ParentBuilder>
                    ()
                    .enter(this.out(),cd -> target.set(cd))
                    .$UNIQUE();
        }

        /**
         * Transform to TableConstraint
         * @return ReferencesTableForeignBuilder
         */
        public Foreigns.ReferencesTableForeignBuilder<ParentBuilder> $FOREIGN_KEY(){
            return new MemoryOptimizedTableConstraintBuilder<ParentBuilder>
                    ()
                    .enter(this.out(),cd -> target.set(cd))
                    .$FOREIGN_KEY();
        }

        /**
         * Transform to TableConstraint
         * @return ParentBuilder
         */
        public ParentBuilder $CHECK(Expression logicalExpression){
            return new MemoryOptimizedTableConstraintBuilder<ParentBuilder>
                    ()
                    .enter(this.out(),cd -> target.set(cd))
                    .$CHECK(logicalExpression);
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
