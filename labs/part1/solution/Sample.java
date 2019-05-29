package com.agiledeveloper;

import io.reactivex.Flowable;
import java.util.concurrent.TimeUnit;

public class Sample {
  public static void main(String[] args) throws InterruptedException {
    Flowable.interval(0, 1, TimeUnit.SECONDS)
      .map(data -> data * 2)
      .subscribe(System.out::println);

    Thread.sleep(10000);
  }
}