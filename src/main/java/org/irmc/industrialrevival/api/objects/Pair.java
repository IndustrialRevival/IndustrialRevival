package org.irmc.industrialrevival.api.objects;

import lombok.Getter;

@Getter
public class Pair<A, B> {
    private final boolean mutable;
    private A first;
    private B second;

    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
        this.mutable = false;
    }

    public Pair(A first, B second, boolean mutable) {
        this.first = first;
        this.second = second;
        this.mutable = mutable;
    }

    public A getA() {
        return first;
    }

    public B getB() {
        return second;
    }

    public void setFirst(A first) {
        if (!mutable) {
            throw new UnsupportedOperationException("Pair is not mutable");
        }

        this.first = first;
    }

    public void setSecond(B second) {
        if (!mutable) {
            throw new UnsupportedOperationException("Pair is not mutable");
        }

        this.second = second;
    }

    public A getKey() {
        return first;
    }

    public B getValue() {
        return second;
    }
}
