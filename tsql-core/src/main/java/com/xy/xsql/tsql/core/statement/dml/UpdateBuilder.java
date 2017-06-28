package com.xy.xsql.tsql.core.statement.dml;

import com.xy.xsql.core.builder.CodeBuilder;
import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.core.lambda.Setter;
import com.xy.xsql.tsql.core.clause.*;
import com.xy.xsql.tsql.model.clause.*;
import com.xy.xsql.tsql.model.element.Alias;
import com.xy.xsql.tsql.model.element.ColumnName;
import com.xy.xsql.tsql.model.element.TableName;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.model.operator.Compound;
import com.xy.xsql.tsql.model.statement.dml.Update;
import com.xy.xsql.tsql.model.variable.LocalVariable;
import com.xy.xsql.util.CheckUtil;

import java.util.Arrays;

import static com.xy.xsql.core.FiledBuilder.initSet;
import static com.xy.xsql.core.ListBuilder.initAdd;
import static com.xy.xsql.core.ListBuilder.initList;

/**
 * Created by xiaoyao9184 on 2017/1/7.
 */
public class UpdateBuilder extends CodeBuilder<Update> {

    public UpdateBuilder(Update tar) {
        super(tar);
    }

    public UpdateBuilder(){
        super(new Update());
    }


    /**
     *
     * @return
     */
    public WithBuilder<UpdateBuilder> withWith(){
        return new WithBuilder<UpdateBuilder>
                (initSet(With::new,
                        target::getWith,
                        target::setWith))
                .in(this);
    }

    /**
     *
     * @return
     */
    public TopBuilder<UpdateBuilder> withTop(){
        return new TopBuilder<UpdateBuilder>
                (initSet(Top::new,
                        target::getTop,
                        target::setTop))
                .in(this);
    }

    /**
     *
     * @param tableAlias
     * @return
     */
    public UpdateBuilder withTableAlias(String tableAlias){
        target.setTableAlias(new Alias<>(tableAlias));
        return this;
    }

    /**
     *
     * @return
     */
    public UpdateBuilder withTableName(TableName tableName){
        target.setTableName(tableName);
        return this;
    }

    /**
     *
     * @param tableName
     * @return
     */
    public UpdateBuilder withTableName(String tableName){
        target.setTableName(new TableName(tableName));
        return this;
    }

//    /**
//     *
//     * @return
//     */
//    @Deprecated
//    public SetListBuilder<UpdateBuilder> withSetList(){
//        List<Update.SetItem> setList = new ArrayList<>();
//        target.setSets(setList);
//        return new SetListBuilder<UpdateBuilder>
//                (setList)
//                .in(this);
//    }

    /**
     *
     * @return
     */
    public SetItemBuilder<UpdateBuilder> withSetItem(){
        initList(target::getSets,
                target::setSets);
        return new SetItemBuilder<UpdateBuilder>
                (target.getSets()::add)
                .in(this);
    }

    /**
     *
     * @return
     */
    public OutputBuilder<UpdateBuilder> withOutput() {
        return new OutputBuilder<UpdateBuilder>
                (initSet(Output::new,
                        target::getOutput,
                        target::setOutput))
                .in(this);
    }

    /**
     *
     * @return
     */
    public FromBuilder<UpdateBuilder> withFrom() {
        return new FromBuilder<UpdateBuilder>
                (initSet(From::new,
                        target::getFrom,
                        target::setFrom))
                .in(this);
    }

    /**
     *
     * @return
     */
    public WhereBuilder<UpdateBuilder> withWhere() {
        return new WhereBuilder<UpdateBuilder>
                (initSet(Where::new,
                        target::getWhere,
                        target::setWhere))
                .in(this);
    }

    /**
     *
     * @return
     */
    public OptionBuilder<UpdateBuilder> withOption() {
        return new OptionBuilder<UpdateBuilder>
                (initSet(Option::new,
                        target::getOption,
                        target::setOption))
                .in(this);
    }




    /**
     *
     * @return
     */
    public static UpdateBuilder UPDATE(){
        return new UpdateBuilder();
    }

    /**
     * Quick set
     * @return
     */
    public WithBuilder<UpdateBuilder> With(){
        return withWith();
    }

    /**
     * Quick set
     * @return
     */
    public TopBuilder<UpdateBuilder> $Top(){
        return withTop();
    }

    /**
     * Quick set
     * @return
     */
    public UpdateBuilder $(String tableAlias){
        return withTableAlias(tableAlias);
    }

    /**
     * Quick set
     * @return
     */
    public UpdateBuilder $(TableName tableName){
        return withTableName(tableName);
    }

//    /**
//     * Quick set
//     * @return
//     */
//    @Deprecated
//    public SetListBuilder<UpdateBuilder> $Set(){
//        return withSetList();
//    }

    /**
     * Quick set
     * @return
     */
    public UpdateBuilder $Set(Update.SetItem... items){
        if(CheckUtil.isNullOrEmpty(items)){
            return this;
        }
        initAdd(Arrays.asList(items),
                target::getSets,
                target::setSets);
        return this;
    }

    /**
     * Quick set
     * @return
     */
    public OutputBuilder<UpdateBuilder> $Output(){
        return withOutput();
    }

    /**
     * Quick set
     * @return
     */
    public FromBuilder<UpdateBuilder> $From(){
        return withFrom();
    }

    /**
     * Quick set
     * @return
     */
    public WhereBuilder<UpdateBuilder> $Where(){
        return withWhere();
    }

    /**
     * Quick set
     * @return
     */
    public OptionBuilder<UpdateBuilder> $Option(){
        return withOption();
    }





//    /**
//     *
//     * @param <ParentBuilder>
//     */
//    public static class SetListBuilder<ParentBuilder>
//            extends CodeTreeBuilder<SetListBuilder<ParentBuilder>,ParentBuilder,List<Update.Set>> {
//
//        public SetListBuilder(List<Update.Set> setList) {
//            super(setList);
//        }
//
//        public SetBuilder<SetListBuilder<ParentBuilder>> withItem(){
//            Update.Set set = new Update.Set();
//            if(target == null){
//                target = new ArrayList<>();
//            }
//            target.add(set);
//            return new SetBuilder<SetListBuilder<ParentBuilder>>
//                    (initSet(Update.Set::new,
//                            target::add))
//                    .in(this);
//        }
//
//        public SetListBuilder<ParentBuilder> $(GroupExpression groupExpression) {
//
//        }
//
//        public SetBuilder<SetListBuilder<ParentBuilder>> $() {
//            return withItem();
//        }
//    }
//
//    /**
//     *
//     * @param <ParentBuilder>
//     */
//    public static class SetBuilder<ParentBuilder>
//            extends CodeTreeBuilder<SetBuilder<ParentBuilder>,ParentBuilder,Update.Set> {
//
//        public SetBuilder(Update.Set set) {
//            super(set);
//        }
//
//        public SetBuilder<ParentBuilder> withColumnName(ColumnName column){
//            target.setColumnName(column);
//            return this;
//        }
//
//        public SetBuilder<ParentBuilder> withColumnName(String columnName) {
//            target.setColumnName(new ColumnName(columnName));
//            return this;
//        }
//
//        public SetBuilder<ParentBuilder> withExpression(Expression expression){
//            target.setExpression(expression);
//            return this;
//        }
//
//        public SetBuilder<ParentBuilder> withUseNull(boolean useNull){
//            target.setUseNull(useNull);
//            return this;
//        }
//    }


    /**
     * Abstract SetItem Builder
     *
     * @param <ParentBuilder>
     */
    public static class SetItemBuilder<ParentBuilder>
            extends CodeTreeBuilder<SetItemBuilder<ParentBuilder>,ParentBuilder,Setter<Update.SetItem>> {

        public SetItemBuilder(Setter<Update.SetItem> tar) {
            super(tar);
        }


        public ColumnAssignmentSetBuilder<ParentBuilder> _ColumnAssignment(){
            Update.ColumnAssignmentSet setItem = new Update.ColumnAssignmentSet();
            target.set(setItem);
            return new ColumnAssignmentSetBuilder<ParentBuilder>
                    (setItem)
                    .in(out());
        }

        public VariableAssignmentSetBuilder<ParentBuilder> _VariableAssignment(){
            Update.VariableAssignmentSet setItem = new Update.VariableAssignmentSet();
            target.set(setItem);
            return new VariableAssignmentSetBuilder<ParentBuilder>
                    (setItem)
                    .in(out());
        }

        public VariableColumnAssignmentSetBuilder<ParentBuilder> _VariableColumnAssignment(){
            Update.VariableColumnAssignmentSet setItem = new Update.VariableColumnAssignmentSet();
            target.set(setItem);
            return new VariableColumnAssignmentSetBuilder<ParentBuilder>
                    (setItem)
                    .in(out());
        }

        public ColumnCompoundSetBuilder<ParentBuilder> _ColumnCompound(){
            Update.ColumnCompoundSet setItem = new Update.ColumnCompoundSet();
            target.set(setItem);
            return new ColumnCompoundSetBuilder<ParentBuilder>
                    (setItem)
                    .in(out());
        }

        public VariableCompoundSetBuilder<ParentBuilder> _VariableCompound(){
            Update.VariableCompoundSet setItem = new Update.VariableCompoundSet();
            target.set(setItem);
            return new VariableCompoundSetBuilder<ParentBuilder>
                    (setItem)
                    .in(out());
        }

        public VariableColumnCompoundSetBuilder<ParentBuilder> _VariableColumnCompound(){
            Update.VariableColumnCompoundSet setItem = new Update.VariableColumnCompoundSet();
            target.set(setItem);
            return new VariableColumnCompoundSetBuilder<ParentBuilder>
                    (setItem)
                    .in(out());
        }


        /**
         * ColumnAssignment SetItem
         * @param columnName
         * @return
         */
        public static Update.SetItem s_default(ColumnName columnName){
            return new ColumnAssignmentSetBuilder<Void>()
                    .withColumnName(columnName)
                    .withUseDefault(true)
                    .build();
        }

        /**
         * ColumnAssignment SetItem
         * @param columnName
         * @return
         */
        public static Update.SetItem s_null(ColumnName columnName){
            return new ColumnAssignmentSetBuilder<Void>()
                    .withColumnName(columnName)
                    .withUseNull(true)
                    .build();
        }

        /**
         * ColumnAssignment SetItem
         * @param columnName
         * @param expression
         * @return
         */
        public static Update.SetItem s(ColumnName columnName, Expression expression){
            return new ColumnAssignmentSetBuilder<Void>()
                    .withColumnName(columnName)
                    .withExpression(expression)
                    .build();
        }

        /**
         * Variable Assignment SetItem
         * @param variable
         * @param expression
         * @return
         */
        public static Update.SetItem s(LocalVariable variable, Expression expression){
            return new VariableAssignmentSetBuilder<Void>()
                    .withVariable(variable)
                    .withExpression(expression)
                    .build();
        }

        /**
         * VariableColumnAssignment SetItem
         * @param variable
         * @param columnName
         * @param expression
         * @return
         */
        public static Update.SetItem s(LocalVariable variable, ColumnName columnName, Expression expression){
            return new VariableColumnAssignmentSetBuilder<Void>()
                    .withVariable(variable)
                    .withColumnName(columnName)
                    .withExpression(expression)
                    .build();
        }

        /**
         * ColumnCompound SetItem
         * @param columnName
         * @param compound
         * @param expression
         * @return
         */
        public static Update.SetItem s(ColumnName columnName, Compound compound, Expression expression){
            return new ColumnCompoundSetBuilder<Void>()
                    .withColumnName(columnName)
                    .withCompound(compound)
                    .withExpression(expression)
                    .build();
        }

        /**
         * VariableCompound SetItem
         * @param variable
         * @param compound
         * @param expression
         * @return
         */
        public static Update.SetItem s(LocalVariable variable, Compound compound, Expression expression){
            return new VariableCompoundSetBuilder<Void>()
                    .withVariable(variable)
                    .withCompound(compound)
                    .withExpression(expression)
                    .build();
        }

        /**
         * VariableColumnCompound SetItem
         * @param variable
         * @param columnName
         * @param compound
         * @param expression
         * @return
         */
        public static Update.SetItem s(LocalVariable variable, ColumnName columnName, Compound compound, Expression expression){
            return new VariableColumnCompoundSetBuilder<Void>()
                    .withVariable(variable)
                    .withColumnName(columnName)
                    .withCompound(compound)
                    .withExpression(expression)
                    .build();
        }

    }


    public static class ColumnAssignmentSetBuilder<ParentBuilder>
            extends CodeTreeBuilder<ColumnAssignmentSetBuilder<ParentBuilder>,ParentBuilder,Update.ColumnAssignmentSet> {

        public ColumnAssignmentSetBuilder(Update.ColumnAssignmentSet set) {
            super(set);
        }

        public ColumnAssignmentSetBuilder() {
            super(new Update.ColumnAssignmentSet());
        }

        public ColumnAssignmentSetBuilder<ParentBuilder> withColumnName(ColumnName column){
            target.setColumnName(column);
            return this;
        }

        public ColumnAssignmentSetBuilder<ParentBuilder> withExpression(Expression expression){
            target.setExpression(expression);
            return this;
        }

        public ColumnAssignmentSetBuilder<ParentBuilder> withUseNull(boolean useNull){
            target.setUseNull(useNull);
            return this;
        }

        public ColumnAssignmentSetBuilder<ParentBuilder> withUseDefault(boolean useDefault){
            target.setUseDefault(useDefault);
            return this;
        }
    }

    public static class VariableAssignmentSetBuilder<ParentBuilder>
            extends CodeTreeBuilder<VariableAssignmentSetBuilder<ParentBuilder>,ParentBuilder,Update.VariableAssignmentSet> {

        public VariableAssignmentSetBuilder(Update.VariableAssignmentSet set) {
            super(set);
        }

        public VariableAssignmentSetBuilder() {
            super(new Update.VariableAssignmentSet());
        }

        public VariableAssignmentSetBuilder<ParentBuilder> withVariable(LocalVariable variable){
            target.setVariable(variable);
            return this;
        }

        public VariableAssignmentSetBuilder<ParentBuilder> withExpression(Expression expression){
            target.setExpression(expression);
            return this;
        }

    }

    public static class VariableColumnAssignmentSetBuilder<ParentBuilder>
            extends CodeTreeBuilder<VariableColumnAssignmentSetBuilder<ParentBuilder>,ParentBuilder,Update.VariableColumnAssignmentSet> {

        public VariableColumnAssignmentSetBuilder(Update.VariableColumnAssignmentSet set) {
            super(set);
        }

        public VariableColumnAssignmentSetBuilder() {
            super(new Update.VariableColumnAssignmentSet());
        }

        public VariableColumnAssignmentSetBuilder<ParentBuilder> withVariable(LocalVariable variable){
            target.setVariable(variable);
            return this;
        }

        public VariableColumnAssignmentSetBuilder<ParentBuilder> withColumnName(ColumnName column){
            target.setColumnName(column);
            return this;
        }

        public VariableColumnAssignmentSetBuilder<ParentBuilder> withExpression(Expression expression){
            target.setExpression(expression);
            return this;
        }

    }

    public static class ColumnCompoundSetBuilder<ParentBuilder>
            extends CodeTreeBuilder<ColumnCompoundSetBuilder<ParentBuilder>,ParentBuilder,Update.ColumnCompoundSet> {

        public ColumnCompoundSetBuilder(Update.ColumnCompoundSet set) {
            super(set);
        }

        public ColumnCompoundSetBuilder() {
            super(new Update.ColumnCompoundSet());
        }

        public ColumnCompoundSetBuilder<ParentBuilder> withColumnName(ColumnName column){
            target.setColumnName(column);
            return this;
        }

        public ColumnCompoundSetBuilder<ParentBuilder> withCompound(Compound compound){
            target.setCompound(compound);
            return this;
        }

        public ColumnCompoundSetBuilder<ParentBuilder> withExpression(Expression expression){
            target.setExpression(expression);
            return this;
        }

    }

    public static class VariableCompoundSetBuilder<ParentBuilder>
            extends CodeTreeBuilder<VariableCompoundSetBuilder<ParentBuilder>,ParentBuilder,Update.VariableCompoundSet> {

        public VariableCompoundSetBuilder(Update.VariableCompoundSet set) {
            super(set);
        }

        public VariableCompoundSetBuilder() {
            super(new Update.VariableColumnCompoundSet());
        }

        public VariableCompoundSetBuilder<ParentBuilder> withVariable(LocalVariable variable){
            target.setVariable(variable);
            return this;
        }

        public VariableCompoundSetBuilder<ParentBuilder> withCompound(Compound compound){
            target.setCompound(compound);
            return this;
        }

        public VariableCompoundSetBuilder<ParentBuilder> withExpression(Expression expression){
            target.setExpression(expression);
            return this;
        }

    }

    public static class VariableColumnCompoundSetBuilder<ParentBuilder>
            extends CodeTreeBuilder<VariableColumnCompoundSetBuilder<ParentBuilder>,ParentBuilder,Update.VariableColumnCompoundSet> {

        public VariableColumnCompoundSetBuilder(Update.VariableColumnCompoundSet set) {
            super(set);
        }

        public VariableColumnCompoundSetBuilder() {
            super(new Update.VariableColumnCompoundSet());
        }

        public VariableColumnCompoundSetBuilder<ParentBuilder> withVariable(LocalVariable variable){
            target.setVariable(variable);
            return this;
        }

        public VariableColumnCompoundSetBuilder<ParentBuilder> withColumnName(ColumnName columnName){
            target.setColumnName(columnName);
            return this;
        }

        public VariableColumnCompoundSetBuilder<ParentBuilder> withCompound(Compound compound){
            target.setCompound(compound);
            return this;
        }

        public VariableColumnCompoundSetBuilder<ParentBuilder> withExpression(Expression expression){
            target.setExpression(expression);
            return this;
        }

    }



}
