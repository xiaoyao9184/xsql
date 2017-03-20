package com.xy.xsql.orm.core.sentence.sql;

import com.xy.xsql.core.builder.BaseBuilder;
import com.xy.xsql.orm.core.Regulator;
import com.xy.xsql.orm.data.config.SentenceSqlBuilderConfig;
import com.xy.xsql.orm.data.sql.sentence.BaseElementsSentence;
import com.xy.xsql.orm.dialect.none.AllVarCharTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * //TODO 应该是 CodeSentenceSqlBuilder 处理 Code -> SqlString
 * ElementsSentenceSqlBuilder
 * core Sql String by BaseElementsSentence
 * Created by xiaoyao9184 on 2016/10/15.
 */
public class ElementsSentenceSqlBuilder implements BaseBuilder<BaseElementsSentence,String> {

    private Logger log;

    protected SentenceSqlBuilderConfig config;

    protected List<Regulator> regulatorList;

    protected BaseElementsSentence elementsSentence;

    protected DialectSentenceSqlBuilder dialectSentenceSqlBuilder;


    public ElementsSentenceSqlBuilder(){
        this.log = LoggerFactory.getLogger(this.getClass());
    }

    /**
     * config
     * @param config SentenceSqlBuilderConfig
     * @return This
     */
    public ElementsSentenceSqlBuilder config(SentenceSqlBuilderConfig config){
        if(config == null){
            this.config = new SentenceSqlBuilderConfig()
                    .typeMapper(AllVarCharTypeMapper.class)
                    .dialectSqlBuilder(BaseSentenceSqlBuilder.class);
        }else{
            this.config = config;
        }

        //create DialectSentenceSqlBuilder
        Class dialectSentenceSqlBuilderClass = config.getDialectSqlBuilder();

        if(dialectSentenceSqlBuilderClass != null){
            try {
                this.dialectSentenceSqlBuilder = (DialectSentenceSqlBuilder) dialectSentenceSqlBuilderClass.newInstance();
            } catch (Exception e) {
                log.error("Cant create Dialect DialectSentenceSqlBuilder, create default.",e);
            }
        }
        if(dialectSentenceSqlBuilderClass == null){
            this.dialectSentenceSqlBuilder = new BaseSentenceSqlBuilder();
        }

        return this;
    }

    /**
     * core
     * @param elementsSentence EntityTemplate
     * @return This
     */
    public String build(BaseElementsSentence elementsSentence){
        if(this.config == null){
            this.config(new SentenceSqlBuilderConfig());
        }

        if(this.elementsSentence == null ||
                !this.elementsSentence.equals(elementsSentence)){
            this.elementsSentence = elementsSentence;
        }

        //调整补全
        for (Regulator regulator: this.regulatorList) {
            regulator.change(this.elementsSentence);
        }

        if(!dialectSentenceSqlBuilder.isSupport(this.elementsSentence)){
            this.log.info("not support this sentence");
            return "";
        }

        return dialectSentenceSqlBuilder.build(this.elementsSentence);
    }

}
