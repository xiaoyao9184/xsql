package com.xy.xsql.entity.core.meta.sql;

import com.xy.xsql.core.builder.CodeBuilder;
import com.xy.xsql.core.lambda.Configurator;
import com.xy.xsql.entity.api.meta.ColumnMeta;
import com.xy.xsql.entity.api.meta.TableMeta;
import com.xy.xsql.entity.core.meta.ProxyObjectMethodRecorder;
import com.xy.xsql.entity.core.meta.SimpleColumnMetaHolder;
import com.xy.xsql.entity.core.meta.SimpleTableMetaManager;
import com.xy.xsql.entity.core.meta.column.ColumnCollectionIndexer;
import com.xy.xsql.entity.core.meta.column.ColumnMethodIndexer;
import com.xy.xsql.entity.core.meta.column.ColumnNameIndexer;
import com.xy.xsql.entity.core.meta.column.ColumnNumberIndexer;
import com.xy.xsql.entity.core.meta.table.TableClassIndexer;
import com.xy.xsql.entity.core.meta.table.TableRelationshipClassIndexer;
import com.xy.xsql.entity.model.definition.NoColumnMethodDefFoundError;
import com.xy.xsql.entity.model.definition.NoTableClassDefFoundError;
import com.xy.xsql.entity.model.sql.*;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.xy.xsql.core.FiledBuilder.initSet2;
import static com.xy.xsql.core.ListBuilder.*;

/**
 * Created by xiaoyao9184 on 2017/10/19.
 */
public class EntityQueryBuilder<Entity> extends CodeBuilder<EntityQuery> {

    //temp
    private List<ColumnReturn> columnReturns;
    private List<TableJoin> tableJoins;
    private List<EntityCondition> entityConditions;
    private List<ColumnOrder> columnOrders;

    private Class<Entity> entity;
    private ProxyObjectMethodRecorder<Entity> recorder;
    private Optional<Runnable> afterColumnCondition;
    private Configurator<EntityQueryBuilder> configurator;

    private ColumnCollectionIndexer<? extends ColumnMeta> columnCollectionIndexer;
    private ColumnNumberIndexer<? extends ColumnMeta> columnNumberIndexer;
    private ColumnMethodIndexer<? extends ColumnMeta> columnMethodIndexer;
    private ColumnNameIndexer<? extends ColumnMeta> columnNameIndexer;
    private TableClassIndexer<? extends TableMeta> tableClassIndexer;
    private TableRelationshipClassIndexer<? extends TableMeta> tableRelationshipClassIndexer;


    public EntityQueryBuilder(Class<Entity> entity) {
        super(new EntityQuery());
        this.entity = entity;
        this.recorder = ProxyObjectMethodRecorder.create(entity);
        this.afterColumnCondition = Optional.empty();
        this.configurator = EntityQueryBuilder::simpleConfig;
        this.configurator.config(this);
    }

    @Deprecated
    public EntityQueryBuilder(EntityQuery entityQuery,Class<Entity> entity) {
        super(entityQuery);
        this.entity = entity;
        this.recorder = ProxyObjectMethodRecorder.create(entity);
        this.afterColumnCondition = Optional.empty();
    }

    public EntityQueryBuilder(EntityQueryBuilder<?> prototype, Class<Entity> entity) {
        super(prototype.build());
        this.entity = entity;
        this.recorder = ProxyObjectMethodRecorder.create(entity);
        this.afterColumnCondition = Optional.empty();

        this.configurator = prototype.configurator;
        this.configurator.config(this);
//
//
//        this.tableClassIndexer = prototype.tableClassIndexer;
//        this.tableRelationshipClassIndexer = prototype.tableRelationshipClassIndexer;
//        this.columnCollectionIndexer = prototype.columnCollectionIndexer;
//        this.columnNumberIndexer = prototype.columnNumberIndexer;
//        this.columnNameIndexer = prototype.columnNameIndexer;
//        this.columnMethodIndexer = prototype.columnMethodIndexer;
    }


    private List<ColumnReturn> getColumnReturns() {
        return columnReturns;
    }

    private void setColumnReturns(List<ColumnReturn> columnReturns) {
        this.columnReturns = columnReturns;
    }

    private List<TableJoin> getTableJoins() {
        return tableJoins;
    }

    private void setTableJoins(List<TableJoin> tableJoins) {
        this.tableJoins = tableJoins;
    }

    private List<EntityCondition> getEntityConditions() {
        return entityConditions;
    }

    private void setEntityConditions(List<EntityCondition> entityConditions) {
        this.entityConditions = entityConditions;
    }

    private List<ColumnOrder> getColumnOrders() {
        return columnOrders;
    }

    private void setColumnOrders(List<ColumnOrder> columnOrders) {
        this.columnOrders = columnOrders;
    }

    /**
     * Select all column to return
     * @return THIS
     */
    public EntityQueryBuilder<Entity> columnAll(){
        columnReturns = columnCollectionIndexer.column().stream()
                .map((Function<ColumnMeta, ColumnReturn>) ColumnReturn::new)
                .collect(Collectors.toList());
        return this;
    }

    /**
     * Select return column by condition
     * @return THIS
     */
    public EntityQueryBuilder<Entity> columnByCondition(){
        if(this.entityConditions == null){
            this.afterColumnCondition = Optional.of(this::columnCondition2Return);
        }else{
            columnCondition2Return();
        }
        return this;
    }

    /**
     * Select return column by name regex
     * @param regex regex
     * @return THIS
     */
    public EntityQueryBuilder<Entity> columnByRegex(Pattern regex){
        columnReturns = columnCollectionIndexer.column().stream()
                .filter(c -> regex.matcher(c.getName()).find())
                .map((Function<ColumnMeta, ColumnReturn>) ColumnReturn::new)
                .collect(Collectors.toList());
        return this;
    }

    /**
     * Select the return column by index
     * @param index index
     * @return THIS
     */
    public EntityQueryBuilder<Entity> column(Integer index){
        return column(index,null);
    }

    /**
     * Select the return column by index
     * @param index index
     * @param returnName return name
     * @return THIS
     */
    public EntityQueryBuilder<Entity> column(Integer index, String returnName){
        ColumnMeta columnMeta = columnNumberIndexer.column(index)
                .orElseThrow(NoColumnMethodDefFoundError::new);
        initAdd(new ColumnReturn(columnMeta,returnName),
                this::getColumnReturns,
                this::setColumnReturns);
        return this;
    }

    /**
     * Select the return column by name
     * @param name name
     * @return THIS
     */
    public EntityQueryBuilder<Entity> column(String name){
        return column(name,null);
    }

    /**
     * Select the return column by name
     * @param name name
     * @param returnName return name
     * @return THIS
     */
    public EntityQueryBuilder<Entity> column(String name, String returnName){
        ColumnMeta columnMeta = columnNameIndexer.column(name)
                .orElseThrow(NoColumnMethodDefFoundError::new);
        initAdd(new ColumnReturn(columnMeta,returnName),
                this::getColumnReturns,
                this::setColumnReturns);
        return this;
    }

    /**
     * Select the return column by lambda
     * @param getter getter lambda
     * @return THIS
     */
    public EntityQueryBuilder<Entity> column(Function<Entity,?> getter){
        return column(getter,null);
    }

    /**
     * Select the return column by lambda
     * @param getter getter lambda
     * @param returnName return name
     * @return THIS
     */
    public EntityQueryBuilder<Entity> column(Function<Entity,?> getter, String returnName){
        columnMethodIndexer.column(recorder.record(getter).getMethod())
                .ifPresent(columnMeta ->
                        initAdd(new ColumnReturn(columnMeta,returnName),
                                this::getColumnReturns,
                                this::setColumnReturns)
                );
        return this;
    }


    /**
     *
     * @param joinEntityClass Class
     * @param <JoinEntity> JoinEntity
     * @return EntityJoinBuilder
     */
    public <JoinEntity> EntityJoinBuilder<Entity,JoinEntity> join(Class<JoinEntity> joinEntityClass) {
        TableMeta joinTableMete = tableClassIndexer.table(joinEntityClass)
                .orElseThrow(NoTableClassDefFoundError::new);

        return new EntityJoinBuilder<>
                (joinEntityClass, this::columnMetaByGetter)
                .enter(this,tableJoin -> initAdd(tableJoin,this::getTableJoins,this::setTableJoins))
                .join(JoinType.base)
                .table(joinTableMete);
    }

//    /**
//     *
//     * @param joinEntityRelationshipClass RelationshipClass
//     * @param <JoinEntity> JoinEntity
//     * @return EntityJoinBuilder
//     */
//    public <JoinEntity> EntityJoinBuilder<Entity,JoinEntity> join(RelationshipClass<Entity,JoinEntity> joinEntityRelationshipClass) {
//        TableMeta joinTableMete = tableRelationshipClassIndexer.table(joinEntityRelationshipClass)
//                .orElseThrow(NoEntityDefFoundError::new);
//
//        return new EntityJoinBuilder<Entity,JoinEntity>
//                (joinEntityRelationshipClass, this::columnMetaByGetter, this::columnMetaByName)
//                .enter(this,tableJoin -> initAdd(tableJoin,this::getTableJoins,this::setTableJoins))
//                .join(JoinType.base)
//                .table(joinTableMete)
//                .columnGetter(this::columnMetaByGetter,this::columnMetaByName);
//    }

    /**
     * Prepare to create EntityCondition
     * select condition column by lambda
     * @param getter lambda
     * @param <ANY> any type
     * @return ColumnConditionBuilder
     */
    public <ANY> ColumnConditionBuilder<Entity,EntityQueryBuilder<Entity>> where(Function<Entity,ANY> getter){
        ColumnMeta columnMeta = columnMetaByGetter(getter);

        return new ColumnConditionBuilder<Entity,EntityQueryBuilder<Entity>>
                (initNew2(ColumnCondition::new,
                        this::getEntityConditions,
                        this::setEntityConditions))
                .in(this)
                .withColumnMeta(columnMeta);
    }

    /**
     * Prepare to create and EntityCondition
     * select condition column by lambda
     * @param getter lambda
     * @param <ANY> any type
     * @return ColumnConditionBuilder
     */
    public <ANY> ColumnConditionBuilder<Entity,EntityQueryBuilder<Entity>> and(Function<Entity,ANY> getter){
        return where(getter)
                .withAnd();
    }

    /**
     * Prepare to create or EntityCondition
     * select condition column by lambda
     * @param getter lambda
     * @param <ANY> any type
     * @return ColumnConditionBuilder
     */
    public <ANY> ColumnConditionBuilder<Entity,EntityQueryBuilder<Entity>> or(Function<Entity,ANY> getter){
        return where(getter)
                .withOr();
    }

    /**
     * Prepare to create complex EntityCondition
     * @return GroupConditionBuilder
     */
    public GroupConditionBuilder<Entity,EntityQueryBuilder<Entity>> where(){
        return new GroupConditionBuilder<Entity,EntityQueryBuilder<Entity>>
                (new GroupCondition(),this::columnMetaByGetter)
                .enter(this,ec -> initAdd(ec,this::getEntityConditions,this::setEntityConditions));
    }

    /**
     * Prepare to create and complex EntityCondition
     * @return GroupConditionBuilder
     */
    public GroupConditionBuilder<Entity,EntityQueryBuilder<Entity>> and() {
        return where()
                .withAnd();
    }

    /**
     * Prepare to create or complex EntityCondition
     * @return GroupConditionBuilder
     */
    public GroupConditionBuilder<Entity,EntityQueryBuilder<Entity>> or() {
        return where()
                .withOr();
    }

    /**
     * Create order
     * @param getter lambda
     * @param <ANY> ANY
     * @return THIS
     */
    public <ANY> EntityQueryBuilder<Entity> order(Function<Entity,ANY> getter) {
        ColumnMeta columnMeta = columnMetaByGetter(getter);
        initAdd(new ColumnOrder(columnMeta),
                this::getColumnOrders,
                this::setColumnOrders);
        return this;
    }

    /**
     * Create ase order
     * @param getter lambda
     * @param <ANY> ANY
     * @return THIS
     */
    public <ANY> EntityQueryBuilder<Entity> ase(Function<Entity,ANY> getter) {
        ColumnMeta columnMeta = columnMetaByGetter(getter);
        initAdd(new ColumnOrder(columnMeta,true),
                this::getColumnOrders,
                this::setColumnOrders);
        return this;
    }

    /**
     * Create desc order
     * @param getter lambda
     * @param <ANY> ANY
     * @return THIS
     */
    public <ANY> EntityQueryBuilder<Entity> desc(Function<Entity,ANY> getter) {
        ColumnMeta columnMeta = columnMetaByGetter(getter);
        initAdd(new ColumnOrder(columnMeta,false),
                this::getColumnOrders,
                this::setColumnOrders);
        return this;
    }


    @Override
    public EntityQuery build() {
        afterColumnCondition.ifPresent(Runnable::run);

        initSet2(() -> this.tableClassIndexer.table(entity)
                .orElseThrow(NoTableClassDefFoundError::new),
                target::getTableMain,
                target::setTableMain);

        initAdd(this.columnReturns,target::getColumnReturns,target::setColumnReturns);
        initAdd(this.entityConditions,target::getEntityConditions,target::setEntityConditions);
        initAdd(this.columnOrders,target::getColumnOrders,target::setColumnOrders);

        initAdd(this.tableJoins,target::getTableJoins,target::setTableJoins);

        return super.build();
    }

    /**
     * Merge ColumnCondition used column to ColumnReturn
     */
    private void columnCondition2Return(){
        List<ColumnReturn> columnReturns = this.entityConditions.stream()
                .flatMap(this::entityCondition2Column)
                .map(cc -> new ColumnReturn(cc.getColumnMeta()))
                .collect(Collectors.toList());

        initAdd2(
                this::getColumnReturns,
                this::setColumnReturns,
                columnReturns);
    }

    /**
     * Extract ColumnCondition Stream form EntityCondition
     * @param entityCondition EntityCondition
     * @return ColumnCondition Stream
     */
    private Stream<ColumnCondition> entityCondition2Column(EntityCondition entityCondition) {
        if(entityCondition instanceof GroupCondition){
            GroupCondition gc = (GroupCondition) entityCondition;
            List<EntityCondition> ecs = gc.getInternal();
            return ecs.stream()
                    .flatMap(this::entityCondition2Column);
        }else if(entityCondition instanceof ColumnCondition){
            ColumnCondition cc = (ColumnCondition) entityCondition;
            return Stream.of(cc);
        }
        return Stream.empty();
    }

    /**
     * Get ColumnMeta by getter
     * @param getter filed getter
     * @return ColumnMeta
     */
    private ColumnMeta columnMetaByGetter(Function<Entity,?> getter) {
        return columnMethodIndexer.column(recorder.record(getter).getMethod())
                .orElseThrow(NoColumnMethodDefFoundError::new);
    }

    /**
     * Get ColumnMeta by name
     * @param name name
     * @return ColumnMeta
     */
    public ColumnMeta columnMetaByName(String name) {
        return columnNameIndexer.column(name)
                .orElseThrow(() -> new NoColumnMethodDefFoundError(name));
    }

    /**
     * Create EntityQueryBuilder
     * @param entity Entity class
     * @param <Entity> Entity
     * @return EntityQueryBuilder
     */
    public static <Entity> EntityQueryBuilder<Entity> create(Class<Entity> entity) {
        EntityQueryBuilder<Entity> builder = new EntityQueryBuilder<>(entity);
        builder.tableClassIndexer = SimpleTableMetaManager.INSTANCE;
        builder.tableRelationshipClassIndexer = SimpleTableMetaManager.INSTANCE;

        SimpleColumnMetaHolder simpleColumnMetaHolder = new SimpleColumnMetaHolder(SimpleTableMetaManager.INSTANCE.table(entity).get());
        builder.columnCollectionIndexer = simpleColumnMetaHolder;
        builder.columnNumberIndexer = simpleColumnMetaHolder;
        builder.columnNameIndexer = simpleColumnMetaHolder;
        builder.columnMethodIndexer = simpleColumnMetaHolder;
        return builder;
    }

    /**
     * Create EntityQueryBuilder
     * @param entityClass Entity class
     * @param tableClassIndexer
     * @param tableRelationshipClassIndexer
     * @param columnCollectionIndexer
     * @param columnNumberIndexer
     * @param columnNameIndexer
     * @param columnMethodIndexer
     * @param <Entity> Entity
     * @return EntityQueryBuilder
     */
    @Deprecated
    public static <Entity> EntityQueryBuilder<Entity> create(
            Class<Entity> entityClass,
            TableClassIndexer<? extends TableMeta> tableClassIndexer,
            TableRelationshipClassIndexer<? extends TableMeta> tableRelationshipClassIndexer,
            ColumnCollectionIndexer<? extends ColumnMeta> columnCollectionIndexer,
            ColumnNumberIndexer<? extends ColumnMeta> columnNumberIndexer,
            ColumnNameIndexer<? extends ColumnMeta> columnNameIndexer,
            ColumnMethodIndexer<? extends ColumnMeta> columnMethodIndexer
            ) {
        EntityQueryBuilder<Entity> builder = new EntityQueryBuilder<>(entityClass);
        builder.tableClassIndexer = tableClassIndexer;
        builder.tableRelationshipClassIndexer = tableRelationshipClassIndexer;
        builder.columnCollectionIndexer = columnCollectionIndexer;
        builder.columnNumberIndexer = columnNumberIndexer;
        builder.columnNameIndexer = columnNameIndexer;
        builder.columnMethodIndexer = columnMethodIndexer;
        return builder;
    }


    public static <Entity> void simpleConfig(EntityQueryBuilder<Entity> builder) {
        builder.tableClassIndexer = SimpleTableMetaManager.INSTANCE;
        builder.tableRelationshipClassIndexer = SimpleTableMetaManager.INSTANCE;
        TableMeta tableMeta = SimpleTableMetaManager.INSTANCE.table(builder.entity)
                .orElseThrow(() -> new NoTableClassDefFoundError(builder.entity));
        SimpleColumnMetaHolder simpleColumnMetaHolder = new SimpleColumnMetaHolder(tableMeta);
        builder.columnCollectionIndexer = simpleColumnMetaHolder;
        builder.columnNumberIndexer = simpleColumnMetaHolder;
        builder.columnNameIndexer = simpleColumnMetaHolder;
        builder.columnMethodIndexer = simpleColumnMetaHolder;
    }

//    public static <Entity> EntityQueryBuilder<Entity> create(RelationshipClass<Entity,?> joinEntityRelationshipClass) {
//
//    }



}
