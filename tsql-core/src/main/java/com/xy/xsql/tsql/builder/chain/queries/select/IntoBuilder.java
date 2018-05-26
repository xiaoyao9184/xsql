package com.xy.xsql.tsql.builder.chain.queries.select;

import com.xy.xsql.core.builder.parent.ParentHoldBuilder;
import com.xy.xsql.tsql.model.queries.select.Into;
import com.xy.xsql.tsql.model.datatypes.table.TableName;

import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e;

/**
 * IntoBuilder
 * Created by xiaoyao9184 on 2017/6/21.
 */
@SuppressWarnings("WeakerAccess")
public class IntoBuilder<ParentBuilder>
        extends ParentHoldBuilder<IntoBuilder<ParentBuilder>,ParentBuilder,Into> {

    public IntoBuilder() {
        super(new Into());
    }

    public IntoBuilder(Into target) {
        super(target);
    }

    /**
     * set
     * @param tableName TableName
     * @return THIS
     */
    public IntoBuilder<ParentBuilder> withNewTable(TableName tableName){
        target.setNewTable(tableName);
        return this;
    }

    /**
     * set
     * @param fileGroup file group
     * @return THIS
     */
    public IntoBuilder<ParentBuilder> withFileGroup(String fileGroup){
        target.setFileGroup(e(fileGroup));
        return this;
    }

}
