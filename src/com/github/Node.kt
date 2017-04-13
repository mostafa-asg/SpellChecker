package com.github

import java.util.*

/**
 *
 * @author Mostafa Asgari
 * @since 4/13/17
 */
class Node(val word : String) {

    val children: HashMap<Int,Node> = hashMapOf()

}