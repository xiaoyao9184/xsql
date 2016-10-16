package com.xy.xsql.orm.core;

import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.info.Column;
import com.xy.xsql.orm.data.sql.info.Param;
import com.xy.xsql.orm.data.sql.info.Table;
import com.xy.xsql.orm.data.sql.grammar.*;

import java.util.ArrayList;
import java.util.List;

/**
 * QSQL
 * Created by xiaoyao9184 on 2016/6/26.
 */
@SuppressWarnings({"unused", "WeakerAccess", "MismatchedQueryAndUpdateOfCollection"})
public class QSql {

    private List<Element> queue = new ArrayList<>();

    /**
     * SELECT
     * http://www.w3school.com.cn/sql/sql_select.asp
     * @return This
     */
    public QSql select(){
        queue.add(new Select());
        return this;
    }

    /**
     * SELECT with COLUMN's
     * http://www.w3school.com.cn/sql/sql_select.asp
     * @param columns COLUMN's
     * @return This
     */
    public QSql select(String... columns) {
        queue.add(new Select());
        for (String column: columns) {
            queue.add(new Column(column));
        }
        return this;
    }

    /**
     * SELECT with COLUMN's
     * @param columns  COLUMN's
     * @return This
     */
    public QSql select(List<Column> columns){
        queue.add(new Select());
        queue.addAll(columns);
        return this;
    }

    /**
     * SELECT with DISTINCT
     * http://www.w3school.com.cn/sql/sql_distinct.asp
     * @see #select()
     * @return This
     */
    public QSql distinct(){
        queue.add(new Select().addDistinct());
        return this;
    }

    /**
     * SELECT withTOP
     * http://www.w3school.com.cn/sql/sql_top.asp
     * @see #select()
     * @param count count or percent
     * @param percent true: use percent ;false:use count
     * @return This
     */
    public QSql top(int count, boolean percent){
        queue.add(new Select().addTop(count, percent));
        return this;
    }

    /**
     * SELECT with TOP
     * http://www.w3school.com.cn/sql/sql_top.asp
     * @param percent percent
     * @return This
     */
    public QSql top(float percent){
        int count = (int)percent;
        if(percent > 1){
            queue.add(new Select().addTop(count, false));
        }else{
            count = (int) Math.floor(percent * 100);
            queue.add(new Select().addTop(count, true));
        }
        return this;
    }

    /**
     * DELETE
     * http://www.w3school.com.cn/sql/sql_delete.asp
     * @return This
     */
    public QSql delete(){
        queue.add(new Delete());
        return this;
    }

    /**
     * DELETE with TABLE's
     * http://www.cnblogs.com/China-Dragon/archive/2009/03/20/1417256.html
     * @param tables TABLE's
     * @return This
     */
    public QSql delete(String... tables){
        queue.add(new Delete());
        for (String table: tables) {
            queue.add(new Table(table));
        }
        return this;
    }

    /**
     * DELETE with TABLE's
     * @param tables TABLE's
     * @return This
     */
    public QSql delete(List<Table> tables){
        queue.add(new Delete());
        queue.addAll(tables);
        return this;
    }


    /**
     * INSERT INTO
     * @return This
     */
    public QSql insert (){
        queue.add(new Insert());
        return this;
    }

    /**
     * INSERT INTO with TABLE
     * @param table TABLE
     * @return This
     */
    public QSql insert (String table){
        queue.add(new Insert());
        queue.add(new Table(table));
        return this;
    }

    /**
     * INSERT INTO with TABLE
     * @param table TABLE
     * @return This
     */
    public QSql insert(Table table){
        queue.add(new Insert());
        queue.add(table);
        return this;
    }

    /**
     * INSERT INTO VALUES with count
     * 无字段
     * @see #insert(String)
     * @param count Count
     * @return This
     */
    public QSql values (int count){
        queue.add(new Values(count));
        return this;
    }

    /**
     * INSERT INTO VALUES with COLUMN's
     * INSERT INTO Persons (LastName, Address) VALUES ('Wilson', 'Champs-Elysees')
     * 字段后置
     * @see #insert(String)
     * @param columns COLUMN's
     * @return This
     */
    public QSql values (String... columns){
        for (String column: columns) {
            queue.add(new Column(column));
        }
        queue.add(new Values(columns.length));
        return this;
    }

    /**
     *
     * INSERT INTO VALUES with COLUMN's
     * INSERT INTO Persons (LastName, Address) VALUES ('Wilson', 'Champs-Elysees')
     * 字段后置
     * @param columns COLUMN's
     * @return This
     */
    public QSql values (List<Column> columns){
        queue.addAll(columns);
        queue.add(new Values(columns.size()));
        return this;
    }

    /**
     *
     * UPDATE
     * @return This
     */
    public QSql update (){
        queue.add(new Update());
        return this;
    }

    /**
     *
     * UPDATE with TABLE
     * @param table TABLE
     * @return This
     */
    public QSql update (String table){
        queue.add(new Update());
        queue.add(new Table(table));
        return this;
    }

    /**
     *
     * UPDATE with TABLE
     * @param table TABLE
     * @return This
     */
    public QSql update (Table table){
        queue.add(new Update());
        queue.add(table);
        return this;
    }


    /**
     *
     * SET
     * @see #update(String)
     * @return This
     */
    public QSql set() {
        queue.add(new Set());
        return this;
    }

    /**
     *
     * SET with COLUMN's
     * @see #update(String)
     * @param columns  COLUMN's
     * @return This
     */
    public QSql set(String... columns) {
        queue.add(new Set());
        for (String column: columns) {
            queue.add(new Column(column));
        }
        return this;
    }

    /**
     *
     * SET with COLUMN's
     * @param columns  COLUMN's
     * @return This
     */
    public QSql set (List<Column> columns){
        queue.add(new Set());
        queue.addAll(columns);
        return this;
    }


    /**
     *
     * FROM
     * @return This
     */
    public QSql from(){
        queue.add(new From());
        return this;
    }

    /**
     *
     * FROM with TABLE
     * @param table TABLE
     * @return This
     */
    public QSql from(String table){
        queue.add(new From());
        queue.add(new Table(table));
        return this;
    }

    /**
     *
     * FROM with TABLE's
     * @param tables TABLE's
     * @return This
     */
    public QSql from(List<Table> tables){
        queue.add(new From());
        queue.addAll(tables);
        return this;
    }


    /**
     *
     * LEFT JOIN with TABLE
     * @param table TABLE
     * @return This
     */
    public QSql leftjoin(String table){
        queue.add(new From());
        queue.add(new Table(table));
        return this;
    }


    /**
     * WHERE
     * @return This
     */
    public QSql where(){
        queue.add(new Where());
        return this;
    }

    /**
     * WHERE with PARAM's
     * @param params PARAM's
     * @return This
     */
    public QSql where(List<Param> params){
        queue.add(new Where());
        queue.addAll(params);
        return this;
    }










    public List<Element> getQueue(){
        return this.queue;
    }

    public void reset(){
        this.queue.clear();
    }

    public String toSql(){
        return null;
    }
}
