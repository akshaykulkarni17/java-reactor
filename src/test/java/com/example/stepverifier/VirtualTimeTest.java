package com.example.stepverifier;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

public class VirtualTimeTest {

    @Test
    public void Test1(){
        StepVerifier.withVirtualTime(this::flux)
                .thenAwait(Duration.ofSeconds(35))
                .expectNext("1a","2a","3a","4a","5a")
                .verifyComplete();
    }

    @Test
    public void Test2(){
        StepVerifier.withVirtualTime(() -> flux())
                .expectSubscription()
                .expectNoEvent(Duration.ofSeconds(3))
                .thenAwait(Duration.ofSeconds(30))
                .expectComplete();
    }

    private Flux<String> flux() {
        return Flux.range(1,5)
                .delayElements(Duration.ofSeconds(6))
                .map(integer -> integer+"a");
    }

}
