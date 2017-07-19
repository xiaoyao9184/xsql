package com.xy.xsql.block.core.meta;

import com.xy.xsql.block.model.BlockMeta;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by xiaoyao9184 on 2017/7/19.
 */
public class MetaReadOnlyProxy implements MethodInterceptor {

    private BlockMeta meta;
    private boolean enable;

    public MetaReadOnlyProxy(BlockMeta meta){
        this.meta = meta;
        this.enable = false;
    }

    /**
     * enable interception setter
     */
    public void enable(){
        this.enable = true;
    }

    /**
     * disable interception setter[default]
     */
    public void disable(){
        this.enable = false;
    }

    /**
     * get proxy meta
     * @return proxy meta
     */
    public BlockMeta meta(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(BlockMeta.class);
        enhancer.setCallback(this);
        return (BlockMeta) enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        if(enable){
            if(Void.TYPE.equals(method.getReturnType())){
                throw new UnsupportedOperationException("BlockMeta not support the setter call after the build is complete!");
            }
        }

        return method.invoke(meta,args);
    }


    /**
     * create MetaReadOnlyProxy
     * @param meta meta can be null
     * @return THIS
     */
    public static MetaReadOnlyProxy create(BlockMeta meta){
        if(meta == null){
            meta = new BlockMeta();
        }
        return new MetaReadOnlyProxy(meta);
    }
}
