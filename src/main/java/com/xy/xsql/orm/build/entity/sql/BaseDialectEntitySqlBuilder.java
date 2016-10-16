package com.xy.xsql.orm.build.entity.sql;

import com.xy.xsql.orm.annotation.EntityColumnStatus;
import com.xy.xsql.orm.build.sentence.data.CodeSentenceDataBuilder;
import com.xy.xsql.orm.build.sentence.sql.ElementsSentenceSqlBuilder;
import com.xy.xsql.orm.core.XSql;
import com.xy.xsql.orm.data.config.EntitySqlBuilderConfig;
import com.xy.xsql.orm.data.entity.SqlColumn;
import com.xy.xsql.orm.data.entity.SqlEntityData;
import com.xy.xsql.orm.data.sql.info.Column;
import com.xy.xsql.orm.data.sql.sentence.ElementsSentence;
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
    
    protected SqlEntityData data;

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
    public DialectEntitySqlBuilder cacheData(SqlEntityData data) {
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
        for (SqlColumn sqlColumn : this.data.getTableColumn()) {
            list.add(sqlColumn.getRealName());
        }

        XSql sql = new XSql()
                .insert(this.data.getTableName().getRealName())
                .values(list);

        return sql.toSql();
    }

    /**
     * one table all columns update
     * @return SQL with (+)? , + is 1 or more , is columns+keys count
     */
    public String sqlUpdateById(){
        List<String> list = new ArrayList<>();
        for (SqlColumn sqlColumn : this.data.getTableColumn()) {
            list.add(sqlColumn.getRealName());
        }

        XSql sql = new XSql()
                .update(this.data.getTableName().getRealName())
                .set(list)
                .where(this.data.getTableKey().get(0).getRealName(),"=");
        for(int i = 1; i < this.data.getTableKey().size(); i++){
            sql.and(this.data.getTableKey().get(i).getRealName(),"=");
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
                .from(this.data.getTableName().getRealName())
                .where(this.data.getTableKey().get(0).getRealName(),"=");
        for(int i = 1; i < this.data.getTableKey().size(); i++){
            sql.and(this.data.getTableKey().get(i).getRealName(),"=");
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
                .from(this.data.getTableName().getRealName())
                .where(this.data.getTableKey().get(0).getRealName(),"=");
        for(int i = 1; i < this.data.getTableKey().size(); i++){
            sql.and(this.data.getTableKey().get(i).getRealName(),"=");
        }

        return sql.toSql();
    }

    /**
     * one table status column update
     * @return SQL with (+)? , + is 1 or more , is keys count
     */
    public String sqlUpdateStatusById(){
        if(this.data.getTableStatus() == null){
            throw new UnsupportedOperationException("没有任何字段被标注为@" + EntityColumnStatus.class.getSimpleName());
        }
        XSql sql = new XSql()
                .update(this.data.getTableName().getRealName())
                .set(this.data.getTableStatus().getEntityColumn().name())
                .where(this.data.getTableKey().get(0).getRealName(),"=");
        for(int i = 1; i < this.data.getTableKey().size(); i++){
            sql.and(this.data.getTableKey().get(i).getRealName(),"=");
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
        for (SqlColumn sqlColumn : this.data.getTableColumn()) {
            list.add(sqlColumn.getRealName());
        }

        XSql sql = new XSql()
                .insert(this.data.getTableName().getRealName())
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
        for (SqlColumn sqlColumn : this.data.getTableColumn()) {
            list.add(sqlColumn.getRealName());
        }
        List<String> caseWhen = new ArrayList<>();
        for (SqlColumn sqlColumn : this.data.getTableColumn()) {
            XSql sql = new XSql()
                    .caseStart(this.data.getTableKey().get(0).getRealName())
                    .whenThen(count)
                    .caseEnd();
            caseWhen.add(sql.toSql());
        }

        XSql sql = new XSql()
                .update(this.data.getTableName().getRealName())
                .set(list,caseWhen)
                .where()
                .in(this.data.getTableKey().get(0).getRealName(),count);

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
                .from(this.data.getTableName().getRealName());
        if(config.isOnlySelectUseStatus()){
            sql.where(this.data.getTableStatus().getRealName(),"=");
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
                .from(this.data.getTableName().getRealName());
        if(config.isOnlySelectUseStatus()){
            sql.where(this.data.getTableStatus().getRealName(),"=");
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
                .from(this.data.getTableName().getRealName())
                .where()
                .in(this.data.getTableKey().get(0).getRealName(),count);

        return sql.toSql();
    }

    /**
     * one table more row status column update
     * @param count row count
     * @return SQL with (count)?
     */
    public String sqlUpdateStatusByIds(int count){
        if(this.data.getTableStatus() == null){
            throw new UnsupportedOperationException("没有任何字段被标注为@" + EntityColumnStatus.class.getSimpleName());
        }
        if(this.data.getTableKey().size() > 1){
            throw new UnsupportedOperationException("联合主键不提供批量更新状态操作！");
        }
        if(count < 1){
            count = 1;
        }

        XSql sql = new XSql()
                .update(this.data.getTableName().getRealName())
                .set(this.data.getTableStatus().getEntityColumn().name())
                .where()
                .in(this.data.getTableKey().get(0).getRealName(),count);

        return sql.toSql();
    }

}
