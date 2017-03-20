package com.xy.xsql.orm.core.sentence.data;

import com.xy.xsql.core.builder.BaseBuilder;
import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.Sentence;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;
import com.xy.xsql.orm.data.sql.element.OperatorEnum;
import com.xy.xsql.orm.data.sql.element.UnknownString;
import com.xy.xsql.orm.data.sql.element.info.*;
import com.xy.xsql.orm.data.sql.expression.fun.Count;
import com.xy.xsql.orm.data.sql.sentence.BaseElementsSentence;
import com.xy.xsql.orm.data.sql.sentence.select.SelectSentence;
import com.xy.xsql.orm.data.sql.sentence.StringSentence;
import com.xy.xsql.orm.util.CheckUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * CodeSentenceDataBuilder
 * core BaseElementsSentence by code
 * Created by xiaoyao9184 on 2016/10/15.
 */
public class CodeSentenceDataBuilder implements BaseBuilder<Void,BaseElementsSentence> {

    private List<Element> data = new ArrayList<>();

    /**
     * SELECT
     * http://www.w3school.com.cn/sql/sql_select.asp
     * @return This
     */
    public CodeSentenceDataBuilder select(){
        data.add(GrammarEnum.SELECT);
        return this;
    }

    /**
     * SELECT append COLUMN
     * http://www.w3school.com.cn/sql/sql_select.asp
     * @param columns Column Array
     * @return This
     */
    @SuppressWarnings("Duplicates")
    public CodeSentenceDataBuilder select(String... columns) {
        data.add(GrammarEnum.SELECT);
        for (String column : columns) {
            data.add(new Column(column));
        }
        return this;
    }

    /**
     * SELECT append TABLE.COLUMN
     * default use table other name and column real name
     * @param columnList Column List
     * @return This
     */
    public CodeSentenceDataBuilder select(List<Column> columnList){
        if(CheckUtil.isNullOrEmpty(columnList)){
            return this;
        }
        data.add(GrammarEnum.SELECT);
        data.addAll(columnList);
        return this;
    }

    /**
     * SELECT DISTINCT
     * http://www.w3school.com.cn/sql/sql_distinct.asp
     * @see #select()
     * @return This
     */
    public CodeSentenceDataBuilder distinct(){
        if(data.size() == 0||
                !GrammarEnum.SELECT.equals(data.get(data.size()-1))){
            data.add(GrammarEnum.SELECT);
            data.add(GrammarEnum.DISTINCT);
        }else{
            data.add(GrammarEnum.DISTINCT);
        }
        return this;
    }

    /**
     * SELECT TOP
     * SELECT TOP COUNT PERCENT
     * http://www.w3school.com.cn/sql/sql_top.asp
     * @see #select()
     * @param count count or percent
     * @param percent true: useLink percent ;false:useLink count
     * @return This
     */
    public CodeSentenceDataBuilder top(int count, boolean percent){
        if(data.size() == 0||
                !GrammarEnum.SELECT.equals(data.get(data.size()-1))){
            data.add(GrammarEnum.SELECT);
            data.add(GrammarEnum.DISTINCT);
        }else{
            data.add(GrammarEnum.DISTINCT);
        }
        return this;
    }

    /**
     * INSERT INTO
     * @return This
     */
    public CodeSentenceDataBuilder insert (){
        data.add(GrammarEnum.INSERT);
        return this;
    }

    /**
     * INSERT INTO append TABLE
     * @param tableName Table Name
     * @return This
     */
    public CodeSentenceDataBuilder insert (String tableName){
        data.add(GrammarEnum.INSERT);
        data.add(new Table(tableName));
        return this;
    }

    /**
     * INSERT INTO append TABLE
     * default use real name
     * @param table Table
     * @return This
     */
    public CodeSentenceDataBuilder insert (Table table){
        data.add(GrammarEnum.INSERT);
        data.add(table);
        return this;
    }

    /**
     * VALUES append VALUE ?
     * @see #insert()
     * @param valueCount Value Count
     * @return This
     */
    public CodeSentenceDataBuilder values (int valueCount){
        if(valueCount < 0){
            return this;
        }
        data.add(GrammarEnum.VALUES);
        for (int i = 0; i < valueCount; i++){
            data.add(new Value());
        }
        return this;
    }

    /**
     * VALUES append COLUMN and VALUE?
     * 字段后置 INSERT INTO Persons (LastName, Address) VALUES ('Wilson', 'Champs-Elysees')
     * @see #insert()
     * @param columns Column Array
     * @return This
     */
    public CodeSentenceDataBuilder values (String... columns){
        List<Column> list = new ArrayList<>();
        for (String column : columns) {
            list.add(new Column(column));
        }
        return this.values(list);
    }

    /**
     * VALUES append COLUMN and VALUE?
     * 字段后置 INSERT INTO Persons (LastName, Address) VALUES ('Wilson', 'Champs-Elysees')
     * @see #insert()
     * @param columnList Column List
     * @return This
     */
    public CodeSentenceDataBuilder values (List<Column> columnList){
        return this.values(columnList,1);
    }

    /**
     * VALUES append COLUMN and ?*{valueGroupCount}
     * @see #insert()
     * @param columnList Column List
     * @param valueGroupCount Value Group Count
     * @return This
     */
    public CodeSentenceDataBuilder values(List<Column> columnList, int valueGroupCount) {
        if(CheckUtil.isNullOrEmpty(columnList)){
            return this;
        }
        for (Column column : columnList) {
            data.add(column);
        }

        data.add(GrammarEnum.VALUES);

        for (int g = 0; g < valueGroupCount; g++){
            for (Column ignored : columnList){
                data.add(new Value());
            }
        }
        return this;
    }

    /**
     * UPDATE
     * @return This
     */
    public CodeSentenceDataBuilder update (){
        data.add(GrammarEnum.UPDATE);
        return this;
    }

    /**
     * UPDATE append Table
     * @param tableName Table Name
     * @return This
     */
    public CodeSentenceDataBuilder update (String tableName){
        data.add(GrammarEnum.UPDATE);
        data.add(new Table(tableName));
        return this;
    }

    /**
     * UPDATE append Table
     * @param table Table
     * @return This
     */
    public CodeSentenceDataBuilder update (Table table){
        data.add(GrammarEnum.UPDATE);
        data.add(table);
        return this;
    }

    /**
     * SET append COLUMN
     * @see #update()
     * @param columns Column Name Array
     * @return This
     */
    public CodeSentenceDataBuilder set (String... columns){
        List<Column> list = new ArrayList<>();
        for (String column : columns) {
            list.add(new Column(column));
        }
        return this.set(list);
    }
    /**
     * SET append COLUMN
     * @see #update()
     * @param column Column Name Array
     * @return This
     */
    public CodeSentenceDataBuilder set(Column column) {
        data.add(GrammarEnum.SET);
        data.add(column);
        return this;
    }

    /**
     * SET append COLUMN
     * @see #update()
     * @param columnList Column List
     * @return This
     */
    public CodeSentenceDataBuilder set (List<Column> columnList){
        if(CheckUtil.isNullOrEmpty(columnList)){
            return this;
        }
        data.add(GrammarEnum.SET);
        for (Column column : columnList) {
            data.add(column);
            data.add(OperatorEnum.EQUAL);
            data.add(new Value());
        }
        return this;
    }

    /**
     * SET append COLUMN and VALUE
     * @see #update()
     * @param columnList Column List
     * @param valueList Value List
     * @return This
     */
    public CodeSentenceDataBuilder set (List<Column> columnList, List<Value> valueList){
        if(CheckUtil.isNullOrEmpty(columnList)){
            return this;
        }
        data.add(GrammarEnum.SET);
        for (int i = 0; i < columnList.size(); i++) {
            data.add(columnList.get(i));
            data.add(OperatorEnum.EQUAL);
            data.add(valueList.get(i));
        }
        return this;
    }

    /**
     * DELETE
     * http://www.w3school.com.cn/sql/sql_delete.asp
     * @return This
     */
    public CodeSentenceDataBuilder delete(){
        data.add(GrammarEnum.DELETE);
        return this;
    }

    /**
     * DELETE append TABLE
     * @param tableName Table Name Array
     * @return This
     */
    @SuppressWarnings("Duplicates")
    public CodeSentenceDataBuilder delete(String... tableName){
        data.add(GrammarEnum.DELETE);
        for (String aTableName : tableName) {
            data.add(new Table(aTableName));
        }
        return this;
    }

    /**
     * DELETE append TABLE
     * default use other name
     * @param table Table
     * @return This
     */
    public CodeSentenceDataBuilder delete(Table table){
        data.add(GrammarEnum.DELETE);
        data.add(table);
        return this;
    }

    /**
     * DELETE append TABLE
     * default use other name
     * @param tableList Table List
     * @return This
     */
    public CodeSentenceDataBuilder delete(List<Table> tableList){
        if(CheckUtil.isNullOrEmpty(tableList)){
            return this;
        }
        data.add(GrammarEnum.DELETE);
        for (Table table : tableList) {
            data.add(table);
        }
        return this;
    }

    /**
     * FORM
     * @return This
     */
    public CodeSentenceDataBuilder from(){
        data.add(GrammarEnum.FROM);
        return this;
    }

    /**
     * FORM append TABLE
     * @param tableName Table Name Array
     * @return This
     */
    @SuppressWarnings("Duplicates")
    public CodeSentenceDataBuilder from(String... tableName){
        data.add(GrammarEnum.FROM);
        for (String table : tableName) {
            data.add(new Table(table));
        }
        return this;
    }

    /**
     * FORM append TABLE
     * default format to as entitySql
     * @param table Table
     * @return This
     */
    public CodeSentenceDataBuilder from(Table table) {
        data.add(GrammarEnum.FROM);
        data.add(table);
        return this;
    }

    /**
     * FORM append TABLE
     * default format to as entitySql
     * @param tableList Table List
     * @return This
     */
    public CodeSentenceDataBuilder from(List<Table> tableList){
        if(CheckUtil.isNullOrEmpty(tableList)){
            return this;
        }
        data.add(GrammarEnum.FROM);
        for (Table table: tableList) {
            data.add(table);
        }
        return this;
    }

    /**
     * FORM append CodeSentenceDataBuilder
     * default format to as entitySql, use subs entitySql
     * @param sentence SelectSentence
     * @return This
     */
    public CodeSentenceDataBuilder from(SelectSentence sentence) {
        data.add(GrammarEnum.FROM);
        data.add(new Table(sentence));
        return this;
    }

    public CodeSentenceDataBuilder from(Sentence<String> sentence) {
        data.add(GrammarEnum.FROM);
        data.add(new Table(sentence));
        return this;
    }

    /**
     * LEFT Join
     * @return This
     */
    public CodeSentenceDataBuilder leftjoin(){
        data.add(GrammarEnum.LEFT);
        data.add(GrammarEnum.JOIN);
        return this;
    }

    /**
     * LEFT Join append TABLE
     * @param tableName Table Name
     * @return This
     */
    public CodeSentenceDataBuilder leftjoin(String tableName){
        data.add(GrammarEnum.LEFT);
        data.add(GrammarEnum.JOIN);
        data.add(new Table(tableName));
        return this;
    }

    /**
     * LEFT Join append TABLE
     * default format to as entitySql
     * @param table Table
     * @return This
     */
    public CodeSentenceDataBuilder leftjoin(Table table) {
        data.add(GrammarEnum.LEFT);
        data.add(GrammarEnum.JOIN);
        data.add(table);
        return this;
    }

    /**
     * LEFT Join append TABLE and PARAM
     * @param leftJoinParam Table Param Map
     * @return This
     */
    public CodeSentenceDataBuilder leftjoin(Map<Table, List<Param>> leftJoinParam) {
        for (Map.Entry<Table, List<Param>> tp: leftJoinParam.entrySet()) {
            Table table = tp.getKey();
            this.leftjoin(table);
            List<Param> paramList = tp.getValue();
            for (Param param: paramList) {
                this.on(param);
            }
        }
        return this;
    }

    /**
     * ON
     * @return This
     */
    public CodeSentenceDataBuilder on(){
        data.add(GrammarEnum.ON);
        return this;
    }

    /**
     * ON
     * @param columnName
     * @param relationship
     * @return This
     */
    public CodeSentenceDataBuilder on(String columnName, String relationship){
        data.add(GrammarEnum.ON);
        data.add(new Column(columnName));
        data.add(new UnknownString(relationship));
        data.add(new Value());
        return this;
    }

    /**
     * ON
     * @see #leftjoin()
     * @param column Main Column
     * @param leftColumn Left Column
     * @return This
     */
    public CodeSentenceDataBuilder on(Column column, Column leftColumn) {
        data.add(GrammarEnum.ON);
        data.add(column);
        data.add(OperatorEnum.EQUAL);
        data.add(leftColumn);
        return this;
    }

    /**
     * ON append COLUMN and VALUE
     * @see #leftjoin()
     * @param column Main Column
     * @param value Value
     * @return This
     */
    public CodeSentenceDataBuilder on(Column column, Value value) {
        data.add(GrammarEnum.ON);
        data.add(new Column());
        data.add(OperatorEnum.EQUAL);
        data.add(value);
        return this;
    }

    /**
     * ON append PARAM
     * @see #leftjoin()
     * @param param param
     * @return This
     */
    public CodeSentenceDataBuilder on(Param param) {
        data.add(GrammarEnum.ON);
        data.add(param.getColumn());
        data.add(param.getRelationshipOperator());
        data.add(param.getValueExpression());
        return this;
    }

    /**
     * WHERE
     * @return This
     */
    public CodeSentenceDataBuilder where(){
        data.add(GrammarEnum.WHERE);
        return this;
    }

    /**
     * WHERE append COLUMN relationshipOperator and ?
     * @param columnName Column Name
     * @param relationship Relationship
     * @return This
     */
    public CodeSentenceDataBuilder where(String columnName, String relationship){
        data.add(GrammarEnum.WHERE);
        data.add(new Column(columnName));
        data.add(new UnknownString(relationship));
        data.add(new Value());
        return this;
    }

    /**
     * WHERE append COLUMN relationshipOperator and ?
     * @param column Column
     * @param relationship Relationship
     * @return This
     */
    public CodeSentenceDataBuilder where(Column column, OperatorEnum relationship){
        data.add(GrammarEnum.WHERE);
        data.add(column);
        data.add(relationship);
        data.add(new Value());
        return this;
    }

    /**
     * WHERE append PARAM
     * @param paramList Param List
     * @return This
     */
    public CodeSentenceDataBuilder where(List<Param> paramList){
        if(CheckUtil.isNullOrEmpty(paramList)){
            return this;
        }
        data.add(GrammarEnum.WHERE);
        for (int i = 0; i < paramList.size(); i++) {
            Param param = paramList.get(i);
            if(i == 0){
                data.add(param.getColumn());
                data.add(param.getRelationshipOperator());
                data.add(param.getValueExpression());
            }else{
                if(param.isAnd()){
                    data.add(OperatorEnum.AND);
                    data.add(param.getColumn());
                    data.add(new UnknownString(param.getRelationshipOperator().toString()));
                    data.add(param.getValueExpression());
                }else{
                    data.add(OperatorEnum.OR);
                    data.add(param.getColumn());
                    data.add(new UnknownString(param.getRelationshipOperator().toString()));
                    data.add(param.getValueExpression());
                }
            }
        }
        return this;
    }

    /**
     * IN append COLUMN append ?*{valueCount}
     * 字段后置
     * @see #where()
     * @param columnName Column Name
     * @param valueCount Value Count
     * @return This
     */
    public CodeSentenceDataBuilder in(String columnName, int valueCount) {
        data.add(new Column(columnName));
        data.add(OperatorEnum.IN);
        for (int i = 0; i < valueCount; i++) {
            data.add(new Value());
        }
        return this;
    }

    /**
     * IN append COLUMN append ?*{valueCount}
     * 字段后置
     * @see #where()
     * @param column Column
     * @param valueCount Value Count
     * @return This
     */
    public CodeSentenceDataBuilder in(Column column, int valueCount) {
        data.add(column);
        data.add(OperatorEnum.IN);
        for (int i = 0; i < valueCount; i++) {
            data.add(new Value());
        }
        return this;
    }


    /**
     * AND
     * @see #where()
     * @see #leftjoin()
     * @param columnName Column Name
     * @param relationship Relationship
     * @return This
     */
    public CodeSentenceDataBuilder and(String columnName, String relationship){
        data.add(OperatorEnum.AND);
        data.add(new Column(columnName));
        data.add(new UnknownString(relationship));
        data.add(new Value());
        return this;
    }

    /**
     * AND
     * @see #where()
     * @see #leftjoin()
     * @param column Column
     * @param relationship Relationship
     * @return This
     */
    public CodeSentenceDataBuilder and(Column column, OperatorEnum relationship){
        data.add(OperatorEnum.AND);
        data.add(column);
        data.add(relationship);
        data.add(new Value());
        return this;
    }

    /**
     * AND
     * @see #where()
     * @see #leftjoin()
     * @param columnName Column Name
     * @param relationship Relationship
     * @param value Value
     * @return This
     */
    public CodeSentenceDataBuilder and(String columnName, String relationship, String value){
        data.add(OperatorEnum.AND);
        data.add(new Column(columnName));
        data.add(new UnknownString(relationship));
        data.add(new Value(value));
        return this;
    }

    /**
     * AND
     * @see #where()
     * @see #leftjoin()
     * @param columnName Column Name
     * @param relationship Relationship
     * @return This
     */
    public CodeSentenceDataBuilder or(String columnName, String relationship){
        data.add(OperatorEnum.OR);
        data.add(new Column(columnName));
        data.add(new UnknownString(relationship));
        data.add(new Value());
        return this;
    }

    /**
     * AND
     * @see #where()
     * @see #leftjoin()
     * @param columnName Column Name
     * @param relationship Relationship
     * @param value Value
     * @return This
     */
    public CodeSentenceDataBuilder or(String columnName, String relationship, String value){
        data.add(OperatorEnum.OR);
        data.add(new Column(columnName));
        data.add(new UnknownString(relationship));
        data.add(new Value(value));
        return this;
    }

    /**
     * ORDER BY
     * @return This
     */
    public CodeSentenceDataBuilder orderBy(){
        data.add(GrammarEnum.ORDER);
        return this;
    }

    /**
     * ORDER BY append COLUMN
     * @param columns Column Name Array
     * @return This
     */
    @SuppressWarnings("Duplicates")
    public CodeSentenceDataBuilder orderBy(String... columns){
        data.add(GrammarEnum.ORDER);
        for (String column : columns) {
            data.add(new Column(column));
            data.add(GrammarEnum.ASC);
        }
        return this;
    }

    /**
     * ORDER BY append COLUMN
     * @param orderList Order List
     * @return This
     */
    public CodeSentenceDataBuilder orderBy(List<com.xy.xsql.orm.data.sql.element.info.Order> orderList){
        if(CheckUtil.isNullOrEmpty(orderList)){
            return this;
        }
        data.add(GrammarEnum.ORDER);
        for (com.xy.xsql.orm.data.sql.element.info.Order order : orderList) {
            data.add(order.getColumn());
            if(order.isAsc()){
                data.add(GrammarEnum.ASC);
            }else{
                data.add(GrammarEnum.DESC);
            }
        }
        return this;
    }

    /**
     * AS
     * @return This
     */
    public CodeSentenceDataBuilder as() {
        data.add(GrammarEnum.AS);
        return this;
    }

    /**
     * AS append name
     * @param otherName Other Name
     * @return This
     */
    public CodeSentenceDataBuilder as(String otherName) {
        data.add(GrammarEnum.AS);
        data.add(new Alias(otherName));
        return this;
    }

    /**
     * CASE
     * @param columnName Column Name
     * @return This
     */
    public CodeSentenceDataBuilder caseStart(String columnName) {
        data.add(GrammarEnum.CASE);
        data.add(new Value(columnName));
        return this;
    }

    /**
     * WHEN append THEN
     * @param count When Then Count
     * @return This
     */
    public CodeSentenceDataBuilder whenThen(int count) {
        for(int i = 0; i< count; i++){
            data.add(GrammarEnum.WHEN);
            data.add(new Value());
            data.add(GrammarEnum.THEN);
            data.add(new Value());
        }
        return this;
    }

    /**
     * WHEN append THEN
     * @param whenValue When Value
     * @param thenValue Then Value
     * @return This
     */
    public CodeSentenceDataBuilder whenThen(String whenValue, String thenValue) {
        data.add(GrammarEnum.WHEN);
        data.add(new Value(whenValue));
        data.add(GrammarEnum.THEN);
        data.add(new Value(thenValue));
        return this;
    }

    /**
     * END
     * @return This
     */
    public CodeSentenceDataBuilder caseEnd() {
        data.add(GrammarEnum.END);
        return this;
    }


    /**
     * function COUNT()
     * @return This
     */
    public CodeSentenceDataBuilder funCount() {
        data.add(new Count());
        return this;
    }


    /**
     * append Element
     * @param e Element interface
     * @return This
     */
    public CodeSentenceDataBuilder add(Element e){
        data.add(e);
        return this;
    }

    /**
     * append entitySql string
     * @param sqlString SQL String
     * @return This
     */
    public CodeSentenceDataBuilder add(String sqlString){
        data.add(new StringSentence(sqlString));
        return this;
    }

    @Override
    public BaseElementsSentence build(Void aVoid) {
        return new BaseElementsSentence(this.data);
    }

}
