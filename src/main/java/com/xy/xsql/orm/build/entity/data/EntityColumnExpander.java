package com.xy.xsql.orm.build.entity.data;

import com.xy.xsql.orm.build.BaseBuilder;
import com.xy.xsql.orm.data.entity.EntityColumn;
import com.xy.xsql.orm.data.entity.EntityLink;
import com.xy.xsql.orm.data.entity.EntityTemplateData;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/12/1.
 */
@SuppressWarnings("Duplicates")
public class EntityColumnExpander implements BaseBuilder<EntityTemplateData,List<EntityColumn>> {

    protected static final Log log = LogFactory.getLog(EntityColumnExpander.class);


    @Override
    public List<EntityColumn> build(EntityTemplateData entityData) {
        List<EntityColumn> result = new ArrayList<>();
        result.addAll(entityData.getColumns());

        if(entityData.getLinks() != null){
            for (EntityLink entityLinkEntity: entityData.getLinks()) {
                if(entityLinkEntity.getEntityTemplateData() == null){
                    continue;
                }
                List<EntityColumn> resultSub = this.build(entityLinkEntity.getEntityTemplateData());
                result.addAll(resultSub);
            }
        }
        return result;
    }
}
