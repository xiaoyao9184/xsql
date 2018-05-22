package com.xy.xsql.tsql.builder.chain.queries.select;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.queries.select.Into;
import com.xy.xsql.tsql.model.datatypes.table.TableName;

import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e;

/**
 * IntoBuilder
 * Created by xiaoyao9184 on 2017/6/21.
 */
public class IntoBuilder<ParentBuilder>
        extends CodeTreeBuilder<IntoBuilder<ParentBuilder>,ParentBuilder,Into> {

    public IntoBuilder() {
        super(new Into());
    }

    public IntoBuilder(Into into) {
        super(into);
    }

    public IntoBuilder<ParentBuilder> withNewTable(TableName tableName){
        target.setNewTable(tableName);
        return this;
    }

    public IntoBuilder<ParentBuilder> withFileGroup(String fileGroup){
        target.setFileGroup(e(fileGroup));
        return this;
    }

}
