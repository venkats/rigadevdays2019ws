package com.agiledeveloper;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;

public class Sample {
  public static void main(String[] args) {
    Flowable.<Integer>create(emitter -> emit(emitter), BackpressureStrategy.BUFFER)
      .skip(5)
      .subscribe(System.out::println);
  }

  private static void emit(FlowableEmitter<Integer> emitter) {
    int count = 0;

    while(++count < 100) {
      emitter.onNext(count);
      try { Thread.sleep(1000); } catch(Exception ex) {}
    }
  }
}