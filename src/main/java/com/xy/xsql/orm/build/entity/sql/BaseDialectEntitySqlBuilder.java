package com.xy.xsql.orm.build.entity.sql;

import com.xy.xsql.orm.annotation.EStatus;
import com.xy.xsql.orm.core.XSql;
import com.xy.xsql.orm.data.config.EntitySqlBuilderConfig;
import com.xy.xsql.orm.data.entity.EntityColumn;
import com.xy.xsql.orm.data.entity.EntityTemplateData;
import com.xy.xsql.orm.data.sql.element.info.Column;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Default DialectEntitySqlBuilder
 * it is T-SQL prototype
 * Created by xiaoyao9184 on 2016/1/13.
 */
@SuppressWarnings({"StringBufferReplaceableByString", "JavaDoc", "unused", "StatementWithEmptyBody", "WeakerAccess"})
public class BaseDialectEntitySqlBuilder implements DialectEntitySqlBuilder {

/*
 * Fields
 */

    protected Logger log;

    protected EntitySqlBuilderConfig config;
    
    protected EntityTemplateData data;

    public BaseDialectEntitySqlBuilder(){
        this.log = LoggerFactory.getLogger(this.getClass());
    }


/*
 * Cache
 */

    @Override
    public BaseDialectEntitySqlBuilder cacheConfig(EntitySqlBuilderConfig config) {
        this.config = config;
        return this;
    }

    @Override
    public DialectEntitySqlBuilder cacheData(EntityTemplateData data) {
        this.data = data;
        return this;
    }

/*
 * Create SQL
 */



    /**
     * one table insert
     * @return SQL with (+)? , + is 1 or more , is columns count
     */
    @Override
    public String sqlInsert(){
        List<String> list = new ArrayList<>();
        for (EntityColumn entityColumn : this.data.getTableColumn()) {
            list.add(entityColumn.getName());
        }

        XSql sql = new XSql()
                .insert(this.data.getTableName().getName())
                .values(list);

        return sql.toSql();
    }

    /**
     * one table all columns update
     * @return SQL with (+)? , + is 1 or more , is columns+keys count
     */
    public String sqlUpdateById(){
        List<String> list = new ArrayList<>();
        for (EntityColumn entityColumn : this.data.getTableColumn()) {
            list.add(entityColumn.getName());
        }

        XSql sql = new XSql()
                .update(this.data.getTableName().getName())
                .set(list)
                .where(this.data.getTableKey().get(0).getName(),"=");
        for(int i = 1; i < this.data.getTableKey().size(); i++){
            sql.and(this.data.getTableKey().get(i).getName(),"=");
        }

        return sql.toSql();
    }

    /**
     * one table select
     * @return SQL with (+)? , + is 1 or more , is keys count
     */
    public String sqlSelectById(){
        List<Column> list = new ArrayList<>();
        list.addAll(this.data.getTableColumn());

        XSql sql = new XSql()
                .select(list)
                .from(this.data.getTableName().getName())
                .where(this.data.getTableKey().get(0).getName(),"=");
        for(int i = 1; i < this.data.getTableKey().size(); i++){
            sql.and(this.data.getTableKey().get(i).getName(),"=");
        }

        return sql.toSql();
    }

    /**
     * one table delete
     * @return SQL with (+)? , + is 1 or more , is keys count
     */
    public String sqlDeleteById(){
        XSql sql = new XSql()
                .delete()
                .from(this.data.getTableName().getName())
                .where(this.data.getTableKey().get(0).getName(),"=");
        for(int i = 1; i < this.data.getTableKey().size(); i++){
            sql.and(this.data.getTableKey().get(i).getName(),"=");
        }

        return sql.toSql();
    }

    /**
     * one table status column update
     * @return SQL with (+)? , + is 1 or more , is keys count
     */
    public String sqlUpdateStatusById(){
        if(this.data.getTableStatus() == null){
            throw new UnsupportedOperationException("没有任何字段被标注为@" + EStatus.class.getSimpleName());
        }
        XSql sql = new XSql()
                .update(this.data.getTableName().getName())
                .set(this.data.getTableStatus().geteColumn().name())
                .where(this.data.getTableKey().get(0).getName(),"=");
        for(int i = 1; i < this.data.getTableKey().size(); i++){
            sql.and(this.data.getTableKey().get(i).getName(),"=");
        }

        return sql.toSql();
    }

    /**
     * one table more row insert
     * @param count row count
     * @return SQL with (count)*(+)? , + is 1 or more , is columns count
     */
    public String sqlInsert(int count){
        List<String> list = new ArrayList<>();
        for (EntityColumn entityColumn : this.data.getTableColumn()) {
            list.add(entityColumn.getName());
        }

        XSql sql = new XSql()
                .insert(this.data.getTableName().getName())
                .values(list,count);

        return sql.toSql();
    }

    /**
     * one table more row status column update
     * @param count row count
     * @return SQL with (count)?
     */
    public String sqlUpdate(int count){
        if(this.data.getTableKey().size() > 1){
            throw new UnsupportedOperationException("联合主键不提供批量更新操作！");
        }
        if(count < 1){
            count = 1;
        }

        List<String> list = new ArrayList<>();
        for (EntityColumn entityColumn : this.data.getTableColumn()) {
            list.add(entityColumn.getName());
        }
        List<String> caseWhen = new ArrayList<>();
        for (EntityColumn entityColumn : this.data.getTableColumn()) {
            XSql sql = new XSql()
                    .caseStart(this.data.getTableKey().get(0).getName())
                    .whenThen(count)
                    .caseEnd();
            caseWhen.add(sql.toSql());
        }

        XSql sql = new XSql()
                .update(this.data.getTableName().getName())
                .set(list,caseWhen)
                .where()
                .in(this.data.getTableKey().get(0).getName(),count);

        return sql.toSql();
    }


    /**
     * one table more row select
     * @return SQL with (?)? , ? is 1 or 0 , if status column used and config it on , it will 1
     */
    public String sqlSelectByStatus(){
        List<Column> list = new ArrayList<>();
        list.addAll(this.data.getTableColumn());

        XSql sql = new XSql()
                .select(list)
                .from(this.data.getTableName().getName());
        if(config.isOnlySelectUseStatus()){
            sql.where(this.data.getTableStatus().getName(),"=");
        }

        return sql.toSql();
    }

    /**
     * one table more row count select
     * @return SQL with (?)? , ? is 1 or 0 , if status column used and config it on , it will 1
     */
    public String sqlSelectCount(){
        XSql sql = new XSql()
                .select()
                .funCount()
                .from(this.data.getTableName().getName());
        if(config.isOnlySelectUseStatus()){
            sql.where(this.data.getTableStatus().getName(),"=");
        }

        return sql.toSql();
    }

    /**
     * one table more row count delete
     * @param count row count
     * @return SQL with (count)?
     */
    public String sqlDeleteByIds(int count){
        if(this.data.getTableKey().size() > 1){
            throw new UnsupportedOperationException("联合主键不提供批量删除操作！");
        }
        if(count < 1){
            count = 1;
        }

        XSql sql = new XSql()
                .delete()
                .from(this.data.getTableName().getName())
                .where()
                .in(this.data.getTableKey().get(0).getName(),count);

        return sql.toSql();
    }

    /**
     * one table more row status column update
     * @param count row count
     * @return SQL with (count)?
     */
    public String sqlUpdateStatusByIds(int count){
        if(this.data.getTableStatus() == null){
            throw new UnsupportedOperationException("没有任何字段被标注为@" + EStatus.class.getSimpleName());
        }
        if(this.data.getTableKey().size() > 1){
            throw new UnsupportedOperationException("联合主键不提供批量更新状态操作！");
        }
        if(count < 1){
            count = 1;
        }

        XSql sql = new XSql()
                .update(this.data.getTableName().getName())
                .set(this.data.getTableStatus().geteColumn().name())
                .where()
                .in(this.data.getTableKey().get(0).getName(),count);

        return sql.toSql();
    }

}
