package com.xy.xsql.block.exception;

import com.xy.xsql.block.model.BlockMeta;

/**
 * Created by xiaoyao9184 on 2017/6/27.
 */
public class MetaException
        extends Exception {

    private BlockMeta meta;
    private Type type;

    public MetaException(String message){
        super(message);
        this.type = Type.UNKNOWN;
    }

    public MetaException(Type type){
        super(type.name());
        this.type = type;
    }

    public MetaException(BlockMeta meta, Type type){
        super(type.name());
        this.type = type;
        this.meta = meta;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + ": " + type.name();
    }


    public enum Type {
        UNKNOWN,
        OTHER,
        MISS_META,
        MISS_MODEL,

        MISS_OPTION_PREDICATE,

        NOT_SAME_EXCLUSIVE_META_PREDICATE,
        NOTHING_PASS_EXCLUSIVE,

        EMPTY_COLLECTION_SCOPE,
        SCOPE_NOT_COLLECTION,

        MISS_REFERENCE_META;

    }

    public static RuntimeException miss_meta(){
        return new RuntimeException(new MetaException(Type.MISS_META));
    }

    public static RuntimeException miss_model(BlockMeta meta){
        return new RuntimeException(new MetaException(meta,Type.MISS_MODEL));
    }

    public static RuntimeException miss_option_predicate(BlockMeta meta){
        return new RuntimeException(new MetaException(meta,Type.MISS_OPTION_PREDICATE));
    }

    public static RuntimeException miss_reference_meta(BlockMeta meta){
        return new RuntimeException(new MetaException(meta,Type.MISS_REFERENCE_META));
    }

    public static RuntimeException not_same_exclusive_meta_and_predicate(BlockMeta meta){
        return new RuntimeException(new MetaException(meta,Type.NOT_SAME_EXCLUSIVE_META_PREDICATE));
    }

    public static RuntimeException nothing_pass_exclusive(BlockMeta meta){
        return new RuntimeException(new MetaException(meta,Type.NOTHING_PASS_EXCLUSIVE));
    }

    public static RuntimeException collection_meta_not_single(BlockMeta meta){
        return new RuntimeException(new MetaException(meta,Type.SCOPE_NOT_COLLECTION));
    }

    public static RuntimeException collection_model_not_collection(BlockMeta meta) {
        return new RuntimeException(new MetaException(meta,Type.SCOPE_NOT_COLLECTION));
    }

    public static RuntimeException data_collection_empty(BlockMeta meta) {
        return new RuntimeException(new MetaException(meta,Type.EMPTY_COLLECTION_SCOPE));
    }

}
