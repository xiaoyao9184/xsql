package com.xy.xsql.entity.core.template;

import com.xy.xsql.core.builder.BaseBuilder;
import com.xy.xsql.entity.model.template.EntityLink;
import com.xy.xsql.entity.model.template.EntityOrder;
import com.xy.xsql.entity.model.template.EntityInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/11/25.
 */
public class EntityOrderExpander implements BaseBuilder<EntityInfo,List<EntityOrder>> {

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
    public List<EntityOrder> build(EntityInfo entityInfo) {
        if(this.deepMax == null || this.deepMax < 0){
            this.deepMax = -1;
        }
        List<EntityOrder> result = new ArrayList<>();
        Integer deep = 0;
        result.addAll(this.buildSub(entityInfo,deep));
        return result;
    }

    private List<EntityOrder> buildSub(EntityInfo entityInfo, Integer deep){
        List<EntityOrder> result = new ArrayList<>();

        if(entityInfo.getOrders() != null){
            result.addAll(entityInfo.getOrders());
        }

        if(this.deepMax != -1 &&
                this.deepMax <= deep){
            return result;
        }

        if(entityInfo.getLinks() != null){
            for (EntityLink entityLink : entityInfo.getLinks()) {
                if(entityLink.getTemplate() == null){
                    continue;
                }
                EntityInfo entityInfoSub = entityLink.getTemplate();
                deep++;
                List<EntityOrder> resultSub = this.buildSub(
                        entityInfoSub,
                        deep);
                deep--;
                result.addAll(resultSub);
            }
        }
        return result;
    }

}
