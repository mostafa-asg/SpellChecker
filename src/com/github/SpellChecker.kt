package com.github

/**
 *
 * @author Mostafa Asgari
 * @since 4/13/17
 */

class SpellChecker private constructor(private val words : Collection<String> ,
                                       distanceFunc : (String,String) -> Int) {

    companion object Builder {

        private val wordsList : MutableList<String> = mutableListOf()
        private var distanceFunc : (String,String) -> Int = ::LevenshteinDistance

        fun load(vararg words : String) : Builder {

            for( word in words )
                wordsList.add( word )

            return this
        }

        fun withEditDistanceFunction( func : (str1:String,str2:String)->Int ) : Builder {
            distanceFunc = func

            return this
        }

        fun build() : SpellChecker {

            if( wordsList.size == 0 )
                throw Exception("please specify words list")

            return SpellChecker(wordsList , distanceFunc)
        }

    }

    private val bkTree : BKTree

    init {

        bkTree = BKTree(distanceFunc)
        for( word in words ){
            bkTree.add( word )
        }

    }

    fun suggest(misspellWord : String , tolerance : Int = 1) : List<String> {

        return bkTree.getSpellSuggestion( misspellWord , tolerance )

    }

}