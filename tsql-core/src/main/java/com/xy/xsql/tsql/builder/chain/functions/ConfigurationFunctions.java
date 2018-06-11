package com.xy.xsql.tsql.builder.chain.functions;

import com.xy.xsql.tsql.model.functions.configuration.$$DbTs;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public interface ConfigurationFunctions {

    static $$DbTs f_$$dbts(){
        return new $$DbTs();
    }
    static $$DbTs f_$$langid(){
        return new $$DbTs();
    }
    static $$DbTs f_$$language(){
        return new $$DbTs();
    }
    static $$DbTs f_$$lock_timeout(){
        return new $$DbTs();
    }
    static $$DbTs f_$$max_connections(){
        return new $$DbTs();
    }
    static $$DbTs f_$$max_precision(){
        return new $$DbTs();
    }
    static $$DbTs f_$$nestlevel(){
        return new $$DbTs();
    }
    static $$DbTs f_$$options(){
        return new $$DbTs();
    }
    static $$DbTs f_$$servername(){
        return new $$DbTs();
    }
    static $$DbTs f_$$servicename(){
        return new $$DbTs();
    }
    static $$DbTs f_$$spid(){
        return new $$DbTs();
    }
    static $$DbTs f_$$textsize(){
        return new $$DbTs();
    }
    static $$DbTs f_$$version(){
        return new $$DbTs();
    }
}
