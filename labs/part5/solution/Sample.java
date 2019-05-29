package com.agiledeveloper;

import drone.DroneLocator;

import java.io.IOException;

public class Sample {
  public static void main(String[] args) throws IOException {
    DroneLocator.fetch("DR10")
      .subscribe(System.out::println,
          err -> System.out.println("Crashed: " + err));

    System.in.read();
  }
}