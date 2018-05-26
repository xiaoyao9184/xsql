package com.xy.xsql.tsql.builder.chain.statements;

import com.xy.xsql.core.builder.simple.CodeBuilder;
import com.xy.xsql.tsql.builder.chain.queries.OutputBuilder;
import com.xy.xsql.tsql.builder.chain.queries.TableValueConstructorBuilder;
import com.xy.xsql.tsql.builder.chain.queries.TopBuilder;
import com.xy.xsql.tsql.builder.chain.queries.WithBuilder;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.datatypes.table.TableName;
import com.xy.xsql.tsql.model.queries.Output;
import com.xy.xsql.tsql.model.queries.TableValueConstructor;
import com.xy.xsql.tsql.model.queries.Top;
import com.xy.xsql.tsql.model.queries.With;
import com.xy.xsql.tsql.model.queries.hints.TableHintLimited;
import com.xy.xsql.tsql.model.statements.Insert;
import com.xy.xsql.util.CheckUtil;

import java.util.List;

import static com.xy.xsql.core.handler.list.ListHandler.list;
import static com.xy.xsql.core.handler.object.GetterSetterObjectHandler.object;

/**
 * InsertBuilder
 * Created by xiaoyao9184 on 2017/1/9.
 */
@SuppressWarnings("unused")
public class InsertBuilder extends CodeBuilder<Insert> {

    public InsertBuilder(Insert target) {
        super(target);
    }

    public InsertBuilder(){
        super(new Insert());
    }

    /**
     * set
     * @param with With
     * @return THIS
     */
    public InsertBuilder withWith(With with){
        this.target.setWith(with);
        return this;
    }

    /**
     * in
     * @return WithBuilder
     */
    public WithBuilder<InsertBuilder> withWith(){
        return new WithBuilder<InsertBuilder>
                (object(target::getWith, target::setWith)
                        .init(With::new))
                .in(this);
    }

    /**
     * set
     * @param top Top
     * @return THIS
     */
    public InsertBuilder withTop(Top top){
        this.target.setTop(top);
        return this;
    }

    /**
     * in
     * @return TopBuilder
     */
    public TopBuilder<InsertBuilder> withTop(){
        return new TopBuilder<InsertBuilder>
                (object(target::getTop, target::setTop)
                        .init(Top::new))
                .in(this);
    }

    /**
     * set
     * @return THIS
     */
    public InsertBuilder withInto(){
        target.setUseInto(true);
        return this;
    }

    /**
     * set
     * @param tableName TableName
     * @return THIS
     */
    public InsertBuilder withTableName(TableName tableName){
        target.setTableName(tableName);
        return this;
    }

    /**
     * set
     * @param tableName table name
     * @return THIS
     */
    public InsertBuilder withTableName(String tableName){
        target.setTableName(new TableName(tableName));
        return this;
    }

    /**
     * set
     * @param tableHintLimiteds TableHintLimited
     * @return THIS
     */
    public InsertBuilder withTableHint(TableHintLimited... tableHintLimiteds){
        list(target::getTableHintLimitedList, target::setTableHintLimitedList)
                .addAll(tableHintLimiteds);
        return this;
    }

    /**
     * set
     * @param tableHintLimiteds TableHintLimited
     * @return THIS
     */
    public InsertBuilder withTableHint(List<TableHintLimited> tableHintLimiteds){
        list(target::getTableHintLimitedList, target::setTableHintLimitedList)
                .addAll(tableHintLimiteds);
        return this;
    }

    /**
     * set
     * @param columnNames columnName
     * @return THIS
     */
    public InsertBuilder withColumn(ColumnName... columnNames){
        if(CheckUtil.isNullOrEmpty(columnNames)){
            return this;
        }
        list(target::getColumns, target::setColumns)
                .addAll(columnNames);
        return this;
    }

    /**
     * set
     * @param columnNames columnName
     * @return THIS
     */
    public InsertBuilder withColumn(List<ColumnName> columnNames){
        if(CheckUtil.isNullOrEmpty(columnNames)){
            return this;
        }
        list(target::getColumns, target::setColumns)
                .addAll(columnNames);
        return this;
    }

    /**
     * set
     * @param output Output
     * @return THIS
     */
    public InsertBuilder withOutput(Output output) {
        this.target.setOutput(output);
        return this;
    }

    /**
     * in
     * @return OutputBuilder
     */
    public OutputBuilder<InsertBuilder> withOutput() {
        return new OutputBuilder<InsertBuilder>
                (object(target::getOutput, target::setOutput)
                        .init(Output::new))
                .in(this);
    }

    /**
     * set
     * @param values TableValueConstructor
     * @return THIS
     */
    public InsertBuilder withValues(TableValueConstructor values){
        this.target.setValues(values);
        return this;
    }

    /**
     * in
     * @return TableValueConstructorBuilder
     */
    public TableValueConstructorBuilder<InsertBuilder> withValues(){
        return new TableValueConstructorBuilder<InsertBuilder>
                (object(target::getValues, target::setValues)
                        .init(TableValueConstructor::new))
                .in(this);
    }

    /**
     * set
     * @return THIS
     */
    public InsertBuilder withDefaultValues() {
        target.setUseDefaultValues(true);
        return this;
    }




    /*
    Quick
     */

    /**
     * set
     * @param with With
     * @return THIS
     */
    public InsertBuilder $With(With with){
        target.setWith(with);
        return this;
    }

    /**
     * in
     * @return WithBuilder
     */
    public WithBuilder<InsertBuilder> $With() {
        return withWith();
    }

    /**
     * in
     * @return TopBuilder
     */
    public TopBuilder<InsertBuilder> $Top() {
        return withTop();
    }

    /**
     * set
     * @return THIS
     */
    public InsertBuilder $Into() {
        return withInto();
    }

    /**
     * set
     * @param tableName TableName
     * @return THIS
     */
    public InsertBuilder $(TableName tableName) {
        return withTableName(tableName);
    }

    /**
     * set
     * @param columnNames columnName
     * @return THIS
     */
    public InsertBuilder $(ColumnName... columnNames) {
        return withColumn(columnNames);
    }

    /**
     * set
     * @param tableHintLimiteds TableHintLimited
     * @return THIS
     */
    public InsertBuilder $With(TableHintLimited... tableHintLimiteds) {
        return withTableHint(tableHintLimiteds);
    }

    /**
     * in
     * @return OutputBuilder
     */
    public OutputBuilder<InsertBuilder> $Output() {
        return withOutput();
    }

    /**
     * in
     * @return TableValueConstructorBuilder
     */
    public TableValueConstructorBuilder<InsertBuilder> $Values() {
        return withValues();
    }

    /**
     * set
     * @return THIS
     */
    public InsertBuilder $DefaultValues() {
        return withDefaultValues();
    }

}
