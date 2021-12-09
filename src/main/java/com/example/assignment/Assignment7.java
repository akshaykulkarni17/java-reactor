package com.example.assignment;

import com.example.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Assignment7 {

    public static final Set<String> allowedCategories = new HashSet<>();

    public static void main(String[] args) {
        allowedCategories.add("Science fiction");
        allowedCategories.add("Fantasy");
        allowedCategories.add("Suspense/Thriller");

        bookOrderStream()
                .filter(bookOrder -> allowedCategories.contains(bookOrder.getCategory()))
                .buffer(Duration.ofSeconds(5))
                .map(Assignment7::getReport)
                .subscribe(Util.getSubscriber());

        Util.sleep(20);
    }

    private static RevenueReport getReport(List<BookOrder> books){
        Map<String,Double> map =books.stream()
                .collect(Collectors.groupingBy(
                        BookOrder::getCategory,
                        Collectors.summingDouble(BookOrder::getCost)));

        return new RevenueReport(map);
    }





    private static Flux<BookOrder> bookOrderStream(){
        return Flux.interval(Duration.ofMillis(100))
                .map(i->new BookOrder());
    }
}
