package org.example
import java.io.File

fun main() {

    val inputFilePath = "ebook.txt"
    val outputFilePath = "text.txt"

    val inputText = readFile(inputFilePath)
    val processedText = processText(inputText)
    writeFile(outputFilePath, processedText)

    println("Textul modificat a fost amplasat în $outputFilePath.")
}
// metoda de citire din fisier
fun readFile(filePath: String): String {
    val file = File(filePath)
    return file.readText()
}
// metoda de scriere in fisier
fun writeFile(filePath: String, content: String) {
    val file = File(filePath)
    file.writeText(content)
}
// metoda de procesare a textului
fun processText(text: String): String {
    //caractere romanastei
    val textmodificat =  text.replace("[ăâ]".toRegex(), "a")
        .replace("[ĂÂ]".toRegex(), "A")
        .replace("[șș]".toRegex(), "s")
        .replace("[ȘȘ]".toRegex(), "S")
        .replace("[țţ]".toRegex(), "t")
        .replace("[ȚŢ]".toRegex(), "T")
        .replace("[ÎÎ]".toRegex(), "I")
        .replace("[îî]".toRegex(), "i")

    // Eliminarea spațiilor multiple din text (ramane doar unul)
    val x = textmodificat.replace(" +".toRegex(), " ")

    // Eliminarea salturilor la linie nouă multiple
    //val y = x.replace("(\\r\\n)+".toRegex(), "\n")
     val y = x.replace("\\n{2,}".toRegex(), "\n")

    // Detectarea și eliminarea numărului de pagină

    val numarPagina = "\\d+"
    val punctOptional = "\\.?"
    val spatiiOptional = "\\s*"
    val paginaFinala = "$numarPagina$punctOptional$spatiiOptional"

    val z = y.replace(paginaFinala.toRegex(), "")
    return z;
}