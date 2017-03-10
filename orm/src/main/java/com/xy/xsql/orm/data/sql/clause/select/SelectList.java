package com.xy.xsql.orm.data.sql.clause.select;

import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.ElementList;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.clause.From;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;
import com.xy.xsql.orm.data.sql.element.OperatorEnum;
import com.xy.xsql.orm.data.sql.element.OtherEnum;
import com.xy.xsql.orm.util.CheckUtil;

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
public class SelectList implements ElementList {
    private List<SelectItem> list;


    public List<SelectItem> getList() {
        return list;
    }

    public void setList(List<SelectItem> list) {
        this.list = list;
    }


    @Override
    public List<Element> toElementList() {
        return new ListElementBuilder()
                .append(list,OtherEnum.DELIMITER)
                .build();
    }


    public static class SelectItem implements ElementList {
        //*
        private boolean useAll;

        //{ table_name | view_name | table_alias }.*
        private boolean useTableAll;
        private String tableViewName;

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
        private String columnName;
        //expression
        private Expression expression;
        //[ [ AS ] column_alias ]
        private boolean useAs;
        private String columnAlias;

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

        public String getTableViewName() {
            return tableViewName;
        }

        public void setTableViewName(String tableViewName) {
            this.tableViewName = tableViewName;
        }

        public String getColumnName() {
            return columnName;
        }

        public void setColumnName(String columnName) {
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

        public String getColumnAlias() {
            return columnAlias;
        }

        public void setColumnAlias(String columnAlias) {
            this.columnAlias = columnAlias;
        }

        public boolean isUseEQ() {
            return useEQ;
        }

        public void setUseEQ(boolean useEQ) {
            this.useEQ = useEQ;
        }


        @Override
        public List<Element> toElementList() {
            ListElementBuilder b = new ListElementBuilder();

            if(useAll){
                b.append("*");
            } else if(useTableAll){
                b.append(tableViewName)
                        .append(".")
                        .append("*");
            } else if(useEQ){
                b.append(columnAlias)
                        .append(OperatorEnum.EQUAL)
                        .append(expression);
            } else {
                if(!CheckUtil.isNullOrEmpty(columnName)){
                    if(!CheckUtil.isNullOrEmpty(tableViewName)){
                        b.append(tableViewName)
                                .append(".");
                    }
                    b.append(columnName);
                }else if(!CheckUtil.isNull(expression)){
                    b.append(expression);
                }

                b.append(useAs ? GrammarEnum.AS : null)
                        .append(columnAlias);
            }

            return b.build();
        }
    }
}
