package com.agiledeveloper;

import io.reactivex.*;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Subscription;

import java.io.IOException;

public class Sample {
  public static void main(String[] args) throws IOException {
    Disposable disposable = Flowable.<Integer>create(emitter -> emit(emitter), BackpressureStrategy.BUFFER)
        .subscribeOn(Schedulers.computation())
        .map(data -> data * 1)
        .subscribe(System.out::println, System.out::println, () -> System.out.println("DONE"));

    System.out.println("Press return to terminate");
    System.in.read();
    disposable.dispose();
  }

  private static void emit(FlowableEmitter<Integer> emitter) {
    int count = 0;

    while(++count < 100 && !emitter.isCancelled()) {
      System.out.println("emitting.... " + count);
      try { Thread.sleep(1000); } catch(Exception ex) {}
      emitter.onNext(count);
    }

    emitter.onComplete();
  }
}