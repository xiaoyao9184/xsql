package com.xy.xsql.orm.data.sql.element;

import com.xy.xsql.orm.data.sql.Element;

/**
 * Created by xiaoyao9184 on 2016/10/22.
 */
public enum GrammarEnum implements Element {

    ASC,
    AS,
    CASE,
    CREATE,
    TABLE,
    COLUMN,
    DELETE,
    DESC,
    END,
    FROM,
    GROUP,
    INSERT,
    INTO,
    JOIN,
    ON,
    ORDER,
    SELECT,
    SET,
    THEN,
    UNION,
    UPDATE,
    VALUES,
    WHEN,
    ELSE,
    WHERE,
    DISTINCT,
    LEFT,
    RIGHT,
    FULL,
    INNER,
    TOP,
    PERCENT,
    NOT,
    BETWEEN,
    AND,
    IN,
    DEFAULT,
    NULL,
    IS,
    ESCAPE,

    TRUNCATE,
    LIMIT,
    WITH,
    TIES,
    BULK,
    HASH,
    LOOP,
    MERGE,
    FORCE,
    DISABLE,
    EXTERNALPUSHDOWN,
    OPTION,
    LABEL,
    OR,
    OUTER,
    REDUCE,
    REPLICATE,
    REDISTRIBUTE,
    CROSS,
    APPLY, MATCHED, BY, TARGET, USING, NULLIF, COALESCE;



    @Override
    public String toString(){
        return this.name();
    }

}
