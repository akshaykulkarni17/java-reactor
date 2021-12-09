package com.example.stepverifier;

import com.example.assignment.BookOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

public class ReactorTest {


    @Test
    public void Test1(){
        Flux<Integer> flux = Flux.just(1,2,3);

        StepVerifier.create(flux)
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .verifyComplete();
    }

    @Test
    public void Test2(){
        Flux<Integer> flux = Flux.just(1,2,3);

        StepVerifier.create(flux)
                .expectNext(1,2,3)
                .verifyComplete();
    }

    @Test
    public void Test3(){
        Flux<Integer> flux = Flux.just(1,2,3);
        Flux<Integer> error = Flux.error(() -> new RuntimeException("oops"));

        StepVerifier.create(Flux.concat(flux,error))
                .expectNext(1,2,3)
                .verifyErrorMessage("oops");
                //.verifyError();

    }

    @Test
    public void Test4(){
        Flux<Integer> flux = Flux.range(1,50);

        StepVerifier.create(flux)
                .expectNextCount(50)
                .verifyComplete();

    }

    @Test
    public void Test5(){
        Flux<Integer> flux = Flux.range(1,50);

        StepVerifier.create(flux)
                .thenConsumeWhile(integer -> integer<52)
                .verifyComplete();

    }

    @Test
    public void Test6(){
        Mono<BookOrder> mono = Mono.just(new BookOrder());

        StepVerifier.create(mono)
                .assertNext(bookOrder -> Assertions.assertNotNull(bookOrder.getAuthor()))
                .verifyComplete();
    }

    @Test
    public void Test7(){
        Mono<BookOrder> mono = Mono.just(new BookOrder())
                .delayElement(Duration.ofSeconds(3));

        StepVerifier.create(mono)
                .assertNext(bookOrder -> Assertions.assertNotNull(bookOrder.getAuthor()))
                .expectComplete()
                .verify(Duration.ofSeconds(4));
    }
}
