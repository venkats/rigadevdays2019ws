package com.agiledeveloper;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

import java.io.IOException;

public class Sample {
  public static void main(String[] args) throws IOException {
    System.out.println("In main from " + Thread.currentThread());
    Flowable.<Integer>create(emitter -> emit(emitter), BackpressureStrategy.BUFFER)
      //.subscribeOn(Schedulers.computation())
      .subscribeOn(Schedulers.io())
      .subscribe(data -> printIt(data));

    System.in.read();
  }

  private static void printIt(Integer data) {
    System.out.println(data + " printed from " + Thread.currentThread());
  }

  private static void emit(FlowableEmitter<Integer> emitter) {
    int count = 0;

    while(++count < 10) {
      System.out.println("emitting " + count + " from " + Thread.currentThread());
      emitter.onNext(count);
    }
  }
}