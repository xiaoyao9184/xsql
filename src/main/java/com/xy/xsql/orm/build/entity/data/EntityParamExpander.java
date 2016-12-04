package com.xy.xsql.orm.build.entity.data;

import com.xy.xsql.orm.build.BaseBuilder;
import com.xy.xsql.orm.data.entity.EntityLink;
import com.xy.xsql.orm.data.entity.EntityParam;
import com.xy.xsql.orm.data.entity.EntityTemplate;
import com.xy.xsql.orm.data.param.EntityTemplateTreeArg;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/11/25.
 */
public class EntityParamExpander implements BaseBuilder<EntityTemplate,List<EntityParam>> {

    protected static final Log log = LogFactory.getLog(EntityParamExpander.class);

    protected EntityTemplateTreeArg entityDataTreeArg;
    protected EntityParamFilter filter;
    protected Integer deepMax;

    /**
     * Set Tree Args
     * @param entityTemplateTreeArg Tree Args
     * @return This
     */
    public EntityParamExpander withTreeArg(EntityTemplateTreeArg entityTemplateTreeArg) {
        this.entityDataTreeArg = entityTemplateTreeArg;
        return this;
    }

    /**
     * Set EntityParamFilter
     * @param filter EntityParamFilter
     * @return This
     */
    public EntityParamExpander withFilter(EntityParamFilter filter) {
        this.filter = filter;
        return this;
    }

    /**
     * Set Max Deep
     * @param deepMax Max Deep
     * @return This
     */
    public EntityParamExpander withDeepMax(Integer deepMax) {
        this.deepMax = deepMax;
        return this;
    }


    @Override
    public List<EntityParam> build(EntityTemplate entityTemplate) {
        if(this.filter == null){
            this.filter = new EntityParamFilter();
        }
        if(this.deepMax == null || this.deepMax < 0){
            this.deepMax = -1;
        }
        List<EntityParam> result = new ArrayList<>();
        Integer deep = 0;
        result.addAll(this.buildSub(entityTemplate,deep,this.entityDataTreeArg));

        return result;
    }

    private List<EntityParam> buildSub(EntityTemplate entityTemplate, Integer deep, EntityTemplateTreeArg entityDataTreeArg){
        List<EntityParam> result = new ArrayList<>();

        if(entityTemplate.getParams() != null){
            List<EntityParam> params = this.filter
                    .withArgs(entityDataTreeArg.getArgs())
                    .build(entityTemplate.getParams());
            result.addAll(params);
        }

        if(this.deepMax != -1 &&
                this.deepMax <= deep){
            return result;
        }

        int index;
        if(entityTemplate.getLinks() != null){
            index = 0;
            for (EntityLink entityLink : entityTemplate.getLinks()) {
                if(entityLink.getTemplate() == null){
                    continue;
                }
                EntityTemplate entityTemplateSub = entityLink.getTemplate();
                EntityTemplateTreeArg entityDataTreeArgSub = entityDataTreeArg.getSubTree(index, entityTemplateSub.getClazz());
                deep++;
                List<EntityParam> resultSub = this.buildSub(
                        entityTemplateSub,
                        deep,
                        entityDataTreeArgSub);
                result.addAll(resultSub);
                index++;
            }
        }
        return result;
    }

}
