package com.xy.xsql.tsql.builder.chain.queries.predicates;

import com.xy.xsql.tsql.model.queries.predicates.Contains;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2017/3/16.
 */
public class ContainsPredicateBuilderTest {

    /**
     * CONTAINS(Name, 'Mountain');
     */
    @Test
    public void testExampleA(){
        // @formatter:off
        Contains contains = new ContainsPredicateBuilder<Void>()
                .withColumnName("Name")
                .withContainsSearchCondition("Mountain")
                .build();
        // @formatter:on

        assertEquals(contains.getColumnName().toString(),"Name");
        assertEquals(contains.getContainsSearchCondition().getString(),"Mountain");
    }

    /**
     * CONTAINS(Name, ' Mountain OR Road ')
     */
    @Test
    public void testExampleB(){
        // @formatter:off
        Contains contains = new ContainsPredicateBuilder<Void>()
                .withColumnName("Name")
                .withContainsSearchCondition(" Mountain OR Road ")
                .build();
        // @formatter:on

        assertEquals(contains.getColumnName().toString(),"Name");
        assertEquals(contains.getContainsSearchCondition().getString()," Mountain OR Road ");
    }

    /**
     * CONTAINS(Name, ' "Chain*" ');
     */
    @Test
    public void testExampleC(){
        // @formatter:off
        Contains contains = new ContainsPredicateBuilder<Void>()
                .withColumnName("Name")
                .withContainsSearchCondition(" \"Chain*\" ")
                .build();
        // @formatter:on

        assertEquals(contains.getColumnName().toString(),"Name");
        assertEquals(contains.getContainsSearchCondition().getString()," \"Chain*\" ");
    }

    /**
     * CONTAINS(Name, '"chain*" OR "full*"');
     */
    @Test
    public void testExampleD(){
        // @formatter:off
        Contains contains = new ContainsPredicateBuilder<Void>()
                .withColumnName("Name")
                .withContainsSearchCondition("\"chain*\" OR \"full*\"")
                .build();
        // @formatter:on

        assertEquals(contains.getColumnName().toString(),"Name");
        assertEquals(contains.getContainsSearchCondition().getString(),"\"chain*\" OR \"full*\"");
    }

    /**
     * CONTAINS(Comments , 'NEAR((bike,control), 10, TRUE)');
     */
    @Test
    public void testExampleE(){
        // @formatter:off
        Contains contains = new ContainsPredicateBuilder<Void>()
                .withColumnName("Name")
                .withContainsSearchCondition("NEAR((bike,control), 10, TRUE)")
                .build();
        // @formatter:on

        assertEquals(contains.getColumnName().toString(),"Name");
        assertEquals(contains.getContainsSearchCondition().getString(),"NEAR((bike,control), 10, TRUE)");
    }

    /**
     * CONTAINS(Description, ' FORMSOF (INFLECTIONAL, ride) ');
     */
    @Test
    public void testExampleF(){
        // @formatter:off
        Contains contains = new ContainsPredicateBuilder<Void>()
                .withColumnName("Name")
                .withContainsSearchCondition(" FORMSOF (INFLECTIONAL, ride) ")
                .build();
        // @formatter:on

        assertEquals(contains.getColumnName().toString(),"Name");
        assertEquals(contains.getContainsSearchCondition().getString()," FORMSOF (INFLECTIONAL, ride) ");
    }

    /**
     * CONTAINS(Description, 'ISABOUT (performance weight (.8),
     comfortable weight (.4), smooth weight (.2) )' );
     */
    @Test
    public void testExampleG(){
        // @formatter:off
        Contains contains = new ContainsPredicateBuilder<Void>()
                .withColumnName("Description")
                .withContainsSearchCondition("ISABOUT (performance weight (.8),\n" +
"     comfortable weight (.4), smooth weight (.2) )")
                .build();
        // @formatter:on

        assertEquals(contains.getColumnName().toString(),"Description");
        assertEquals(contains.getContainsSearchCondition().getString(),"ISABOUT (performance weight (.8),\n" +
                "     comfortable weight (.4), smooth weight (.2) )");
    }

    /**
     * CONTAINS(Description, @SearchWord)
     */
    @Test
    public void testExampleH(){
        // @formatter:off
        Contains contains = new ContainsPredicateBuilder<Void>()
                .withColumnName("Description")
                //TODO
//                .withContainsSearchCondition(e_variable("SearchWord"))
                .build();
        // @formatter:on

        assertEquals(contains.getColumnName().toString(),"Description");
//        assertEquals(contains.getContainsSearchCondition().toString(),"@SearchWord");
    }

    /**
     * CONTAINS(Description, 'Aluminum $AND spindle');
     */
    @Test
    public void testExampleI(){
        // @formatter:off
        Contains contains = new ContainsPredicateBuilder<Void>()
                .withColumnName("Description")
                .withContainsSearchCondition("Aluminum $AND spindle")
                .build();
        // @formatter:on

        assertEquals(contains.getColumnName().toString(),"Description");
        assertEquals(contains.getContainsSearchCondition().getString(),"Aluminum $AND spindle");
    }

    /**
     * CONTAINS (Comments,
     ' AdventureWorks2008 $AND
     Redmond $AND
     "Mountain-200 Silver" '));
     */
    @Test
    public void testExampleJ(){
        // @formatter:off
        Contains contains = new ContainsPredicateBuilder<Void>()
                .withColumnName("Comments")
                .withContainsSearchCondition(" AdventureWorks2008 $AND\n" +
"     Redmond $AND\n" +
"     \"Mountain-200 Silver\" ")
                .build();
        // @formatter:on

        assertEquals(contains.getColumnName().toString(),"Comments");
        assertEquals(contains.getContainsSearchCondition().getString()," AdventureWorks2008 $AND\n" +
                "     Redmond $AND\n" +
                "     \"Mountain-200 Silver\" ");
    }

    /**
     * CONTAINS(PROPERTY(Document,'Title'), 'Maintenance OR Repair');
     */
    @Test
    public void testExampleK(){
        // @formatter:off
        Contains contains = new ContainsPredicateBuilder<Void>()
                .withProperty("Description","Title")
                .withContainsSearchCondition("Maintenance OR Repair")
                .build();
        // @formatter:on

        assertEquals(contains.getPropertyColumnName().toString(),"Description");
        assertEquals(contains.getPropertyName().getString(),"Title");
        assertEquals(contains.getContainsSearchCondition().getString(),"Maintenance OR Repair");
    }
}
