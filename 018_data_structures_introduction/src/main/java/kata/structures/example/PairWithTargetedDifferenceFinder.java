package kata.structures.example;

import kata.data.Pair;

import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.sort;
import static kata.data.Pair.of;

public class PairWithTargetedDifferenceFinder<T extends Number> {

    private final T[] inputs;

    public PairWithTargetedDifferenceFinder(final T[] inputs) {
        this.inputs = inputs;
    }

    /**
     * O(n).
     */
    public Set<Pair<T, T>> find(final int targetedDifference) {

        final Set<Pair<T, T>> pairs = new HashSet<>();

        sort(this.inputs);

        for (final T input : inputs) {
            for (final T current : inputs) {
                final int difference = current.intValue() - input.intValue();
                if (difference == targetedDifference) pairs.add(of(input, current));
            }
        }

        return pairs;

    }

}