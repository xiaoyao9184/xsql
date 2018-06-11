package com.xy.xsql.tsql.model.functions;

import com.xy.xsql.tsql.model.elements.expressions.Expression;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public interface Function extends Expression {




    @SuppressWarnings("SpellCheckingInspection")
    enum Keywords {
        //
        AVG,
        CHECKSUM_AGG,
        COUNT,
        COUNT_BIG,
        GROUPING,
        GROUPING_ID,
        MAX,
        MIN,
        STDEV,
        STDEVP,
        SUM,
        VAR,
        VARP,

        //
        CUME_DIST,
        FIRST_VALUE,
        LAG,
        LAST_VALUE,
        LEAD,
        PERCENTILE_CONT,
        PERCENTILE_DISC,
        PERCENT_RANK,

        //
        COLLATIONPROPERTY,
        TERTIARY_WEIGHTS,

        $$DBTS,
        $$LANGID,
        $$LANGUAGE,
        $$LOCK_TIMEOUT,
        $$MAX_CONNECTIONS,
        $$MAX_PRECISION,
        $$NESTLEVEL,
        $$DATEFIRST,
        $$OPTIONS,
        $$REMSERVER,
        $$SERVERNAME,
        $$SERVICENAME,
        $$SPID,
        $$TEXTSIZE,
        $$VERSION,

        CAST,
        CONVERT,
        PARSE,
        TRY_CAST,
        TRY_CONVERT,
        TRY_PARSE,


        ASYMKEY_ID,
        ASYMKEYPROPERTY,
        CertProperty,
        Cert_Id,
        CRYPT_GEN_RANDOM,
        DecryptByAsymKey,
        DecryptByCert,
        DecryptByKey,
        DecryptByKeyAutoAsymKey,
        DecryptByKeyAutoCert,
        DecryptByPassPhrase,
        EncryptByAsymKey,
        EncryptByCert,
        EncryptByKey,
        EncryptByPassPhrase,
        HASHBYTES,
        IS_OBJECTSIGNED,
        Key_GUID,
        Key_ID,
        KEY_NAME,
        SignByAsymKey,
        SignByCert,
        SYMKEYPROPERTY,
        VerifySignedByCert,
        VerifySignedByAsymKey,

        //
        $$CURSOR_ROWS,
        $$FETCH_STATUS,
        CURSOR_STATUS,

        //
        DATALENGTH,
        IDENT_CURRENT,
        IDENT_INCR,
        IDENT_SEED,
        IDENTITY,
        SQL_VARIANT_PROPERTY,

        //
        CURRENT_TIMESTAMP,
        DATEADD,
        DATEDIFF,
        DATEDIFF_BIG,
        DATEFROMPARTS,
        DATENAME,
        DATEPART,
        DATETIME2FROMPARTS,

        //
        ISJSON,
        JSON_VALUE,
        JSON_QUERY,
        JSON_MODIFY,

        //
        CHOOSE, IIF,

        //
        ABS,
        ACOS,
        ASIN,
        ATAN,
        ATN2,
        CEILING,
        COS,
        COT,
        DEGREES,
        EXP,
        FLOOR,
        LOG,
        LOG10,
        PI,
        POWER,
        RADIANS,
        RAND,
        ROUND,
        SIGN,
        SIN,
        SQRT,
        SQUARE,
        TAN,

        //
        DENSE_RANK,
        NTILE,
        RANK,
        ROW_NUMBER,

        //
        PUBLISHINGSERVERNAME,

        //
        OPENDATASOURCE,
        OPENJSON,
        OPENQUERY,
        OPENROWSET,
        OPENXML,

        //
        ASCII,
        CHAR,
        CHARINDEX,
        CONCAT,
        CONCAT_WS,
        DIFFERENCE,
        FORMAT,
        LEFT,
        LEN,
        LOWER,
        LTRIM,
        NCHAR,
        PATINDEX,
        QUOTENAME,
        REPLACE,
        REPLICATE,
        REVERSE,
        RTRIM,
        SOUNDEX,
        SPACE,
        STR,
        STRING_ESCAPE,
        STRING_SPLIT,
        STUFF,
        SUBSTRING,
        TRANSLATE,
        TRIM,
        UNICODE,
        UPPER,

        //
        $$ERROR,
        $$IDENTITY,
        $$PACK_RECEIVED,
        $$ROWCOUNT,
        $$TRANCOUNT,
        BINARY_CHECKSUM,
        CHECKSUM,
        COMPRESS,
        CONNECTIONPROPERTY,
        CONTEXT_INFO,
        CURRENT_REQUEST_ID,
        CURRENT_TRANSACTION_ID,
        DECOMPRESS,
        ERROR_LINE,
        ERROR_MESSAGE,
        ERROR_NUMBER,
        ERROR_PROCEDURE,
        ERROR_SEVERITY,
        ERROR_STATE,
        FORMATMESSAGE,
        GET_FILESTREAM_TRANSACTION_CONTEXT,
        GETANSINULL,
        HOST_ID,
        HOST_NAME,
        ISNULL,
        ISNUMERIC,
        MIN_ACTIVE_ROWVERSION,
        NEWID,
        NEWSEQUENTIALID,
        ROWCOUNT_BIG,
        SESSION_CONTEXT,
        XACT_STATE,

        //
        DATETIMEFROMPARTS,
        DATETIMEOFFSETFROMPARTS,
        DAY,
        EOMONTH,
        GETDATE,
        GETUTCDATE,
        ISDATE,
        MONTH,
        SMALLDATETIMEFROMPARTS,
        SWITCHOFFSET,
        SYSDATETIME,
        SYSDATETIMEOFFSET,
        SYSUTCDATETIME,
        TIMEFROMPARTS,
        TODATETIMEOFFSET,
        YEAR,

        //
        TEXTPTR,
        TEXTVALID,

        //
        COLUMNS_UPDATED,
        EVENTDATA,
        TRIGGER_NESTLEVEL,
        UPDATE

,
        $$CONNECTIONS,
        $$CPU_BUSY,
        $$IDLE,
        $$IO_BUSY,
        fn_virtualfilestats,
        $$PACK_SENT,
        $$PACKET_ERRORS,
        $$PACKET_RECEIVED,
        $$TIMETICKS,
        $$TOTAL_ERRORS,
        $$TOTAL_READ,
        $$TOTAL_WRITE,


        $$PROCID,
        APP_NAME,
        APPLOCK_MODE,
        APPLOCK_TEST,
        ASSEMBLYPROPERTY,
        COL_LENGTH,
        COL_NAME,
        COLUMNPROPERTY,
        DATABASE_PRINCIPAL_ID,
        DATABASEPROPERTYEX,
        DB_ID,
        DB_NAME,
        FILE_ID,
        FILE_IDEX,
        FILE_NAME,
        FILEGROUP_NAME,
        FILEGROUPPROPERTY,
        FILEPROPERTY,
        FULLTEXTCATALOGPROPERTY,
        FULLTEXTSERVICEPROPERTY,
        INDEX_COL,
        INDEXKEY_PROPERTY,
        INDEXPROPERTY,
        NEXT_VALUE_FOR("NEXT VALUE FOR"),
        OBJECT_DEFINITION,
        OBJECT_ID,
        OBJECT_NAME,
        OBJECT_SCHEMA_NAME,
        OBJECTPROPERTY,
        OBJECTPROPERTYEX,
        ORIGINAL_DB_NAME,
        PARSENAME,
        SCHEMA_ID,
        SCHEMA_NAME,
        SCOPE_IDENTITY,
        SERVERPROPERTY,
        STATS_DATE,
        TYPE_ID,
        TYPE_NAME,
        TYPEPROPERTY;



        private String string;

        Keywords(){
            this.string = this.name().replace("$$","@@");
        }
        Keywords(String string){
            this.string = string;
        }

        @Override
        public String toString(){
            return string;
        }

    }


    interface InternalFunction extends Function {

        default Function.Keywords keyword(){
            String name = this.getClass().getSimpleName().toUpperCase();
            name = name.replace("$$","");
            return Keywords.valueOf(name);
        }
    }
}
