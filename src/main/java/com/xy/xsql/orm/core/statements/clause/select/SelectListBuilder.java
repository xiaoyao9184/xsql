package com.xy.xsql.orm.core.statements.clause.select;

import com.xy.xsql.orm.core.statements.SubBuilder;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.clause.select.SelectList;

import java.util.ArrayList;

/**
 * Created by xiaoyao9184 on 2016/12/28.
 */
public class SelectListBuilder<Done>
        extends SubBuilder<SelectListBuilder<Done>,Void,Done> {

    private SelectList selectList;

    private SelectItemBuilder<SelectListBuilder<Done>> selectItemBuilder;

    public SelectListBuilder(SelectList selectList) {
        this.selectList = selectList;
    }


    /**
     *
     * @return
     */
    public SelectItemBuilder<SelectListBuilder<Done>> withSelectItem(){
        SelectList.SelectItem selectItem = new SelectList.SelectItem();
        if(this.selectList.getList() == null){
            this.selectList.setList(new ArrayList<SelectList.SelectItem>());
        }
        this.selectList.getList().add(selectItem);

        selectItemBuilder = new SelectItemBuilder<>(selectItem);
        return selectItemBuilder.in(this);
    }


    public class SelectItemBuilder<Done2>
            extends SubBuilder<SelectItemBuilder<Done2>,Void,Done2> {

        private SelectList.SelectItem selectItem;

        public SelectItemBuilder(SelectList.SelectItem selectItem) {
            this.selectItem = selectItem;
        }

        public SelectItemBuilder<Done2> withAll(){
            this.selectItem.setUseAll(true);
            return this;
        }

        public SelectItemBuilder<Done2> withTableAll(){
            this.selectItem.setUseTableAll(true);
            return this;
        }

        public SelectItemBuilder<Done2> withColumnName(String columnName){
            this.selectItem.setColumnName(columnName);
            return this;
        }

        public SelectItemBuilder<Done2> withExpression(Expression expression){
            this.selectItem.setExpression(expression);
            return this;
        }

        public SelectItemBuilder<Done2> withAs(){
            this.selectItem.setUseAs(true);
            return this;
        }

        public SelectItemBuilder<Done2> withColumnAlias(String columnAlias){
            this.selectItem.setColumnAlias(columnAlias);
            return this;
        }

        public SelectItemBuilder<Done2> withEQ(){
            this.selectItem.setUseEQ(true);
            return this;
        }
    }

}
