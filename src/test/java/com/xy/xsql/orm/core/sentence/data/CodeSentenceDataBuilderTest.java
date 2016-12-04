package com.xy.xsql.orm.core.sentence.data;

import com.xy.xsql.orm.data.sql.Sentence;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;
import com.xy.xsql.orm.data.sql.element.OperatorEnum;
import com.xy.xsql.orm.data.sql.element.info.Column;
import com.xy.xsql.orm.data.sql.element.info.Param;
import com.xy.xsql.orm.data.sql.element.info.Table;
import com.xy.xsql.orm.data.sql.element.info.Value;
import com.xy.xsql.orm.data.sql.sentence.BaseElementsSentence;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2016/10/15.
 */
public class CodeSentenceDataBuilderTest {

    /**
     * select
     */
    @Test
    public void testSelect(){
        BaseElementsSentence data = new CodeSentenceDataBuilder().select().build(null);
        assert data != null;
        assert data.getData().size() == 1;
        assert GrammarEnum.SELECT.equals(data.getData().get(0));

        BaseElementsSentence data2 = new CodeSentenceDataBuilder().select("c1","c2").build(null);
        assert data2 != null;
        assert data2.getData().size() == 3;
        assert GrammarEnum.SELECT.equals(data2.getData().get(0));
        assert Column.class.isInstance(data2.getData().get(1));
        assert Column.class.isInstance(data2.getData().get(2));

        List<Column> columnList = new ArrayList<>();
        columnList.add(new Column("column1","c1"));
        BaseElementsSentence data3 = new CodeSentenceDataBuilder().select(columnList).build(null);
        assert data3 != null;
        assert data3.getData().size() == 2;
        assert GrammarEnum.SELECT.equals(data3.getData().get(0));
        assert Column.class.isInstance(data3.getData().get(1));

        BaseElementsSentence data4 = new CodeSentenceDataBuilder().distinct().build(null);
        assert data4 != null;
        assert data4.getData().size() == 1;
        assert GrammarEnum.SELECT.equals(data4.getData().get(0));

        BaseElementsSentence data5 = new CodeSentenceDataBuilder().select().top(100,false).build(null);
        assert data5 != null;
        assert data5.getData().size() == 1;
        assert GrammarEnum.SELECT.equals(data5.getData().get(0));
    }

    /**
     * Where
     */
    @Test
    public void testInsert(){
        BaseElementsSentence data = new CodeSentenceDataBuilder().insert().build(null);
        assert data != null;
        assert data.getData().size() == 1;
        assert GrammarEnum.INSERT.equals(data.getData().get(0));

        BaseElementsSentence data2 = new CodeSentenceDataBuilder().insert("t1").build(null);
        assert data2 != null;
        assert data2.getData().size() == 2;
        assert GrammarEnum.INSERT.equals(data2.getData().get(0));
        assert Table.class.isInstance(data2.getData().get(1));

        BaseElementsSentence data3 = new CodeSentenceDataBuilder().insert(new Table("table1","t1")).build(null);
        assert data3 != null;
        assert data3.getData().size() == 2;
        assert GrammarEnum.INSERT.equals(data3.getData().get(0));
        assert Table.class.isInstance(data3.getData().get(1));
    }

    /**
     * Values
     */
    @Test
    public void testValues(){
        BaseElementsSentence data = new CodeSentenceDataBuilder().values(1).build(null);
        assert data != null;
        assert data.getData().size() == 2;
        assert GrammarEnum.VALUES.equals(data.getData().get(0));
        assert Value.class.isInstance(data.getData().get(1));

        BaseElementsSentence data2 = new CodeSentenceDataBuilder().values("c1","c2").build(null);
        assert data2 != null;
        assert data2.getData().size() == 5;
        assert Column.class.isInstance(data2.getData().get(0));
        assert Column.class.isInstance(data2.getData().get(1));
        assert GrammarEnum.VALUES.equals(data2.getData().get(2));
        assert Value.class.isInstance(data2.getData().get(3));
        assert Value.class.isInstance(data2.getData().get(4));

        List<Column> columnList = new ArrayList<>();
        columnList.add(new Column("column1","c1"));
        BaseElementsSentence data3 = new CodeSentenceDataBuilder().values(columnList).build(null);
        assert data3 != null;
        assert data3.getData().size() == 3;
        assert Column.class.isInstance(data3.getData().get(0));
        assert GrammarEnum.VALUES.equals(data3.getData().get(1));
        assert Value.class.isInstance(data3.getData().get(2));

        BaseElementsSentence data4 = new CodeSentenceDataBuilder().values(columnList,2).build(null);
        assert data4 != null;
        assert data4.getData().size() == 4;
        assert Column.class.isInstance(data4.getData().get(0));
        assert GrammarEnum.VALUES.equals(data4.getData().get(1));
        assert Value.class.isInstance(data4.getData().get(2));
        assert Value.class.isInstance(data4.getData().get(3));
    }

    /**
     * Update
     */
    @Test
    public void testUpdate(){
        BaseElementsSentence data = new CodeSentenceDataBuilder().update().build(null);
        assert data != null;
        assert data.getData().size() == 1;
        assert GrammarEnum.UPDATE.equals(data.getData().get(0));

        BaseElementsSentence data2 = new CodeSentenceDataBuilder().update("t1").build(null);
        assert data2 != null;
        assert data2.getData().size() == 2;
        assert GrammarEnum.UPDATE.equals(data2.getData().get(0));
        assert Table.class.isInstance(data2.getData().get(1));
    }

    /**
     * SetItem
     */
    @Test
    public void testSet(){
        BaseElementsSentence data = new CodeSentenceDataBuilder().set("c1","c2").build(null);
        assert data != null;
        assert data.getData().size() == 7;
        assert GrammarEnum.SET.equals(data.getData().get(0));
        assert Column.class.isInstance(data.getData().get(1));
        assert OperatorEnum.class.isInstance(data.getData().get(2));
        assert Value.class.isInstance(data.getData().get(3));
        assert Column.class.isInstance(data.getData().get(4));
        assert OperatorEnum.class.isInstance(data.getData().get(5));
        assert Value.class.isInstance(data.getData().get(6));

        List<Column> columnList = new ArrayList<>();
        columnList.add(new Column("column1","c1"));
        BaseElementsSentence data2 = new CodeSentenceDataBuilder().set(columnList).build(null);
        assert data2 != null;
        assert data2.getData().size() == 4;
        assert GrammarEnum.SET.equals(data2.getData().get(0));
        assert Column.class.isInstance(data2.getData().get(1));
        assert OperatorEnum.class.isInstance(data2.getData().get(2));
        assert Value.class.isInstance(data2.getData().get(3));

        List<Value> valueList = new ArrayList<>();
        valueList.add(new Value());
        BaseElementsSentence data3 = new CodeSentenceDataBuilder().set(columnList,valueList).build(null);
        assert data3 != null;
        assert data3.getData().size() == 4;
        assert GrammarEnum.SET.equals(data3.getData().get(0));
        assert Column.class.isInstance(data3.getData().get(1));
        assert OperatorEnum.class.isInstance(data3.getData().get(2));
        assert Value.class.isInstance(data3.getData().get(3));
    }

    /**
     * Delete
     */
    @Test
    public void testDelete(){
        BaseElementsSentence data = new CodeSentenceDataBuilder().delete().build(null);
        assert data != null;
        assert data.getData().size() == 1;
        assert GrammarEnum.DELETE.equals(data.getData().get(0));

        BaseElementsSentence data2 = new CodeSentenceDataBuilder().delete("t1").build(null);
        assert data2 != null;
        assert data2.getData().size() == 2;
        assert GrammarEnum.DELETE.equals(data2.getData().get(0));
        assert Table.class.isInstance(data2.getData().get(1));

        BaseElementsSentence data3 = new CodeSentenceDataBuilder().delete(new Table("table1","t1")).build(null);
        assert data3 != null;
        assert data3.getData().size() == 2;
        assert GrammarEnum.DELETE.equals(data3.getData().get(0));
        assert Table.class.isInstance(data3.getData().get(1));

        List<Table> tableList = new ArrayList<>();
        tableList.add(new Table("table1","t1"));
        tableList.add(new Table("table2","t2"));
        BaseElementsSentence data4 = new CodeSentenceDataBuilder().delete(tableList).build(null);
        assert data4 != null;
        assert data4.getData().size() == 3;
        assert GrammarEnum.DELETE.equals(data4.getData().get(0));
        assert Table.class.isInstance(data4.getData().get(1));
        assert Table.class.isInstance(data4.getData().get(2));
    }

    /**
     * From
     */
    @Test
    public void testFrom(){
        BaseElementsSentence data = new CodeSentenceDataBuilder().from().build(null);
        assert data != null;
        assert data.getData().size() == 1;
        assert GrammarEnum.FROM.equals(data.getData().get(0));

        BaseElementsSentence data2 = new CodeSentenceDataBuilder().from("c1","c2").build(null);
        assert data2 != null;
        assert data2.getData().size() == 3;
        assert GrammarEnum.FROM.equals(data2.getData().get(0));
        assert Table.class.isInstance(data2.getData().get(1));
        assert Table.class.isInstance(data2.getData().get(2));

        BaseElementsSentence data3 = new CodeSentenceDataBuilder().from(new Table("t")).build(null);
        assert data3 != null;
        assert data3.getData().size() == 2;
        assert GrammarEnum.FROM.equals(data3.getData().get(0));
        assert Table.class.isInstance(data3.getData().get(1));

        List<Table> tableList = new ArrayList<>();
        tableList.add(new Table("table1","t1"));
        BaseElementsSentence data4 = new CodeSentenceDataBuilder().from(tableList).build(null);
        assert data4 != null;
        assert data4.getData().size() == 2;
        assert GrammarEnum.FROM.equals(data4.getData().get(0));
        assert Table.class.isInstance(data4.getData().get(1));

        Sentence<String> stringSentence = new Sentence<String>("select * from t2") {
            @Override
            public String getData() {
                return super.getData();
            }
        };
        BaseElementsSentence data5 = new CodeSentenceDataBuilder().from(stringSentence).build(null);
        assert data5 != null;
        assert data5.getData().size() == 2;
        assert GrammarEnum.FROM.equals(data5.getData().get(0));
        assert Table.class.isInstance(data5.getData().get(1));
    }

    /**
     * LeftJoin
     */
    @Test
    public void testLeftJoin(){
        BaseElementsSentence data = new CodeSentenceDataBuilder().leftjoin().build(null);
        assert data != null;
        assert data.getData().size() == 2;
        assert GrammarEnum.LEFT.equals(data.getData().get(0));
        assert GrammarEnum.JOIN.equals(data.getData().get(1));

        BaseElementsSentence data2 = new CodeSentenceDataBuilder().leftjoin("t1").build(null);
        assert data2 != null;
        assert data2.getData().size() == 3;
        assert GrammarEnum.LEFT.equals(data.getData().get(0));
        assert GrammarEnum.JOIN.equals(data.getData().get(1));
        assert Table.class.isInstance(data2.getData().get(2));

        BaseElementsSentence data3 = new CodeSentenceDataBuilder().leftjoin(new Table("t")).build(null);
        assert data3 != null;
        assert data3.getData().size() == 3;
        assert GrammarEnum.LEFT.equals(data.getData().get(0));
        assert GrammarEnum.JOIN.equals(data.getData().get(1));
        assert Table.class.isInstance(data3.getData().get(2));

        Map<Table,List<Param>> tableListMap = new HashMap<>();
        List<Param> paramList1 = new ArrayList<>();
        paramList1.add(new Param(true,new Column("c1","c1"), OperatorEnum.EQUAL,new Value()));
        List<Param> paramList2 = new ArrayList<>();
        paramList2.add(new Param(true,new Column("c2","c2"), OperatorEnum.EQUAL,new Value()));
        tableListMap.put(new Table("table1","t1"),paramList1);
        tableListMap.put(new Table("table2","t2"),paramList2);
        BaseElementsSentence data4 = new CodeSentenceDataBuilder().leftjoin(tableListMap).build(null);
        assert data4 != null;
        assert data4.getData().size() == 14;
        assert GrammarEnum.LEFT.equals(data.getData().get(0));
        assert GrammarEnum.JOIN.equals(data.getData().get(1));
        assert Table.class.isInstance(data4.getData().get(2));
        assert GrammarEnum.SET.equals(data4.getData().get(3));
        assert Column.class.isInstance(data4.getData().get(4));
        assert OperatorEnum.class.isInstance(data4.getData().get(5));
        assert Value.class.isInstance(data4.getData().get(6));
        assert GrammarEnum.LEFT.equals(data.getData().get(7));
        assert GrammarEnum.JOIN.equals(data.getData().get(8));
        assert Table.class.isInstance(data4.getData().get(9));
        assert GrammarEnum.SET.equals(data4.getData().get(10));
        assert Column.class.isInstance(data4.getData().get(11));
        assert OperatorEnum.class.isInstance(data4.getData().get(12));
        assert Value.class.isInstance(data4.getData().get(13));
    }

    /**
     * On
     */
    @Test
    public void testOn(){
        BaseElementsSentence data = new CodeSentenceDataBuilder().on().build(null);
        assert data != null;
        assert data.getData().size() == 1;
        assert GrammarEnum.SET.equals(data.getData().get(0));

        BaseElementsSentence data2 = new CodeSentenceDataBuilder().on("c1","=").build(null);
        assert data2 != null;
        assert data2.getData().size() == 4;
        assert GrammarEnum.SET.equals(data2.getData().get(0));
        assert Column.class.isInstance(data2.getData().get(1));
        assert OperatorEnum.class.isInstance(data2.getData().get(2));
        assert Value.class.isInstance(data2.getData().get(3));

        BaseElementsSentence data3 = new CodeSentenceDataBuilder().on(new Column("c1"),new Value()).build(null);
        assert data3 != null;
        assert data3.getData().size() == 4;
        assert GrammarEnum.SET.equals(data3.getData().get(0));
        assert Column.class.isInstance(data3.getData().get(1));
        assert OperatorEnum.class.isInstance(data3.getData().get(2));
        assert Value.class.isInstance(data3.getData().get(3));

        BaseElementsSentence data4 = new CodeSentenceDataBuilder().on(new Column("c1"),new Column("c2")).build(null);
        assert data4 != null;
        assert data4.getData().size() == 6;
        //TODO 应该将Table Column 合并 为Column，第二个Column变为Value
        assert GrammarEnum.SET.equals(data4.getData().get(0));
        assert Table.class.isInstance(data4.getData().get(1));
        assert Column.class.isInstance(data4.getData().get(2));
        assert OperatorEnum.class.isInstance(data4.getData().get(3));
        assert Table.class.isInstance(data4.getData().get(4));
        assert Column.class.isInstance(data4.getData().get(5));

        BaseElementsSentence data5 = new CodeSentenceDataBuilder().on(new Param(true,new Column("c1"), OperatorEnum.EQUAL,new Value())).build(null);
        assert data5 != null;
        assert data5.getData().size() == 4;
        assert GrammarEnum.SET.equals(data5.getData().get(0));
        assert Column.class.isInstance(data5.getData().get(1));
        assert OperatorEnum.class.isInstance(data5.getData().get(2));
        assert Value.class.isInstance(data5.getData().get(3));
    }

    /**
     * Where
     */
    @Test
    public void testWhere(){
        BaseElementsSentence data = new CodeSentenceDataBuilder().where().build(null);
        assert data != null;
        assert data.getData().size() == 1;
        assert GrammarEnum.WHERE.equals(data.getData().get(0));

        BaseElementsSentence data2 = new CodeSentenceDataBuilder().where("c1","=").build(null);
        assert data2 != null;
        assert data2.getData().size() == 4;
        assert GrammarEnum.WHERE.equals(data2.getData().get(0));
        assert Column.class.isInstance(data2.getData().get(1));
        assert OperatorEnum.class.isInstance(data2.getData().get(2));
        assert Value.class.isInstance(data2.getData().get(3));

        List<Param> paramList = new ArrayList<>();
        paramList.add(new Param(true,new Column("c1"), OperatorEnum.EQUAL,new Value()));
        BaseElementsSentence data3 = new CodeSentenceDataBuilder().where(paramList).build(null);
        assert data3 != null;
        assert data3.getData().size() == 4;
        assert GrammarEnum.WHERE.equals(data3.getData().get(0));
        assert Column.class.isInstance(data3.getData().get(1));
        assert OperatorEnum.class.isInstance(data3.getData().get(2));
        assert Value.class.isInstance(data3.getData().get(3));
    }

    /**
     * In
     */
    @Test
    public void testIn() {
        BaseElementsSentence data = new CodeSentenceDataBuilder().in("c1", 4).build(null);
        assert data != null;
        assert data.getData().size() == 6;
        assert Column.class.isInstance(data.getData().get(0));
        assert OperatorEnum.IN.equals(data.getData().get(1));
        assert Value.class.isInstance(data.getData().get(2));
        assert Value.class.isInstance(data.getData().get(3));
        assert Value.class.isInstance(data.getData().get(4));
        assert Value.class.isInstance(data.getData().get(5));
    }

    /**
     * And
     */
    @Test
    public void testAnd() {
        BaseElementsSentence data = new CodeSentenceDataBuilder().and("c1", "=").build(null);
        assert data != null;
        assert data.getData().size() == 4;
        assert OperatorEnum.AND.equals(data.getData().get(0));
        assert Column.class.isInstance(data.getData().get(1));
        assert OperatorEnum.class.isInstance(data.getData().get(2));
        assert Value.class.isInstance(data.getData().get(3));

        BaseElementsSentence data2 = new CodeSentenceDataBuilder().and("c1", "=", "111").build(null);
        assert data2 != null;
        assert data2.getData().size() == 4;
        assert OperatorEnum.AND.equals(data2.getData().get(0));
        assert Column.class.isInstance(data2.getData().get(1));
        assert OperatorEnum.class.isInstance(data2.getData().get(2));
        assert Value.class.isInstance(data2.getData().get(3));
    }

    /**
     * Or
     */
    @Test
    public void testOr() {
        BaseElementsSentence data = new CodeSentenceDataBuilder().or("c1", "=").build(null);
        assert data != null;
        assert data.getData().size() == 4;
        assert OperatorEnum.OR.equals(data.getData().get(0));
        assert Column.class.isInstance(data.getData().get(1));
        assert OperatorEnum.class.isInstance(data.getData().get(2));
        assert Value.class.isInstance(data.getData().get(3));

        BaseElementsSentence data2 = new CodeSentenceDataBuilder().or("c1", "=", "111").build(null);
        assert data2 != null;
        assert data2.getData().size() == 4;
        assert OperatorEnum.AND.equals(data2.getData().get(0));
        assert Column.class.isInstance(data2.getData().get(1));
        assert OperatorEnum.class.isInstance(data2.getData().get(2));
        assert Value.class.isInstance(data2.getData().get(3));
    }

    /**
     * OrderBy
     */
    @Test
    public void testOrderBy() {
        BaseElementsSentence data = new CodeSentenceDataBuilder().orderBy().build(null);
        assert data != null;
        assert data.getData().size() == 1;
        assert GrammarEnum.ORDER.equals(data.getData().get(0));

        BaseElementsSentence data2 = new CodeSentenceDataBuilder().orderBy("c1").build(null);
        assert data2 != null;
        assert data2.getData().size() == 3;
        assert GrammarEnum.ORDER.equals(data2.getData().get(0));
        assert Column.class.isInstance(data2.getData().get(1));
        assert GrammarEnum.AES.equals(data2.getData().get(2));

        List<com.xy.xsql.orm.data.sql.element.info.Order> orderList = new ArrayList<>();
        orderList.add(new com.xy.xsql.orm.data.sql.element.info.Order(new Column("c1"),true));
        orderList.add(new com.xy.xsql.orm.data.sql.element.info.Order(new Column("c2"),false));
        BaseElementsSentence data3 = new CodeSentenceDataBuilder().orderBy(orderList).build(null);
        assert data3 != null;
        assert data3.getData().size() == 5;
        assert GrammarEnum.ORDER.equals(data3.getData().get(0));
        assert Column.class.isInstance(data3.getData().get(1));
        assert GrammarEnum.AES.equals(data3.getData().get(2));
        assert Column.class.isInstance(data3.getData().get(3));
        assert GrammarEnum.DESC.equals(data3.getData().get(4));
    }

    /**
     * OrderBy
     */
    @Test
    public void testAs() {
        BaseElementsSentence data = new CodeSentenceDataBuilder().as("a").build(null);
        assert data != null;
        assert data.getData().size() == 1;
        assert GrammarEnum.AS.equals(data.getData().get(0));
    }


}
