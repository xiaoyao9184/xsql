package com.xy.xsql.spring.dialect;


import com.xy.xsql.core.builder.BaseBuilder;
import com.xy.xsql.entity.api.dialect.jpql.TemplateCRUDRenderer;
import com.xy.xsql.entity.dialect.none.BaseTemplateRenderer;
import com.xy.xsql.spring.dialect.mssql.MSSQLTemplateRenderer;

/**
 * Created by xiaoyao9184 on 2016/11/25.
 */
public class TemplateRendererFactory implements BaseBuilder<DialectType,TemplateCRUDRenderer> {

    private String name;

    public TemplateRendererFactory withClassName(String name){
        this.name = name;
        return this;
    }

    @Override
    public TemplateCRUDRenderer build(DialectType dialect) {
        TemplateCRUDRenderer entityCRUDSql;

        switch (dialect){
            case NONE:
                try {
                    entityCRUDSql = (TemplateCRUDRenderer) Class.forName(name).newInstance();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case SQLSERVER:
                entityCRUDSql = new MSSQLTemplateRenderer();
                break;
            default:
                entityCRUDSql = new BaseTemplateRenderer();
        }

        return entityCRUDSql;
    }
}
