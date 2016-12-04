package com.xy.xsql.orm.data.config;

import com.xy.xsql.orm.core.ConfigInOut;
import com.xy.xsql.orm.core.entity.sql.AnnotationEntitySqlBuilder;
import com.xy.xsql.orm.core.entity.sql.EntitySqlBuilder;

/**
 * Created by xiaoyao9184 on 2016/10/15.
 */
public class AnnotationEntitySqlBuildConfig implements
        ConfigInOut<AnnotationEntitySqlBuildConfig,AnnotationEntitySqlBuilder>,
        Cloneable {

    private boolean onlySelectUseStatus;
    private Class<? extends EntitySqlBuilder> dialectEntitySqlBuilder;
    private EntityDialectSqlBuildConfig<AnnotationEntitySqlBuildConfig> entityDialectSqlBuildConfig;
    private AnnotationEntityTemplateBuildConfig<AnnotationEntitySqlBuildConfig> entityTemplateBuildConfig;

    //
    private AnnotationEntitySqlBuilder builder;


    public boolean isOnlySelectUseStatus() {
        return onlySelectUseStatus;
    }

    public Class<? extends EntitySqlBuilder> getDialectEntitySqlBuilder() {
        return dialectEntitySqlBuilder;
    }

    public EntityDialectSqlBuildConfig<AnnotationEntitySqlBuildConfig> getDialectConfig() {
        return entityDialectSqlBuildConfig;
    }

    public AnnotationEntityTemplateBuildConfig<AnnotationEntitySqlBuildConfig> getTemplateConfig() {
        return entityTemplateBuildConfig;
    }




    /**
     * ConfigInOut Only Select Use Status
     * @param onlySelectUseStatus Only Select Use Status
     * @return This
     */
    public AnnotationEntitySqlBuildConfig withOnlySelectUseStatus(boolean onlySelectUseStatus) {
        this.onlySelectUseStatus = onlySelectUseStatus;
        return this;
    }

    /**
     * ConfigInOut EntitySqlBuilder
     * @param dialectEntitySqlBuilder EntitySqlBuilder
     * @return This
     */
    public AnnotationEntitySqlBuildConfig withDialectEntitySqlBuilder(Class<? extends EntitySqlBuilder> dialectEntitySqlBuilder) {
        this.dialectEntitySqlBuilder = dialectEntitySqlBuilder;
        return this;
    }

    /**
     * Set Sub config AnnotationEntityTemplateBuildConfig
     * @param entityDialectSqlBuildConfig AnnotationEntityTemplateBuildConfig with AnnotationEntitySqlBuildConfig
     * @return This
     */
    public AnnotationEntitySqlBuildConfig withDialectEntitySqlBuildConfig(EntityDialectSqlBuildConfig<AnnotationEntitySqlBuildConfig> entityDialectSqlBuildConfig) {
        this.entityDialectSqlBuildConfig = entityDialectSqlBuildConfig;
        return this;
    }

    /**
     * Set Sub config AnnotationEntityTemplateBuildConfig
     * @param entityTemplateBuildConfig AnnotationEntityTemplateBuildConfig with AnnotationEntitySqlBuildConfig
     * @return This
     */
    public AnnotationEntitySqlBuildConfig withEntityTemplateBuildConfig(AnnotationEntityTemplateBuildConfig<AnnotationEntitySqlBuildConfig> entityTemplateBuildConfig) {
        this.entityTemplateBuildConfig = entityTemplateBuildConfig;
        return this;
    }



    @Deprecated
    public EntityDialectSqlBuildConfig toEntitySqlBuilderConfig(){
        return new EntityDialectSqlBuildConfig()
                .withOnlySelectUseStatus(this.onlySelectUseStatus);
    }

    /**
     * Config EntityDialectSqlBuildConfig
     * @return EntityDialectSqlBuildConfig with This
     */
    public EntityDialectSqlBuildConfig<AnnotationEntitySqlBuildConfig> configDialect() {
        if(this.entityDialectSqlBuildConfig == null){
            this.entityDialectSqlBuildConfig = new EntityDialectSqlBuildConfig<>();
        }
        return this.entityDialectSqlBuildConfig.in(this);
    }

    /**
     * Config AnnotationEntityTemplateBuildConfig
     * @return AnnotationEntityTemplateBuildConfig with This
     */
    public AnnotationEntityTemplateBuildConfig<AnnotationEntitySqlBuildConfig> configTemplate() {
        if(this.entityTemplateBuildConfig == null){
            this.entityTemplateBuildConfig = new AnnotationEntityTemplateBuildConfig<>();
        }
        return this.entityTemplateBuildConfig.in(this);
    }


    @Override
    public AnnotationEntitySqlBuildConfig in(AnnotationEntitySqlBuilder annotationEntitySqlBuilder) {
        this.builder = annotationEntitySqlBuilder;
        return this;
    }

    @Override
    public AnnotationEntitySqlBuilder out() {
        return this.builder;
    }

    @SuppressWarnings("CloneDoesntCallSuperClone")
    @Override
    public AnnotationEntitySqlBuildConfig clone(){
        return new AnnotationEntitySqlBuildConfig()
                .withOnlySelectUseStatus(this.onlySelectUseStatus)
                .withDialectEntitySqlBuilder(this.dialectEntitySqlBuilder)
                .withEntityTemplateBuildConfig(this.entityTemplateBuildConfig.clone())
                .withDialectEntitySqlBuildConfig(this.entityDialectSqlBuildConfig.clone());
    }

}
