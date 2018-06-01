package com.xy.xsql.tsql.model.functions.metadata;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.security.SecurityFunction;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class AssemblyProperty
        implements MetaDataFunction, Function.InternalFunction {

    private StringConstant assemblyName;
    private StringConstant propertyName;

    public StringConstant getAssemblyName() {
        return assemblyName;
    }

    public void setAssemblyName(StringConstant assemblyName) {
        this.assemblyName = assemblyName;
    }

    public StringConstant getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(StringConstant propertyName) {
        this.propertyName = propertyName;
    }
}
