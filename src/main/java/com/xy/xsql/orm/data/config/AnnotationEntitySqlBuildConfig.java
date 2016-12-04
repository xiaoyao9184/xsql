package com.xy.xsql.orm.data.config;

import com.xy.xsql.orm.build.entity.sql.EntitySqlBuilder;
import com.xy.xsql.orm.mapping.type.TypeMapper;

/**
 * Created by xiaoyao9184 on 2016/10/15.
 */
public class AnnotationEntitySqlBuildConfig extends AnnotationEntityTemplateBuildConfig {

    private boolean onlySelectUseStatus;
    private Class<? extends EntitySqlBuilder> dialectEntitySqlBuilder;

    public boolean isOnlySelectUseStatus() {
        return onlySelectUseStatus;
    }

    public Class<? extends EntitySqlBuilder> getDialectEntitySqlBuilder() {
        return dialectEntitySqlBuilder;
    }


    /**
     * Config Only Select Use Status
     * @param onlySelectUseStatus
     * @return This
     */
    public AnnotationEntitySqlBuildConfig withOnlySelectUseStatus(boolean onlySelectUseStatus) {
        this.onlySelectUseStatus = onlySelectUseStatus;
        return this;
    }

    /**
     * Config EntitySqlBuilder
     * @param dialectEntitySqlBuilder EntitySqlBuilder
     * @return This
     */
    public AnnotationEntitySqlBuildConfig withDialectEntitySqlBuilder(Class<? extends EntitySqlBuilder> dialectEntitySqlBuilder) {
        this.dialectEntitySqlBuilder = dialectEntitySqlBuilder;
        return this;
    }

    /**
     * Config TypeMapper
     * @param typeMapper TypeMapper
     * @return This
     */
    @Override
    public AnnotationEntitySqlBuildConfig withTypeMapper(TypeMapper<Class<?>,String> typeMapper){
        this.typeMapper = typeMapper;
        return this;
    }

    /**
     * Config Prefix Suffix Separator
     * @param separator Prefix Suffix Separator
     * @return This
     */
    @Override
    public AnnotationEntitySqlBuildConfig withSeparator(String separator) {
        this.separator = separator;
        return this;
    }

    /**
     * Config Name Prefix
     * @param namePrefix Name Prefix
     * @return This
     */
    @Override
    public AnnotationEntitySqlBuildConfig withNamePrefix(String namePrefix){
        this.namePrefix = namePrefix;
        return this;
    }

    /**
     * Config Name Suffix
     * @param nameSuffix Name Suffix
     * @return This
     */
    @Override
    public AnnotationEntitySqlBuildConfig withNameSuffix(String nameSuffix){
        this.nameSuffix = nameSuffix;
        return this;
    }

    /**
     * Config Alias Name Prefix
     * @param aliasNamePrefix Alias Name Prefix
     * @return This
     */
    @Override
    public AnnotationEntitySqlBuildConfig withAliasNamePrefix(String aliasNamePrefix){
        this.aliasNamePrefix = aliasNamePrefix;
        return this;
    }

    /**
     * Config true if you want turn on Multiple Key support
     * @param yesNo Yes/No
     * @return This
     */
    @Override
    public AnnotationEntitySqlBuildConfig withSupportMultipleKey(Boolean yesNo){
        this.supportMultipleKey = yesNo;
        return this;
    }

    /**
     * Config true if you want scan Status
     * @param yesNo Yes/No
     * @return This
     */
    @Override
    public AnnotationEntitySqlBuildConfig withScanStatus(Boolean yesNo){
        this.scanStatus = yesNo;
        return this;
    }

    /**
     * Config true if you want scan Entity
     * @param yesNo Yes/No
     * @return This
     */
    @Override
    public AnnotationEntitySqlBuildConfig withScanLink(Boolean yesNo){
        this.scanLink = yesNo;
        return this;
    }

    /**
     * Config true if you want scan Param
     * @param yesNo Yes/No
     * @return This
     */
    @Override
    public AnnotationEntitySqlBuildConfig withScanParam(Boolean yesNo){
        this.scanParam = yesNo;
        return this;
    }

    /**
     * Config true if you want scan Order
     * @param yesNo Yes/No
     * @return This
     */
    @Override
    public AnnotationEntityTemplateBuildConfig withScanOrder(Boolean yesNo){
        this.scanOrder = yesNo;
        return this;
    }




    public DialectEntitySqlBuildConfig toEntitySqlBuilderConfig(){
        return new DialectEntitySqlBuildConfig()
                .onlySelectUseStatus(this.onlySelectUseStatus);
    }

}
