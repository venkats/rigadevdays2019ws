package com.agiledeveloper;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;

public class Sample {
  public static void main(String[] args) {
    Flowable.<Integer>create(emitter -> emit(emitter), BackpressureStrategy.BUFFER)
      .filter(data -> data % 2 == 0)
      .map(data -> data * 2)
      .subscribe(data -> System.out.println(data));

    System.out.println("done");
  }

  private static void emit(FlowableEmitter<Integer> emitter) {
    System.out.println("emitting....");

    int count = 0;
    while(count < 10) {
      count++;

      emitter.onNext(count); //push
    }
  }
}