package com.agiledeveloper;

import drone.DroneLocator;

import java.io.IOException;

public class Sample {
  public static void main(String[] args) throws IOException {
    DroneLocator.fetch("DR01")
      .skipWhile(location -> location.getAltitude() <= 50)
      .take(25)
      .subscribe(System.out::println, System.out::println,
          () -> System.out.println("landed"));

    System.in.read();
  }
}