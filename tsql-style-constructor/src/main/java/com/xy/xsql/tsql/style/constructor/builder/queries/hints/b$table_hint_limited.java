package com.xy.xsql.tsql.style.constructor.builder.queries.hints;

import com.xy.xsql.core.builder.simple.CodeBuilder;
import com.xy.xsql.tsql.model.queries.hints.TableHintLimited;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoyao9184 on 2015/5/25.
 */
public class b$table_hint_limited extends CodeBuilder<TableHintLimited> {

    public b$table_hint_limited(TableHintLimited tableHintLimited) {
        super(tableHintLimited);
    }


    public static class b_WITH extends CodeBuilder<List<TableHintLimited>> {

        public b_WITH() {
            super(new ArrayList<>());
        }

    }
}
