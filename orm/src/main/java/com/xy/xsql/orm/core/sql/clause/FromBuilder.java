package com.xy.xsql.orm.core.sql.clause;

import com.xy.xsql.orm.core.SubBuilder;
import com.xy.xsql.orm.core.sql.clause.subquery.SubQueryBuilder;
import com.xy.xsql.orm.data.sql.clause.From;
import com.xy.xsql.orm.data.sql.clause.SearchCondition;
import com.xy.xsql.orm.data.sql.element.info.Table;

import java.util.ArrayList;

/**
 * Created by xiaoyao9184 on 2016/12/28.
 */
public class FromBuilder<Done>
        extends SubBuilder<FromBuilder<Done>,Void,Done> {

    private From from;

    private TableSourceBuilder<FromBuilder<Done>> tableSourceBuilder;

    public FromBuilder(From from) {
        this.from = from;
    }


    /**
     *
     * @return
     */
    public TableSourceBuilder<FromBuilder<Done>> withTableSource(){
        From.TableSource tableSource = new From.TableSource();
        if(this.from.getTableSourceList() == null){
            this.from.setTableSourceList(new ArrayList<From.TableSource>());
        }
        this.from.getTableSourceList().add(tableSource);

        tableSourceBuilder = new TableSourceBuilder<>(tableSource);
        return tableSourceBuilder.in(this);
    }

    /**
     *
     * @param <Done2>
     */
    public static class TableSourceBuilder<Done2>
            extends SubBuilder<TableSourceBuilder<Done2>,Void,Done2> {

        private final From.TableSource tableSource;

        private SubQueryBuilder<TableSourceBuilder<Done2>> subQueryBuilder;
        private JoinedTableBuilder<TableSourceBuilder<Done2>> joinedTableBuilder;

        public TableSourceBuilder(From.TableSource tableSource) {
            this.tableSource = tableSource;
        }

        public TableSourceBuilder<Done2> withTable(String tableName){
            this.tableSource.setTable(new Table(tableName));
            return this;
        }


        public SubQueryBuilder<TableSourceBuilder<Done2>> withDerivedTable() {
            subQueryBuilder = new SubQueryBuilder<>();
            return subQueryBuilder.in(this);
        }

        public TableSourceBuilder<Done2> withTableAlias(){
            this.tableSource.setUseTableAlias(true);
            return this;
        }

        public JoinedTableBuilder<TableSourceBuilder<Done2>> withJoinedTable(){
            From.JoinedTable joinedTable = new From.JoinedTable();
            joinedTableBuilder = new JoinedTableBuilder<>(joinedTable);
            return joinedTableBuilder.in(this);
        }
    }


    public static class JoinedTableBuilder<Done3>
            extends SubBuilder<JoinedTableBuilder<Done3>,Void,Done3> {

        private From.JoinedTable joinedTable;

        public JoinedTableBuilder(From.JoinedTable joinedTable) {
            this.joinedTable = joinedTable;
        }


        public TableSourceBuilder<JoinedTableBuilder<Done3>> withTableSource(){
            From.TableSource tableSource = new From.TableSource();
            this.joinedTable.setTableSource(tableSource);
            TableSourceBuilder<JoinedTableBuilder<Done3>> tableSourceBuilder = new TableSourceBuilder<>(tableSource);
            return tableSourceBuilder.in(this);
        }

        public JoinedTableBuilder<Done3> withJoinType(From.JoinType joinType){
            this.joinedTable.setJoinType(joinType);
            return this;
        }

        public TableSourceBuilder<JoinedTableBuilder<Done3>> withTableSource2(){
            From.TableSource tableSource = new From.TableSource();
            this.joinedTable.setTableSource2(tableSource);
            TableSourceBuilder<JoinedTableBuilder<Done3>> tableSourceBuilder = new TableSourceBuilder<>(tableSource);
            return tableSourceBuilder.in(this);
        }

        public SearchConditionBuilder<JoinedTableBuilder<Done3>> withSearchCondition(){
            SearchCondition searchCondition = new SearchCondition();
            this.joinedTable.setSearchCondition(searchCondition);
            SearchConditionBuilder<JoinedTableBuilder<Done3>> searchConditionBuilder = new SearchConditionBuilder<>(searchCondition);
            return searchConditionBuilder.in(this);
        }
    }
}
