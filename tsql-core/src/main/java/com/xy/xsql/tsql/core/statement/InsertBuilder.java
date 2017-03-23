package com.xy.xsql.tsql.core.statement;

import com.xy.xsql.core.builder.CodeBuilder;
import com.xy.xsql.tsql.core.clause.OutputBuilder;
import com.xy.xsql.tsql.core.clause.TableValueConstructorBuilder;
import com.xy.xsql.tsql.core.clause.TopBuilder;
import com.xy.xsql.tsql.core.clause.WithBuilder;
import com.xy.xsql.tsql.model.clause.Output;
import com.xy.xsql.tsql.model.clause.TableValueConstructor;
import com.xy.xsql.tsql.model.clause.Top;
import com.xy.xsql.tsql.model.clause.With;
import com.xy.xsql.tsql.model.element.ColumnName;
import com.xy.xsql.tsql.model.element.TableName;
import com.xy.xsql.tsql.model.statement.dml.Insert;

import java.util.Arrays;

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

    public TopBuilder<InsertBuilder> withTop(){
        return new TopBuilder<InsertBuilder>
                (initSet(Top::new,
                        target::getTop,
                        target::setTop))
                .in(this);
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

    public InsertBuilder withColumn(ColumnName... columnNames){
        initAdd(Arrays.asList(columnNames),
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

    public TableValueConstructorBuilder<InsertBuilder> withValues(){
        return new TableValueConstructorBuilder<InsertBuilder>
                (initSet(TableValueConstructor::new,
                        target::getValues,
                        target::setValues))
                .in(this);
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
