package com.xy.xsql.tsql.core.clause.select;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.clause.select.Into;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.element.TableName;

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
        target.setFileGroup(new StringConstant(fileGroup));
        return this;
    }

}
