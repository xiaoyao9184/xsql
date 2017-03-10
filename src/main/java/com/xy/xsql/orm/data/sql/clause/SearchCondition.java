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


    //{ [ NOT ] <predicate> | ( <search_condition> ) }
    private boolean useNot;
    private Predicate predicate;
    private SearchCondition searchCondition;

    //[ { AND | OR } [ NOT ] { <predicate> | ( <search_condition> ) } ]
    //[ ,...n ]
    private List<AndOrItem> andOrList;


    public boolean isUseNot() {
        return useNot;
    }

    public void setUseNot(boolean useNot) {
        this.useNot = useNot;
    }

    public Predicate getPredicate() {
        return predicate;
    }

    public void setPredicate(Predicate predicate) {
        this.predicate = predicate;
    }

    public SearchCondition getSearchCondition() {
        return searchCondition;
    }

    public void setSearchCondition(SearchCondition searchCondition) {
        this.searchCondition = searchCondition;
    }

    public List<AndOrItem> getAndOrList() {
        return andOrList;
    }

    public void setAndOrList(List<AndOrItem> otherSearchCondition) {
        this.andOrList = otherSearchCondition;
    }


    @Override
    public List<Element> toElementList() {
        ListElementBuilder b = new ListElementBuilder();

        b.append(useNot ? GrammarEnum.NOT : null)
                .append(this.predicate != null ? predicate : searchCondition);

        if(!CheckUtil.isNullOrEmpty(this.andOrList)){
            for (AndOrItem andOrItem : this.andOrList) {
                b.append(andOrItem.toElementList(),null);
            }
        }
        return b.build(null);
    }


    /**
     * <predicate>
     */
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

        public Expression getExpression() {
            return expression;
        }

        public void setExpression(Expression expression) {
            this.expression = expression;
        }

        public boolean isNotOperator() {
            return NotOperator;
        }

        public void setNotOperator(boolean notOperator) {
            NotOperator = notOperator;
        }

        public OperatorEnum getOperatorEnum() {
            return operatorEnum;
        }

        public void setOperatorEnum(OperatorEnum operatorEnum) {
            this.operatorEnum = operatorEnum;
        }

        public Expression getOperatorExpression() {
            return operatorExpression;
        }

        public void setOperatorExpression(Expression operatorExpression) {
            this.operatorExpression = operatorExpression;
        }

        public Expression getAndExpression() {
            return andExpression;
        }

        public void setAndExpression(Expression andExpression) {
            this.andExpression = andExpression;
        }

        public ASA getAsa() {
            return asa;
        }

        public void setAsa(ASA asa) {
            this.asa = asa;
        }

        public enum ASA implements Element {
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

    /**
     * <predicate>
     * { AND | OR } [ NOT ] { <predicate> | ( <search_condition> )
     */
    public static class AndOrItem implements ElementList {

        private boolean useAnd;
        private boolean useNot;

        private Predicate predicate;
        private SearchCondition searchCondition;


        public boolean isUseAnd() {
            return useAnd;
        }

        public void setUseAnd(boolean useAnd) {
            this.useAnd = useAnd;
        }

        public boolean isUseNot() {
            return useNot;
        }

        public void setUseNot(boolean useNot) {
            this.useNot = useNot;
        }

        public Predicate getPredicate() {
            return predicate;
        }

        public void setPredicate(Predicate predicate) {
            this.predicate = predicate;
        }

        public SearchCondition getSearchCondition() {
            return searchCondition;
        }

        public void setSearchCondition(SearchCondition searchCondition) {
            this.searchCondition = searchCondition;
        }

        @Override
        public List<Element> toElementList() {
            ListElementBuilder b = new ListElementBuilder()
                    .withDelimiter(OtherEnum.SPACE);

            b.append(useAnd ? GrammarEnum.AND : GrammarEnum.OR);

            b.append(useNot ? GrammarEnum.NOT : null)
                    .append(this.predicate != null ? predicate : searchCondition);

            return b.build(null);
        }
    }
}
