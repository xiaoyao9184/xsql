package com.xy.xsql.tsql.builder.chain.statements;

import com.xy.xsql.core.builder.CodeBuilder;
import com.xy.xsql.tsql.model.statements.create.CreateDataBase;

/**
 * Created by xiaoyao9184 on 2017/8/3.
 */
public class CreateDataBaseBuilder extends CodeBuilder<CreateDataBase> {

    public CreateDataBaseBuilder(CreateDataBase tar) {
        super(tar);
    }

    public CreateDataBaseBuilder() {
        super(new CreateDataBase());
    }

    public CreateDataBaseBuilder withDBName(String dbName){
        target.setDatabaseName(dbName);
        return this;
    }

    public CreateDataBaseBuilder withContainmentNone(){
        target.setUseContainmentNone(true);
        return this;
    }

    public CreateDataBaseBuilder withContainmentPartial(){
        target.setUseContainmentPartial(true);
        return this;
    }



    public CreateDataBaseBuilder $(String name) {
        return withDBName(name);
    }

    /*
    Quick build
     */

    /**
     * Quick build Create DataBase
     * @param name the last is new name
     * @return
     */
    public static CreateDataBase CREATE_DATABASE(String name){
        return new CreateDataBaseBuilder()
                .withDBName(name)
                .build();
    }
}
