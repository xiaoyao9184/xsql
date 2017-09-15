package com.xy.xsql.model;

/**
 * Created by xiaoyao9184 on 2017/9/14.
 */
public class ModelParamHolder<M,P> {

    private M model;
    private P param;

    public ModelParamHolder() {
    }

    public ModelParamHolder(M model, P param) {
        this.model = model;
        this.param = param;
    }

    public M getModel() {
        return model;
    }

    public void setModel(M model) {
        this.model = model;
    }

    public P getParam() {
        return param;
    }

    public void setParam(P param) {
        this.param = param;
    }
}
