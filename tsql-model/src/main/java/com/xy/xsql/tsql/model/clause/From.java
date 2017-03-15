package com.xy.xsql.tsql.model.clause;


import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.element.Alias;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.element.TableName;
import com.xy.xsql.tsql.model.statement.dml.Select;
import com.xy.xsql.tsql.util.ListBlockBuilder;

import java.util.Arrays;
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


    @Override
    public List<Block> toBlockList() {
        ListBlockBuilder b = new ListBlockBuilder()
                .append(Keywords.FROM);
        for (TableSource table: getTableSourceList()) {
            b.append(table.toBlockList(), Other.DELIMITER);
        }
        return b.build();
    }

    /**
     * <table_source>
     */
    public static class TableSource implements Block  {

        //table_or_view_name [ [ AS ] table_alias ]
        private TableName tableName;
        //derived_table
        private Select derivedTable;
        private boolean useAs;
        private Alias<Void> tableAlias;
        //<joined_table>
        private JoinedTable joinedTable;


        public TableName getTableName() {
            return tableName;
        }

        public void setTableName(TableName table) {
            this.tableName = table;
        }

        public Alias<Void> getTableAlias() {
            return tableAlias;
        }

        public void setTableAlias(Alias<Void> tableAlias) {
            this.tableAlias = tableAlias;
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
        public List<Block> toBlockList() {
            if(joinedTable == null){
                ListBlockBuilder b = new ListBlockBuilder()
                        .append(tableName);
                b.append(useAs ? Keywords.AS : null)
                        .append(tableAlias);
                return b.build();
            }

            return joinedTable.toBlockList();
        }
    }

    /**
     * <joined_table>
     */
    public static class JoinedTable implements Block {
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
        public List<Block> toBlockList() {
            ListBlockBuilder b = new ListBlockBuilder()
                    .withDelimiter(Other.SPACE);
            if(isUseParenthesis()){
                b.append(Other.GROUP_START);
            }
            if(isUseJoinOn()){
                b.append(getTableSource())
                        .append(getJoinType())
                        .append(getTableSource2())
                        .append(Keywords.ON)
                        .append(getSearchCondition());
            }else if(isUseCrossJoin()){
                b.append(getTableSource())
                        .append(Keywords.CROSS)
                        .append(Keywords.JOIN)
                        .append(getTableSource2());
            }else if(isUseCrossApply()){
                b.append(getTableSource())
                        .append(Keywords.CROSS)
                        .append(Keywords.Key.APPLY)
                        .append(getTableSource2());
            }else if(isUseOuterApply()){
                b.append(getTableSource())
                        .append(Keywords.OUTER)
                        .append(Keywords.Key.APPLY)
                        .append(getTableSource2());
            }

            if(isUseParenthesis()){
                b.append(Other.GROUP_END);
            }
            return b.build();
        }

    }

    /**
     * <join_type>
     *     <join_hint>
     */
    public enum JoinType implements Block {
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

        private Block[] es;

        JoinType(Block... elements){
            this.es = elements;
        }

        @Override
        public List<Block> toBlockList() {
            return new ListBlockBuilder()
                    .append(Arrays.asList(this.es), Other.SPACE)
                    .build(null);
        }

    }
}
