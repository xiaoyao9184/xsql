package com.xy.xsql.entity.core.template;

import com.xy.xsql.core.builder.BaseBuilder;
import com.xy.xsql.entity.model.template.EntityColumn;
import com.xy.xsql.entity.model.template.EntityInfo;
import com.xy.xsql.entity.model.template.EntityLink;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/12/1.
 */
@SuppressWarnings("Duplicates")
public class EntityColumnExpander implements BaseBuilder<EntityInfo,List<EntityColumn>> {

    protected static final Log log = LogFactory.getLog(EntityColumnExpander.class);
    private Integer deepMax;

    /**
     * Set Max Deep
     * @param deepMax Max Deep
     * @return This
     */
    public EntityColumnExpander withDeepMax(Integer deepMax) {
        this.deepMax = deepMax;
        return this;
    }

    @Override
    public List<EntityColumn> build(EntityInfo entityInfo) {
        if(this.deepMax == null || this.deepMax < 0){
            this.deepMax = -1;
        }
        List<EntityColumn> result = new ArrayList<>();
        Integer deep = 0;
        result.addAll(this.buildSub(entityInfo,deep));
        return result;
    }

    private List<EntityColumn> buildSub(EntityInfo entityInfo, Integer deep){
        List<EntityColumn> result = new ArrayList<>();

        result.addAll(entityInfo.getColumns());

        if(this.deepMax != -1 &&
                this.deepMax <= deep){
            return result;
        }

        if(entityInfo.getLinks() != null){
            for (EntityLink entityLink: entityInfo.getLinks()) {
                if(entityLink.getTemplate() == null){
                    continue;
                }
                EntityInfo entityInfoSub = entityLink.getTemplate();
                deep++;
                List<EntityColumn> resultSub = this.buildSub(
                        entityInfoSub,
                        deep);
                deep--;
                result.addAll(resultSub);
            }
        }
        return result;
    }
}
