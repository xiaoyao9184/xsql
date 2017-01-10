package com.xy.xsql.orm.core.statements;

import com.xy.xsql.orm.core.BaseBuilder;
import com.xy.xsql.orm.core.statements.clause.FromBuilder;
import com.xy.xsql.orm.core.statements.clause.TopBuilder;
import com.xy.xsql.orm.core.statements.clause.WhereBuilder;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.clause.From;
import com.xy.xsql.orm.data.sql.clause.Top;
import com.xy.xsql.orm.data.sql.clause.Where;
import com.xy.xsql.orm.data.sql.element.info.Alias;
import com.xy.xsql.orm.data.sql.element.info.Column;
import com.xy.xsql.orm.data.sql.element.info.TableName;
import com.xy.xsql.orm.data.sql.statements.dml.Update;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoyao9184 on 2017/1/7.
 */
public class UpdateBuilder implements BaseBuilder<Void,Update> {

    private Update update;

    public UpdateBuilder(){
        this.update = new Update();
    }


    @Override
    public Update build(Void aVoid) {
        return update;
    }


    /**
     *
     * @return
     */
    public TopBuilder<UpdateBuilder> withTop(){
        Top top = new Top();
        update.setTop(top);
        return new TopBuilder<UpdateBuilder>(top)
                .in(this);
    }

    /**
     *
     * @param tableAlias
     * @return
     */
    public UpdateBuilder withTableAlias(String tableAlias){
        update.setTableAlias(new Alias<Void>(tableAlias));
        return this;
    }

    /**
     *
     * @return
     */
    public UpdateBuilder withTableName(TableName tableName){
        update.setTableName(tableName);
        return this;
    }

    /**
     *
     * @param tableName
     * @return
     */
    public UpdateBuilder withTableName(String tableName){
        update.setTableName(new TableName(tableName));
        return this;
    }

    /**
     *
     * @return
     */
    public SetListBuilder<UpdateBuilder> withSetList(){
        List<Update.Set> setList = new ArrayList<>();
        update.setSets(setList);
        return new SetListBuilder<UpdateBuilder>(setList)
                .in(this);
    }

    /**
     *
     * @return
     */
    public SetBuilder<UpdateBuilder> withSetItem(){
        Update.Set set = new Update.Set();
        if(this.update.getSets() == null){
            this.update.setSets(new ArrayList<Update.Set>());
        }
        update.getSets().add(set);
        return new SetBuilder<UpdateBuilder>(set)
                .in(this);
    }

    /**
     *
     * @return
     */
    public FromBuilder<UpdateBuilder> withFrom() {
        From from = new From();
        update.setFrom(from);
        return new FromBuilder<UpdateBuilder>(from)
                .in(this);
    }

    /**
     *
     * @return
     */
    public WhereBuilder<UpdateBuilder> withWhere() {
        Where where = new Where();
        update.setWhere(where);
        return new WhereBuilder<UpdateBuilder>(where)
                .in(this);
    }


    /**
     *
     * @param <Done>
     */
    public static class SetListBuilder<Done>
            extends SubBuilder<SetListBuilder<Done>,Void,Done> {

        private List<Update.Set> setList;

        public SetListBuilder(List<Update.Set> setList) {
            this.setList = setList;
        }

        public SetBuilder<SetListBuilder<Done>> withItem(){
            Update.Set set = new Update.Set();
            if(this.setList == null){
                this.setList = new ArrayList<>();
            }
            this.setList.add(set);
            return new SetBuilder<SetListBuilder<Done>>(set)
                    .in(this);
        }
    }

    /**
     *
     * @param <Done>
     */
    public static class SetBuilder<Done>
            extends SubBuilder<SetBuilder<Done>,Void,Done> {

        private Update.Set set;

        public SetBuilder(Update.Set set) {
            this.set = set;
        }

        public SetBuilder<Done> withColumnName(Column column){
            this.set.setColumnName(column);
            return this;
        }

        public SetBuilder<Done> withColumnName(String columnName) {
            this.set.setColumnName(new Column(columnName));
            return this;
        }

        public SetBuilder<Done> withExpression(Expression expression){
            this.set.setExpression(expression);
            return this;
        }

        public SetBuilder<Done> withUseNull(boolean useNull){
            this.set.setUseNull(useNull);
            return this;
        }
    }
}
