package week1;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by Rick on 10/21/16.
 */
public class Day1 {
    public static void main(String[] args) {
        System.out.println("--- Uniqueness ---");
        checkUnique("");
        checkUnique("a");
        checkUnique("ab");
        checkUnique("abcd");
        checkUnique("abad");
        checkUnique("abcdefghijklmnopqrstuvwxyz");
        checkUnique("abcdefghijklmmopqrstuvwxyz");
    }

    /*  1.1 Is Unique: Implement an algorithm to determine if a string has all unique characters.
     *  What if you cannot use additional data structures?
     */

    /** 
     * Checks uniqueness, uses a hashset, which offers constant time insertion, to keep
     * track of characters used.
     *
     * O(n), where n is length of string
     *  - add(..): 1
     *  - loop through string: n
     *
     * @param string the string to check
     * @return True/False is unique
     */
    static boolean isUnique(String string) {
        if (string.length() <= 1)
            return true;
        HashSet<Character> set = new HashSet<>(string.length());

        for (char c : string.toCharArray()) {
            if (!set.add(c))
                return false;
        }

        return true;
    }

    /**
     * Checks uniqueness, uses sorting (no other data structures). Pretty
     * inefficient solution.
     *
     * O(n + n log n) = O(n log n)
     *  - sort, using dual-pivot quicksort: O(n log n)
     *  - loop through array: O(n)
     *
     * @param string the string
     * @return True/False is unique
     */
    static boolean isUnique_NDS(String string) {
        if (string.length() <= 1) {
            return true;
        }
        char[] chars = string.toCharArray();
        Arrays.sort(chars); // O(n log n)

        char last = chars[0];
        for (int i = 1; i < chars.length; i++) { // O(n)
            if (last == chars[i])
                return false;
            last = chars[i];
        }

        return true;
    }

    /**
     * Uses a bit vector to check uniqueness, can only handle up to 32 unique chars.
     * Best for a string of just lower case characters.
     *
     * O(n), where n is length of string
     *  - loop through string: n
     *
     * @param string the string
     * @return True/False is unique
     */
    static boolean isUnique_Bit(String string) {
        // If more chars than in alphabet, it couldn't be unique
        if (string.length() > 26) {
            return false;
        }
        if (string.length() <= 1) {
            return true;
        }

        int bitVector = 0;
        for (int i = 0; i < string.length(); i++) {
            int intVal = string.charAt(i) - 'a'; // normalize to 0-25 inclusive
            int mask = 1 << intVal;              // shift '1' over to the correct spot in the vector

            if ((bitVector & mask) > 0) {        // if the bitVector & mask is   bitVector: 01101001
                return false;                    // bigger than 0, that means   &     mask: 00001000
            }                                    // that bit was already set.            => 00001000 > 0, a match!

            bitVector |= mask;                   // set the bit corresponding to this character
        }

        return true;
    }

    static void checkUnique(String arg) {
        System.out.printf("\"%s\" ? %s\n", arg, isUnique(arg) ? "true" : "false");
        System.out.printf("\"%s\" ? %s\n", arg, isUnique_NDS(arg) ? "true" : "false");
        System.out.printf("\"%s\" ? %s\n", arg, isUnique_Bit(arg) ? "true" : "false");
    }
}
