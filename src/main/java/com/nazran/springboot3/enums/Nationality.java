/*
 *
 * Government Teacher - Module# 23
 * Copyright (c) IEIMS. All rights reserved.
 *
 */

package com.nazran.springboot3.enums;

import lombok.Getter;

@Getter
public enum Nationality {

    BANGLADESHI("BANGLADESHI");

    private final String label;

    Nationality(String label) {
        this.label = label;
    }

}
