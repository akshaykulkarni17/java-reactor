package com.example.operators;

import com.example.util.Person;
import com.example.util.Util;
import reactor.core.publisher.Flux;

import java.util.function.Function;

public class Transform {

    public static void main(String[] args) {
        getPersons()
                //.filter(person -> person.getAge()>15)
                //.map(person -> person.getName().toUpperCase())
                .transform(applyFilterAndMap())
                .subscribe(Util.getSubscriber());
    }

    private static Function<Flux<Person>,Flux<Person>> applyFilterAndMap() {
        return personFlux -> personFlux
                .filter(person -> person.getAge()>15)
                .doOnNext(p-> p.setName(p.getName().toUpperCase()))
                .doOnDiscard(Person.class,p->System.out.println("Discarded: "+ p));

    }

    private static Flux<Person> getPersons(){
        return Flux.range(1,10)
                .map(i -> new Person());
    }
}
