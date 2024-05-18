/*
 *
 * Government Teacher - Module# 23
 * Copyright (c) IEIMS. All rights reserved.
 *
 */

package com.nazran.springboot3.enums;

public enum RecordStatus {
    DRAFT("DRAFT"),
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE"),
    DELETED("DELETED");

    private final String label;

    RecordStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
