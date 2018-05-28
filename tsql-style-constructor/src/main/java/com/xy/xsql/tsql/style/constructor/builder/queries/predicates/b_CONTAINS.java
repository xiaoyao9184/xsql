package com.xy.xsql.tsql.style.constructor.builder.queries.predicates;

import com.xy.xsql.core.builder.CodeBuilder;
import com.xy.xsql.tsql.builder.chain.queries.predicates.ContainsPredicateBuilder;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.style.constructor.builder.elements.variables.b_SET;
import com.xy.xsql.tsql.style.constructor.builder.queries.b_WITH;
import com.xy.xsql.tsql.style.constructor.builder.statements.b_INSERT;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoyao9184 on 2015/5/25.
 */
public class b_CONTAINS extends ContainsPredicateBuilder<b_CONTAINS> {

    public b_CONTAINS() {
        this.in(this);
    }

    public static class b_PROPERTY extends ContainsPredicateBuilder<b_PROPERTY> {

        public b_PROPERTY() {
            this.in(this);
        }

    }

    /**
     * same as
     * @see b_SET.b_OF
     * @see b_WITH.b$column_name_list
     * @see b_FREETEXT.b$column_list
     * @see b_CONTAINS.b$column_list
     * @see b_INSERT.b$column_list
     */
    public static class b$column_list extends CodeBuilder<List<ColumnName>> {

        public b$column_list() {
            super(new ArrayList<>());
        }

    }
}