package com.xy.xsql.tsql.builder.chain.functions;

import com.xy.xsql.tsql.model.functions.systemstatistical.*;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public interface SystemStatisticalFunctions {

    static $$Connections f_$$connections(){
        return new $$Connections();
    }
    static $$Cpu_Busy f_$$cpu_busy(){
        return new $$Cpu_Busy();
    }
    static $$Idle f_$$idle(){
        return new $$Idle();
    }
    static $$Io_Busy f_$$io_busy(){
        return new $$Io_Busy();
    }
    static $$Pack_Sent f_$$pack_sent(){
        return new $$Pack_Sent();
    }
    static $$Packet_Errors f_$$packet_errors(){
        return new $$Packet_Errors();
    }
    static $$TimeTicks f_$$timeticks(){
        return new $$TimeTicks();
    }
    static $$Total_Errors f_$$total_errors(){
        return new $$Total_Errors();
    }
    static $$Total_Read f_$$total_read(){
        return new $$Total_Read();
    }
    static $$Total_Write f_$$total_write(){
        return new $$Total_Write();
    }
    static fn_virtualfilestats fn_virtualfilestats(){
        return new fn_virtualfilestats();
    }
}
