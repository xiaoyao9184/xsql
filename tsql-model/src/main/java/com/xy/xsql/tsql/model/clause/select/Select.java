package com.xy.xsql.tsql.model.clause.select;

import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.clause.Clause;
import com.xy.xsql.tsql.model.clause.Top;
import com.xy.xsql.tsql.model.element.Alias;
import com.xy.xsql.tsql.model.element.ColumnName;
import com.xy.xsql.tsql.model.element.TableName;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.model.operator.Comparison;
import com.xy.xsql.tsql.util.CheckUtil;
import com.xy.xsql.tsql.util.ListBlockBuilder;

import java.util.List;

/**
 *
 *

 SELECT [ ALL | DISTINCT ]
 [ TOP ( expression ) [ PERCENT ] [ WITH TIES ] ]
 <select_list>
 <select_list> ::=
 {
 *
 | { table_name | view_name | table_alias }.*
 | {
 [ { table_name | view_name | table_alias }. ]
 { column_name | $IDENTITY | $ROWGUID }
 | udt_column_name [ { . | :: } { { property_name | field_name }
 | method_name ( argument [ ,...n] ) } ]
 | expression
 [ [ AS ] column_alias ]
 }
 | column_alias = expression
 } [ ,...n ]

 *
 * Created by xiaoyao9184 on 2016/12/23.
 */
public class Select implements Clause {

    // [ ALL | DISTINCT ]
    private boolean useAll;
    private boolean useDistinct;

    //<TOP Clause>
    private Top top;

    //<select_list>
    private List<SelectItem> selectList;


    public boolean isUseAll() {
        return useAll;
    }

    public void setUseAll(boolean useAll) {
        this.useAll = useAll;
    }

    public boolean isUseDistinct() {
        return useDistinct;
    }

    public void setUseDistinct(boolean useDistinct) {
        this.useDistinct = useDistinct;
    }

    public Top getTop() {
        return top;
    }

    public void setTop(Top top) {
        this.top = top;
    }

    public List<SelectItem> getSelectList() {
        return selectList;
    }

    public void setSelectList(List<SelectItem> selectList) {
        this.selectList = selectList;
    }


    @Override
    public List<Block> toBlockList() {
        return new ListBlockBuilder()
                .append(useAll ?
                        Keywords.ALL :
                        useDistinct ?
                                Keywords.DISTINCT :
                                null)
                .append(top)
                .append(selectList)
                .build();
    }


    /**
     * TODO maybe use interface
     */
    public static class SelectItem implements Block {
        //*
        private boolean useAll;

        //{ table_name | view_name | table_alias }.*
        private boolean useTableAll;
        private TableName tableViewName;

        /*
        {
          [ { table_name | view_name | table_alias }. ]
               { column_name | $IDENTITY | $ROWGUID }
          | udt_column_name [ { . | :: } { { property_name | field_name }
            | method_name ( argument [ ,...n] ) } ]
          | expression
          [ [ AS ] column_alias ]
         }
        */
        //column_name
        private ColumnName columnName;
        //expression
        private Expression expression;
        //[ [ AS ] column_alias ]
        private boolean useAs;
        private Alias<Void> columnAlias;

        //column_alias = expression
        private boolean useEQ;

        public boolean isUseAll() {
            return useAll;
        }

        public void setUseAll(boolean useAll) {
            this.useAll = useAll;
        }

        public boolean isUseTableAll() {
            return useTableAll;
        }

        public void setUseTableAll(boolean useTableAll) {
            this.useTableAll = useTableAll;
        }

        public TableName getTableViewName() {
            return tableViewName;
        }

        public void setTableViewName(TableName tableViewName) {
            this.tableViewName = tableViewName;
        }

        public ColumnName getColumnName() {
            return columnName;
        }

        public void setColumnName(ColumnName columnName) {
            this.columnName = columnName;
        }

        public Expression getExpression() {
            return expression;
        }

        public void setExpression(Expression expression) {
            this.expression = expression;
        }

        public boolean isUseAs() {
            return useAs;
        }

        public void setUseAs(boolean useAs) {
            this.useAs = useAs;
        }

        public Alias<Void> getColumnAlias() {
            return columnAlias;
        }

        public void setColumnAlias(Alias<Void> columnAlias) {
            this.columnAlias = columnAlias;
        }

        public boolean isUseEQ() {
            return useEQ;
        }

        public void setUseEQ(boolean useEQ) {
            this.useEQ = useEQ;
        }


        @Override
        public List<Block> toBlockList() {
            ListBlockBuilder b = new ListBlockBuilder();

            if(useAll){
                b.append("*");
            } else if(useTableAll){
                b.append(tableViewName)
                        .append(".")
                        .append("*");
            } else if(useEQ){
                b.append(columnAlias)
                        .append(Comparison.EQUAL)
                        .append(expression);
            } else {
                if(!CheckUtil.isNull(columnName)){
                    if(tableViewName != null){
                        b.append(tableViewName)
                                .append(".");
                    }
                    b.append(columnName);
                }else if(!CheckUtil.isNull(expression)){
                    b.append(expression);
                }

                b.append(useAs ? Keywords.AS : null)
                        .append(columnAlias);
            }

            return b.build();
        }
    }
}
