package com.agiledeveloper;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;

public class Sample {
  public static void main(String[] args) {
    Flowable.<Integer>create(emitter -> emit(emitter), BackpressureStrategy.BUFFER)
      .skipWhile(data -> data < 50)
      .takeWhile(data -> data < 70)
      .subscribe(System.out::println, System.out::println,
          () -> System.out.println("DONE"));
  }

  private static void emit(FlowableEmitter<Integer> emitter) {
    int count = 0;

    while(++count < 100 && !emitter.isCancelled()) {
      int data = (int)(Math.random() * 100);
      System.out.println("emitting... " + data);
      emitter.onNext(data);
      try { Thread.sleep(1000); } catch(Exception ex) {}
    }
  }
}