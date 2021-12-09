package com.example.assignment;

import com.example.util.Util;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;

//File service
public class Assignment1 {


    public static void main(String[] args) throws IOException {
        String fileName = "file1.txt";
        String content = " dhum dhum dhum dhum dhum ";
        read(fileName).subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete()
        );
        write(fileName,content).subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete()
        );
        read(fileName).subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete()
        );
        delete(fileName).subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete()
        );
    }

    public static Mono<String> read(String fileName) throws IOException {
        return Mono.fromSupplier(()-> {
            try {
                return readFile(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        });
    }
    public static Mono<Void> write(String fileName, String content){
        return Mono.fromRunnable(()-> {
            try {
                writeFile(fileName,content);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    public static Mono<Void> delete(String fileName){
        return Mono.fromRunnable(()-> {
            try {
                deleteFile(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    static final Path path = Paths.get("src/main/resources/assignment1");

    public static String readFile(String fileName) throws IOException {
        return Arrays.toString(Files.readAllBytes(path.resolve(fileName)));
    }

    public static void writeFile(String fileName, String content) throws IOException {
        Files.write(path.resolve(fileName), Collections.singleton(content));
    }

    public static void deleteFile(String fileName) throws IOException {
        Files.deleteIfExists(path.resolve(fileName));
    }
}
