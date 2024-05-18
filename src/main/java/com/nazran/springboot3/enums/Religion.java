/*
 *
 * Government Teacher - Module# 23
 * Copyright (c) IEIMS. All rights reserved.
 *
 */

package com.nazran.springboot3.enums;

import java.util.Arrays;

public enum Religion {

    NONE("NONE"),
    ISLAM("ISLAM"),
    HINDUISM("HINDUISM"),
    BUDDHISM("BUDDHISM"),
    CHRISTIANITY("CHRISTIANITY"),
    OTHER("OTHER");

    private final String label;

    Religion(String label) {
        this.label = label;
    }

    public static Religion resolveFromLabel(String label) {
        String l = label.replaceAll(" ", "");
        return Arrays.stream(values())
                .filter(t -> t.getLabel().replaceAll(" ", "").equalsIgnoreCase(l))
                .findAny()
                .orElse(null);
    }

    public String getLabel() {
        return label;
    }
}
