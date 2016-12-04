package com.xy.xsql.orm.core.sentence.sql;

import com.xy.xsql.orm.core.BaseBuilder;
import com.xy.xsql.orm.data.sql.sentence.BaseElementsSentence;

/**
 * Sentence Sql Builder
 * dialect if you want change default implementation
 * Created by xiaoyao9184 on 2016/10/15.
 */
public interface DialectSentenceSqlBuilder extends BaseBuilder<BaseElementsSentence,String> {

    /**
     * 是否支持
     * @return 是/否
     */
    boolean isSupport(BaseElementsSentence elementsSentence);

}
