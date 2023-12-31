package ru.job4j.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Analyze {

    public static double averageScore(Stream<Pupil> stream) {
        return stream.flatMap(pupil -> pupil.subjects().stream())
                .mapToDouble(Subject::score)
                .average()
                .orElse(0.0D);
    }

    public static List<Tuple> averageScoreByPupil(Stream<Pupil> stream) {
        return stream.map(pupil -> new Tuple(pupil.name(),
                        pupil.subjects().stream().collect(Collectors.averagingDouble(Subject::score))))
                .toList();
    }

    public static List<Tuple> averageScoreBySubject(Stream<Pupil> stream) {
        return stream.flatMap(pupil -> pupil.subjects().stream())
                .collect(Collectors.groupingBy(Subject::name,
                        LinkedHashMap::new,
                        Collectors.averagingDouble(Subject::score)))
                .entrySet()
                .stream()
                .map(k -> new Tuple(k.getKey(), k.getValue()))
                .toList();
    }

    public static Tuple bestStudent(Stream<Pupil> stream) {
        return stream.map(pupil -> new Tuple(pupil.name(),
                        pupil.subjects().stream()
                                .mapToDouble(Subject::score)
                                .sum()))
                .max(Comparator.comparing(Tuple::score))
                .orElse(null);
    }

    public static Tuple bestSubject(Stream<Pupil> stream) {
        return stream.flatMap(pupil -> pupil.subjects().stream())
                .collect(Collectors.groupingBy(Subject::name,
                        Collectors.summingDouble(Subject::score)))
                .entrySet().stream()
                .map(k -> new Tuple(k.getKey(), k.getValue()))
                .max(Comparator.comparing(Tuple::score))
                .orElse(null);
    }
}