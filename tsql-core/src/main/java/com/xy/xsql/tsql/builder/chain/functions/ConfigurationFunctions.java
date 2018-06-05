package com.xy.xsql.tsql.builder.chain.functions;

import com.xy.xsql.tsql.model.functions.configuration.$$DbTs;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public interface ConfigurationFunctions {

    static $$DbTs f_dbts(){
        return new $$DbTs();
    }
    static $$DbTs f_langid(){
        return new $$DbTs();
    }
    static $$DbTs f_language(){
        return new $$DbTs();
    }
    static $$DbTs f_lock_timeout(){
        return new $$DbTs();
    }
    static $$DbTs f_max_connections(){
        return new $$DbTs();
    }
    static $$DbTs f_max_precision(){
        return new $$DbTs();
    }
    static $$DbTs f_nestlevel(){
        return new $$DbTs();
    }
    static $$DbTs f_options(){
        return new $$DbTs();
    }
    static $$DbTs f_servername(){
        return new $$DbTs();
    }
    static $$DbTs f_servicename(){
        return new $$DbTs();
    }
    static $$DbTs f_spid(){
        return new $$DbTs();
    }
    static $$DbTs f_textsize(){
        return new $$DbTs();
    }
    static $$DbTs f_version(){
        return new $$DbTs();
    }
}
