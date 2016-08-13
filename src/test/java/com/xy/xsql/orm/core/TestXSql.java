package com.xy.xsql.orm.core;

import org.junit.Test;

/**
 *
 * Created by xiaoyao9184 on 2016/6/26.
 */
public class TestXSql {

    @Test
    public void select(){
        XSql sql = new XSql()
                .select();

        assert sql.toSql().equals("SELECT\n");

        sql.reset().
                select("c1","c2");

        assert sql.toSql().equals("SELECT\n" +
                "\tc1\n" +
                "\t, c2\n");

        sql.reset().
                orderBy();

        assert sql.toSql().equals("ORDER BY\n");
    }


    /**
     *
     * http://www.w3school.com.cn/sql/sql_insert.asp
     */
    @Test
    public void insert(){
        XSql sql = new XSql();
        sql.insert("Persons")
                .values("LastName", "Address");
        assert sql.toSql().equals("INSERT INTO\n" +
                "\tPersons\n" +
                "(LastName, Address)\n" +
                "VALUES \n" +
                "(?, ?)\n");

        sql.reset();
        sql.insert("Persons")
                .values(4);
        assert sql.toSql().equals("INSERT INTO\n" +
                "\tPersons\n" +
                "VALUES \n" +
                "(?, ?, ?, ?)\n");
    }

    /**
     *
     * http://www.w3school.com.cn/sql/sql_update.asp
     */
    @Test
    public void update(){
        XSql sql = new XSql();
        sql.update("Person")
                .set("FirstName")
                .where("LastName","=");
        assert sql.toSql().equals("UPDATE\n" +
                "\tPerson\n" +
                "SET\n" +
                "\tFirstName = ?\n" +
                "WHERE\n" +
                "\tLastName = ?\n");
    }

    /**
     *
     */
    @Test
    public void delete(){
        XSql sql = new XSql();
        sql.delete()
                .from("Person")
                .where("LastName","=");
        assert sql.toSql().equals("DELETE\n" +
                "FROM\n" +
                "\tPerson\n" +
                "WHERE\n" +
                "\tLastName = ?\n");
    }
}
