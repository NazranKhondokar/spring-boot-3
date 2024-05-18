/*
 *
 * Government Teacher - Module# 23
 * Copyright (c) IEIMS. All rights reserved.
 *
 */

package com.nazran.springboot3.enums;

import lombok.Getter;

@Getter
public enum Gender {
    MALE("MALE"),
    FEMALE("FEMALE"),
    THIRD_GENDER("THIRD_GENDER");

    private final String label;

    Gender(String label) {
        this.label = label;
    }

}
