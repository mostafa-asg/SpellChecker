package com.github

import java.util.*

/**
 *
 * @author Mostafa Asgari
 * @since 4/13/17
 */

fun main(args : Array<String> ){

    if( args.size > 0 ) {
        val start = System.currentTimeMillis()

        val spellChecker = SpellChecker.Builder.loadFromFile(args[0]).build()
        println("loaded ${spellChecker.totalWords} words in ${(System.currentTimeMillis() - start) / 1000.0} seconds")

        val scanner : Scanner = Scanner(System.`in`)

        while (true){

            println("enter your misspell word (enter q for exit) :")
            val word = scanner.nextLine()
            if( word==null || word.equals("q") )
                break

            val suggestion = spellChecker.suggest(word,1)
            if (suggestion.size == 0)
                println("it spelt correctly")
            else
                println(suggestion)
        }

    }

}



