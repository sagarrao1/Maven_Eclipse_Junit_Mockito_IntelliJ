package com.pluralsight.migratingjunit4junit5.assumptions.environment;

public class OperatingSystem {
    private String name;
    private String architecture;

    public OperatingSystem(String name, String architecture) {
        this.name = name;
        this.architecture = architecture;
    }

    public String getName() {
        return name;
    }

    public String getArchitecture() {
        return architecture;
    }
}
