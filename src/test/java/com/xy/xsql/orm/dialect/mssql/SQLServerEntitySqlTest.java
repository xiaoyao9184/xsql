package com.xy.xsql.orm.dialect.mssql;

import com.xy.xsql.orm.core.entity.template.AnnotationEntityTemplateBuilder;
import com.xy.xsql.orm.data.entity.EntityTemplate;
import com.xy.xsql.orm.data.param.ArgSql;
import com.xy.xsql.orm.data.param.EntityTemplateTreeArg;
import com.xy.xsql.orm.test.bean.User;
import com.xy.xsql.orm.test.bean.UserType;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by xiaoyao9184 on 2016/11/17.
 */
public class SQLServerEntitySqlTest {

    @Test
    public void testGetTopWithRowNumberSql(){
        SQLServerEntitySql entitySql = new SQLServerEntitySql();
        String sql = entitySql.getAddRowNumberWithTopSql(
                "SELECT id, name, time FROM test WHERE 1 =1 ORDER BY time",
                "number",
                10);

        Assert.assertEquals(
                sql,
                "SELECT TOP 10\n" +
                        "ROW_NUMBER() OVER ( \n" +
                        "ORDER BY time\n" +
                        "\n" +
                        ") AS number,\n" +
                        "*\n" +
                        "FROM\n" +
                        "(\n" +
                        "SELECT id, name, time FROM test WHERE 1 =1 \n" +
                        ") AS rowTo\n" +
                        "ORDER BY time\n");

        String src = "SELECT    \n" +
                "\t\tlaws.id ,\n" +
                "        laws.title ,\n" +
                "        laws.content ,\n" +
                "        laws.level ,\n" +
                "        laws.release_unit ,\n" +
                "        laws.effective_time ,\n" +
                "        laws.invalid_time ,\n" +
                "        laws.factor ,\n" +
                "        laws.region ,\n" +
                "        laws.type ,\n" +
                "        laws.status ,\n" +
                "        laws.time ,\n" +
                "        laws.description ,\n" +
                "        dicttype1.name type_name ,\n" +
                "        dicttype1.sort sort ,\n" +
                "        dicttype2.name level_name\n" +
                "FROM    sur_laws AS laws\n" +
                "        LEFT JOIN sur_dict AS dicttype1 ON laws.type = dicttype1.id\n" +
                "        LEFT JOIN sur_dict AS dicttype2 ON laws.level = dicttype2.id\n" +
                "WHERE   1 = 1\n" +
                "ORDER BY sort, title ASC";
        sql = entitySql.getAddRowNumberWithTopSql(
                src,
                "number",
                10);
        Assert.assertEquals(sql,"SELECT TOP 10\n" +
                "ROW_NUMBER() OVER ( \n" +
                "ORDER BY sort, title ASC\n" +
                "\n" +
                ") AS number,\n" +
                "*\n" +
                "FROM\n" +
                "(\n" +
                "SELECT    \n" +
                "\t\tlaws.id ,\n" +
                "        laws.title ,\n" +
                "        laws.content ,\n" +
                "        laws.level ,\n" +
                "        laws.release_unit ,\n" +
                "        laws.effective_time ,\n" +
                "        laws.invalid_time ,\n" +
                "        laws.factor ,\n" +
                "        laws.region ,\n" +
                "        laws.type ,\n" +
                "        laws.status ,\n" +
                "        laws.time ,\n" +
                "        laws.description ,\n" +
                "        dicttype1.name type_name ,\n" +
                "        dicttype1.sort sort ,\n" +
                "        dicttype2.name level_name\n" +
                "FROM    sur_laws AS laws\n" +
                "        LEFT JOIN sur_dict AS dicttype1 ON laws.type = dicttype1.id\n" +
                "        LEFT JOIN sur_dict AS dicttype2 ON laws.level = dicttype2.id\n" +
                "WHERE   1 = 1\n" +
                "\n" +
                ") AS rowTo\n" +
                "ORDER BY sort, title ASC\n");
    }


    @Test
    public void testGetSelectArgPageSql(){
        EntityTemplate entityTemplate = new AnnotationEntityTemplateBuilder()
                .configStart()
                    .withScanAll(true)
                    .out()
                .build(User.class);

        SQLServerEntitySql entitySql = new SQLServerEntitySql();

        ArgSql sql = entitySql.getSelectArgsPageSql(
                entityTemplate,
                1,
                10,
                "number",
                "name1","code1","type1");

        Assert.assertEquals(
                sql.getSql(),
                "SELECT\n" +
                        " * \n" +
                        "FROM (\n" +
                        "SELECT TOP 10\n" +
                        "ROW_NUMBER() OVER (\n" +
                        "ORDER BY\n" +
                        " time ASC\n" +
                        ") AS number\n" +
                        ",id\n" +
                        ",name\n" +
                        ",code\n" +
                        ",type\n" +
                        ",status\n" +
                        ",time\n" +
                        "FROM\n" +
                        "b_user\n" +
                        "WHERE\n" +
                        " name = ?\n" +
                        "AND code = ?\n" +
                        "AND type = ?\n" +
                        "ORDER BY\n" +
                        " time ASC\n" +
                        ") AS temp\n" +
                        "WHERE\n" +
                        "number > 0\n");
    }

    @Test
    public void testGetSelectArgCountSql(){
        EntityTemplate entityTemplate = new AnnotationEntityTemplateBuilder()
                .configStart()
                    .withScanAll(true)
                    .out()
                .build(User.class);

        SQLServerEntitySql entitySql = new SQLServerEntitySql();

        ArgSql sql = entitySql.getSelectArgsCountSql(
                entityTemplate,
                "name1","code1","type1");

        Assert.assertEquals(
                sql.getSql(),
                "SELECT\n" +
                        "COUNT(*)\n" +
                        "FROM\n" +
                        "b_user\n" +
                        "WHERE\n" +
                        " name = ?\n" +
                        "AND code = ?\n" +
                        "AND type = ?\n");
    }


    @Test
    public void testGetSelectJoinByTreeArgPageSql(){
        EntityTemplate entityTemplate = new AnnotationEntityTemplateBuilder()
                .configStart()
                    .withScanAll(true)
                    .out()
                .build(User.class);

        SQLServerEntitySql entitySql = new SQLServerEntitySql();

        ArgSql sql = entitySql.getSelectJoinByTreeArgPageSql(
                entityTemplate,
                1,
                10,
                "number",
                new EntityTemplateTreeArg()
                        .withArgs("name1","code1","type1")
                        .withSub(new EntityTemplateTreeArg()
                                .withClass(UserType.class)
                                .withArgs("name1","code1")));

        Assert.assertEquals(
                sql.getSql(),
                "SELECT\n" +
                        " * \n" +
                        "FROM (\n" +
                        "SELECT TOP 10\n" +
                        "ROW_NUMBER() OVER (\n" +
                        "ORDER BY\n" +
                        " u.time ASC\n" +
                        ") AS number\n" +
                        ",u.id AS id\n" +
                        ",u.name AS name\n" +
                        ",u.code AS code\n" +
                        ",u.type AS typeID\n" +
                        ",u.status AS status\n" +
                        ",u.time AS time\n" +
                        ",typeID_ut.id AS typeID_id\n" +
                        ",typeID_ut.name AS typeID_name\n" +
                        ",typeID_ut.code AS typeID_code\n" +
                        ",typeID_ut.status AS typeID_status\n" +
                        "FROM\n" +
                        "b_user AS u\n" +
                        "LEFT JOIN\n" +
                        "b_user_type AS typeID_ut\n" +
                        "ON typeID_ut.id = u.type\n" +
                        "WHERE\n" +
                        " u.name = ?\n" +
                        "AND u.code = ?\n" +
                        "AND u.type = ?\n" +
                        "AND typeID_ut.name = ?\n" +
                        "AND typeID_ut.code = ?\n" +
                        "ORDER BY\n" +
                        " u.time ASC\n" +
                        ") AS temp\n" +
                        "WHERE\n" +
                        "number > 0\n");
    }

    @Test
    public void testGetSelectJoinByTreeArgCountSql(){
        EntityTemplate entityTemplate = new AnnotationEntityTemplateBuilder()
                .configStart()
                .withScanAll(true)
                .out()
                .build(User.class);

        SQLServerEntitySql entitySql = new SQLServerEntitySql();

        ArgSql sql = entitySql.getSelectJoinByTreeArgCountSql(
                entityTemplate,
                new EntityTemplateTreeArg()
                        .withArgs("name1","code1","type1")
                        .withSub(new EntityTemplateTreeArg()
                                .withClass(UserType.class)
                                .withArgs("name1","code1")));

        Assert.assertEquals(
                sql.getSql(),
                "SELECT\n" +
                        "COUNT(*)\n" +
                        "FROM\n" +
                        "b_user AS u\n" +
                        "LEFT JOIN\n" +
                        "b_user_type AS typeID_ut\n" +
                        "ON typeID_ut.id = u.type\n" +
                        "WHERE\n" +
                        " u.name = ?\n" +
                        "AND u.code = ?\n" +
                        "AND u.type = ?\n" +
                        "AND typeID_ut.name = ?\n" +
                        "AND typeID_ut.code = ?\n");
    }
}
