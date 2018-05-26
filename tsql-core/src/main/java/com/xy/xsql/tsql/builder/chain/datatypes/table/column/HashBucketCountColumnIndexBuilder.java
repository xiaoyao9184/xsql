package com.xy.xsql.tsql.builder.chain.datatypes.table.column;

import com.xy.xsql.core.builder.parent.ParentHoldBuilder;
import com.xy.xsql.tsql.model.datatypes.table.column.ColumnIndex;

/**
 * HashBucketCountColumnIndexBuilder
 * Use
 * in --Memory optimized CREATE TABLE Syntax
 * Created by xiaoyao9184 on 2017/8/18.
 * @param <ParentBuilder>
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class HashBucketCountColumnIndexBuilder<ParentBuilder>
        extends ParentHoldBuilder<HashBucketCountColumnIndexBuilder<ParentBuilder>,ParentBuilder,ColumnIndex> {

    public HashBucketCountColumnIndexBuilder() {
        super(new ColumnIndex());
    }

    public HashBucketCountColumnIndexBuilder(ColumnIndex target) {
        super(target);
    }

    /**
     * set
     * @param indexName index name
     * @return THIS
     */
    public HashBucketCountColumnIndexBuilder<ParentBuilder> withIndexName(String indexName) {
        target.setIndexName(indexName);
        return this;
    }

    /**
     * set
     * @return THIS
     */
    public HashBucketCountColumnIndexBuilder<ParentBuilder> withUseClustered() {
        target.setUseClustered(false);
        return this;
    }

    /**
     * set
     * @param bucketCount bucket count
     * @return THIS
     */
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
    public HashBucketCountColumnIndexBuilder<ParentBuilder> $Index(String indexName){
        return withIndexName(indexName);
    }

    /**
     * Quick set
     * @return THIS
     */
    public HashBucketCountColumnIndexBuilder<ParentBuilder> $Nonclustered(){
        return withUseClustered();
    }

    /**
     * Quick set
     * @param bucketCount bucket count
     * @return THIS
     */
    public HashBucketCountColumnIndexBuilder<ParentBuilder> $HashWithBucketCount(Integer bucketCount){
        return withBucketCount(bucketCount);
    }

}
