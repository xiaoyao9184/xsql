package com.xy.xsql.tsql.model;

import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.element.Unknown;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by xiaoyao9184 on 2017/3/14.
 */
public enum Keywords {

    ADD,EXTERNAL,PROCEDURE,
    ALL,FETCH,PUBLIC,
    ALTER,FILE,RAISERROR,
    AND,FILLFACTOR,READ,
    ANY,FOR,READTEXT,
    AS,FOREIGN,RECONFIGURE,
    ASC,FREETEXT,REFERENCES,
    AUTHORIZATION,FREETEXTTABLE,REPLICATION,
    BACKUP,FROM,RESTORE,
    BEGIN,FULL,RESTRICT,
    BETWEEN,FUNCTION,RETURN,
    BREAK,GOTO,REVERT,
    BROWSE,GRANT,REVOKE,
    BULK,GROUP,RIGHT,
    BY,HAVING,ROLLBACK,
    CASCADE,HOLDLOCK,ROWCOUNT,
    CASE,IDENTITY,ROWGUIDCOL,
    CHECK,IDENTITY_INSERT,RULE,
    CHECKPOINT,IDENTITYCOL,SAVE,
    CLOSE,IF,SCHEMA,
    CLUSTERED,IN,SECURITYAUDIT,
    COALESCE,INDEX,SELECT,
    COLLATE,INNER,SEMANTICKEYPHRASETABLE,
    COLUMN,INSERT,SEMANTICSIMILARITYDETAILSTABLE,
    COMMIT,INTERSECT,SEMANTICSIMILARITYTABLE,
    COMPUTE,INTO,SESSION_USER,
    CONSTRAINT,IS,SET,
    CONTAINS,JOIN,SETUSER,
    CONTAINSTABLE,KEY,SHUTDOWN,
    CONTINUE,KILL,SOME,
    CONVERT,LEFT,STATISTICS,
    CREATE,LIKE,SYSTEM_USER,
    CROSS,LINENO,TABLE,
    CURRENT,LOAD,TABLESAMPLE,
    CURRENT_DATE,MERGE,TEXTSIZE,
    CURRENT_TIME,NATIONAL,THEN,
    CURRENT_TIMESTAMP,NOCHECK,TO,
    CURRENT_USER,NONCLUSTERED,TOP,
    CURSOR,NOT,TRAN,
    DATABASE,NULL,TRANSACTION,
    DBCC,NULLIF,TRIGGER,
    DEALLOCATE,OF,TRUNCATE,
    DECLARE,OFF,TRY_CONVERT,
    DEFAULT,OFFSETS,TSEQUAL,
    DELETE,ON,UNION,
    DENY,OPEN,UNIQUE,
    DESC,OPENDATASOURCE,UNPIVOT,
    DISK,OPENQUERY,UPDATE,
    DISTINCT,OPENROWSET,UPDATETEXT,
    DISTRIBUTED,OPENXML,USE,
    DOUBLE,OPTION,USER,
    DROP,OR,VALUES,
    DUMP,ORDER,VARYING,
    ELSE,OUTER,VIEW,
    END,OVER,WAITFOR,
    ERRLVL,PERCENT,WHEN,
    ESCAPE,PIVOT,WHERE,
    EXCEPT,PLAN,WHILE,
    EXEC,PRECISION,WITH,
    EXECUTE,PRIMARY,WITHIN_GROUP("WITHIN GROUP"),
    EXISTS,PRINT,WRITETEXT,
    EXIT,PROC, EXTERNALPUSHDOWN;


//    ASSERTION,FOUND,PUBLIC,
//    AT,FROM,READ,
//    AUTHORIZATION,FULL,REAL,
//    AVG,GET,REFERENCES,
//    BEGIN,GLOBAL,RELATIVE,
//    BETWEEN,GO,RESTRICT,
//    BIT,GOTO,REVOKE,
//    BIT_LENGTH,GRANT,RIGHT,
//    BOTH,GROUP,ROLLBACK,
//    BY,HAVING,ROWS,
//    CASCADE,HOUR,SCHEMA,
//    CASCADED,IDENTITY,SCROLL,
//    CASE,IMMEDIATE,SECOND,
//    CAST,IN,SECTION,
//    CATALOG,INCLUDE,SELECT,
//    CHAR,INDEX,SESSION,
//    CHAR_LENGTH,INDICATOR,SESSION_USER,
//    CHARACTER,INITIALLY,SET,
//    CHARACTER_LENGTH,INNER,SIZE,
//    CHECK,INPUT,SMALLINT,
//    CLOSE,INSENSITIVE,SOME,
//    COALESCE,INSERT,SPACE,
//    COLLATE,INT,SQL,
//    COLLATION,INTEGER,SQLCA,
//    COLUMN,INTERSECT,SQLCODE,
//    COMMIT,INTERVAL,SQLERROR,
//    CONNECT,INTO,SQLSTATE,
//    CONNECTION,IS,SQLWARNING,
//    CONSTRAINT,ISOLATION,SUBSTRING,
//    CONSTRAINTS,JOIN,SUM,
//    CONTINUE,KEY,SYSTEM_USER,
//    CONVERT,LANGUAGE,TABLE,
//    CORRESPONDING,LAST,TEMPORARY,
//    COUNT,LEADING,THEN,
//    CREATE,LEFT,TIME,
//    CROSS,LEVEL,TIMESTAMP,
//    CURRENT,LIKE,TIMEZONE_HOUR,
//    CURRENT_DATE,LOCAL,TIMEZONE_MINUTE,
//    CURRENT_TIME,LOWER,TO,
//    CURRENT_TIMESTAMP,MATCH,TRAILING,
//    CURRENT_USER,MAX,TRANSACTION,
//    CURSOR,MIN,TRANSLATE,
//    DATE,MINUTE,TRANSLATION,
//    DAY,MODULE,TRIM,
//    DEALLOCATE,MONTH,TRUE,
//    DEC,NAMES,UNION,
//    DECIMAL,NATIONAL,UNIQUE,
//    DECLARE,NATURAL,UNKNOWN,
//    DEFAULT,NCHAR,UPDATE,
//    DEFERRABLE,NEXT,UPPER,
//    DEFERRED,NO,USAGE,
//    DELETE,NONE,USER,
//    DESC,NOT,USING,
//    DESCRIBE,NULL,VALUE,
//    DESCRIPTOR,NULLIF,VALUES,
//    DIAGNOSTICS,NUMERIC,VARCHAR,
//    DISCONNECT,OCTET_LENGTH,VARYING,
//    DISTINCT,OF,VIEW,
//    DOMAIN,ON,WHEN,
//    DOUBLE,ONLY,WHENEVER,
//    DROP,OPEN,WHERE,
//    ELSE,OPTION,WITH,
//    END,OR,WORK,
//    END_EXEC("END-EXEC"),ORDER,WRITE,
//    ESCAPE,OUTER,YEAR,
//    EXCEPT,OUTPUT,ZONE,
//    EXCEPTION,



//    ABSOLUTE,HOST,RELATIVE,
//    ACTION,HOUR,RELEASE,
//    ADMIN,IGNORE,RESULT,
//    AFTER,IMMEDIATE,RETURNS,
//    AGGREGATE,INDICATOR,ROLE,
//    ALIAS,INITIALIZE,ROLLUP,
//    ALLOCATE,INITIALLY,ROUTINE,
//    ARE,INOUT,ROW,
//    ARRAY,INPUT,ROWS,
//    ASENSITIVE,INT,SAVEPOINT,
//    ASSERTION,INTEGER,SCROLL,
//    ASYMMETRIC,INTERSECTION,SCOPE,
//    AT,INTERVAL,SEARCH,
//    ATOMIC,ISOLATION,SECOND,
//    BEFORE,ITERATE,SECTION,
//    BINARY,LANGUAGE,SENSITIVE,
//    BIT,LARGE,SEQUENCE,
//    BLOB,LAST,SESSION,
//    BOOLEAN,LATERAL,SETS,
//    BOTH,LEADING,SIMILAR,
//    BREADTH,LESS,SIZE,
//    CALL,LEVEL,SMALLINT,
//    CALLED,LIKE_REGEX,SPACE,
//    CARDINALITY,LIMIT,SPECIFIC,
//    CASCADED,LN,SPECIFICTYPE,
//    CAST,LOCAL,SQL,
//    CATALOG,LOCALTIME,SQLEXCEPTION,
//    CHAR,LOCALTIMESTAMP,SQLSTATE,
//    CHARACTER,LOCATOR,SQLWARNING,
//    CLASS,MAP,START,
//    CLOB,MATCH,STATE,
//    COLLATION,MEMBER,STATEMENT,
//    COLLECT,METHOD,STATIC,
//    COMPLETION,MINUTE,STDDEV_POP,
//    CONDITION,MOD,STDDEV_SAMP,
//    CONNECT,MODIFIES,STRUCTURE,
//    CONNECTION,MODIFY,SUBMULTISET,
//    CONSTRAINTS,MODULE,SUBSTRING_REGEX,
//    CONSTRUCTOR,MONTH,SYMMETRIC,
//    CORR,MULTISET,SYSTEM,
//    CORRESPONDING,NAMES,TEMPORARY,
//    COVAR_POP,NATURAL,TERMINATE,
//    COVAR_SAMP,NCHAR,THAN,
//    CUBE,NCLOB,TIME,
//    CUME_DIST,NEW,TIMESTAMP,
//    CURRENT_CATALOG,NEXT,TIMEZONE_HOUR,
//    CURRENT_DEFAULT_TRANSFORM_GROUP,NO,TIMEZONE_MINUTE,
//    CURRENT_PATH,NONE,TRAILING,
//    CURRENT_ROLE,NORMALIZE,TRANSLATE_REGEX,
//    CURRENT_SCHEMA,NUMERIC,TRANSLATION,
//    CURRENT_TRANSFORM_GROUP_FOR_TYPE,OBJECT,TREAT,
//    CYCLE,OCCURRENCES_REGEX,TRUE,
//    DATA,OLD,UESCAPE,
//    DATE,ONLY,UNDER,
//    DAY,OPERATION,UNKNOWN,
//    DEC,ORDINALITY,UNNEST,
//    DECIMAL,OUT,USAGE,
//    DEFERRABLE,OVERLAY,USING,
//    DEFERRED,OUTPUT,VALUE,
//    DEPTH,PAD,VAR_POP,
//    DEREF,PARAMETER,VAR_SAMP,
//    DESCRIBE,PARAMETERS,VARCHAR,
//    DESCRIPTOR,PARTIAL,VARIABLE,
//    DESTROY,PARTITION,WHENEVER,
//    DESTRUCTOR,PATH,WIDTH_BUCKET,
//    DETERMINISTIC,POSTFIX,WITHOUT,
//    DICTIONARY,PREFIX,WINDOW,
//    DIAGNOSTICS,PREORDER,WITHIN,
//    DISCONNECT,PREPARE,WORK,
//    DOMAIN,PERCENT_RANK,WRITE,
//    DYNAMIC,PERCENTILE_CONT,XMLAGG,
//    EACH,PERCENTILE_DISC,XMLATTRIBUTES,
//    ELEMENT,POSITION_REGEX,XMLBINARY,
//    END-EXEC,PRESERVE,XMLCAST,
//    EQUALS,PRIOR,XMLCOMMENT,
//    EVERY,PRIVILEGES,XMLCONCAT,
//    EXCEPTION,RANGE,XMLDOCUMENT,
//    FALSE,READS,XMLELEMENT,
//    FILTER,REAL,XMLEXISTS,
//    FIRST,RECURSIVE,XMLFOREST,
//    FLOAT,REF,XMLITERATE,
//    FOUND,REFERENCING,XMLNAMESPACES,
//    FREE,REGR_AVGX,XMLPARSE,
//    FULLTEXTTABLE,REGR_AVGY,XMLPI,
//    FUSION,REGR_COUNT,XMLQUERY,
//    GENERAL,REGR_INTERCEPT,XMLSERIALIZE,
//    GET,REGR_R2,XMLTABLE,
//    GLOBAL,REGR_SLOPE,XMLTEXT,
//    GO,REGR_SXX,XMLVALIDATE,
//    GROUPING,REGR_SXY,YEAR,
//    HOLD,REGR_SYY,ZONE;


    private String string;

    Keywords(){
        this.string = this.name();
    }
    Keywords(String string){
        this.string = string;
    }

    @Override
    public String toString(){
        return string;
    }



    public enum Key {

        //Output
        DELETED, INSERTED,
        //Top
        TIES,
        //Option
        LABEL,
        //Form
        APPLY, REDUCE, REPLICATE, REDISTRIBUTE,
        //TableHint
        NOEXPAND,
        //For
        BINARY, BASE64, TYPE, ROOT, PATH, AUTO, INCLUDE_NULL_VALUES, WITHOUT_ARRAY_WRAPPER, ELEMENTS, ABSENT, XSINIL, RAW, XMLDATA, XMLSCHEMA, EXPLICIT,
        //GroupBy
        ROLLUP, CUBE, GROUPING, SETS,
        //OrderBy
        OFFSET, ROW, FIRST,
        //Over
        PARTITION,
        //Merge
        MATCHED, TARGET, SOURCE, USING,
        //ReNameTable
        RENAME, OBJECT,
        //QueryHint
        UNKNOWN,
        //OrderBy
        ROWS, NEXT, ONLY,
        //Output
        OUTPUT,
        //AtTimeZone
        AT, TIME, ZONE,
        //TruncateTable
        PARTITIONS, XML, JSON,

        //FreeText
        LANGUAGE,
        //Contains
        PROPERTY,

        //QueryHint
        HASH, CONCAT, MERGE, LOOP, FORCE, DISABLE, PARAMETERIZATION, SIMPLE,

        //
        DISTRIBUTED_AGG,

        //ORDER
        RANGE, UNBOUNDED, PRECEDING, FOLLOWING;

        @Override
        public String toString(){
            return name();
        }
    }

    /**
     * keyword + keyword
     * @param keywords
     * @return
     */
    public static Unknown k(Keywords... keywords){
        String b = String.valueOf(Arrays.stream(keywords)
                .map(Keywords::toString)
                .collect(Collectors.toList()));

        return new Unknown(b);
    }

    public static Unknown k(Other other, Keywords... keywords){
        String b = String.valueOf(Arrays.stream(keywords)
                .map(Keywords::toString)
                .collect(Collectors.joining(other.toString())));

        return new Unknown(b);
    }
}
