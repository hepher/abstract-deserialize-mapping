package com.deserialize.mapping;

import java.util.function.Function;

public class Range<T extends Comparable<T>> implements Comparable<Range<T>> {
    private final T startingPoint;
    private final T endingPoint;

    public Range(T firstPoint, T secondPoint) {

        if (firstPoint.compareTo(secondPoint) > 0) {
            startingPoint = secondPoint;
            endingPoint = firstPoint;
        } else {
            startingPoint = firstPoint;
            endingPoint = secondPoint;
        }
    }

    public boolean contains(T point) {
        return checkAndExecute(point, (inputPoint) -> startingPoint.compareTo(inputPoint) <= 0 && endingPoint.compareTo(inputPoint) >= 0);
    }

    public boolean existOverlap(Range<T> range) {

        return checkAndExecute(range, (inputRange) -> startingPoint.compareTo(inputRange.getEndingPoint()) < 0 && inputRange.getStartingPoint().compareTo(endingPoint) < 0);
    }

    public boolean contains(Range<T> range) {
        return checkAndExecute(range, (inputRange) -> startingPoint.compareTo(inputRange.getStartingPoint()) <= 0 && endingPoint.compareTo(inputRange.getEndingPoint()) >= 0);
    }

    public boolean isContained(Range<T> range) {
        return checkAndExecute(range, (inputRange) -> startingPoint.compareTo(inputRange.getStartingPoint()) >= 0 && endingPoint.compareTo(inputRange.getEndingPoint()) <= 0);
    }

    @Override
    public int compareTo(Range<T> range) {
        return checkAndExecute(range, (inputRange) -> endingPoint.compareTo(startingPoint) - inputRange.getEndingPoint().compareTo(inputRange.getStartingPoint()));
    }

    public T getStartingPoint() {
        return startingPoint;
    }

    public T getEndingPoint() {
        return endingPoint;
    }

    private <I, O> O checkAndExecute(I input, Function<I, O> function) {
        if (input == null) {
            throw new RuntimeException("Input parameter cannot be null");
        }

        return function.apply(input);
    }
}
