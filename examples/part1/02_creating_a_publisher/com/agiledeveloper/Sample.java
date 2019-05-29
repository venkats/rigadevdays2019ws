package com.agiledeveloper;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;

public class Sample {
  public static void main(String[] args) {
    Flowable.<Integer>create(emitter -> emit(emitter), BackpressureStrategy.BUFFER)
      .map(data -> data * 2)
      .subscribe(data -> System.out.println(data));
  }

  private static void emit(FlowableEmitter<Integer> emitter) {
    int count = 0;

    while(count < 10) {
      emitter.onNext(++count);
    }
  }
}