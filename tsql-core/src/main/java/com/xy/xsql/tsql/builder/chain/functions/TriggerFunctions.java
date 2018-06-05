package com.xy.xsql.tsql.builder.chain.functions;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.trigger.Columns_Updated;
import com.xy.xsql.tsql.model.functions.trigger.EventData;
import com.xy.xsql.tsql.model.functions.trigger.Trigger_NestLevel;
import com.xy.xsql.tsql.model.functions.trigger.Update;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public interface TriggerFunctions {

    static Columns_Updated f_$columns_updated(){
        return new Columns_Updated();
    }
    static EventData f_$eventdata(){
        return new EventData();
    }
    static Trigger_NestLevel f_$trigger_nestlevel(
            Expression objectId,
            Expression triggerType,
            Expression triggerEventCategory){
        Trigger_NestLevel f = new Trigger_NestLevel();
        f.setObjectId(objectId);
        f.setTriggerType(triggerType);
        f.setTriggerEventCategory(triggerEventCategory);
        return f;
    }
    static Trigger_NestLevel f_$trigger_nestlevel(){
        Trigger_NestLevel f = new Trigger_NestLevel();
        return f;
    }
    static Update f_$update(){
        return new Update();
    }
}
