package com.xy.xsql.entity.test.h2.repository;

import com.xy.xsql.entity.test.config.db.h2.DBConfig;
import com.xy.xsql.entity.test.config.spring.MVCConfig;
import com.xy.xsql.entity.test.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by xiaoyao9184 on 2017/3/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        MVCConfig.class,
        DBConfig.class
})
public class UserRepositoryTest {

    @Resource
    private UserRepository userRepository;

    @Test
    @Rollback(false)
    public void testNoError(){
        try{
            userRepository.checkTable();
        }catch (Exception e){
            e.printStackTrace();
            assert false;
        }
        assert true;
    }
}
