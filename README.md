It uses [BK-Tree](https://en.wikipedia.org/wiki/BK-tree) to find out misspell words.In the resource folder, there is a file of correct words.You can use it as a database of words.

Usage (load correct words from a file) :
```kotlin
 val spellChecker = SpellChecker.Builder.loadFromFile("words.txt").build()
 println( spellChecker.suggest("worlt",1) )
```
you can also add words manually using load method :
```kotlin
val spellChecker = SpellChecker.Builder.load("world","word","work","workshop").build()
println( spellChecker.suggest("worlt",1) )
```
Also in [Program.kt](https://github.com/mostafa-asg/SpellChecker/blob/master/src/com/github/Program.kt) you can find sample usage.

