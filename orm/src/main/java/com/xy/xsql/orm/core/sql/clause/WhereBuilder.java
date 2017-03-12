package com.xy.xsql.orm.core.sql.clause;

import com.xy.xsql.orm.core.CodeTreeBuilder;
import com.xy.xsql.orm.core.SubBuilder;
import com.xy.xsql.orm.data.sql.clause.SearchCondition;
import com.xy.xsql.orm.data.sql.clause.Where;
import com.xy.xsql.orm.data.sql.clause.With;

import static com.xy.xsql.orm.util.FiledBuilder.set;

/**
 * Created by xiaoyao9184 on 2016/12/28.
 */
public class WhereBuilder<ParentBuilder>
        extends CodeTreeBuilder<WhereBuilder<ParentBuilder>,ParentBuilder,Where> {

    public WhereBuilder() {
        super(new Where());
    }

    public WhereBuilder(Where where) {
        super(where);
    }

    public SearchConditionBuilder<WhereBuilder<ParentBuilder>> withSearchCondition(){
        return new SearchConditionBuilder<WhereBuilder<ParentBuilder>>
                (set(SearchCondition::new,
                        tar::setSearchCondition))
                .in(this);
    }
}
