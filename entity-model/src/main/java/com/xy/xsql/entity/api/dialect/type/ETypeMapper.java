package com.xy.xsql.entity.api.dialect.type;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by xiaoyao9184 on 2017/9/23
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ETypeMapper {

    /**
     * @return Entity classe packages
     */
    String[] packages() default {};

    /**
     * @return Entity classes
     */
    Class<?>[] entities() default {};

    /**
     * @return Type mapper class
     */
    Class mapper() default Void.class;

}
