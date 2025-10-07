package kz

import javafx.application.Application
import javafx.application.Application.launch
import javafx.stage.Stage

class Main: Application() {

    override fun start(stage: Stage) {
        stage.title = "Pdf Converter"
        stage.show()
    }

}

fun main() {
    launch()
}