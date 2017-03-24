package com.xy.xsql.entity.test.repository;

import com.xy.xsql.entity.test.entity.User;
import com.xy.xsql.spring.dao.impl.EntityDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * Created by xiaoyao9184 on 2017/3/24.
 */
@Repository
public class UserRepository
        extends EntityDaoImpl<User, String> {

}


