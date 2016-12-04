package com.xy.xsql.orm.core;

import com.xy.xsql.orm.data.sql.element.info.*;
import com.xy.xsql.orm.util.CheckUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * XSQL
 * Created by xiaoyao9184 on 2016/6/26.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class XSql {

    private StringBuilder sql = new StringBuilder();
    private List<XSql> cache = new ArrayList<>();

    /**
     * SELECT
     * http://www.w3school.com.cn/sql/sql_select.asp
     * @return This
     */
    public XSql select(){
        sql.append("SELECT");
        sql.append("\n");
        return this;
    }

    /**
     * SELECT with COLUMN
     * http://www.w3school.com.cn/sql/sql_select.asp
     * @param columns Column Array
     * @return This
     */
    @SuppressWarnings("Duplicates")
    public XSql select(String... columns) {
        sql.append("SELECT\n");
        for (int i = 0; i < columns.length; i++) {
            if(i != 0){
                sql.append("\t, ");
            }else{
                sql.append("\t");
            }
            sql.append(columns[i]);
            sql.append("\n");
        }
        return this;
    }

    /**
     * SELECT with TABLE.COLUMN
     * default useLink table other name and column real name
     * @param columnList Column List
     * @return This
     */
    public XSql select(List<Column> columnList){
        if(CheckUtil.isNullOrEmpty(columnList)){
            return this;
        }
        sql.append("SELECT\n");
        sql.append("\t");
        for (int i = 0; i < columnList.size(); i++) {
            Column column = columnList.get(i);
            if(i != 0){
                sql.append("\n\t, ");
            }
            sql.append(column.getFullName());
            if(column.isUseOtherName()){
                this.as(column.getAliasName());
            }
        }
        sql.append("\n");
        return this;
    }

    /**
     * SELECT DISTINCT
     * http://www.w3school.com.cn/sql/sql_distinct.asp
     * @see #select()
     * @return This
     */
    public XSql distinct(){
        sql.append("DISTINCT\n");
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
    public XSql top(int count, boolean percent){
        sql.append("TOP ");
        sql.append(count);
        if(percent){
            sql.append(" PERCENT");
        }
        sql.append("\n");
        return this;
    }

    /**
     * INSERT INTO
     * @return This
     */
    public XSql insert (){
        sql.append("INSERT INTO\n");
        return this;
    }

    /**
     * INSERT INTO with TABLE
     * @param tableName Table Name
     * @return This
     */
    public XSql insert (String tableName){
        sql.append("INSERT INTO\n");
        sql.append("\t");
        sql.append(tableName);
        sql.append("\n");
        return this;
    }

    /**
     * INSERT INTO with TABLE
     * default useLink real name
     * @param table Table
     * @return This
     */
    public XSql insert (Table table){
        sql.append("INSERT INTO\n");
        sql.append("\t");
        sql.append(table.getName());
        sql.append("\n");
        return this;
    }

    /**
     * VALUES with VALUE ?
     * @see #insert()
     * @param valueCount Value Count
     * @return This
     */
    public XSql values (int valueCount){
        if(valueCount < 0){
            return this;
        }
        sql.append("VALUES \n(");
        for (int i = 0; i < valueCount; i++) {
            if(i != 0){
                sql.append(", ");
            }
            sql.append("?");
        }
        sql.append(")");
        sql.append("\n");
        return this;
    }

    /**
     * VALUES with COLUMN and VALUE?
     * 字段后置 INSERT INTO Persons (LastName, Address) VALUES ('Wilson', 'Champs-Elysees')
     * @see #insert()
     * @param columns Column Array
     * @return This
     */
    public XSql values (String... columns){
        return values(Arrays.asList(columns));
    }

    /**
     * VALUES with COLUMN and VALUE?
     * 字段后置 INSERT INTO Persons (LastName, Address) VALUES ('Wilson', 'Champs-Elysees')
     * @see #insert()
     * @param columnList Column List
     * @return This
     */
    public XSql values (List<String> columnList){
        return this.values(columnList,1);
    }

    /**
     * VALUES with COLUMN and ?*{valueGroupCount}
     * @see #insert()
     * @param columnList Column List
     * @param valueGroupCount Value Group Count
     * @return This
     */
    public XSql values(List<String> columnList, int valueGroupCount) {
        if(CheckUtil.isNullOrEmpty(columnList)){
            return this;
        }
        sql.append("(");
        for (int i = 0; i < columnList.size(); i++) {
            if(i != 0){
                sql.append(", ");
            }
            sql.append(columnList.get(i));
        }
        sql.append(")");
        sql.append("\n");
        sql.append("VALUES \n");
        for(int j = 0; j < valueGroupCount; j++){
            if(j != 0){
                sql.append("\n,");
            }
            sql.append("(");
            for (int i = 0; i < columnList.size(); i++) {
                if(i != 0){
                    sql.append(", ");
                }
                sql.append("?");
            }
            sql.append(")");
        }
        sql.append("\n");
        return this;
    }

    /**
     * UPDATE
     * @return This
     */
    public XSql update (){
        sql.append("UPDATE\n");
        return this;
    }

    /**
     * UPDATE with Table
     * @param tableName Table Name
     * @return This
     */
    public XSql update (String tableName){
        sql.append("UPDATE\n");
        sql.append("\t");
        sql.append(tableName);
        sql.append("\n");
        return this;
    }

    /**
     * SET with COLUMN
     * @see #update()
     * @param columns Column Name Array
     * @return This
     */
    public XSql set (String... columns){
        return set(Arrays.asList(columns));
    }

    /**
     * SET with COLUMN
     * @see #update()
     * @param columnList Column List
     * @return This
     */
    public XSql set (List<String> columnList){
        if(CheckUtil.isNullOrEmpty(columnList)){
            return this;
        }
        sql.append("SET\n");
        for (int i = 0; i < columnList.size(); i++) {
            if(i != 0){
                sql.append(", ");
            }else{
                sql.append("\t");
            }
            sql.append(columnList.get(i));
            sql.append(" = ");
            sql.append("?");
        }
        sql.append("\n");
        return this;
    }

    /**
     * SET with COLUMN and VALUE
     * @see #update()
     * @param columnList Column List
     * @param valueList Value List
     * @return This
     */
    public XSql set (List<String> columnList, List<String> valueList){
        if(CheckUtil.isNullOrEmpty(columnList)){
            return this;
        }
        sql.append("SET\n");
        for (int i = 0; i < columnList.size(); i++) {
            if(i != 0){
                sql.append(", ");
            }else{
                sql.append("\t");
            }
            sql.append(columnList.get(i));
            sql.append(" = ");

            if((i + 1) > valueList.size()){
                sql.append("?");
            }else{
                sql.append(valueList.get(i));
            }
        }
        sql.append("\n");
        return this;
    }

    /**
     * DELETE
     * http://www.w3school.com.cn/sql/sql_delete.asp
     * @return This
     */
    public XSql delete(){
        sql.append("DELETE\n");
        return this;
    }

    /**
     * DELETE with TABLE
     * @param tableName Table Name Array
     * @return This
     */
    @SuppressWarnings("Duplicates")
    public XSql delete(String... tableName){
        sql.append("DELETE\n");
        for (int i = 0; i < tableName.length; i++) {
            if(i != 0){
                sql.append("\t, ");
            }else{
                sql.append("\t");
            }
            sql.append(tableName[i]);
            sql.append("\n");
        }
        return this;
    }

    /**
     * DELETE with TABLE
     * default useLink other name
     * @param table Table
     * @return This
     */
    public XSql delete(Table table){
        sql.append("DELETE\n");
        sql.append("\t");
        sql.append(table.getAliasName());
        sql.append("\n");
        return this;
    }

    /**
     * DELETE with TABLE
     * default useLink other name
     * @param tableList Table List
     * @return This
     */
    public XSql delete(List<Table> tableList){
        if(CheckUtil.isNullOrEmpty(tableList)){
            return this;
        }
        sql.append("DELETE\n");
        sql.append("\t");
        for (int i = 0; i < tableList.size(); i++) {
            if(i != 0){
                sql.append(", ");
            }
            sql.append(tableList.get(i).getAliasName());
        }
        sql.append("\n");
        return this;
    }

    /**
     * FORM
     * @return This
     */
    public XSql from(){
        sql.append("FROM\n");
        return this;
    }

    /**
     * FORM with TABLE
     * @param tableName Table Name Array
     * @return This
     */
    @SuppressWarnings("Duplicates")
    public XSql from(String... tableName){
        sql.append("FROM\n");
        for (int i = 0; i < tableName.length; i++) {
            if(i != 0){
                sql.append("\t, ");
            }else{
                sql.append("\t");
            }
            sql.append(tableName[i]);
            sql.append("\n");
        }
        return this;
    }

    /**
     * FORM with TABLE
     * default format to as sql
     * @param table Table
     * @return This
     */
    public XSql from(Table table) {
        sql.append("FROM\n");
        sql.append("\t");
        sql.append(table.toAsSql());
        sql.append("\n");
        return this;
    }

    /**
     * FORM with TABLE
     * default format to as sql
     * @param tableList Table List
     * @return This
     */
    public XSql from(List<Table> tableList){
        if(CheckUtil.isNullOrEmpty(tableList)){
            return this;
        }
        sql.append("FROM\n");
        sql.append("\t");
        for (int i = 0; i < tableList.size(); i++) {
            if(i != 0){
                sql.append(", ");
            }
            sql.append(tableList.get(i).toAsSql());
        }
        sql.append("\n");
        return this;
    }

    /**
     * FORM with XSQL
     * default format to as sql, useLink subs sql
     * @param xSql XSql
     * @return This
     */
    public XSql from(XSql xSql) {
        String result = xSql.toSql().trim();
        if(result.toUpperCase().contains("ORDER BY")){
            result = result.substring(0, xSql.toSql().toUpperCase().lastIndexOf("ORDER BY"));
        }

        sql.append("FROM\n");
        sql.append("\t");
        sql.append("(");
        sql.append("\n");
        sql.append(result);
        sql.append("\n\t");
        sql.append(")\n");
        sql.append("AS temp");
        sql.append("\n");
        return this;
    }

    /**
     * LEFT JOIN
     * @return This
     */
    public XSql leftjoin(){
        sql.append("LEFT JOIN");
        sql.append("\n");
        return this;
    }

    /**
     * LEFT JOIN with TABLE
     * @param tableName Table Name
     * @return This
     */
    public XSql leftjoin(String tableName){
        sql.append("LEFT JOIN");
        sql.append("\n\t");
        sql.append(tableName);
        sql.append("\n");
        return this;
    }

    /**
     * LEFT JOIN with TABLE
     * default format to as sql
     * @param table Table
     * @return This
     */
    public XSql leftjoin(Table table) {
        sql.append("LEFT JOIN");
        sql.append("\n\t");
        sql.append(table.toAsSql());
        sql.append("\n");
        return this;
    }

    /**
     * LEFT JOIN with TABLE and PARAM
     * @param leftJoinParam Table Param Map
     * @return This
     */
    public XSql leftjoin(Map<Table, List<Param>> leftJoinParam) {
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
     * @see #leftjoin()
     * @param tableName Main Table Name
     * @param columnName Main Column Name
     * @param leftTableName Left Table Name
     * @param leftColumnName Left Column Name
     * @return This
     */
    public XSql on(Name tableName, Name columnName, Name leftTableName, Name leftColumnName) {
        sql.append("\t");
        sql.append("ON ");
        sql.append(tableName.toPrefixSql());
        sql.append(columnName.getName());
        sql.append(" = ");
        sql.append(leftTableName.toPrefixSql());
        sql.append(leftColumnName.getName());
        sql.append("\n");
        return this;
    }

    /**
     * ON with COLUMN and VALUE
     * @see #leftjoin()
     * @param column Main Column
     * @param value Value
     * @return This
     */
    public XSql on(Column column, com.xy.xsql.orm.data.sql.Value value) {
        sql.append("\t");
        sql.append("ON ");
        sql.append(column.getFullName());
        sql.append(" = ");
        sql.append(value.toValueString());
        sql.append("\n");
        return this;
    }

    /**
     * ON with PARAM
     * @see #leftjoin()
     * @param param param
     * @return This
     */
    public XSql on(Param param) {
        sql.append("\t");
        sql.append("ON ");
        sql.append(param.getColumn().toPrefixSql());
        sql.append(" ");
        sql.append(param.getRelationshipOperator());
        sql.append(" ");
        sql.append(param.getValueExpression());
        sql.append("\n");
        return this;
    }

    /**
     * WHERE
     * @return This
     */
    public XSql where(){
        sql.append("WHERE\n");
        return this;
    }

    /**
     * WHERE with COLUMN relationshipOperator and ?
     * @param columnName Column Name
     * @param relationship Relationship
     * @return This
     */
    public XSql where(String columnName, String relationship){
        sql.append("WHERE");
        sql.append("\n\t");
        sql.append(columnName);
        sql.append(" ");
        sql.append(relationship);
        sql.append(" ?");
        sql.append("\n");
        return this;
    }

    /**
     * WHERE with PARAM
     * @param paramList Param List
     * @return This
     */
    public XSql where(List<Param> paramList){
        if(CheckUtil.isNullOrEmpty(paramList)){
            return this;
        }
        sql.append("WHERE\n");
        sql.append("\t");
        for (int i = 0; i < paramList.size(); i++) {
            Param param = paramList.get(i);
            if(i == 0){
                sql.append(param.getColumn().getFullName());
                sql.append(" ");
                sql.append(param.getRelationshipOperator());
                sql.append(" ");
                sql.append(param.getValueExpression());
                sql.append("\n");
            }else{
                if(param.isAnd()){
                    and(param.getColumn().getFullName(),
                            param.getRelationshipOperator().toString(),
                            param.getValueExpression().toString());
                }else{
                    or(param.getColumn().getFullName(),
                            param.getRelationshipOperator().toString(),
                            param.getValueExpression().toString());
                }
            }
        }
        sql.append("\n");
        return this;
    }

    /**
     * IN with COLUMN with ?*{valueCount}
     * 字段后置
     * @see #where()
     * @param columnName Column Name
     * @param valueCount Value Count
     * @return This
     */
    public XSql in(String columnName, int valueCount) {
        sql.append("\t");
        sql.append(columnName);
        sql.append("\n");
        sql.append("IN");
        sql.append("\n\t(");
        for (int i = 0; i < valueCount; i++) {
            if(i != 0){
                sql.append(", ");
            }
            sql.append("?");
        }
        sql.append(")");
        sql.append("\n");
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
    public XSql and(String columnName, String relationship){
        sql.append("AND\n");
        sql.append("\t");
        sql.append(columnName);
        sql.append(" ");
        sql.append(relationship);
        sql.append(" ?");
        sql.append("\n");
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
    public XSql and(String columnName, String relationship, String value){
        sql.append("AND\n");
        sql.append("\t");
        sql.append(columnName);
        sql.append(" ");
        sql.append(relationship);
        sql.append(" ");
        sql.append(value);
        sql.append("\n");
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
    public XSql or(String columnName, String relationship){
        sql.append("OR\n");
        sql.append("\t");
        sql.append(columnName);
        sql.append(" ");
        sql.append(relationship);
        sql.append(" ?");
        sql.append("\n");
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
    public XSql or(String columnName, String relationship, String value){
        sql.append("OR\n");
        sql.append("\t");
        sql.append(columnName);
        sql.append(" ");
        sql.append(relationship);
        sql.append(" ");
        sql.append(value);
        sql.append("\n");
        return this;
    }

    /**
     * ORDER BY
     * @return This
     */
    public XSql orderBy(){
        sql.append("ORDER BY");
        sql.append("\n");
        return this;
    }

    /**
     * ORDER BY with COLUMN
     * @param columns Column Name Array
     * @return This
     */
    @SuppressWarnings("Duplicates")
    public XSql orderBy(String... columns){
        sql.append("ORDER BY");
        for (int i = 0; i < columns.length; i++) {
            if(i != 0){
                sql.append("\t, ");
            }else{
                sql.append("\t");
            }
            sql.append(columns[i]);
            sql.append("\n");
        }
        return this;
    }

    /**
     * ORDER BY with COLUMN
     * @param orderList Order List
     * @return This
     */
    public XSql orderBy(List<Order> orderList){
        if(CheckUtil.isNullOrEmpty(orderList)){
            return this;
        }
        sql.append("ORDER BY");
        for (int i = 0; i < orderList.size(); i++) {
            if(i != 0){
                sql.append(", ");
            }
            sql.append(orderList.get(i).getColumn().getName());
            if(orderList.get(i).isAes()){
                sql.append(" AES");
            }else{
                sql.append(" DESC");
            }
        }
        sql.append("\n");
        return this;
    }

    /**
     * AS
     * @return This
     */
    public XSql as() {
        sql.append(" AS ");
        return this;
    }

    /**
     * AS with name
     * @param otherName Other Name
     * @return This
     */
    public XSql as(String otherName) {
        sql.append(" AS ");
        sql.append(otherName);
        return this;
    }

    /**
     * CASE
     * @param columnName Column Name
     * @return This
     */
    public XSql caseStart(String columnName) {
        sql.append("\nCASE");
        sql.append(" ");
        sql.append(columnName);
        sql.append("\n");
        return this;
    }

    /**
     * WHEN with THEN
     * @param count When Then Count
     * @return This
     */
    public XSql whenThen(int count) {
        for(int i = 0; i< count; i++){
            sql.append("\t");
            sql.append("WHEN");
            sql.append(" ? THEN ?");
            sql.append("\n");
        }
        return this;
    }

    /**
     * WHEN with THEN
     * @param whenValue When Value
     * @param thenValue Then Value
     * @return This
     */
    public XSql whenThen(String whenValue, String thenValue) {
        sql.append("\t");
        sql.append("WHEN");
        sql.append(" ");
        sql.append(whenValue);
        sql.append(" ");
        sql.append("THEN");
        sql.append(thenValue);
        sql.append("\n");
        return this;
    }

    /**
     * END
     * @return This
     */
    public XSql caseEnd() {
        sql.append("END");
//        sql.append("\n");
        return this;
    }


    /**
     * function COUNT()
     * @return This
     */
    public XSql funCount() {
        sql.append("\t");
        sql.append("COUNT(*)");
        sql.append("\n");
        return this;
    }



    /**
     * append sql string
     * @param sqlString SQL String
     * @return This
     */
    public XSql sql(String sqlString){
        sql.append(sqlString);
        return this;
    }

    /**
     * clear all String
     */
    public XSql reset() {
        this.sql = new StringBuilder();
        return this;
    }

    /**
     * 获取缓存的子级XSql
     * @param index 索引（< 0 new ;> 0 cache）
     * @return This
     */
    public XSql cache(int index) {
        if(index < 0){
            XSql xSql =  new XSql();
            this.cache.add(xSql);
            return xSql;
        }else{
            return this.cache.get(index);
        }
    }


    /**
     * to String
     * @return String
     */
    public String toSql(){
        return this.sql.toString();
    }

}
