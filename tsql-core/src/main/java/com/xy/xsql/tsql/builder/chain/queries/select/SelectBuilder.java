package com.xy.xsql.tsql.builder.chain.queries.select;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.builder.chain.queries.TopBuilder;
import com.xy.xsql.tsql.model.queries.Top;
import com.xy.xsql.tsql.model.queries.select.Select;
import com.xy.xsql.tsql.model.datatypes.table.Alias;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.datatypes.table.TableName;
import com.xy.xsql.tsql.model.elements.expressions.Expression;

import java.util.ArrayList;
import java.util.List;

import static com.xy.xsql.core.FiledBuilder.initSet;
import static com.xy.xsql.core.ListBuilder.initNew;

/**
 * Created by xiaoyao9184 on 2016/12/28.
 */
public class SelectBuilder<ParentBuilder>
        extends CodeTreeBuilder<SelectBuilder<ParentBuilder>,ParentBuilder,Select> {
    
    public SelectBuilder(Select selectList) {
        super(selectList);
    }

    public SelectBuilder() {
        super(new Select());
    }


    public SelectBuilder<ParentBuilder> withAll() {
        target.setUseAll(true);
        return this;
    }

    public SelectBuilder<ParentBuilder> withDistinct() {
        target.setUseDistinct(true);
        return this;
    }

    public TopBuilder<SelectBuilder<ParentBuilder>> withTop() {
        return new TopBuilder<SelectBuilder<ParentBuilder>>
                (initSet(Top::new,
                        target::getTop,
                        target::setTop))
                .in(this);
    }

    public SelectItemBuilder<SelectBuilder<ParentBuilder>> withSelectItem(){
        return new SelectItemBuilder<SelectBuilder<ParentBuilder>>
                (initNew(Select.SelectItem::new,
                        target::getSelectList,
                        target::setSelectList))
                .in(this);
    }

    public SelectBuilder<ParentBuilder> withTop(Top top) {
        this.target.setTop(top);
        return this;
    }


    /*
    Quick set/into
     */

    /**
     * Quick set useAll
     * @return
     */
    public SelectBuilder<ParentBuilder> $All() {
        return withAll();
    }

    /**
     * Quick set useDistinct
     * @return
     */
    public SelectBuilder<ParentBuilder> $Distinct() {
        return withDistinct();
    }

    /**
     * Quick into TopBuilder
     * And set top
     * @return
     */
    public TopBuilder<SelectBuilder<ParentBuilder>> $Top(){
        return withTop();
    }

    /**
     * Quick set selectList
     * into SelectItemBuilder get-out
     * @return
     */
    public SelectBuilder<ParentBuilder> $() {
        return withSelectItem()
                .withAll()
                .and();
    }

    /**
     * Quick set selectList
     * into SelectItemBuilder get-out
     * @param tableName
     * @return
     */
    public SelectBuilder<ParentBuilder> $(TableName tableName) {
        return withSelectItem()
                .withTableAll(tableName)
                .and();
    }

    /**
     * Quick set selectList
     * into SelectItemBuilder get-out
     * @param columnName
     * @return
     */
    public SelectBuilder<ParentBuilder> $(ColumnName columnName) {
        return withSelectItem()
                .withColumnName(columnName)
                .and();
    }

    /**
     * Quick set selectList
     * into SelectItemBuilder get-out
     * @param columnName
     * @param columnAlias
     * @return
     */
    public SelectBuilder<ParentBuilder> $(ColumnName columnName, String columnAlias) {
        return withSelectItem()
                .withColumnName(columnName)
                .withAs()
                .withColumnAlias(columnAlias)
                .and();
    }

    /**
     * Quick set selectList
     * into SelectItemBuilder get-out
     * @param columnAlias
     * @param expression
     * @return
     */
    public SelectBuilder<ParentBuilder> $(String columnAlias, Expression expression) {
        return withSelectItem()
                .withColumnAlias(columnAlias)
                .withExpression(expression)
                .withEQ()
                .and();
    }

    /**
     * Quick set selectList
     * into SelectItemBuilder get-out
     * @param expression
     * @return
     */
    public SelectBuilder<ParentBuilder> $(Expression expression) {
        return withSelectItem()
                .withExpression(expression)
                .and();
    }

    /**
     * Quick set selectList
     * into SelectItemBuilder get-out
     * @param expression
     * @param columnAlias
     * @return
     */
    public SelectBuilder<ParentBuilder> $(Expression expression, String columnAlias) {
        return withSelectItem()
                .withExpression(expression)
                .withAs()
                .withColumnAlias(columnAlias)
                .and();
    }


    public static class SelectListBuilder<ParentBuilder>
            extends CodeTreeBuilder<SelectListBuilder<ParentBuilder>,ParentBuilder,List<Select.SelectItem>> {

        public SelectListBuilder() {
            super(new ArrayList<>());
        }

        public SelectItemBuilder<SelectListBuilder<ParentBuilder>> withItem(){
            return new SelectItemBuilder<SelectListBuilder<ParentBuilder>>
                    ()
                    .enter(this, item -> this.target.add(item));
        }
    }

    /**
     * SelectItemBuilder
     * @param <ParentBuilder>
     */
    public static class SelectItemBuilder<ParentBuilder>
            extends CodeTreeBuilder<SelectItemBuilder<ParentBuilder>,ParentBuilder,Select.SelectItem> {

        public SelectItemBuilder() {
            super(new Select.SelectItem());
        }

        public SelectItemBuilder(Select.SelectItem selectItem) {
            super(selectItem);
        }

        public SelectItemBuilder<ParentBuilder> withAll(){
            target.setUseAll(true);
            return this;
        }

        public SelectItemBuilder<ParentBuilder> withTableAll(TableName tableName){
            target.setUseTableAll(true);
            target.setTableViewName(tableName);
            return this;
        }

        public SelectItemBuilder<ParentBuilder> withColumnName(ColumnName columnName){
            target.setColumnName(columnName);
            return this;
        }

        public SelectItemBuilder<ParentBuilder> withExpression(Expression expression){
            target.setExpression(expression);
            return this;
        }

        public SelectItemBuilder<ParentBuilder> withAs(){
            target.setUseAs(true);
            return this;
        }

        public SelectItemBuilder<ParentBuilder> withColumnAlias(String columnAlias){
            target.setColumnAlias(new Alias<>(columnAlias));
            return this;
        }

        public SelectItemBuilder<ParentBuilder> withColumnAlias(Alias<Void> columnAlias){
            target.setColumnAlias(columnAlias);
            return this;
        }

        public SelectItemBuilder<ParentBuilder> withEQ(){
            target.setUseEQ(true);
            return this;
        }
    }

}
