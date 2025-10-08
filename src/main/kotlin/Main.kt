package kz

import javafx.application.Application
import javafx.application.Application.launch
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage

class Main: Application() {

    override fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader(javaClass.getResource("/views/file_browser.fxml"))
        val scene = Scene(fxmlLoader.load(), 1200.0, 600.0)
        stage.title = "Pdf Converter"
        stage.scene = scene
        stage.show()
    }

}

fun main() {
    launch()
}