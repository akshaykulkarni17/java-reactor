package com.example.publishers;

import com.example.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

public class ColdPublisher {


    public static void main(String[] args) {
        Flux<String> movieStream = Flux.fromStream(ColdPublisher::getMovie).delayElements(Duration.ofSeconds(2));

        movieStream.subscribe(Util.getSubscriber("sam"));
        Util.sleep(5);
        movieStream.subscribe(Util.getSubscriber("mike"));
        Util.sleep(60);
    }
    public static Stream<String> getMovie(){
        return Stream.of(
                "Scene 1",
                "Scene 2",
                "Scene 3",
                "Scene 4",
                "Scene 5",
                "Scene 6",
                "Scene 7"
        );
    }
}
