package com.xy.xsql.tsql.model.functions.trigger;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Trigger_NestLevel
        implements TriggerFunction, Function.InternalFunction {

    private Expression objectId;
    private Expression triggerType;
    private Expression triggerEventCategory;

    public Expression getObjectId() {
        return objectId;
    }

    public void setObjectId(Expression objectId) {
        this.objectId = objectId;
    }

    public Expression getTriggerType() {
        return triggerType;
    }

    public void setTriggerType(Expression triggerType) {
        this.triggerType = triggerType;
    }

    public Expression getTriggerEventCategory() {
        return triggerEventCategory;
    }

    public void setTriggerEventCategory(Expression triggerEventCategory) {
        this.triggerEventCategory = triggerEventCategory;
    }
}
