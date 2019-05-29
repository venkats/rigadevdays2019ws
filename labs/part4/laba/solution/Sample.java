package com.agiledeveloper;

import drone.DroneLocation;
import drone.DroneLocator;
import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;

import java.util.Scanner;

public class Sample {
  public static void main(String[] args) {
    Disposable disposable = DroneLocator.fetch("DR01")
        .subscribe(System.out::println,
          System.out::println,
          () -> System.out.println("Drone landed"));

    System.out.println("enter end to terminate");

    String input = "";
    try(Scanner scanner = new Scanner(System.in)) {
      while(!input.equals("end")) {
        input = scanner.nextLine().trim();
      }
    }

    disposable.dispose();
  }
}
