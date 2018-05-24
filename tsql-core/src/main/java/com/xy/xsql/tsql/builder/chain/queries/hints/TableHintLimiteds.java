package com.xy.xsql.tsql.builder.chain.queries.hints;

import com.xy.xsql.tsql.model.queries.hints.TableHintLimited;

/**
 * TableHintLimited Factory
 * Created by xiaoyao9184 on 2017/7/5.
 */
@SuppressWarnings("unused")
public class TableHintLimiteds {

    /*
    Quick build
     */

    /**
     * Quick build
     * @return TableHintLimited
     */
    public static TableHintLimited $Keepidentity(){
        return TableHintLimited.KEEPIDENTITY;
    }

    /**
     * Quick build
     * @return TableHintLimited
     */
    public static TableHintLimited $Keepdefaults(){
        return TableHintLimited.KEEPDEFAULTS;
    }

    /**
     * Quick build
     * @return TableHintLimited
     */
    public static TableHintLimited $Holdlock(){
        return TableHintLimited.HOLDLOCK;
    }

    /**
     * Quick build
     * @return TableHintLimited
     */
    public static TableHintLimited $IgnoreConstraints(){
        return TableHintLimited.IGNORE_CONSTRAINTS;
    }

    /**
     * Quick build
     * @return TableHintLimited
     */
    public static TableHintLimited $IgnoreTriggers(){
        return TableHintLimited.IGNORE_TRIGGERS;
    }

    /**
     * Quick build
     * @return TableHintLimited
     */
    public static TableHintLimited $Nolock(){
        return TableHintLimited.NOLOCK;
    }

    /**
     * Quick build
     * @return TableHintLimited
     */
    public static TableHintLimited $Nowait(){
        return TableHintLimited.NOWAIT;
    }

    /**
     * Quick build
     * @return TableHintLimited
     */
    public static TableHintLimited $Paglock(){
        return TableHintLimited.PAGLOCK;
    }

    /**
     * Quick build
     * @return TableHintLimited
     */
    public static TableHintLimited $Readcommitted(){
        return TableHintLimited.READCOMMITTED;
    }

    /**
     * Quick build
     * @return TableHintLimited
     */
    public static TableHintLimited $Readcommittedlock(){
        return TableHintLimited.READCOMMITTEDLOCK;
    }

    /**
     * Quick build
     * @return TableHintLimited
     */
    public static TableHintLimited $Readpast(){
        return TableHintLimited.READPAST;
    }

    /**
     * Quick build
     * @return TableHintLimited
     */
    public static TableHintLimited $Repeatableread(){
        return TableHintLimited.REPEATABLEREAD;
    }

    /**
     * Quick build
     * @return TableHintLimited
     */
    public static TableHintLimited $Rowlock(){
        return TableHintLimited.ROWLOCK;
    }

    /**
     * Quick build
     * @return TableHintLimited
     */
    public static TableHintLimited $Serializable(){
        return TableHintLimited.SERIALIZABLE;
    }

    /**
     * Quick build
     * @return TableHintLimited
     */
    public static TableHintLimited $Snapshot(){
        return TableHintLimited.SNAPSHOT;
    }

    /**
     * Quick build
     * @return TableHintLimited
     */
    public static TableHintLimited $Tablock(){
        return TableHintLimited.TABLOCK;
    }

    /**
     * Quick build
     * @return TableHintLimited
     */
    public static TableHintLimited $Tablockx(){
        return TableHintLimited.TABLOCKX;
    }

    /**
     * Quick build
     * @return TableHintLimited
     */
    public static TableHintLimited $Updlock(){
        return TableHintLimited.UPDLOCK;
    }

    /**
     * Quick build
     * @return TableHintLimited
     */
    public static TableHintLimited $Xlock(){
        return TableHintLimited.XLOCK;
    }

}
