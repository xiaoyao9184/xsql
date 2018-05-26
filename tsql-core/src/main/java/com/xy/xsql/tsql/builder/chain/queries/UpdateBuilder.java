package com.xy.xsql.tsql.builder.chain.queries;

import com.xy.xsql.core.builder.parent.ParentHoldBuilder;
import com.xy.xsql.core.builder.parent.ParentHoldLazyConfigBuilder;
import com.xy.xsql.core.builder.simple.CodeBuilder;
import com.xy.xsql.core.lambda.Getter;
import com.xy.xsql.tsql.model.datatypes.table.Alias;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.datatypes.table.TableName;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.elements.operators.Compound;
import com.xy.xsql.tsql.model.elements.variables.LocalVariable;
import com.xy.xsql.tsql.model.queries.*;
import com.xy.xsql.tsql.model.queries.hints.TableHintLimited;
import com.xy.xsql.util.CheckUtil;

import java.util.ArrayList;
import java.util.List;

import static com.xy.xsql.core.handler.list.ListHandler.list;
import static com.xy.xsql.core.handler.object.GetterSetterObjectHandler.object;
import static com.xy.xsql.core.handler.object.SupplierObjectHandler.object;

/**
 * UpdateBuilder
 * Created by xiaoyao9184 on 2017/1/7.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class UpdateBuilder extends CodeBuilder<Update> {

    public UpdateBuilder(Update target) {
        super(target);
    }

    public UpdateBuilder(){
        super(new Update());
    }


    /**
     * set
     * @param with With
     * @return THIS
     */
    public UpdateBuilder withWith(With with){
        this.target.setWith(with);
        return this;
    }

    /**
     * in
     * @return WithBuilder
     */
    public WithBuilder<UpdateBuilder> withWith(){
        return new WithBuilder<UpdateBuilder>
                (object(target::getWith, target::setWith)
                        .init(With::new))
                .in(this);
    }

    /**
     * set
     * @param top Top
     * @return THIS
     */
    public UpdateBuilder withTop(Top top){
        this.target.setTop(top);
        return this;
    }

    /**
     * in
     * @return TopBuilder
     */
    public TopBuilder<UpdateBuilder> withTop(){
        return new TopBuilder<UpdateBuilder>
                (object(target::getTop, target::setTop)
                        .init(Top::new))
                .in(this);
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
        list(target::getTableHintLimitedList, target::setTableHintLimitedList)
                .addAll(tableHintLimiteds);
        return this;
    }

    /**
     * set
     * @param tableHintLimiteds TableHintLimited
     * @return THIS
     */
    public UpdateBuilder withTableHint(List<TableHintLimited> tableHintLimiteds){
        list(target::getTableHintLimitedList, target::setTableHintLimitedList)
                .addAll(tableHintLimiteds);
        return this;
    }

    /**
     * set
     * @param setItems SetItem
     * @return THIS
     */
    public UpdateBuilder withSetItem(List<Update.SetItem> setItems){
        list(target::getSets, target::setSets)
                .addAll(setItems);
        return this;
    }

    /**
     * in
     * @return SetItemBuilder
     */
    public SetItemBuilder<UpdateBuilder> withSetItem(){
        list(target::getSets, target::setSets).init();
        return new SetItemBuilder<UpdateBuilder>()
                .enter(this, Getter.empty(), target.getSets()::add);
    }

    /**
     * set
     * @param output Output
     * @return THIS
     */
    public UpdateBuilder withOutput(Output output) {
        this.target.setOutput(output);
        return this;
    }

    /**
     * in
     * @return OutputBuilder
     */
    public OutputBuilder<UpdateBuilder> withOutput() {
        return new OutputBuilder<UpdateBuilder>
                (object(target::getOutput, target::setOutput)
                        .init(Output::new))
                .in(this);
    }

    /**
     * set
     * @param from From
     * @return THIS
     */
    public UpdateBuilder withFrom(From from) {
        this.target.setFrom(from);
        return this;
    }

    /**
     * in
     * @return FromBuilder
     */
    public FromBuilder<UpdateBuilder> withFrom() {
        return new FromBuilder<UpdateBuilder>
                (object(target::getFrom, target::setFrom)
                        .init(From::new))
                .in(this);
    }

    /**
     * set
     * @param where Where
     * @return THIS
     */
    public UpdateBuilder withWhere(Where where) {
        this.target.setWhere(where);
        return this;
    }

    /**
     * in
     * @return WhereBuilder
     */
    public WhereBuilder<UpdateBuilder> withWhere() {
        return new WhereBuilder<UpdateBuilder>
                (object(target::getWhere, target::setWhere)
                        .init(Where::new))
                .in(this);
    }

    /**
     * set
     * @param option Option
     * @return THIS
     */
    public UpdateBuilder withOption(Option option) {
        this.target.setOption(option);
        return this;
    }

    /**
     * in
     * @return OptionBuilder
     */
    public OptionBuilder<UpdateBuilder> withOption() {
        return new OptionBuilder<UpdateBuilder>
                (object(target::getOption, target::setOption)
                        .init(Option::new))
                .in(this);
    }




    /*
    Quick
     */

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
        list(target::getSets, target::setSets)
                .addAll(items);
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


    /**
     * SetListBuilder
     * @param <ParentBuilder>
     */
    public static class SetListBuilder<ParentBuilder>
            extends ParentHoldBuilder<SetListBuilder<ParentBuilder>,ParentBuilder,List<Update.SetItem>> {

        public SetListBuilder() {
            super(new ArrayList<>());
        }

        public SetListBuilder(List<Update.SetItem> target) {
            super(target);
        }

        /**
         * in
         * @return SetItemBuilder
         */
        public SetItemBuilder<SetListBuilder<ParentBuilder>> withItem(){
            return new SetItemBuilder<SetListBuilder<ParentBuilder>>()
                    .enter(this, Getter.empty(), target::add);
        }
    }

    /**
     * Abstract SetItem Builder
     * @param <ParentBuilder>
     */
    public static class SetItemBuilder<ParentBuilder>
            extends ParentHoldLazyConfigBuilder<SetItemBuilder<ParentBuilder>,ParentBuilder,Update.SetItem> {

        public SetItemBuilder() {}

        /**
         * select in
         * @return ColumnAssignmentSetBuilder
         */
        public ColumnAssignmentSetBuilder<ParentBuilder> _ColumnAssignment(){
            return new ColumnAssignmentSetBuilder<ParentBuilder>
                    (object(Update.ColumnAssignmentSet::new).set(this::init))
                    .in(this.and());
        }

        /**
         * select in
         * @return VariableAssignmentSetBuilder
         */
        public VariableAssignmentSetBuilder<ParentBuilder> _VariableAssignment(){
            return new VariableAssignmentSetBuilder<ParentBuilder>
                    (object(Update.VariableAssignmentSet::new).set(this::init))
                    .in(this.and());
        }

        /**
         * select in
         * @return VariableColumnAssignmentSetBuilder
         */
        public VariableColumnAssignmentSetBuilder<ParentBuilder> _VariableColumnAssignment(){
            return new VariableColumnAssignmentSetBuilder<ParentBuilder>
                    (object(Update.VariableColumnAssignmentSet::new).set(this::init))
                    .in(this.and());
        }

        /**
         * select in
         * @return ColumnCompoundSetBuilder
         */
        public ColumnCompoundSetBuilder<ParentBuilder> _ColumnCompound(){
            return new ColumnCompoundSetBuilder<ParentBuilder>
                    (object(Update.ColumnCompoundSet::new).set(this::init))
                    .in(this.and());
        }

        /**
         * select in
         * @return VariableCompoundSetBuilder
         */
        public VariableCompoundSetBuilder<ParentBuilder> _VariableCompound(){
            return new VariableCompoundSetBuilder<ParentBuilder>
                    (object(Update.VariableCompoundSet::new).set(this::init))
                    .in(this.and());
        }

        /**
         * select in
         * @return VariableColumnCompoundSetBuilder
         */
        public VariableColumnCompoundSetBuilder<ParentBuilder> _VariableColumnCompound(){
            return new VariableColumnCompoundSetBuilder<ParentBuilder>
                    (object(Update.VariableColumnCompoundSet::new).set(this::init))
                    .in(this.and());
        }




        /*
        Quick
         */

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
            extends ParentHoldBuilder<ColumnAssignmentSetBuilder<ParentBuilder>,ParentBuilder,Update.ColumnAssignmentSet> {

        public ColumnAssignmentSetBuilder(Update.ColumnAssignmentSet target) {
            super(target);
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
            extends ParentHoldBuilder<VariableAssignmentSetBuilder<ParentBuilder>,ParentBuilder,Update.VariableAssignmentSet> {

        public VariableAssignmentSetBuilder(Update.VariableAssignmentSet target) {
            super(target);
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
            extends ParentHoldBuilder<VariableColumnAssignmentSetBuilder<ParentBuilder>,ParentBuilder,Update.VariableColumnAssignmentSet> {

        public VariableColumnAssignmentSetBuilder(Update.VariableColumnAssignmentSet target) {
            super(target);
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
            extends ParentHoldBuilder<ColumnCompoundSetBuilder<ParentBuilder>,ParentBuilder,Update.ColumnCompoundSet> {

        public ColumnCompoundSetBuilder(Update.ColumnCompoundSet target) {
            super(target);
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
            extends ParentHoldBuilder<VariableCompoundSetBuilder<ParentBuilder>,ParentBuilder,Update.VariableCompoundSet> {

        public VariableCompoundSetBuilder(Update.VariableCompoundSet target) {
            super(target);
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
            extends ParentHoldBuilder<VariableColumnCompoundSetBuilder<ParentBuilder>,ParentBuilder,Update.VariableColumnCompoundSet> {

        public VariableColumnCompoundSetBuilder(Update.VariableColumnCompoundSet target) {
            super(target);
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
