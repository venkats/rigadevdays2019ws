package com.agiledeveloper;

import drone.DroneLocation;
import drone.DroneLocator;
import io.reactivex.Flowable;

public class Sample {
  public static void main(String[] args) throws InterruptedException {
    //There are two ways we can find out.

    //1:

    Flowable<DroneLocation> dr01 = DroneLocator.fetch("DR01");

    dr01.subscribe(location -> System.out.println("s1: " + location));
    dr01.subscribe(location -> System.out.println("s2: " + location));

    Thread.sleep(10000);

    //2:
    //We can look at the reverse engineered code.
    
    //hot
  }
}