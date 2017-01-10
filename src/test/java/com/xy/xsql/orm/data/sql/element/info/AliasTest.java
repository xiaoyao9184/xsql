package com.xy.xsql.orm.data.sql.element.info;

import org.junit.Test;

/**
 * Created by xiaoyao9184 on 2017/1/10.
 */
public class AliasTest {

    @Test
    public void testVoid(){
        Alias<Void> voidAlias = new Alias<>();
        voidAlias.withAliasName("test");

        try{
            Void v = voidAlias.withAliasName("test");
        }catch (Exception e){
            assert true;
        }

        try{
            Object obj = voidAlias.withAliasName("test");
            assert obj != null;
        }catch (Exception e){
            assert false;
        }

        assert true;
    }

    @Test
    public void testObject(){
        Alias voidAlias = new Alias();
        voidAlias.withAliasName("test");

        try{
            Object obj = voidAlias.withAliasName("test");
            assert obj != null;
        }catch (Exception e){
            assert false;
        }

        assert true;
    }

}
