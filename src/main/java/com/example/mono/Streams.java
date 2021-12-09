package com.example.mono;

import java.util.stream.Stream;

public class Streams {

    public static void main(String[] args) {
        Stream<Integer> input = Stream.of(1)
                                .map(integer -> {
                                    return integer*3;
                                });
        input.forEach(System.out::println);
    }
}
