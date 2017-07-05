package com.xy.xsql.tsql.core.clause.hints;

import com.xy.xsql.tsql.model.clause.hints.TableHintLimited;

/**
 * TableHint Factory
 * Created by xiaoyao9184 on 2017/7/5.
 */
public class TableHintLimiteds {

    /*
    Quick build
     */

    /**
     * Quick build
     * @return
     */
    public static TableHintLimited KEEPIDENTITY(){
        return TableHintLimited.KEEPIDENTITY;
    }

    /**
     * Quick build
     * @return
     */
    public static TableHintLimited KEEPDEFAULTS(){
        return TableHintLimited.KEEPDEFAULTS;
    }

    /**
     * Quick build
     * @return
     */
    public static TableHintLimited HOLDLOCK(){
        return TableHintLimited.HOLDLOCK;
    }

    /**
     * Quick build
     * @return
     */
    public static TableHintLimited IGNORE_CONSTRAINTS(){
        return TableHintLimited.IGNORE_CONSTRAINTS;
    }

    /**
     * Quick build
     * @return
     */
    public static TableHintLimited IGNORE_TRIGGERS(){
        return TableHintLimited.IGNORE_TRIGGERS;
    }

    /**
     * Quick build
     * @return
     */
    public static TableHintLimited NOLOCK(){
        return TableHintLimited.NOLOCK;
    }

    /**
     * Quick build
     * @return
     */
    public static TableHintLimited NOWAIT(){
        return TableHintLimited.NOWAIT;
    }

    /**
     * Quick build
     * @return
     */
    public static TableHintLimited PAGLOCK(){
        return TableHintLimited.PAGLOCK;
    }

    /**
     * Quick build
     * @return
     */
    public static TableHintLimited READCOMMITTED(){
        return TableHintLimited.READCOMMITTED;
    }

    /**
     * Quick build
     * @return
     */
    public static TableHintLimited READCOMMITTEDLOCK(){
        return TableHintLimited.READCOMMITTEDLOCK;
    }

    /**
     * Quick build
     * @return
     */
    public static TableHintLimited READPAST(){
        return TableHintLimited.READPAST;
    }

    /**
     * Quick build
     * @return
     */
    public static TableHintLimited REPEATABLEREAD(){
        return TableHintLimited.REPEATABLEREAD;
    }

    /**
     * Quick build
     * @return
     */
    public static TableHintLimited ROWLOCK(){
        return TableHintLimited.ROWLOCK;
    }

    /**
     * Quick build
     * @return
     */
    public static TableHintLimited SERIALIZABLE(){
        return TableHintLimited.SERIALIZABLE;
    }

    /**
     * Quick build
     * @return
     */
    public static TableHintLimited SNAPSHOT(){
        return TableHintLimited.SNAPSHOT;
    }

    /**
     * Quick build
     * @return
     */
    public static TableHintLimited TABLOCK(){
        return TableHintLimited.TABLOCK;
    }

    /**
     * Quick build
     * @return
     */
    public static TableHintLimited TABLOCKX(){
        return TableHintLimited.TABLOCKX;
    }

    /**
     * Quick build
     * @return
     */
    public static TableHintLimited UPDLOCK(){
        return TableHintLimited.UPDLOCK;
    }

    /**
     * Quick build
     * @return
     */
    public static TableHintLimited XLOCK(){
        return TableHintLimited.XLOCK;
    }

}
