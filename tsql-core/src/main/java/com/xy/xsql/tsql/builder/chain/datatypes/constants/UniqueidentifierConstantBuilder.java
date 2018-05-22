package com.xy.xsql.tsql.builder.chain.datatypes.constants;

import com.xy.xsql.core.builder.CodeBuilder;
import com.xy.xsql.tsql.model.datatypes.constants.UniqueidentifierConstant;

import java.util.UUID;

/**
 * Created by xiaoyao9184 on 2018/5/22.
 */
public class UniqueidentifierConstantBuilder extends CodeBuilder<UniqueidentifierConstant> {

    public UniqueidentifierConstantBuilder() {
        super(new UniqueidentifierConstant());
    }

    public UniqueidentifierConstantBuilder(UniqueidentifierConstant tar) {
        super(tar);
    }

    public UniqueidentifierConstantBuilder withUUID() {
        target.setUuid(UUID.randomUUID());
        return this;
    }

    public UniqueidentifierConstantBuilder withUUID(UUID uuid) {
        target.setUuid(uuid);
        return this;
    }

    public UniqueidentifierConstantBuilder withUUID(String uuid) {
        target.setUuid(UUID.fromString(uuid));
        return this;
    }

    public UniqueidentifierConstantBuilder withUseBinaryFormat() {
        target.setUseBinaryFormat(true);
        return this;
    }

    public UniqueidentifierConstantBuilder withUseBinaryFormat(boolean useBinaryFormat) {
        target.setUseBinaryFormat(useBinaryFormat);
        return this;
    }

}
