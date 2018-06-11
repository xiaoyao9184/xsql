package com.xy.xsql.tsql.builder.chain.functions.cryptographic;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.variables.LocalVariable;
import com.xy.xsql.tsql.model.functions.cryptographic.Is_ObjectSigned;
import com.xy.xsql.tsql.model.functions.metadata.Object_Id;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_variable;
import static com.xy.xsql.tsql.builder.chain.functions.CryptographicFunctions.f_is_objectsigned;
import static com.xy.xsql.tsql.builder.chain.functions.MetaDataFunctions.f_object_id;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class IsObjectSignedFunctionTest {


    /**
     * IS_OBJECTSIGNED(
     'OBJECT', OBJECT_ID(@objectname), 'certificate', @thumbprint
     )
     */
    public Is_ObjectSigned exampleA = f_is_objectsigned(
            f_object_id(e_variable("objectname")),
            c_string("certificate"),
            e_variable("thumbprint")
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getObjectId().getClass(), Object_Id.class);
        assertEquals(exampleA.getClazz().getClass(), StringConstant.class);
        assertEquals(exampleA.getThumbprint().getClass(), LocalVariable.class);
    }

}