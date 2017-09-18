package com.xy.xsql.model.param;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2017/9/15.
 */
public class ListParameterModel<M>
        extends ParameterModel<M,List<Object>> {

    public ListParameterModel(M model, List<Object> param){
        super(model,param);
    }
}
