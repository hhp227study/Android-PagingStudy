package com.hhp227.pagingstudy.api

import android.content.res.AssetManager
import com.hhp227.pagingstudy.model.SampleData
import org.json.JSONArray
import org.json.JSONObject
import java.io.FileNotFoundException
import java.nio.charset.Charset
import kotlin.jvm.Throws

class MainService(private val assets: AssetManager) {
    private fun getJsonData(page: Int) = assets.open("sample$page.json").use { input ->
        ByteArray(input.available()).let { buffer ->
            input.read(buffer)
            String(buffer, Charset.defaultCharset())
        }
    }

    private fun jsonObjectToData(jsonObject: JSONObject): SampleData {
        val id = jsonObject.getInt("id")
        val name = jsonObject.getString("name")
        val image = jsonObject.getString("image")
        return SampleData(id, name, image)
    }

    fun getData(pageIndex: Int) = try {
        JSONArray(getJsonData(pageIndex)).map { jsonObjectToData(it as JSONObject) }
    } catch (e: Exception) {
        emptyList()
    }

    private inline fun <reified T> JSONArray.map(transform: (Any) -> T): List<T> {
        return List(this.length()) { i ->
            transform(get(i))
        }
    }
}