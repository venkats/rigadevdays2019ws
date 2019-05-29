package com.agiledeveloper;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

import java.io.IOException;

public class Sample {
  public static void main(String[] args) {
    //Ground rules
    //Subscriber should not tell the publisher to run faster or slower
    //Publisher should not tell subscriber to run faster or slower
    //Backpressure.

    Flowable.<Integer>create(emitter -> emit(emitter), BackpressureStrategy.BUFFER)
      .observeOn(Schedulers.io(), true, 2)
      .subscribe(data -> printIt(data));

    sleep(10000);
  }

  private static void printIt(Integer data) {
    System.out.println("received: " + data);
    sleep(1000);
  }

  private static void emit(FlowableEmitter<Integer> emitter) {
    int count = 0;

    while(++count < 10) {
      System.out.println("emitting... " + count);
      emitter.onNext(count);
      sleep(500);
    }
  }

  private static boolean sleep(int ms) {
    try {
      Thread.sleep(ms);
      return true;
    } catch (InterruptedException e) {
      return false;
    }
  }
}