package com.pluralsight.migratingjunit4junit5.assumptions.environment;

public class TestsEnvironment {
    private JavaSpecification javaSpecification;
    private OperatingSystem operatingSystem;

    public TestsEnvironment(JavaSpecification javaSpecification, OperatingSystem operatingSystem) {
        this.javaSpecification = javaSpecification;
        this.operatingSystem = operatingSystem;
    }

    public boolean isWindows() {
        return operatingSystem.getName().contains("Windows");
    }

    public boolean isAmd64Architecture() {
        return operatingSystem.getArchitecture().equals("amd64");
    }

    public String getJavaVersion() {
        return javaSpecification.getVersion();
    }

}
