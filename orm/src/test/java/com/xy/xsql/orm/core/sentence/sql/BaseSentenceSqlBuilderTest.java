package com.xy.xsql.orm.core.sentence.sql;

import com.xy.xsql.orm.core.sentence.data.CodeSentenceDataBuilder;
import com.xy.xsql.orm.data.sql.sentence.BaseElementsSentence;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by xiaoyao9184 on 2016/10/23.
 */
public class BaseSentenceSqlBuilderTest {

    @Before
    public void setUp(){

    }


    @Test
    public void testSelectFrom(){
        //SELECT a,b FROM t
        BaseElementsSentence data = new CodeSentenceDataBuilder().select("a","b").from("t").build(null);

        BaseSentenceSqlBuilder builder = new BaseSentenceSqlBuilder();
        String sql = builder.build(data);
        assert sql != null;
        assert sql.equals("SELECT a,b FROM t");
    }

    @Test
    public void testSelectFromWhere(){
        //SELECT * FROM t WHERE a = ?
        BaseElementsSentence data = new CodeSentenceDataBuilder().select("*").from("t").where("a","=").build(null);

        BaseSentenceSqlBuilder builder = new BaseSentenceSqlBuilder();
        String sql = builder.build(data);
        assert sql != null;
        assert sql.equals("SELECT * FROM t WHERE a = ?");
    }

    @Test
    public void testSelectFromJoin(){
        //SELECT * FROM t LEFT JOIN t2 ON t2.a2 = ?
        BaseElementsSentence data = new CodeSentenceDataBuilder().select("*").from("t").leftjoin("t2").on("t2.a2","=").build(null);

        BaseSentenceSqlBuilder builder = new BaseSentenceSqlBuilder();
        String sql = builder.build(data);
        assert sql != null;
        assert sql.equals("SELECT * FROM t LEFT JOIN t2 ON t2.a2 = ?");
    }

    @Test
    public void testInsertValues(){
        //INSERT INTO t (a,b) VALUES (?,?)
        BaseElementsSentence data = new CodeSentenceDataBuilder().insert("t").values("a","b").build(null);

        BaseSentenceSqlBuilder builder = new BaseSentenceSqlBuilder();
        String sql = builder.build(data);
        assert sql != null;
        assert sql.equals("INSERT INTO t (a,b) VALUES (?,?)");
    }


    @Test
    public void testUpdateSet(){
        //UPDATE t SET a = ?, b = ?
        BaseElementsSentence data = new CodeSentenceDataBuilder().update("t").set("a","b").build(null);

        BaseSentenceSqlBuilder builder = new BaseSentenceSqlBuilder();
        String sql = builder.build(data);
        assert sql != null;
        assert sql.equals("UPDATE t SET a = ?,b = ?");
    }

    @Test
    public void testDeleteFrom(){
        //DELETE t FROM t
        BaseElementsSentence data = new CodeSentenceDataBuilder().delete("t").from("t").build(null);

        BaseSentenceSqlBuilder builder = new BaseSentenceSqlBuilder();
        String sql = builder.build(data);
        assert sql != null;
        assert sql.equals("DELETE t FROM t");
    }



}
