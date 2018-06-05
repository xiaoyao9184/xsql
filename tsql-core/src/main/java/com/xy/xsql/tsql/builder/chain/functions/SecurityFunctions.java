package com.xy.xsql.tsql.builder.chain.functions;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.security.*;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public interface SecurityFunctions {

    static Certencoded f_certencoded(
            Expression certId){
        Certencoded f = new Certencoded();
        f.setCertId(certId);
        return f;
    }
    static CertPrivateKey f_certprivatekey(
            Expression certId,
            StringConstant encryptionPassword,
            StringConstant decryptionPassword){
        CertPrivateKey f = new CertPrivateKey();
        f.setCertId(certId);
        f.setEncryptionPassword(encryptionPassword);
        f.setDecryptionPassword(decryptionPassword);
        return f;
    }
    static CertPrivateKey f_certprivatekey(
            Expression certId,
            StringConstant encryptionPassword){
        CertPrivateKey f = new CertPrivateKey();
        f.setCertId(certId);
        f.setEncryptionPassword(encryptionPassword);
        return f;
    }
    static Current_User f_current_user(){
        return new Current_User();
    }
    static Has_DBAccess f_has_dbaccess(
            StringConstant databaseName){
        Has_DBAccess f = new Has_DBAccess();
        f.setDatabaseName(databaseName);
        return f;
    }
    static Has_Perms_By_Name f_has_perms_by_name(
            Expression securable,
            Expression securableClass,
            Expression permission,
            Expression subSecurable,
            Expression subSecurableClass){
        Has_Perms_By_Name f = new Has_Perms_By_Name();
        f.setSecurable(securable);
        f.setSecurableClass(securableClass);
        f.setPermission(permission);
        f.setSubSecurable(subSecurable);
        f.setSubSecurableClass(subSecurableClass);
        return f;
    }
    static Has_Perms_By_Name f_has_perms_by_name(
            Expression securable,
            Expression securableClass,
            Expression permission){
        Has_Perms_By_Name f = new Has_Perms_By_Name();
        f.setSecurable(securable);
        f.setSecurableClass(securableClass);
        f.setPermission(permission);
        return f;
    }
    static Is_Member f_is_member(
            StringConstant role){
        Is_Member f = new Is_Member();
        f.setRole(role);
        return f;
    }
    static Is_RoleMember f_is_rolemember(
            StringConstant role,
            StringConstant database_principal){
        Is_RoleMember f = new Is_RoleMember();
        f.setRole(role);
        f.setDatabase_principal(database_principal);
        return f;
    }
    static Is_RoleMember f_is_rolemember(
            StringConstant role){
        Is_RoleMember f = new Is_RoleMember();
        f.setRole(role);
        return f;
    }
    static Is_SrvRoleMember f_is_srvrolemember(
            StringConstant role,
            StringConstant login){
        Is_SrvRoleMember f = new Is_SrvRoleMember();
        f.setRole(role);
        f.setLogin(login);
        return f;
    }
    static Is_SrvRoleMember f_is_srvrolemember(
            StringConstant role){
        Is_SrvRoleMember f = new Is_SrvRoleMember();
        f.setRole(role);
        return f;
    }
    static LoginProperty f_loginproperty(
            StringConstant loginName,
            StringConstant propertyName){
        LoginProperty f = new LoginProperty();
        f.setLoginName(loginName);
        f.setPropertyName(propertyName);
        return f;
    }
    static Original_Login f_original_login(){
        return new Original_Login();
    }
    static Permissions f_permissions(
            Expression objectId,
            StringConstant column){
        Permissions f = new Permissions();
        f.setObjectId(objectId);
        f.setColumn(column);
        return f;
    }
    static Permissions f_permissions(
            Expression objectId){
        Permissions f = new Permissions();
        f.setObjectId(objectId);
        return f;
    }
    static Permissions f_permissions(){
        Permissions f = new Permissions();
        return f;
    }
    static PWDencrypt f_pwdencrypt(
            StringConstant password){
        PWDencrypt f = new PWDencrypt();
        f.setPassword(password);
        return f;
    }
    static PWDcompare f_pwdcompare(
            StringConstant clearTextPassword,
            Expression passwordHash,
            Expression version){
        PWDcompare f = new PWDcompare();
        f.setClearTextPassword(clearTextPassword);
        f.setPasswordHash(passwordHash);
        f.setVersion(version);
        return f;
    }
    static PWDcompare f_pwdcompare(
            StringConstant clearTextPassword,
            Expression passwordHash){
        PWDcompare f = new PWDcompare();
        f.setClearTextPassword(clearTextPassword);
        f.setPasswordHash(passwordHash);
        return f;
    }
    static Session_User f_session_user(){
        return new Session_User();
    }
    static SessionProperty f_sessionproperty(
            StringConstant option){
        SessionProperty f = new SessionProperty();
        f.setOption(option);
        return f;
    }
    static SUser_Id f_suser_id(
            StringConstant login){
        SUser_Id f = new SUser_Id();
        f.setLogin(login);
        return f;
    }
    static SUser_Id f_suser_id(){
        SUser_Id f = new SUser_Id();
        return f;
    }
    static SUser_Name f_suser_name(
            StringConstant serverUserId){
        SUser_Name f = new SUser_Name();
        f.setServerUserId(serverUserId);
        return f;
    }
    static SUser_Name f_suser_name(){
        SUser_Name f = new SUser_Name();
        return f;
    }
    static SUser_SId f_suser_sid(
            StringConstant login,
            StringConstant param2){
        SUser_SId f = new SUser_SId();
        f.setLogin(login);
        f.setParam2(param2);
        return f;
    }
    static SUser_SId f_suser_sid(){
        SUser_SId f = new SUser_SId();
        return f;
    }
    static SUser_SName f_suser_sname(
            StringConstant serverUserSId){
        SUser_SName f = new SUser_SName();
        f.setServerUserSId(serverUserSId);
        return f;
    }
    static System_User f_system_user(){
        return new System_User();
    }
    static User f_user(){
        return new User();
    }
    static User_Id f_user_id(
            StringConstant user){
        User_Id f = new User_Id();
        f.setUser(user);
        return f;
    }
    static User_Id f_user_id(){
        User_Id f = new User_Id();
        return f;
    }
    static User_Name f_user_name(
            StringConstant id){
        User_Name f = new User_Name();
        f.setId(id);
        return f;
    }
    static User_Name f_user_name(){
        User_Name f = new User_Name();
        return f;
    }
}
