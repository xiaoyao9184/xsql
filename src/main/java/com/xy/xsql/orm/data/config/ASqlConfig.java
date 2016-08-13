package com.xy.xsql.orm.data.config;

/**
 * ASql配置
 * Created by xiaoyao9184 on 2016/6/26.
 */
public class ASqlConfig
        implements Cloneable{

    private final Class clazz;
    private String tablePrefix;
    private String levelString;
    private boolean onlySelectUseStatus;
    private boolean useCache;

    public ASqlConfig(Class clazz){
        this.clazz = clazz;
        this.levelString = "_";
    }


    /**
     * 获取表名前缀
     * @return 不包含'等级连接字符串'
     */
    public String getTablePrefix() {
        return tablePrefix;
    }

    public void setTablePrefix(String tablePrefix) {
        this.tablePrefix = tablePrefix;
    }

    /**
     * 等级连接字符串
     * @return 字符串
     */
    public String getLevelString() {
        return levelString;
    }

    public void setLevelString(String levelString) {
        this.levelString = levelString;
    }

    /**
     * SELECT语句过滤状态
     * @return 是/否
     */
    public boolean isOnlySelectUseStatus() {
        return onlySelectUseStatus;
    }

    public void setOnlySelectUseStatus(boolean onlySelectUseStatus) {
        this.onlySelectUseStatus = onlySelectUseStatus;
    }

    /**
     * 是否使用缓存
     * @return 是/否
     */
    public boolean isUseCache() {
        return useCache;
    }

    public void setUseCache(boolean useCache) {
        this.useCache = useCache;
    }

    /**
     * 获取实体类
     * @return 实体类
     */
    public Class getClazz() {
        return clazz;
    }


    @SuppressWarnings({"RedundantStringConstructorCall", "CloneDoesntCallSuperClone"})
    @Override
    public ASqlConfig clone() {
        ASqlConfig result = new ASqlConfig(this.getClazz());
        result.tablePrefix = this.tablePrefix;
        result.levelString = this.levelString;
        result.onlySelectUseStatus = this.onlySelectUseStatus;
        result.useCache = this.useCache;
        return result;
    }

    public ASqlConfig clone(Class clazz) {
        ASqlConfig result = new ASqlConfig(clazz);
        result.tablePrefix = this.tablePrefix;
        result.levelString = this.levelString;
        result.onlySelectUseStatus = this.onlySelectUseStatus;
        result.useCache = this.useCache;
        return result;
    }
}
