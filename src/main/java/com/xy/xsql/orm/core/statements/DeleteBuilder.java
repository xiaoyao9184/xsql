package com.xy.xsql.orm.core.statements;

import com.xy.xsql.orm.core.BaseBuilder;
import com.xy.xsql.orm.core.statements.clause.FromBuilder;
import com.xy.xsql.orm.core.statements.clause.TopBuilder;
import com.xy.xsql.orm.core.statements.clause.WhereBuilder;
import com.xy.xsql.orm.data.sql.clause.From;
import com.xy.xsql.orm.data.sql.clause.Top;
import com.xy.xsql.orm.data.sql.clause.Where;
import com.xy.xsql.orm.data.sql.element.info.Alias;
import com.xy.xsql.orm.data.sql.element.info.TableName;
import com.xy.xsql.orm.data.sql.statements.dml.Delete;

/**
 * Created by xiaoyao9184 on 2017/1/9.
 */
public class DeleteBuilder implements BaseBuilder<Void,Delete> {

    private Delete delete;

    public DeleteBuilder(){
        this.delete = new Delete();
    }


    @Override
    public Delete build(Void aVoid) {
        return delete;
    }


    /**
     *
     * @return
     */
    public TopBuilder<DeleteBuilder> withTop(){
        Top top = new Top();
        delete.setTop(top);
        return new TopBuilder<DeleteBuilder>(top)
                .in(this);
    }

    /**
     *
     * @param useForm
     * @return
     */
    public DeleteBuilder withForm(boolean useForm){
        delete.setUseForm(useForm);
        return this;
    }

    /**
     *
     * @param tableAlias
     * @return
     */
    public DeleteBuilder withTableAlias(String tableAlias){
        delete.setTableAlias(new Alias<Alias>(tableAlias));
        return this;
    }

    /**
     *
     * @param tableName
     * @return
     */
    public DeleteBuilder withTableName(TableName tableName){
        delete.setTableName(tableName);
        return this;
    }

    /**
     *
     * @param tableName
     * @return
     */
    public DeleteBuilder withTableName(String tableName){
        delete.setTableName(new TableName(tableName));
        return this;
    }

    /**
     *
     * @return
     */
    public FromBuilder<DeleteBuilder> withFrom() {
        From from = new From();
        delete.setFrom(from);
        return new FromBuilder<DeleteBuilder>(from)
                .in(this);
    }

    /**
     *
     * @return
     */
    public WhereBuilder<DeleteBuilder> withWhere() {
        Where where = new Where();
        delete.setWhere(where);
        return new WhereBuilder<DeleteBuilder>(where)
                .in(this);
    }

}
