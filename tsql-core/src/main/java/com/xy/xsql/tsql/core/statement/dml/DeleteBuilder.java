package com.xy.xsql.tsql.core.statement.dml;

import com.xy.xsql.core.builder.CodeBuilder;
import com.xy.xsql.tsql.core.clause.*;
import com.xy.xsql.tsql.model.clause.*;
import com.xy.xsql.tsql.model.element.Alias;
import com.xy.xsql.tsql.model.element.TableName;
import com.xy.xsql.tsql.model.statement.dml.Delete;

import static com.xy.xsql.core.FiledBuilder.initSet;

/**
 * Created by xiaoyao9184 on 2017/1/9.
 */
public class DeleteBuilder extends CodeBuilder<Delete> {

    public DeleteBuilder(Delete tar) {
        super(tar);
    }

    public DeleteBuilder(){
        super(new Delete());
    }


    /**
     *
     * @return
     */
    public WithBuilder<DeleteBuilder> withWith(){
        return new WithBuilder<DeleteBuilder>
                (initSet(With::new,
                        target::getWith,
                        target::setWith))
                .in(this);
    }

    /**
     *
     * @return
     */
    public TopBuilder<DeleteBuilder> withTop(){
        return new TopBuilder<DeleteBuilder>
                (initSet(Top::new,
                        target::getTop,
                        target::setTop))
                .in(this);
    }

    /**
     *
     * @param useForm
     * @return
     */
    public DeleteBuilder withFrom(boolean useForm){
        target.setUseForm(useForm);
        return this;
    }

    /**
     *
     * @param tableAlias
     * @return
     */
    public DeleteBuilder withTableAlias(String tableAlias){
        target.setTableAlias(new Alias<>(tableAlias));
        return this;
    }

    /**
     *
     * @param tableName
     * @return
     */
    public DeleteBuilder withTableName(TableName tableName){
        target.setTableName(tableName);
        return this;
    }

    /**
     *
     * @param tableName
     * @return
     */
    public DeleteBuilder withTableName(String tableName){
        target.setTableName(new TableName(tableName));
        return this;
    }

    /**
     *
     * @return
     */
    public OutputBuilder<DeleteBuilder> withOutput() {
        return new OutputBuilder<DeleteBuilder>
                (initSet(Output::new,
                        target::getOutput,
                        target::setOutput))
                .in(this);
    }

    /**
     *
     * @return
     */
    public FromBuilder<DeleteBuilder> withFrom() {
        return new FromBuilder<DeleteBuilder>
                (initSet(From::new,
                        target::getFrom,
                        target::setFrom))
                .in(this);
    }

    /**
     *
     * @return
     */
    public WhereBuilder<DeleteBuilder> withWhere() {
        return new WhereBuilder<DeleteBuilder>
                (initSet(Where::new,
                        target::getWhere,
                        target::setWhere))
                .in(this);
    }

    /**
     *
     * @return
     */
    public OptionBuilder<DeleteBuilder> withOption() {
        return new OptionBuilder<DeleteBuilder>
                (initSet(Option::new,
                        target::getOption,
                        target::setOption))
                .in(this);
    }




    /*
    Quick
     */

    public static DeleteBuilder DELETE(){
        return new DeleteBuilder();
    }

    public TopBuilder<DeleteBuilder> $Top(){
        return withTop();
    }

    public DeleteBuilder $(TableName tableName) {
        return withTableName(tableName);
    }

    public DeleteBuilder $From(String alias) {
        return withTableAlias(alias);
    }

    public DeleteBuilder $From(TableName tableName) {
        return withFrom(true)
                .withTableName(tableName);
    }

    public OutputBuilder<DeleteBuilder> $Output() {
        return withOutput();
    }

    public FromBuilder<DeleteBuilder> $From() {
        return withFrom();
    }

    public WhereBuilder<DeleteBuilder> $Where() {
        return withWhere();
    }

    public OptionBuilder<DeleteBuilder> $Option() {
        return withOption();
    }
}
