package com.xy.xsql.tsql.builder.chain.statements.alter.table;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.core.lambda.Setter;
import com.xy.xsql.tsql.builder.chain.datatypes.table.column.ColumnDefinitionBuilder;
import com.xy.xsql.tsql.builder.chain.datatypes.table.column.ColumnSetDefinitionBuilder;
import com.xy.xsql.tsql.builder.chain.datatypes.table.column.ComputedColumnDefinitionBuilder;
import com.xy.xsql.tsql.builder.chain.datatypes.table.constraint.CheckBuilder;
import com.xy.xsql.tsql.builder.chain.datatypes.table.constraint.Foreigns;
import com.xy.xsql.tsql.builder.chain.datatypes.table.constraint.PrimaryUniques;
import com.xy.xsql.tsql.builder.chain.datatypes.table.table.TableConstraintBuilder;
import com.xy.xsql.tsql.model.datatypes.DataType;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.datatypes.table.column.ColumnDefinition;
import com.xy.xsql.tsql.model.datatypes.table.column.ColumnSetDefinition;
import com.xy.xsql.tsql.model.datatypes.table.column.ComputedColumnDefinition;
import com.xy.xsql.tsql.model.datatypes.table.table.TableConstraint;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.statements.alter.table.Add;

import java.util.Arrays;
import java.util.List;

import static com.xy.xsql.core.ListBuilder.initAdd;

/**
 * AddBuilder
 * Created by xiaoyao9184 on 2017/9/16.
 */
@SuppressWarnings({"WeakerAccess", "unused","TypeParameterHidesVisibleType"})
public class AddBuilder<ParentBuilder>
        extends CodeTreeBuilder<AddBuilder<ParentBuilder>,ParentBuilder,Add> {

    public AddBuilder(Add target) {
        super(target);
    }

    /**
     * set
     * @param items AddItem
     * @return THIS
     */
    public AddBuilder<ParentBuilder> withItems(List<Add.AddItem> items){
        target.setItems(items);
        return this;
    }

    /**
     * set
     * @param items AddItem
     * @return THIS
     */
    public AddBuilder<ParentBuilder> withItems(Add.AddItem... items){
        target.setItems(Arrays.asList(items));
        return this;
    }

    /**
     * set
     * @param systemStartTimeColumnName system start time column name
     * @return THIS
     */
    public AddBuilder<ParentBuilder> withSystemStartTimeColumnName(String systemStartTimeColumnName){
        target.setSystemStartTimeColumnName(systemStartTimeColumnName);
        return this;
    }

    /**
     * set
     * @param useHidden4StartTime hidden
     * @return THIS
     */
    public AddBuilder<ParentBuilder> withUseHidden4StartTime(boolean useHidden4StartTime){
        target.setUseHidden4StartTime(useHidden4StartTime);
        return this;
    }

    /**
     * set
     * @param useNotNull4StartTime not null
     * @return THIS
     */
    public AddBuilder<ParentBuilder> withUseNotNull4StartTime(boolean useNotNull4StartTime){
        target.setUseNotNull4StartTime(useNotNull4StartTime);
        return this;
    }

    /**
     * set
     * @param constraintName4StartTime constraint name
     * @return THIS
     */
    public AddBuilder<ParentBuilder> withConstraintName4StartTime(String constraintName4StartTime){
        target.setConstraintName4StartTime(constraintName4StartTime);
        return this;
    }

    /**
     * set
     * @param constantExpression4StartTime constant expression
     * @return THIS
     */
    public AddBuilder<ParentBuilder> withConstantExpression4StartTime(Expression constantExpression4StartTime){
        target.setConstantExpression4StartTime(constantExpression4StartTime);
        return this;
    }

    /**
     * set
     * @param useWithValues4StartTime with values
     * @return THIS
     */
    public AddBuilder<ParentBuilder> withUseWithValues4StartTime(boolean useWithValues4StartTime){
        target.setUseWithValues4StartTime(useWithValues4StartTime);
        return this;
    }

    /**
     * set
     * @param systemEndTimeColumnName system end time column name
     * @return THIS
     */
    public AddBuilder<ParentBuilder> withSystemEndTimeColumnName(String systemEndTimeColumnName){
        target.setSystemEndTimeColumnName(systemEndTimeColumnName);
        return this;
    }

    /**
     * set
     * @param useHidden4EndTime hidden
     * @return THIS
     */
    public AddBuilder<ParentBuilder> withUseHidden4EndTime(boolean useHidden4EndTime){
        target.setUseHidden4EndTime(useHidden4EndTime);
        return this;
    }

    /**
     * set
     * @param useNotNull4EndTime not null
     * @return THIS
     */
    public AddBuilder<ParentBuilder> withUseNotNull4EndTime(boolean useNotNull4EndTime){
        target.setUseNotNull4EndTime(useNotNull4EndTime);
        return this;
    }

    /**
     * set
     * @param constraintName4EndTime constraint name
     * @return THIS
     */
    public AddBuilder<ParentBuilder> withConstraintName4EndTime(String constraintName4EndTime){
        target.setConstraintName4EndTime(constraintName4EndTime);
        return this;
    }

    /**
     * set
     * @param constantExpression4EndTime constant expression
     * @return THIS
     */
    public AddBuilder<ParentBuilder> withConstantExpression4EndTime(Expression constantExpression4EndTime){
        target.setConstantExpression4EndTime(constantExpression4EndTime);
        return this;
    }

    /**
     * set
     * @param useWithValues4EndTime with values
     * @return THIS
     */
    public AddBuilder<ParentBuilder> withUseWithValues4EndTime(boolean useWithValues4EndTime){
        target.setUseWithValues4EndTime(useWithValues4EndTime);
        return this;
    }

    /**
     * set
     * @param systemStartTimeColumnName system start time column name
     * @return THIS
     */
    public AddBuilder<ParentBuilder> withSystemStartTimeColumnName4Period(String systemStartTimeColumnName){
        target.setSystemStartTimeColumnName4Period(systemStartTimeColumnName);
        return this;
    }

    /**
     * set
     * @param systemEndTimeColumnName system end time column name
     * @return THIS
     */
    public AddBuilder<ParentBuilder> withSystemEndTimeColumnName4Period(String systemEndTimeColumnName){
        target.setSystemEndTimeColumnName4Period(systemEndTimeColumnName);
        return this;
    }




    /*
    Quick
     */

    private boolean tempFlagStartEnd;

    /**
     * Quick in
     * @return AddItemBuilder
     */
    public AddItemBuilder<AddBuilder<ParentBuilder>> $(){
        return new AddItemBuilder<AddBuilder<ParentBuilder>>
                ((addItem) -> initAdd(addItem,
                        target::getItems,
                        target::setItems))
                .in(this);
    }

    /**
     * Quick set
     * @param systemStartTimeColumnName system start time column name
     * @return THIS
     */
    public AddBuilder<ParentBuilder> $GeneratedAlwaysAsRowStart(String systemStartTimeColumnName){
        tempFlagStartEnd = true;
        return withSystemStartTimeColumnName(systemStartTimeColumnName);
    }

    /**
     * Quick set
     * @param systemEndTimeColumnName system end time column name
     * @return THIS
     */
    public AddBuilder<ParentBuilder> $GeneratedAlwaysAsRowEnd(String systemEndTimeColumnName){
        tempFlagStartEnd = false;
        return withSystemEndTimeColumnName(systemEndTimeColumnName);
    }

    /**
     * Quick set
     * @return THIS
     */
    public AddBuilder<ParentBuilder> $Hidden(){
        if(tempFlagStartEnd){
            return withUseHidden4StartTime(true);
        }else{
            return withUseHidden4EndTime(true);
        }
    }

    /**
     * Quick set
     * @return THIS
     */
    public AddBuilder<ParentBuilder> $NotNull(){
        if(tempFlagStartEnd){
            return withUseNotNull4StartTime(true);
        }else{
            return withUseNotNull4EndTime(true);
        }
    }

    /**
     * Quick set
     * @param constraintName constraint name
     * @return THIS
     */
    public AddBuilder<ParentBuilder> $Constraint(String constraintName){
        if(tempFlagStartEnd){
            return withConstraintName4StartTime(constraintName);
        }else{
            return withConstraintName4EndTime(constraintName);
        }
    }

    /**
     * Quick set
     * @param constantExpression constant expression
     * @return THIS
     */
    public AddBuilder<ParentBuilder> $Default(Expression constantExpression){
        if(tempFlagStartEnd){
            return withConstantExpression4StartTime(constantExpression);
        }else{
            return withConstantExpression4EndTime(constantExpression);
        }
    }

    /**
     * Quick set
     * @return THIS
     */
    public AddBuilder<ParentBuilder> $WithValues(){
        if(tempFlagStartEnd){
            return withUseWithValues4StartTime(true);
        }else{
            return withUseWithValues4EndTime(true);
        }
    }

    /**
     * Quick set
     * @param systemStartTimeColumnName system start time column name
     * @param systemEndTimeColumnName system end time column name
     * @return THIS
     */
    public AddBuilder<ParentBuilder> $PeriodForSystemTime(String systemStartTimeColumnName, String systemEndTimeColumnName){
        return withSystemStartTimeColumnName4Period(systemStartTimeColumnName)
                .withSystemEndTimeColumnName4Period(systemEndTimeColumnName);
    }


    /**
     * AddItemBuilder
     * @param <ParentBuilder>
     */
    public class AddItemBuilder<ParentBuilder>
            extends CodeTreeBuilder<AddItemBuilder<ParentBuilder>,ParentBuilder,Setter<Add.AddItem>> {

        public AddItemBuilder(Setter<Add.AddItem> target) {
            super(target);
        }

        /**
         * Confirm type of AddItem
         * @return ColumnDefinitionBuilder
         */
        public ColumnDefinitionBuilder<ParentBuilder> _ColumnDefinition(){
            ColumnDefinition columnDefinition = new ColumnDefinition();
            target.set(columnDefinition);
            return new ColumnDefinitionBuilder<ParentBuilder>
                    (columnDefinition)
                    .in(out());
        }

        /**
         * Confirm type of AddItem
         * @return ComputedColumnDefinitionBuilder
         */
        public ComputedColumnDefinitionBuilder<ParentBuilder> _ComputedColumnDefinition(){
            ComputedColumnDefinition computedColumnDefinition = new ComputedColumnDefinition();
            target.set(computedColumnDefinition);
            return new ComputedColumnDefinitionBuilder<ParentBuilder>
                    (computedColumnDefinition)
                    .in(out());
        }

        /**
         * Confirm type of AddItem
         * @return ColumnSetDefinitionBuilder
         */
        public ColumnSetDefinitionBuilder<ParentBuilder> _ColumnSetDefinition(){
            ColumnSetDefinition columnSetDefinition = new ColumnSetDefinition();
            target.set(columnSetDefinition);
            return new ColumnSetDefinitionBuilder<ParentBuilder>
                    (columnSetDefinition)
                    .in(out());
        }

        /**
         * Confirm type of AddItem
         * @return TableConstraintBuilder
         */
        public TableConstraintBuilder<ParentBuilder> _TableConstraint(){
            TableConstraint tableConstraint = new TableConstraint();
            target.set(tableConstraint);
            return new TableConstraintBuilder<ParentBuilder>
                    (tableConstraint)
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
        public ColumnTransformBuilder<ParentBuilder> $(ColumnName columnName){
            return new ColumnTransformBuilder<ParentBuilder>
                    (target)
                    .in(this.out())
                    .withColumn(columnName);
        }

        /**
         * Transform to TableConstraint
         * @param constraintName constraint name
         * @return TableConstraintBuilder
         */
        public TableConstraintBuilder<ParentBuilder> $Constraint(String constraintName){
            return new TableConstraintBuilder<ParentBuilder>
                    ()
                    .enter(this.out(),cd -> target.set(cd))
                    .withConstraintName(constraintName);
        }

        /**
         * Transform to TableConstraint
         * @return TablePrimaryUniqueBuilder
         */
        public PrimaryUniques.TablePrimaryUniqueBuilder<ParentBuilder> $PrimaryKey(){
            return new TableConstraintBuilder<ParentBuilder>
                    ()
                    .enter(this.out(),cd -> target.set(cd))
                    .$PrimaryKey();
        }

        /**
         * Transform to TableConstraint
         * @return TablePrimaryUniqueBuilder
         */
        public PrimaryUniques.TablePrimaryUniqueBuilder<ParentBuilder> $Unique(){
            return new TableConstraintBuilder<ParentBuilder>
                    ()
                    .enter(this.out(),cd -> target.set(cd))
                    .$Unique();
        }

        /**
         * Transform to TableConstraint
         * @return TableForeignBuilder
         */
        public Foreigns.TableForeignBuilder<ParentBuilder> $ForeignKey(){
            return new TableConstraintBuilder<ParentBuilder>
                    ()
                    .enter(this.out(),cd -> target.set(cd))
                    .$ForeignKey();
        }

        /**
         * Transform to TableConstraint
         * @return CheckBuilder
         */
        public CheckBuilder<ParentBuilder> $Check(){
            return new TableConstraintBuilder<ParentBuilder>
                    ()
                    .enter(this.out(),cd -> target.set(cd))
                    .$Check();
        }

    }


    /**
     * DiskItemColumnTransformBuilder
     * @param <ParentBuilder>
     */
    public static class ColumnTransformBuilder<ParentBuilder>
            extends CodeTreeBuilder<ColumnTransformBuilder<ParentBuilder>,ParentBuilder,Setter<Add.AddItem>> {

        private ColumnName columnName;

        public ColumnTransformBuilder(Setter<Add.AddItem> addItemSetter) {
            super(addItemSetter);
        }

        /**
         * set
         * @param columnName ColumnName
         * @return THIS
         */
        public ColumnTransformBuilder<ParentBuilder> withColumn(ColumnName columnName) {
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
         * @return PARENT
         */
        public ParentBuilder $XmlColumnSetForAllSparseColumns(){
            return new ColumnSetDefinitionBuilder<ParentBuilder>
                    ()
                    .in(this.out())
                    .withColumnName(columnName)
                    .and();
        }
    }


}
