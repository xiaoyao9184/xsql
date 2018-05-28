package com.xy.xsql.tsql.style.constructor.introducer.queries;

import com.xy.xsql.tsql.model.queries.Option;
import com.xy.xsql.tsql.model.queries.hints.QueryHint;
import org.junit.Test;

import static com.xy.xsql.tsql.model.queries.hints.QueryHint.Type.HASH_JOIN;
import static com.xy.xsql.tsql.model.queries.hints.QueryHint.Type.MERGE_JOIN;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.option.*;
import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/5/10.
 */
public class optionTest {

    /*
    Examples
    See https://docs.microsoft.com/zh-cn/sql/t-sql/queries/option-clause-transact-sql#examples
     */

    // @formatter:off
    //parent+quick
    /**
     * OPTION (HASH GROUP, FAST 10)
     */
    public Option exampleA = OPTION(
            HASH_GROUP(),
            FAST(10)
    ).build();
    // @formatter:on

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getQueryOption().size(),2);
        assertEquals(exampleA.getQueryOption().get(0).getClass(), QueryHint.class);
        assertEquals(exampleA.getQueryOption().get(1).getClass(), QueryHint.class);
        QueryHint queryHint = (QueryHint) exampleA.getQueryOption().get(0);
        QueryHint queryHint1 = (QueryHint) exampleA.getQueryOption().get(1);
        assertEquals(queryHint.getType(), QueryHint.Type.HASH_GROUP);
        assertEquals(queryHint1.getType(), QueryHint.Type.FAST);
    }


    /*
    Examples: Azure SQL Data Warehouse and Parallel Data Warehouse
    See https://docs.microsoft.com/zh-cn/sql/t-sql/queries/option-clause-transact-sql#examples-includesssdwfullincludessssdwfull-mdmd-and-includesspdwincludessspdw-mdmd
     */

    // @formatter:off
    //parent+quick
    /**
     * OPTION ( LABEL = 'q17' )
     */
    public Option exampleB = OPTION(
            LABEL("q17")
    ).build();
    // @formatter:on

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getQueryOption().size(),1);
        assertEquals(exampleB.getQueryOption().get(0).getClass(), Option.LabelQueryOption.class);
        Option.LabelQueryOption labelQueryOption = (Option.LabelQueryOption) exampleB.getQueryOption().get(0);
        assertEquals(labelQueryOption.getLabelName().getString(),"q17");
    }


    // @formatter:off
    //parent+quick
    /**
     * OPTION (HASH JOIN);
     */
    public Option exampleC = OPTION(
            HASH_JOIN()
    ).build();
    // @formatter:on

    @SuppressWarnings("Duplicates")
    @Test
    public void testExampleC(){
        assertEquals(exampleC.getQueryOption().size(),1);
        assertEquals(exampleC.getQueryOption().get(0).getClass(), QueryHint.class);
        QueryHint queryHint = (QueryHint) exampleC.getQueryOption().get(0);
        assertEquals(queryHint.getType(), HASH_JOIN);
    }


    // @formatter:off
    //parent+quick
    /**
     * OPTION ( Label = 'CustJoin', HASH JOIN, MERGE JOIN);
     */
    public Option exampleD = OPTION(
            LABEL("CustJoin"),
            HASH_JOIN(),
            MERGE_JOIN()
    ).build();
    // @formatter:on

    @Test
    public void testExampleD(){
        assertEquals(exampleD.getQueryOption().size(),3);
        assertEquals(exampleD.getQueryOption().get(0).getClass(), Option.LabelQueryOption.class);
        Option.LabelQueryOption labelQueryOption = (Option.LabelQueryOption) exampleD.getQueryOption().get(0);
        assertEquals(labelQueryOption.getLabelName().getString(),"CustJoin");

        assertEquals(exampleD.getQueryOption().get(1).getClass(), QueryHint.class);
        QueryHint queryHint = (QueryHint) exampleD.getQueryOption().get(1);
        assertEquals(queryHint.getType(),HASH_JOIN);

        assertEquals(exampleD.getQueryOption().get(2).getClass(), QueryHint.class);
        QueryHint queryHint2 = (QueryHint) exampleD.getQueryOption().get(2);
        assertEquals(queryHint2.getType(),MERGE_JOIN);
    }


    // @formatter:off
    //parent+quick
    /**
     * OPTION (HASH JOIN);
     */
    public Option exampleE = OPTION(
            HASH_JOIN()
    ).build();
    // @formatter:on

    @SuppressWarnings("Duplicates")
    @Test
    public void testExampleE(){
        assertEquals(exampleE.getQueryOption().size(),1);

        assertEquals(exampleE.getQueryOption().get(0).getClass(), QueryHint.class);
        QueryHint queryHint = (QueryHint) exampleE.getQueryOption().get(0);
        assertEquals(queryHint.getType(), HASH_JOIN);
    }


    // @formatter:off
    //parent+quick
    /**
     * OPTION (HASH JOIN);
     */
    public Option exampleF = OPTION(
            HASH_JOIN()
    ).build();
    // @formatter:on

    @SuppressWarnings("Duplicates")
    @Test
    public void testExampleF(){
        assertEquals(exampleF.getQueryOption().size(),1);

        assertEquals(exampleF.getQueryOption().get(0).getClass(), QueryHint.class);
        QueryHint queryHint = (QueryHint) exampleF.getQueryOption().get(0);
        assertEquals(queryHint.getType(), HASH_JOIN);
    }


    // @formatter:off
    //parent+quick
    /**
     * OPTION ( FORCE ORDER )
     */
    public Option exampleG = OPTION(
            FORCE_ORDER()
    ).build();
    // @formatter:on

    @Test
    public void testExampleG(){
        assertEquals(exampleG.getQueryOption().size(),1);

        assertEquals(exampleG.getQueryOption().get(0).getClass(), QueryHint.class);
        QueryHint queryHint = (QueryHint) exampleG.getQueryOption().get(0);
        assertEquals(queryHint.getType(), QueryHint.Type.FORCE_ORDER);
    }


    // @formatter:off
    //parent+quick
    /**
     * OPTION (FORCE EXTERNALPUSHDOWN)
     */
    public Option exampleH1 = OPTION(
            FORCE_EXTERNALPUSHDOWN()
    ).build();

    /**
     * OPTION (DISABLE EXTERNALPUSHDOWN)
     */
    public Option exampleH2 = OPTION(
            DISABLE_EXTERNALPUSHDOWN()
    ).build();
    // @formatter:on

    @Test
    public void testExampleH(){
        assertEquals(exampleH1.getQueryOption().size(),1);

        assertEquals(exampleH1.getQueryOption().get(0).getClass(), QueryHint.class);
        QueryHint queryHint = (QueryHint) exampleH1.getQueryOption().get(0);
        assertEquals(queryHint.getType(), QueryHint.Type.FORCE_EXTERNALPUSHDOWN);


        assertEquals(exampleH2.getQueryOption().size(),1);

        assertEquals(exampleH2.getQueryOption().get(0).getClass(), QueryHint.class);
        QueryHint queryHint2 = (QueryHint) exampleH2.getQueryOption().get(0);
        assertEquals(queryHint2.getType(), QueryHint.Type.DISABLE_EXTERNALPUSHDOWN);
    }

}