package com.xy.xsql.orm.build.entity.data;

import com.xy.xsql.orm.build.BaseBuilder;
import com.xy.xsql.orm.data.entity.EntityColumn;
import com.xy.xsql.orm.data.entity.EntityLink;
import com.xy.xsql.orm.data.entity.EntityTemplate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/12/1.
 */
@SuppressWarnings("Duplicates")
public class EntityColumnExpander implements BaseBuilder<EntityTemplate,List<EntityColumn>> {

    protected static final Log log = LogFactory.getLog(EntityColumnExpander.class);


    @Override
    public List<EntityColumn> build(EntityTemplate entityData) {
        List<EntityColumn> result = new ArrayList<>();
        result.addAll(entityData.getColumns());

        if(entityData.getLinks() != null){
            for (EntityLink entityLinkEntity: entityData.getLinks()) {
                if(entityLinkEntity.getEntityTemplate() == null){
                    continue;
                }
                List<EntityColumn> resultSub = this.build(entityLinkEntity.getEntityTemplate());
                result.addAll(resultSub);
            }
        }
        return result;
    }
}
