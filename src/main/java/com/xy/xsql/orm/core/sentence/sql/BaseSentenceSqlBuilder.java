package com.xy.xsql.orm.core.sentence.sql;

import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;
import com.xy.xsql.orm.data.sql.element.info.Column;
import com.xy.xsql.orm.data.sql.element.info.Name;
import com.xy.xsql.orm.data.sql.element.info.Table;
import com.xy.xsql.orm.data.sql.sentence.BaseElementsSentence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Default Sentence Sql Builder
 * no dialect, just append it convert to string
 * Created by xiaoyao9184 on 2016/10/15.
 */
public class BaseSentenceSqlBuilder implements DialectSentenceSqlBuilder {

    private Logger log;

    public BaseSentenceSqlBuilder(){
        this.log = LoggerFactory.getLogger(this.getClass());
    }

    @Override
    public String build(BaseElementsSentence elementsSentence) {
        StringBuilder stringBuilder = new StringBuilder();
        int index = 0;
        int next = index + 1;
        for (Element e : elementsSentence.getData()) {
            if(e instanceof Name){
                log.debug("Element " + index + " is Name");
                Name name = (Name)e;
                stringBuilder.append(name.getName());
            }else{
                stringBuilder.append(e.toString());
            }

            //(
            if(e instanceof Table &&
                    elementsSentence.getData().get(index+1) instanceof Column){
                log.debug("Element " + index + " is Table next is Column");
                stringBuilder.append(" (");
            }
            //)
            if(e instanceof Column &&
                    GrammarEnum.VALUES.equals(elementsSentence.getData().get(index+1))){
                log.debug("Element " + index + " is Column next is Values");
                stringBuilder.append(") ");
            }

            //,
            if(index == elementsSentence.getData().size()-1){
                log.debug("Element " + index + " is end");
            }else if(next <= elementsSentence.getData().size()-1 &&
                    e.getClass().equals(elementsSentence.getData().get(index+1).getClass())){
                log.debug("Element " + index + " same as "+ (index+1));
                stringBuilder.append(",");
            }else{
                stringBuilder.append(" ");
            }
            index++;
            next = index + 1;
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean isSupport(BaseElementsSentence elementsSentence) {
        return true;
    }

}
