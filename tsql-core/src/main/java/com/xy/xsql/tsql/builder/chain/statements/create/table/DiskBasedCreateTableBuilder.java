package com.xy.xsql.tsql.builder.chain.statements.create.table;

import com.xy.xsql.core.builder.CodeBuilder;
import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.core.lambda.Setter;
import com.xy.xsql.tsql.builder.chain.datatypes.table.column.ColumnDefinitionBuilder;
import com.xy.xsql.tsql.builder.chain.datatypes.table.column.ColumnSetDefinitionBuilder;
import com.xy.xsql.tsql.builder.chain.datatypes.table.column.ComputedColumnDefinitionBuilder;
import com.xy.xsql.tsql.builder.chain.datatypes.table.constraint.CheckBuilder;
import com.xy.xsql.tsql.builder.chain.datatypes.table.constraint.Foreigns;
import com.xy.xsql.tsql.builder.chain.datatypes.table.constraint.PrimaryUniques;
import com.xy.xsql.tsql.builder.chain.datatypes.table.index.PartitionBuilder;
import com.xy.xsql.tsql.builder.chain.datatypes.table.table.TableConstraintBuilder;
import com.xy.xsql.tsql.builder.chain.datatypes.table.table.TableIndexBuilder;
import com.xy.xsql.tsql.model.datatypes.table.column.DataType;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.datatypes.table.TableName;
import com.xy.xsql.tsql.model.datatypes.table.column.ColumnDefinition;
import com.xy.xsql.tsql.model.datatypes.table.column.ColumnSetDefinition;
import com.xy.xsql.tsql.model.datatypes.table.column.ComputedColumnDefinition;
import com.xy.xsql.tsql.model.datatypes.table.index.Partition;
import com.xy.xsql.tsql.model.datatypes.table.table.TableConstraint;
import com.xy.xsql.tsql.model.datatypes.table.table.TableIndex;
import com.xy.xsql.tsql.model.datatypes.table.table.TableOption;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.statements.create.table.DiskBasedCreateTable;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.xy.xsql.core.FiledBuilder.initSet;
import static com.xy.xsql.core.ListBuilder.initAdd;
import static com.xy.xsql.core.ListBuilder.initList;

/**
 * Created by xiaoyao9184 on 2017/8/18.
 */
public class DiskBasedCreateTableBuilder extends CodeBuilder<DiskBasedCreateTable> {

    public DiskBasedCreateTableBuilder(DiskBasedCreateTable createDiskTable) {
        super(createDiskTable);
    }

    public DiskBasedCreateTableBuilder() {
        super(new DiskBasedCreateTable());
    }

    public DiskBasedCreateTableBuilder withTableName(TableName tableName){
        target.setTableName(tableName);
        return this;
    }

    public DiskBasedCreateTableBuilder withAsFileTable(boolean asFileTable){
        target.setUseAsFileTable(asFileTable);
        return this;
    }

    public DiskBasedCreateTableBuilder withDiskBasedColumn(List<DiskBasedCreateTable.Item> diskItemList){
        target.setItems(diskItemList);
        return this;
    }

    public DiskBasedCreateTableBuilder withPeriodForSystemTime(String systemStartTimeColumnName, String systemEndTimeColumnName){
        target.setSystemStartTimeColumnName(systemStartTimeColumnName);
        target.setSystemEndTimeColumnName(systemEndTimeColumnName);
        return this;
    }

    public DiskBasedCreateTableBuilder withOn(Partition on){
        target.setOn(on);
        return this;
    }

    public DiskBasedCreateTableBuilder withTextImageOn(Partition on){
        target.setTextImageOn(on);
        return this;
    }

    public DiskBasedCreateTableBuilder withFileStreamOn(Partition on){
        target.setFileStreamOn(on);
        return this;
    }

    public DiskBasedCreateTableBuilder withTableOption(List<TableOption> tableOptionsList){
        target.setTableOptions(tableOptionsList);
        return this;
    }


    /*
    Quick
     */

    /**
     * Quick set
     * @param tableName tableName
     * @return THIS
     */
    public DiskBasedCreateTableBuilder $(TableName tableName) {
        return withTableName(tableName);
    }

    /**
     * Quick set
     * @param tableName tableName
     * @return THIS
     */
    public DiskBasedCreateTableBuilder $As_FileTable(TableName tableName) {
        return withTableName(tableName);
    }

    /**
     * Quick set
     * @param diskItems diskItems
     * @return THIS
     */
    public DiskBasedCreateTableBuilder $(DiskBasedCreateTable.Item... diskItems) {
        initAdd(Stream.of(diskItems)
                        .collect(Collectors.toList()),
                this.target::getItems,
                this.target::setItems);
        return this;
    }

    /**
     * Quick in
     * @return ItemBuilder
     */
    public ItemBuilder<DiskBasedCreateTableBuilder> $(){
        initList(target::getItems,target::setItems);
        return new ItemBuilder<DiskBasedCreateTableBuilder>
                (target.getItems()::add)
                .in(this);
    }

    /**
     * Quick set
     * @param systemStartTimeColumnName systemStartTimeColumnName
     * @param systemEndTimeColumnName systemEndTimeColumnName
     * @return THIS
     */
    public DiskBasedCreateTableBuilder $Period_For_System_Time(String systemStartTimeColumnName, String systemEndTimeColumnName){
        target.setSystemStartTimeColumnName(systemStartTimeColumnName);
        target.setSystemEndTimeColumnName(systemEndTimeColumnName);
        return this;
    }

    /**
     * Quick in
     * @return PartitionBuilder
     */
    public PartitionBuilder<DiskBasedCreateTableBuilder> $On(){
        return new PartitionBuilder<DiskBasedCreateTableBuilder>
                (initSet(Partition::new,
                        target::getOn,
                        target::setOn))
                .in(this);
    }

    /**
     * Quick in
     * @return PartitionBuilder
     */
    public PartitionBuilder<DiskBasedCreateTableBuilder> $TextImage_On(){
        return new PartitionBuilder<DiskBasedCreateTableBuilder>
                (initSet(Partition::new,
                        target::getTextImageOn,
                        target::setTextImageOn))
                .in(this);
    }

    /**
     * Quick in
     * @return PartitionBuilder
     */
    public PartitionBuilder<DiskBasedCreateTableBuilder> $FileStream_On(){
        return new PartitionBuilder<DiskBasedCreateTableBuilder>
                (initSet(Partition::new,
                        target::getFileStreamOn,
                        target::setFileStreamOn))
                .in(this);
    }

    /**
     * Quick set
     * @param tableOptions tableOptions
     * @return THIS
     */
    public DiskBasedCreateTableBuilder $With(TableOption... tableOptions){
        initAdd(Arrays.asList(tableOptions),
                target::getTableOptions,
                target::setTableOptions);
        return this;
    }


    /**
     *
     * @param <ParentBuilder>
     */
    public static class ItemBuilder<ParentBuilder>
            extends CodeTreeBuilder<ItemBuilder<ParentBuilder>,ParentBuilder,Setter<DiskBasedCreateTable.Item>> {

        public ItemBuilder(Setter<DiskBasedCreateTable.Item> itemSetter) {
            super(itemSetter);
        }


        public ColumnDefinitionBuilder<ParentBuilder> _ColumnDefinition(){
            ColumnDefinition diskBasedColumn = new ColumnDefinition();
            target.set(diskBasedColumn);
            return new ColumnDefinitionBuilder<ParentBuilder>
                    (diskBasedColumn)
                    .in(out());
        }

        public ComputedColumnDefinitionBuilder<ParentBuilder> _ComputedColumnDefinition(){
            ComputedColumnDefinition diskBasedColumn = new ComputedColumnDefinition();
            target.set(diskBasedColumn);
            return new ComputedColumnDefinitionBuilder<ParentBuilder>
                    (diskBasedColumn)
                    .in(out());
        }

        public ColumnSetDefinitionBuilder<ParentBuilder> _ColumnSetDefinition(){
            ColumnSetDefinition diskBasedColumn = new ColumnSetDefinition();
            target.set(diskBasedColumn);
            return new ColumnSetDefinitionBuilder<ParentBuilder>
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
         * Transform to
         * @param columnName columnName
         * @return DiskItemColumnTransformBuilder
         */
        public DiskItemColumnTransformBuilder<ParentBuilder> $(ColumnName columnName){
            return new DiskItemColumnTransformBuilder<ParentBuilder>
                    (target)
                    .in(this.out())
                    .withColumn(columnName);
        }

        /**
         * Transform to TableConstraint
         * @param constraintName constraintName
         * @return TableConstraintBuilder
         */
        public TableConstraintBuilder<ParentBuilder> $CONSTRAINT(String constraintName){
            return new TableConstraintBuilder<ParentBuilder>
                    ()
                    .enter(this.out(),cd -> target.set(cd))
                    .withConstraintName(constraintName);
        }

        /**
         * Transform to TableConstraint
         * @return TablePrimaryUniqueBuilder
         */
        public PrimaryUniques.TablePrimaryUniqueBuilder<ParentBuilder> $PRIMARY_KEY(){
            return new TableConstraintBuilder<ParentBuilder>
                    ()
                    .enter(this.out(),cd -> target.set(cd))
                    .$PRIMARY_KEY();
        }

        /**
         * Transform to TableConstraint
         * @return TablePrimaryUniqueBuilder
         */
        public PrimaryUniques.TablePrimaryUniqueBuilder<ParentBuilder> $UNIQUE(){
            return new TableConstraintBuilder<ParentBuilder>
                    ()
                    .enter(this.out(),cd -> target.set(cd))
                    .$UNIQUE();
        }

        /**
         * Transform to TableConstraint
         * @return TableForeignBuilder
         */
        public Foreigns.TableForeignBuilder<ParentBuilder> $FOREIGN_KEY(){
            return new TableConstraintBuilder<ParentBuilder>
                    ()
                    .enter(this.out(),cd -> target.set(cd))
                    .$FOREIGN_KEY();
        }

        /**
         * Transform to TableConstraint
         * @return CheckBuilder
         */
        public CheckBuilder<ParentBuilder> $CHECK(){
            return new TableConstraintBuilder<ParentBuilder>
                    ()
                    .enter(this.out(),cd -> target.set(cd))
                    .$CHECK();
        }

        /**
         * Transform to TableIndex
         * @param indexName indexName
         * @return TableIndexBuilder
         */
        public TableIndexBuilder<ParentBuilder> $INDEX(String indexName){
            return new TableIndexBuilder<ParentBuilder>
                    ()
                    .in(this.out())
                    .withIndexName(indexName);
        }

    }


    /**
     * DiskItemColumnTransformBuilder
     * @param <ParentBuilder>
     */
    public static class DiskItemColumnTransformBuilder<ParentBuilder>
            extends CodeTreeBuilder<DiskItemColumnTransformBuilder<ParentBuilder>,ParentBuilder,Setter<DiskBasedCreateTable.Item>> {

        private ColumnName columnName;

        public DiskItemColumnTransformBuilder(Setter<DiskBasedCreateTable.Item> diskBasedColumnSetter) {
            super(diskBasedColumnSetter);
        }

        public DiskItemColumnTransformBuilder<ParentBuilder> withColumn(ColumnName columnName) {
            this.columnName = columnName;
            return this;
        }

        /*
        Transform
         */

        /**
         * Transform to ColumnDefinition
         * @param dataType dataType
         * @return ColumnDefinitionBuilder
         */
        public ColumnDefinitionBuilder<ParentBuilder> $(DataType dataType){
            return new ColumnDefinitionBuilder<ParentBuilder>
                    ()
                    .in(this.out())
                    .withColumnName(columnName)
                    .withDataType(dataType);
        }

        /**
         * Transform to ComputedColumnDefinition
         * @param computedColumnExpression computedColumnExpression
         * @return ComputedColumnDefinitionBuilder
         */
        public ComputedColumnDefinitionBuilder<ParentBuilder> $As(Expression computedColumnExpression){
            return new ComputedColumnDefinitionBuilder<ParentBuilder>
                    ()
                    .in(this.out())
                    .withColumnName(columnName)
                    .withComputedColumnExpression(computedColumnExpression);
        }

        /**
         * Transform to ColumnSetDefinition
         * @return ParentBuilder
         */
        public ParentBuilder $XML_COLUMN_SET_FOR_ALL_SPARSE_COLUMNS(){
            return new ColumnSetDefinitionBuilder<ParentBuilder>
                    ()
                    .in(this.out())
                    .withColumnName(columnName)
                    .and();
        }
    }

}
