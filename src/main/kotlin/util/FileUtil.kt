package kz.util

import org.docx4j.Docx4J
import org.docx4j.openpackaging.packages.WordprocessingMLPackage
import java.io.File
import java.io.FileOutputStream

object FileUtil {

    val defaultDirectory = File(System.getProperty("user.home"), "Downloads")

    fun convertDocxToPdf(filePath: String): File {
        try {
            val inputFile = File(filePath)
            val outputFile = File(inputFile.parent, inputFile.nameWithoutExtension + ".pdf")

            val wordMLPackage = WordprocessingMLPackage.load(inputFile)

            FileOutputStream(outputFile).use { os ->
                Docx4J.toPDF(wordMLPackage, os)
            }

            println("✅ PDF сохранён: ${outputFile.absolutePath}")
            return outputFile

        } catch (e: Exception) {
            e.printStackTrace()
            throw RuntimeException("Ошибка конвертации: ${e.message}", e)
        }
    }

}