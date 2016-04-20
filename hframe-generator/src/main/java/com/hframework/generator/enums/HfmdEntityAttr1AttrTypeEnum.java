package com.hframework.generator.enums;


public enum HfmdEntityAttr1AttrTypeEnum {
    INT("int", 1),
    BIGINT("bigint", 2),
    VARCHAR("varchar", 3),
    NUMERIC("numeric", 4),
    DATETIME("datetime", 5),
    DATE("date", 6),
    SMALLINT("smallint", 7),
    DOUBLE("double", 8),
    DECIMAL("decimal", 9),
    FLOAT("float", 10),
    text("text", 11);


    // 成员变量
    private String name;
    private int index;

    // 构造方法
    private HfmdEntityAttr1AttrTypeEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (HfmdEntityAttr1AttrTypeEnum c : HfmdEntityAttr1AttrTypeEnum.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    // 普通方法
    public static int getIndex(String name) {
        for (HfmdEntityAttr1AttrTypeEnum c : HfmdEntityAttr1AttrTypeEnum.values()) {
            if (c.getName().equals(name)) {
                return c.index;
            }
        }
        System.out.println("32432");
        return 1;
    }

    // get set 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
