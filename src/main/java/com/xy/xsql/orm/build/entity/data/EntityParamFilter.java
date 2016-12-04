package com.xy.xsql.orm.build.entity.data;

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
                log.debug("index of args is null，column " + param.getColumn().getFullName() + " 's param will ignore!");
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
