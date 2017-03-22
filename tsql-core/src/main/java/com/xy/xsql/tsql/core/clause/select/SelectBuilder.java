package com.xy.xsql.tsql.core.clause.select;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.clause.select.Select;
import com.xy.xsql.tsql.model.datatype.StringConstant;
import com.xy.xsql.tsql.model.element.Alias;
import com.xy.xsql.tsql.model.element.ColumnName;
import com.xy.xsql.tsql.model.element.TableName;
import com.xy.xsql.tsql.model.expression.Expression;

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



    public SelectBuilder<ParentBuilder> withDistinct() {
        target.setUseDistinct(true);
        return this;
    }

    public SelectItemBuilder<SelectBuilder<ParentBuilder>> withSelectItem(){
        return new SelectItemBuilder<SelectBuilder<ParentBuilder>>
                (initNew(Select.SelectItem::new,
                        target::getList,
                        target::setList))
                .in(this);
    }



    public SelectBuilder<ParentBuilder> $Distinct() {
        return withDistinct();
    }


    /**
     * Quick set
     * @return
     */
    public SelectBuilder<ParentBuilder> $() {
        return withSelectItem()
                .withAll()
                .and();
    }

    /**
     *
     * @param tableName
     * @return
     */
    public SelectBuilder<ParentBuilder> $(TableName tableName) {
        return withSelectItem()
                .withTableAll(tableName)
                .and();
    }

    public SelectBuilder<ParentBuilder> $(ColumnName columnName) {
        return withSelectItem()
                .withColumnName(columnName)
                .and();
    }

    public SelectBuilder<ParentBuilder> $(ColumnName columnName, String columnAlias) {
        return withSelectItem()
                .withColumnName(columnName)
                .withColumnAlias(columnAlias)
                .and();
    }

    public SelectBuilder<ParentBuilder> $(ColumnName columnName, Expression expression) {
        return withSelectItem()
                .withColumnName(columnName)
                .withExpression(expression)
                .and();
    }

    public SelectBuilder<ParentBuilder> $(Expression expression) {
        return withSelectItem()
                .withExpression(expression)
                .and();
    }

    public SelectBuilder<ParentBuilder> $(Expression expression, String columnAlias) {
        return withSelectItem()
                .withExpression(expression)
                .withColumnAlias(columnAlias)
                .and();
    }


    /**
     * SelectItemBuilder
     * @param <ParentBuilder>
     */
    public class SelectItemBuilder<ParentBuilder>
            extends CodeTreeBuilder<SelectItemBuilder<ParentBuilder>,ParentBuilder,Select.SelectItem> {

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
