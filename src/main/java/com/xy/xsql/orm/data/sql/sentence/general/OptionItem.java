package com.xy.xsql.orm.data.sql.sentence.general;

import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;
import com.xy.xsql.orm.data.sql.element.OperatorEnum;
import com.xy.xsql.orm.data.sql.element.OtherEnum;
import com.xy.xsql.orm.data.sql.element.UnknownString;
import com.xy.xsql.orm.util.CheckUtil;

import java.util.Arrays;
import java.util.List;

/**
 * https://msdn.microsoft.com/en-us/library/ms190322.aspx
 *
 -- Syntax for SQL Server and Azure SQL Database

 [ OPTION ( <query_hint> [ ,...n ] ) ]

 *

 -- Syntax for Azure SQL Data Warehouse and Parallel Data Warehouse

 OPTION ( <query_option> [ ,...n ] )

 <query_option> ::=
 LABEL = label_name |
 <query_hint>

 <query_hint> ::=
 HASH JOIN
 | LOOP JOIN
 | MERGE JOIN
 | FORCE ORDER
 | { FORCE | DISABLE } EXTERNALPUSHDOWN
 *
 * Created by xiaoyao9184 on 2016/12/20.
 */
public class OptionItem implements Expression {

    private List<QueryOption> queryOption;


    public List<QueryOption> getQueryOption() {
        return queryOption;
    }

    public void setQueryOption(List<QueryOption> queryOption) {
        this.queryOption = queryOption;
    }

    @Override
    public List<Element> toElementList() {
        ListElementBuilder b = new ListElementBuilder();

        if(!CheckUtil.isNullOrEmpty(this.queryOption)){
            b.append(GrammarEnum.OPTION);
            for (QueryOption queryOption : this.queryOption) {
                if(!CheckUtil.isNullOrEmpty(queryOption.getLabelName())){
                    b.append(GrammarEnum.LABEL)
                            .append(OtherEnum.SPACE)
                            .append(OperatorEnum.EQUAL)
                            .append(OtherEnum.SPACE)
                            .append(new UnknownString(queryOption.getLabelName()));
                }else {
                    b.append(queryOption.getQueryHint().toElementList(),null);
                }
            }
        }
        return b.build(null);
    }

    /**
     * query_option
     */
    public static class QueryOption {
        //label_name
        private String labelName;
        //<query_hint>
        private QueryHint queryHint;


        public String getLabelName() {
            return labelName;
        }

        public void setLabelName(String labelName) {
            this.labelName = labelName;
        }

        public QueryHint getQueryHint() {
            return queryHint;
        }

        public void setQueryHint(QueryHint queryHint) {
            this.queryHint = queryHint;
        }
    }

    /**
     * query_hint
     */
    public enum QueryHint implements Expression {
        HASH_JOIN(GrammarEnum.HASH,GrammarEnum.JOIN),
        LOOP_JOIN(GrammarEnum.LOOP,GrammarEnum.JOIN),
        MERGE_JOIN(GrammarEnum.MERGE,GrammarEnum.JOIN),
        FORCE_ORDER(GrammarEnum.FORCE,GrammarEnum.JOIN),
        FORCE_EXTERNALPUSHDOWN(GrammarEnum.FORCE,GrammarEnum.EXTERNALPUSHDOWN),
        DISABLE_EXTERNALPUSHDOWN(GrammarEnum.DISABLE,GrammarEnum.EXTERNALPUSHDOWN);

        private Element[] es;

        QueryHint(Element... elements){
            this.es = elements;
        }

        @Override
        public List<Element> toElementList() {
            return new ListElementBuilder()
                    .append(Arrays.asList(this.es),OtherEnum.SPACE)
                    .build(null);
        }

    }
}
