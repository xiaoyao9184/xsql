package com.xy.xsql.orm.build.entity.sql;

import com.xy.xsql.orm.build.entity.sql.agreement.SqlEntityCRUD;
import com.xy.xsql.orm.data.config.DialectEntitySqlBuildConfig;
import com.xy.xsql.orm.data.entity.EntityTemplate;
import com.xy.xsql.orm.dialect.none.BaseEntitySql;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Default EntitySqlBuilder
 * it is T-SQL prototype
 * Created by xiaoyao9184 on 2016/1/13.
 */
@SuppressWarnings({"StringBufferReplaceableByString", "JavaDoc", "unused", "StatementWithEmptyBody", "WeakerAccess"})
public class BaseEntitySqlBuilder implements EntitySqlBuilder {

/*
 * Fields
 */

    protected Logger log;

    protected DialectEntitySqlBuildConfig config;
    
    protected EntityTemplate template;

    protected BaseEntitySql entitySql;

    protected Map<String,String> sqlCache;


    public BaseEntitySqlBuilder(){
        this.log = LoggerFactory.getLogger(this.getClass());
        this.sqlCache = new HashMap<>();
    }


/*
 * Cache
 */

    @Override
    public BaseEntitySqlBuilder cacheConfig(DialectEntitySqlBuildConfig config) {
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
        return entitySql.getClass().isAssignableFrom(agreementClass);
    }

    @Override
    public Void build(Void aVoid) {
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
        return (T) entitySql;
    }

    @Override
    public EntityTemplate getTemplate() {
        return this.template;
    }

    /**
     *
     */
    public void resetCache(){
        this.sqlCache.clear();
    }

    /**
     * 插入
     * @return SQL
     */
    String sqlInsert(){
//        if(!isSupport(SqlEntityCRUD.class)){
//            throw new RuntimeException("This dialect cant support " + SqlEntityCRUD.class.getName() + " Agreement Sql!");
//        }
        String sql;
        if(this.sqlCache.containsKey(SqlEntityCRUD.InsertSql)){
            SqlEntityCRUD sqlEntityCRUD = entitySql;
            sql = sqlEntityCRUD.getInsertSql(this.template);
            this.sqlCache.put(SqlEntityCRUD.InsertSql,sql);
            return sql;
        }else{
            return this.sqlCache.get(SqlEntityCRUD.InsertSql);
        }
    }

    /**
     * 插入
     * @param entityCount 实体 数量
     * @return SQL
     */
    String sqlInsertByEntityCount(int entityCount){
        SqlEntityCRUD sqlEntityCRUD = entitySql;
        return sqlEntityCRUD.getInsertByEntityCountSql(this.template,entityCount);
    }

    /**
     * 获取
     * @return SQL
     */
    String sqlSelectById(){
        String sql;
        if(this.sqlCache.containsKey(SqlEntityCRUD.SelectByIdSql)){
            SqlEntityCRUD sqlEntityCRUD = entitySql;
            sql = sqlEntityCRUD.getSelectByIdSql(this.template);
            this.sqlCache.put(SqlEntityCRUD.SelectByIdSql,sql);
            return sql;
        }else{
            return this.sqlCache.get(SqlEntityCRUD.SelectByIdSql);
        }
    }

    /**
     * 获取
     * @param idCount ID 数量
     * @return SQL
     */
    String sqlSelectByIds(int idCount){
        SqlEntityCRUD sqlEntityCRUD = entitySql;
        return sqlEntityCRUD.getSelectByIdsSql(this.template,idCount);
    }

    /**
     * 更新
     * @return SQL
     */
    String sqlUpdateById(){
        String sql;
        if(this.sqlCache.containsKey(SqlEntityCRUD.UpdateByIdSql)){
            SqlEntityCRUD sqlEntityCRUD = entitySql;
            sql = sqlEntityCRUD.getUpdateByIdSql(this.template);
            this.sqlCache.put(SqlEntityCRUD.UpdateByIdSql,sql);
            return sql;
        }else{
            return this.sqlCache.get(SqlEntityCRUD.UpdateByIdSql);
        }
    }

    /**
     * 更新
     * @param idCount ID 数量
     * @return SQL
     */
    String sqlUpdateByIds(int idCount){
        SqlEntityCRUD sqlEntityCRUD = entitySql;
        return sqlEntityCRUD.getUpdateByIdsSql(this.template,idCount);
    }

    /**
     * 删除
     * @return SQL
     */
    String sqlDeleteById(){
        String sql;
        if(this.sqlCache.containsKey(SqlEntityCRUD.DeleteByIdSql)){
            SqlEntityCRUD sqlEntityCRUD = entitySql;
            sql = sqlEntityCRUD.getDeleteByIdSql(this.template);
            this.sqlCache.put(SqlEntityCRUD.DeleteByIdSql,sql);
            return sql;
        }else{
            return this.sqlCache.get(SqlEntityCRUD.DeleteByIdSql);
        }
    }

    /**
     * 删除
     * @param idCount ID 数量
     * @return SQL
     */
    String sqlDeleteByIds(int idCount){
        SqlEntityCRUD sqlEntityCRUD = entitySql;
        return sqlEntityCRUD.getDeleteByIdsSql(this.template,idCount);
    }


}
