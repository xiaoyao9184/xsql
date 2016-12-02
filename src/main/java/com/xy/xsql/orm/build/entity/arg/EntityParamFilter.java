package com.xy.xsql.orm.build.entity.arg;


import com.xy.xsql.orm.build.BaseBuilder;
import com.xy.xsql.orm.data.entity.EntityParam;
import com.xy.xsql.orm.util.CheckUtil;
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
     *
     * @param args
     * @return
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
                index++;
                result.add(param.clone());
            }else if((index+1) > args.length ||
                    CheckUtil.isNull(args[index])){
                //实际参数 未设置 忽略
                log.debug("索引超出参数大小，" + param.getColumn().getName() + " 字段的参数被忽略！");
                index++;
            }else{
                result.add(param.clone()
                        .withArgs(args[index]));
                index++;
            }
        }

        return result;
    }


}
