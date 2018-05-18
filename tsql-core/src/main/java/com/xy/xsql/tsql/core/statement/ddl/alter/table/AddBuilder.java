package com.xy.xsql.tsql.core.statement.ddl.alter.table;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.core.lambda.Setter;
import com.xy.xsql.tsql.core.element.column.ColumnDefinitionBuilder;
import com.xy.xsql.tsql.core.element.column.ColumnSetDefinitionBuilder;
import com.xy.xsql.tsql.core.element.column.ComputedColumnDefinitionBuilder;
import com.xy.xsql.tsql.core.element.constraint.CheckBuilder;
import com.xy.xsql.tsql.core.element.constraint.Foreigns;
import com.xy.xsql.tsql.core.element.constraint.PrimaryUniques;
import com.xy.xsql.tsql.core.element.table.TableConstraintBuilder;
import com.xy.xsql.tsql.model.datatypes.DataType;
import com.xy.xsql.tsql.model.element.ColumnName;
import com.xy.xsql.tsql.model.element.column.ColumnDefinition;
import com.xy.xsql.tsql.model.element.column.ColumnSetDefinition;
import com.xy.xsql.tsql.model.element.column.ComputedColumnDefinition;
import com.xy.xsql.tsql.model.element.table.TableConstraint;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.statements.alter.table.Add;

import java.util.Arrays;
import java.util.List;

import static com.xy.xsql.core.ListBuilder.initAdd;

/**
 * Created by xiaoyao9184 on 2017/9/16.
 */
public class AddBuilder<ParentBuilder>
        extends CodeTreeBuilder<AddBuilder<ParentBuilder>,ParentBuilder,Add> {

    public AddBuilder(Add target) {
        super(target);
    }

    public AddBuilder<ParentBuilder> withItems(List<Add.AddItem> items){
        target.setItems(items);
        return this;
    }

    public AddBuilder<ParentBuilder> withItems(Add.AddItem... items){
        target.setItems(Arrays.asList(items));
        return this;
    }

    public AddBuilder<ParentBuilder> withSystemStartTimeColumnName(String systemStartTimeColumnName){
        target.setSystemStartTimeColumnName(systemStartTimeColumnName);
        return this;
    }

    public AddBuilder<ParentBuilder> withUseHidden4StartTime(boolean useHidden4StartTime){
        target.setUseHidden4StartTime(useHidden4StartTime);
        return this;
    }

    public AddBuilder<ParentBuilder> withUseNotNull4StartTime(boolean useNotNull4StartTime){
        target.setUseNotNull4StartTime(useNotNull4StartTime);
        return this;
    }

    public AddBuilder<ParentBuilder> withConstraintName4StartTime(String constraintName4StartTime){
        target.setConstraintName4StartTime(constraintName4StartTime);
        return this;
    }

    public AddBuilder<ParentBuilder> withConstantExpression4StartTime(Expression constantExpression4StartTime){
        target.setConstantExpression4StartTime(constantExpression4StartTime);
        return this;
    }

    public AddBuilder<ParentBuilder> withUseWithValues4StartTime(boolean useWithValues4StartTime){
        target.setUseWithValues4StartTime(useWithValues4StartTime);
        return this;
    }


    public AddBuilder<ParentBuilder> withSystemEndTimeColumnName(String systemEndTimeColumnName){
        target.setSystemEndTimeColumnName(systemEndTimeColumnName);
        return this;
    }

    public AddBuilder<ParentBuilder> withUseHidden4EndTime(boolean useHidden4EndTime){
        target.setUseHidden4EndTime(useHidden4EndTime);
        return this;
    }

    public AddBuilder<ParentBuilder> withUseNotNull4EndTime(boolean useNotNull4EndTime){
        target.setUseNotNull4EndTime(useNotNull4EndTime);
        return this;
    }

    public AddBuilder<ParentBuilder> withConstraintName4EndTime(String constraintName4EndTime){
        target.setConstraintName4EndTime(constraintName4EndTime);
        return this;
    }

    public AddBuilder<ParentBuilder> withConstantExpression4EndTime(Expression constantExpression4EndTime){
        target.setConstantExpression4EndTime(constantExpression4EndTime);
        return this;
    }

    public AddBuilder<ParentBuilder> withUseWithValues4EndTime(boolean useWithValues4EndTime){
        target.setUseWithValues4EndTime(useWithValues4EndTime);
        return this;
    }

    public AddBuilder<ParentBuilder> withSystemStartTimeColumnName4Period(String systemStartTimeColumnName){
        target.setSystemStartTimeColumnName4Period(systemStartTimeColumnName);
        return this;
    }

    public AddBuilder<ParentBuilder> withSystemEndTimeColumnName4Period(String systemEndTimeColumnName){
        target.setSystemEndTimeColumnName4Period(systemEndTimeColumnName);
        return this;
    }


    /*
    Quick
     */

    private boolean tempFlagStartEnd;


    public AddItemBuilder<AddBuilder<ParentBuilder>> $(){
        return new AddItemBuilder<AddBuilder<ParentBuilder>>
                ((addItem) -> initAdd(addItem,
                        target::getItems,
                        target::setItems))
                .in(this);
    }

    public AddBuilder<ParentBuilder> $GENERATED_ALWAYS_AS_ROW_START(String systemStartTimeColumnName){
        tempFlagStartEnd = true;
        return withSystemStartTimeColumnName(systemStartTimeColumnName);
    }

    public AddBuilder<ParentBuilder> $GENERATED_ALWAYS_AS_ROW_END(String systemEndTimeColumnName){
        tempFlagStartEnd = false;
        return withSystemEndTimeColumnName(systemEndTimeColumnName);
    }

    public AddBuilder<ParentBuilder> $HIDDEN(){
        if(tempFlagStartEnd){
            return withUseHidden4StartTime(true);
        }else{
            return withUseHidden4EndTime(true);
        }
    }

    public AddBuilder<ParentBuilder> $NOT_NULL(){
        if(tempFlagStartEnd){
            return withUseNotNull4StartTime(true);
        }else{
            return withUseNotNull4EndTime(true);
        }
    }

    public AddBuilder<ParentBuilder> $CONSTRAINT(String constraintName){
        if(tempFlagStartEnd){
            return withConstraintName4StartTime(constraintName);
        }else{
            return withConstraintName4EndTime(constraintName);
        }
    }

    public AddBuilder<ParentBuilder> $DEFAULT(Expression constantExpression){
        if(tempFlagStartEnd){
            return withConstantExpression4StartTime(constantExpression);
        }else{
            return withConstantExpression4EndTime(constantExpression);
        }
    }

    public AddBuilder<ParentBuilder> $WITH_VALUES(){
        if(tempFlagStartEnd){
            return withUseWithValues4StartTime(true);
        }else{
            return withUseWithValues4EndTime(true);
        }
    }

    public AddBuilder<ParentBuilder> $PERIOD_FOR_SYSTEM_TIME(String systemStartTimeColumnName, String systemEndTimeColumnName){
        return withSystemStartTimeColumnName4Period(systemStartTimeColumnName)
                .withSystemEndTimeColumnName4Period(systemEndTimeColumnName);
    }



    public class AddItemBuilder<ParentBuilder>
            extends CodeTreeBuilder<AddItemBuilder<ParentBuilder>,ParentBuilder,Setter<Add.AddItem>> {

        public AddItemBuilder(Setter<Add.AddItem> target) {
            super(target);
        }

        public ColumnDefinitionBuilder<ParentBuilder> _ColumnDefinition(){
            ColumnDefinition columnDefinition = new ColumnDefinition();
            target.set(columnDefinition);
            return new ColumnDefinitionBuilder<ParentBuilder>
                    (columnDefinition)
                    .in(out());
        }

        public ComputedColumnDefinitionBuilder<ParentBuilder> _ComputedColumnDefinition(){
            ComputedColumnDefinition computedColumnDefinition = new ComputedColumnDefinition();
            target.set(computedColumnDefinition);
            return new ComputedColumnDefinitionBuilder<ParentBuilder>
                    (computedColumnDefinition)
                    .in(out());
        }

        public ColumnSetDefinitionBuilder<ParentBuilder> _ColumnSetDefinition(){
            ColumnSetDefinition columnSetDefinition = new ColumnSetDefinition();
            target.set(columnSetDefinition);
            return new ColumnSetDefinitionBuilder<ParentBuilder>
                    (columnSetDefinition)
                    .in(out());
        }

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
