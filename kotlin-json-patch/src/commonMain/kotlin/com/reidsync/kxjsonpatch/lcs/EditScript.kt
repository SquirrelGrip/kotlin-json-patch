package com.reidsync.kxjsonpatch.lcs

/**
 * This class gathers all the [commands][EditCommand] needed to transform
 * one objects sequence into another objects sequence.
 *
 *
 * An edit script is the most general view of the differences between two
 * sequences. It is built as the result of the comparison between two sequences
 * by the [SequencesComparator] class. The user can
 * walk through it using the *visitor* design pattern.
 *
 *
 * It is guaranteed that the objects embedded in the [insert][InsertCommand] come from the second sequence and that the objects embedded in
 * either the [delete commands][DeleteCommand] or [keep][KeepCommand] come from the first sequence. This can be important if subclassing
 * is used for some elements in the first sequence and the `equals`
 * method is specialized.
 *
 * @see SequencesComparator
 *
 * @see EditCommand
 *
 * @see CommandVisitor
 *
 *
 * @since 4.0
 * @version $Id: EditScript.java 1477760 2013-04-30 18:34:03Z tn $
 */
class EditScript<T> {
    /** Container for the commands.  */
    private val commands: MutableList<EditCommand<T>>
    /**
     * Get the length of the Longest Common Subsequence (LCS). The length of the
     * longest common subsequence is the number of [keep][KeepCommand] in the script.
     *
     * @return length of the Longest Common Subsequence
     */
    /** Length of the longest common subsequence.  */
    var lCSLength: Int
        private set
    /**
     * Get the number of effective modifications. The number of effective
     * modification is the number of [delete][DeleteCommand] and
     * [insert][InsertCommand] commands in the script.
     *
     * @return number of effective modifications
     */
    /** Number of modifications.  */
    var modifications: Int
        private set

    /**
     * Simple constructor. Creates a new empty script.
     */
    init {
        commands = ArrayList<EditCommand<T>>()
        lCSLength = 0
        modifications = 0
    }

    /**
     * Add a keep command to the script.
     *
     * @param command  command to add
     */
    fun append(command: KeepCommand<T>) {
        commands.add(command)
        ++lCSLength
    }

    /**
     * Add an insert command to the script.
     *
     * @param command  command to add
     */
    fun append(command: InsertCommand<T>) {
        commands.add(command)
        ++modifications
    }

    /**
     * Add a delete command to the script.
     *
     * @param command  command to add
     */
    fun append(command: DeleteCommand<T>) {
        commands.add(command)
        ++modifications
    }

    /**
     * Visit the script. The script implements the *visitor* design
     * pattern, this method is the entry point to which the user supplies its
     * own visitor, the script will be responsible to drive it through the
     * commands in order and call the appropriate method as each command is
     * encountered.
     *
     * @param visitor  the visitor that will visit all commands in turn
     */
    fun visit(visitor: CommandVisitor<T>) {
        for (command in commands) {
            command.accept(visitor)
        }
    }
}