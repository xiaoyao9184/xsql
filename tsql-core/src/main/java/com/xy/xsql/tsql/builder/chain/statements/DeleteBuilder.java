package com.xy.xsql.tsql.builder.chain.statements;

import com.xy.xsql.core.builder.CodeBuilder;
import com.xy.xsql.tsql.builder.chain.queries.*;
import com.xy.xsql.tsql.model.queries.hints.TableHintLimited;
import com.xy.xsql.tsql.model.datatypes.table.Alias;
import com.xy.xsql.tsql.model.datatypes.table.TableName;
import com.xy.xsql.tsql.model.queries.*;
import com.xy.xsql.tsql.model.statements.Delete;

import java.util.Arrays;
import java.util.List;

import static com.xy.xsql.core.FiledBuilder.initSet;
import static com.xy.xsql.core.ListBuilder.initAdd;

/**
 * DeleteBuilder
 * Created by xiaoyao9184 on 2017/1/9.
 */
@SuppressWarnings("unused")
public class DeleteBuilder extends CodeBuilder<Delete> {

    public DeleteBuilder(Delete tar) {
        super(tar);
    }

    public DeleteBuilder(){
        super(new Delete());
    }


    /**
     * set
     * @param with With
     * @return THIS
     */
    public DeleteBuilder withWith(With with){
        this.target.setWith(with);
        return this;
    }

    /**
     * in
     * @return WithBuilder
     */
    public WithBuilder<DeleteBuilder> withWith(){
        return new WithBuilder<DeleteBuilder>
                (initSet(With::new,
                        target::getWith,
                        target::setWith))
                .in(this);
    }

    /**
     * set
     * @param top Top
     * @return THIS
     */
    public DeleteBuilder withTop(Top top){
        this.target.setTop(top);
        return this;
    }

    /**
     * in
     * @return TopBuilder
     */
    public TopBuilder<DeleteBuilder> withTop(){
        return new TopBuilder<DeleteBuilder>
                (initSet(Top::new,
                        target::getTop,
                        target::setTop))
                .in(this);
    }

    /**
     * set
     * @param useForm form
     * @return THIS
     */
    public DeleteBuilder withFrom(boolean useForm){
        target.setUseForm(useForm);
        return this;
    }

    /**
     * set
     * @param tableAlias table alias
     * @return THIS
     */
    public DeleteBuilder withTableAlias(String tableAlias){
        target.setTableAlias(new Alias<>(tableAlias));
        return this;
    }

    /**
     * set
     * @param tableName TableName
     * @return THIS
     */
    public DeleteBuilder withTableName(TableName tableName){
        target.setTableName(tableName);
        return this;
    }

    /**
     * set
     * @param tableName table name
     * @return THIS
     */
    public DeleteBuilder withTableName(String tableName){
        target.setTableName(new TableName(tableName));
        return this;
    }

    /**
     * set
     * @param tableHintLimiteds TableHintLimited
     * @return THIS
     */
    public DeleteBuilder withTableHint(TableHintLimited... tableHintLimiteds){
        initAdd(Arrays.asList(tableHintLimiteds),
                target::getTableHintLimitedList,
                target::setTableHintLimitedList);
        return this;
    }

    /**
     * set
     * @param tableHintLimiteds TableHintLimited
     * @return THIS
     */
    public DeleteBuilder withTableHint(List<TableHintLimited> tableHintLimiteds){
        initAdd(tableHintLimiteds,
                target::getTableHintLimitedList,
                target::setTableHintLimitedList);
        return this;
    }

    /**
     * set
     * @param output Output
     * @return THIS
     */
    public DeleteBuilder withOutput(Output output) {
        this.target.setOutput(output);
        return this;
    }

    /**
     * in
     * @return OutputBuilder
     */
    public OutputBuilder<DeleteBuilder> withOutput() {
        return new OutputBuilder<DeleteBuilder>
                (initSet(Output::new,
                        target::getOutput,
                        target::setOutput))
                .in(this);
    }

    /**
     * set
     * @param from From
     * @return THIS
     */
    public DeleteBuilder withFrom(From from) {
        this.target.setFrom(from);
        return this;
    }

    /**
     * in
     * @return FromBuilder
     */
    public FromBuilder<DeleteBuilder> withFrom() {
        return new FromBuilder<DeleteBuilder>
                (initSet(From::new,
                        target::getFrom,
                        target::setFrom))
                .in(this);
    }

    /**
     * set
     * @param where Where
     * @return THIS
     */
    public DeleteBuilder withWhere(Where where) {
        this.target.setWhere(where);
        return this;
    }

    /**
     * in
     * @return WhereBuilder
     */
    public WhereBuilder<DeleteBuilder> withWhere() {
        return new WhereBuilder<DeleteBuilder>
                (initSet(Where::new,
                        target::getWhere,
                        target::setWhere))
                .in(this);
    }

    /**
     * set
     * @param option Option
     * @return THIS
     */
    public DeleteBuilder withOption(Option option) {
        this.target.setOption(option);
        return this;
    }

    /**
     * in
     * @return OptionBuilder
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

    /**
     * Quick set
     * @param with With
     * @return THIS
     */
    public DeleteBuilder $With(With with){
        target.setWith(with);
        return this;
    }

    /**
     * Quick in
     * @return WithBuilder
     */
    public WithBuilder<DeleteBuilder> $With(){
        return withWith();
    }

    /**
     * Quick in
     * @return TopBuilder
     */
    public TopBuilder<DeleteBuilder> $Top(){
        return withTop();
    }

    /**
     * Quick set
     * @param tableName TableName
     * @return THIS
     */
    public DeleteBuilder $(TableName tableName) {
        return withTableName(tableName);
    }

    /**
     * Quick set
     * @param alias alias
     * @return THIS
     */
    public DeleteBuilder $From(String alias) {
        return withTableAlias(alias);
    }

    /**
     * Quick set
     * @param tableName TableName
     * @return THIS
     */
    public DeleteBuilder $From(TableName tableName) {
        return withFrom(true)
                .withTableName(tableName);
    }

    /**
     * Quick in
     * @return OutputBuilder
     */
    public OutputBuilder<DeleteBuilder> $Output() {
        return withOutput();
    }

    /**
     * Quick in
     * @return FromBuilder
     */
    public FromBuilder<DeleteBuilder> $From() {
        return withFrom();
    }

    /**
     * Quick in
     * @return WhereBuilder
     */
    public WhereBuilder<DeleteBuilder> $Where() {
        return withWhere();
    }

    /**
     * Quick in
     * @return OptionBuilder
     */
    public OptionBuilder<DeleteBuilder> $Option() {
        return withOption();
    }
}
