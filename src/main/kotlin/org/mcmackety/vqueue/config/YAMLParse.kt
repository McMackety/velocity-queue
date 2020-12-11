package org.mcmackety.vqueue.config

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule
import java.io.File
import java.nio.file.FileSystems
import java.nio.file.Files
import java.nio.file.Path
import kotlin.reflect.KClass

object YAMLParse {
    private val mapper = let {
        val mapper = ObjectMapper(YAMLFactory())
        mapper.registerModule(KotlinModule())
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true)
        mapper.propertyNamingStrategy = PropertyNamingStrategy.LOWER_CAMEL_CASE
        mapper
    }


    fun <T: Any> parseFile(path: Path, fileName: String, dto: KClass<T>): T {
        return Files.newBufferedReader(File(path.toFile(), fileName).toPath()).use { mapper.readValue(it, dto.java) }
    }

    fun writeFile(path: Path, fileName: String, obj: Any) {
        mapper.writeValue(File(path.toFile(), fileName), obj)
    }
}