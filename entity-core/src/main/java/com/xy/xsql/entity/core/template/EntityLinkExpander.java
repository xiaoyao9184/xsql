package com.xy.xsql.entity.core.template;

import com.xy.xsql.core.builder.BaseBuilder;
import com.xy.xsql.entity.model.template.EntityLink;
import com.xy.xsql.entity.model.template.EntityInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/12/1.
 */
@SuppressWarnings("Duplicates")
public class EntityLinkExpander implements BaseBuilder<EntityInfo,List<EntityLink>> {

    protected static final Log log = LogFactory.getLog(EntityLinkExpander.class);
    private Integer deepMax;

    /**
     * Set Max Deep
     * @param deepMax Max Deep
     * @return This
     */
    public EntityLinkExpander withDeepMax(Integer deepMax) {
        this.deepMax = deepMax;
        return this;
    }

    @Override
    public List<EntityLink> build(EntityInfo entityInfo) {
        if(this.deepMax == null || this.deepMax < 0){
            this.deepMax = -1;
        }
        List<EntityLink> result = new ArrayList<>();
        Integer deep = 0;
        result.addAll(this.buildSub(entityInfo,deep));
        return result;
    }

    private List<EntityLink> buildSub(EntityInfo entityInfo, Integer deep) {
        List<EntityLink> result = new ArrayList<>();
        if(entityInfo.getLinks() != null){
            result.addAll(entityInfo.getLinks());

            if(this.deepMax != -1 &&
                    this.deepMax <= deep){
                return result;
            }

            for (EntityLink entityLink: entityInfo.getLinks()) {
                if(entityLink.getTemplate() == null){
                    continue;
                }
                EntityInfo entityInfoSub = entityLink.getTemplate();
                deep++;
                List<EntityLink> resultSub = this.buildSub(
                        entityInfoSub,
                        deep);
                deep--;
                result.addAll(resultSub);
            }
        }
        return result;
    }
}
