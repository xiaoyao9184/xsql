package com.xy.xsql.spring.dialect;

import com.xy.xsql.core.builder.BaseBuilder;
import com.xy.xsql.entity.api.dialect.type.TypeMapper;
import com.xy.xsql.entity.core.dialect.none.AllVarcharTypeMapper;
import com.xy.xsql.spring.dialect.h2.H2TypeMapper;
import com.xy.xsql.spring.dialect.mssql.MSSQLTypeMapper;

/**
 * Created by xiaoyao9184 on 2016/12/2.
 */
public class TypeMapperFactory implements BaseBuilder<DialectType,TypeMapper> {

    private String name;

    public TypeMapperFactory withClassName(String name){
        this.name = name;
        return this;
    }

    @Override
    public TypeMapper build(DialectType dialectType) {
        TypeMapper mapper;

        switch (dialectType){
            case NONE:
                try {
                    mapper = (TypeMapper) Class.forName(name).newInstance();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case SQLSERVER:
                mapper = new MSSQLTypeMapper();
                break;
            case H2:
                mapper = new H2TypeMapper();
                break;
            default:
                mapper = new AllVarcharTypeMapper();
        }

        return mapper;
    }
}
