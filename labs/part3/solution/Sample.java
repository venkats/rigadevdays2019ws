package com.agiledeveloper;

import io.reactivex.Flowable;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Sample {
  public static void main(String[] args) throws Exception {
    final List<String> symbols = List.of(
        "AMD", "HPQ", "IBM", "TXN", "VMW", "XRX", "AAPL", "ADBE",
        "AMZN", "CRAY", "CSCO", "SNE", "GOOG", "INTC", "INTU", "MSFT", "ORCL", "TIBX", "VRSN", "YHOO");

    System.out.println("Press return to exit");

    Flowable.interval(0, 1, TimeUnit.SECONDS)
      .map(index -> symbols.get((int)(index % symbols.size())))
      .map(aSymbol -> new Object() {
        String symbol = aSymbol;
        int price = StockPrice.getPrice(aSymbol);
      })
      .subscribe(stockPrice -> System.out.println(stockPrice.symbol + " : " + stockPrice.price));

    System.in.read();
    System.out.println("Completed");
  }
}
