package com.agiledeveloper;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableSubscriber;
import org.reactivestreams.Subscription;

public class Sample {
  public static void main(String[] args) {
    Flowable.<Integer>create(emitter -> emit(emitter), BackpressureStrategy.BUFFER)
      .map(data -> data * 1)
      .subscribe(new FlowableSubscriber<Integer>() {
        private Subscription subscription;

        @Override
        public void onSubscribe(Subscription subscription) {
          this.subscription = subscription;
          this.subscription.request(10);
        }

        @Override
        public void onNext(Integer data) {
          System.out.println(data);

          if(data == 5) {
            System.out.println("got enough... thank you, stop now");
            subscription.cancel();
          }
        }

        @Override
        public void onError(Throwable throwable) {

        }

        @Override
        public void onComplete() {
          System.out.printf("DONE...cleanup...");
        }
      });
  }

  private static void emit(FlowableEmitter<Integer> emitter) {
    int count = 0;

    while(++count < 10 && !emitter.isCancelled()) {
      System.out.println("emitting.... " + count);
      emitter.onNext(count);
    }

    emitter.onComplete();
  }
}