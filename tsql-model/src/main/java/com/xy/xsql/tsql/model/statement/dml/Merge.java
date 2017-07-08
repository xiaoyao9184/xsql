package com.xy.xsql.tsql.model.statement.dml;

import com.xy.xsql.tsql.model.clause.*;
import com.xy.xsql.tsql.model.clause.hints.TableHintLimited;
import com.xy.xsql.tsql.model.datatype.StringConstant;
import com.xy.xsql.tsql.model.element.Alias;
import com.xy.xsql.tsql.model.element.ColumnName;
import com.xy.xsql.tsql.model.element.TableName;
import com.xy.xsql.tsql.model.statement.Statement;

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
public class Merge implements Statement {
    //<WITH Clause>
    private With with;
    //<TOP Clause>
    private Top top;

    //INTO
    private boolean useInto;
    //<target_table>
    private TableName targetTable;
    //WITH ( <merge_hint> )
    private MergeHint mergeHint;
    //[ [ AS ] table_alias ]
    private boolean useAs;
    private Alias<Void> tableAlias;

    //USING <table_source>
    private From.TableSource tableSource;

    //ON <merge_search_condition>
    private SearchCondition mergeSearchCondition;

    //[ WHEN MATCHED [ AND <clause_search_condition> ]
    //THEN <merge_matched> ] [ ...n ]
    private List<MatchedWhenThen> matchedWhenThenList;
    //[ WHEN NOT MATCHED [ BY TARGET ] [ AND <clause_search_condition> ]
    //THEN <merge_not_matched> ]
    private NotMatchedWhenThen notMatchedWhenThenTarget;
    //[ WHEN NOT MATCHED BY SOURCE [ AND <clause_search_condition> ]
    //THEN <merge_matched> ] [ ...n ]
    private List<MatchedWhenThen> notMatchedWhenThenSourceList;

    //<OUTPUT Clause>
    private Output output;

    //<OPTION Clause>
    private Option option;


    public With getWith() {
        return with;
    }

    public void setWith(With with) {
        this.with = with;
    }

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

    public TableName getTargetTable() {
        return targetTable;
    }

    public void setTargetTable(TableName targetTable) {
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

    public From.TableSource getTableSource() {
        return tableSource;
    }

    public void setTableSource(From.TableSource tableSource) {
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

    public NotMatchedWhenThen getNotMatchedWhenThenTarget() {
        return notMatchedWhenThenTarget;
    }

    public void setNotMatchedWhenThenTarget(NotMatchedWhenThen notMatchedWhenThenTarget) {
        this.notMatchedWhenThenTarget = notMatchedWhenThenTarget;
    }

    public List<MatchedWhenThen> getNotMatchedWhenThenSourceList() {
        return notMatchedWhenThenSourceList;
    }

    public void setNotMatchedWhenThenSourceList(List<MatchedWhenThen> notMatchedWhenThenSourceList) {
        this.notMatchedWhenThenSourceList = notMatchedWhenThenSourceList;
    }

    public Output getOutput() {
        return output;
    }

    public void setOutput(Output output) {
        this.output = output;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }


    /**
     * <merge_hint>
     */
    public static class MergeHint {
        /*
        { [ <table_hint_limited> [ ,...n ] ]
        [ [ , ] INDEX ( index_val [ ,...n ] ) ] }
        */
        private List<TableHintLimited> tableHintLimitedList;
        private List<StringConstant> indexValues;

        public List<TableHintLimited> getTableHintLimitedList() {
            return tableHintLimitedList;
        }

        public void setTableHintLimitedList(List<TableHintLimited> tableHintLimitedList) {
            this.tableHintLimitedList = tableHintLimitedList;
        }

        public List<StringConstant> getIndexValues() {
            return indexValues;
        }

        public void setIndexValues(List<StringConstant> indexValues) {
            this.indexValues = indexValues;
        }
    }

    /**
     * WHEN MATCHED
     * WHEN NOT MATCHED
     */
    public static class MatchedWhenThen {
        protected boolean useNot;

        private boolean useByTarget;
        private SearchCondition clauseSearchCondition;
        private MergeMatched mergeMatched;
        private MergeNotMatched mergeNotMatched;


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

    }

    /**
     *
     */
    public static class NotMatchedWhenThen extends MatchedWhenThen {

        public NotMatchedWhenThen() {
            this.useNot = true;
        }
    }

    /**
     * <merge_matched>
     */
    public static class MergeMatched {
        //{ UPDATE SET <set_clause> | DELETE }
        private boolean useSet;
        private List<Update.SetItem> sets;


        public boolean isUseSet() {
            return useSet;
        }

        public void setUseSet(boolean useSet) {
            this.useSet = useSet;
        }

        public List<Update.SetItem> getSets() {
            return sets;
        }

        public void setSets(List<Update.SetItem> sets) {
            this.sets = sets;
        }

    }

    /**
     * <merge_not_matched>
     */
    public static class MergeNotMatched {
        //INSERT [ ( column_list ) ]
        //{ VALUES ( values_list )
        //        | DEFAULT VALUES }
        private List<ColumnName> columns;
        private TableValueConstructor values;
        private boolean useDefaultValues;


        public List<ColumnName> getColumns() {
            return columns;
        }

        public void setColumns(List<ColumnName> columns) {
            this.columns = columns;
        }

        public TableValueConstructor getValues() {
            return values;
        }

        public void setValues(TableValueConstructor values) {
            this.values = values;
        }

        public boolean isUseDefaultValues() {
            return useDefaultValues;
        }

        public void setUseDefaultValues(boolean useDefaultValues) {
            this.useDefaultValues = useDefaultValues;
        }

    }
}
