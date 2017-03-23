package com.xy.xsql.spring.dialect.none;

import com.xy.xsql.entity.dialect.none.AllVarcharTypeMapper;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by xiaoyao9184 on 2016/11/17.
 */
public class AllVarCharTypeMapperTest {

    @Test
    public void testTypeMapper(){
        AllVarcharTypeMapper mapper = new AllVarcharTypeMapper();

        assert mapper.isSupport(Boolean.class);
        assert mapper.isSupport(Integer.class);
        assert mapper.isSupport(Long.class);
        assert mapper.isSupport(Float.class);
        assert mapper.isSupport(Double.class);
        assert mapper.isSupport(String.class);
        assert mapper.isSupport(Date.class);
        assert mapper.isSupport(BigDecimal.class);

        assert mapper.mapType(Boolean.class).equals("VARCHAR");
        assert mapper.mapType(Integer.class).equals("VARCHAR");
        assert mapper.mapType(Long.class).equals("VARCHAR");
        assert mapper.mapType(Float.class).equals("VARCHAR");
        assert mapper.mapType(Double.class).equals("VARCHAR");
        assert mapper.mapType(String.class).equals("VARCHAR");
        assert mapper.mapType(Date.class).equals("VARCHAR");
        assert mapper.mapType(BigDecimal.class).equals("VARCHAR");

        assert mapper.defaultLength(String.class).equals(255);
    }

}
