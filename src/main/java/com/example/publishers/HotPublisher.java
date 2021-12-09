package com.example.publishers;

import com.example.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

public class HotPublisher {


    //share() = publish().refCount(1);
    //cache() = publish().refcount(1).replay();
    public static void main(String[] args) {
        Flux<String> movieStream = Flux.fromStream(HotPublisher::getMovie)
                .delayElements(Duration.ofSeconds(1))
                .publish()
                .autoConnect(0);

        Util.sleep(2);
        movieStream.subscribe(Util.getSubscriber("sam"));
        Util.sleep(4);
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
