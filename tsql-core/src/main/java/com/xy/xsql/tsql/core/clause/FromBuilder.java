package com.xy.xsql.tsql.core.clause;

import com.xy.xsql.core.CodeTreeBuilder;
import com.xy.xsql.tsql.core.clause.subquery.SubQueryBuilder;
import com.xy.xsql.tsql.model.clause.From;
import com.xy.xsql.tsql.model.clause.SearchCondition;
import com.xy.xsql.tsql.model.element.Alias;
import com.xy.xsql.tsql.model.element.TableName;

import static com.xy.xsql.core.FiledBuilder.set;
import static com.xy.xsql.core.ListBuilder.initNew;

/**
 * Created by xiaoyao9184 on 2016/12/28.
 */
public class FromBuilder<ParentBuilder>
        extends CodeTreeBuilder<FromBuilder<ParentBuilder>,ParentBuilder,From> {

    public FromBuilder() {
        super(new From());
    }

    public FromBuilder(From from) {
        super(from);
    }

    /**
     *
     * @return
     */
    public TableSourceBuilder<FromBuilder<ParentBuilder>> withTableSource(){
        return new TableSourceBuilder<FromBuilder<ParentBuilder>>
                (initNew(From.TableSource::new,
                        tar::getTableSourceList,
                        tar::setTableSourceList))
                .in(this);
    }

    /**
     *
     * @param <ParentBuilder>
     */
    public static class TableSourceBuilder<ParentBuilder>
            extends CodeTreeBuilder<TableSourceBuilder<ParentBuilder>,ParentBuilder,From.TableSource> {

        public TableSourceBuilder(From.TableSource tableSource) {
            super(tableSource);
        }

        public TableSourceBuilder<ParentBuilder> withTableName(TableName tableName){
            tar.setTableName(tableName);
            return this;
        }

        @Deprecated
        public TableSourceBuilder<ParentBuilder> withTableName(String tableName){
            tar.setTableName(new TableName(tableName));
            return this;
        }

        public SubQueryBuilder<TableSourceBuilder<ParentBuilder>> withDerivedTable() {
            return new SubQueryBuilder<TableSourceBuilder<ParentBuilder>>
                    ()
                    .in(this);
        }

        public TableSourceBuilder<ParentBuilder> withTableAlias(String alias){
            tar.setTableAlias(new Alias<>(alias));
            return this;
        }

        public JoinedTableBuilder<TableSourceBuilder<ParentBuilder>> withJoinedTable(){
            return new JoinedTableBuilder<TableSourceBuilder<ParentBuilder>>
                    (set(From.JoinedTable::new,
                            tar::setJoinedTable))
                    .in(this);
        }
    }


    /**
     * 
     * @param <ParentBuilder>
     */
    public static class JoinedTableBuilder<ParentBuilder>
            extends CodeTreeBuilder<JoinedTableBuilder<ParentBuilder>,ParentBuilder,From.JoinedTable> {

        public JoinedTableBuilder(From.JoinedTable joinedTable) {
            super(joinedTable);
        }


        public TableSourceBuilder<JoinedTableBuilder<ParentBuilder>> withTableSource(){
            return new TableSourceBuilder<JoinedTableBuilder<ParentBuilder>>
                    (set(From.TableSource::new,
                            tar::setTableSource))
                    .in(this);
        }

        public JoinedTableBuilder<ParentBuilder> withJoinType(From.JoinType joinType){
            tar.setJoinType(joinType);
            return this;
        }

        public TableSourceBuilder<JoinedTableBuilder<ParentBuilder>> withTableSource2(){
            return new TableSourceBuilder<JoinedTableBuilder<ParentBuilder>>
                    (set(From.TableSource::new,
                            tar::setTableSource2))
                    .in(this);
        }

        public SearchConditionBuilder<JoinedTableBuilder<ParentBuilder>> withSearchCondition(){
            return new SearchConditionBuilder<JoinedTableBuilder<ParentBuilder>>
                    (set(SearchCondition::new,
                            tar::setSearchCondition))
                    .in(this);
        }
    }
}
