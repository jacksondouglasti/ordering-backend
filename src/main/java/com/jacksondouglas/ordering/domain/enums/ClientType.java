package com.jacksondouglas.ordering.domain.enums;

public enum ClientType {

    PHYSICALPERSON(1, "Physical Person"),
    LEGALPERSON(2, "Legal Person");

    private int id;
    private String desc;

    private ClientType(int id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }

    public static ClientType toEnum(int id) {
        for (ClientType c : ClientType.values()) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }
}
