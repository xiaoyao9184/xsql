package com.xy.xsql.orm.build.entity.data;

import com.xy.xsql.orm.build.BaseBuilder;
import com.xy.xsql.orm.data.entity.EntityLink;
import com.xy.xsql.orm.data.entity.EntityParam;
import com.xy.xsql.orm.data.entity.EntityTemplateData;
import com.xy.xsql.orm.data.param.EntityTemplateDataArgTree;
import com.xy.xsql.orm.util.CheckUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/11/25.
 */
public class EntityParamExpander implements BaseBuilder<EntityTemplateData,List<EntityParam>> {

    protected static final Log log = LogFactory.getLog(EntityParamExpander.class);

    private EntityTemplateDataArgTree entityDataTreeArg;

    public EntityParamExpander withTreeArg(EntityTemplateDataArgTree entityDataTreeArg) {
        this.entityDataTreeArg = entityDataTreeArg;
        return this;
    }

    @Override
    public List<EntityParam> build(EntityTemplateData entityData) {
        List<EntityParam> result = new ArrayList<>();
        Integer deep = 0;
        result.addAll(this.buildSub(entityData,deep,this.entityDataTreeArg));

        return result;
    }

    public List<EntityParam> buildSub(EntityTemplateData entityData, Integer deep, EntityTemplateDataArgTree entityDataTreeArg){
        List<EntityParam> result = new ArrayList<>();
        int index = 0;
        for (EntityParam param: entityData.getParams()) {
            if (!param.isNeedValue()) {
                index++;
                result.add(param.clone());
            }else if(CheckUtil.isNull(entityDataTreeArg.getArgs(),index)){
                //实际参数 未设置 忽略
                log.debug("索引超出参数大小，表：" + param.getColumn().getTable().getName() + "-字段" + param.getColumn().getName() + " 的参数被忽略！");
                index++;
            }else{
                result.add(param.clone()
                        .withArgs(entityDataTreeArg.getArgs()[index]));
                index++;
            }
        }

        if(entityData.getLinks() != null){
            index = 0;
            for (EntityLink entityLinkEntity: entityData.getLinks()) {
                if(entityLinkEntity.getEntityTemplateData() == null){
                    continue;
                }
                EntityTemplateData entityDataSub = entityLinkEntity.getEntityTemplateData();
                EntityTemplateDataArgTree entityDataTreeArgSub = entityDataTreeArg.getSubTree(index,entityDataSub.getClazz());
                deep++;
                List<EntityParam> resultSub = this.buildSub(
                        entityDataSub,
                        deep,
                        entityDataTreeArgSub);
                result.addAll(resultSub);
                index++;
            }
        }
        return result;
    }


}
