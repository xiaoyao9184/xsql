package com.xy.xsql.entity.core.template;

import com.xy.xsql.core.builder.BaseBuilder;
import com.xy.xsql.entity.core.util.CheckUtil;
import com.xy.xsql.entity.model.template.EntityParam;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/11/25.
 */
public class EntityParamFilter implements BaseBuilder<List<EntityParam>,List<EntityParam>> {

    protected static final Log log = LogFactory.getLog(EntityParamFilter.class);
    private Object[] args;

    /**
     * Set Args
     * @param args Args
     * @return This
     */
    public EntityParamFilter withArgs(Object... args) {
        this.args = args;
        return this;
    }

    @Override
    public List<EntityParam> build(List<EntityParam> paramList) {
        List<EntityParam> result = new ArrayList<>();
        int index = 0;
        for (EntityParam param: paramList) {
            if (!param.isNeedValue()) {
                result.add(param.clone());
            }else if(CheckUtil.isNull(args, index)){
                //arg not set, ignore param
                log.debug("index of args is null，column " + param.getColumn().getName() + " 's param will ignore!");
                index++;
            }else{
                if(args[index].getClass().isArray()){
                    Object[] argArray = (Object[])args[index];
                    if(argArray.length != 0){
                        result.add(param.clone()
                                .withArgs(argArray));
                    }
                }else{
                    result.add(param.clone()
                            .withArg(args[index]));
                }
                index++;
            }
        }
        return result;
    }

}
