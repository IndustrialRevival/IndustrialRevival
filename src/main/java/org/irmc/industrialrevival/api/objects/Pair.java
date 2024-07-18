package org.irmc.industrialrevival.api.objects;

public class Pair<A, B> {
  private A first;
  private B second;
  private final boolean mutable;

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

  public A getFirst() {
    return first;
  }

  public B getSecond() {
    return second;
  }

  public A getA() {
    return first;
  }

  public B getB() {
    return second;
  }

  public boolean isMutable() {
    return mutable;
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
