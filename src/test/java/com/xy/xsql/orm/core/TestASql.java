package com.xy.xsql.orm.core;

import com.xy.xsql.orm.data.config.ASqlConfig;
import com.xy.xsql.orm.test.bean.User;
import org.junit.Before;
import org.junit.Test;

/**
 * Test ASql
 * Created by xiaoyao9184 on 2016/6/26.
 */
public class TestASql {


    ASql aSql = null;

    @Before
    public void setUp(){
        ASqlConfig config = new ASqlConfig(User.class);
        config.setOnlySelectUseStatus(true);
        aSql = new ASql(config);
    }

    @Test
    public void oneInsert(){
        String sql = aSql.createInsertSql();
        assert sql.equals("INSERT INTO\n" +
                "\tb_user\n" +
                "(id, name, code, type, status)\n" +
                "VALUES \n" +
                "(?, ?, ?, ?, ?)\n");
    }

    @Test
    public void oneUpdate(){
        String sql = aSql.createUpdateSql();
        assert sql.equals("UPDATE\n" +
                "\tb_user\n" +
                "SET\n" +
                "\tid = ?, name = ?, code = ?, type = ?, status = ?\n" +
                "WHERE\n" +
                "\tid = ?\n");
    }

    @Test
    public void oneSelect(){
        String sql = aSql.createSelectSql();
        assert sql.equals("SELECT\n" +
                "\tu.id\n" +
                "\t, u.name\n" +
                "\t, u.code\n" +
                "\t, u.type AS typeID\n" +
                "\t, u.status\n" +
                "FROM\n" +
                "\tb_user\n" +
                "WHERE\n" +
                "\tid = ?\n");
    }

    @Test
    public void oneDelete(){
        String sql = aSql.createDeleteSql();
        assert sql.equals("DELETE\n" +
                "FROM\n" +
                "\tb_user\n" +
                "WHERE\n" +
                "\tid = ?\n");
    }

    @Test
    public void oneUpdateStatus(){
        String sql = aSql.createUpdateStatusSql();
        assert sql.equals("UPDATE\n" +
                "\tb_user\n" +
                "SET\n" +
                "\tstatus = ?\n" +
                "WHERE\n" +
                "\tid = ?\n");
    }






    @Test
    public void allInsert(){
        String sql = aSql.createInsertAllSql(10);
        assert sql.equals("INSERT INTO\n" +
                "\tb_user\n" +
                "(id, name, code, type, status)\n" +
                "VALUES \n" +
                "(?, ?, ?, ?, ?)\n" +
                ",(?, ?, ?, ?, ?)\n" +
                ",(?, ?, ?, ?, ?)\n" +
                ",(?, ?, ?, ?, ?)\n" +
                ",(?, ?, ?, ?, ?)\n" +
                ",(?, ?, ?, ?, ?)\n" +
                ",(?, ?, ?, ?, ?)\n" +
                ",(?, ?, ?, ?, ?)\n" +
                ",(?, ?, ?, ?, ?)\n" +
                ",(?, ?, ?, ?, ?)\n");
    }

    @Test
    public void allUpdate(){
        String sql = aSql.createUpdateAllSql(10);
        assert sql.equals("UPDATE\n" +
                "\tb_user\n" +
                "SET\n" +
                "\tid = \n" +
                "CASE id\n" +
                "\tWHEN ? THEN ?\n" +
                "\tWHEN ? THEN ?\n" +
                "\tWHEN ? THEN ?\n" +
                "\tWHEN ? THEN ?\n" +
                "\tWHEN ? THEN ?\n" +
                "\tWHEN ? THEN ?\n" +
                "\tWHEN ? THEN ?\n" +
                "\tWHEN ? THEN ?\n" +
                "\tWHEN ? THEN ?\n" +
                "\tWHEN ? THEN ?\n" +
                "END, name = \n" +
                "CASE id\n" +
                "\tWHEN ? THEN ?\n" +
                "\tWHEN ? THEN ?\n" +
                "\tWHEN ? THEN ?\n" +
                "\tWHEN ? THEN ?\n" +
                "\tWHEN ? THEN ?\n" +
                "\tWHEN ? THEN ?\n" +
                "\tWHEN ? THEN ?\n" +
                "\tWHEN ? THEN ?\n" +
                "\tWHEN ? THEN ?\n" +
                "\tWHEN ? THEN ?\n" +
                "END, code = \n" +
                "CASE id\n" +
                "\tWHEN ? THEN ?\n" +
                "\tWHEN ? THEN ?\n" +
                "\tWHEN ? THEN ?\n" +
                "\tWHEN ? THEN ?\n" +
                "\tWHEN ? THEN ?\n" +
                "\tWHEN ? THEN ?\n" +
                "\tWHEN ? THEN ?\n" +
                "\tWHEN ? THEN ?\n" +
                "\tWHEN ? THEN ?\n" +
                "\tWHEN ? THEN ?\n" +
                "END, type = \n" +
                "CASE id\n" +
                "\tWHEN ? THEN ?\n" +
                "\tWHEN ? THEN ?\n" +
                "\tWHEN ? THEN ?\n" +
                "\tWHEN ? THEN ?\n" +
                "\tWHEN ? THEN ?\n" +
                "\tWHEN ? THEN ?\n" +
                "\tWHEN ? THEN ?\n" +
                "\tWHEN ? THEN ?\n" +
                "\tWHEN ? THEN ?\n" +
                "\tWHEN ? THEN ?\n" +
                "END, status = \n" +
                "CASE id\n" +
                "\tWHEN ? THEN ?\n" +
                "\tWHEN ? THEN ?\n" +
                "\tWHEN ? THEN ?\n" +
                "\tWHEN ? THEN ?\n" +
                "\tWHEN ? THEN ?\n" +
                "\tWHEN ? THEN ?\n" +
                "\tWHEN ? THEN ?\n" +
                "\tWHEN ? THEN ?\n" +
                "\tWHEN ? THEN ?\n" +
                "\tWHEN ? THEN ?\n" +
                "END\n" +
                "WHERE\n" +
                "\tid\n" +
                "IN\n" +
                "\t(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)\n");
    }

    @Test
    public void allSelect(){
        String sql = aSql.createSelectAllSql();
        assert sql.equals("SELECT\n" +
                "\tu.id\n" +
                "\t, u.name\n" +
                "\t, u.code\n" +
                "\t, u.type AS typeID\n" +
                "\t, u.status\n" +
                "FROM\n" +
                "\tb_user\n" +
                "WHERE\n" +
                "\tstatus = ?\n");
    }

    @Test
    public void allSelectCount(){
        String sql = aSql.createSelectAllCountSql();
        assert sql.equals("SELECT\n" +
                "\tCOUNT(*)\n" +
                "FROM\n" +
                "\tb_user\n" +
                "WHERE\n" +
                "\tstatus = ?\n");
    }

    @Test
    public void allDelete(){
        String sql = aSql.createDeleteAllSql(10);
        assert sql.equals("DELETE\n" +
                "FROM\n" +
                "\tb_user\n" +
                "WHERE\n" +
                "\tid\n" +
                "IN\n" +
                "\t(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)\n");
    }

    @Test
    public void allUpdateStatus(){
        String sql = aSql.createUpdateAllStatusSql(10);
        assert sql.equals("UPDATE\n" +
                "\tb_user\n" +
                "SET\n" +
                "\tstatus = ?\n" +
                "WHERE\n" +
                "\tid\n" +
                "IN\n" +
                "\t(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)\n");
    }

}
