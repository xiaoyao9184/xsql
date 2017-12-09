package com.xy.xsql.entity.model.definition;

/**
 * Created by xiaoyao9184 on 2017/9/22.
 */
public class RelationshipClass<L,R> {
    public final Class<L> leftClass;
    public final Class<R> rightClass;
    private final boolean inverted;

    public RelationshipClass(Class<L> leftClass, Class<R> rightClass) {
        this(leftClass, rightClass, false);
    }

    public RelationshipClass(Class<L> leftClass, Class<R> rightClass, boolean inverted) {
        this.leftClass = leftClass;
        this.rightClass = rightClass;
        this.inverted = inverted;
    }

    public RelationshipClass<R, L> invert() {
        return new RelationshipClass<>(rightClass, leftClass, true);
    }

    public Class<L> getLeftClass() {
        return leftClass;
    }

    public Class<R> getRightClass() {
        return rightClass;
    }

    public boolean isInverted() {
        return inverted;
    }

    public static <L,R> RelationshipClass<L,R> relationship(Class<L> leftClass, Class<R> rightClass) {
        return new RelationshipClass<>(leftClass, rightClass);
    }
}
