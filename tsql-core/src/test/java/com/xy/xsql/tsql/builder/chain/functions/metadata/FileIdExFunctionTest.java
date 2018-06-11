package com.xy.xsql.tsql.builder.chain.functions.metadata;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.metadata.File_IdEx;
import com.xy.xsql.tsql.model.queries.Select;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.TableNameFactory.t;
import static com.xy.xsql.tsql.builder.chain.functions.MetaDataFunctions.f_file_idex;
import static com.xy.xsql.tsql.builder.chain.queries.Queries.$Query;
import static com.xy.xsql.tsql.builder.chain.queries.predicates.Predicates.p_equal;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class FileIdExFunctionTest {


    /**
     * FILE_IDEX('AdventureWorks2012_Data')
     */
    public File_IdEx exampleA = f_file_idex(
            c_string("AdventureWorks2012_Data")
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getFileName().getClass(), StringConstant.class);
    }

    /**
     * FILE_IDEX((SELECT TOP (1) name FROM sys.database_files WHERE type = 1))
     */
    public File_IdEx exampleB = f_file_idex(
            $Query()
                .$Top()
                    .$(c_number(1))
                    .and()
                .$(c("name"))
                .$From()
                    .$(t("sys","database_files"))
                    .and()
                .$Where()
                    .$(p_equal(
                            c("type"),
                            c_number(1)
                    ))
                    .and()
                .build()
    );

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getFileName().getClass(), Select.QuerySpecification.class);
    }

    /**
     * FILE_IDEX((SELECT name FROM sys.master_files WHERE type = 4))
     */
    public File_IdEx exampleC = f_file_idex(
            $Query()
                .$(c("name"))
                .$From()
                    .$(t("sys","master_files"))
                    .and()
                .$Where()
                    .$(p_equal(
                            c("type"),
                            c_number(4)
                    ))
                    .and()
                .build()
    );

    @Test
    public void testExampleC(){
        assertEquals(exampleC.getFileName().getClass(), Select.QuerySpecification.class);
    }

}