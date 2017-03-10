package com.xy.xsql.spring.config;

import com.xy.xsql.orm.core.BaseBuilder;
import com.xy.xsql.orm.core.entity.sql.agreement.SqlEntityCRUD;
import com.xy.xsql.orm.dialect.mssql.SQLServerEntitySql;
import com.xy.xsql.orm.dialect.none.BaseEntitySql;

/**
 * Created by xiaoyao9184 on 2016/11/25.
 */
public class SqlEntityBuilder implements BaseBuilder<DialectType,SqlEntityCRUD> {

    private String name;

    public SqlEntityBuilder withClassName(String name){
        this.name = name;
        return this;
    }

    @Override
    public SqlEntityCRUD build(DialectType dialect) {
        SqlEntityCRUD entityCRUDSql;

        switch (dialect){
            case NONE:
                try {
                    entityCRUDSql = (SqlEntityCRUD) Class.forName(name).newInstance();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case SQLSERVER:
                entityCRUDSql = new SQLServerEntitySql();
                break;
            default:
                entityCRUDSql = new BaseEntitySql();
        }

        return entityCRUDSql;
    }
}
