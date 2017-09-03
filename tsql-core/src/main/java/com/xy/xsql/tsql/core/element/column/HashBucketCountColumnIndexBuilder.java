package com.xy.xsql.tsql.core.element.column;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.element.column.ColumnIndex;

/**
 * Use
 * in --Memory optimized CREATE TABLE Syntax
 * Created by xiaoyao9184 on 2017/8/18.
 * @param <ParentBuilder>
 */
public class HashBucketCountColumnIndexBuilder<ParentBuilder>
        extends CodeTreeBuilder<HashBucketCountColumnIndexBuilder<ParentBuilder>,ParentBuilder,ColumnIndex> {

    public HashBucketCountColumnIndexBuilder(ColumnIndex columnIndex) {
        super(columnIndex);
    }

    public HashBucketCountColumnIndexBuilder() {
        super(new ColumnIndex());
    }

    public HashBucketCountColumnIndexBuilder<ParentBuilder> withIndexName(String indexName) {
        target.setIndexName(indexName);
        return this;
    }

    public HashBucketCountColumnIndexBuilder<ParentBuilder> withUseClustered() {
        target.setUseClustered(false);
        return this;
    }

    public HashBucketCountColumnIndexBuilder<ParentBuilder> withBucketCount(Integer bucketCount) {
        target.setBucketCount(bucketCount);
        return this;
    }


    /*
    Quick
     */

    /**
     * Quick set
     * @param indexName indexName
     * @return THIS
     */
    public HashBucketCountColumnIndexBuilder<ParentBuilder> $INDEX(String indexName){
        return withIndexName(indexName);
    }

    /**
     * Quick set
     * @return THIS
     */
    public HashBucketCountColumnIndexBuilder<ParentBuilder> $NONCLUSTERED(){
        return withUseClustered();
    }

    /**
     * Quick set
     * @param bucketCount bucketCount
     * @return THIS
     */
    public HashBucketCountColumnIndexBuilder<ParentBuilder> $HASH_WITH_BUCKET_COUNT(Integer bucketCount){
        return withBucketCount(bucketCount);
    }

}
