package com.xy.xsql.orm.data.sql.clause;

import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.ElementList;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;
import com.xy.xsql.orm.data.sql.element.OtherEnum;
import com.xy.xsql.orm.data.sql.element.info.Table;
import com.xy.xsql.orm.data.sql.statements.dml.Select;

import java.util.Arrays;
import java.util.List;

/**
 * https://msdn.microsoft.com/en-us/library/ms177634.aspx
 *
 * Created by xiaoyao9184 on 2016/12/21.
 */
public class From implements ElementList {

    //{ <table_source> } [ ,...n ]
    private List<TableSource> tableSourceList;


    public List<TableSource> getTableSourceList() {
        return tableSourceList;
    }

    public void setTableSourceList(List<TableSource> tableSourceList) {
        this.tableSourceList = tableSourceList;
    }


    @Override
    public List<Element> toElementList() {
        ListElementBuilder b = new ListElementBuilder()
                .append(GrammarEnum.FROM);
        for (TableSource table: getTableSourceList()) {
            b.append(table.toElementList(),OtherEnum.DELIMITER);
        }
        return b.build();
    }

    /**
     * <table_source>
     */
    public static class TableSource implements ElementList  {

        //table_or_view_name
        private Table table;
        //derived_table
        private Select derivedTable;
        private boolean useTableAlias;
        //<joined_table>
        private JoinedTable joinedTable;


        public Table getTable() {
            return table;
        }

        public void setTable(Table table) {
            this.table = table;
        }

        public boolean isUseTableAlias() {
            return useTableAlias;
        }

        public void setUseTableAlias(boolean useTableAlias) {
            this.useTableAlias = useTableAlias;
        }

        public JoinedTable getJoinedTable() {
            return joinedTable;
        }

        public void setJoinedTable(JoinedTable joinedTable) {
            this.joinedTable = joinedTable;
        }

        public Select getDerivedTable() {
            return derivedTable;
        }

        public void setDerivedTable(Select derivedTable) {
            this.derivedTable = derivedTable;
        }


        @Override
        public List<Element> toElementList() {
            if(joinedTable == null){
                ListElementBuilder b = new ListElementBuilder()
                        .append(table);
                if (useTableAlias) {
                    b.append(GrammarEnum.AS)
                            .append(table.getAliasName());
                }
                return b.build();
            }

            return joinedTable.toElementList();
        }
    }

    /**
     * <joined_table>
     */
    public static class JoinedTable implements ElementList {
        private TableSource tableSource;
        private JoinType joinType;
        private TableSource tableSource2;
        private SearchCondition searchCondition;

        //
        private boolean useJoinOn = true;
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

        @Override
        public List<Element> toElementList() {
            ListElementBuilder b = new ListElementBuilder()
                    .withDelimiter(OtherEnum.SPACE);
            if(isUseParenthesis()){
                b.append(OtherEnum.GROUP_START);
            }
            if(isUseJoinOn()){
                b.append(getTableSource())
                        .append(getJoinType())
                        .append(getTableSource2())
                        .append(GrammarEnum.ON)
                        .append(getSearchCondition());
            }else if(isUseCrossJoin()){
                b.append(getTableSource())
                        .append(GrammarEnum.CROSS)
                        .append(GrammarEnum.JOIN)
                        .append(getTableSource2());
            }else if(isUseCrossApply()){
                b.append(getTableSource())
                        .append(GrammarEnum.CROSS)
                        .append(GrammarEnum.APPLY)
                        .append(getTableSource2());
            }else if(isUseOuterApply()){
                b.append(getTableSource())
                        .append(GrammarEnum.OUTER)
                        .append(GrammarEnum.APPLY)
                        .append(getTableSource2());
            }

            if(isUseParenthesis()){
                b.append(OtherEnum.GROUP_END);
            }
            return b.build();
        }

    }

    /**
     * <join_type>
     *     <join_hint>
     */
    public enum JoinType implements ElementList {
        JOIN(GrammarEnum.JOIN),
        INNER_JOIN(GrammarEnum.INNER,GrammarEnum.JOIN),
        INNER_REDUCE_JOIN(GrammarEnum.INNER,GrammarEnum.REDUCE,GrammarEnum.JOIN),
        INNER_REPLICATE_JOIN(GrammarEnum.INNER,GrammarEnum.REPLICATE,GrammarEnum.JOIN),
        INNER_REDISTRIBUTE_JOIN(GrammarEnum.INNER,GrammarEnum.REDISTRIBUTE,GrammarEnum.JOIN),
        LEFT_JOIN(GrammarEnum.LEFT,GrammarEnum.JOIN),
        RIGHT_JOIN(GrammarEnum.RIGHT,GrammarEnum.JOIN),
        FULL_JOIN(GrammarEnum.FULL,GrammarEnum.JOIN),
        LEFT_OUTER_JOIN(GrammarEnum.LEFT,GrammarEnum.OUTER,GrammarEnum.JOIN),
        RIGHT_OUTER_JOIN(GrammarEnum.RIGHT,GrammarEnum.OUTER,GrammarEnum.JOIN),
        FULL_OUTER_JOIN(GrammarEnum.FULL,GrammarEnum.OUTER,GrammarEnum.JOIN);

        private Element[] es;

        JoinType(Element... elements){
            this.es = elements;
        }

        @Override
        public List<Element> toElementList() {
            return new ListElementBuilder()
                    .append(Arrays.asList(this.es), OtherEnum.SPACE)
                    .build(null);
        }

    }
}
