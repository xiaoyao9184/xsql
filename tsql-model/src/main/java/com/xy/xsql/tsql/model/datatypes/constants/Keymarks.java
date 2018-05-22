package com.xy.xsql.tsql.model.datatypes.constants;

/**
 * Created by xiaoyao9184 on 2018/5/22.
 */
public enum Keymarks {

    quotation('\''),
    negative('-'),
    positive('+'),
    N_identifier('N');

    private String string;

    Keymarks(){
        this.string = this.name();
    }
    Keymarks(String string){
        this.string = string;
    }
    Keymarks(char c){
        this.string = Character.toString(c);
    }

    @Override
    public String toString(){
        return string;
    }
}
