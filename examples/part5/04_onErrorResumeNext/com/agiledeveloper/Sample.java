package com.agiledeveloper;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.functions.Function;
import org.reactivestreams.Publisher;

import java.util.*;

public class Sample {
  public static Flowable<Integer> createFlowable(int numberOfRetries) {
    Flowable<Integer> flowable = Flowable.<Integer>create(Sample::emit, BackpressureStrategy.BUFFER);

    Function<? super Throwable, ? extends Publisher<? extends Integer>> func =
        throwable -> createFlowable(numberOfRetries - 1);

    if(numberOfRetries > 0)
      return flowable.onErrorResumeNext(func);

    return flowable;
  }

  public static void main(String[] args) {
    createFlowable(5)
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