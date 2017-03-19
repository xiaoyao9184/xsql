package com.xy.xsql.tsql.core.statement;

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
                        tar::getWith,
                        tar::setWith))
                .in(this);
    }

    /**
     *
     * @return
     */
    public TopBuilder<DeleteBuilder> withTop(){
        return new TopBuilder<DeleteBuilder>
                (initSet(Top::new,
                        tar::getTop,
                        tar::setTop))
                .in(this);
    }

    /**
     *
     * @param useForm
     * @return
     */
    public DeleteBuilder withForm(boolean useForm){
        tar.setUseForm(useForm);
        return this;
    }

    /**
     *
     * @param tableAlias
     * @return
     */
    public DeleteBuilder withTableAlias(String tableAlias){
        tar.setTableAlias(new Alias<>(tableAlias));
        return this;
    }

    /**
     *
     * @param tableName
     * @return
     */
    public DeleteBuilder withTableName(TableName tableName){
        tar.setTableName(tableName);
        return this;
    }

    /**
     *
     * @param tableName
     * @return
     */
    public DeleteBuilder withTableName(String tableName){
        tar.setTableName(new TableName(tableName));
        return this;
    }

    /**
     *
     * @return
     */
    public OutputBuilder<DeleteBuilder> withOutput() {
        return new OutputBuilder<DeleteBuilder>
                (initSet(Output::new,
                        tar::getOutput,
                        tar::setOutput))
                .in(this);
    }

    /**
     *
     * @return
     */
    public FromBuilder<DeleteBuilder> withFrom() {
        return new FromBuilder<DeleteBuilder>
                (initSet(From::new,
                        tar::getFrom,
                        tar::setFrom))
                .in(this);
    }

    /**
     *
     * @return
     */
    public WhereBuilder<DeleteBuilder> withWhere() {
        return new WhereBuilder<DeleteBuilder>
                (initSet(Where::new,
                        tar::getWhere,
                        tar::setWhere))
                .in(this);
    }

    /**
     *
     * @return
     */
    public OptionBuilder<DeleteBuilder> withOption() {
        return new OptionBuilder<DeleteBuilder>
                (initSet(Option::new,
                        tar::getOption,
                        tar::setOption))
                .in(this);
    }

}
