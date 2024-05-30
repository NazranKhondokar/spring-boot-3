package com.nazran.springboot3.enums;

public enum TaskCompletionStatus {

    UNCOMPLETED("UNCOMPLETED"),
    COMPLETED("COMPLETED");

    private final String label;

    TaskCompletionStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
