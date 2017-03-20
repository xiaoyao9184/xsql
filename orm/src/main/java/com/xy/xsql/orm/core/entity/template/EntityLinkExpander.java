package com.xy.xsql.orm.core.entity.template;

import com.xy.xsql.core.builder.BaseBuilder;
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
public class EntityLinkExpander implements BaseBuilder<EntityTemplate,List<EntityLink>> {

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
    public List<EntityLink> build(EntityTemplate entityTemplate) {
        if(this.deepMax == null || this.deepMax < 0){
            this.deepMax = -1;
        }
        List<EntityLink> result = new ArrayList<>();
        Integer deep = 0;
        result.addAll(this.buildSub(entityTemplate,deep));
        return result;
    }

    private List<EntityLink> buildSub(EntityTemplate entityTemplate, Integer deep) {
        List<EntityLink> result = new ArrayList<>();
        if(entityTemplate.getLinks() != null){
            result.addAll(entityTemplate.getLinks());

            if(this.deepMax != -1 &&
                    this.deepMax <= deep){
                return result;
            }

            for (EntityLink entityLink: entityTemplate.getLinks()) {
                if(entityLink.getTemplate() == null){
                    continue;
                }
                EntityTemplate entityTemplateSub = entityLink.getTemplate();
                deep++;
                List<EntityLink> resultSub = this.buildSub(
                        entityTemplateSub,
                        deep);
                deep--;
                result.addAll(resultSub);
            }
        }
        return result;
    }
}
