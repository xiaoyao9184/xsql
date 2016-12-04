package com.xy.xsql.orm.core;

import com.xy.xsql.orm.data.sql.sentence.BaseElementsSentence;

/**
 * Regulator
 * for change BaseElementsSentence
 * Created by xiaoyao9184 on 2016/10/15.
 */
public interface Regulator {

    /**
     * 修改
     * @param elementsSentence BaseElementsSentence
     */
    void change(BaseElementsSentence elementsSentence);
}
