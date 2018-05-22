package com.xy.xsql.tsql.model.datatypes.constants;

import java.util.UUID;

/**
 * Created by xiaoyao9184 on 2018/5/22.
 */
public class UniqueidentifierConstant implements Constant {

    private UUID uuid;
    private boolean useBinaryFormat;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public boolean isUseBinaryFormat() {
        return useBinaryFormat;
    }

    public void setUseBinaryFormat(boolean useBinaryFormat) {
        this.useBinaryFormat = useBinaryFormat;
    }
}
