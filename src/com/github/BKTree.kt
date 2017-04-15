package com.github

/**
 *
 * @author Mostafa Asgari
 * @since 4/13/17
 */
class BKTree( private val distanceFunc : (String,String) -> Int ) {

    private var root : Node? = null

    fun add(word : String) {

        if( root == null ){
            root = Node(word)
        }
        else {
            add( root , word )
        }

    }

    private fun add( node : Node? , word : String ) {

        if( node == null )
            return

        val distance = distanceFunc(node?.word,word)

        //it means that two string is the same
        if( distance == 0 )
            return

        if( node.children[distance] == null ){
            node.children.put( distance , Node(word) )
        }
        else{
            add( node.children[distance] , word )
        }

    }

    fun getSpellSuggestion( word: String , tolerance : Int = 1 ) : List<String> {

        if( root != null ) {
            val suggestion = getSpellSuggestion(root!!, word.decapitalize(), tolerance)
            if( suggestion.size == 1 && suggestion[0].decapitalize().equals(word) )
                return listOf()
            else
                return suggestion
        }

        return listOf()

    }
    private fun getSpellSuggestion( node : Node , word: String , tolerance : Int = 1 ) : List<String> {

        val result : MutableList<String> = mutableListOf()

        val distance = distanceFunc( word , node.word )

        //the word has spelt correctly
        if( distance == 0 ){
            return listOf(word)
        }

        if( distance <= tolerance )
            result.add( node.word )

        // iterate over the children having tolerance in range (distance-tolerance , distance+tolerance)
        val start = if ((distance-tolerance) < 0) 1 else distance-tolerance
        val end = distance+tolerance

        var find = false
        for( dist in start..end ){

            node.children[dist]?.let {
                val similarWordResult = getSpellSuggestion( it , word , tolerance )

                if( similarWordResult.size == 1 && similarWordResult[0].decapitalize().equals(word) ) {
                    result.clear()
                    result.add(similarWordResult[0])
                    find = true
                }
                else {
                    for (similarWord in similarWordResult)
                        result.add(similarWord)
                }
            }

            if(find)
                break

        }

        return result
    }

}