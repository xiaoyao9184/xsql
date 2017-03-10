package com.xy.xsql.spring.config;


import com.xy.xsql.orm.core.BaseBuilder;
import com.xy.xsql.orm.dialect.mssql.SQLServerTypeMapper;
import com.xy.xsql.orm.dialect.none.AllVarCharTypeMapper;
import com.xy.xsql.orm.mapping.type.TypeMapper;

/**
 * Created by xiaoyao9184 on 2016/12/2.
 */
public class TypeMapperBuilder implements BaseBuilder<DialectType,TypeMapper> {

    private String name;

    public TypeMapperBuilder withClassName(String name){
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
                mapper = new SQLServerTypeMapper();
                break;
            default:
                mapper = new AllVarCharTypeMapper();
        }

        return mapper;
    }
}
