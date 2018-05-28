package com.xy.xsql.tsql.style.constructor.builder.elements.operators;

import com.xy.xsql.core.builder.SimpleBuilder;
import com.xy.xsql.tsql.model.elements.operators.Scope_Resolution;

/**
 * Created by xiaoyao9184 on 2015/5/25.
 */
public interface b2scope
        extends SimpleBuilder<Scope_Resolution> {

    b2scope $$scope(String member);

}