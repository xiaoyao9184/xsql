package com.xy.xsql.orm.core.sentence.data;

import com.xy.xsql.orm.core.x.XInsert;
import com.xy.xsql.orm.data.sql.element.info.Column;
import com.xy.xsql.orm.data.sql.element.info.Table;
import com.xy.xsql.orm.data.sql.element.info.Value;
import com.xy.xsql.orm.data.sql.sentence.insert.InsertSentence;
import com.xy.xsql.orm.util.ListBuilder;
import org.junit.Test;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/11/13.
 */
public class InsertSentenceDataBuilderTest {

    /**
     * string
     */
    @Test
    public void testUseString(){
        XInsert<InsertSentenceDataBuilder> xInsert = new InsertSentenceDataBuilder();
        InsertSentence insertSentence = xInsert
                .insert()
                .into()
                .table("table1")
                .columns("column1","column2")
                .values(2)
                .build(null);

        assert insertSentence != null;
        assert insertSentence.getTable().getName().equals("table1");
        assert insertSentence.getColumn().get(0).getName().equals("column1");
        assert insertSentence.getColumn().get(1).getName().equals("column2");
        assert Value.class.isInstance(insertSentence.getValues().getExpressionList().get(0));
        assert Value.class.isInstance(insertSentence.getValues().getExpressionList().get(1));
    }

    /**
     *
     */
    @Test
    public void testUseQuick(){
        XInsert<InsertSentenceDataBuilder> xInsert = new InsertSentenceDataBuilder();
        InsertSentence insertSentence = xInsert
                .insertInto("table1")
                .columns("column1","column2")
                .values(2)
                .build(null);

        assert insertSentence != null;
        assert insertSentence.getTable().getName().equals("table1");
        assert insertSentence.getColumn().get(0).getName().equals("column1");
        assert insertSentence.getColumn().get(1).getName().equals("column2");
        assert Value.class.isInstance(insertSentence.getValues().getExpressionList().get(0));
        assert Value.class.isInstance(insertSentence.getValues().getExpressionList().get(1));
    }

    /**
     *
     */
    @Test
    public void testUseMultipleGroupValue(){
        XInsert<InsertSentenceDataBuilder> xInsert = new InsertSentenceDataBuilder();
        InsertSentence insertSentence = xInsert
                .insertInto("table1")
                .columns("column1","column2")
                .values(2,2)
                .build(null);

        assert insertSentence != null;
        assert insertSentence.getTable().getName().equals("table1");
        assert insertSentence.getColumn().get(0).getName().equals("column1");
        assert insertSentence.getColumn().get(1).getName().equals("column2");
        assert insertSentence.getGroupValues().size() == 2;
    }

    /**
     *
     */
    @Test
    public void testUseObject(){
        Table table = new Table("table1");
        Column column1 = new Column(table,"column1");
        Column column2 = new Column(table,"column2");
        List<Column> columnList = new ListBuilder<Column>()
                .withItem(column1)
                .withItem(column2)
                .build(null);

        XInsert<InsertSentenceDataBuilder> xInsert = new InsertSentenceDataBuilder();
        InsertSentence insertSentence = xInsert
                .insertInto(table)
                .columns(columnList)
                .values(columnList.size())
                .build(null);

        assert insertSentence != null;
        assert insertSentence.getTable().getName().equals("table1");
        assert insertSentence.getColumn().get(0).getName().equals("column1");
        assert insertSentence.getColumn().get(1).getName().equals("column2");
        assert insertSentence.getValues().getExpressionList().size() == 2;
    }


}
