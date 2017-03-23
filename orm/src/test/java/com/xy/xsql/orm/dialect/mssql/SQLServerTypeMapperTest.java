package com.xy.xsql.orm.dialect.mssql;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by xiaoyao9184 on 2016/11/17.
 */
@Deprecated
public class SQLServerTypeMapperTest {

    @Test
    public void testTypeMapper(){
        SQLServerTypeMapper mapper = new SQLServerTypeMapper();

        assert mapper.isSupport(Boolean.class);
        assert mapper.isSupport(Integer.class);
        assert mapper.isSupport(Long.class);
        assert mapper.isSupport(Float.class);
        assert mapper.isSupport(Double.class);
        assert mapper.isSupport(String.class);
        assert mapper.isSupport(Date.class);
        assert mapper.isSupport(BigDecimal.class);

        assert mapper.mapType(Boolean.class).equals("bit");
        assert mapper.mapType(Integer.class).equals("int");
        assert mapper.mapType(Long.class).equals("bigint");
        assert mapper.mapType(Float.class).equals("float");
        assert mapper.mapType(Double.class).equals("float");
        assert mapper.mapType(String.class).equals("varchar");
        assert mapper.mapType(Date.class).equals("datetime");
        assert mapper.mapType(BigDecimal.class).equals("decimal");

        assert mapper.defaultLength(String.class).equals(255);
    }

}
