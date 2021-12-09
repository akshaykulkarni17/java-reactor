package com.example.flux;

import com.example.util.Util;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

public class FluxJust {

    public static void main(String[] args) {
        Flux<Object> flux = Flux.just(1,2,3,"a", Util.getFakerInstance().hitchhikersGuideToTheGalaxy().character());
        flux.subscribe(Util.onNext(),Util.onError(),Util.onComplete());

        List<String> list = Arrays.asList("a","b","c");
        Flux.fromIterable(list).subscribe(Util.onNext());

        Integer[] arr= {1,2,3,4,5};
        Flux.fromArray(arr).subscribe(Util.onNext());

        Flux.fromStream(Arrays.stream(arr)).subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete()
        );
        Flux.fromStream(list.stream().parallel()).subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete()
        );
        Flux.fromStream(Arrays.stream(arr)).subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete()
        );
    }
}
