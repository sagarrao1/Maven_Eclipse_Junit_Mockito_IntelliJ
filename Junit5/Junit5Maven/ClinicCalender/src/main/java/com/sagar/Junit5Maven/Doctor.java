package com.sagar.Junit5Maven;

public enum Doctor {
   avery("Ralph Avery"),
   johnson("Beth Johnson"),
   murphy("Pat Murpy");

   private String name;

   Doctor(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }
}