package com.xy.xsql.entity.test.h2.template;

import com.xy.xsql.entity.model.definition.RelationshipClass;
import com.xy.xsql.entity.test.config.db.h2.DBConfig;
import com.xy.xsql.entity.test.config.spring.EntityTemplateConfig;
import com.xy.xsql.entity.test.config.spring.MVCConfig;
import com.xy.xsql.entity.test.entity.User;
import com.xy.xsql.entity.test.entity.UserLog;
import com.xy.xsql.spring.template.EntityTemplate;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static com.xy.xsql.entity.model.definition.RelationshipClass.relationship;

/**
 * Created by xiaoyao9184 on 2017/3/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        DBConfig.class,
        MVCConfig.class,
        EntityTemplateConfig.class
})
public class EntityTemplateTest {

    @Resource
    private EntityTemplate entityTemplate;

    @Test
    @Rollback(false)
    public void testClass(){
        try{
            //test manage
            boolean already = entityTemplate.manage(User.class)
                    .checkTable();

            if(already){
                entityTemplate.manage(User.class)
                        .dropTable();

                entityTemplate.manage(User.class)
                        .createTable();
            }

            //test crud
            User u1 = new User();
            u1.setCode("code1");
            u1.setId("id1");
            u1.setTypeID("type1");
            u1.setStatus(1);
            u1.setTime(1000);

            entityTemplate.crud(User.class)
                    .save(u1);
            User u1_0 = entityTemplate.crud(User.class)
                    .getById("id1");
            Assert.assertEquals(u1.getName(),u1_0.getName());

            u1_0.setTime(2000);
            entityTemplate.crud(User.class)
                    .update(u1_0);

            User u1_1 = entityTemplate.crud(User.class)
                    .getById("id1");
            Assert.assertEquals(u1.getTime(),u1_1.getTime());



        }catch (Exception e){
            e.printStackTrace();
            assert false;
        }
        assert true;
    }

    @Test
    @Rollback(false)
    public void testRelationship(){
        RelationshipClass<User,UserLog> relationship = relationship(User.class, UserLog.class);

        //test manage
        boolean already = entityTemplate.manage(relationship)
                .checkTable();

        if(already){
            entityTemplate.manage(relationship)
                    .dropTable();

            entityTemplate.manage(relationship)
                    .createTable();
        }

    }
}
