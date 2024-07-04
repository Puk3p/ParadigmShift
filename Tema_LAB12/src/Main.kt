//ex_1

fun filterNumbers(numbers: List<Int>) : List<Int> {
    return numbers.filter { it >= 5 }
}

fun pairNumbers(numbers: List<Int>) : List<Pair<Int, Int>> {
    return numbers.chunked(2)
        .map { it[0] to it[1] }
}

fun multiplyPerechi(perechi : List<Pair<Int, Int>>) : List<Int> {
    return perechi.map { (a, b) -> a * b }
}

fun sumaProduct(products : List<Int>) : Int {
    return products.sum()
}

fun main() {
    val numbers = listOf(1, 21, 75, 39, 7, 2, 35, 3, 31, 7, 8)

    val filteredNumbers = filterNumbers(numbers)
    val pairedNumbers = pairNumbers(filteredNumbers)
    val products = multiplyPerechi(pairedNumbers)
    val resultSum = sumaProduct(products)

    println("Numere filtrate: $filteredNumbers")
    println("Numere imperecheate: $pairedNumbers")
    println("Produsele perechilor: $products")
    println("Suma produselor: $resultSum")
}
