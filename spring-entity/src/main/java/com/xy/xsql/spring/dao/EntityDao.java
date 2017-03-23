package com.xy.xsql.spring.dao;

import com.xy.xsql.entity.model.template.param.EntityTemplateTreeArg;
import com.xy.xsql.model.page.PageQuery;
import com.xy.xsql.model.page.PageResult;

import java.util.List;

/**
 *
 * @param <Entity> 实体
 * @param <ID> ID
 */
public interface EntityDao<Entity, ID> {

    /**
     * 表是否存在
     * @return 存在/不存在
     */
    Boolean checkTable();

    /**
     * 创建表
     */
    void createTable();

    /**
     * 删除表
     */
    void dropTable();

    /**
     * 获取
     * @param id ID
     * @return 实体
     */
    Entity getById(ID id);

    /**
     * 列举
     * @param id ID
     * @return 实体 集合
     */
    List<Entity> listByIds(ID... id);

    /**
     * 删除
     * @param id ID
     */
    void deleteById(ID... id);

    /**
     * 新增
     * @param entity 实体
     */
    void save(Entity... entity);

    /**
     * 修改
     * @param entity 实体
     */
    void update(Entity... entity);

    /**
     * 补丁更新
     * @param entity
     */
    void updatePath(Entity entity);

    /**
     * 更新状态
     * @param status 状态
     * @param id ID
     */
    void updateStatus(Enum status, ID... id);

    /**
     * 获取 根据参数
     * @param args 参数
     * @return 实体
     */
    Entity getByArg(Object... args);

    /**
     * 列举 根据参数
     * @param args 参数
     * @return 实体 集合
     */
    List<Entity> listByArg(Object... args);

    void deleteListByArg(Object... args);

    <T> T searchById(Class<T> resultClass, ID id);

    <T> List<T> searchListByIds(Class<T> resultClass, ID... ids);

    <T> List<T> searchListByArg(Class<T> resultClass, Object... args);

    <T> PageResult<T> searchPageByArg(PageQuery<T> pageQuery, Object... args);

    <T> List<T> searchListByTreeArg(Class<T> resultClass, EntityTemplateTreeArg entityTemplateTreeArg);

    <T> PageResult<T> searchPageByTreeArg(PageQuery<T> pageQuery, EntityTemplateTreeArg entityTemplateTreeArg);


    interface EntityDaoInit {
        /**
         * 初始化之前
         */
        void beforeInit();

        /**
         * 初始化之后
         */
        void afterInit();

        /**
         * 方言初始化之后
         */
        void afterDbDialectInit();

        /**
         * 实体数据初始化之后
         */
        void afterEntityDataInit();
    }
}
