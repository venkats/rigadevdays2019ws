package com.agiledeveloper;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;

public class Sample {
  public static void main(String[] args) {
    System.out.println("In main from " + Thread.currentThread());
    Flowable.<Integer>create(emitter -> emit(emitter), BackpressureStrategy.BUFFER)
      .subscribe(data -> printIt(data));
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