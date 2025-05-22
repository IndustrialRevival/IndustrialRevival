package org.irmc.industrialrevival.api.objects;

@FunctionalInterface
public interface CiFunction<A, B, C, R> {
    R apply(A a, B b, C c);
}
