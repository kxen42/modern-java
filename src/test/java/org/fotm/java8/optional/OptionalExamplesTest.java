package org.fotm.java8.optional;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;


class OptionalExamplesTest {

    @Test
    void calculateAverageWithoutStream_Empty() {
        Optional<Double> v = OptionalExamples.calculateAverageWithoutStream();
        assertThat(v).isEmpty();
    }

    @Test
    void calculateAverageWithoutStream_OneArg() {
        Optional<Double> v = OptionalExamples.calculateAverageWithoutStream(42);
        assertThat(v).isPresent();
        assertThat(v).hasValue(42.0);
    }

    @Test
    void calculateAverageWithoutStream_ManyArgs() {
        Optional<Double> v = OptionalExamples.calculateAverageWithoutStream(1, 2, 42, 532, 47, 4021, 1166);
        assertThat(v).isPresent();
        assertThat(v).hasValue(830.1428571428571);
    }

    @Test
    void calculateAverageWithEmptyStream_NaN() {
        Optional<Double> v = OptionalExamples.calculateAverageWithoutStream();
        assertThat(Double.isNaN(v.orElse(Double.NaN))).isTrue();
    }

    @Test
    void calculateAverageWithEmptyStream_NotNaN() {
        Optional<Double> v = OptionalExamples.calculateAverageWithoutStream(10, 20, 30);
        assertThat(Double.isNaN(v.orElse(Double.NaN))).isFalse();
    }

    @Test
    void calculateAverageWithEmptyStream_OrElseGet() {
        Optional<Double> v = OptionalExamples.calculateAverageWithoutStream(10, 20, 30);
        assertThat(Double.isNaN(v.orElseGet(Math::random))).isFalse();
    }

    @Test
    void handlePossibleNullValue_NotNull() {
        Optional<String> v = OptionalExamples.handlePossibleNullValue("Bob");
        assertThat(v).isPresent()
                     .hasValue("Bob");
    }

    @Test
    void handlePossibleNullValue_Null() {
        Optional<String> v = OptionalExamples.handlePossibleNullValue(null);
        assertThat(v).isNotPresent()
                     .isEmpty();

        String result = v.orElseGet(() -> "ouch");
        assertThat(result).isEqualTo("ouch");

        String result2 = v.orElse("boo");
        assertThat(result2).isEqualTo("boo");

        assertThatIllegalStateException().isThrownBy(() -> {
            String s = v.orElseThrow(IllegalStateException::new);
        });
    }

    @Test
    void primitiveAverage() {
        IntStream range = IntStream.range(1, 5);
        OptionalDouble average = range.average();

        assertThat(average).hasValueCloseTo(2.0, Offset.offset(0.5));

        range = IntStream.of();
        average = range.average();

        assertThat(average).isEmpty();
    }
}