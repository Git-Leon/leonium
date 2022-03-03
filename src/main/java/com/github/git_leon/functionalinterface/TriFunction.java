package com.github.git_leon.functionalinterface;

import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
public interface TriFunction<FirstArgumentType, SecondArgumentType, ThirdArgumentType, ReturnType> {

    ReturnType apply(FirstArgumentType a, SecondArgumentType b, ThirdArgumentType c);

    default <V> TriFunction<FirstArgumentType, SecondArgumentType, ThirdArgumentType, V> andThen(
            Function<? super ReturnType, ? extends V> after) {
        Objects.requireNonNull(after);
        return (FirstArgumentType a, SecondArgumentType b, ThirdArgumentType c) -> after.apply(apply(a, b, c));
    }
}

