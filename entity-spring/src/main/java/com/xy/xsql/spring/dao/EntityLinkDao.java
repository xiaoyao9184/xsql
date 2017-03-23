package com.xy.xsql.spring.dao;

import java.util.List;

/**
 *
 * @param <EntityLink> 实体
 * @param <ID> ID
 * @param <LinkID> ID
 */
public interface EntityLinkDao<EntityLink, ID, LinkID>
        extends EntityDao<EntityLink, ID> {

    /**
     * 获取
     * @param linkID 关系ID
     * @return 关系实体
     */
    EntityLink getByLinkId(LinkID... linkID);

    /**
     * 获取
     * @param linkEntity 实体类
     * @param linkID 关系ID
     * @return 关系实体
     */
    List<EntityLink> listByLinkId(Class linkEntity, LinkID linkID);


    /**
     * 删除
     * @param linkID 关系ID
     */
    void deleteByLinkId(LinkID... linkID);

    /**
     * 删除
     * @param linkEntity 实体类
     * @param linkID 关系ID
     */
    void deleteByLinkId(Class linkEntity, LinkID linkID);


    <ResultEntity> List<ResultEntity> listEntityByLinkId(Class<ResultEntity> resultEntity, LinkID... linkID);

    <ResultEntity> List<ResultEntity> listEntityByLinkId(Class<ResultEntity> resultEntity, Class linkEntity, LinkID linkID);


    <ResultEntity> List<ResultEntity> distinctEntityByLinkId(Class<ResultEntity> resultEntity, Class linkEntity, LinkID... linkID);
}
