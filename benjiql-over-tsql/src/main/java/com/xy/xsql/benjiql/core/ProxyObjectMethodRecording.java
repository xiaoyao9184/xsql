package com.xy.xsql.benjiql.core;

import com.google.common.collect.ImmutableMap;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2017/7/27.
 */
public class ProxyObjectMethodRecording<T> implements MethodInterceptor {

    private T t;
    private ProxyObjectMethodRecording<?> currentMock = null;

    private Object o;
    private Method method;
    private Object[] os;
    private MethodProxy mp;

    @SuppressWarnings("unchecked")
    public static <T> ProxyObjectMethodRecording<T> create(Class<T> cls) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(cls);
        final ProxyObjectMethodRecording recordingObject = new ProxyObjectMethodRecording();
        enhancer.setCallback(recordingObject);
        recordingObject.t = (T) enhancer.create();

        return recordingObject;
    }

    public Object intercept(Object o, Method method, Object[] os, MethodProxy mp) throws Throwable {
//        if (method.getName().equals("getCurrentPropertyName")) {
//            return getCurrentPropertyName();
//        }
        if (method.getName().equals("toString")) {
            return null;
        }
//        currentPropertyName = Conventions.toDbName(method.getName());
//        try {
//            currentMock = create(method.getReturnType());
//            return currentMock.getObject();
//        } catch (IllegalArgumentException e) {
//            return DefaultValues.getDefault(method.getReturnType());
//        }
        this.o = o;
        this.method = method;
        this.os = os;
        this.mp = mp;

        return null;
//        return DefaultValues.getDefault(method.getReturnType());
    }

//    public String getCurrentPropertyName() {
//        return currentPropertyName + (currentMock == null ? "" : ("." + currentMock.getCurrentPropertyName()));
//    }

    public T getObject() {
        return t;
    }

    public Object getO() {
        return o;
    }

    public void setO(Object o) {
        this.o = o;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object[] getOs() {
        return os;
    }

    public void setOs(Object[] os) {
        this.os = os;
    }

    public MethodProxy getMp() {
        return mp;
    }

    public void setMp(MethodProxy mp) {
        this.mp = mp;
    }

    public ProxyObjectMethodRecording<?> getCurrentMock() {
        return currentMock;
    }

    public void setCurrentMock(ProxyObjectMethodRecording<?> currentMock) {
        this.currentMock = currentMock;
    }


    public static class DefaultValues {

        private static final Map<Class<?>, Object> defaultValues = ImmutableMap.<Class<?>,Object>builder()
                .put(String.class, "string")
                .put(Integer.class,0)
                .put(Float.class, 0f)
                .put(Double.class, 0d)
                .put(Long.class, 0L)
                .put(Character.class, 'c')
                .put(Byte.class, (byte)0)
                .put(int.class, 0)
                .put(float.class,0f)
                .put(double.class,0d)
                .put(long.class, 0L)
                .put(char.class, 'c')
                .put(byte.class, (byte)0)
                .build();

        @SuppressWarnings("unchecked")
        public static <T> T getDefault(Class<T> cls) {
            return (T) defaultValues.get(cls);
        }
    }
}
