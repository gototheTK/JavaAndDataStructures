package org.example.java.stream;

import java.util.*;
import java.util.stream.Stream;

/**
 * StreamEx1
 */
public class StreamEx1 {

    public static void main(String[] args) {

        Stream<Student> studentsStream = Stream.of(
                new Student("이자바", 3, 300),
                new Student("김자바", 1, 200),
                new Student("안자바", 2, 100),
                new Student("박자바", 2, 150),
                new Student("소자바", 1, 200),
                new Student("나자바", 3, 290),
                new Student("감자바", 3, 180));

        studentsStream.sorted(Comparator.comparing(Student::getBan).thenComparing(Comparator.naturalOrder()))
                .forEach(System.out::println);

    }

}

class Student implements Comparable<Student> {

    String name;
    int ban;
    int totalScore;

    Student(String name, int ban, int totalScore) {
        this.name = name;
        this.ban = ban;
        this.totalScore = totalScore;
    }

    public String toString() {
        return String.format("[%s, %d, %d]", name, ban, totalScore);

    }

    String getName() {
        return name;
    }

    int getBan() {
        return ban;
    };

    int getTotalScore() {
        return totalScore;
    }

    @Override
    public int compareTo(Student o) {
        // TODO Auto-generated method stub
        return o.totalScore - this.totalScore;
    }

}