package com.xy.xsql.tsql.builder.chain.functions;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.datatypes.table.TableName;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.rowset.*;
import com.xy.xsql.tsql.model.statements.BulkInsert;

import java.util.Arrays;
import java.util.List;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public interface RowsetFunctions {


    static OpenDataSource f_opendatasource(
            Expression providerName,
            String initString){
        OpenDataSource f = new OpenDataSource();
        f.setProviderName(providerName);
        f.setInitString(initString);
        return f;
    }
    static OpenJson f_openjson(
            Expression jsonExpression,
            String path,
            List<OpenJson.Item> with){
        OpenJson f = new OpenJson();
        f.setJsonExpression(jsonExpression);
        f.setPath(path);
        f.setWith(with);
        return f;
    }
    static OpenJson f_openjson(
            Expression jsonExpression,
            String path){
        OpenJson f = new OpenJson();
        f.setJsonExpression(jsonExpression);
        f.setPath(path);
        return f;
    }
    static OpenJson f_openjson(
            Expression jsonExpression,
            List<OpenJson.Item> with){
        OpenJson f = new OpenJson();
        f.setJsonExpression(jsonExpression);
        f.setWith(with);
        return f;
    }
    static OpenJson f_openjson(
            Expression jsonExpression){
        OpenJson f = new OpenJson();
        f.setJsonExpression(jsonExpression);
        return f;
    }
    static OpenQuery f_openquery(
            String linkedServer,
            StringConstant query){
        OpenQuery f = new OpenQuery();
        f.setLinkedServer(linkedServer);
        f.setQuery(query);
        return f;
    }

    static OpenRowSet f_openrowset(
            StringConstant providerName,
            StringConstant providerString,
            StringConstant query){
        OpenRowSet f = new OpenRowSet();
        f.setProviderName(providerName);
        f.setProvider_string(providerString);
        f.setQuery(query);
        return f;
    }
    static OpenRowSet f_openrowset(
            StringConstant providerName,
            StringConstant providerString,
            String... object){
        OpenRowSet f = new OpenRowSet();
        f.setProviderName(providerName);
        f.setProvider_string(providerString);
        f.setObject(Arrays.asList(object));
        return f;
    }
    static OpenRowSet f_openrowset(
            StringConstant providerName,
            String datasource,
            String user_id,
            String password,
            StringConstant query){
        OpenRowSet f = new OpenRowSet();
        f.setProviderName(providerName);
        f.setDatasource(c_string(datasource));
        f.setUserId(c_string(user_id));
        f.setPassword(c_string(password));
        f.setQuery(query);
        return f;
    }
    static OpenRowSet f_openrowset(
            StringConstant providerName,
            String datasource,
            String user_id,
            String password,
            String... object){
        OpenRowSet f = new OpenRowSet();
        f.setProviderName(providerName);
        f.setDatasource(c_string(datasource));
        f.setUserId(c_string(user_id));
        f.setPassword(c_string(password));
        f.setObject(Arrays.asList(object));
        return f;
    }
    static OpenRowSet f_openrowset(
            BulkInsert bulkInsert,
            OpenRowSet.Single single){
        OpenRowSet f = new OpenRowSet();
        f.setBulkInsert(bulkInsert);
        f.setSingle(single);
        return f;
    }
    static OpenRowSet f_openrowset(
            BulkInsert bulkInsert){
        OpenRowSet f = new OpenRowSet();
        f.setBulkInsert(bulkInsert);
        return f;
    }

    static OpenXml f_openxml(
            Expression idoc,
            String rowPattern,
            OpenXml.Flags flags,
            OpenXml.SchemaDeclaration schemaDeclaration){
        OpenXml f = new OpenXml();
        f.setIdoc(idoc);
        f.setRowPattern(rowPattern);
        f.setFlags(flags);
        f.setSchemaDeclaration(schemaDeclaration);
        return f;
    }
    static OpenXml f_openxml(
            Expression idoc,
            String rowPattern,
            OpenXml.Flags flags,
            TableName tableName){
        OpenXml f = new OpenXml();
        f.setIdoc(idoc);
        f.setRowPattern(rowPattern);
        f.setFlags(flags);
        f.setTableName(tableName);
        return f;
    }
    static OpenXml f_openxml(
            Expression idoc,
            String rowPattern,
            OpenXml.Flags flags){
        OpenXml f = new OpenXml();
        f.setIdoc(idoc);
        f.setRowPattern(rowPattern);
        f.setFlags(flags);
        return f;
    }
    static OpenXml f_openxml(
            Expression idoc,
            String rowPattern){
        OpenXml f = new OpenXml();
        f.setIdoc(idoc);
        f.setRowPattern(rowPattern);
        return f;
    }

}
