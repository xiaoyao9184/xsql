package com.xy.xsql.entity.core.template;

import com.xy.xsql.core.builder.BaseBuilder;
import com.xy.xsql.entity.model.template.EntityInfo;
import com.xy.xsql.entity.model.template.param.EntityTemplateTreeArg;
import com.xy.xsql.entity.model.template.EntityLink;
import com.xy.xsql.entity.model.template.EntityParam;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/11/25.
 */
public class EntityParamExpander implements BaseBuilder<EntityInfo,List<EntityParam>> {

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
    public List<EntityParam> build(EntityInfo entityInfo) {
        if(this.filter == null){
            this.filter = new EntityParamFilter();
        }
        if(this.deepMax == null || this.deepMax < 0){
            this.deepMax = -1;
        }
        List<EntityParam> result = new ArrayList<>();
        Integer deep = 0;
        result.addAll(this.buildSub(entityInfo,deep,this.entityDataTreeArg));
        return result;
    }

    private List<EntityParam> buildSub(EntityInfo entityInfo, Integer deep, EntityTemplateTreeArg entityDataTreeArg){
        List<EntityParam> result = new ArrayList<>();

        if(entityInfo.getParams() != null){
            List<EntityParam> params = this.filter
                    .withArgs(entityDataTreeArg.getArgs())
                    .build(entityInfo.getParams());
            result.addAll(params);
        }

        if(this.deepMax != -1 &&
                this.deepMax <= deep){
            return result;
        }

        int index;
        if(entityInfo.getLinks() != null){
            index = 0;
            for (EntityLink entityLink : entityInfo.getLinks()) {
                if(entityLink.getTemplate() == null){
                    continue;
                }
                EntityInfo entityInfoSub = entityLink.getTemplate();
                EntityTemplateTreeArg entityDataTreeArgSub = entityDataTreeArg.getSubTree(index, entityInfoSub.getClazz());
                if(entityDataTreeArgSub == null){
                    entityDataTreeArgSub = new EntityTemplateTreeArg()
                            .withClass(entityInfoSub.getClazz());
                }else{
                    index++;
                }
                deep++;
                List<EntityParam> resultSub = this.buildSub(
                        entityInfoSub,
                        deep,
                        entityDataTreeArgSub);
                deep--;
                result.addAll(resultSub);
            }
        }
        return result;
    }

}
