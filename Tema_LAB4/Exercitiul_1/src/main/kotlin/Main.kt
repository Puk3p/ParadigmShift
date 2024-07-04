import khttp.responses.Response
import org.json.JSONObject

interface Parser {
    fun parse(text: String): Map<String, Any>
}

class JsonParser : Parser {
    override fun parse(text: String): Map<String, Any> {
        val jsonObject = JSONObject(text)
        val map = jsonObject.toMap()
        return map
    }
}

class Crawler(private val url: String, private val parser: Parser) {
    fun getResource(): Response {
        return khttp.get(url)
    }
    //rescriu
    private fun getParserforContent(content: String) = when (content) {
            "application/json"-> JsonParser()
            else -> TODO()
    }

    fun processContent(content: String): Map<String, Any> {
        return getParserforContent(content).parse(content)
    }
}

fun main() {
    val url = "http://rss.cnn.com/rss/edition.rss"

    val jsonCrawler = Crawler(url, JsonParser())
    val jsonResponse = jsonCrawler.getResource()
    val jsonContent = jsonResponse.text
    val jsonData = jsonCrawler.processContent(jsonContent)
    println("JSON: $jsonData")
}