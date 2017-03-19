package com.xy.xsql.tsql.core.statement;

import com.xy.xsql.core.builder.CodeBuilder;
import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.core.builder.SubBuilder;
import com.xy.xsql.tsql.core.clause.*;
import com.xy.xsql.tsql.model.clause.*;
import com.xy.xsql.tsql.model.element.Alias;
import com.xy.xsql.tsql.model.element.ColumnName;
import com.xy.xsql.tsql.model.element.TableName;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.model.statement.dml.Update;

import java.util.ArrayList;
import java.util.List;

import static com.xy.xsql.core.FiledBuilder.initSet;
import static com.xy.xsql.core.ListBuilder.initNew;

/**
 * Created by xiaoyao9184 on 2017/1/7.
 */
public class UpdateBuilder extends CodeBuilder<Update> {

    public UpdateBuilder(Update tar) {
        super(tar);
    }

    public UpdateBuilder(){
        super(new Update());
    }


    /**
     *
     * @return
     */
    public WithBuilder<UpdateBuilder> withWith(){
        return new WithBuilder<UpdateBuilder>
                (initSet(With::new,
                        tar::getWith,
                        tar::setWith))
                .in(this);
    }

    /**
     *
     * @return
     */
    public TopBuilder<UpdateBuilder> withTop(){
        return new TopBuilder<UpdateBuilder>
                (initSet(Top::new,
                        tar::getTop,
                        tar::setTop))
                .in(this);
    }

    /**
     *
     * @param tableAlias
     * @return
     */
    public UpdateBuilder withTableAlias(String tableAlias){
        tar.setTableAlias(new Alias<>(tableAlias));
        return this;
    }

    /**
     *
     * @return
     */
    public UpdateBuilder withTableName(TableName tableName){
        tar.setTableName(tableName);
        return this;
    }

    /**
     *
     * @param tableName
     * @return
     */
    public UpdateBuilder withTableName(String tableName){
        tar.setTableName(new TableName(tableName));
        return this;
    }

    /**
     *
     * @return
     */
    @Deprecated
    public SetListBuilder<UpdateBuilder> withSetList(){
        List<Update.Set> setList = new ArrayList<>();
        tar.setSets(setList);
        return new SetListBuilder<UpdateBuilder>
                (setList)
                .in(this);
    }

    /**
     *
     * @return
     */
    public SetBuilder<UpdateBuilder> withSetItem(){
        return new SetBuilder<UpdateBuilder>
                (initNew(Update.Set::new,
                        tar::getSets,
                        tar::setSets))
                .in(this);
    }

    /**
     *
     * @return
     */
    public OutputBuilder<UpdateBuilder> withOutput() {
        return new OutputBuilder<UpdateBuilder>
                (initSet(Output::new,
                        tar::getOutput,
                        tar::setOutput))
                .in(this);
    }

    /**
     *
     * @return
     */
    public FromBuilder<UpdateBuilder> withFrom() {
        return new FromBuilder<UpdateBuilder>
                (initSet(From::new,
                        tar::getFrom,
                        tar::setFrom))
                .in(this);
    }

    /**
     *
     * @return
     */
    public WhereBuilder<UpdateBuilder> withWhere() {
        return new WhereBuilder<UpdateBuilder>
                (initSet(Where::new,
                        tar::getWhere,
                        tar::setWhere))
                .in(this);
    }

    /**
     *
     * @return
     */
    public OptionBuilder<UpdateBuilder> withOption() {
        return new OptionBuilder<UpdateBuilder>
                (initSet(Option::new,
                        tar::getOption,
                        tar::setOption))
                .in(this);
    }


    /**
     *
     * @param <Done>
     */
    @Deprecated
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
     * @param <ParentBuilder>
     */
    public static class SetBuilder<ParentBuilder>
            extends CodeTreeBuilder<SetBuilder<ParentBuilder>,ParentBuilder,Update.Set> {

        public SetBuilder(Update.Set set) {
            super(set);
        }

        public SetBuilder<ParentBuilder> withColumnName(ColumnName column){
            tar.setColumnName(column);
            return this;
        }

        public SetBuilder<ParentBuilder> withColumnName(String columnName) {
            tar.setColumnName(new ColumnName(columnName));
            return this;
        }

        public SetBuilder<ParentBuilder> withExpression(Expression expression){
            tar.setExpression(expression);
            return this;
        }

        public SetBuilder<ParentBuilder> withUseNull(boolean useNull){
            tar.setUseNull(useNull);
            return this;
        }
    }
}
