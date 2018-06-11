package com.xy.xsql.tsql.builder.chain.functions;

import com.xy.xsql.tsql.model.datatypes.DataType;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.datatypes.table.TableName;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.metadata.*;
import com.xy.xsql.tsql.model.queries.select.Over;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public interface MetaDataFunctions {


    static $$ProcId f_$$procid(){
        return new $$ProcId();
    }
    static App_Name f_app_name(){
        return new App_Name();
    }
    static AppLock_Mode f_applock_mode(
            StringConstant databasePrincipal,
            StringConstant resourceName,
            StringConstant lockOwner){
        AppLock_Mode f = new AppLock_Mode();
        f.setDatabasePrincipal(databasePrincipal);
        f.setResourceName(resourceName);
        f.setLockOwner(lockOwner);
        return f;
    }
    static AppLock_Test f_applock_test(
            StringConstant databasePrincipal,
            StringConstant resourceName,
            StringConstant lockMode,
            StringConstant lockOwner){
        AppLock_Test f = new AppLock_Test();
        f.setDatabasePrincipal(databasePrincipal);
        f.setResourceName(resourceName);
        f.setLockMode(lockMode);
        f.setLockOwner(lockOwner);
        return f;
    }
    static AssemblyProperty f_assemblyproperty(
            StringConstant assemblyName,
            StringConstant propertyName){
        AssemblyProperty f = new AssemblyProperty();
        f.setAssemblyName(assemblyName);
        f.setPropertyName(propertyName);
        return f;
    }
    static Col_Length f_col_length(
            StringConstant table,
            StringConstant column){
        Col_Length f = new Col_Length();
        f.setTable(table);
        f.setColumn(column);
        return f;
    }
    static Col_Name f_col_name(
            Expression tableId,
            Expression columnId){
        Col_Name f = new Col_Name();
        f.setTableId(tableId);
        f.setColumnId(columnId);
        return f;
    }
    static ColumnProperty f_columnproperty(
            Expression id,
            Expression column,
            Expression property){
        ColumnProperty f = new ColumnProperty();
        f.setId(id);
        f.setColumn(column);
        f.setProperty(property);
        return f;
    }
    static Database_Principal_Id f_database_principal_id(
            StringConstant principalName){
        Database_Principal_Id f = new Database_Principal_Id();
        f.setPrincipalName(principalName);
        return f;
    }
    static DatabasePropertyEX f_databasepropertyex(
            Expression database,
            Expression property){
        DatabasePropertyEX f = new DatabasePropertyEX();
        f.setDatabase(database);
        f.setProperty(property);
        return f;
    }
    static Db_Id f_db_id(
            Expression databaseName){
        Db_Id f = new Db_Id();
        f.setDatabaseName(databaseName);
        return f;
    }
    static Db_Id f_db_id(){
        Db_Id f = new Db_Id();
        return f;
    }
    static Db_Name f_db_name(
            Expression databaseId){
        Db_Name f = new Db_Name();
        f.setDatabaseId(databaseId);
        return f;
    }
    static Db_Name f_db_name(){
        Db_Name f = new Db_Name();
        return f;
    }
    static File_Id f_file_id(
            Expression fileName){
        File_Id f = new File_Id();
        f.setFileName(fileName);
        return f;
    }
    static File_IdEx f_file_idex(
            Expression fileName){
        File_IdEx f = new File_IdEx();
        f.setFileName(fileName);
        return f;
    }
    static File_Name f_file_name(
            Expression fileId){
        File_Name f = new File_Name();
        f.setFileId(fileId);
        return f;
    }
    static FileGroup_Id f_filegroup_id(
            StringConstant filegroupName){
        FileGroup_Id f = new FileGroup_Id();
        f.setFilegroupName(filegroupName);
        return f;
    }
    static FileGroup_Name f_filegroup_name(
            Expression filegroupId){
        FileGroup_Name f = new FileGroup_Name();
        f.setFilegroupId(filegroupId);
        return f;
    }
    static FileGroupProperty f_filegroupproperty(
            StringConstant filegroupName,
            Expression property){
        FileGroupProperty f = new FileGroupProperty();
        f.setFilegroupName(filegroupName);
        f.setProperty(property);
        return f;
    }
    static FileProperty f_fileproperty(
            StringConstant fileName,
            Expression property){
        FileProperty f = new FileProperty();
        f.setFileName(fileName);
        f.setProperty(property);
        return f;
    }
    static FullTextCatalogProperty f_fulltextcatalogproperty(
            StringConstant catalogName,
            Expression property){
        FullTextCatalogProperty f = new FullTextCatalogProperty();
        f.setCatalogName(catalogName);
        f.setProperty(property);
        return f;
    }
    static FullTextServiceProperty f_fulltextserviceproperty(
            Expression property){
        FullTextServiceProperty f = new FullTextServiceProperty();
        f.setProperty(property);
        return f;
    }
    static Index_Col f_index_col(
            TableName tableName,
            Expression indexId,
            Expression keyId){
        Index_Col f = new Index_Col();
        f.setTableName(tableName);
        f.setIndexId(indexId);
        f.setKeyId(keyId);
        return f;
    }
    static IndexKey_Property f_indexkey_property(
            Expression objectId,
            Expression indexId,
            Expression keyId,
            Expression property){
        IndexKey_Property f = new IndexKey_Property();
        f.setObjectId(objectId);
        f.setIndexId(indexId);
        f.setKeyId(keyId);
        f.setProperty(property);
        return f;
    }
    static IndexProperty f_indexproperty(
            Expression objectId,
            Expression indexOrStatisticsName,
            Expression property){
        IndexProperty f = new IndexProperty();
        f.setObjectId(objectId);
        f.setIndexOrStatisticsName(indexOrStatisticsName);
        f.setProperty(property);
        return f;
    }
    static NextValueFor f_nextvaluefor(
            TableName sequenceName,
            Over over){
        NextValueFor f = new NextValueFor();
        f.setSequenceName(sequenceName);
        f.setOver(over);
        return f;
    }
    static NextValueFor f_nextvaluefor(
            TableName sequenceName){
        NextValueFor f = new NextValueFor();
        f.setSequenceName(sequenceName);
        return f;
    }
    static Object_Definition f_object_definition(
            Expression objectId){
        Object_Definition f = new Object_Definition();
        f.setObjectId(objectId);
        return f;
    }
    static Object_Id f_object_id(
            Expression objectName,
            Expression objectType){
        Object_Id f = new Object_Id();
        f.setObjectName(objectName);
        f.setObjectType(objectType);
        return f;
    }
    static Object_Id f_object_id(
            Expression objectName){
        Object_Id f = new Object_Id();
        f.setObjectName(objectName);
        return f;
    }
    static Object_Name f_object_name(
            Expression objectId,
            Expression databaseId){
        Object_Name f = new Object_Name();
        f.setObjectId(objectId);
        f.setDatabaseId(databaseId);
        return f;
    }
    static Object_Name f_object_name(
            Expression objectId){
        Object_Name f = new Object_Name();
        f.setObjectId(objectId);
        return f;
    }
    static Object_Schema_Name f_object_schema_name(
            Expression objectId,
            Expression databaseId){
        Object_Schema_Name f = new Object_Schema_Name();
        f.setObjectId(objectId);
        f.setDatabaseId(databaseId);
        return f;
    }
    static Object_Schema_Name f_object_schema_name(
            Expression objectId){
        Object_Schema_Name f = new Object_Schema_Name();
        f.setObjectId(objectId);
        return f;
    }
    static ObjectProperty f_objectproperty(
            Expression id,
            Expression property){
        ObjectProperty f = new ObjectProperty();
        f.setId(id);
        f.setProperty(property);
        return f;
    }
    static ObjectPropertyEX f_objectpropertyex(
            Expression id,
            Expression property){
        ObjectPropertyEX f = new ObjectPropertyEX();
        f.setId(id);
        f.setProperty(property);
        return f;
    }
    static Original_Db_Name f_original_db_name(){
        return new Original_Db_Name();
    }
    static ParseName f_parsename(
            StringConstant objectName,
            Expression objectPiece){
        ParseName f = new ParseName();
        f.setObjectName(objectName);
        f.setObjectPiece(objectPiece);
        return f;
    }
    static Schema_Id f_schema_id(
            Expression schemaName){
        Schema_Id f = new Schema_Id();
        f.setSchemaName(schemaName);
        return f;
    }
    static Schema_Id f_schema_id(){
        Schema_Id f = new Schema_Id();
        return f;
    }
    static Schema_Name f_schema_name(
            Expression schemaId){
        Schema_Name f = new Schema_Name();
        f.setSchemaId(schemaId);
        return f;
    }
    static Schema_Name f_schema_name(){
        Schema_Name f = new Schema_Name();
        return f;
    }
    static Scope_Identity f_scope_identity(){
        return new Scope_Identity();
    }
    static ServerProperty f_serverproperty(
            StringConstant propertyName){
        ServerProperty f = new ServerProperty();
        f.setPropertyName(propertyName);
        return f;
    }
    static Stats_Date f_stats_date(
            Expression objectId,
            Expression statsId){
        Stats_Date f = new Stats_Date();
        f.setObjectId(objectId);
        f.setStatsId(statsId);
        return f;
    }
    static Type_Id f_type_id(
            Expression schemaName,
            Expression typeName){
        Type_Id f = new Type_Id();
        f.setSchemaName(schemaName);
        f.setTypeName(typeName);
        return f;
    }
    static Type_Id f_type_id(
            Expression typeName){
        Type_Id f = new Type_Id();
        f.setTypeName(typeName);
        return f;
    }
    static Type_Id f_type_id(
            DataType typeName){
        Type_Id f = new Type_Id();
        f.setTypeName(c_string(typeName.name()));
        return f;
    }
    static Type_Name f_type_name(
            Expression typeId){
        Type_Name f = new Type_Name();
        f.setTypeId(typeId);
        return f;
    }
    static TypeProperty f_typeproperty(
            Expression type,
            Expression property){
        TypeProperty f = new TypeProperty();
        f.setType(type);
        f.setProperty(property);
        return f;
    }
}
