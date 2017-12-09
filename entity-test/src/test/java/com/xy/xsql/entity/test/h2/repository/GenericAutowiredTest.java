package com.xy.xsql.entity.test.h2.repository;

import com.xy.xsql.entity.test.config.db.h2.DBConfig;
import com.xy.xsql.entity.test.config.spring.MVCConfig;
import com.xy.xsql.entity.test.repository.GenericRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * Created by xiaoyao9184 on 2017/3/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        MVCConfig.class,
        DBConfig.class
})
public class GenericAutowiredTest {

    public static class Entity001 {

    }

    /**
     * Generic Field
     */
    @Autowired
    private GenericRepository<Entity001> genericFieldRepository;

    /**
     * Cant get Generic Field's Generic type
     */
    @Test
    public void testGenericFieldAutowired(){
        Assert.assertEquals(
                genericFieldRepository.getFirstTypeParameterName(),
                "T");
    }


    public static class Entity002 {

    }

    /**
     * Generic Class
     */
    @Repository
    public static class ClassGenericRepository extends GenericRepository<Entity002> {

    }

    @Autowired
    private ClassGenericRepository genericClassRepository;

    @Test
    public void testGenericClassAutowired(){
        Assert.assertEquals(
                genericClassRepository.getFirstTypeParameterName(),
                Entity002.class.getTypeName());
    }

}
