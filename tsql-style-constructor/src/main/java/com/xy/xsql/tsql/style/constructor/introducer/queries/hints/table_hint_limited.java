package com.xy.xsql.tsql.style.constructor.introducer.queries.hints;

import com.xy.xsql.tsql.model.queries.hints.TableHintLimited;
import com.xy.xsql.tsql.style.constructor.builder.queries.hints.b$table_hint_limited;

/**
 * Created by xiaoyao9184 on 2018/5/9.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public interface table_hint_limited {

    static b$table_hint_limited KEEPIDENTITY(){
        return new b$table_hint_limited(TableHintLimited.KEEPIDENTITY);
    }
    static b$table_hint_limited KEEPDEFAULTS(){
        return new b$table_hint_limited(TableHintLimited.KEEPDEFAULTS);
    }
    static b$table_hint_limited HOLDLOCK(){
        return new b$table_hint_limited(TableHintLimited.HOLDLOCK);
    }
    static b$table_hint_limited IGNORE_CONSTRAINTS(){
        return new b$table_hint_limited(TableHintLimited.IGNORE_CONSTRAINTS);
    }
    static b$table_hint_limited IGNORE_TRIGGERS(){
        return new b$table_hint_limited(TableHintLimited.IGNORE_TRIGGERS);
    }
    static b$table_hint_limited NOLOCK(){
        return new b$table_hint_limited(TableHintLimited.NOLOCK);
    }
    static b$table_hint_limited NOWAIT(){
        return new b$table_hint_limited(TableHintLimited.NOWAIT);
    }
    static b$table_hint_limited PAGLOCK(){
        return new b$table_hint_limited(TableHintLimited.PAGLOCK);
    }
    static b$table_hint_limited READCOMMITTED(){
        return new b$table_hint_limited(TableHintLimited.READCOMMITTED);
    }
    static b$table_hint_limited READCOMMITTEDLOCK(){
        return new b$table_hint_limited(TableHintLimited.READCOMMITTEDLOCK);
    }
    static b$table_hint_limited READPAST(){
        return new b$table_hint_limited(TableHintLimited.READPAST);
    }
    static b$table_hint_limited REPEATABLEREAD(){
        return new b$table_hint_limited(TableHintLimited.REPEATABLEREAD);
    }
    static b$table_hint_limited ROWLOCK(){
        return new b$table_hint_limited(TableHintLimited.ROWLOCK);
    }
    static b$table_hint_limited SERIALIZABLE(){
        return new b$table_hint_limited(TableHintLimited.SERIALIZABLE);
    }
    static b$table_hint_limited SNAPSHOT(){
        return new b$table_hint_limited(TableHintLimited.SNAPSHOT);
    }
    static b$table_hint_limited TABLOCK(){
        return new b$table_hint_limited(TableHintLimited.TABLOCK);
    }
    static b$table_hint_limited TABLOCKX(){
        return new b$table_hint_limited(TableHintLimited.TABLOCKX);
    }
    static b$table_hint_limited UPDLOCK(){
        return new b$table_hint_limited(TableHintLimited.UPDLOCK);
    }
    static b$table_hint_limited XLOCK(){
        return new b$table_hint_limited(TableHintLimited.XLOCK);
    }

}
