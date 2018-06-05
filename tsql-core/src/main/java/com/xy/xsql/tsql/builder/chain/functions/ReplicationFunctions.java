package com.xy.xsql.tsql.builder.chain.functions;

import com.xy.xsql.tsql.model.functions.replication.PublishingServerName;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public interface ReplicationFunctions {


    static PublishingServerName f_publishingservername(){
        return new PublishingServerName();
    }

}
