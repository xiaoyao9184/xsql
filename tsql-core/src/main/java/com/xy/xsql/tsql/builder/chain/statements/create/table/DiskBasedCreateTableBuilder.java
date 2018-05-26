package com.xy.xsql.tsql.builder.chain.statements.create.table;

import com.xy.xsql.core.builder.parent.ParentHoldLazyConfigBuilder;
import com.xy.xsql.core.builder.simple.CodeBuilder;
import com.xy.xsql.core.lambda.Getter;
import com.xy.xsql.tsql.builder.chain.datatypes.table.column.ColumnDefinitionBuilder;
import com.xy.xsql.tsql.builder.chain.datatypes.table.column.ColumnSetDefinitionBuilder;
import com.xy.xsql.tsql.builder.chain.datatypes.table.column.ComputedColumnDefinitionBuilder;
import com.xy.xsql.tsql.builder.chain.datatypes.table.constraint.CheckBuilder;
import com.xy.xsql.tsql.builder.chain.datatypes.table.constraint.Foreigns;
import com.xy.xsql.tsql.builder.chain.datatypes.table.constraint.PrimaryUniques;
import com.xy.xsql.tsql.builder.chain.datatypes.table.index.PartitionBuilder;
import com.xy.xsql.tsql.builder.chain.datatypes.table.table.TableConstraintBuilder;
import com.xy.xsql.tsql.builder.chain.datatypes.table.table.TableIndexBuilder;
import com.xy.xsql.tsql.model.datatypes.DataType;
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

import java.util.List;

import static com.xy.xsql.core.handler.list.ListHandler.list;
import static com.xy.xsql.core.handler.object.GetterSetterObjectHandler.object;
import static com.xy.xsql.core.handler.object.SupplierObjectHandler.object;

/**
 * DiskBasedCreateTableBuilder
 * Created by xiaoyao9184 on 2017/8/18.
 */
@SuppressWarnings({"unused","WeakerAccess"})
public class DiskBasedCreateTableBuilder extends CodeBuilder<DiskBasedCreateTable> {

    public DiskBasedCreateTableBuilder(DiskBasedCreateTable target) {
        super(target);
    }

    public DiskBasedCreateTableBuilder() {
        super(new DiskBasedCreateTable());
    }

    /**
     * set
     * @param tableName TableName
     * @return THIS
     */
    public DiskBasedCreateTableBuilder withTableName(TableName tableName){
        target.setTableName(tableName);
        return this;
    }

    /**
     * set
     * @param asFileTable as file tble
     * @return THIS
     */
    public DiskBasedCreateTableBuilder withAsFileTable(boolean asFileTable){
        target.setUseAsFileTable(asFileTable);
        return this;
    }

    /**
     * set
     * @param diskItemList Item
     * @return THIS
     */
    public DiskBasedCreateTableBuilder withDiskBasedColumn(List<DiskBasedCreateTable.Item> diskItemList){
        target.setItems(diskItemList);
        return this;
    }

    /**
     * set
     * @param systemStartTimeColumnName system start time column name
     * @param systemEndTimeColumnName system end time column name
     * @return THIS
     */
    public DiskBasedCreateTableBuilder withPeriodForSystemTime(String systemStartTimeColumnName, String systemEndTimeColumnName){
        target.setSystemStartTimeColumnName(systemStartTimeColumnName);
        target.setSystemEndTimeColumnName(systemEndTimeColumnName);
        return this;
    }

    /**
     * set
     * @param on Partition
     * @return THIS
     */
    public DiskBasedCreateTableBuilder withOn(Partition on){
        target.setOn(on);
        return this;
    }

    /**
     * set
     * @param on Partition
     * @return THIS
     */
    public DiskBasedCreateTableBuilder withTextImageOn(Partition on){
        target.setTextImageOn(on);
        return this;
    }

    /**
     * set
     * @param on Partition
     * @return THIS
     */
    public DiskBasedCreateTableBuilder withFileStreamOn(Partition on){
        target.setFileStreamOn(on);
        return this;
    }

    /**
     * set
     * @param tableOptionsList TableOption
     * @return THIS
     */
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
        list(target::getItems, target::setItems)
                .addAll(diskItems);
        return this;
    }

    /**
     * Quick in
     * @return ItemBuilder
     */
    public ItemBuilder<DiskBasedCreateTableBuilder> $(){
        list(target::getItems,target::setItems).init();
        return new ItemBuilder<DiskBasedCreateTableBuilder>()
                .enter(this, Getter.empty(), target.getItems()::add);
    }

    /**
     * Quick set
     * @param systemStartTimeColumnName systemStartTimeColumnName
     * @param systemEndTimeColumnName systemEndTimeColumnName
     * @return THIS
     */
    public DiskBasedCreateTableBuilder $PeriodForSystemTime(String systemStartTimeColumnName, String systemEndTimeColumnName){
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
                (object(target::getOn, target::setOn)
                        .init(Partition::new))
                .in(this);
    }

    /**
     * Quick in
     * @return PartitionBuilder
     */
    public PartitionBuilder<DiskBasedCreateTableBuilder> $TextImageOn(){
        return new PartitionBuilder<DiskBasedCreateTableBuilder>
                (object(target::getTextImageOn, target::setTextImageOn)
                        .init(Partition::new))
                .in(this);
    }

    /**
     * Quick in
     * @return PartitionBuilder
     */
    public PartitionBuilder<DiskBasedCreateTableBuilder> $FileStreamOn(){
        return new PartitionBuilder<DiskBasedCreateTableBuilder>
                (object(target::getFileStreamOn, target::setFileStreamOn)
                        .init(Partition::new))
                .in(this);
    }

    /**
     * Quick set
     * @param tableOptions tableOptions
     * @return THIS
     */
    public DiskBasedCreateTableBuilder $With(TableOption... tableOptions){
        list(target::getTableOptions, target::setTableOptions)
                .addAll(tableOptions);
        return this;
    }


    /**
     * ItemBuilder
     * @param <ParentBuilder>
     */
    public static class ItemBuilder<ParentBuilder>
            extends ParentHoldLazyConfigBuilder<ItemBuilder<ParentBuilder>,ParentBuilder,DiskBasedCreateTable.Item> {

        public ItemBuilder() {}

        /**
         * Confirm type of Item
         * @return ColumnDefinitionBuilder
         */
        public ColumnDefinitionBuilder<ParentBuilder> _ColumnDefinition(){
            return new ColumnDefinitionBuilder<ParentBuilder>
                    (object(ColumnDefinition::new).set(this::init))
                    .in(this.and());
        }

        /**
         * Confirm type of Item
         * @return ComputedColumnDefinitionBuilder
         */
        public ComputedColumnDefinitionBuilder<ParentBuilder> _ComputedColumnDefinition(){
            return new ComputedColumnDefinitionBuilder<ParentBuilder>
                    (object(ComputedColumnDefinition::new).set(this::init))
                    .in(this.and());
        }

        /**
         * Confirm type of Item
         * @return ColumnSetDefinitionBuilder
         */
        public ColumnSetDefinitionBuilder<ParentBuilder> _ColumnSetDefinition(){
            return new ColumnSetDefinitionBuilder<ParentBuilder>
                    (object(ColumnSetDefinition::new).set(this::init))
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
         * Transform to
         * @param columnName columnName
         * @return DiskItemColumnTransformBuilder
         */
        public DiskItemColumnTransformBuilder<ParentBuilder> $(ColumnName columnName){
            return new DiskItemColumnTransformBuilder<ParentBuilder>()
                    //TODO this build cant set to Parent
                    .enter(this.out(), Getter.empty(), this::build)
                    .withColumn(columnName);
        }

        /**
         * Transform to TableConstraint
         * @param constraintName constraint name
         * @return TableConstraintBuilder
         */
        public TableConstraintBuilder<ParentBuilder> $Constraint(String constraintName){
            return new TableConstraintBuilder<ParentBuilder>
                    (object(TableConstraint::new).set(this::init))
                    .in(this.and())
                    .withConstraintName(constraintName);
        }

        /**
         * Transform to TableConstraint
         * @return TablePrimaryUniqueBuilder
         */
        public PrimaryUniques.TablePrimaryUniqueBuilder<ParentBuilder> $PrimaryKey(){
            return new TableConstraintBuilder<ParentBuilder>
                    (object(TableConstraint::new).set(this::init))
                    .in(this.and())
                    .$PrimaryKey();
        }

        /**
         * Transform to TableConstraint
         * @return TablePrimaryUniqueBuilder
         */
        public PrimaryUniques.TablePrimaryUniqueBuilder<ParentBuilder> $Unique(){
            return new TableConstraintBuilder<ParentBuilder>
                    (object(TableConstraint::new).set(this::init))
                    .in(this.and())
                    .$Unique();
        }

        /**
         * Transform to TableConstraint
         * @return TableForeignBuilder
         */
        public Foreigns.TableForeignBuilder<ParentBuilder> $ForeignKey(){
            return new TableConstraintBuilder<ParentBuilder>
                    (object(TableConstraint::new).set(this::init))
                    .in(this.and())
                    .$ForeignKey();
        }

        /**
         * Transform to TableConstraint
         * @return CheckBuilder
         */
        public CheckBuilder<ParentBuilder> $Check(){
            return new TableConstraintBuilder<ParentBuilder>
                    (object(TableConstraint::new).set(this::init))
                    .in(this.and())
                    .$Check();
        }

        /**
         * Transform to TableIndex
         * @param indexName indexName
         * @return TableIndexBuilder
         */
        public TableIndexBuilder<ParentBuilder> $Index(String indexName){
            return new TableIndexBuilder<ParentBuilder>
                    ()
                    .in(this.and())
                    .withIndexName(indexName);
        }

    }


    /**
     * DiskItemColumnTransformBuilder
     * @param <ParentBuilder>
     */
    public static class DiskItemColumnTransformBuilder<ParentBuilder>
            extends ParentHoldLazyConfigBuilder<DiskItemColumnTransformBuilder<ParentBuilder>,ParentBuilder,DiskBasedCreateTable.Item> {

        private ColumnName columnName;

        public DiskItemColumnTransformBuilder() {}

        /**
         * set
         * @param columnName ColumnName
         * @return THIS
         */
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
                    (object(ColumnDefinition::new).set(this::init))
                    .in(this.and())
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
                    (object(ComputedColumnDefinition::new).set(this::init))
                    .in(this.and())
                    .withColumnName(columnName)
                    .withComputedColumnExpression(computedColumnExpression);
        }

        /**
         * Transform to ColumnSetDefinition
         * @return PARENT
         */
        public ParentBuilder $XmlColumnSetForAllSparseColumns(){
            return new ColumnSetDefinitionBuilder<ParentBuilder>
                    (object(ColumnSetDefinition::new).set(this::init))
                    .in(this.and())
                    .withColumnName(columnName)
                    .and();
        }
    }

}
