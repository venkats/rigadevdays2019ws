package com.agiledeveloper;

import io.reactivex.Flowable;

import java.util.concurrent.TimeUnit;

public class Sample {
  public static void main(String[] args) throws InterruptedException {
    Flowable<Long> feed = Flowable.interval(0, 1, TimeUnit.SECONDS)
        .share();

//you can make it a hot publisher.

    feed.subscribe(data -> printIt("S1", data));

    Thread.sleep(5000);
    feed.subscribe(data -> printIt("S2", data));

    Thread.sleep(10000);
  }

  private static void printIt(String msg, Long data) {
    System.out.println(msg + ":" + data);
  }
}