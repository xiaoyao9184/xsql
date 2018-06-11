package com.xy.xsql.tsql.builder.chain.functions;

import com.xy.xsql.tsql.model.datatypes.constants.BinaryConstant;
import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.elements.variables.LocalVariable;
import com.xy.xsql.tsql.model.functions.cryptographic.*;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public interface CryptographicFunctions {


    static AsymKey_Id f_asymkey_id(
            StringConstant asymKeyName){
        AsymKey_Id f = new AsymKey_Id();
        f.setAsymKeyName(asymKeyName);
        return f;
    }
    static AsymKeyProperty f_asymkeyproperty(
            Expression keyId,
            AsymKeyProperty.Properties properties){
        AsymKeyProperty f = new AsymKeyProperty();
        f.setKeyId(keyId);
        f.setProperties(properties);
        return f;
    }
    static Cert_Id f_cert_id(
            StringConstant certName){
        Cert_Id f = new Cert_Id();
        f.setCertName(certName);
        return f;
    }
    static CertProperty f_certproperty(
            Expression certId,
            CertProperty.Properties properties){
        CertProperty f = new CertProperty();
        f.setCertId(certId);
        f.setProperties(properties);
        return f;
    }
    static Crypt_Gen_Random f_crypt_gen_random(
            Integer length,
            BinaryConstant seed){
        Crypt_Gen_Random f = new Crypt_Gen_Random();
        f.setLength(length);
        f.setSeed(seed);
        return f;
    }
    static Crypt_Gen_Random f_crypt_gen_random(
            Integer length){
        Crypt_Gen_Random f = new Crypt_Gen_Random();
        f.setLength(length);
        return f;
    }
    static DecryptByAsymKey f_decryptbyasymkey(
            Expression asymKeyId,
            StringConstant ciphertext,
            StringConstant asymKeyPassword){
        DecryptByAsymKey f = new DecryptByAsymKey();
        f.setAsymKeyId(asymKeyId);
        f.setCiphertext(ciphertext);
        f.setAsymKeyPassword(asymKeyPassword);
        return f;
    }
    static DecryptByAsymKey f_decryptbyasymkey(
            Expression asymKeyId,
            LocalVariable ciphertextVariable,
            StringConstant asymKeyPassword){
        DecryptByAsymKey f = new DecryptByAsymKey();
        f.setAsymKeyId(asymKeyId);
        f.setCiphertextVariable(ciphertextVariable);
        f.setAsymKeyPassword(asymKeyPassword);
        return f;
    }
    static DecryptByAsymKey f_decryptbyasymkey(
            Expression asymKeyId,
            StringConstant ciphertext){
        DecryptByAsymKey f = new DecryptByAsymKey();
        f.setAsymKeyId(asymKeyId);
        f.setCiphertext(ciphertext);
        return f;
    }
    static DecryptByAsymKey f_decryptbyasymkey(
            Expression asymKeyId,
            LocalVariable ciphertextVariable){
        DecryptByAsymKey f = new DecryptByAsymKey();
        f.setAsymKeyId(asymKeyId);
        f.setCiphertextVariable(ciphertextVariable);
        return f;
    }
    static DecryptByCert f_decryptbycert(
            Expression certificateId,
            StringConstant ciphertext,
            StringConstant certPassword){
        DecryptByCert f = new DecryptByCert();
        f.setCertificateId(certificateId);
        f.setCiphertext(ciphertext);
        f.setCertPassword(certPassword);
        return f;
    }
    static DecryptByCert f_decryptbycert(
            Expression certificateId,
            StringConstant ciphertext,
            LocalVariable certPasswordVariable){
        DecryptByCert f = new DecryptByCert();
        f.setCertificateId(certificateId);
        f.setCiphertext(ciphertext);
        f.setCertPasswordVariable(certPasswordVariable);
        return f;
    }
    static DecryptByCert f_decryptbycert(
            Expression certificateId,
            LocalVariable ciphertextVariable,
            StringConstant certPassword){
        DecryptByCert f = new DecryptByCert();
        f.setCertificateId(certificateId);
        f.setCiphertextVariable(ciphertextVariable);
        f.setCertPassword(certPassword);
        return f;
    }
    static DecryptByCert f_decryptbycert(
            Expression certificateId,
            LocalVariable ciphertextVariable,
            LocalVariable certPasswordVariable){
        DecryptByCert f = new DecryptByCert();
        f.setCertificateId(certificateId);
        f.setCiphertextVariable(ciphertextVariable);
        f.setCertPasswordVariable(certPasswordVariable);
        return f;
    }

    static DecryptByKey f_decryptbykey(
            StringConstant ciphertext,
            Expression addAuthenticator,
            Expression authenticator){
        DecryptByKey f = new DecryptByKey();
        f.setCiphertext(ciphertext);
        f.setAddAuthenticator(addAuthenticator);
        f.setAuthenticator(authenticator);
        return f;
    }
    static DecryptByKey f_decryptbykey(
            StringConstant ciphertext,
            Expression addAuthenticator,
            LocalVariable authenticatorVariable){
        DecryptByKey f = new DecryptByKey();
        f.setCiphertext(ciphertext);
        f.setAddAuthenticator(addAuthenticator);
        f.setAuthenticatorVariable(authenticatorVariable);
        return f;
    }
    static DecryptByKey f_decryptbykey(
            StringConstant ciphertext){
        DecryptByKey f = new DecryptByKey();
        f.setCiphertext(ciphertext);
        return f;
    }
    static DecryptByKey f_decryptbykey(
            LocalVariable ciphertextVariable,
            Expression addAuthenticator,
            Expression authenticator){
        DecryptByKey f = new DecryptByKey();
        f.setCiphertextVariable(ciphertextVariable);
        f.setAddAuthenticator(addAuthenticator);
        f.setAuthenticator(authenticator);
        return f;
    }
    static DecryptByKey f_decryptbykey(
            LocalVariable ciphertextVariable,
            Expression addAuthenticator,
            LocalVariable authenticatorVariable){
        DecryptByKey f = new DecryptByKey();
        f.setCiphertextVariable(ciphertextVariable);
        f.setAddAuthenticator(addAuthenticator);
        f.setAuthenticatorVariable(authenticatorVariable);
        return f;
    }
    static DecryptByKey f_decryptbykey(
            LocalVariable ciphertextVariable){
        DecryptByKey f = new DecryptByKey();
        f.setCiphertextVariable(ciphertextVariable);
        return f;
    }

    static DecryptByKeyAutoAsymKey f_decryptbykeyautoasymkey(
            Expression akeyId,
            Expression akeyPassword,
            StringConstant ciphertext,
            NumberConstant addAuthenticator,
            StringConstant authenticator){
        DecryptByKeyAutoAsymKey f = new DecryptByKeyAutoAsymKey();
        f.setAkeyId(akeyId);
        f.setAkeyPassword(akeyPassword);
        f.setCiphertext(ciphertext);
        f.setAddAuthenticator(addAuthenticator);
        f.setAuthenticator(authenticator);
        return f;
    }
    static DecryptByKeyAutoAsymKey f_decryptbykeyautoasymkey(
            Expression akeyId,
            Expression akeyPassword,
            StringConstant ciphertext,
            NumberConstant addAuthenticator,
            LocalVariable authenticatorVariable){
        DecryptByKeyAutoAsymKey f = new DecryptByKeyAutoAsymKey();
        f.setAkeyId(akeyId);
        f.setAkeyPassword(akeyPassword);
        f.setCiphertext(ciphertext);
        f.setAddAuthenticator(addAuthenticator);
        f.setAuthenticatorVariable(authenticatorVariable);
        return f;
    }
    static DecryptByKeyAutoAsymKey f_decryptbykeyautoasymkey(
            Expression akeyId,
            Expression akeyPassword,
            StringConstant ciphertext,
            NumberConstant addAuthenticator){
        DecryptByKeyAutoAsymKey f = new DecryptByKeyAutoAsymKey();
        f.setAkeyId(akeyId);
        f.setAkeyPassword(akeyPassword);
        f.setCiphertext(ciphertext);
        f.setAddAuthenticator(addAuthenticator);
        return f;
    }
    static DecryptByKeyAutoAsymKey f_decryptbykeyautoasymkey(
            Expression akeyId,
            Expression akeyPassword,
            StringConstant ciphertext,
            LocalVariable addAuthenticatorVariable,
            StringConstant authenticator){
        DecryptByKeyAutoAsymKey f = new DecryptByKeyAutoAsymKey();
        f.setAkeyId(akeyId);
        f.setAkeyPassword(akeyPassword);
        f.setCiphertext(ciphertext);
        f.setAddAuthenticatorVariable(addAuthenticatorVariable);
        f.setAuthenticator(authenticator);
        return f;
    }
    static DecryptByKeyAutoAsymKey f_decryptbykeyautoasymkey(
            Expression akeyId,
            Expression akeyPassword,
            StringConstant ciphertext,
            LocalVariable addAuthenticatorVariable,
            LocalVariable authenticatorVariable){
        DecryptByKeyAutoAsymKey f = new DecryptByKeyAutoAsymKey();
        f.setAkeyId(akeyId);
        f.setAkeyPassword(akeyPassword);
        f.setCiphertext(ciphertext);
        f.setAddAuthenticatorVariable(addAuthenticatorVariable);
        f.setAuthenticatorVariable(authenticatorVariable);
        return f;
    }
    static DecryptByKeyAutoAsymKey f_decryptbykeyautoasymkey(
            Expression akeyId,
            Expression akeyPassword,
            StringConstant ciphertext,
            LocalVariable authenticatorVariable){
        DecryptByKeyAutoAsymKey f = new DecryptByKeyAutoAsymKey();
        f.setAkeyId(akeyId);
        f.setAkeyPassword(akeyPassword);
        f.setCiphertext(ciphertext);
        f.setAuthenticatorVariable(authenticatorVariable);
        return f;
    }
    static DecryptByKeyAutoAsymKey f_decryptbykeyautoasymkey(
            Expression akeyId,
            Expression akeyPassword,
            StringConstant ciphertext){
        DecryptByKeyAutoAsymKey f = new DecryptByKeyAutoAsymKey();
        f.setAkeyId(akeyId);
        f.setAkeyPassword(akeyPassword);
        f.setCiphertext(ciphertext);
        return f;
    }
    static DecryptByKeyAutoAsymKey f_decryptbykeyautoasymkey(
            Expression akeyId,
            Expression akeyPassword,
            LocalVariable ciphertextVariable,
            NumberConstant addAuthenticator,
            StringConstant authenticator){
        DecryptByKeyAutoAsymKey f = new DecryptByKeyAutoAsymKey();
        f.setAkeyId(akeyId);
        f.setAkeyPassword(akeyPassword);
        f.setCiphertextVariable(ciphertextVariable);
        f.setAddAuthenticator(addAuthenticator);
        f.setAuthenticator(authenticator);
        return f;
    }
    static DecryptByKeyAutoAsymKey f_decryptbykeyautoasymkey(
            Expression akeyId,
            Expression akeyPassword,
            LocalVariable ciphertextVariable,
            NumberConstant addAuthenticator,
            LocalVariable authenticatorVariable){
        DecryptByKeyAutoAsymKey f = new DecryptByKeyAutoAsymKey();
        f.setAkeyId(akeyId);
        f.setAkeyPassword(akeyPassword);
        f.setCiphertextVariable(ciphertextVariable);
        f.setAddAuthenticator(addAuthenticator);
        f.setAuthenticatorVariable(authenticatorVariable);
        return f;
    }
    static DecryptByKeyAutoAsymKey f_decryptbykeyautoasymkey(
            Expression akeyId,
            Expression akeyPassword,
            LocalVariable ciphertextVariable,
            NumberConstant addAuthenticator){
        DecryptByKeyAutoAsymKey f = new DecryptByKeyAutoAsymKey();
        f.setAkeyId(akeyId);
        f.setAkeyPassword(akeyPassword);
        f.setCiphertextVariable(ciphertextVariable);
        f.setAddAuthenticator(addAuthenticator);
        return f;
    }
    static DecryptByKeyAutoAsymKey f_decryptbykeyautoasymkey(
            Expression akeyId,
            Expression akeyPassword,
            LocalVariable ciphertextVariable,
            LocalVariable addAuthenticatorVariable,
            StringConstant authenticator){
        DecryptByKeyAutoAsymKey f = new DecryptByKeyAutoAsymKey();
        f.setAkeyId(akeyId);
        f.setAkeyPassword(akeyPassword);
        f.setCiphertextVariable(ciphertextVariable);
        f.setAddAuthenticatorVariable(addAuthenticatorVariable);
        f.setAuthenticator(authenticator);
        return f;
    }
    static DecryptByKeyAutoAsymKey f_decryptbykeyautoasymkey(
            Expression akeyId,
            Expression akeyPassword,
            LocalVariable ciphertextVariable,
            LocalVariable addAuthenticatorVariable,
            LocalVariable authenticatorVariable){
        DecryptByKeyAutoAsymKey f = new DecryptByKeyAutoAsymKey();
        f.setAkeyId(akeyId);
        f.setAkeyPassword(akeyPassword);
        f.setCiphertextVariable(ciphertextVariable);
        f.setAddAuthenticatorVariable(addAuthenticatorVariable);
        f.setAuthenticatorVariable(authenticatorVariable);
        return f;
    }
    static DecryptByKeyAutoAsymKey f_decryptbykeyautoasymkey(
            Expression akeyId,
            Expression akeyPassword,
            LocalVariable addAuthenticatorVariable,
            LocalVariable authenticatorVariable){
        DecryptByKeyAutoAsymKey f = new DecryptByKeyAutoAsymKey();
        f.setAkeyId(akeyId);
        f.setAkeyPassword(akeyPassword);
        f.setAddAuthenticatorVariable(addAuthenticatorVariable);
        f.setAuthenticatorVariable(authenticatorVariable);
        return f;
    }
    static DecryptByKeyAutoAsymKey f_decryptbykeyautoasymkey(
            Expression akeyId,
            Expression akeyPassword,
            LocalVariable ciphertextVariable){
        DecryptByKeyAutoAsymKey f = new DecryptByKeyAutoAsymKey();
        f.setAkeyId(akeyId);
        f.setAkeyPassword(akeyPassword);
        f.setCiphertextVariable(ciphertextVariable);
        return f;
    }

    static DecryptByKeyAutoCert f_decryptbykeyautocert(
            Expression certId,
            Expression certPassword,
            StringConstant ciphertext,
            NumberConstant addAuthenticator,
            StringConstant authenticator){
        DecryptByKeyAutoCert f = new DecryptByKeyAutoCert();
        f.setCertId(certId);
        f.setCertPassword(certPassword);
        f.setCiphertext(ciphertext);
        f.setAddAuthenticator(addAuthenticator);
        f.setAuthenticator(authenticator);
        return f;
    }
    static DecryptByKeyAutoCert f_decryptbykeyautocert(
            Expression certId,
            Expression certPassword,
            StringConstant ciphertext,
            NumberConstant addAuthenticator,
            LocalVariable authenticatorVariable){
        DecryptByKeyAutoCert f = new DecryptByKeyAutoCert();
        f.setCertId(certId);
        f.setCertPassword(certPassword);
        f.setCiphertext(ciphertext);
        f.setAddAuthenticator(addAuthenticator);
        f.setAuthenticatorVariable(authenticatorVariable);
        return f;
    }
    static DecryptByKeyAutoCert f_decryptbykeyautocert(
            Expression certId,
            Expression certPassword,
            StringConstant ciphertext,
            NumberConstant addAuthenticator){
        DecryptByKeyAutoCert f = new DecryptByKeyAutoCert();
        f.setCertId(certId);
        f.setCertPassword(certPassword);
        f.setCiphertext(ciphertext);
        f.setAddAuthenticator(addAuthenticator);
        return f;
    }
    static DecryptByKeyAutoCert f_decryptbykeyautocert(
            Expression certId,
            Expression certPassword,
            StringConstant ciphertext,
            LocalVariable addAuthenticatorVariable,
            StringConstant authenticator){
        DecryptByKeyAutoCert f = new DecryptByKeyAutoCert();
        f.setCertId(certId);
        f.setCertPassword(certPassword);
        f.setCiphertext(ciphertext);
        f.setAddAuthenticatorVariable(addAuthenticatorVariable);
        f.setAuthenticator(authenticator);
        return f;
    }
    static DecryptByKeyAutoCert f_decryptbykeyautocert(
            Expression certId,
            Expression certPassword,
            StringConstant ciphertext,
            LocalVariable addAuthenticatorVariable,
            LocalVariable authenticatorVariable){
        DecryptByKeyAutoCert f = new DecryptByKeyAutoCert();
        f.setCertId(certId);
        f.setCertPassword(certPassword);
        f.setCiphertext(ciphertext);
        f.setAddAuthenticatorVariable(addAuthenticatorVariable);
        f.setAuthenticatorVariable(authenticatorVariable);
        return f;
    }
    static DecryptByKeyAutoCert f_decryptbykeyautocert(
            Expression certId,
            Expression certPassword,
            StringConstant ciphertext,
            LocalVariable addAuthenticatorVariable){
        DecryptByKeyAutoCert f = new DecryptByKeyAutoCert();
        f.setCertId(certId);
        f.setCertPassword(certPassword);
        f.setCiphertext(ciphertext);
        f.setAddAuthenticatorVariable(addAuthenticatorVariable);
        return f;
    }
    static DecryptByKeyAutoCert f_decryptbykeyautocert(
            Expression certId,
            Expression certPassword,
            StringConstant ciphertext){
        DecryptByKeyAutoCert f = new DecryptByKeyAutoCert();
        f.setCertId(certId);
        f.setCertPassword(certPassword);
        f.setCiphertext(ciphertext);
        return f;
    }
    static DecryptByKeyAutoCert f_decryptbykeyautocert(
            Expression certId,
            Expression certPassword,
            LocalVariable ciphertextVariable,
            NumberConstant addAuthenticator,
            StringConstant authenticator){
        DecryptByKeyAutoCert f = new DecryptByKeyAutoCert();
        f.setCertId(certId);
        f.setCertPassword(certPassword);
        f.setCiphertextVariable(ciphertextVariable);
        f.setAddAuthenticator(addAuthenticator);
        f.setAuthenticator(authenticator);
        return f;
    }
    static DecryptByKeyAutoCert f_decryptbykeyautocert(
            Expression certId,
            Expression certPassword,
            LocalVariable ciphertextVariable,
            NumberConstant addAuthenticator,
            LocalVariable authenticatorVariable){
        DecryptByKeyAutoCert f = new DecryptByKeyAutoCert();
        f.setCertId(certId);
        f.setCertPassword(certPassword);
        f.setCiphertextVariable(ciphertextVariable);
        f.setAddAuthenticator(addAuthenticator);
        f.setAuthenticatorVariable(authenticatorVariable);
        return f;
    }
    static DecryptByKeyAutoCert f_decryptbykeyautocert(
            Expression certId,
            Expression certPassword,
            LocalVariable ciphertextVariable,
            NumberConstant addAuthenticator){
        DecryptByKeyAutoCert f = new DecryptByKeyAutoCert();
        f.setCertId(certId);
        f.setCertPassword(certPassword);
        f.setCiphertextVariable(ciphertextVariable);
        f.setAddAuthenticator(addAuthenticator);
        return f;
    }
    static DecryptByKeyAutoCert f_decryptbykeyautocert(
            Expression certId,
            Expression certPassword,
            LocalVariable ciphertextVariable,
            LocalVariable addAuthenticatorVariable,
            StringConstant authenticator){
        DecryptByKeyAutoCert f = new DecryptByKeyAutoCert();
        f.setCertId(certId);
        f.setCertPassword(certPassword);
        f.setCiphertextVariable(ciphertextVariable);
        f.setAddAuthenticatorVariable(addAuthenticatorVariable);
        f.setAuthenticator(authenticator);
        return f;
    }
    static DecryptByKeyAutoCert f_decryptbykeyautocert(
            Expression certId,
            Expression certPassword,
            LocalVariable ciphertextVariable,
            LocalVariable addAuthenticatorVariable,
            LocalVariable authenticatorVariable){
        DecryptByKeyAutoCert f = new DecryptByKeyAutoCert();
        f.setCertId(certId);
        f.setCertPassword(certPassword);
        f.setCiphertextVariable(ciphertextVariable);
        f.setAddAuthenticatorVariable(addAuthenticatorVariable);
        f.setAuthenticatorVariable(authenticatorVariable);
        return f;
    }
    static DecryptByKeyAutoCert f_decryptbykeyautocert(
            Expression certId,
            Expression certPassword,
            LocalVariable ciphertextVariable,
            LocalVariable addAuthenticatorVariable){
        DecryptByKeyAutoCert f = new DecryptByKeyAutoCert();
        f.setCertId(certId);
        f.setCertPassword(certPassword);
        f.setCiphertextVariable(ciphertextVariable);
        f.setAddAuthenticatorVariable(addAuthenticatorVariable);
        return f;
    }
    static DecryptByKeyAutoCert f_decryptbykeyautocert(
            Expression certId,
            Expression certPassword,
            LocalVariable ciphertextVariable){
        DecryptByKeyAutoCert f = new DecryptByKeyAutoCert();
        f.setCertId(certId);
        f.setCertPassword(certPassword);
        f.setCiphertextVariable(ciphertextVariable);
        return f;
    }

    static DecryptByPassPhrase f_decryptbypassphrase(
            StringConstant passphrase,
            StringConstant ciphertext,
            Expression addAuthenticator,
            Expression authenticator){
        DecryptByPassPhrase f = new DecryptByPassPhrase();
        f.setPassphrase(passphrase);
        f.setCiphertext(ciphertext);
        f.setAddAuthenticator(addAuthenticator);
        f.setAuthenticator(authenticator);
        return f;
    }
    static DecryptByPassPhrase f_decryptbypassphrase(
            StringConstant passphrase,
            StringConstant ciphertext,
            Expression addAuthenticator,
            LocalVariable authenticatorVariable){
        DecryptByPassPhrase f = new DecryptByPassPhrase();
        f.setPassphrase(passphrase);
        f.setCiphertext(ciphertext);
        f.setAddAuthenticator(addAuthenticator);
        f.setAuthenticatorVariable(authenticatorVariable);
        return f;
    }
    static DecryptByPassPhrase f_decryptbypassphrase(
            StringConstant passphrase,
            StringConstant ciphertext,
            Expression addAuthenticator){
        DecryptByPassPhrase f = new DecryptByPassPhrase();
        f.setPassphrase(passphrase);
        f.setCiphertext(ciphertext);
        f.setAddAuthenticator(addAuthenticator);
        return f;
    }
    static DecryptByPassPhrase f_decryptbypassphrase(
            StringConstant passphrase,
            StringConstant ciphertext,
            LocalVariable addAuthenticatorVariable,
            Expression authenticator){
        DecryptByPassPhrase f = new DecryptByPassPhrase();
        f.setPassphrase(passphrase);
        f.setCiphertext(ciphertext);
        f.setAddAuthenticatorVariable(addAuthenticatorVariable);
        f.setAuthenticator(authenticator);
        return f;
    }
    static DecryptByPassPhrase f_decryptbypassphrase(
            StringConstant passphrase,
            StringConstant ciphertext,
            LocalVariable addAuthenticatorVariable,
            LocalVariable authenticatorVariable){
        DecryptByPassPhrase f = new DecryptByPassPhrase();
        f.setPassphrase(passphrase);
        f.setCiphertext(ciphertext);
        f.setAddAuthenticatorVariable(addAuthenticatorVariable);
        f.setAuthenticatorVariable(authenticatorVariable);
        return f;
    }
    static DecryptByPassPhrase f_decryptbypassphrase(
            StringConstant passphrase,
            StringConstant ciphertext,
            LocalVariable addAuthenticatorVariable){
        DecryptByPassPhrase f = new DecryptByPassPhrase();
        f.setPassphrase(passphrase);
        f.setCiphertext(ciphertext);
        f.setAddAuthenticatorVariable(addAuthenticatorVariable);
        return f;
    }
    static DecryptByPassPhrase f_decryptbypassphrase(
            StringConstant passphrase,
            StringConstant ciphertext){
        DecryptByPassPhrase f = new DecryptByPassPhrase();
        f.setPassphrase(passphrase);
        f.setCiphertext(ciphertext);
        return f;
    }
    static DecryptByPassPhrase f_decryptbypassphrase(
            StringConstant passphrase,
            LocalVariable ciphertextVariable,
            Expression addAuthenticator,
            Expression authenticator){
        DecryptByPassPhrase f = new DecryptByPassPhrase();
        f.setPassphrase(passphrase);
        f.setCiphertextVariable(ciphertextVariable);
        f.setAddAuthenticator(addAuthenticator);
        f.setAuthenticator(authenticator);
        return f;
    }
    static DecryptByPassPhrase f_decryptbypassphrase(
            StringConstant passphrase,
            LocalVariable ciphertextVariable,
            Expression addAuthenticator,
            LocalVariable authenticatorVariable){
        DecryptByPassPhrase f = new DecryptByPassPhrase();
        f.setPassphrase(passphrase);
        f.setCiphertextVariable(ciphertextVariable);
        f.setAddAuthenticator(addAuthenticator);
        f.setAuthenticatorVariable(authenticatorVariable);
        return f;
    }
    static DecryptByPassPhrase f_decryptbypassphrase(
            StringConstant passphrase,
            LocalVariable ciphertextVariable,
            Expression addAuthenticator){
        DecryptByPassPhrase f = new DecryptByPassPhrase();
        f.setPassphrase(passphrase);
        f.setCiphertextVariable(ciphertextVariable);
        f.setAddAuthenticator(addAuthenticator);
        return f;
    }
    static DecryptByPassPhrase f_decryptbypassphrase(
            StringConstant passphrase,
            LocalVariable ciphertextVariable,
            LocalVariable addAuthenticatorVariable,
            Expression authenticator){
        DecryptByPassPhrase f = new DecryptByPassPhrase();
        f.setPassphrase(passphrase);
        f.setCiphertextVariable(ciphertextVariable);
        f.setAddAuthenticatorVariable(addAuthenticatorVariable);
        f.setAuthenticator(authenticator);
        return f;
    }
    static DecryptByPassPhrase f_decryptbypassphrase(
            StringConstant passphrase,
            LocalVariable ciphertextVariable,
            LocalVariable addAuthenticatorVariable,
            LocalVariable authenticatorVariable){
        DecryptByPassPhrase f = new DecryptByPassPhrase();
        f.setPassphrase(passphrase);
        f.setCiphertextVariable(ciphertextVariable);
        f.setAddAuthenticatorVariable(addAuthenticatorVariable);
        f.setAuthenticatorVariable(authenticatorVariable);
        return f;
    }
    static DecryptByPassPhrase f_decryptbypassphrase(
            StringConstant passphrase,
            LocalVariable ciphertextVariable,
            LocalVariable addAuthenticatorVariable){
        DecryptByPassPhrase f = new DecryptByPassPhrase();
        f.setPassphrase(passphrase);
        f.setCiphertextVariable(ciphertextVariable);
        f.setAddAuthenticatorVariable(addAuthenticatorVariable);
        return f;
    }
    static DecryptByPassPhrase f_decryptbypassphrase(
            StringConstant passphrase,
            LocalVariable ciphertextVariable){
        DecryptByPassPhrase f = new DecryptByPassPhrase();
        f.setPassphrase(passphrase);
        f.setCiphertextVariable(ciphertextVariable);
        return f;
    }
    static DecryptByPassPhrase f_decryptbypassphrase(
            LocalVariable passphraseVariable,
            StringConstant ciphertext,
            Expression addAuthenticator,
            Expression authenticator){
        DecryptByPassPhrase f = new DecryptByPassPhrase();
        f.setPassphraseVariable(passphraseVariable);
        f.setCiphertext(ciphertext);
        f.setAddAuthenticator(addAuthenticator);
        f.setAuthenticator(authenticator);
        return f;
    }
    static DecryptByPassPhrase f_decryptbypassphrase(
            LocalVariable passphraseVariable,
            StringConstant ciphertext,
            Expression addAuthenticator,
            LocalVariable authenticatorVariable){
        DecryptByPassPhrase f = new DecryptByPassPhrase();
        f.setPassphraseVariable(passphraseVariable);
        f.setCiphertext(ciphertext);
        f.setAddAuthenticator(addAuthenticator);
        f.setAuthenticatorVariable(authenticatorVariable);
        return f;
    }
    static DecryptByPassPhrase f_decryptbypassphrase(
            LocalVariable passphraseVariable,
            StringConstant ciphertext,
            Expression addAuthenticator){
        DecryptByPassPhrase f = new DecryptByPassPhrase();
        f.setPassphraseVariable(passphraseVariable);
        f.setCiphertext(ciphertext);
        f.setAddAuthenticator(addAuthenticator);
        return f;
    }
    static DecryptByPassPhrase f_decryptbypassphrase(
            LocalVariable passphraseVariable,
            StringConstant ciphertext,
            LocalVariable addAuthenticatorVariable,
            Expression authenticator){
        DecryptByPassPhrase f = new DecryptByPassPhrase();
        f.setPassphraseVariable(passphraseVariable);
        f.setCiphertext(ciphertext);
        f.setAddAuthenticatorVariable(addAuthenticatorVariable);
        f.setAuthenticator(authenticator);
        return f;
    }
    static DecryptByPassPhrase f_decryptbypassphrase(
            LocalVariable passphraseVariable,
            StringConstant ciphertext,
            LocalVariable addAuthenticatorVariable,
            LocalVariable authenticatorVariable){
        DecryptByPassPhrase f = new DecryptByPassPhrase();
        f.setPassphraseVariable(passphraseVariable);
        f.setCiphertext(ciphertext);
        f.setAddAuthenticatorVariable(addAuthenticatorVariable);
        f.setAuthenticatorVariable(authenticatorVariable);
        return f;
    }
    static DecryptByPassPhrase f_decryptbypassphrase(
            LocalVariable passphraseVariable,
            StringConstant ciphertext,
            LocalVariable addAuthenticatorVariable){
        DecryptByPassPhrase f = new DecryptByPassPhrase();
        f.setPassphraseVariable(passphraseVariable);
        f.setCiphertext(ciphertext);
        f.setAddAuthenticatorVariable(addAuthenticatorVariable);
        return f;
    }
    static DecryptByPassPhrase f_decryptbypassphrase(
            LocalVariable passphraseVariable,
            LocalVariable ciphertextVariable,
            Expression addAuthenticator,
            Expression authenticator){
        DecryptByPassPhrase f = new DecryptByPassPhrase();
        f.setPassphraseVariable(passphraseVariable);
        f.setCiphertextVariable(ciphertextVariable);
        f.setAddAuthenticator(addAuthenticator);
        f.setAuthenticator(authenticator);
        return f;
    }
    static DecryptByPassPhrase f_decryptbypassphrase(
            LocalVariable passphraseVariable,
            LocalVariable ciphertextVariable,
            Expression addAuthenticator,
            LocalVariable authenticatorVariable){
        DecryptByPassPhrase f = new DecryptByPassPhrase();
        f.setPassphraseVariable(passphraseVariable);
        f.setCiphertextVariable(ciphertextVariable);
        f.setAddAuthenticator(addAuthenticator);
        f.setAuthenticatorVariable(authenticatorVariable);
        return f;
    }
    static DecryptByPassPhrase f_decryptbypassphrase(
            LocalVariable passphraseVariable,
            LocalVariable ciphertextVariable,
            Expression addAuthenticator){
        DecryptByPassPhrase f = new DecryptByPassPhrase();
        f.setPassphraseVariable(passphraseVariable);
        f.setCiphertextVariable(ciphertextVariable);
        f.setAddAuthenticator(addAuthenticator);
        return f;
    }
    static DecryptByPassPhrase f_decryptbypassphrase(
            LocalVariable passphraseVariable,
            LocalVariable ciphertextVariable,
            LocalVariable addAuthenticatorVariable,
            Expression authenticator){
        DecryptByPassPhrase f = new DecryptByPassPhrase();
        f.setPassphraseVariable(passphraseVariable);
        f.setCiphertextVariable(ciphertextVariable);
        f.setAddAuthenticatorVariable(addAuthenticatorVariable);
        f.setAuthenticator(authenticator);
        return f;
    }
    static DecryptByPassPhrase f_decryptbypassphrase(
            LocalVariable passphraseVariable,
            LocalVariable ciphertextVariable,
            LocalVariable addAuthenticatorVariable,
            LocalVariable authenticatorVariable){
        DecryptByPassPhrase f = new DecryptByPassPhrase();
        f.setPassphraseVariable(passphraseVariable);
        f.setCiphertextVariable(ciphertextVariable);
        f.setAddAuthenticatorVariable(addAuthenticatorVariable);
        f.setAuthenticatorVariable(authenticatorVariable);
        return f;
    }
    static DecryptByPassPhrase f_decryptbypassphrase(
            LocalVariable passphraseVariable,
            LocalVariable ciphertextVariable,
            LocalVariable addAuthenticatorVariable){
        DecryptByPassPhrase f = new DecryptByPassPhrase();
        f.setPassphraseVariable(passphraseVariable);
        f.setCiphertextVariable(ciphertextVariable);
        f.setAddAuthenticatorVariable(addAuthenticatorVariable);
        return f;
    }
    static DecryptByPassPhrase f_decryptbypassphrase(
            LocalVariable passphraseVariable,
            LocalVariable ciphertextVariable){
        DecryptByPassPhrase f = new DecryptByPassPhrase();
        f.setPassphraseVariable(passphraseVariable);
        f.setCiphertextVariable(ciphertextVariable);
        return f;
    }

    static EncryptByAsymKey f_encryptbyasymkey(
            Expression asymKeyId,
            StringConstant plaintext){
        EncryptByAsymKey f = new EncryptByAsymKey();
        f.setAsymKeyId(asymKeyId);
        f.setPlaintext(plaintext);
        return f;
    }
    static EncryptByAsymKey f_encryptbyasymkey(
            Expression asymKeyId,
            LocalVariable plaintextVariable){
        EncryptByAsymKey f = new EncryptByAsymKey();
        f.setAsymKeyId(asymKeyId);
        f.setPlaintextVariable(plaintextVariable);
        return f;
    }

    static EncryptByCert f_encryptbycert(
            Expression certificateId,
            StringConstant cleartext){
        EncryptByCert f = new EncryptByCert();
        f.setCertificateId(certificateId);
        f.setCleartext(cleartext);
        return f;
    }
    static EncryptByCert f_encryptbycert(
            Expression certificateId,
            LocalVariable cleartextVariable){
        EncryptByCert f = new EncryptByCert();
        f.setCertificateId(certificateId);
        f.setCleartextVariable(cleartextVariable);
        return f;
    }

    static EncryptByKey f_encryptbykey(
            Expression keyGUID,
            StringConstant cleartext,
            Expression addAuthenticator,
            Expression authenticator){
        EncryptByKey f = new EncryptByKey();
        f.setKeyGUID(keyGUID);
        f.setCleartext(cleartext);
        f.setAddAuthenticator(addAuthenticator);
        f.setAuthenticator(authenticator);
        return f;
    }
    static EncryptByKey f_encryptbykey(
            Expression keyGUID,
            StringConstant cleartext,
            Expression addAuthenticator,
            LocalVariable authenticatorVariable){
        EncryptByKey f = new EncryptByKey();
        f.setKeyGUID(keyGUID);
        f.setCleartext(cleartext);
        f.setAddAuthenticator(addAuthenticator);
        f.setAuthenticatorVariable(authenticatorVariable);
        return f;
    }
    static EncryptByKey f_encryptbykey(
            Expression keyGUID,
            StringConstant cleartext,
            LocalVariable addAuthenticatorVariable,
            Expression authenticator){
        EncryptByKey f = new EncryptByKey();
        f.setKeyGUID(keyGUID);
        f.setCleartext(cleartext);
        f.setAddAuthenticatorVariable(addAuthenticatorVariable);
        f.setAuthenticator(authenticator);
        return f;
    }
    static EncryptByKey f_encryptbykey(
            Expression keyGUID,
            StringConstant cleartext,
            LocalVariable addAuthenticatorVariable,
            LocalVariable authenticatorVariable){
        EncryptByKey f = new EncryptByKey();
        f.setKeyGUID(keyGUID);
        f.setCleartext(cleartext);
        f.setAddAuthenticatorVariable(addAuthenticatorVariable);
        f.setAuthenticatorVariable(authenticatorVariable);
        return f;
    }
    static EncryptByKey f_encryptbykey(
            Expression keyGUID,
            StringConstant cleartext){
        EncryptByKey f = new EncryptByKey();
        f.setKeyGUID(keyGUID);
        f.setCleartext(cleartext);
        return f;
    }
    static EncryptByKey f_encryptbykey(
            Expression keyGUID,
            LocalVariable cleartextVariable,
            Expression addAuthenticator,
            Expression authenticator){
        EncryptByKey f = new EncryptByKey();
        f.setKeyGUID(keyGUID);
        f.setCleartextVariable(cleartextVariable);
        f.setAddAuthenticator(addAuthenticator);
        f.setAuthenticator(authenticator);
        return f;
    }
    static EncryptByKey f_encryptbykey(
            Expression keyGUID,
            LocalVariable cleartextVariable,
            Expression addAuthenticator,
            LocalVariable authenticatorVariable){
        EncryptByKey f = new EncryptByKey();
        f.setKeyGUID(keyGUID);
        f.setCleartextVariable(cleartextVariable);
        f.setAddAuthenticator(addAuthenticator);
        f.setAuthenticatorVariable(authenticatorVariable);
        return f;
    }
    static EncryptByKey f_encryptbykey(
            Expression keyGUID,
            LocalVariable cleartextVariable,
            LocalVariable addAuthenticatorVariable,
            Expression authenticator){
        EncryptByKey f = new EncryptByKey();
        f.setKeyGUID(keyGUID);
        f.setCleartextVariable(cleartextVariable);
        f.setAddAuthenticatorVariable(addAuthenticatorVariable);
        f.setAuthenticator(authenticator);
        return f;
    }
    static EncryptByKey f_encryptbykey(
            Expression keyGUID,
            LocalVariable cleartextVariable,
            LocalVariable addAuthenticatorVariable,
            LocalVariable authenticatorVariable){
        EncryptByKey f = new EncryptByKey();
        f.setKeyGUID(keyGUID);
        f.setCleartextVariable(cleartextVariable);
        f.setAddAuthenticatorVariable(addAuthenticatorVariable);
        f.setAuthenticatorVariable(authenticatorVariable);
        return f;
    }
    static EncryptByKey f_encryptbykey(
            Expression keyGUID,
            LocalVariable cleartextVariable){
        EncryptByKey f = new EncryptByKey();
        f.setKeyGUID(keyGUID);
        f.setCleartextVariable(cleartextVariable);
        return f;
    }

    static EncryptByPassPhrase f_encryptbypassphrase(
            StringConstant passphrase,
            StringConstant cleartext,
            Expression addAuthenticator,
            Expression authenticator){
        EncryptByPassPhrase f = new EncryptByPassPhrase();
        f.setPassphrase(passphrase);
        f.setCleartext(cleartext);
        f.setAddAuthenticator(addAuthenticator);
        f.setAuthenticator(authenticator);
        return f;
    }
    static EncryptByPassPhrase f_encryptbypassphrase(
            StringConstant passphrase,
            StringConstant cleartext,
            Expression addAuthenticator,
            LocalVariable authenticatorVariable){
        EncryptByPassPhrase f = new EncryptByPassPhrase();
        f.setPassphrase(passphrase);
        f.setCleartext(cleartext);
        f.setAddAuthenticator(addAuthenticator);
        f.setAuthenticatorVariable(authenticatorVariable);
        return f;
    }
    static EncryptByPassPhrase f_encryptbypassphrase(
            StringConstant passphrase,
            StringConstant cleartext,
            LocalVariable addAuthenticatorVariable,
            Expression authenticator){
        EncryptByPassPhrase f = new EncryptByPassPhrase();
        f.setPassphrase(passphrase);
        f.setCleartext(cleartext);
        f.setAddAuthenticatorVariable(addAuthenticatorVariable);
        f.setAuthenticator(authenticator);
        return f;
    }
    static EncryptByPassPhrase f_encryptbypassphrase(
            StringConstant passphrase,
            StringConstant cleartext,
            LocalVariable addAuthenticatorVariable,
            LocalVariable authenticatorVariable){
        EncryptByPassPhrase f = new EncryptByPassPhrase();
        f.setPassphrase(passphrase);
        f.setCleartext(cleartext);
        f.setAddAuthenticatorVariable(addAuthenticatorVariable);
        f.setAuthenticatorVariable(authenticatorVariable);
        return f;
    }
    static EncryptByPassPhrase f_encryptbypassphrase(
            StringConstant passphrase,
            StringConstant cleartext){
        EncryptByPassPhrase f = new EncryptByPassPhrase();
        f.setPassphrase(passphrase);
        f.setCleartext(cleartext);
        return f;
    }
    static EncryptByPassPhrase f_encryptbypassphrase(
            StringConstant passphrase,
            LocalVariable cleartextVariable,
            Expression addAuthenticator,
            Expression authenticator){
        EncryptByPassPhrase f = new EncryptByPassPhrase();
        f.setPassphrase(passphrase);
        f.setCleartextVariable(cleartextVariable);
        f.setAddAuthenticator(addAuthenticator);
        f.setAuthenticator(authenticator);
        return f;
    }
    static EncryptByPassPhrase f_encryptbypassphrase(
            StringConstant passphrase,
            LocalVariable cleartextVariable,
            Expression addAuthenticator,
            LocalVariable authenticatorVariable){
        EncryptByPassPhrase f = new EncryptByPassPhrase();
        f.setPassphrase(passphrase);
        f.setCleartextVariable(cleartextVariable);
        f.setAddAuthenticator(addAuthenticator);
        f.setAuthenticatorVariable(authenticatorVariable);
        return f;
    }
    static EncryptByPassPhrase f_encryptbypassphrase(
            StringConstant passphrase,
            LocalVariable cleartextVariable,
            LocalVariable addAuthenticatorVariable,
            Expression authenticator){
        EncryptByPassPhrase f = new EncryptByPassPhrase();
        f.setPassphrase(passphrase);
        f.setCleartextVariable(cleartextVariable);
        f.setAddAuthenticatorVariable(addAuthenticatorVariable);
        f.setAuthenticator(authenticator);
        return f;
    }
    static EncryptByPassPhrase f_encryptbypassphrase(
            StringConstant passphrase,
            LocalVariable cleartextVariable,
            LocalVariable addAuthenticatorVariable,
            LocalVariable authenticatorVariable){
        EncryptByPassPhrase f = new EncryptByPassPhrase();
        f.setPassphrase(passphrase);
        f.setCleartextVariable(cleartextVariable);
        f.setAddAuthenticatorVariable(addAuthenticatorVariable);
        f.setAuthenticatorVariable(authenticatorVariable);
        return f;
    }
    static EncryptByPassPhrase f_encryptbypassphrase(
            StringConstant passphrase,
            LocalVariable cleartextVariable){
        EncryptByPassPhrase f = new EncryptByPassPhrase();
        f.setPassphrase(passphrase);
        f.setCleartextVariable(cleartextVariable);
        return f;
    }
    static EncryptByPassPhrase f_encryptbypassphrase(
            LocalVariable passphraseVariable,
            StringConstant cleartext,
            Expression addAuthenticator,
            Expression authenticator){
        EncryptByPassPhrase f = new EncryptByPassPhrase();
        f.setPassphraseVariable(passphraseVariable);
        f.setCleartext(cleartext);
        f.setAddAuthenticator(addAuthenticator);
        f.setAuthenticator(authenticator);
        return f;
    }
    static EncryptByPassPhrase f_encryptbypassphrase(
            LocalVariable passphraseVariable,
            StringConstant cleartext,
            Expression addAuthenticator,
            LocalVariable authenticatorVariable){
        EncryptByPassPhrase f = new EncryptByPassPhrase();
        f.setPassphraseVariable(passphraseVariable);
        f.setCleartext(cleartext);
        f.setAddAuthenticator(addAuthenticator);
        f.setAuthenticatorVariable(authenticatorVariable);
        return f;
    }
    static EncryptByPassPhrase f_encryptbypassphrase(
            LocalVariable passphraseVariable,
            StringConstant cleartext,
            LocalVariable addAuthenticatorVariable,
            Expression authenticator){
        EncryptByPassPhrase f = new EncryptByPassPhrase();
        f.setPassphraseVariable(passphraseVariable);
        f.setCleartext(cleartext);
        f.setAddAuthenticatorVariable(addAuthenticatorVariable);
        f.setAuthenticator(authenticator);
        return f;
    }
    static EncryptByPassPhrase f_encryptbypassphrase(
            LocalVariable passphraseVariable,
            StringConstant cleartext,
            LocalVariable addAuthenticatorVariable,
            LocalVariable authenticatorVariable){
        EncryptByPassPhrase f = new EncryptByPassPhrase();
        f.setPassphraseVariable(passphraseVariable);
        f.setCleartext(cleartext);
        f.setAddAuthenticatorVariable(addAuthenticatorVariable);
        f.setAuthenticatorVariable(authenticatorVariable);
        return f;
    }
    static EncryptByPassPhrase f_encryptbypassphrase(
            LocalVariable passphraseVariable,
            LocalVariable cleartextVariable,
            Expression addAuthenticator,
            Expression authenticator){
        EncryptByPassPhrase f = new EncryptByPassPhrase();
        f.setPassphraseVariable(passphraseVariable);
        f.setCleartextVariable(cleartextVariable);
        f.setAddAuthenticator(addAuthenticator);
        f.setAuthenticator(authenticator);
        return f;
    }
    static EncryptByPassPhrase f_encryptbypassphrase(
            LocalVariable passphraseVariable,
            LocalVariable cleartextVariable,
            Expression addAuthenticator,
            LocalVariable authenticatorVariable){
        EncryptByPassPhrase f = new EncryptByPassPhrase();
        f.setPassphraseVariable(passphraseVariable);
        f.setCleartextVariable(cleartextVariable);
        f.setAddAuthenticator(addAuthenticator);
        f.setAuthenticatorVariable(authenticatorVariable);
        return f;
    }
    static EncryptByPassPhrase f_encryptbypassphrase(
            LocalVariable passphraseVariable,
            LocalVariable cleartextVariable,
            LocalVariable addAuthenticatorVariable,
            Expression authenticator){
        EncryptByPassPhrase f = new EncryptByPassPhrase();
        f.setPassphraseVariable(passphraseVariable);
        f.setCleartextVariable(cleartextVariable);
        f.setAddAuthenticatorVariable(addAuthenticatorVariable);
        f.setAuthenticator(authenticator);
        return f;
    }
    static EncryptByPassPhrase f_encryptbypassphrase(
            LocalVariable passphraseVariable,
            LocalVariable cleartextVariable,
            LocalVariable addAuthenticatorVariable,
            LocalVariable authenticatorVariable){
        EncryptByPassPhrase f = new EncryptByPassPhrase();
        f.setPassphraseVariable(passphraseVariable);
        f.setCleartextVariable(cleartextVariable);
        f.setAddAuthenticatorVariable(addAuthenticatorVariable);
        f.setAuthenticatorVariable(authenticatorVariable);
        return f;
    }

    static HashBytes f_hashbytes(
            HashBytes.Algorithm algorithm,
            Expression input){
        HashBytes f = new HashBytes();
        f.setAlgorithm(algorithm);
        f.setInput(input);
        return f;
    }
    static HashBytes f_hashbytes(
            HashBytes.Algorithm algorithm,
            LocalVariable inputVariable){
        HashBytes f = new HashBytes();
        f.setAlgorithm(algorithm);
        f.setInputVariable(inputVariable);
        return f;
    }

    static Is_ObjectSigned f_is_objectsigned(
            Expression objectId,
            Expression clazz,
            Expression thumbprint){
        Is_ObjectSigned f = new Is_ObjectSigned();
        f.setObjectId(objectId);
        f.setClazz(clazz);
        f.setThumbprint(thumbprint);
        return f;
    }
    static Key_GUID f_key_guid(
            StringConstant keyName){
        Key_GUID f = new Key_GUID();
        f.setKeyName(keyName);
        return f;
    }
    static Key_Id f_key_id(
            StringConstant keyName){
        Key_Id f = new Key_Id();
        f.setKeyName(keyName);
        return f;
    }
    static Key_Name f_key_name(
            Expression ciphertext){
        Key_Name f = new Key_Name();
        f.setCiphertext(ciphertext);
        return f;
    }
    //todo
    //    static Key_Name f_key_name(
    //            Expression ciphertext){
    //        Key_Name f = new Key_Name();
    //        f.setCiphertext(ciphertext);
    //        return f;
    //    }

    static SignByAsymKey f_signbyasymkey(
            Expression asymKeyId,
            LocalVariable plaintext,
            Expression password){
        SignByAsymKey f = new SignByAsymKey();
        f.setAsymKeyId(asymKeyId);
        f.setPlaintext(plaintext);
        f.setPassword(password);
        return f;
    }
    static SignByAsymKey f_signbyasymkey(
            Expression asymKeyId,
            LocalVariable plaintext){
        SignByAsymKey f = new SignByAsymKey();
        f.setAsymKeyId(asymKeyId);
        f.setPlaintext(plaintext);
        return f;
    }

    static SignByCert f_signbycert(
            Expression certificateId,
            LocalVariable cleartext,
            Expression password){
        SignByCert f = new SignByCert();
        f.setCertificateId(certificateId);
        f.setCleartext(cleartext);
        f.setPassword(password);
        return f;
    }
    static SignByCert f_signbycert(
            Expression certificateId,
            LocalVariable cleartext){
        SignByCert f = new SignByCert();
        f.setCertificateId(certificateId);
        f.setCleartext(cleartext);
        return f;
    }

    static SymKeyProperty f_symkeyproperty(
            Expression keyId,
            AsymKeyProperty.Properties properties){
        SymKeyProperty f = new SymKeyProperty();
        f.setKeyId(keyId);
        f.setProperties(properties);
        return f;
    }

    static VerifySignedByAsymKey f_verifysignedbyasymkey(
            Expression asymKeyId,
            LocalVariable cleartext,
            Expression signature){
        VerifySignedByAsymKey f = new VerifySignedByAsymKey();
        f.setAsymKeyId(asymKeyId);
        f.setCleartext(cleartext);
        f.setSignature(signature);
        return f;
    }
    static VerifySignedByCert f_verifysignedbycert(
            Expression certId,
            LocalVariable signedData,
            Expression signature){
        VerifySignedByCert f = new VerifySignedByCert();
        f.setCertId(certId);
        f.setSignedData(signedData);
        f.setSignature(signature);
        return f;
    }

}
