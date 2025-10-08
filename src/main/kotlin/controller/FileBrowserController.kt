package kz.controller

import javafx.application.Platform
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.control.ProgressBar
import javafx.stage.FileChooser
import javafx.stage.Stage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kz.util.FileUtil
import java.awt.Desktop
import java.io.File

class FileBrowserController {

    private val scope = CoroutineScope(Dispatchers.Default)

    @FXML
    private lateinit var selectedFileLabel: Label

    @FXML
    private lateinit var progressBar: ProgressBar

    @FXML
    fun onBrowseFile() {
        val fileChooser = FileChooser()
        fileChooser.title = "choose file"
        fileChooser.initialDirectory = FileUtil.defaultDirectory
        fileChooser.extensionFilters.addAll(FileChooser.ExtensionFilter("docx",  "*.docx"))

        val stage = selectedFileLabel.scene.window as Stage
        val selectedFile: File = fileChooser.showOpenDialog(stage) ?: return

        Platform.runLater {
            selectedFileLabel.text = "Converting..."
            progressBar.isVisible = true
            progressBar.progress = ProgressBar.INDETERMINATE_PROGRESS
        }

        selectedFileLabel.text = "Converting..."
        scope.launch {
            try {
                val outputFile = FileUtil.convertDocxToPdf(selectedFile.absolutePath)
                Platform.runLater {
                    progressBar.isVisible = false
                    selectedFileLabel.text = "Converted to PDF: ${selectedFile.name}"

                    if (Desktop.isDesktopSupported()) {
                        Desktop.getDesktop().open(outputFile)
                    }
                }
            } catch (e: Exception) {
                Platform.runLater {
                    progressBar.isVisible = false
                    selectedFileLabel.text = "Error: ${e.message}"
                }
            }
        }
    }

}
