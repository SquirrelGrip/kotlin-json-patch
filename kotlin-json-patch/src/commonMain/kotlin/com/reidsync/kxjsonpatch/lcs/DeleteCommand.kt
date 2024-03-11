package com.reidsync.kxjsonpatch.lcs

/**
 * Command representing the deletion of one object of the first sequence.
 *
 *
 * When one object of the first sequence has no corresponding object in the
 * second sequence at the right place, the [edit script][EditScript]
 * transforming the first sequence into the second sequence uses an instance of
 * this class to represent the deletion of this object. The objects embedded in
 * these type of commands always come from the first sequence.
 *
 * @see SequencesComparator
 *
 * @see EditScript
 *
 *
 * @since 4.0
 * @version $Id: DeleteCommand.java 1477760 2013-04-30 18:34:03Z tn $
 */
class DeleteCommand<T>
/**
 * Simple constructor. Creates a new instance of [DeleteCommand].
 *
 * @param object  the object of the first sequence that should be deleted
 */
    (`object`: T) : EditCommand<T>(`object`) {
    /**
     * Accept a visitor. When a `DeleteCommand` accepts a visitor, it calls
     * its [visitDeleteCommand][CommandVisitor.visitDeleteCommand] method.
     *
     * @param visitor  the visitor to be accepted
     */
    override fun accept(visitor: CommandVisitor<T>?) {
        visitor?.visitDeleteCommand(`object`)
    }
}