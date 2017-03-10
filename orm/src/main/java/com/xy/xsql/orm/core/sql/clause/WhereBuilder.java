package com.xy.xsql.orm.core.sql.clause;

import com.xy.xsql.orm.core.SubBuilder;
import com.xy.xsql.orm.data.sql.clause.SearchCondition;
import com.xy.xsql.orm.data.sql.clause.Where;

/**
 * Created by xiaoyao9184 on 2016/12/28.
 */
public class WhereBuilder<Done>
        extends SubBuilder<WhereBuilder<Done>,Void,Done> {

    private Where where;

    public WhereBuilder(Where where) {
        this.where = where;
    }

    public SearchConditionBuilder<WhereBuilder<Done>> withSearchCondition(){
        SearchCondition searchCondition = new SearchCondition();
        this.where.setSearchCondition(searchCondition);
        SearchConditionBuilder<WhereBuilder<Done>> searchConditionBuilder = new SearchConditionBuilder<>(searchCondition);
        return searchConditionBuilder.in(this);
    }
}
