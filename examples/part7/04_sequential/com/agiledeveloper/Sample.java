package com.agiledeveloper;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;

import java.io.IOException;

public class Sample {
  public static void main(String[] args) {
    Flowable.<Integer>create(emitter -> emit(emitter), BackpressureStrategy.BUFFER)
      .subscribe(data -> printIt(data));
  }

  private static void printIt(Integer data) {
    System.out.println(data + " printed from " + Thread.currentThread());
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