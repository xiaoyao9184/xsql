package com.xy.xsql.orm.data.sql.statements.dml;

import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.ElementList;
import com.xy.xsql.orm.data.sql.clause.SearchCondition;
import com.xy.xsql.orm.data.sql.clause.Top;
import com.xy.xsql.orm.data.sql.clause.hints.TableHintLimited;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;
import com.xy.xsql.orm.data.sql.element.OtherEnum;
import com.xy.xsql.orm.data.sql.element.UnknownString;
import com.xy.xsql.orm.data.sql.element.info.Alias;
import com.xy.xsql.orm.data.sql.element.info.Table;
import com.xy.xsql.orm.data.sql.element.info.TableName;
import com.xy.xsql.orm.data.sql.sentence.BaseElementsSentence;
import com.xy.xsql.orm.data.sql.sentence.CustomizeSentence;

import java.util.EnumSet;
import java.util.List;

/**
 *
 * https://msdn.microsoft.com/en-us/library/bb510625.aspx
 *
 [ WITH <common_table_expression> [,...n] ]
 MERGE
 [ TOP ( expression ) [ PERCENT ] ]
 [ INTO ] <target_table> [ WITH ( <merge_hint> ) ] [ [ AS ] table_alias ]
 USING <table_source>
 ON <merge_search_condition>
 [ WHEN MATCHED [ AND <clause_search_condition> ]
 THEN <merge_matched> ] [ ...n ]
 [ WHEN NOT MATCHED [ BY TARGET ] [ AND <clause_search_condition> ]
 THEN <merge_not_matched> ]
 [ WHEN NOT MATCHED BY SOURCE [ AND <clause_search_condition> ]
 THEN <merge_matched> ] [ ...n ]
 [ <output_clause> ]
 [ OPTION ( <query_hint> [ ,...n ] ) ]
 ;

 <target_table> ::=
 {
 [ database_name . schema_name . | schema_name . ]
 target_table
 }

 <merge_hint>::=
 {
 { [ <table_hint_limited> [ ,...n ] ]
 [ [ , ] INDEX ( index_val [ ,...n ] ) ] }
 }

 <table_source> ::=
 {
 table_or_view_name [ [ AS ] table_alias ] [ <tablesample_clause> ]
 [ WITH ( table_hint [ [ , ]...n ] ) ]
 | rowset_function [ [ AS ] table_alias ]
 [ ( bulk_column_alias [ ,...n ] ) ]
 | user_defined_function [ [ AS ] table_alias ]
 | OPENXML <openxml_clause>
 | derived_table [ AS ] table_alias [ ( column_alias [ ,...n ] ) ]
 | <joined_table>
 | <pivoted_table>
 | <unpivoted_table>
 }

 <merge_search_condition> ::=
 <search_condition>

 <merge_matched>::=
 { UPDATE SET <set_clause> | DELETE }

 <set_clause>::=
 SET
 { column_name = { expression | DEFAULT | NULL }
 | { udt_column_name.{ { property_name = expression
 | field_name = expression }
 | method_name ( argument [ ,...n ] ) }
 }
 | column_name { .WRITE ( expression , @Offset , @Length ) }
 | @variable = expression
 | @variable = column = expression
 | column_name { += | -= | *= | /= | %= | &= | ^= | |= } expression
 | @variable { += | -= | *= | /= | %= | &= | ^= | |= } expression
 | @variable = column { += | -= | *= | /= | %= | &= | ^= | |= } expression
 } [ ,...n ]

 <merge_not_matched>::=
 {
 INSERT [ ( column_list ) ]
 { VALUES ( values_list )
 | DEFAULT VALUES }
 }

 <clause_search_condition> ::=
 <search_condition>

 <search condition> ::=
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
 ( { column | * } , '< contains_search_condition >' )
 | FREETEXT ( { column | * } , 'freetext_string' )
 | expression [ NOT ] IN ( subquery | expression [ ,...n ] )
 | expression { = | < > | ! = | > | > = | ! > | < | < = | ! < }
 { ALL | SOME | ANY} ( subquery )
 | EXISTS ( subquery ) }

 <output_clause>::=
 {
 [ OUTPUT <dml_select_list> INTO { @table_variable | output_table }
 [ (column_list) ] ]
 [ OUTPUT <dml_select_list> ]
 }

 <dml_select_list>::=
 { <column_name> | scalar_expression }
 [ [AS] column_alias_identifier ] [ ,...n ]

 <column_name> ::=
 { DELETED | INSERTED | from_table_name } . { * | column_name }
 | $action

 *
 * Created by xiaoyao9184 on 2016/10/15.
 */
public class Merge extends CustomizeSentence {

    //TOP
    private Top top;

    //INTO
    private boolean useInto;
    //<target_table>
    private Table targetTable;
    //WITH ( <merge_hint> )
    private MergeHint mergeHint;
    //[ [ AS ] table_alias ]
    private boolean useAs;
    private Alias<Void> tableAlias;

    //USING <table_source>
    private TableName tableSource;

    //ON <merge_search_condition>
    private SearchCondition mergeSearchCondition;

    //[ WHEN MATCHED [ AND <clause_search_condition> ]
    //THEN <merge_matched> ] [ ...n ]
    private List<MatchedWhenThen> matchedWhenThenList;
    //[ WHEN NOT MATCHED [ BY TARGET ] [ AND <clause_search_condition> ]
    //THEN <merge_not_matched> ]
    private List<MatchedWhenThen> notMatchedWhenThenTargetList;
    //[ WHEN NOT MATCHED BY SOURCE [ AND <clause_search_condition> ]
    //THEN <merge_matched> ] [ ...n ]
    private List<MatchedWhenThen> notMatchedWhenThenSourceList;

    public Top getTop() {
        return top;
    }

    public void setTop(Top top) {
        this.top = top;
    }

    public boolean isUseInto() {
        return useInto;
    }

    public void setUseInto(boolean useInto) {
        this.useInto = useInto;
    }

    public Table getTargetTable() {
        return targetTable;
    }

    public void setTargetTable(Table targetTable) {
        this.targetTable = targetTable;
    }

    public MergeHint getMergeHint() {
        return mergeHint;
    }

    public void setMergeHint(MergeHint mergeHint) {
        this.mergeHint = mergeHint;
    }

    public boolean isUseAs() {
        return useAs;
    }

    public void setUseAs(boolean useAs) {
        this.useAs = useAs;
    }

    public Alias<Void> getTableAlias() {
        return tableAlias;
    }

    public void setTableAlias(Alias<Void> tableAlias) {
        this.tableAlias = tableAlias;
    }

    public TableName getTableSource() {
        return tableSource;
    }

    public void setTableSource(TableName tableSource) {
        this.tableSource = tableSource;
    }

    public SearchCondition getMergeSearchCondition() {
        return mergeSearchCondition;
    }

    public void setMergeSearchCondition(SearchCondition mergeSearchCondition) {
        this.mergeSearchCondition = mergeSearchCondition;
    }

    public List<MatchedWhenThen> getMatchedWhenThenList() {
        return matchedWhenThenList;
    }

    public void setMatchedWhenThenList(List<MatchedWhenThen> matchedWhenThenList) {
        this.matchedWhenThenList = matchedWhenThenList;
    }

    public List<MatchedWhenThen> getNotMatchedWhenThenTargetList() {
        return notMatchedWhenThenTargetList;
    }

    public void setNotMatchedWhenThenTargetList(List<MatchedWhenThen> notMatchedWhenThenTargetList) {
        this.notMatchedWhenThenTargetList = notMatchedWhenThenTargetList;
    }

    public List<MatchedWhenThen> getNotMatchedWhenThenSourceList() {
        return notMatchedWhenThenSourceList;
    }

    public void setNotMatchedWhenThenSourceList(List<MatchedWhenThen> notMatchedWhenThenSourceList) {
        this.notMatchedWhenThenSourceList = notMatchedWhenThenSourceList;
    }


    @Override
    public BaseElementsSentence toBaseElementsSentence() {
        ListElementBuilder b = new ListElementBuilder()
                .append(GrammarEnum.UPDATE);

        //[ TOP ( expression ) [ PERCENT ] ]
        b.append(OtherEnum.SPACE)
                .append(top.toElementList(),null);

        //[ INTO ] <target_table> [ WITH ( <merge_hint> ) ] [ [ AS ] table_alias ]
        b.append(useInto ? GrammarEnum.INTO : null)
                .append(targetTable)
                .append(mergeHint)
                .append(useAs ? GrammarEnum.AS : null)
                .append(tableAlias);

        //USING <table_source>
        b.append(GrammarEnum.USING)
                .append(tableSource);

        //ON <merge_search_condition>
        b.append(GrammarEnum.ON)
                .append(mergeSearchCondition);

        //[ WHEN MATCHED [ AND <clause_search_condition> ]
        //THEN <merge_matched> ] [ ...n ]
        if(matchedWhenThenList != null){
            int i = 0;
            for (MatchedWhenThen matchedWhenThen: matchedWhenThenList) {
                b.append(i == 0 ? null : OtherEnum.DELIMITER)
                        .append(matchedWhenThen);
                i++;
            }
        }

        //[ WHEN NOT MATCHED [ BY TARGET ] [ AND <clause_search_condition> ]
        //THEN <merge_not_matched> ]
        if(notMatchedWhenThenTargetList != null){
            int i = 0;
            for (MatchedWhenThen matchedWhenThen: notMatchedWhenThenTargetList) {
                b.append(i == 0 ? null : OtherEnum.DELIMITER)
                        .append(matchedWhenThen);
                i++;
            }
        }

        //[ WHEN NOT MATCHED BY SOURCE [ AND <clause_search_condition> ]
        //THEN <merge_matched> ] [ ...n ]
        if(notMatchedWhenThenSourceList != null){
            int i = 0;
            for (MatchedWhenThen matchedWhenThen: notMatchedWhenThenSourceList) {
                b.append(i == 0 ? null : OtherEnum.DELIMITER)
                        .append(matchedWhenThen);
                i++;
            }
        }

        return new BaseElementsSentence(b.build(null));
    }

    /**
     * <merge_hint>
     */
    public class MergeHint implements ElementList {
        /*
        { [ <table_hint_limited> [ ,...n ] ]
        [ [ , ] INDEX ( index_val [ ,...n ] ) ] }
        */
        private EnumSet<TableHintLimited> tableHintLimitedList;
        private boolean useDelimiter;
        private List<UnknownString> indexValList;

        public EnumSet<TableHintLimited> getTableHintLimitedList() {
            return tableHintLimitedList;
        }

        public void setTableHintLimitedList(EnumSet<TableHintLimited> tableHintLimitedList) {
            this.tableHintLimitedList = tableHintLimitedList;
        }

        public boolean isUseDelimiter() {
            return useDelimiter;
        }

        public void setUseDelimiter(boolean useDelimiter) {
            this.useDelimiter = useDelimiter;
        }

        public List<UnknownString> getIndexValList() {
            return indexValList;
        }

        public void setIndexValList(List<UnknownString> indexValList) {
            this.indexValList = indexValList;
        }

        @Override
        public List<Element> toElementList() {
            return new ListElementBuilder()
                    .append(tableHintLimitedList,OtherEnum.DELIMITER)
                    .append(useDelimiter ? OtherEnum.DELIMITER : null)
                    .append(GrammarEnum.INDEX)
                    .append(OtherEnum.GROUP_START)
                    .append(indexValList,OtherEnum.DELIMITER)
                    .append(OtherEnum.GROUP_END)
                    .build();
        }
    }

    /**
     * WHEN MATCHED
     * WHEN NOT MATCHED
     */
    public class MatchedWhenThen implements ElementList {
        private boolean useNot;
        private boolean useByTarget;
        private SearchCondition clauseSearchCondition;
        private MergeMatched mergeMatched;
        private MergeNotMatched mergeNotMatched;


        public boolean isUseNot() {
            return useNot;
        }

        public void setUseNot(boolean useNot) {
            this.useNot = useNot;
        }

        public boolean isUseByTarget() {
            return useByTarget;
        }

        public void setUseByTarget(boolean useByTarget) {
            this.useByTarget = useByTarget;
        }

        public SearchCondition getClauseSearchCondition() {
            return clauseSearchCondition;
        }

        public void setClauseSearchCondition(SearchCondition clauseSearchCondition) {
            this.clauseSearchCondition = clauseSearchCondition;
        }

        public MergeMatched getMergeMatched() {
            return mergeMatched;
        }

        public void setMergeMatched(MergeMatched mergeMatched) {
            this.mergeMatched = mergeMatched;
        }

        public MergeNotMatched getMergeNotMatched() {
            return mergeNotMatched;
        }

        public void setMergeNotMatched(MergeNotMatched mergeNotMatched) {
            this.mergeNotMatched = mergeNotMatched;
        }


        @Override
        public List<Element> toElementList() {
            ListElementBuilder b = new ListElementBuilder()
                    .withDelimiter(OtherEnum.SPACE)
                    .append(GrammarEnum.WHEN)
                    .append(useNot ? GrammarEnum.NOT : null)
                    .append(GrammarEnum.MATCHED)
                    .append(useByTarget ? GrammarEnum.BY : null)
                    .append(useByTarget ? GrammarEnum.TARGET : null)
                    .append(clauseSearchCondition != null ? GrammarEnum.AND : null)
                    .append(clauseSearchCondition != null ? clauseSearchCondition : null)
                    .append(GrammarEnum.THEN)
                    .append(useNot ? mergeNotMatched : mergeMatched);
            return b.build();
        }
    }

    /**
     * <merge_matched>
     */
    public class MergeMatched implements ElementList {
        //{ UPDATE SET <set_clause> | DELETE }
        private boolean useSet;
        private Update update;


        public boolean isUseSet() {
            return useSet;
        }

        public void setUseSet(boolean useSet) {
            this.useSet = useSet;
        }

        public Update getUpdate() {
            return update;
        }

        public void setUpdate(Update update) {
            this.update = update;
        }


        @Override
        public List<Element> toElementList() {
            ListElementBuilder b = new ListElementBuilder()
                    .withDelimiter(OtherEnum.SPACE);
            if(useSet){
                b.append(update.toBaseElementsSentence().getData());
            }else{
                b.append(GrammarEnum.DELETE);
            }
            return b.build();
        }
    }

    /**
     * <merge_not_matched>
     */
    public class MergeNotMatched implements ElementList {
        //INSERT [ ( column_list ) ]
        //{ VALUES ( values_list )
        //        | DEFAULT VALUES }

        private Insert insert;

        public Insert getInsert() {
            return insert;
        }

        public void setInsert(Insert insert) {
            this.insert = insert;
        }

        @Override
        public List<Element> toElementList() {
            ListElementBuilder b = new ListElementBuilder()
                    .withDelimiter(OtherEnum.SPACE)
                    .append(insert.toBaseElementsSentence().getData());
            return b.build();
        }
    }
}
