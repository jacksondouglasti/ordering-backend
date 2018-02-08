package com.jacksondouglas.ordering.domain.enums;

public enum Profile {

    ADMIN(1, "ROLE_ADMIN"),
    CLIENT(2, "ROLE_CLIENT");

    private int id;
    private String desc;

    private Profile(int id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }

    public static Profile toEnum(int id) {
        for (Profile p : Profile.values()) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
}
