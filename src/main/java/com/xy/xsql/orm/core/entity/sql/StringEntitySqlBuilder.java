package com.xy.xsql.orm.core.entity.sql;

import com.sun.applet2.AppletParameters;
import com.xy.xsql.orm.core.entity.sql.agreement.*;
import com.xy.xsql.orm.data.config.EntityDialectSqlBuildConfig;
import com.xy.xsql.orm.data.entity.EntityTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * String EntitySqlBuilder
 * it is T-SQL prototype
 * Created by xiaoyao9184 on 2016/1/13.
 */
@SuppressWarnings({"StringBufferReplaceableByString", "JavaDoc", "unused", "StatementWithEmptyBody", "WeakerAccess"})
public class StringEntitySqlBuilder implements EntitySqlBuilder {


    public static List<Class> supportAgreementSqlClassList;

    static {
        supportAgreementSqlClassList = new ArrayList<>();
        supportAgreementSqlClassList.add(SqlEntityCRUD.class);
        supportAgreementSqlClassList.add(SqlEntityLinkSearch.class);
        supportAgreementSqlClassList.add(SqlEntitySearchArg.class);
        supportAgreementSqlClassList.add(SqlEntityStatusUpdate.class);
        supportAgreementSqlClassList.add(SqlEntityTableManage.class);
        supportAgreementSqlClassList.add(SqlPage.class);
    }
/*
 * Fields
 */

    protected Logger log;

    protected EntityDialectSqlBuildConfig<StringEntitySqlBuilder> config;
    
    protected EntityTemplate template;

    protected Map<Class,Object> agreementSql;
    protected Map<String,String> sqlCache;


    public StringEntitySqlBuilder(){
        this.log = LoggerFactory.getLogger(this.getClass());
        this.sqlCache = new HashMap<>();
    }


/*
 * Cache
 */

    @Override
    public StringEntitySqlBuilder cacheConfig(EntityDialectSqlBuildConfig config) {
        this.config = config;
        return this;
    }

    @Override
    public EntitySqlBuilder cacheTemplate(EntityTemplate template) {
        this.template = template;
        return this;
    }

    @Override
    public Boolean isSupport(Class<?> agreementClass) {
        return this.agreementSql.keySet().contains(agreementClass);
    }

    @Override
    public Void build(Void aVoid) {
        this.buildAgreementSlq();

        this.sqlInsert();
        this.sqlSelectById();
        this.sqlUpdateById();
        this.sqlDeleteById();
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T toAgreementSql(Class<T> agreementClass) {
        if(!isSupport(agreementClass)){
            throw new RuntimeException("This dialect cant support " + agreementClass.getName() + " Agreement Sql!");
        }
        return (T) this.agreementSql.get(agreementClass);
    }

    @Override
    public EntityTemplate getTemplate() {
        return this.template;
    }

    /**
     * Reset Cache
     */
    public void resetCache(){
        this.sqlCache.clear();
    }


    /**
     *
     */
    private void buildAgreementSlq(){
        this.agreementSql = new HashMap<>();
        if(this.config.isAllInOne()){
            try{
                Object agreementSlq = this.config.getAllInOne().newInstance();
                for (Class supportAgreementSqlClass: supportAgreementSqlClassList) {
                    if(supportAgreementSqlClass.isInstance(agreementSlq)){
                        this.agreementSql.put(supportAgreementSqlClass, agreementSlq);
                    }else{
                        log.debug("Ignore not match instance " + agreementSlq.getClass() + " is not " + supportAgreementSqlClass.getName());
                    }
                }
            } catch (Exception ex){
                throw new RuntimeException("Cant instance ",ex);
            }
        }else{
            Map<Class,Class> map = this.config.getDialectMap();
            for (Map.Entry<Class,Class> kv :map.entrySet()) {
                try{
                    Object agreementSlq = kv.getValue().newInstance();
                    if(kv.getKey().isInstance(agreementSlq)){
                        this.agreementSql.put(kv.getKey(), agreementSlq);
                    }else{
                        log.debug("Ignore not match instance " + agreementSlq.getClass() + " is not " + kv.getKey().getName());
                    }
                } catch (Exception ex){
                    log.error("Ignore cant instance, " + ex.getMessage());
                }
            }
        }
    }


    /**
     * 插入
     * @return SQL
     */
    String sqlInsert(){
        if(!isSupport(SqlEntityCRUD.class)){
            throw new RuntimeException("This dialect cant support " + SqlEntityCRUD.class.getName() + " Agreement Sql!");
        }
        String sql;
        if(this.sqlCache.containsKey(SqlEntityCRUD.InsertSql)){
            return this.sqlCache.get(SqlEntityCRUD.InsertSql);
        }else{
            SqlEntityCRUD sqlEntityCRUD = this.toAgreementSql(SqlEntityCRUD.class);
            sql = sqlEntityCRUD.getInsertSql(this.template);
            this.sqlCache.put(SqlEntityCRUD.InsertSql,sql);
            return sql;
        }
    }

    /**
     * 插入
     * @param entityCount 实体 数量
     * @return SQL
     */
    String sqlInsertByEntityCount(int entityCount){
        if(!isSupport(SqlEntityCRUD.class)){
            throw new RuntimeException("This dialect cant support " + SqlEntityCRUD.class.getName() + " Agreement Sql!");
        }
        String sql;
        if(this.sqlCache.containsKey(SqlEntityCRUD.InsertByEntityCountSql + entityCount)){
            return this.sqlCache.get(SqlEntityCRUD.InsertByEntityCountSql + entityCount);
        }else{
            SqlEntityCRUD sqlEntityCRUD = this.toAgreementSql(SqlEntityCRUD.class);
            sql = sqlEntityCRUD.getInsertByEntityCountSql(this.template,entityCount);
            this.sqlCache.put(SqlEntityCRUD.InsertByEntityCountSql + entityCount,sql);
            return sql;
        }
    }

    /**
     * 获取
     * @return SQL
     */
    String sqlSelectById(){
        if(!isSupport(SqlEntityCRUD.class)){
            throw new RuntimeException("This dialect cant support " + SqlEntityCRUD.class.getName() + " Agreement Sql!");
        }
        String sql;
        if(this.sqlCache.containsKey(SqlEntityCRUD.SelectByIdSql)){
            return this.sqlCache.get(SqlEntityCRUD.SelectByIdSql);
        }else{
            SqlEntityCRUD sqlEntityCRUD = this.toAgreementSql(SqlEntityCRUD.class);
            sql = sqlEntityCRUD.getSelectByIdSql(this.template);
            this.sqlCache.put(SqlEntityCRUD.SelectByIdSql,sql);
            return sql;
        }
    }

    /**
     * 获取
     * @param idCount ID 数量
     * @return SQL
     */
    String sqlSelectByIds(int idCount){
        if(!isSupport(SqlEntityCRUD.class)){
            throw new RuntimeException("This dialect cant support " + SqlEntityCRUD.class.getName() + " Agreement Sql!");
        }
        String sql;
        if(this.sqlCache.containsKey(SqlEntityCRUD.SelectByIdsSql + idCount)){
            return this.sqlCache.get(SqlEntityCRUD.SelectByIdsSql + idCount);
        }else{
            SqlEntityCRUD sqlEntityCRUD = this.toAgreementSql(SqlEntityCRUD.class);
            sql = sqlEntityCRUD.getSelectByIdsSql(this.template,idCount);
            this.sqlCache.put(SqlEntityCRUD.SelectByIdsSql + idCount,sql);
            return sql;
        }
    }

    /**
     * 更新
     * @return SQL
     */
    String sqlUpdateById(){
        if(!isSupport(SqlEntityCRUD.class)){
            throw new RuntimeException("This dialect cant support " + SqlEntityCRUD.class.getName() + " Agreement Sql!");
        }
        String sql;
        if(this.sqlCache.containsKey(SqlEntityCRUD.UpdateByIdSql)){
            return this.sqlCache.get(SqlEntityCRUD.UpdateByIdSql);
        }else{
            SqlEntityCRUD sqlEntityCRUD = this.toAgreementSql(SqlEntityCRUD.class);
            sql = sqlEntityCRUD.getUpdateByIdSql(this.template);
            this.sqlCache.put(SqlEntityCRUD.UpdateByIdSql,sql);
            return sql;
        }
    }

    /**
     * 更新
     * @param idCount ID 数量
     * @return SQL
     */
    String sqlUpdateByIds(int idCount){
        if(!isSupport(SqlEntityCRUD.class)){
            throw new RuntimeException("This dialect cant support " + SqlEntityCRUD.class.getName() + " Agreement Sql!");
        }
        String sql;
        if(this.sqlCache.containsKey(SqlEntityCRUD.UpdateByIdsSql + idCount)){
            return this.sqlCache.get(SqlEntityCRUD.UpdateByIdsSql + idCount);
        }else{
            SqlEntityCRUD sqlEntityCRUD = this.toAgreementSql(SqlEntityCRUD.class);
            sql = sqlEntityCRUD.getUpdateByIdsSql(this.template,idCount);
            this.sqlCache.put(SqlEntityCRUD.UpdateByIdsSql + idCount,sql);
            return sql;
        }
    }

    /**
     * 删除
     * @return SQL
     */
    String sqlDeleteById(){
        if(!isSupport(SqlEntityCRUD.class)){
            throw new RuntimeException("This dialect cant support " + SqlEntityCRUD.class.getName() + " Agreement Sql!");
        }
        String sql;
        if(this.sqlCache.containsKey(SqlEntityCRUD.DeleteByIdSql)){
            return this.sqlCache.get(SqlEntityCRUD.DeleteByIdSql);
        }else{
            SqlEntityCRUD sqlEntityCRUD = this.toAgreementSql(SqlEntityCRUD.class);
            sql = sqlEntityCRUD.getDeleteByIdSql(this.template);
            this.sqlCache.put(SqlEntityCRUD.DeleteByIdSql,sql);
            return sql;
        }
    }

    /**
     * 删除
     * @param idCount ID 数量
     * @return SQL
     */
    String sqlDeleteByIds(int idCount){
        if(!isSupport(SqlEntityCRUD.class)){
            throw new RuntimeException("This dialect cant support " + SqlEntityCRUD.class.getName() + " Agreement Sql!");
        }
        String sql;
        if(this.sqlCache.containsKey(SqlEntityCRUD.DeleteByIdsSql + idCount)){
            return this.sqlCache.get(SqlEntityCRUD.DeleteByIdsSql + idCount);
        }else{
            SqlEntityCRUD sqlEntityCRUD = this.toAgreementSql(SqlEntityCRUD.class);
            sql = sqlEntityCRUD.getDeleteByIdsSql(this.template,idCount);
            this.sqlCache.put(SqlEntityCRUD.DeleteByIdsSql + idCount,sql);
            return sql;
        }
    }


}
