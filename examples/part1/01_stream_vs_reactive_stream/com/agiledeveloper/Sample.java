package com.agiledeveloper;

import io.reactivex.Flowable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Sample {
  public static long doubleIt(long value) {
    System.out.println("called for " + value);
    return value * 2;
  }

  public static void main(String[] args) throws InterruptedException {
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

    numbers.stream()
      .filter(e -> e % 2 == 0)
      .map(e -> doubleIt(e))
      .forEach(System.out::println) //comment out this line
      ;

    Flowable.interval(0, 1, TimeUnit.SECONDS)
      .filter(e -> e % 2 == 0)
      .map(e -> doubleIt(e))
      .take(3)
      .subscribe(System.out::println) //comment out this line
      ;

    Thread.sleep(5000);

/*
  Reactive Programming == Functional Programming++

      Java 8 Stream             vs.       Reactive Stream
      functional composition              functional composition
      pipeline                            pipeline
      lazy evaluation                     lazy evaluation

      data only                           data also
      How do we deal with exception?
      good luck                           Deal with it downstream
      Exception handling is an imperative
      style of programming idea

      one channel (data)                  three channels
                                          -----> data
                                          -----> error
                                          -----> complete

                                          data comes through data channel
                                          when an error emerges from error channel
                                            or a complete signal comes from the complete channel
                                            the data channel closes forever (based on the concept of circuit breaker)

      serial vs. parallel                 synchronous vs. asynchronous

      no forking                          can have multiple subscribers
 */
  }
}
