package com.xy.xsql.orm.build.entity.data;

import com.xy.xsql.orm.build.BaseBuilder;
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
public class EntityLinkExpander implements BaseBuilder<EntityTemplateData,List<EntityLink>> {

    protected static final Log log = LogFactory.getLog(EntityLinkExpander.class);


    @Override
    public List<EntityLink> build(EntityTemplateData entityData) {
        List<EntityLink> result = new ArrayList<>();
        if(entityData.getLinks() != null){
            result.addAll(entityData.getLinks());

            for (EntityLink entityLinkEntity: entityData.getLinks()) {
                if(entityLinkEntity.getEntityTemplateData() == null){
                    continue;
                }
                List<EntityLink> resultSub = this.build(entityLinkEntity.getEntityTemplateData());
                result.addAll(resultSub);
            }
        }
        return result;
    }
}
