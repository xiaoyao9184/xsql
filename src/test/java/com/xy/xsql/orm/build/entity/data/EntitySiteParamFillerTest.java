package com.xy.xsql.orm.build.entity.data;

import com.xy.xsql.orm.build.entity.data.AnnotationEntityTemplateBuilder;
import com.xy.xsql.orm.build.entity.data.EntitySiteParamFiller;
import com.xy.xsql.orm.data.entity.EntityTemplate;
import com.xy.xsql.orm.data.param.EntitySiteParam;
import com.xy.xsql.orm.test.bean.User;
import com.xy.xsql.orm.test.bean.UserLog;
import com.xy.xsql.orm.test.bean.UserType;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/11/28.
 */
public class EntitySiteParamFillerTest {


    @Test
    public void testDeep2WithNoParam(){
        EntityTemplate data = new AnnotationEntityTemplateBuilder()
                .withScanEntity(true)
                .build(User.class);

        List<EntitySiteParam> paramsUser = new ArrayList<>();
        paramsUser.add(new EntitySiteParam());
        paramsUser.add(new EntitySiteParam()
                .withLinkClass(UserType.class));

        List<EntitySiteParam> paramsDeep1 = new EntitySiteParamFiller()
                .withDeep(1)
                .withEntitySiteParamList(paramsUser)
                .build(data);
        Assert.assertEquals(paramsDeep1.size(), 1);
        Assert.assertEquals(paramsDeep1.get(0).isUseLink(),true);
        Assert.assertEquals(paramsDeep1.get(0).isUseArgs(),false);

        List<EntitySiteParam> paramsDeep2 = new EntitySiteParamFiller()
                .withDeep(2)
                .withEntitySiteParamList(paramsUser)
                .build(data);
        Assert.assertEquals(paramsDeep2.size(), 2);
        Assert.assertEquals(paramsDeep2.get(1).isUseLink(),true);
        Assert.assertEquals(paramsDeep2.get(1).isUseArgs(),false);

    }

    @Test
    public void testDeep3WithParam(){
        EntityTemplate data = new AnnotationEntityTemplateBuilder()
                .withScanEntity(true)
                .withScanParam(true)
                .build(UserLog.class);

        List<EntitySiteParam> paramsUserLog = new ArrayList<>();
        paramsUserLog.add(new EntitySiteParam()
                .withArgs("logNew","2016-12-12"));
        paramsUserLog.add(new EntitySiteParam()
                .withLinkClass(User.class)
                .withArgs("name1","code1","type1"));
        paramsUserLog.add(new EntitySiteParam()
                .withLinkClass(UserType.class)
                .withUseLink(true)
                .withArgs("name1","code1"));

        EntitySiteParamFiller filler = new EntitySiteParamFiller()
                .withDeep(2)
                .withEntitySiteParamList(paramsUserLog);


        List<EntitySiteParam> params1 = filler.build(data);

        Assert.assertEquals(params1.size(), 2);
        Assert.assertEquals(params1.get(0).getLinkEntityClass(),User.class);
        Assert.assertEquals(params1.get(1).getLinkEntityClass(),UserType.class);
    }
}
