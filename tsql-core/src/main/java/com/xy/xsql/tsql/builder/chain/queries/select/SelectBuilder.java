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
 * SelectBuilder
 * Created by xiaoyao9184 on 2016/12/28.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class SelectBuilder<ParentBuilder>
        extends CodeTreeBuilder<SelectBuilder<ParentBuilder>,ParentBuilder,Select> {
    
    public SelectBuilder(Select selectList) {
        super(selectList);
    }

    public SelectBuilder() {
        super(new Select());
    }


    /**
     * set
     * @return THIS
     */
    public SelectBuilder<ParentBuilder> withAll() {
        target.setUseAll(true);
        return this;
    }
    /**
     * set
     * @return THIS
     */
    public SelectBuilder<ParentBuilder> withDistinct() {
        target.setUseDistinct(true);
        return this;
    }

    /**
     * set
     * @param top Top
     * @return THIS
     */
    public SelectBuilder<ParentBuilder> withTop(Top top) {
        this.target.setTop(top);
        return this;
    }

    /**
     * in
     * @return TopBuilder
     */
    public TopBuilder<SelectBuilder<ParentBuilder>> withTop() {
        return new TopBuilder<SelectBuilder<ParentBuilder>>
                (initSet(Top::new,
                        target::getTop,
                        target::setTop))
                .in(this);
    }

    /**
     * in
     * @return SelectItemBuilder
     */
    public SelectItemBuilder<SelectBuilder<ParentBuilder>> withSelectItem(){
        return new SelectItemBuilder<SelectBuilder<ParentBuilder>>
                (initNew(Select.SelectItem::new,
                        target::getSelectList,
                        target::setSelectList))
                .in(this);
    }




    /*
    Quick set/into
     */

    /**
     * Quick set useAll
     * @return THIS
     */
    public SelectBuilder<ParentBuilder> $All() {
        return withAll();
    }

    /**
     * Quick set useDistinct
     * @return THIS
     */
    public SelectBuilder<ParentBuilder> $Distinct() {
        return withDistinct();
    }

    /**
     * Quick in TopBuilder
     * And set top
     * @return TopBuilder
     */
    public TopBuilder<SelectBuilder<ParentBuilder>> $Top(){
        return withTop();
    }

    /**
     * Quick set selectList
     * into SelectItemBuilder get-out
     * @return TopBuilder
     */
    public SelectBuilder<ParentBuilder> $() {
        return withSelectItem()
                .withAll()
                .and();
    }

    /**
     * Quick set selectList
     * into SelectItemBuilder get-out
     * @param tableName TableName
     * @return TopBuilder
     */
    public SelectBuilder<ParentBuilder> $(TableName tableName) {
        return withSelectItem()
                .withTableAll(tableName)
                .and();
    }

    /**
     * Quick set selectList
     * into SelectItemBuilder get-out
     * @param columnName ColumnName
     * @return TopBuilder
     */
    public SelectBuilder<ParentBuilder> $(ColumnName columnName) {
        return withSelectItem()
                .withColumnName(columnName)
                .and();
    }

    /**
     * Quick set selectList
     * into SelectItemBuilder get-out
     * @param columnName ColumnName
     * @param columnAlias column alias
     * @return TopBuilder
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
     * @param columnAlias column alias
     * @param expression Expression
     * @return TopBuilder
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
     * @param expression Expression
     * @return TopBuilder
     */
    public SelectBuilder<ParentBuilder> $(Expression expression) {
        return withSelectItem()
                .withExpression(expression)
                .and();
    }

    /**
     * Quick set selectList
     * into SelectItemBuilder get-out
     * @param expression Expression
     * @param columnAlias column alias
     * @return TopBuilder
     */
    public SelectBuilder<ParentBuilder> $(Expression expression, String columnAlias) {
        return withSelectItem()
                .withExpression(expression)
                .withAs()
                .withColumnAlias(columnAlias)
                .and();
    }


    /**
     * SelectListBuilder
     * @param <ParentBuilder>
     */
    public static class SelectListBuilder<ParentBuilder>
            extends CodeTreeBuilder<SelectListBuilder<ParentBuilder>,ParentBuilder,List<Select.SelectItem>> {

        public SelectListBuilder() {
            super(new ArrayList<>());
        }

        /**
         * in
         * @return SelectItemBuilder
         */
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

        /**
         * set
         * @return THIS
         */
        public SelectItemBuilder<ParentBuilder> withAll(){
            target.setUseAll(true);
            return this;
        }

        /**
         * set
         * @param tableName TableName
         * @return THIS
         */
        public SelectItemBuilder<ParentBuilder> withTableAll(TableName tableName){
            target.setUseTableAll(true);
            target.setTableViewName(tableName);
            return this;
        }

        /**
         * set
         * @param columnName ColumnName
         * @return THIS
         */
        public SelectItemBuilder<ParentBuilder> withColumnName(ColumnName columnName){
            target.setColumnName(columnName);
            return this;
        }

        /**
         * set
         * @param expression Expression
         * @return THIS
         */
        public SelectItemBuilder<ParentBuilder> withExpression(Expression expression){
            target.setExpression(expression);
            return this;
        }

        /**
         * set
         * @return THIS
         */
        public SelectItemBuilder<ParentBuilder> withAs(){
            target.setUseAs(true);
            return this;
        }

        /**
         * set
         * @param columnAlias column alias
         * @return THIS
         */
        public SelectItemBuilder<ParentBuilder> withColumnAlias(String columnAlias){
            target.setColumnAlias(new Alias<>(columnAlias));
            return this;
        }

        /**
         * set
         * @param columnAlias column alias
         * @return THIS
         */
        public SelectItemBuilder<ParentBuilder> withColumnAlias(Alias<Void> columnAlias){
            target.setColumnAlias(columnAlias);
            return this;
        }

        /**
         * set
         * @return THIS
         */
        public SelectItemBuilder<ParentBuilder> withEQ(){
            target.setUseEQ(true);
            return this;
        }
    }

}
