package com.xy.xsql.tsql.core.clause;

import com.xy.xsql.tsql.core.MockParent;
import com.xy.xsql.tsql.core.MockParentBuilder;
import com.xy.xsql.tsql.model.clause.Option;
import com.xy.xsql.tsql.model.clause.hints.QueryHint;
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
                .withItem()
                    ._QueryHint(HASH_GROUP(),FAST(10))
                    .and()
                .build();

        //parent+quick
        MockParent<Option> parent = new MockParentBuilder<OptionBuilder<MockParent<Option>>,Option>
                (OptionBuilder.class,Option.class)
                .$child()
                    .$(HASH_GROUP(),
                        FAST(10))
                    .and();
        // @formatter:on

        Assert.assertEquals(option.getQueryOption().size(),2);
        Assert.assertEquals(option.getQueryOption().get(0).getClass(), QueryHint.class);
        Assert.assertEquals(option.getQueryOption().get(1).getClass(), QueryHint.class);
        QueryHint queryHint = (QueryHint) option.getQueryOption().get(0);
        QueryHint queryHint1 = (QueryHint) option.getQueryOption().get(1);
        Assert.assertEquals(queryHint.getType(),HASH_GROUP);
        Assert.assertEquals(queryHint1.getType(),FAST);
    }

    /**
     * OPTION ( LABEL = 'q17' );
     */
    @Test
    public void testExampleB(){
        // @formatter:off
        Option option = new OptionBuilder<Void>()
                .withItem()
                    ._LabelName("q17")
                    .and()
                .build();

        //parent+quick
        MockParent<Option> parent = new MockParentBuilder<OptionBuilder<MockParent<Option>>,Option>
                (OptionBuilder.class,Option.class)
                .$child()
                    .$("q17")
                    .and();
        // @formatter:on

        Assert.assertEquals(option.getQueryOption().size(),1);
        Assert.assertEquals(option.getQueryOption().get(0).getClass(), Option.LabelQueryOption.class);
        Option.LabelQueryOption labelQueryOption = (Option.LabelQueryOption) option.getQueryOption().get(0);
        Assert.assertEquals(labelQueryOption.getLabelName(),"q17");
    }

    /**
     * OPTION (HASH JOIN);
     */
    @Test
    public void testExampleC(){
        // @formatter:off
        Option option = new OptionBuilder<Void>()
                .withItem()
                    ._QueryHint(HASH_JOIN())
                    .and()
                .build();

        //parent+quick
        MockParent<Option> parent = new MockParentBuilder<OptionBuilder<MockParent<Option>>,Option>
                (OptionBuilder.class,Option.class)
                .$child()
                    .$(HASH_JOIN())
                    .and();
        // @formatter:on

        Assert.assertEquals(option.getQueryOption().size(),1);
        Assert.assertEquals(option.getQueryOption().get(0).getClass(), QueryHint.class);
        QueryHint queryHint = (QueryHint) option.getQueryOption().get(0);
        Assert.assertEquals(queryHint.getType(),HASH_JOIN);
    }

    /**
     * OPTION ( Label = 'CustJoin', HASH JOIN, MERGE JOIN);
     */
    @Test
    public void testExampleD(){
        // @formatter:off
        Option option = new OptionBuilder<Void>()
                .withItem()
                    ._LabelName("CustJoin")
                    .and()
                .withItem()
                    ._QueryHint(HASH_JOIN(),MERGE_JOIN())
                    .and()
                .build();

        //parent+quick
        MockParent<Option> parent = new MockParentBuilder<OptionBuilder<MockParent<Option>>,Option>
                (OptionBuilder.class,Option.class)
                .$child()
                    .$("CustJoin")
                    .$(HASH_JOIN(),MERGE_JOIN())
                    .and();
        // @formatter:on

        Assert.assertEquals(option.getQueryOption().size(),3);
        Assert.assertEquals(option.getQueryOption().get(0).getClass(), Option.LabelQueryOption.class);
        Option.LabelQueryOption labelQueryOption = (Option.LabelQueryOption) option.getQueryOption().get(0);
        Assert.assertEquals(labelQueryOption.getLabelName(),"CustJoin");

        Assert.assertEquals(option.getQueryOption().get(1).getClass(), QueryHint.class);
        QueryHint queryHint = (QueryHint) option.getQueryOption().get(1);
        Assert.assertEquals(queryHint.getType(),HASH_JOIN);

        Assert.assertEquals(option.getQueryOption().get(2).getClass(), QueryHint.class);
        QueryHint queryHint2 = (QueryHint) option.getQueryOption().get(2);
        Assert.assertEquals(queryHint2.getType(),MERGE_JOIN);
    }

    /**
     * OPTION (HASH JOIN);
     */
    @Test
    public void testExampleE(){
        // @formatter:off
        Option option = new OptionBuilder<Void>()
                .withItem()
                    ._QueryHint(HASH_JOIN())
                    .and()
                .build();

        //parent+quick
        MockParent<Option> parent = new MockParentBuilder<OptionBuilder<MockParent<Option>>,Option>
                (OptionBuilder.class,Option.class)
                .$child()
                    .$(HASH_JOIN())
                    .and();
        // @formatter:on

        Assert.assertEquals(option.getQueryOption().size(),1);

        Assert.assertEquals(option.getQueryOption().get(0).getClass(), QueryHint.class);
        QueryHint queryHint = (QueryHint) option.getQueryOption().get(0);
        Assert.assertEquals(queryHint.getType(),HASH_JOIN);
    }

    /**
     * OPTION (HASH JOIN);
     */
    @Test
    public void testExampleF(){
        // @formatter:off
        Option option = new OptionBuilder<Void>()
                .withItem()
                    ._QueryHint(HASH_JOIN())
                    .and()
                .build();

        //parent+quick
        MockParent<Option> parent = new MockParentBuilder<OptionBuilder<MockParent<Option>>,Option>
                (OptionBuilder.class,Option.class)
                .$child()
                    .$(HASH_JOIN())
                    .and();
        // @formatter:on

        Assert.assertEquals(option.getQueryOption().size(),1);

        Assert.assertEquals(option.getQueryOption().get(0).getClass(), QueryHint.class);
        QueryHint queryHint = (QueryHint) option.getQueryOption().get(0);
        Assert.assertEquals(queryHint.getType(),HASH_JOIN);
    }

    /**
     * OPTION ( FORCE ORDER )
     */
    @Test
    public void testExampleG(){
        // @formatter:off
        Option option = new OptionBuilder<Void>()
                .withItem()
                    ._QueryHint(FORCE_ORDER())
                    .and()
                .build();

        //parent+quick
        MockParent<Option> parent = new MockParentBuilder<OptionBuilder<MockParent<Option>>,Option>
                (OptionBuilder.class,Option.class)
                .$child()
                    .$(FORCE_ORDER())
                    .and();
        // @formatter:on

        Assert.assertEquals(option.getQueryOption().size(),1);

        Assert.assertEquals(option.getQueryOption().get(0).getClass(), QueryHint.class);
        QueryHint queryHint = (QueryHint) option.getQueryOption().get(0);
        Assert.assertEquals(queryHint.getType(),FORCE_ORDER);
    }

    /**
     * OPTION (FORCE EXTERNALPUSHDOWN);
     * OPTION (DISABLE EXTERNALPUSHDOWN);
     */
    @Test
    public void testExampleH(){
        // @formatter:off
        Option option = new OptionBuilder<Void>()
                .withItem()
                    ._QueryHint(FORCE_EXTERNALPUSHDOWN())
                    .and()
                .build();

        //parent+quick
        MockParent<Option> parent = new MockParentBuilder<OptionBuilder<MockParent<Option>>,Option>
                (OptionBuilder.class,Option.class)
                .$child()
                    .$(FORCE_EXTERNALPUSHDOWN())
                    .and();
        // @formatter:on

        Assert.assertEquals(option.getQueryOption().size(),1);

        Assert.assertEquals(option.getQueryOption().get(0).getClass(), QueryHint.class);
        QueryHint queryHint = (QueryHint) option.getQueryOption().get(0);
        Assert.assertEquals(queryHint.getType(),FORCE_EXTERNALPUSHDOWN);

        // @formatter:off
        Option option2 = new OptionBuilder<Void>()
                .withItem()
                    ._QueryHint(DISABLE_EXTERNALPUSHDOWN())
                    .and()
                .build();

        //parent+quick
        MockParent<Option> parent2 = new MockParentBuilder<OptionBuilder<MockParent<Option>>,Option>
                (OptionBuilder.class,Option.class)
                .$child()
                    .$(DISABLE_EXTERNALPUSHDOWN())
                    .and();
        // @formatter:on

        Assert.assertEquals(option2.getQueryOption().size(),1);

        Assert.assertEquals(option.getQueryOption().get(0).getClass(), QueryHint.class);
        QueryHint queryHint2 = (QueryHint) option2.getQueryOption().get(0);
        Assert.assertEquals(queryHint2.getType(),DISABLE_EXTERNALPUSHDOWN);
    }

}
