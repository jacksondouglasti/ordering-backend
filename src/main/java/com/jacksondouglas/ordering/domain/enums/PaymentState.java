package com.jacksondouglas.ordering.domain.enums;

public enum PaymentState {

    PENDING(1, "Pending"),
    SETTLED(2, "Settled"),
    CANCELED(3, "Canceled");

    private int id;
    private String desc;

    private PaymentState(int id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }

    public static PaymentState toEnum(int id) {
        for (PaymentState p : PaymentState.values()) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
}
