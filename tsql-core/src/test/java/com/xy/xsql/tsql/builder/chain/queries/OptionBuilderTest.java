package com.xy.xsql.tsql.builder.chain.queries;

import com.xy.xsql.tsql.builder.chain.MockParent;
import com.xy.xsql.tsql.builder.chain.MockParentBuilder;
import com.xy.xsql.tsql.model.queries.Option;
import com.xy.xsql.tsql.model.queries.hints.QueryHint;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.queries.hints.QueryHintBuilder.*;
import static com.xy.xsql.tsql.model.queries.hints.QueryHint.Type.DISABLE_EXTERNALPUSHDOWN;
import static com.xy.xsql.tsql.model.queries.hints.QueryHint.Type.FAST;
import static com.xy.xsql.tsql.model.queries.hints.QueryHint.Type.FORCE_EXTERNALPUSHDOWN;
import static com.xy.xsql.tsql.model.queries.hints.QueryHint.Type.FORCE_ORDER;
import static com.xy.xsql.tsql.model.queries.hints.QueryHint.Type.HASH_GROUP;
import static com.xy.xsql.tsql.model.queries.hints.QueryHint.Type.HASH_JOIN;
import static com.xy.xsql.tsql.model.queries.hints.QueryHint.Type.MERGE_JOIN;
import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2017/3/11.
 */
public class OptionBuilderTest {

    /*
    Examples
    See https://docs.microsoft.com/zh-cn/sql/t-sql/queries/option-clause-transact-sql#examples
     */

    // @formatter:off
    //parent+quick
    /**
     * OPTION (HASH GROUP, FAST 10)
     */
    public Option exampleA = new MockParentBuilder<OptionBuilder<MockParent<Option>>,Option>
            (OptionBuilder.class,Option.class)
            .$child()
                .$($HashGroup(),
                    $Fast(10))
                .and()
            .get();
    // @formatter:on

    @Test
    public void testExampleA(){
        // @formatter:off
        Option option = new OptionBuilder<Void>()
                .withItem()
                    ._QueryHint($HashGroup(),$Fast(10))
                    .and()
                .build();
        // @formatter:on

        assertEquals(option.getQueryOption().size(),2);
        assertEquals(option.getQueryOption().get(0).getClass(), QueryHint.class);
        assertEquals(option.getQueryOption().get(1).getClass(), QueryHint.class);
        QueryHint queryHint = (QueryHint) option.getQueryOption().get(0);
        QueryHint queryHint1 = (QueryHint) option.getQueryOption().get(1);
        assertEquals(queryHint.getType(),HASH_GROUP);
        assertEquals(queryHint1.getType(),FAST);
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
    public Option exampleB = new MockParentBuilder<OptionBuilder<MockParent<Option>>,Option>
            (OptionBuilder.class,Option.class)
            .$child()
                .$("q17")
                .and()
            .get();
    // @formatter:on

    @Test
    public void testExampleB(){
        // @formatter:off
        Option option = new OptionBuilder<Void>()
                .withItem()
                    ._LabelName("q17")
                    .and()
                .build();
        // @formatter:on

        assertEquals(option.getQueryOption().size(),1);
        assertEquals(option.getQueryOption().get(0).getClass(), Option.LabelQueryOption.class);
        Option.LabelQueryOption labelQueryOption = (Option.LabelQueryOption) option.getQueryOption().get(0);
        assertEquals(labelQueryOption.getLabelName().getString(),"q17");
    }


    // @formatter:off
    //parent+quick
    /**
     * OPTION (HASH JOIN);
     */
    public Option exampleC = new MockParentBuilder<OptionBuilder<MockParent<Option>>,Option>
            (OptionBuilder.class,Option.class)
            .$child()
                .$($HashJoin())
                .and()
            .get();
    // @formatter:on

    @SuppressWarnings("Duplicates")
    @Test
    public void testExampleC(){
        // @formatter:off
        Option option = new OptionBuilder<Void>()
                .withItem()
                    ._QueryHint($HashJoin())
                    .and()
                .build();
        // @formatter:on

        assertEquals(option.getQueryOption().size(),1);
        assertEquals(option.getQueryOption().get(0).getClass(), QueryHint.class);
        QueryHint queryHint = (QueryHint) option.getQueryOption().get(0);
        assertEquals(queryHint.getType(),HASH_JOIN);
    }


    // @formatter:off
    //parent+quick
    /**
     * OPTION ( Label = 'CustJoin', HASH JOIN, MERGE JOIN);
     */
    public Option exampleD = new MockParentBuilder<OptionBuilder<MockParent<Option>>,Option>
                (OptionBuilder.class,Option.class)
                .$child()
                    .$("CustJoin")
                    .$($HashJoin(),$MergeJoin())
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleD(){
        // @formatter:off
        Option option = new OptionBuilder<Void>()
                .withItem()
                    ._LabelName("CustJoin")
                    .and()
                .withItem()
                    ._QueryHint($HashJoin(),$MergeJoin())
                    .and()
                .build();
        // @formatter:on

        assertEquals(option.getQueryOption().size(),3);
        assertEquals(option.getQueryOption().get(0).getClass(), Option.LabelQueryOption.class);
        Option.LabelQueryOption labelQueryOption = (Option.LabelQueryOption) option.getQueryOption().get(0);
        assertEquals(labelQueryOption.getLabelName().getString(),"CustJoin");

        assertEquals(option.getQueryOption().get(1).getClass(), QueryHint.class);
        QueryHint queryHint = (QueryHint) option.getQueryOption().get(1);
        assertEquals(queryHint.getType(),HASH_JOIN);

        assertEquals(option.getQueryOption().get(2).getClass(), QueryHint.class);
        QueryHint queryHint2 = (QueryHint) option.getQueryOption().get(2);
        assertEquals(queryHint2.getType(),MERGE_JOIN);
    }


    // @formatter:off
    //parent+quick
    /**
     * OPTION (HASH JOIN);
     */
    public Option exampleE = new MockParentBuilder<OptionBuilder<MockParent<Option>>,Option>
                (OptionBuilder.class,Option.class)
                .$child()
                    .$($HashJoin())
                    .and()
                .get();
    // @formatter:on

    @SuppressWarnings("Duplicates")
    @Test
    public void testExampleE(){
        // @formatter:off
        Option option = new OptionBuilder<Void>()
                .withItem()
                    ._QueryHint($HashJoin())
                    .and()
                .build();
        // @formatter:on

        assertEquals(option.getQueryOption().size(),1);

        assertEquals(option.getQueryOption().get(0).getClass(), QueryHint.class);
        QueryHint queryHint = (QueryHint) option.getQueryOption().get(0);
        assertEquals(queryHint.getType(),HASH_JOIN);
    }


    // @formatter:off
    //parent+quick
    /**
     * OPTION (HASH JOIN);
     */
    public Option exampleF = new MockParentBuilder<OptionBuilder<MockParent<Option>>,Option>
                (OptionBuilder.class,Option.class)
                .$child()
                    .$($HashJoin())
                    .and()
                .get();
    // @formatter:on

    @SuppressWarnings("Duplicates")
    @Test
    public void testExampleF(){
        // @formatter:off
        Option option = new OptionBuilder<Void>()
                .withItem()
                    ._QueryHint($HashJoin())
                    .and()
                .build();
        // @formatter:on

        assertEquals(option.getQueryOption().size(),1);

        assertEquals(option.getQueryOption().get(0).getClass(), QueryHint.class);
        QueryHint queryHint = (QueryHint) option.getQueryOption().get(0);
        assertEquals(queryHint.getType(),HASH_JOIN);
    }


    // @formatter:off
    //parent+quick
    /**
     * OPTION ( FORCE ORDER )
     */
    public Option exampleG = new MockParentBuilder<OptionBuilder<MockParent<Option>>,Option>
                (OptionBuilder.class,Option.class)
                .$child()
                    .$($ForceOrder())
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleG(){
        // @formatter:off
        Option option = new OptionBuilder<Void>()
                .withItem()
                    ._QueryHint($ForceOrder())
                    .and()
                .build();
        // @formatter:on

        assertEquals(option.getQueryOption().size(),1);

        assertEquals(option.getQueryOption().get(0).getClass(), QueryHint.class);
        QueryHint queryHint = (QueryHint) option.getQueryOption().get(0);
        assertEquals(queryHint.getType(),FORCE_ORDER);
    }


    // @formatter:off
    //parent+quick
    /**
     * OPTION (FORCE EXTERNALPUSHDOWN)
     */
    public Option exampleH1 = new MockParentBuilder<OptionBuilder<MockParent<Option>>,Option>
                (OptionBuilder.class,Option.class)
                .$child()
                    .$($ForceExternalpushdown())
                    .and()
                .get();

    /**
     * OPTION (DISABLE EXTERNALPUSHDOWN)
     */
    public Option exampleH2 = new MockParentBuilder<OptionBuilder<MockParent<Option>>,Option>
                (OptionBuilder.class,Option.class)
                .$child()
                    .$($DisableExternalpushdown())
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleH(){
        // @formatter:off
        Option option = new OptionBuilder<Void>()
                .withItem()
                    ._QueryHint($ForceExternalpushdown())
                    .and()
                .build();
        // @formatter:on

        assertEquals(option.getQueryOption().size(),1);

        assertEquals(option.getQueryOption().get(0).getClass(), QueryHint.class);
        QueryHint queryHint = (QueryHint) option.getQueryOption().get(0);
        assertEquals(queryHint.getType(),FORCE_EXTERNALPUSHDOWN);

        // @formatter:off
        Option option2 = new OptionBuilder<Void>()
                .withItem()
                    ._QueryHint($DisableExternalpushdown())
                    .and()
                .build();
        // @formatter:on

        assertEquals(option2.getQueryOption().size(),1);

        assertEquals(option2.getQueryOption().get(0).getClass(), QueryHint.class);
        QueryHint queryHint2 = (QueryHint) option2.getQueryOption().get(0);
        assertEquals(queryHint2.getType(),DISABLE_EXTERNALPUSHDOWN);
    }

}
