package com.agiledeveloper;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

import java.io.IOException;

public class Sample {
  public static void main(String[] args) throws IOException {

    //Java 8 Streams the entire pipeline is sequential or parallel
    //depending on last configuration.


    //Reactive Stream:
    //subscribeOn behaves exactly like that. The entire pipeline
    //runs in the pool specified by subscribeOn

    //But, we can also use observeOn to create segments that are
    //in one thread pool vs. another.

    System.out.println("In main from " + Thread.currentThread());
    Flowable.<Integer>create(emitter -> emit(emitter), BackpressureStrategy.BUFFER)
      //.subscribeOn(Schedulers.io())
      .observeOn(Schedulers.io())
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