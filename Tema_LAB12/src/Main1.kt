import java.io.File

private const val alfabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

fun cesarCifru(plainText : String, rotationKey : Int) : String {
    var cipherText = ""
    val plainTextCase = plainText.uppercase()

    for (character in plainTextCase) {
        val characterValue = alfabet.indexOf(character)
        val shiftCharacter = (characterValue + rotationKey) % alfabet.length
        cipherText += alfabet[shiftCharacter]
    }
    return cipherText
}


fun processFile(filePath: String, shift: Int) {
    val file = File(filePath)
    val result = file.readLines().map { line ->
        line.split("\\s+".toRegex()).joinToString(" ") { word ->
            if (word.length in 4..7) cesarCifru(word, shift) else
                word
        }
    }.joinToString("\n")
    println(result)
}


fun main() {
    val filePath = "text.txt"
    val shift = 3
    processFile(filePath, shift)
}