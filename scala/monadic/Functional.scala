
bject Functional extends App {

    /**
     * Some functional concepts in Scala
     */

    // Declare and initilize some collections to apply the monadic functions 

    val someNumbers = Seq(1, 2, 3, 4, 5);
    val teams = Seq("Arsenal", "FC Porto", "AC Milan", "Cheltenham Town", "Barca");

    /**
     * Monadic Functions
     *  1 - Map = Apply function to each element in a collection
     *  2 - Fold (Left/Right) / Reduce = Reduce elements in a collection to a single value
     *  3 - Filter => Create a new collection based on predicate
     *  4 - Partition => Partition collection into two collections based on a predicate
     *  5 - Zip => "Join" two collections together
     */

    // Map
    println("Map each element of x to the function x*x == " +
            someNumbers.map(x => x * x).toString()) // Multiply each element in the
        collection with itself

        // Fold
        println("Fold a to sum of collection based on function a + b == " +
                someNumbers.fold(0)((a, b) => a + b)) // Sum of the collection


        //filter
        println("Filter collection no 'a' allowed == " + teams.filter(x
                    => !x.contains('a')).toString())
}
