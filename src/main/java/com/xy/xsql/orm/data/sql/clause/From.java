package com.xy.xsql.orm.data.sql.clause;

import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.ElementList;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;
import com.xy.xsql.orm.data.sql.element.OtherEnum;
import com.xy.xsql.orm.data.sql.element.info.Table;

import java.util.Arrays;
import java.util.List;

/**
 * https://msdn.microsoft.com/en-us/library/ms177634.aspx
 *
 * Created by xiaoyao9184 on 2016/12/21.
 */
public class From implements ElementList {
    private List<TableSource> tableSourceList;

    @Override
    public List<Element> toElementList() {
        return null;
    }


    public static class TableSource implements ElementList  {

        //table_or_view_name
        private Table table;
        private boolean useTableAlias;
        //<joined_table>
        private JoinedTable joinedTable;

        @Override
        public List<Element> toElementList() {
            return null;
        }
    }

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

        @Override
        public List<Element> toElementList() {
            ListElementBuilder b = new ListElementBuilder()
                    .withDelimiter(OtherEnum.SPACE);
            if(useParenthesis){
                b.append(OtherEnum.GROUP_START);
            }
            if(useJoinOn){
                b.append(tableSource)
                    .append(joinType)
                    .append(tableSource2)
                    .append(GrammarEnum.ON)
                    .append(searchCondition);
            }else if(useCrossJoin){
                b.append(tableSource)
                        .append(GrammarEnum.CROSS)
                        .append(GrammarEnum.JOIN)
                        .append(tableSource2);
            }else if(useCrossApply){
                b.append(tableSource)
                        .append(GrammarEnum.CROSS)
                        .append(GrammarEnum.APPLY)
                        .append(tableSource2);
            }else if(useOuterApply){
                b.append(tableSource)
                        .append(GrammarEnum.OUTER)
                        .append(GrammarEnum.APPLY)
                        .append(tableSource2);
            }

            if(useParenthesis){
                b.append(OtherEnum.GROUP_END);
            }
            return b.build(null);
        }
    }

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
