package com.agiledeveloper;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

public class Sample {
  public static void main(String[] args) throws InterruptedException {
    Flowable<Integer> feed = Flowable.<Integer>create(emitter -> emit(emitter), BackpressureStrategy.BUFFER)
        .subscribeOn(Schedulers.computation());

    feed.subscribe(data -> printIt("S1", data));
    feed.subscribe(data -> printIt("S2", data));

    Thread.sleep(10000);
  }

  //each call to subscriber creates a new subscription (session) by
  //default.

  private static void emit(FlowableEmitter<Integer> emitter) {
    System.out.println("called....");
    emitter.onNext(1);
    sleep(1000);
    emitter.onNext(2);
    sleep(1000);
    emitter.onNext(3);
    sleep(1000);
  }

  private static boolean sleep(int ms) {
    try {
      Thread.sleep(ms);
      return true;
    } catch (InterruptedException e) {
      return false;
    }
  }
  private static void printIt(String msg, int data) {
    System.out.println(msg + ":" + data);
  }
}