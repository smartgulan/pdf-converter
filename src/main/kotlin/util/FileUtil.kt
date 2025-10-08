package kz.util

import org.jodconverter.local.JodConverter
import org.jodconverter.local.office.LocalOfficeManager
import java.io.File

object FileUtil {

    val defaultDirectory = File(System.getProperty("user.home"), "Downloads")

    fun convertDocxToPdf(filePath: String): File {
        try {
            val officeManager = LocalOfficeManager.install()
            officeManager.start()

            val inputFile = File(filePath)
            val outputFile = File(inputFile.parent, inputFile.nameWithoutExtension + ".pdf")

            JodConverter
                .convert(inputFile)
                .to(outputFile)
                .execute()

            officeManager.stop()
            println("✅ PDF сохранён: ${outputFile.absolutePath}")
            return outputFile
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

}