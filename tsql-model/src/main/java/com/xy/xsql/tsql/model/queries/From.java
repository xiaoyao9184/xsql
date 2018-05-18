package com.xy.xsql.tsql.model.queries;

import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.queries.hints.JoinHint;
import com.xy.xsql.tsql.model.queries.hints.TableHint;
import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.element.Alias;
import com.xy.xsql.tsql.model.element.TableName;
import com.xy.xsql.tsql.model.statement.dml.Select;
import com.xy.xsql.tsql.model.elements.variables.LocalVariable;

import java.util.List;

/**
 * https://msdn.microsoft.com/en-us/library/ms177634.aspx
 *
 * Created by xiaoyao9184 on 2016/12/21.
 */
public class From implements Clause {

    //{ <table_source> } [ ,...n ]
    private List<TableSource> tableSourceList;


    public List<TableSource> getTableSourceList() {
        return tableSourceList;
    }

    public void setTableSourceList(List<TableSource> tableSourceList) {
        this.tableSourceList = tableSourceList;
    }

//    /**
//     * <table_source>
//     */
//    public static class TableSource implements Block  {
//
//        //table_or_view_name [ [ AS ] table_alias ]
//        private TableName tableName;
//        //derived_table
//        private Select derivedTable;
//        private boolean useAs;
//        private Alias<Void> tableAlias;
//        //<joined_table>
//        private JoinedTable joinedTable;
//
//
//        public TableName getTableName() {
//            return tableName;
//        }
//
//        public void setTableName(TableName table) {
//            this.tableName = table;
//        }
//
//        public Alias<Void> getTableAlias() {
//            return tableAlias;
//        }
//
//        public void setTableAlias(Alias<Void> tableAlias) {
//            this.tableAlias = tableAlias;
//        }
//
//        public JoinedTable getJoinedTable() {
//            return joinedTable;
//        }
//
//        public void setJoinedTable(JoinedTable joinedTable) {
//            this.joinedTable = joinedTable;
//        }
//
//        public Select getDerivedTable() {
//            return derivedTable;
//        }
//
//        public void setDerivedTable(Select derivedTable) {
//            this.derivedTable = derivedTable;
//        }
//
//
//        @Override
//        public List<Block> toBlockList() {
//            if(joinedTable == null){
//                ListBlockBuilder b = new ListBlockBuilder()
//                        .append(tableName);
//                b.append(useAs ? Keywords.AS : null)
//                        .append(tableAlias);
//                return b.build();
//            }
//
//            return joinedTable.toBlockList();
//        }
//    }

    public interface TableSource {

    }

    /**
     * table_or_view_name [ [ AS ] table_alias ]
     [ <tablesample_clause> ]
     [ WITH ( < table_hint > [ [ , ]...n ] ) ]
     */
    public static class BaseTable implements TableSource {

        //table_or_view_name [ [ AS ] table_alias ]
        private TableName tableName;
        private boolean useAs;
        private Alias<Void> tableAlias;
        //[ <tablesample_clause> ]
        private TableSample tableSample;
        //[ WITH ( < table_hint > [ [ , ]...n ] ) ]
        private List<TableHint> tableHintList;


        public TableName getTableName() {
            return tableName;
        }

        public void setTableName(TableName table) {
            this.tableName = table;
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

        public TableSample getTableSample() {
            return tableSample;
        }

        public void setTableSample(TableSample tableSample) {
            this.tableSample = tableSample;
        }

        public List<TableHint> getTableHintList() {
            return tableHintList;
        }

        public void setTableHintList(List<TableHint> tableHintList) {
            this.tableHintList = tableHintList;
        }
    }

    /**
     * TODO use derived_table to be interface, and rename this to DerivedTableWithAs
     * derived_table [ [ AS ] table_alias ] [ ( column_alias [ ,...n ] ) ]
     */
    public static class DerivedTable implements TableSource {

        private Select subQuery;
        private TableValueConstructor values;
        private boolean useAs;
        private Alias<Void> tableAlias;
        private List<Alias<Void>> columnAliass;

        public Select getSubQuery() {
            return subQuery;
        }

        public void setSubQuery(Select subQuery) {
            this.subQuery = subQuery;
        }

        public TableValueConstructor getValues() {
            return values;
        }

        public void setValues(TableValueConstructor values) {
            this.values = values;
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

        public List<Alias<Void>> getColumnAliass() {
            return columnAliass;
        }

        public void setColumnAliass(List<Alias<Void>> columnAliass) {
            this.columnAliass = columnAliass;
        }

    }

    /**
     * <joined_table>
     * <table_source> <join_type> <table_source> ON <search_condition>
     | <table_source> CROSS JOIN <table_source>
     | left_table_source { CROSS | OUTER } APPLY right_table_source
     | [ ( ] <joined_table> [ ) ]
     */
    public static class JoinedTable implements TableSource {
        private TableSource tableSource;
        private JoinType joinType;
        private TableSource tableSource2;
        private SearchCondition searchCondition;

        //
        private boolean useJoinOn = false;
        private boolean useCrossJoin = false;
        private boolean useCrossApply = false;
        private boolean useOuterApply = false;
        private boolean useParenthesis = false;


        public TableSource getTableSource() {
            return tableSource;
        }

        public void setTableSource(TableSource tableSource) {
            this.tableSource = tableSource;
        }

        public JoinType getJoinType() {
            return joinType;
        }

        public void setJoinType(JoinType joinType) {
            this.joinType = joinType;
        }

        public TableSource getTableSource2() {
            return tableSource2;
        }

        public void setTableSource2(TableSource tableSource2) {
            this.tableSource2 = tableSource2;
        }

        public SearchCondition getSearchCondition() {
            return searchCondition;
        }

        public void setSearchCondition(SearchCondition searchCondition) {
            this.searchCondition = searchCondition;
        }

        public boolean isUseJoinOn() {
            return useJoinOn;
        }

        public void setUseJoinOn(boolean useJoinOn) {
            this.useJoinOn = useJoinOn;
        }

        public boolean isUseCrossJoin() {
            return useCrossJoin;
        }

        public void setUseCrossJoin(boolean useCrossJoin) {
            this.useCrossJoin = useCrossJoin;
        }

        public boolean isUseCrossApply() {
            return useCrossApply;
        }

        public void setUseCrossApply(boolean useCrossApply) {
            this.useCrossApply = useCrossApply;
        }

        public boolean isUseOuterApply() {
            return useOuterApply;
        }

        public void setUseOuterApply(boolean useOuterApply) {
            this.useOuterApply = useOuterApply;
        }

        public boolean isUseParenthesis() {
            return useParenthesis;
        }

        public void setUseParenthesis(boolean useParenthesis) {
            this.useParenthesis = useParenthesis;
        }

    }

    /**
     * <join_type>
     *     <join_hint>
     */
    public static class JoinType {
        private JoinTypeKeywords keywords;
        private JoinHint joinHint;

        public JoinType(){

        }

        public JoinType(JoinTypeKeywords keywords){
            this.keywords = keywords;
        }

        public JoinType(JoinTypeKeywords joinType, JoinHint joinHint) {
            this.keywords = joinType;
            this.joinHint = joinHint;
        }

        public JoinTypeKeywords getKeywords() {
            return keywords;
        }

        public void setKeyword(JoinTypeKeywords keywords) {
            this.keywords = keywords;
        }

        public JoinHint getJoinHint() {
            return joinHint;
        }

        public void setJoinHint(JoinHint joinHint) {
            this.joinHint = joinHint;
        }
    }


    public enum JoinTypeKeywords {
        JOIN(Keywords.JOIN),
        INNER_JOIN(Keywords.INNER,Keywords.JOIN),
        INNER_REDUCE_JOIN(Keywords.INNER,Keywords.Key.REDUCE,Keywords.JOIN),
        INNER_REPLICATE_JOIN(Keywords.INNER,Keywords.Key.REPLICATE,Keywords.JOIN),
        INNER_REDISTRIBUTE_JOIN(Keywords.INNER,Keywords.Key.REDISTRIBUTE,Keywords.JOIN),
        LEFT_JOIN(Keywords.LEFT,Keywords.JOIN),
        RIGHT_JOIN(Keywords.RIGHT,Keywords.JOIN),
        FULL_JOIN(Keywords.FULL,Keywords.JOIN),
        LEFT_OUTER_JOIN(Keywords.LEFT,Keywords.OUTER,Keywords.JOIN),
        RIGHT_OUTER_JOIN(Keywords.RIGHT,Keywords.OUTER,Keywords.JOIN),
        FULL_OUTER_JOIN(Keywords.FULL,Keywords.OUTER,Keywords.JOIN);

        private Enum[] es;

        JoinTypeKeywords(Enum... elements){
            this.es = elements;
        }
    }

    /**
     * @variable [ [ AS ] table_alias ]
     */
    public static class VariableTable implements TableSource {

        private LocalVariable variable;
        private boolean useAs;
        private Alias<Void> tableAlias;

        public LocalVariable getVariable() {
            return variable;
        }

        public void setVariable(LocalVariable variable) {
            this.variable = variable;
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

    }

    /**
     * table_or_view_name FOR SYSTEM_TIME <system_time>
     */
    public static class BaseWithTimeTable implements TableSource {

        //table_or_view_name
        private TableName tableName;
        //[ FOR SYSTEM_TIME <system_time> ]
        private SystemTime systemTime;


        public TableName getTableName() {
            return tableName;
        }

        public void setTableName(TableName table) {
            this.tableName = table;
        }

        public SystemTime getSystemTime() {
            return systemTime;
        }

        public void setSystemTime(SystemTime systemTime) {
            this.systemTime = systemTime;
        }
    }

    /**
     * TABLESAMPLE [SYSTEM] ( sample_number [ PERCENT | ROWS ] )
     [ REPEATABLE ( repeat_seed ) ]
     */
    public static class TableSample {

        //[SYSTEM]
        private boolean useSystem;
        //table_or_view_name [ [ AS ] table_alias ]
        private NumberConstant sampleNumber;
        //[ PERCENT | ROWS ]
        private boolean usePercent;
        private boolean useRows;
        //[ REPEATABLE ( repeat_seed ) ]
        private NumberConstant repeatSeed;

        public boolean isUseSystem() {
            return useSystem;
        }

        public void setUseSystem(boolean useSystem) {
            this.useSystem = useSystem;
        }

        public NumberConstant getSampleNumber() {
            return sampleNumber;
        }

        public void setSampleNumber(NumberConstant sampleNumber) {
            this.sampleNumber = sampleNumber;
        }

        public boolean isUsePercent() {
            return usePercent;
        }

        public void setUsePercent(boolean usePercent) {
            this.usePercent = usePercent;
        }

        public boolean isUseRows() {
            return useRows;
        }

        public void setUseRows(boolean useRows) {
            this.useRows = useRows;
        }

        public NumberConstant getRepeatSeed() {
            return repeatSeed;
        }

        public void setRepeatSeed(NumberConstant repeatSeed) {
            this.repeatSeed = repeatSeed;
        }

    }

    /**
     *{
     AS OF <date_time>
     |  FROM <start_date_time> TO <end_date_time>
     |  BETWEEN <start_date_time> AND <end_date_time>
     |  CONTAINED IN (<start_date_time> , <end_date_time>)
     |  ALL
     }
     */
    public static class SystemTime {

        //AS OF <date_time>
        private DateTime dateTime;
        //FROM <start_date_time> TO <end_date_time>
        //BETWEEN <start_date_time> AND <end_date_time>
        //CONTAINED IN (<start_date_time> , <end_date_time>)
        private boolean useFrom;
        private boolean useBetween;
        private boolean useContained;
        private DateTime startDateTime;
        private DateTime endDateTime;

        //ALL
        private boolean useAll;

        public DateTime getDateTime() {
            return dateTime;
        }

        public void setDateTime(DateTime dateTime) {
            this.dateTime = dateTime;
        }

        public boolean isUseFrom() {
            return useFrom;
        }

        public void setUseFrom(boolean useFrom) {
            this.useFrom = useFrom;
        }

        public boolean isUseBetween() {
            return useBetween;
        }

        public void setUseBetween(boolean useBetween) {
            this.useBetween = useBetween;
        }

        public boolean isUseContained() {
            return useContained;
        }

        public void setUseContained(boolean useContained) {
            this.useContained = useContained;
        }

        public DateTime getStartDateTime() {
            return startDateTime;
        }

        public void setStartDateTime(DateTime startDateTime) {
            this.startDateTime = startDateTime;
        }

        public DateTime getEndDateTime() {
            return endDateTime;
        }

        public void setEndDateTime(DateTime endDateTime) {
            this.endDateTime = endDateTime;
        }

        public boolean isUseAll() {
            return useAll;
        }

        public void setUseAll(boolean useAll) {
            this.useAll = useAll;
        }
    }

    /**
     * <date_time_literal> | @date_time_variable
     */
    public static class DateTime {

        //date_time_literal
        private StringConstant dateTimeLiteral;
        //@date_time_variable
        private LocalVariable dateTimeVariable;

        public DateTime(StringConstant dateTime) {
            this.dateTimeLiteral = dateTime;
        }

        public DateTime(LocalVariable dateTime) {
            this.dateTimeVariable = dateTime;
        }

        public StringConstant getDateTimeLiteral() {
            return dateTimeLiteral;
        }

        public void setDateTimeLiteral(StringConstant dateTimeLiteral) {
            this.dateTimeLiteral = dateTimeLiteral;
        }

        public LocalVariable getDateTimeVariable() {
            return dateTimeVariable;
        }

        public void setDateTimeVariable(LocalVariable dateTimeVariable) {
            this.dateTimeVariable = dateTimeVariable;
        }
    }

}
