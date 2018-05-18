package com.xy.xsql.tsql.core.statement.dml;

import com.xy.xsql.core.builder.CodeBuilder;
import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.core.lambda.Setter;
import com.xy.xsql.tsql.core.clause.*;
import com.xy.xsql.tsql.model.clause.*;
import com.xy.xsql.tsql.model.clause.hints.TableHintLimited;
import com.xy.xsql.tsql.model.element.Alias;
import com.xy.xsql.tsql.model.element.ColumnName;
import com.xy.xsql.tsql.model.element.TableName;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.operator.Compound;
import com.xy.xsql.tsql.model.statement.dml.Update;
import com.xy.xsql.tsql.model.variable.LocalVariable;
import com.xy.xsql.util.CheckUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.xy.xsql.core.FiledBuilder.initSet;
import static com.xy.xsql.core.ListBuilder.initAdd;
import static com.xy.xsql.core.ListBuilder.initList;

/**
 * UpdateBuilder
 * Created by xiaoyao9184 on 2017/1/7.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class UpdateBuilder extends CodeBuilder<Update> {

    public UpdateBuilder(Update tar) {
        super(tar);
    }

    public UpdateBuilder(){
        super(new Update());
    }


    /**
     * in
     * @return WithBuilder
     */
    public WithBuilder<UpdateBuilder> withWith(){
        return new WithBuilder<UpdateBuilder>
                (initSet(With::new,
                        target::getWith,
                        target::setWith))
                .in(this);
    }

    public UpdateBuilder withWith(With with){
        this.target.setWith(with);
        return this;
    }

    /**
     * in
     * @return TopBuilder
     */
    public TopBuilder<UpdateBuilder> withTop(){
        return new TopBuilder<UpdateBuilder>
                (initSet(Top::new,
                        target::getTop,
                        target::setTop))
                .in(this);
    }

    public UpdateBuilder withTop(Top top){
        this.target.setTop(top);
        return this;
    }

    /**
     * set
     * @param tableAlias TableAlias
     * @return THIS
     */
    public UpdateBuilder withTableAlias(String tableAlias){
        target.setTableAlias(new Alias<>(tableAlias));
        return this;
    }

    /**
     * set
     * @param tableName TableName
     * @return THIS
     */
    public UpdateBuilder withTableName(TableName tableName){
        target.setTableName(tableName);
        return this;
    }

    /**
     * set
     * @param tableName TableName
     * @return THIS
     */
    public UpdateBuilder withTableName(String tableName){
        target.setTableName(new TableName(tableName));
        return this;
    }

    /**
     * set
     * @param tableHintLimiteds TableHintLimited
     * @return THIS
     */
    public UpdateBuilder withTableHint(TableHintLimited... tableHintLimiteds){
        initAdd(Arrays.asList(tableHintLimiteds),
                target::getTableHintLimitedList,
                target::setTableHintLimitedList);
        return this;
    }

    public UpdateBuilder withTableHint(List<TableHintLimited> tableHintLimiteds){
        initAdd(tableHintLimiteds,
                target::getTableHintLimitedList,
                target::setTableHintLimitedList);
        return this;
    }

    /**
     * in
     * @return SetItemBuilder
     */
    public SetItemBuilder<UpdateBuilder> withSetItem(){
        initList(target::getSets,
                target::setSets);
        return new SetItemBuilder<UpdateBuilder>
                (target.getSets()::add)
                .in(this);
    }

    public UpdateBuilder withSetItem(List<Update.SetItem> setItems){
        initAdd(setItems,
                target::getSets,
                target::setSets);
        return this;
    }

    /**
     * in
     * @return OutputBuilder
     */
    public OutputBuilder<UpdateBuilder> withOutput() {
        return new OutputBuilder<UpdateBuilder>
                (initSet(Output::new,
                        target::getOutput,
                        target::setOutput))
                .in(this);
    }

    public UpdateBuilder withOutput(Output output) {
        this.target.setOutput(output);
        return this;
    }

    /**
     * in
     * @return FromBuilder
     */
    public FromBuilder<UpdateBuilder> withFrom() {
        return new FromBuilder<UpdateBuilder>
                (initSet(From::new,
                        target::getFrom,
                        target::setFrom))
                .in(this);
    }

    public UpdateBuilder withFrom(From from) {
        this.target.setFrom(from);
        return this;
    }

    /**
     * in
     * @return WhereBuilder
     */
    public WhereBuilder<UpdateBuilder> withWhere() {
        return new WhereBuilder<UpdateBuilder>
                (initSet(Where::new,
                        target::getWhere,
                        target::setWhere))
                .in(this);
    }

    public UpdateBuilder withWhere(Where where) {
        this.target.setWhere(where);
        return this;
    }

    /**
     * in
     * @return OptionBuilder
     */
    public OptionBuilder<UpdateBuilder> withOption() {
        return new OptionBuilder<UpdateBuilder>
                (initSet(Option::new,
                        target::getOption,
                        target::setOption))
                .in(this);
    }

    public UpdateBuilder withOption(Option option) {
        this.target.setOption(option);
        return this;
    }



    /*
    Quick
     */

    /**
     * Quick
     * @return UpdateBuilder
     */
    public static UpdateBuilder UPDATE(){
        return new UpdateBuilder();
    }

    /**
     * Quick set
     * @param with With
     * @return THIS
     */
    public UpdateBuilder $With(With with){
        target.setWith(with);
        return this;
    }

    /**
     * Quick in
     * @return WithBuilder
     */
    public WithBuilder<UpdateBuilder> $With() {
        return withWith();
    }

    /**
     * Quick in
     * @return TopBuilder
     */
    public TopBuilder<UpdateBuilder> $Top(){
        return withTop();
    }

    /**
     * Quick set
     * @param tableAlias TableAlias
     * @return THIS
     */
    public UpdateBuilder $(String tableAlias){
        return withTableAlias(tableAlias);
    }

    /**
     * Quick set
     * @param tableName TableName
     * @return THIS
     */
    public UpdateBuilder $(TableName tableName){
        return withTableName(tableName);
    }

    /**
     * Quick set
     * @param tableHintLimiteds TableHintLimited
     * @return THIS
     */
    public UpdateBuilder $With(TableHintLimited... tableHintLimiteds){
        return withTableHint(tableHintLimiteds);
    }

    /**
     * Quick in
     * @return SetItemBuilder
     */
    public SetItemBuilder<UpdateBuilder> $Set(){
        return withSetItem();
    }

    /**
     * Quick set
     * @param items SetItem
     * @return THIS
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
     * Quick in
     * @return OutputBuilder
     */
    public OutputBuilder<UpdateBuilder> $Output(){
        return withOutput();
    }

    /**
     * Quick in
     * @return FromBuilder
     */
    public FromBuilder<UpdateBuilder> $From(){
        return withFrom();
    }

    /**
     * Quick in
     * @return WhereBuilder
     */
    public WhereBuilder<UpdateBuilder> $Where(){
        return withWhere();
    }

    /**
     * Quick in
     * @return OptionBuilder
     */
    public OptionBuilder<UpdateBuilder> $Option(){
        return withOption();
    }



    public static class SetListBuilder<ParentBuilder>
            extends CodeTreeBuilder<SetListBuilder<ParentBuilder>,ParentBuilder,List<Update.SetItem>> {

        public SetListBuilder() {
            super(new ArrayList<>());
        }

        public SetItemBuilder<SetListBuilder<ParentBuilder>> withItem(){
            return new SetItemBuilder<SetListBuilder<ParentBuilder>>
                    (item -> this.target.add(item))
                    .in(this);
        }
    }

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


        /**
         * select in
         * @return ColumnAssignmentSetBuilder
         */
        public ColumnAssignmentSetBuilder<ParentBuilder> _ColumnAssignment(){
            Update.ColumnAssignmentSet setItem = new Update.ColumnAssignmentSet();
            target.set(setItem);
            return new ColumnAssignmentSetBuilder<ParentBuilder>
                    (setItem)
                    .in(out());
        }

        /**
         * select in
         * @return VariableAssignmentSetBuilder
         */
        public VariableAssignmentSetBuilder<ParentBuilder> _VariableAssignment(){
            Update.VariableAssignmentSet setItem = new Update.VariableAssignmentSet();
            target.set(setItem);
            return new VariableAssignmentSetBuilder<ParentBuilder>
                    (setItem)
                    .in(out());
        }

        /**
         * select in
         * @return VariableColumnAssignmentSetBuilder
         */
        public VariableColumnAssignmentSetBuilder<ParentBuilder> _VariableColumnAssignment(){
            Update.VariableColumnAssignmentSet setItem = new Update.VariableColumnAssignmentSet();
            target.set(setItem);
            return new VariableColumnAssignmentSetBuilder<ParentBuilder>
                    (setItem)
                    .in(out());
        }

        /**
         * select in
         * @return ColumnCompoundSetBuilder
         */
        public ColumnCompoundSetBuilder<ParentBuilder> _ColumnCompound(){
            Update.ColumnCompoundSet setItem = new Update.ColumnCompoundSet();
            target.set(setItem);
            return new ColumnCompoundSetBuilder<ParentBuilder>
                    (setItem)
                    .in(out());
        }

        /**
         * select in
         * @return VariableCompoundSetBuilder
         */
        public VariableCompoundSetBuilder<ParentBuilder> _VariableCompound(){
            Update.VariableCompoundSet setItem = new Update.VariableCompoundSet();
            target.set(setItem);
            return new VariableCompoundSetBuilder<ParentBuilder>
                    (setItem)
                    .in(out());
        }

        /**
         * select in
         * @return VariableColumnCompoundSetBuilder
         */
        public VariableColumnCompoundSetBuilder<ParentBuilder> _VariableColumnCompound(){
            Update.VariableColumnCompoundSet setItem = new Update.VariableColumnCompoundSet();
            target.set(setItem);
            return new VariableColumnCompoundSetBuilder<ParentBuilder>
                    (setItem)
                    .in(out());
        }


        /**
         * Quick build
         * @param columnName ColumnName
         * @return ColumnAssignment SetItem
         */
        public static Update.SetItem s_default(ColumnName columnName){
            return new ColumnAssignmentSetBuilder<Void>()
                    .withColumnName(columnName)
                    .withUseDefault(true)
                    .build();
        }

        /**
         * Quick build
         * @param columnName ColumnName
         * @return ColumnAssignment SetItem
         */
        public static Update.SetItem s_null(ColumnName columnName){
            return new ColumnAssignmentSetBuilder<Void>()
                    .withColumnName(columnName)
                    .withUseNull(true)
                    .build();
        }

        /**
         * Quick build
         * @param columnName ColumnName
         * @param expression Expression
         * @return ColumnAssignment SetItem
         */
        public static Update.SetItem s(ColumnName columnName, Expression expression){
            return new ColumnAssignmentSetBuilder<Void>()
                    .withColumnName(columnName)
                    .withExpression(expression)
                    .build();
        }

        /**
         * Quick build
         * @param variable LocalVariable
         * @param expression Expression
         * @return Variable Assignment SetItem
         */
        public static Update.SetItem s(LocalVariable variable, Expression expression){
            return new VariableAssignmentSetBuilder<Void>()
                    .withVariable(variable)
                    .withExpression(expression)
                    .build();
        }

        /**
         * Quick build
         * @param variable LocalVariable
         * @param columnName ColumnName
         * @param expression Expression
         * @return VariableColumnAssignment SetItem
         */
        public static Update.SetItem s(LocalVariable variable, ColumnName columnName, Expression expression){
            return new VariableColumnAssignmentSetBuilder<Void>()
                    .withVariable(variable)
                    .withColumnName(columnName)
                    .withExpression(expression)
                    .build();
        }

        /**
         * Quick build
         * @param columnName ColumnName
         * @param compound Compound
         * @param expression Expression
         * @return ColumnCompound SetItem
         */
        public static Update.SetItem s(ColumnName columnName, Compound compound, Expression expression){
            return new ColumnCompoundSetBuilder<Void>()
                    .withColumnName(columnName)
                    .withCompound(compound)
                    .withExpression(expression)
                    .build();
        }

        /**
         * Quick build
         * @param variable LocalVariable
         * @param compound Compound
         * @param expression Expression
         * @return VariableCompound SetItem
         */
        public static Update.SetItem s(LocalVariable variable, Compound compound, Expression expression){
            return new VariableCompoundSetBuilder<Void>()
                    .withVariable(variable)
                    .withCompound(compound)
                    .withExpression(expression)
                    .build();
        }

        /**
         * Quick build
         * @param variable LocalVariable
         * @param columnName ColumnName
         * @param compound Compound
         * @param expression Expression
         * @return VariableColumnCompound SetItem
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


    /**
     * ColumnAssignmentSetBuilder
     * @param <ParentBuilder>
     */
    @SuppressWarnings({"SameParameterValue", "DanglingJavadoc"})
    public static class ColumnAssignmentSetBuilder<ParentBuilder>
            extends CodeTreeBuilder<ColumnAssignmentSetBuilder<ParentBuilder>,ParentBuilder,Update.ColumnAssignmentSet> {

        public ColumnAssignmentSetBuilder(Update.ColumnAssignmentSet set) {
            super(set);
        }

        public ColumnAssignmentSetBuilder() {
            super(new Update.ColumnAssignmentSet());
        }

        /**
         * set
         * @param column ColumnName
         * @return THIS
         */
        public ColumnAssignmentSetBuilder<ParentBuilder> withColumnName(ColumnName column){
            target.setColumnName(column);
            return this;
        }

        /**
         * set
         * @param expression Expression
         * @return THIS
         */
        public ColumnAssignmentSetBuilder<ParentBuilder> withExpression(Expression expression){
            target.setExpression(expression);
            return this;
        }

        /**
         * set
         * @param useNull UseNull
         * @return THIS
         */
        public ColumnAssignmentSetBuilder<ParentBuilder> withUseNull(boolean useNull){
            target.setUseNull(useNull);
            return this;
        }

        /**
         * set
         * @param useDefault UseDefault
         * @return THIS
         */
        public ColumnAssignmentSetBuilder<ParentBuilder> withUseDefault(boolean useDefault){
            target.setUseDefault(useDefault);
            return this;
        }

        /**
         * Quick
         * All in {@link SetItemBuilder}
         */
    }

    /**
     * VariableAssignmentSetBuilder
     * @param <ParentBuilder>
     */
    @SuppressWarnings({"SameParameterValue", "DanglingJavadoc"})
    public static class VariableAssignmentSetBuilder<ParentBuilder>
            extends CodeTreeBuilder<VariableAssignmentSetBuilder<ParentBuilder>,ParentBuilder,Update.VariableAssignmentSet> {

        public VariableAssignmentSetBuilder(Update.VariableAssignmentSet set) {
            super(set);
        }

        public VariableAssignmentSetBuilder() {
            super(new Update.VariableAssignmentSet());
        }

        /**
         * set
         * @param variable LocalVariable
         * @return THIS
         */
        public VariableAssignmentSetBuilder<ParentBuilder> withVariable(LocalVariable variable){
            target.setVariable(variable);
            return this;
        }

        /**
         * set
         * @param expression Expression
         * @return THIS
         */
        public VariableAssignmentSetBuilder<ParentBuilder> withExpression(Expression expression){
            target.setExpression(expression);
            return this;
        }

        /**
         * Quick
         * All in {@link SetItemBuilder}
         */

    }

    /**
     * VariableColumnAssignmentSetBuilder
     * @param <ParentBuilder>
     */
    @SuppressWarnings({"SameParameterValue", "DanglingJavadoc"})
    public static class VariableColumnAssignmentSetBuilder<ParentBuilder>
            extends CodeTreeBuilder<VariableColumnAssignmentSetBuilder<ParentBuilder>,ParentBuilder,Update.VariableColumnAssignmentSet> {

        public VariableColumnAssignmentSetBuilder(Update.VariableColumnAssignmentSet set) {
            super(set);
        }

        public VariableColumnAssignmentSetBuilder() {
            super(new Update.VariableColumnAssignmentSet());
        }

        /**
         * set
         * @param variable LocalVariable
         * @return THIS
         */
        public VariableColumnAssignmentSetBuilder<ParentBuilder> withVariable(LocalVariable variable){
            target.setVariable(variable);
            return this;
        }

        /**
         * set
         * @param column ColumnName
         * @return THIS
         */
        public VariableColumnAssignmentSetBuilder<ParentBuilder> withColumnName(ColumnName column){
            target.setColumnName(column);
            return this;
        }

        /**
         * set
         * @param expression Expression
         * @return THIS
         */
        public VariableColumnAssignmentSetBuilder<ParentBuilder> withExpression(Expression expression){
            target.setExpression(expression);
            return this;
        }

        /**
         * Quick
         * All in {@link SetItemBuilder}
         */

    }

    /**
     * ColumnCompoundSetBuilder
     * @param <ParentBuilder>
     */
    @SuppressWarnings({"SameParameterValue", "DanglingJavadoc"})
    public static class ColumnCompoundSetBuilder<ParentBuilder>
            extends CodeTreeBuilder<ColumnCompoundSetBuilder<ParentBuilder>,ParentBuilder,Update.ColumnCompoundSet> {

        public ColumnCompoundSetBuilder(Update.ColumnCompoundSet set) {
            super(set);
        }

        public ColumnCompoundSetBuilder() {
            super(new Update.ColumnCompoundSet());
        }

        /**
         * set
         * @param column ColumnName
         * @return THIS
         */
        public ColumnCompoundSetBuilder<ParentBuilder> withColumnName(ColumnName column){
            target.setColumnName(column);
            return this;
        }

        /**
         * set
         * @param compound Compound
         * @return THIS
         */
        public ColumnCompoundSetBuilder<ParentBuilder> withCompound(Compound compound){
            target.setCompound(compound);
            return this;
        }

        /**
         * set
         * @param expression Expression
         * @return THIS
         */
        public ColumnCompoundSetBuilder<ParentBuilder> withExpression(Expression expression){
            target.setExpression(expression);
            return this;
        }

        /**
         * Quick
         * All in {@link SetItemBuilder}
         */

    }

    /**
     * VariableCompoundSetBuilder
     * @param <ParentBuilder>
     */
    @SuppressWarnings({"SameParameterValue", "DanglingJavadoc"})
    public static class VariableCompoundSetBuilder<ParentBuilder>
            extends CodeTreeBuilder<VariableCompoundSetBuilder<ParentBuilder>,ParentBuilder,Update.VariableCompoundSet> {

        public VariableCompoundSetBuilder(Update.VariableCompoundSet set) {
            super(set);
        }

        public VariableCompoundSetBuilder() {
            super(new Update.VariableColumnCompoundSet());
        }

        /**
         * set
         * @param variable LocalVariable
         * @return THIS
         */
        public VariableCompoundSetBuilder<ParentBuilder> withVariable(LocalVariable variable){
            target.setVariable(variable);
            return this;
        }

        /**
         * set
         * @param compound Compound
         * @return THIS
         */
        public VariableCompoundSetBuilder<ParentBuilder> withCompound(Compound compound){
            target.setCompound(compound);
            return this;
        }

        /**
         * set
         * @param expression Expression
         * @return THIS
         */
        public VariableCompoundSetBuilder<ParentBuilder> withExpression(Expression expression){
            target.setExpression(expression);
            return this;
        }

        /**
         * Quick
         * All in {@link SetItemBuilder}
         */

    }

    /**
     * VariableColumnCompoundSetBuilder
     * @param <ParentBuilder>
     */
    @SuppressWarnings({"SameParameterValue", "DanglingJavadoc"})
    public static class VariableColumnCompoundSetBuilder<ParentBuilder>
            extends CodeTreeBuilder<VariableColumnCompoundSetBuilder<ParentBuilder>,ParentBuilder,Update.VariableColumnCompoundSet> {

        public VariableColumnCompoundSetBuilder(Update.VariableColumnCompoundSet set) {
            super(set);
        }

        public VariableColumnCompoundSetBuilder() {
            super(new Update.VariableColumnCompoundSet());
        }

        /**
         * set
         * @param variable LocalVariable
         * @return THIS
         */
        public VariableColumnCompoundSetBuilder<ParentBuilder> withVariable(LocalVariable variable){
            target.setVariable(variable);
            return this;
        }

        /**
         * set
         * @param columnName ColumnName
         * @return THIS
         */
        public VariableColumnCompoundSetBuilder<ParentBuilder> withColumnName(ColumnName columnName){
            target.setColumnName(columnName);
            return this;
        }

        /**
         * set
         * @param compound Compound
         * @return THIS
         */
        public VariableColumnCompoundSetBuilder<ParentBuilder> withCompound(Compound compound){
            target.setCompound(compound);
            return this;
        }

        /**
         * set
         * @param expression Expression
         * @return THIS
         */
        public VariableColumnCompoundSetBuilder<ParentBuilder> withExpression(Expression expression){
            target.setExpression(expression);
            return this;
        }

        /**
         * Quick
         * All in {@link SetItemBuilder}
         */

    }



}
