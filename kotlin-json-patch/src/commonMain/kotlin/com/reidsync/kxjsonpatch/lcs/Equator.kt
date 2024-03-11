package com.reidsync.kxjsonpatch.lcs

/**
 * An equation function, which determines equality between objects of type T.
 *
 *
 * It is the functional sibling of [java.util.Comparator]; [Equator] is to
 * [Object] as [java.util.Comparator] is to [java.lang.Comparable].
 *
 * @param <T> the types of object this [Equator] can evaluate.
 * @since 4.0
 * @version $Id: Equator.java 1540567 2013-11-10 22:19:29Z tn $
</T> */
interface Equator<T> {
    /**
     * Evaluates the two arguments for their equality.
     *
     * @param o1 the first object to be equated.
     * @param o2 the second object to be equated.
     * @return whether the two objects are equal.
     */
    fun equate(o1: T, o2: T): Boolean

    /**
     * Calculates the hash for the object, based on the method of equality used in the equate
     * method. This is used for classes that delegate their [equals(Object)][Object.equals] method to an
     * Equator (and so must also delegate their [hashCode()][Object.hashCode] method), or for implementations
     * of  org.apache.commons.collections4.map.HashedMap that use an Equator for the key objects.
     *
     * @param o the object to calculate the hash for.
     * @return the hash of the object.
     */
    fun hash(o: T): Int
}