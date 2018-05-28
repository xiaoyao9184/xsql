package com.xy.xsql.tsql.style.constructor.introducer.queries.hints;

import com.xy.xsql.tsql.style.constructor.builder.queries.hints.b$table_hint;

/**
 * Created by xiaoyao9184 on 2018/5/9.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public interface table_hint {

    static b$table_hint NOEXPAND_INDEX(String... index_value){
        return new b$table_hint(){
            {
                this.target = $Index(index_value);
                withNOEXPAND();
            }
        };
    }
    static b$table_hint NOEXPAND_FORCESEEK(String index_value, String... index_column_name){
        return new b$table_hint(){
            {
                this.target = $Forceseek(index_value, index_column_name);
                withNOEXPAND();
            }
        };
    }
    static b$table_hint NOEXPAND_FORCESCAN(){
        return new b$table_hint(){
            {
                this.target = $Forcescan();
                withNOEXPAND();
            }
        };
    }
    static b$table_hint NOEXPAND_FORCESEEK(){
        return new b$table_hint(){
            {
                this.target = $Forceseek();
                withNOEXPAND();
            }
        };
    }
    static b$table_hint NOEXPAND_HOLDLOCK(){
        return new b$table_hint(){
            {
                this.target = $Holdlock();
                withNOEXPAND();
            }
        };
    }
    static b$table_hint NOEXPAND_NOLOCK(){
        return new b$table_hint(){
            {
                this.target = $Nolock();
                withNOEXPAND();
            }
        };
    }
    static b$table_hint NOEXPAND_NOWAIT(){
        return new b$table_hint(){
            {
                this.target = $Nowait();
                withNOEXPAND();
            }
        };
    }
    static b$table_hint NOEXPAND_PAGLOCK(){
        return new b$table_hint(){
            {
                this.target = $Paglock();
                withNOEXPAND();
            }
        };
    }
    static b$table_hint NOEXPAND_READCOMMITTED(){
        return new b$table_hint(){
            {
                this.target = $Readcommitted();
                withNOEXPAND();
            }
        };
    }
    static b$table_hint NOEXPAND_READCOMMITTEDLOCK(){
        return new b$table_hint(){
            {
                this.target = $Readcommittedlock();
                withNOEXPAND();
            }
        };
    }
    static b$table_hint NOEXPAND_READPAST(){
        return new b$table_hint(){
            {
                this.target = $Readpast();
                withNOEXPAND();
            }
        };
    }
    static b$table_hint NOEXPAND_READUNCOMMITTED(){
        return new b$table_hint(){
            {
                this.target = $Readuncommitted();
                withNOEXPAND();
            }
        };
    }
    static b$table_hint NOEXPAND_REPEATABLEREAD(){
        return new b$table_hint(){
            {
                this.target = $Repeatableread();
                withNOEXPAND();
            }
        };
    }
    static b$table_hint NOEXPAND_ROWLOCK(){
        return new b$table_hint(){
            {
                this.target = $Rowlock();
                withNOEXPAND();
            }
        };
    }
    static b$table_hint NOEXPAND_SERIALIZABLE(){
        return new b$table_hint(){
            {
                this.target = $Serializable();
                withNOEXPAND();
            }
        };
    }
    static b$table_hint NOEXPAND_SNAPSHOT(){
        return new b$table_hint(){
            {
                this.target = $Snapshot();
                withNOEXPAND();
            }
        };
    }
    static b$table_hint NOEXPAND_SPATIAL_WINDOW_MAX_CELLS(Integer integer){
        return new b$table_hint(){
            {
                this.target = $SpatialWindowMaxCells(integer);
                withNOEXPAND();
            }
        };
    }
    static b$table_hint NOEXPAND_TABLOCK(){
        return new b$table_hint(){
            {
                this.target = $Tablock();
                withNOEXPAND();
            }
        };
    }
    static b$table_hint NOEXPAND_TABLOCKX(){
        return new b$table_hint(){
            {
                this.target = $Tablockx();
                withNOEXPAND();
            }
        };
    }
    static b$table_hint NOEXPAND_UPDLOCK(){
        return new b$table_hint(){
            {
                this.target = $Updlock();
                withNOEXPAND();
            }
        };
    }
    static b$table_hint NOEXPAND_XLOCK(){
        return new b$table_hint(){
            {
                this.target = $Xlock();
                withNOEXPAND();
            }
        };
    }



    static b$table_hint INDEX(String... index_value){
        return new b$table_hint(){
            {
                this.target = $Index(index_value);
            }
        };
    }
    static b$table_hint FORCESEEK(String index_value, String... index_column_name){
        return new b$table_hint(){
            {
                this.target = $Forceseek(index_value, index_column_name);
            }
        };
    }
    static b$table_hint FORCESCAN(){
        return new b$table_hint(){
            {
                this.target = $Forcescan();
            }
        };
    }
    static b$table_hint FORCESEEK(){
        return new b$table_hint(){
            {
                this.target = $Forceseek();
            }
        };
    }
    static b$table_hint HOLDLOCK(){
        return new b$table_hint(){
            {
                this.target = $Holdlock();
            }
        };
    }
    static b$table_hint NOLOCK(){
        return new b$table_hint(){
            {
                this.target = $Nolock();
            }
        };
    }
    static b$table_hint NOWAIT(){
        return new b$table_hint(){
            {
                this.target = $Nowait();
            }
        };
    }
    static b$table_hint PAGLOCK(){
        return new b$table_hint(){
            {
                this.target = $Paglock();
            }
        };
    }
    static b$table_hint READCOMMITTED(){
        return new b$table_hint(){
            {
                this.target = $Readcommitted();
            }
        };
    }
    static b$table_hint READCOMMITTEDLOCK(){
        return new b$table_hint(){
            {
                this.target = $Readcommittedlock();
            }
        };
    }
    static b$table_hint READPAST(){
        return new b$table_hint(){
            {
                this.target = $Readpast();
            }
        };
    }
    static b$table_hint READUNCOMMITTED(){
        return new b$table_hint(){
            {
                this.target = $Readuncommitted();
            }
        };
    }
    static b$table_hint REPEATABLEREAD(){
        return new b$table_hint(){
            {
                this.target = $Repeatableread();
            }
        };
    }
    static b$table_hint ROWLOCK(){
        return new b$table_hint(){
            {
                this.target = $Rowlock();
            }
        };
    }
    static b$table_hint SERIALIZABLE(){
        return new b$table_hint(){
            {
                this.target = $Serializable();
            }
        };
    }
    static b$table_hint SNAPSHOT(){
        return new b$table_hint(){
            {
                this.target = $Snapshot();
            }
        };
    }
    static b$table_hint SPATIAL_WINDOW_MAX_CELLS(Integer integer){
        return new b$table_hint(){
            {
                this.target = $SpatialWindowMaxCells(integer);
            }
        };
    }
    static b$table_hint TABLOCK(){
        return new b$table_hint(){
            {
                this.target = $Tablock();
            }
        };
    }
    static b$table_hint TABLOCKX(){
        return new b$table_hint(){
            {
                this.target = $Tablockx();
            }
        };
    }
    static b$table_hint UPDLOCK(){
        return new b$table_hint(){
            {
                this.target = $Updlock();
            }
        };
    }
    static b$table_hint XLOCK(){
        return new b$table_hint(){
            {
                this.target = $Xlock();
            }
        };
    }
}
