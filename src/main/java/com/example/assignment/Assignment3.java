package com.example.assignment;

import com.example.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.SynchronousSink;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.Consumer;

//File reader service
public class Assignment3 {

    public static void main(String[] args) {
        Path path = Paths.get("src/main/resources/assginment3/file.txt");
        fileRead(path)
                .take(100)
                .subscribe(Util.getSubscriber());
    }

    public static Flux<String> fileRead(Path path){
        return Flux.generate(
               openReader(path),
               read(),
               closeReader()
        );
    }

    private static Callable<BufferedReader> openReader(Path path)  {
        return () -> Files.newBufferedReader(path);
    }
    private static BiFunction<BufferedReader, SynchronousSink<String>,BufferedReader> read(){
        return (bufferedReader, stringSynchronousSink) -> {
            try {
                String line = bufferedReader.readLine();
                if (line==null) stringSynchronousSink.complete();
                else stringSynchronousSink.next(line);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bufferedReader;
        };
    }
    private static Consumer<BufferedReader> closeReader(){
        return bufferedReader -> {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }
}
