package kz.util

import java.util.*

object AppConfig {

    private val props = Properties()

    init {
        val inputStream = this::class.java.classLoader.getResourceAsStream("application.properties")
            ?: throw IllegalStateException("application.properties not found in resources!")
        props.load(inputStream)
    }

    fun getProp(key: String): String? = props.getProperty(key)

}