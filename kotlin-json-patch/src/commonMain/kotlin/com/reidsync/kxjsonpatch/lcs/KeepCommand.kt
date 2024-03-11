package com.reidsync.kxjsonpatch.lcs

/**
 * Command representing the keeping of one object present in both sequences.
 *
 *
 * When one object of the first sequence `equals` another objects in
 * the second sequence at the right place, the [edit script][EditScript]
 * transforming the first sequence into the second sequence uses an instance of
 * this class to represent the keeping of this object. The objects embedded in
 * these type of commands always come from the first sequence.
 *
 * @see SequencesComparator
 *
 * @see EditScript
 *
 *
 * @since 4.0
 * @version $Id: KeepCommand.java 1477760 2013-04-30 18:34:03Z tn $
 */
class KeepCommand<T>
/**
 * Simple constructor. Creates a new instance of KeepCommand
 *
 * @param object  the object belonging to both sequences (the object is a
 * reference to the instance in the first sequence which is known
 * to be equal to an instance in the second sequence)
 */
    (`object`: T) : EditCommand<T>(`object`) {
    /**
     * Accept a visitor. When a `KeepCommand` accepts a visitor, it
     * calls its [visitKeepCommand][CommandVisitor.visitKeepCommand] method.
     *
     * @param visitor  the visitor to be accepted
     */

    override fun accept(visitor: CommandVisitor<T>?) {
        visitor?.visitKeepCommand(`object`)
    }
}