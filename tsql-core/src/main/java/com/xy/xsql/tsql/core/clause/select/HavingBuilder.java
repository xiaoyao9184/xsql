package com.xy.xsql.tsql.core.clause.select;

import com.xy.xsql.core.CodeTreeBuilder;
import com.xy.xsql.tsql.core.clause.SearchConditionBuilder;
import com.xy.xsql.tsql.model.clause.SearchCondition;
import com.xy.xsql.tsql.model.clause.select.Having;

import static com.xy.xsql.core.FiledBuilder.set;

/**
 * Created by xiaoyao9184 on 2016/12/28.
 */
public class HavingBuilder<ParentBuilder>
        extends CodeTreeBuilder<HavingBuilder<ParentBuilder>,ParentBuilder,Having> {

    public HavingBuilder() {
        super(new Having());
    }

    public HavingBuilder(Having having) {
        super(having);
    }

    public SearchConditionBuilder<HavingBuilder<ParentBuilder>> withSearchCondition(){
        return new SearchConditionBuilder<HavingBuilder<ParentBuilder>>
                (set(SearchCondition::new,
                        tar::setSearchCondition))
                .in(this);
    }
}
