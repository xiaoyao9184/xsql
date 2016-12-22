package com.xy.xsql.orm.data.sql.clause;

import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.ElementList;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;
import com.xy.xsql.orm.data.sql.element.OperatorEnum;
import com.xy.xsql.orm.data.sql.element.OtherEnum;
import com.xy.xsql.orm.util.CheckUtil;

import java.util.List;

/**
 * https://msdn.microsoft.com/en-us/library/ms173545.aspx
 *
 -- Syntax for SQL Server and Azure SQL Database

 <search_condition> ::=
 { [ NOT ] <predicate> | ( <search_condition> ) }
 [ { AND | OR } [ NOT ] { <predicate> | ( <search_condition> ) } ]
 [ ,...n ]

 <predicate> ::=
 { expression { = | < > | ! = | > | > = | ! > | < | < = | ! < } expression
 | string_expression [ NOT ] LIKE string_expression
 [ ESCAPE 'escape_character' ]
 | expression [ NOT ] BETWEEN expression AND expression
 | expression IS [ NOT ] NULL
 | CONTAINS
 ( { column | * } , '<contains_search_condition>' )
 | FREETEXT ( { column | * } , 'freetext_string' )
 | expression [ NOT ] IN ( subquery | expression [ ,...n ] )
 | expression { = | < > | ! = | > | > = | ! > | < | < = | ! < }
 { ALL | SOME | ANY} ( subquery )
 | EXISTS ( subquery )     }
 *
 * Created by xiaoyao9184 on 2016/12/20.
 */
public class SearchCondition implements ElementList {

    private boolean useAnd;
    private boolean useOr;
    private boolean useNot;
    //
    private Predicate predicate;
    private SearchCondition searchCondition;

    List<SearchCondition> otherSearchCondition;


    @Override
    public List<Element> toElementList() {
        ListElementBuilder b = new ListElementBuilder();

        if(useAnd) {
            b.append(OtherEnum.SPACE)
                    .append(GrammarEnum.AND);
        }else if(useOr){
            b.append(OtherEnum.SPACE)
                    .append(GrammarEnum.OR);
        }

        if(useNot) {
            b.append(OtherEnum.SPACE)
                    .append(GrammarEnum.NOT);
        }

        if(this.predicate != null){
            b.append(predicate);
        }else{
            b.append(searchCondition);
        }

        if(!CheckUtil.isNullOrEmpty(this.otherSearchCondition)){
            for (SearchCondition searchCondition : this.otherSearchCondition) {
                b.append(searchCondition.toElementList(),null);
            }
        }
        return b.build(null);
    }

    public static class Predicate implements ElementList {

        private Expression expression;
        private boolean NotOperator;
        private OperatorEnum operatorEnum;
        //
        private Expression operatorExpression;
        private Expression andExpression;

        //{ ALL | SOME | ANY}
        private ASA asa;

        //subquery
        //TODO

        @Override
        public List<Element> toElementList() {
            return null;
        }

        enum ASA implements Element {
            ALL("ALL"),
            SOME("SOME"),
            ANY("ANY");

            private String string;

            ASA(String string){
                this.string = string;
            }

            @Override
            public String toString(){
                return this.string;
            }
        }
    }
}
