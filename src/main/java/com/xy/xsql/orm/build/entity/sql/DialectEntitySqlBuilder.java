package com.xy.xsql.orm.build.entity.sql;

import com.xy.xsql.orm.data.config.DialectEntitySqlBuilderConfig;
import com.xy.xsql.orm.data.entity.EntityTemplate;

/**
 * Entity Sql Builder
 * dialect if you want change default implementation
 * Created by xiaoyao9184 on 2016/1/13.
 */
public interface DialectEntitySqlBuilder {

    /**
     * cache config for later
     * @param config DialectEntitySqlBuilderConfig
     * @return DialectEntitySqlBuilder
     */
    DialectEntitySqlBuilder cacheConfig(DialectEntitySqlBuilderConfig config);

    /**
     * cache elementsSentence for later
     * @param data EntityTemplate
     * @return DialectEntitySqlBuilder
     */
    DialectEntitySqlBuilder cacheData(EntityTemplate data);

     /**
     * one table insert
     * @return SQL with (+)? , + is 1 or more , is columns count
     */
    String sqlInsert();

    /**
     * one table all columns update
     * @return SQL with (+)? , + is 1 or more , is columns+keys count
     */
    String sqlUpdateById();

    /**
     * one table select
     * @return SQL with (+)? , + is 1 or more , is keys count
     */
    String sqlSelectById();

    /**
     * one table delete
     * @return SQL with (+)? , + is 1 or more , is keys count
     */
    String sqlDeleteById();

    /**
     * one table status column update
     * @return SQL with (+)? , + is 1 or more , is keys count
     */
    String sqlUpdateStatusById();

    /**
     * one table more row insert
     * @param count row count
     * @return SQL with (count)*(+)? , + is 1 or more , is columns count
     */
    String sqlInsert(int count);

    /**
     * one table more row status column update
     * @param count row count
     * @return SQL with (count)?
     */
    String sqlUpdate(int count);


    /**
     * one table more row select
     * @return SQL with (?)? , ? is 1 or 0 , if status column used and config it on , it will 1
     */
    String sqlSelectByStatus();

    /**
     * one table more row count select
     * @return SQL with (?)? , ? is 1 or 0 , if status column used and config it on , it will 1
     */
    String sqlSelectCount();

    /**
     * one table more row count delete
     * @param count row count
     * @return SQL with (count)?
     */
    String sqlDeleteByIds(int count);

    /**
     * one table more row status column update
     * @param count row count
     * @return SQL with (count)?
     */
    String sqlUpdateStatusByIds(int count);

}
