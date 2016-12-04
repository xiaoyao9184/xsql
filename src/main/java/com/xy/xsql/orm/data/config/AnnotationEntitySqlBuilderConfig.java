package com.xy.xsql.orm.data.config;

import com.xy.xsql.orm.build.entity.sql.DialectEntitySqlBuilder;
import com.xy.xsql.orm.mapping.type.TypeMapper;

/**
 * Created by xiaoyao9184 on 2016/10/15.
 */
public class AnnotationEntitySqlBuilderConfig extends AnnotationEntityTemplateBuilderConfig {

    private boolean onlySelectUseStatus;
    private Class<? extends DialectEntitySqlBuilder> dialectEntitySqlBuilder;

    public boolean isOnlySelectUseStatus() {
        return onlySelectUseStatus;
    }

    public Class<? extends DialectEntitySqlBuilder> getDialectEntitySqlBuilder() {
        return dialectEntitySqlBuilder;
    }


    /**
     * Config Only Select Use Status
     * @param onlySelectUseStatus
     * @return This
     */
    public AnnotationEntitySqlBuilderConfig withOnlySelectUseStatus(boolean onlySelectUseStatus) {
        this.onlySelectUseStatus = onlySelectUseStatus;
        return this;
    }

    /**
     * Config DialectEntitySqlBuilder
     * @param dialectEntitySqlBuilder DialectEntitySqlBuilder
     * @return This
     */
    public AnnotationEntitySqlBuilderConfig withDialectEntitySqlBuilder(Class<? extends DialectEntitySqlBuilder> dialectEntitySqlBuilder) {
        this.dialectEntitySqlBuilder = dialectEntitySqlBuilder;
        return this;
    }

    /**
     * Config TypeMapper
     * @param typeMapper TypeMapper
     * @return This
     */
    @Override
    public AnnotationEntitySqlBuilderConfig withTypeMapper(TypeMapper<Class<?>,String> typeMapper){
        this.typeMapper = typeMapper;
        return this;
    }

    /**
     * Config Prefix Suffix Separator
     * @param separator Prefix Suffix Separator
     * @return This
     */
    @Override
    public AnnotationEntitySqlBuilderConfig withSeparator(String separator) {
        this.separator = separator;
        return this;
    }

    /**
     * Config Name Prefix
     * @param namePrefix Name Prefix
     * @return This
     */
    @Override
    public AnnotationEntitySqlBuilderConfig withNamePrefix(String namePrefix){
        this.namePrefix = namePrefix;
        return this;
    }

    /**
     * Config Name Suffix
     * @param nameSuffix Name Suffix
     * @return This
     */
    @Override
    public AnnotationEntitySqlBuilderConfig withNameSuffix(String nameSuffix){
        this.nameSuffix = nameSuffix;
        return this;
    }

    /**
     * Config Alias Name Prefix
     * @param aliasNamePrefix Alias Name Prefix
     * @return This
     */
    @Override
    public AnnotationEntitySqlBuilderConfig withAliasNamePrefix(String aliasNamePrefix){
        this.aliasNamePrefix = aliasNamePrefix;
        return this;
    }

    /**
     * Config Alias Name Suffix
     * @param aliasNameSuffix Alias Name Suffix
     * @return This
     */
    @Override
    public AnnotationEntitySqlBuilderConfig withAliasNameSuffix(String aliasNameSuffix){
        this.aliasNameSuffix = aliasNameSuffix;
        return this;
    }

    /**
     * Config true if you want turn on Multiple Key support
     * @param yesNo Yes/No
     * @return This
     */
    @Override
    public AnnotationEntitySqlBuilderConfig withSupportMultipleKey(Boolean yesNo){
        this.supportMultipleKey = yesNo;
        return this;
    }

    /**
     * Config true if you want scan Status
     * @param yesNo Yes/No
     * @return This
     */
    @Override
    public AnnotationEntitySqlBuilderConfig withScanStatus(Boolean yesNo){
        this.scanStatus = yesNo;
        return this;
    }

    /**
     * Config true if you want scan Entity
     * @param yesNo Yes/No
     * @return This
     */
    @Override
    public AnnotationEntitySqlBuilderConfig withScanEntity(Boolean yesNo){
        this.scanEntity = yesNo;
        return this;
    }

    /**
     * Config true if you want scan Param
     * @param yesNo Yes/No
     * @return This
     */
    @Override
    public AnnotationEntitySqlBuilderConfig withScanParam(Boolean yesNo){
        this.scanParam = yesNo;
        return this;
    }

    /**
     * Config true if you want scan Order
     * @param yesNo Yes/No
     * @return This
     */
    @Override
    public AnnotationEntityTemplateBuilderConfig withScanOrder(Boolean yesNo){
        this.scanOrder = yesNo;
        return this;
    }




    public DialectEntitySqlBuilderConfig toEntitySqlBuilderConfig(){
        return new DialectEntitySqlBuilderConfig()
                .onlySelectUseStatus(this.onlySelectUseStatus);
    }

}
