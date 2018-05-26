package com.xy.xsql.tsql.builder.chain.statements;

import com.xy.xsql.core.builder.simple.CodeBuilder;
import com.xy.xsql.tsql.builder.chain.queries.*;
import com.xy.xsql.tsql.model.datatypes.table.Alias;
import com.xy.xsql.tsql.model.datatypes.table.TableName;
import com.xy.xsql.tsql.model.queries.*;
import com.xy.xsql.tsql.model.queries.hints.TableHintLimited;
import com.xy.xsql.tsql.model.statements.Delete;

import java.util.List;

import static com.xy.xsql.core.handler.list.ListHandler.list;
import static com.xy.xsql.core.handler.object.GetterSetterObjectHandler.object;

/**
 * DeleteBuilder
 * Created by xiaoyao9184 on 2017/1/9.
 */
@SuppressWarnings("unused")
public class DeleteBuilder extends CodeBuilder<Delete> {

    public DeleteBuilder(Delete target) {
        super(target);
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
                (object(target::getWith, target::setWith)
                        .init(With::new))
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
                (object(target::getTop, target::setTop)
                        .init(Top::new))
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
        list(target::getTableHintLimitedList, target::setTableHintLimitedList)
                .addAll(tableHintLimiteds);
        return this;
    }

    /**
     * set
     * @param tableHintLimiteds TableHintLimited
     * @return THIS
     */
    public DeleteBuilder withTableHint(List<TableHintLimited> tableHintLimiteds){
        list(target::getTableHintLimitedList, target::setTableHintLimitedList)
                .addAll(tableHintLimiteds);
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
                (object(target::getOutput, target::setOutput)
                        .init(Output::new))
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
                (object(target::getFrom, target::setFrom)
                        .init(From::new))
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
                (object(target::getWhere, target::setWhere)
                        .init(Where::new))
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
     * @param alias alias
     * @return THIS
     */
    public DeleteBuilder $(String alias) {
        return withTableAlias(alias);
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
        return withFrom(true)
                .withTableAlias(alias);
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
