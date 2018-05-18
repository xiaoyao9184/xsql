package com.xy.xsql.tsql.model.queries;

import com.xy.xsql.tsql.model.queries.predicates.Predicate;

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
public class SearchCondition implements Clause {

    //{ [ NOT ] <predicate> | ( <search_condition> ) }
    private boolean useNot;
    private Predicate predicate;
    private SearchCondition searchCondition;

    //[ { AND | OR } [ NOT ] { <predicate> | ( <search_condition> ) } ]
    //[ ,...n ]
    private List<AndOrNotItem> andOrList;


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

    public List<AndOrNotItem> getAndOrList() {
        return andOrList;
    }

    public void setAndOrList(List<AndOrNotItem> otherSearchCondition) {
        this.andOrList = otherSearchCondition;
    }

    /**
     * { AND | OR } [ NOT ] { <predicate> | ( <search_condition> )
     */
    public static class AndOrNotItem {

        private boolean useAnd;
        private boolean useNot;

        private Predicate predicate;
        private SearchCondition searchCondition;

        public AndOrNotItem() {
        }

        public AndOrNotItem(Predicate predicate) {
            this.predicate = predicate;
        }

        public AndOrNotItem(Predicate predicate, boolean useAnd) {
            this.predicate = predicate;
            this.useAnd = useAnd;
        }


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

    }
}
