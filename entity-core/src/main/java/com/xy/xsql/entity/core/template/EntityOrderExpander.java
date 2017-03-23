package com.xy.xsql.entity.core.template;

import com.xy.xsql.core.builder.BaseBuilder;
import com.xy.xsql.entity.model.template.EntityLink;
import com.xy.xsql.entity.model.template.EntityOrder;
import com.xy.xsql.entity.model.template.EntityTemplate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/11/25.
 */
public class EntityOrderExpander implements BaseBuilder<EntityTemplate,List<EntityOrder>> {

    protected static final Log log = LogFactory.getLog(EntityOrderExpander.class);
    protected Integer deepMax;

    /**
     * Set Max Deep
     * @param deepMax Max Deep
     * @return This
     */
    public EntityOrderExpander withDeepMax(Integer deepMax) {
        this.deepMax = deepMax;
        return this;
    }


    @Override
    public List<EntityOrder> build(EntityTemplate entityTemplate) {
        if(this.deepMax == null || this.deepMax < 0){
            this.deepMax = -1;
        }
        List<EntityOrder> result = new ArrayList<>();
        Integer deep = 0;
        result.addAll(this.buildSub(entityTemplate,deep));
        return result;
    }

    private List<EntityOrder> buildSub(EntityTemplate entityTemplate, Integer deep){
        List<EntityOrder> result = new ArrayList<>();

        if(entityTemplate.getOrders() != null){
            result.addAll(entityTemplate.getOrders());
        }

        if(this.deepMax != -1 &&
                this.deepMax <= deep){
            return result;
        }

        if(entityTemplate.getLinks() != null){
            for (EntityLink entityLink : entityTemplate.getLinks()) {
                if(entityLink.getTemplate() == null){
                    continue;
                }
                EntityTemplate entityTemplateSub = entityLink.getTemplate();
                deep++;
                List<EntityOrder> resultSub = this.buildSub(
                        entityTemplateSub,
                        deep);
                deep--;
                result.addAll(resultSub);
            }
        }
        return result;
    }

}
