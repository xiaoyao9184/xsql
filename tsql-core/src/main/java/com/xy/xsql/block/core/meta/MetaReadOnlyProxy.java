package com.xy.xsql.block.core.meta;

import com.xy.xsql.block.model.BlockMeta;
import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by xiaoyao9184 on 2017/7/19.
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class MetaReadOnlyProxy implements MethodInterceptor {

    private BlockMeta meta;
    private boolean enable;

    public MetaReadOnlyProxy(BlockMeta meta){
        this.meta = meta;
        this.enable = false;
        this.init();
    }

    private void init(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(BlockMeta.class);
        enhancer.setCallback(this);
        BlockMeta proxy = (BlockMeta) enhancer.create();
        if(this.meta != null){
            BeanCopier.create(BlockMeta.class,BlockMeta.class,false)
                    .copy(meta,proxy,null);
        }
        this.meta = proxy;
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
        return this.meta;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        if(enable){
            if(Void.TYPE.equals(method.getReturnType())){
                throw new UnsupportedOperationException("BlockMeta not support the setter call after the build is complete!");
            }
        }

        return proxy.invokeSuper(obj,args);
    }


    /**
     * create MetaReadOnlyProxy
     * @param meta meta can be null
     * @return THIS
     */
    @SuppressWarnings("WeakerAccess")
    public static MetaReadOnlyProxy create(BlockMeta meta){
        return new MetaReadOnlyProxy(meta);
    }
}
