package com.xy.xsql.tsql.builder.chain.functions;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.system.*;

import java.util.Arrays;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public interface SystemFunctions {

    static $$Error f_$$error(){
        return new $$Error();
    }
    static $$Identity f_$$identity(){
        return new $$Identity();
    }
    static $$Packet_Received f_$$packet_received(){
        return new $$Packet_Received();
    }
    static $$RowCount f_$$rowcount(){
        return new $$RowCount();
    }
    static $$TranCount f_$$trancount(){
        return new $$TranCount();
    }
    static Binary_Checksum f_binary_checksum(Expression... expressions){
        Binary_Checksum f = new Binary_Checksum();
        f.setExpressionList(Arrays.asList(expressions));
        return f;
    }
    static Checksum f_checksum(Expression... expressions){
        Checksum f = new Checksum();
        f.setExpressionList(Arrays.asList(expressions));
        return f;
    }
    static Compress f_compress(Expression expression){
        Compress f = new Compress();
        f.setExpression(expression);
        return f;
    }
    static ConnectionProperty f_connectionproperty(Expression expression){
        ConnectionProperty f = new ConnectionProperty();
        f.setProperty(expression);
        return f;
    }
    static Context_Info f_context_info(){
        return new Context_Info();
    }
    static Current_Request_Id f_current_request_id(){
        return new Current_Request_Id();
    }
    static Current_Transaction_Id f_current_transaction_id(){
        return new Current_Transaction_Id();
    }
    static DeCompress f_decompress(Expression expression){
        DeCompress f = new DeCompress();
        f.setExpression(expression);
        return f;
    }
    static Error_Line f_error_line(){
        return new Error_Line();
    }
    static Error_Message f_error_message(){
        return new Error_Message();
    }
    static Error_Number f_error_number(){
        return new Error_Number();
    }
    static Error_Procedure f_error_procedure(){
        return new Error_Procedure();
    }
    static Error_Severity f_error_severity(){
        return new Error_Severity();
    }
    static Error_State f_error_state(){
        return new Error_State();
    }

    static FormatMessage f_formatmessage(Expression msg_number, Expression... param_value){
        FormatMessage f = new FormatMessage();
        f.setMsgMumber(msg_number);
        f.setParamValueList(Arrays.asList(param_value));
        return f;
    }
    static FormatMessage f_formatmessage(StringConstant msg_string, Expression... param_value){
        FormatMessage f = new FormatMessage();
        f.setMsgString(msg_string);
        f.setParamValueList(Arrays.asList(param_value));
        return f;
    }

    static Get_Filestream_Transaction_Context f_get_filestream_transaction_context(){
        return new Get_Filestream_Transaction_Context();
    }

    static Getansinull f_getansinull(){
        Getansinull f = new Getansinull();
        return f;
    }
    static Getansinull f_getansinull(StringConstant database){
        Getansinull f = new Getansinull();
        f.setDatabase(database);
        return f;
    }

    static Host_Id f_host_id(){
        return new Host_Id();
    }
    static Host_Name f_host_name(){
        return new Host_Name();
    }
    static IsNull f_isnull(Expression check_expression, Expression replacement_value){
        IsNull f = new IsNull();
        f.setCheckExpression(check_expression);
        f.setReplacementValue(replacement_value);
        return f;
    }
    static IsNumeric f_isnumeric(Expression expression){
        IsNumeric f = new IsNumeric();
        f.setExpression(expression);
        return f;
    }

    static Min_Active_Rowversion f_min_active_rowversion(){
        return new Min_Active_Rowversion();
    }

    static NewId f_newid(){
        return new NewId();
    }
    static NewSequentialId f_newsequentialid(){
        return new NewSequentialId();
    }
    static RowCount_Big f_rowcount_big(){
        return new RowCount_Big();
    }
    static Session_Context f_session_context(StringConstant key){
        Session_Context f = new Session_Context();
        f.setKey(key);
        return f;
    }
    static Xact_State f_xact_state(){
        return new Xact_State();
    }
}
