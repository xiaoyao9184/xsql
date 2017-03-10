package com.xy.xsql.orm.core.entity.sql;

import com.xy.xsql.orm.annotation.EStatus;
import com.xy.xsql.orm.core.sentence.data.CodeSentenceDataBuilder;
import com.xy.xsql.orm.core.sentence.sql.ElementsSentenceSqlBuilder;
import com.xy.xsql.orm.data.config.EntityDialectSqlBuildConfig;
import com.xy.xsql.orm.data.entity.EntityColumn;
import com.xy.xsql.orm.data.sql.element.OperatorEnum;
import com.xy.xsql.orm.data.sql.element.info.Column;
import com.xy.xsql.orm.data.sql.element.info.Value;
import com.xy.xsql.orm.data.sql.sentence.BaseElementsSentence;

import java.util.ArrayList;
import java.util.List;

/**
 * Default EntitySqlBuilder
 * it is T-SQL prototype
 * Created by xiaoyao9184 on 2016/1/13.
 */
@SuppressWarnings({"StringBufferReplaceableByString", "JavaDoc", "unused", "StatementWithEmptyBody", "WeakerAccess"})
public class SentenceEntitySqlBuilder extends StringEntitySqlBuilder {

/*
 * Fields
 */

    protected ElementsSentenceSqlBuilder elementsSentenceSqlBuilder;

    public SentenceEntitySqlBuilder(){
        super();
    }


/*
 * Cache
 */

    @Override
    public SentenceEntitySqlBuilder cacheConfig(EntityDialectSqlBuildConfig config) {
        this.config = config;

        if(this.config.isUseSentenceBuilder()){
            this.elementsSentenceSqlBuilder = new ElementsSentenceSqlBuilder();
        }
        return this;
    }

/*
 * Create SQL
 */



    /**
     * one table insert
     * @return SQL append (+)? , + is 1 or more , is columns count
     */
    @Override
    public String sqlInsert(){
        //TODO use CodeSentenceDataBuilder
        if(this.config.isUseSentenceBuilder()){
            List<Column> list = new ArrayList<>();
            list.addAll(this.template.getColumns());

            BaseElementsSentence elementsSentence = new CodeSentenceDataBuilder()
                    .insert(this.template.getTable())
                    .values(list)
                    .build(null);

            return this.elementsSentenceSqlBuilder.build(elementsSentence);
        }else{
            return super.sqlInsert();
        }
    }

    /**
     * one table all columns update
     * @return SQL append (+)? , + is 1 or more , is columns+keys count
     */
    public String sqlUpdateById(){
        List<Column> list = new ArrayList<>();
        list.addAll(this.template.getColumns());

        CodeSentenceDataBuilder builder = new CodeSentenceDataBuilder()
                .update(this.template.getTable())
                .set(list)
                .where(this.template.getKeys().get(0), OperatorEnum.EQUAL);

        for(int i = 1; i < this.template.getKeys().size(); i++){
            builder.and(this.template.getKeys().get(i), OperatorEnum.EQUAL);
        }

        BaseElementsSentence elementsSentence = builder.build(null);

        return this.elementsSentenceSqlBuilder.build(elementsSentence);
    }

    /**
     * one table select
     * @return SQL append (+)? , + is 1 or more , is keys count
     */
    public String sqlSelectById(){
        List<Column> list = new ArrayList<>();
        list.addAll(this.template.getColumns());

        CodeSentenceDataBuilder builder = new CodeSentenceDataBuilder()
                .select(list)
                .from(this.template.getTable())
                .where(this.template.getKeys().get(0), OperatorEnum.EQUAL);

        for(int i = 1; i < this.template.getKeys().size(); i++){
            builder.and(this.template.getKeys().get(i), OperatorEnum.EQUAL);
        }

        BaseElementsSentence elementsSentence = builder.build(null);

        return this.elementsSentenceSqlBuilder.build(elementsSentence);
    }

    /**
     * one table delete
     * @return SQL append (+)? , + is 1 or more , is keys count
     */
    public String sqlDeleteById(){
        CodeSentenceDataBuilder builder = new CodeSentenceDataBuilder()
                .delete()
                .from(this.template.getTable())
                .where(this.template.getKeys().get(0), OperatorEnum.EQUAL);

        for(int i = 1; i < this.template.getKeys().size(); i++){
            builder.and(this.template.getKeys().get(i), OperatorEnum.EQUAL);
        }

        BaseElementsSentence elementsSentence = builder.build(null);

        return this.elementsSentenceSqlBuilder.build(elementsSentence);
    }

    /**
     * one table status column update
     * @return SQL append (+)? , + is 1 or more , is keys count
     */
    public String sqlUpdateStatusById(){
        if(this.template.getStatus() == null){
            throw new UnsupportedOperationException("没有任何字段被标注为@" + EStatus.class.getSimpleName());
        }

        CodeSentenceDataBuilder builder = new CodeSentenceDataBuilder()
                .update(this.template.getTable())
                .set(this.template.getStatus())
                .where(this.template.getKeys().get(0), OperatorEnum.EQUAL);

        for(int i = 1; i < this.template.getKeys().size(); i++){
            builder.and(this.template.getKeys().get(i), OperatorEnum.EQUAL);
        }

        BaseElementsSentence elementsSentence = builder.build(null);

        return this.elementsSentenceSqlBuilder.build(elementsSentence);
    }

    /**
     * one table more row insert
     * @param entityCount row count
     * @return SQL append (count)*(+)? , + is 1 or more , is columns count
     */
    public String sqlInsert(int entityCount){
        List<Column> list = new ArrayList<>();
        list.addAll(this.template.getColumns());

        BaseElementsSentence elementsSentence = new CodeSentenceDataBuilder()
                .insert(this.template.getTable())
                .values(list, entityCount)
                .build(null);

        return this.elementsSentenceSqlBuilder.build(elementsSentence);
    }

    /**
     * one table more row status column update
     * @param count row count
     * @return SQL append (count)?
     */
    public String sqlUpdate(int count){
        if(this.template.getKeys().size() > 1){
            throw new UnsupportedOperationException("联合主键不提供批量更新操作！");
        }
        if(count < 1){
            count = 1;
        }

        List<Column> list = new ArrayList<>();
        list.addAll(this.template.getColumns());

        List<Value> valueList = new ArrayList<>();
        for (EntityColumn entityColumn : this.template.getColumns()) {
            BaseElementsSentence elementsSentence = new CodeSentenceDataBuilder()
                    .caseStart(this.template.getKeys().get(0).getName())
                    .whenThen(count)
                    .caseEnd()
                    .build(null);
            valueList.add(new Value(elementsSentence));
        }

        BaseElementsSentence elementsSentence = new CodeSentenceDataBuilder()
                .update(this.template.getTable().getName())
                .set(list,valueList)
                .where()
                .in(this.template.getKeys().get(0),count)
                .build(null);

        return this.elementsSentenceSqlBuilder.build(elementsSentence);
    }


    /**
     * one table more row select
     * @return SQL append (?)? , ? is 1 or 0 , if status column used and config it on , it will 1
     */
    public String sqlSelectByStatus(){
        List<Column> list = new ArrayList<>();
        list.addAll(this.template.getColumns());

        CodeSentenceDataBuilder builder = new CodeSentenceDataBuilder()
                .select(list)
                .from(this.template.getTable());

        if(config.isOnlySelectUseStatus()){
            builder.where(this.template.getStatus(), OperatorEnum.EQUAL);
        }

        BaseElementsSentence elementsSentence = builder.build(null);

        return this.elementsSentenceSqlBuilder.build(elementsSentence);
    }

    /**
     * one table more row count select
     * @return SQL append (?)? , ? is 1 or 0 , if status column used and config it on , it will 1
     */
    public String sqlSelectCount(){
        CodeSentenceDataBuilder builder = new CodeSentenceDataBuilder()
                .select()
                .funCount()
                .from(this.template.getTable());

        if(config.isOnlySelectUseStatus()){
            builder.where(this.template.getStatus(), OperatorEnum.EQUAL);
        }

        BaseElementsSentence elementsSentence = builder.build(null);

        return this.elementsSentenceSqlBuilder.build(elementsSentence);
    }

    /**
     * one table more row count delete
     * @param count row count
     * @return SQL append (count)?
     */
    public String sqlDeleteByIds(int count){
        if(this.template.getKeys().size() > 1){
            throw new UnsupportedOperationException("联合主键不提供批量删除操作！");
        }
        if(count < 1){
            count = 1;
        }

        BaseElementsSentence elementsSentence = new CodeSentenceDataBuilder()
                .delete()
                .from(this.template.getTable())
                .where()
                .in(this.template.getKeys().get(0),count)
                .build(null);

        return this.elementsSentenceSqlBuilder.build(elementsSentence);
    }

    /**
     * one table more row status column update
     * @param count row count
     * @return SQL append (count)?
     */
    public String sqlUpdateStatusByIds(int count){
        if(this.template.getStatus() == null){
            throw new UnsupportedOperationException("没有任何字段被标注为@" + EStatus.class.getSimpleName());
        }
        if(this.template.getKeys().size() > 1){
            throw new UnsupportedOperationException("联合主键不提供批量更新状态操作！");
        }
        if(count < 1){
            count = 1;
        }

        BaseElementsSentence elementsSentence = new CodeSentenceDataBuilder()
                .update(this.template.getTable())
                .set(this.template.getStatus())
                .where()
                .in(this.template.getKeys().get(0),count)
                .build(null);

        return this.elementsSentenceSqlBuilder.build(elementsSentence);
    }

}
