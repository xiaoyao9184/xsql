package com.xy.xsql.orm.core;

import com.xy.xsql.orm.data.cache.ASqlCache;
import com.xy.xsql.orm.data.config.ASqlConfig;
import com.xy.xsql.orm.data.param.EntityParam;
import com.xy.xsql.orm.data.param.EntityParamTree;
import com.xy.xsql.orm.test.bean.User;
import com.xy.xsql.orm.test.bean.UserType;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Test A2Sql
 * Created by xiaoyao9184 on 2016/6/26.
 */
public class TestA2Sql {

    A2Sql aSql = null;

    @Before
    public void setUp() throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        ASqlConfig config = new ASqlConfig(User.class);
        config.setOnlySelectUseStatus(true);
        config.setUseCache(true);

        aSql = ASqlCache.create(config, A2Sql.class);
    }

    /**
     * 树状条件
     */
    @Test
    public void treeParam(){
        EntityParamTree entityParamTree = new EntityParamTree(User.class,"name1","code1");

        assert entityParamTree.getChild().size() == 0;

        entityParamTree.add(UserType.class,"name1","code1");

        assert entityParamTree.getChild().size() == 1;
    }

    /**
     * 填充条件
     */
    @Test
    public void fullParam(){
        List<EntityParam> params = new ArrayList<>();
        params = aSql.fillEntityParam(params,false);
        assert params.size() == 2;
        for (EntityParam param: params) {
            if(!param.isUseColumn()){
                assert param.isUse();
            }else{
                assert !param.isUse();
            }
        }

        params.clear();
        params = aSql.fillEntityParam(params,true);
        assert params.size() == 2;
        for (EntityParam param: params) {
            assert param.isUse();
        }
    }

    /**
     * 多表查询
     * @throws Exception
     */
    @Test
    public void fullSelect() throws Exception {
        List<EntityParam> params = new ArrayList<>();
        params = aSql.fillEntityParam(params,true);
        String sql = aSql.createFullSelectSql(params);
        assert sql.equals("SELECT\n" +
                "\tu.id\n" +
                "\t, u.name\n" +
                "\t, u.code\n" +
                "\t, u.type AS typeID\n" +
                "\t, u.status\n" +
                "\t, type.id AS type_id\n" +
                "\t, type.name AS type_name\n" +
                "\t, type.code AS type_code\n" +
                "\t, type.status AS type_status\n" +
                "FROM\n" +
                "\tb_user AS u\n" +
                "LEFT JOIN\n" +
                "\tb_user_type AS type\n" +
                "\tON type.id = u.type\n");

        sql = aSql.createFullSelectCountSql(params);
        assert sql.equals("SELECT\n" +
                "\tCOUNT(*)\n" +
                "FROM\n" +
                "\t(\n" +
                "SELECT\n" +
                "\tu.id\n" +
                "\t, u.name\n" +
                "\t, u.code\n" +
                "\t, u.type AS typeID\n" +
                "\t, u.status\n" +
                "\t, type.id AS type_id\n" +
                "\t, type.name AS type_name\n" +
                "\t, type.code AS type_code\n" +
                "\t, type.status AS type_status\n" +
                "FROM\n" +
                "\tb_user AS u\n" +
                "LEFT JOIN\n" +
                "\tb_user_type AS type\n" +
                "\tON type.id = u.type\n" +
                "\t)\n" +
                "AS temp\n");
    }

    /**
     * 多表删除
     * @throws Exception
     */
    @Test
    public void fullDelete() throws Exception {
        List<EntityParam> params = new ArrayList<>();
        params = aSql.fillEntityParam(params,true);
        String sql = aSql.createFullDeleteSql(params);
        assert sql.equals("DELETE\n" +
                "\tu\n" +
                "FROM\n" +
                "\tb_user AS u\n" +
                "LEFT JOIN\n" +
                "\tb_user_type AS type\n" +
                "\tON type.id = u.type\n");
    }

    /**
     * 自定义参数多表查询
     * @throws Exception
     */
    @Test
    public void customizeSelect() throws Exception {
        List<EntityParam> params = new ArrayList<>();
        //not use UserType
        EntityParam typeEntityParam = new EntityParam(UserType.class);
        typeEntityParam.setUse(false);
        params.add(typeEntityParam);
        params = aSql.fillEntityParam(params,true);

        String sql = aSql.createFullSelectSql(params);
        assert sql.equals("SELECT\n" +
                "\tu.id\n" +
                "\t, u.name\n" +
                "\t, u.code\n" +
                "\t, u.type AS typeID\n" +
                "\t, u.status\n" +
                "FROM\n" +
                "\tb_user AS u\n");


        params.clear();
        //use User args
        EntityParam mainEntityParam = new EntityParam();
        mainEntityParam.addArgs("name1","code1","type1");
        params.add(mainEntityParam);
        params = aSql.fillEntityParam(params,true);

        sql = aSql.createFullSelectSql(params);
        assert sql.equals("SELECT\n" +
                "\tu.id\n" +
                "\t, u.name\n" +
                "\t, u.code\n" +
                "\t, u.type AS typeID\n" +
                "\t, u.status\n" +
                "\t, type.id AS type_id\n" +
                "\t, type.name AS type_name\n" +
                "\t, type.code AS type_code\n" +
                "\t, type.status AS type_status\n" +
                "FROM\n" +
                "\tb_user AS u\n" +
                "LEFT JOIN\n" +
                "\tb_user_type AS type\n" +
                "\tON type.id = u.type\n" +
                "WHERE\n" +
                "\tu.name = ?\n" +
                "AND\n" +
                "\tu.code = ?\n" +
                "AND\n" +
                "\tu.type = ?\n" +
                "\n");


        params.clear();
        //use User & User args
        mainEntityParam = new EntityParam();
        mainEntityParam.addArgs("name1","code1","type1");
        params.add(mainEntityParam);
        typeEntityParam.setUse(true);
        typeEntityParam.addArgs("name1",null,100);
        params.add(typeEntityParam);
        params = aSql.fillEntityParam(params,true);

        sql = aSql.createFullSelectSql(params);
        assert sql.equals("SELECT\n" +
                "\tu.id\n" +
                "\t, u.name\n" +
                "\t, u.code\n" +
                "\t, u.type AS typeID\n" +
                "\t, u.status\n" +
                "\t, type.id AS type_id\n" +
                "\t, type.name AS type_name\n" +
                "\t, type.code AS type_code\n" +
                "\t, type.status AS type_status\n" +
                "FROM\n" +
                "\tb_user AS u\n" +
                "LEFT JOIN\n" +
                "\tb_user_type AS type\n" +
                "\tON type.id = u.type\n" +
                "WHERE\n" +
                "\tu.name = ?\n" +
                "AND\n" +
                "\tu.code = ?\n" +
                "AND\n" +
                "\tu.type = ?\n" +
                "AND\n" +
                "\ttype.name = ?\n" +
                "\n");

    }

}
