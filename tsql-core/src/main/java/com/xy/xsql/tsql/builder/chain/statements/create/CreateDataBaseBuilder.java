package com.xy.xsql.tsql.builder.chain.statements.create;

import com.xy.xsql.core.builder.CodeBuilder;
import com.xy.xsql.tsql.model.statements.create.CreateDataBase;

/**
 * CreateDataBaseBuilder
 * Created by xiaoyao9184 on 2017/8/3.
 */
@SuppressWarnings("unused")
public class CreateDataBaseBuilder extends CodeBuilder<CreateDataBase> {

    public CreateDataBaseBuilder(CreateDataBase tar) {
        super(tar);
    }

    public CreateDataBaseBuilder() {
        super(new CreateDataBase());
    }

    /**
     * set
     * @param dbName db name
     * @return THIS
     */
    public CreateDataBaseBuilder withDBName(String dbName){
        target.setDatabaseName(dbName);
        return this;
    }

    /**
     * set
     * @return THIS
     */
    public CreateDataBaseBuilder withContainmentNone(){
        target.setUseContainmentNone(true);
        return this;
    }

    /**
     * set
     * @return THIS
     */
    public CreateDataBaseBuilder withContainmentPartial(){
        target.setUseContainmentPartial(true);
        return this;
    }




    /*
    Quick
     */

    /**
     * Quick set
     * @param name name
     * @return THIS
     */
    public CreateDataBaseBuilder $(String name) {
        return withDBName(name);
    }

}
