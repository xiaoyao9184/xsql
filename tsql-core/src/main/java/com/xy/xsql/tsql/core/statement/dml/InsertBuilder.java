package com.xy.xsql.tsql.core.statement.dml;

import com.xy.xsql.core.builder.CodeBuilder;
import com.xy.xsql.tsql.core.clause.OutputBuilder;
import com.xy.xsql.tsql.core.clause.TableValueConstructorBuilder;
import com.xy.xsql.tsql.core.clause.TopBuilder;
import com.xy.xsql.tsql.core.clause.WithBuilder;
import com.xy.xsql.tsql.model.clause.Output;
import com.xy.xsql.tsql.model.clause.TableValueConstructor;
import com.xy.xsql.tsql.model.clause.Top;
import com.xy.xsql.tsql.model.clause.With;
import com.xy.xsql.tsql.model.clause.hints.TableHintLimited;
import com.xy.xsql.tsql.model.element.ColumnName;
import com.xy.xsql.tsql.model.element.TableName;
import com.xy.xsql.tsql.model.statement.dml.Insert;
import com.xy.xsql.util.CheckUtil;

import java.util.Arrays;
import java.util.List;

import static com.xy.xsql.core.FiledBuilder.initSet;
import static com.xy.xsql.core.ListBuilder.initAdd;

/**
 * Created by xiaoyao9184 on 2017/1/9.
 */
public class InsertBuilder extends CodeBuilder<Insert> {

    public InsertBuilder(Insert tar) {
        super(tar);
    }

    public InsertBuilder(){
        super(new Insert());
    }


    public WithBuilder<InsertBuilder> withWith(){
        return new WithBuilder<InsertBuilder>
                (initSet(With::new,
                        target::getWith,
                        target::setWith))
                .in(this);
    }

    public InsertBuilder withWith(With with){
        this.target.setWith(with);
        return this;
    }

    public TopBuilder<InsertBuilder> withTop(){
        return new TopBuilder<InsertBuilder>
                (initSet(Top::new,
                        target::getTop,
                        target::setTop))
                .in(this);
    }

    public InsertBuilder withTop(Top top){
        this.target.setTop(top);
        return this;
    }

    public InsertBuilder withInto(){
        target.setUseInto(true);
        return this;
    }

    public InsertBuilder withTableName(TableName tableName){
        target.setTableName(tableName);
        return this;
    }

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
        initAdd(Arrays.asList(tableHintLimiteds),
                target::getTableHintLimitedList,
                target::setTableHintLimitedList);
        return this;
    }

    public InsertBuilder withTableHint(List<TableHintLimited> tableHintLimiteds){
        initAdd(tableHintLimiteds,
                target::getTableHintLimitedList,
                target::setTableHintLimitedList);
        return this;
    }

    public InsertBuilder withColumn(ColumnName... columnNames){
        if(CheckUtil.isNullOrEmpty(columnNames)){
            return this;
        }
        initAdd(Arrays.asList(columnNames),
                target::getColumns,
                target::setColumns);
        return this;
    }

    public InsertBuilder withColumn(List<ColumnName> columnNames){
        if(CheckUtil.isNullOrEmpty(columnNames)){
            return this;
        }
        initAdd(columnNames,
                target::getColumns,
                target::setColumns);
        return this;
    }

    public OutputBuilder<InsertBuilder> withOutput() {
        return new OutputBuilder<InsertBuilder>
                (initSet(Output::new,
                        target::getOutput,
                        target::setOutput))
                .in(this);
    }

    public InsertBuilder withOutput(Output output) {
        this.target.setOutput(output);
        return this;
    }

    public TableValueConstructorBuilder<InsertBuilder> withValues(){
        return new TableValueConstructorBuilder<InsertBuilder>
                (initSet(TableValueConstructor::new,
                        target::getValues,
                        target::setValues))
                .in(this);
    }

    public InsertBuilder withValues(TableValueConstructor values){
        this.target.setValues(values);
        return this;
    }

    public InsertBuilder withDefaultValues() {
        target.setUseDefaultValues(true);
        return this;
    }



    /*
    Quick
     */

    public static InsertBuilder INSERT(){
        return new InsertBuilder();
    }

    public InsertBuilder $With(With with){
        target.setWith(with);
        return this;
    }

    public WithBuilder<InsertBuilder> $With() {
        return withWith();
    }

    public TopBuilder<InsertBuilder> $Top() {
        return withTop();
    }

    public InsertBuilder $Into() {
        return withInto();
    }

    public InsertBuilder $(TableName tableName) {
        return withTableName(tableName);
    }

    public InsertBuilder $(ColumnName... columnNames) {
        return withColumn(columnNames);
    }

    public InsertBuilder $With(TableHintLimited... tableHintLimiteds) {
        return withTableHint(tableHintLimiteds);
    }

    public OutputBuilder<InsertBuilder> $Output() {
        return withOutput();
    }

    public TableValueConstructorBuilder<InsertBuilder> $Values() {
        return withValues();
    }

    public InsertBuilder $Default_Values() {
        return withDefaultValues();
    }

}
