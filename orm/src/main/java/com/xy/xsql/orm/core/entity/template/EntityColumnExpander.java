package com.xy.xsql.orm.core.entity.template;

import com.xy.xsql.core.builder.BaseBuilder;
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
    public List<EntityColumn> build(EntityTemplate entityTemplate) {
        if(this.deepMax == null || this.deepMax < 0){
            this.deepMax = -1;
        }
        List<EntityColumn> result = new ArrayList<>();
        Integer deep = 0;
        result.addAll(this.buildSub(entityTemplate,deep));
        return result;
    }

    private List<EntityColumn> buildSub(EntityTemplate entityTemplate, Integer deep){
        List<EntityColumn> result = new ArrayList<>();

        result.addAll(entityTemplate.getColumns());

        if(this.deepMax != -1 &&
                this.deepMax <= deep){
            return result;
        }

        if(entityTemplate.getLinks() != null){
            for (EntityLink entityLink: entityTemplate.getLinks()) {
                if(entityLink.getTemplate() == null){
                    continue;
                }
                EntityTemplate entityTemplateSub = entityLink.getTemplate();
                deep++;
                List<EntityColumn> resultSub = this.buildSub(
                        entityTemplateSub,
                        deep);
                deep--;
                result.addAll(resultSub);
            }
        }
        return result;
    }
}
