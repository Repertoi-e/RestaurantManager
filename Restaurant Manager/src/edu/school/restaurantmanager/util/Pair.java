package edu.school.restaurantmanager.util;

// Две променливи в една

public class Pair<T, U> {

    public T First;
    public U Second;

    // creates a new pair
    public Pair(T first, U second) {
        First = first;
        Second = second;
    }

    @Override
    public String toString() {
        return "{ " + First + ", " + Second + " }";
    }

    @Override
    public int hashCode() {
        return First.hashCode() * 13 + (Second == null ? 0 : Second.hashCode());
    }

     @Override
     public boolean equals(Object o) {
         if (this == o) return true;
         if (o instanceof Pair) {
             Pair<?, ?> pair = (Pair<?, ?>) o;
             if (First != null ? !First.equals(pair.First) : pair.First != null) 
                return false;
             return Second != null ? Second.equals(pair.Second) : pair.Second == null;
         }
         return false;
     }
 }
