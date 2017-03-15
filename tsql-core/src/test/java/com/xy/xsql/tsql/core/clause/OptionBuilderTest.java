package com.xy.xsql.tsql.core.clause;

import com.xy.xsql.tsql.model.clause.Option;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.clause.hints.QueryHintBuilder.DISABLE_EXTERNALPUSHDOWN;
import static com.xy.xsql.tsql.core.clause.hints.QueryHintBuilder.FAST;
import static com.xy.xsql.tsql.core.clause.hints.QueryHintBuilder.FORCE_EXTERNALPUSHDOWN;
import static com.xy.xsql.tsql.core.clause.hints.QueryHintBuilder.FORCE_ORDER;
import static com.xy.xsql.tsql.core.clause.hints.QueryHintBuilder.HASH_GROUP;
import static com.xy.xsql.tsql.core.clause.hints.QueryHintBuilder.HASH_JOIN;
import static com.xy.xsql.tsql.core.clause.hints.QueryHintBuilder.MERGE_JOIN;
import static com.xy.xsql.tsql.model.clause.hints.QueryHint.Type.DISABLE_EXTERNALPUSHDOWN;
import static com.xy.xsql.tsql.model.clause.hints.QueryHint.Type.FAST;
import static com.xy.xsql.tsql.model.clause.hints.QueryHint.Type.FORCE_EXTERNALPUSHDOWN;
import static com.xy.xsql.tsql.model.clause.hints.QueryHint.Type.FORCE_ORDER;
import static com.xy.xsql.tsql.model.clause.hints.QueryHint.Type.HASH_GROUP;
import static com.xy.xsql.tsql.model.clause.hints.QueryHint.Type.HASH_JOIN;
import static com.xy.xsql.tsql.model.clause.hints.QueryHint.Type.MERGE_JOIN;


/**
 * Created by xiaoyao9184 on 2017/3/11.
 */
public class OptionBuilderTest {


    /**
     * OPTION (HASH GROUP, FAST 10);
     */
    @Test
    public void testExampleA(){
        // @formatter:off
        Option option = new OptionBuilder<Void>()
                .withItemQueryHint(HASH_GROUP(),FAST(10))
                .build();
        // @formatter:on

        Assert.assertEquals(option.getQueryOption().size(),2);
        Assert.assertEquals(option.getQueryOption().get(0).getQueryHint().getType(),HASH_GROUP);
        Assert.assertEquals(option.getQueryOption().get(1).getQueryHint().getType(),FAST);
    }

    /**
     * OPTION ( LABEL = 'q17' );
     */
    @Test
    public void testExampleB(){
        // @formatter:off
        Option option = new OptionBuilder<Void>()
                .withItemLabelName("q17")
                .build();
        // @formatter:on

        Assert.assertEquals(option.getQueryOption().size(),1);
        Assert.assertEquals(option.getQueryOption().get(0).getLabelName(),"q17");
    }

    /**
     * OPTION (HASH JOIN);
     */
    @Test
    public void testExampleC(){
        // @formatter:off
        Option option = new OptionBuilder<Void>()
                .withItemQueryHint(HASH_JOIN())
                .build();
        // @formatter:on

        Assert.assertEquals(option.getQueryOption().size(),1);
        Assert.assertEquals(option.getQueryOption().get(0).getQueryHint().getType(),HASH_JOIN);
    }

    /**
     * OPTION ( Label = 'CustJoin', HASH JOIN, MERGE JOIN);
     */
    @Test
    public void testExampleD(){
        // @formatter:off
        Option option = new OptionBuilder<Void>()
                .withItemLabelName("CustJoin")
                .withItemQueryHint(HASH_JOIN(),MERGE_JOIN())
                .build();
        // @formatter:on

        Assert.assertEquals(option.getQueryOption().size(),3);
        Assert.assertEquals(option.getQueryOption().get(0).getLabelName(),"CustJoin");
        Assert.assertEquals(option.getQueryOption().get(1).getQueryHint().getType(),HASH_JOIN);
        Assert.assertEquals(option.getQueryOption().get(2).getQueryHint().getType(),MERGE_JOIN);
    }

    /**
     * OPTION (HASH JOIN);
     */
    @Test
    public void testExampleE(){
        // @formatter:off
        Option option = new OptionBuilder<Void>()
                .withItemQueryHint(HASH_JOIN())
                .build();
        // @formatter:on

        Assert.assertEquals(option.getQueryOption().size(),1);
        Assert.assertEquals(option.getQueryOption().get(0).getQueryHint().getType(),HASH_JOIN);
    }

    /**
     * OPTION (HASH JOIN);
     */
    @Test
    public void testExampleF(){
        // @formatter:off
        Option option = new OptionBuilder<Void>()
                .withItemQueryHint(HASH_JOIN())
                .build();
        // @formatter:on

        Assert.assertEquals(option.getQueryOption().size(),1);
        Assert.assertEquals(option.getQueryOption().get(0).getQueryHint().getType(),HASH_JOIN);
    }

    /**
     * OPTION ( FORCE ORDER )
     */
    @Test
    public void testExampleG(){
        // @formatter:off
        Option option = new OptionBuilder<Void>()
                .withItemQueryHint(FORCE_ORDER())
                .build();
        // @formatter:on

        Assert.assertEquals(option.getQueryOption().size(),1);
        Assert.assertEquals(option.getQueryOption().get(0).getQueryHint().getType(),FORCE_ORDER);
    }

    /**
     * OPTION (FORCE EXTERNALPUSHDOWN);
     * OPTION (DISABLE EXTERNALPUSHDOWN);
     */
    @Test
    public void testExampleH(){
        // @formatter:off
        Option option = new OptionBuilder<Void>()
                .withItemQueryHint(FORCE_EXTERNALPUSHDOWN())
                .build();
        // @formatter:on

        Assert.assertEquals(option.getQueryOption().size(),1);
        Assert.assertEquals(option.getQueryOption().get(0).getQueryHint().getType(),FORCE_EXTERNALPUSHDOWN);

        // @formatter:off
        Option option2 = new OptionBuilder<Void>()
                .withItemQueryHint(DISABLE_EXTERNALPUSHDOWN())
                .build();
        // @formatter:on

        Assert.assertEquals(option2.getQueryOption().size(),1);
        Assert.assertEquals(option2.getQueryOption().get(0).getQueryHint().getType(),DISABLE_EXTERNALPUSHDOWN);
    }

}
