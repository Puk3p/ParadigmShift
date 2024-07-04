import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

data class HistoryLogRecord(val startTimestamp: Long, val commandLine: String) : Comparable<HistoryLogRecord> {
    override fun compareTo(other: HistoryLogRecord): Int = this.startTimestamp.compareTo(other.startTimestamp)
}

fun parseDateTime(dateTimeStr: String): LocalDateTime {
    val cleanedDateTimeStr = dateTimeStr.replace(Regex("\\s+"), " ").trim()
    val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
    return LocalDateTime.parse(cleanedDateTimeStr, dateFormatter)
}

fun parseHistoryLog(filePath: String): List<HistoryLogRecord> {
    val fileContent = File(filePath).readText()
    val logEntries = fileContent.split("\n\n").takeLast(50)
    val records = mutableListOf<HistoryLogRecord>()

    logEntries.forEach { entry ->
        val lines = entry.split("\n").map { it.trim() }
        val startLine = lines.find { it.startsWith("Start-Date:") }
        val commandLine = lines.find { it.startsWith("Commandline:") } ?: ""

        if (startLine != null) {
            val startDateTimeStr = startLine.removePrefix("Start-Date:").trim()
            val startDateTime = parseDateTime(startDateTimeStr)
            val startTimestamp = startDateTime.atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli()

            records.add(HistoryLogRecord(startTimestamp, commandLine.removePrefix("Commandline:").trim()))
        }
    }
    return records
}

fun main() {
    val filePath = "/home/george/Desktop/aici/history.log.1"
    val historyRecords = parseHistoryLog(filePath)

    val sortedRecords = historyRecords.sorted()

    sortedRecords.forEach {
        println("Timestamp: ${it.startTimestamp}, Command: ${it.commandLine}")
    }
}
