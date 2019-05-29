package com.agiledeveloper;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;

import java.util.*;

public class Sample {
  public static void main(String[] args) {
    //failure happens.
    //Rather than prevent failure, we will gracefully recover
    //failure is a first class citizen
    //error is just another form of data

    //we can receive error through the error channel.

    Flowable.<Integer>create(emitter -> emit(emitter), BackpressureStrategy.BUFFER)
      .onErrorResumeNext(throwable -> {
        System.out.println("Failed, starting backup...");
        return Flowable.<Integer>create(Sample::emit, BackpressureStrategy.BUFFER);
      })
      .map(data -> data * 2)
      .subscribe(System.out::println,
          err -> System.out.println("ERROR: " + err));
  }

  //the blowup was captured and given to use using the error channel.

  private static void emit(FlowableEmitter<Integer> emitter) {
    int count = 0;

    while(++count < 10) {
      System.out.println("emitting... " + count);
      emitter.onNext(count);

      if(count == 7) {
        //throw new RuntimeException("something went wrong");
        emitter.onError(new RuntimeException("something went wrong here..."));
      }
    }
  }
}