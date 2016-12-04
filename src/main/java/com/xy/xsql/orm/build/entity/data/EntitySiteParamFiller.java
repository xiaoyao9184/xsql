package com.xy.xsql.orm.build.entity.data;

import com.xy.xsql.orm.build.BaseBuilder;
import com.xy.xsql.orm.data.entity.EntityLink;
import com.xy.xsql.orm.data.entity.EntityParam;
import com.xy.xsql.orm.data.entity.EntityTemplate;
import com.xy.xsql.orm.data.param.EntitySiteParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 填充器
 * Created by xiaoyao9184 on 2016/11/25.
 */
public class EntitySiteParamFiller implements BaseBuilder<EntityTemplate,List<EntitySiteParam>>, Cloneable {


    private static Logger log = LoggerFactory.getLogger(EntitySiteParamFiller.class);


    private List<EntitySiteParam> tarList;
    private List<EntitySiteParam> srcList;
    private Integer deep;

    public EntitySiteParamFiller withEntitySiteParamList(List<EntitySiteParam> srcList){
        this.srcList = srcList;
        return this;
    }

//    public EntitySiteParamFiller withEntityTemplateData(EntityTemplate entityTemplateData){
//        this.entityTemplateData = entityTemplateData;
//        return this;
//    }

    public EntitySiteParamFiller withDeep(Integer deep){
        if(deep < 0){
            log.warn("Deep can not set less than or equal to zero, replace to '1'!");
            deep = 1;
        }
        this.deep = deep;
        return this;
    }


    @Override
    public List<EntitySiteParam> build(EntityTemplate entityTemplate) {
        if(entityTemplate == null){
            throw  new RuntimeException("EntityTemplate is not set, so cant fill it form EntityTemplate!");
        }
        this.tarList = new ArrayList<>();

        int deepNow = 0;
        int indexNow = 0;

        EntitySiteParam paramNow;

        EntitySiteParam siteParam = srcList.get(indexNow);
        if(siteParam.getLinkEntityClass() == null &&
                siteParam.getLinkColumnName() == null){
            indexNow++;
            paramNow = new EntitySiteParam()
                    .withUseLink(true)
                    .withUseArgs(siteParam.getArgs() != null)
                    .withArgs(siteParam.getArgs() != null ? siteParam.getArgs().clone() : null);
        }else{
            paramNow = new EntitySiteParam()
                    .withUseLink(true)
                    .withUseArgs(false);
        }
        deepNow++;
        buildDeep(deepNow,indexNow, entityTemplate,paramNow);

        return tarList;
    }


    private void buildDeep(Integer deepNow, Integer indexNow, EntityTemplate dataNow, EntitySiteParam paramNow){
        if(deepNow > deep){
            return;
        }
        if(paramNow.isUseArgs()){
            //合并参数
            int sizeParam = dataNow.getParams() == null ? 0 : dataNow.getParams().size();
            Object[] params = new Object[sizeParam];
            int indexParam = 0;
            int indexArg = 0;
            while (indexParam < sizeParam){
                EntityParam templateParam = dataNow.getParams().get(indexParam);
                if(templateParam.isNeedValue()){
                    //get form EntitySiteParam
                    params[indexParam] = paramNow.getArgs()[indexArg];
                    indexArg++;
                }else{
                    //get from EntityParam
                    params[indexParam] = templateParam.getArgs()[indexParam];
                }
                indexParam++;
            }

            paramNow.withArgs(params);
        }
        tarList.add(paramNow);

        //子集
        for (EntityLink link: dataNow.getLinks()) {
            EntityTemplate dataSub = link.getTemplate();
            EntitySiteParam paramSub;

            if(indexNow > srcList.size()-1){
                break;
            }
            EntitySiteParam siteParam = srcList.get(indexNow);
            //TODO 根据尸体类获取获取？
            if(link.getColumn().getName().equals(siteParam.getLinkColumnName())){
                indexNow++;
                paramSub = new EntitySiteParam()
                        .withUseLink(true)
                        .withLinkColumnName(siteParam.getLinkColumnName())
                        .withUseArgs(siteParam.getArgs() != null)
                        .withArgs(siteParam.getArgs().clone());
            }else{
                paramSub = new EntitySiteParam()
                        .withUseLink(false);
            }
            deepNow++;
            buildDeep(deepNow,indexNow,dataSub,paramSub);
            deepNow--;
        }

    }
}
