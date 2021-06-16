package com.hhp227.pagingstudy.api

import android.content.res.AssetManager
import com.hhp227.pagingstudy.model.SampleData
import org.json.JSONArray
import org.json.JSONObject
import java.nio.charset.Charset

class MainService(private val assets: AssetManager) {
    fun test(page: Int) = assets.open("sample1.json").use { input ->
        ByteArray(input.available()).let { buffer ->
            input.read(buffer)
            String(buffer, Charset.defaultCharset())
        }
    }

    fun getData(pageIndex: Int) = JSONArray(test(pageIndex)).map { jsonObjectToData(it as JSONObject) }

    private fun jsonObjectToData(jsonObject: JSONObject): SampleData {
        val id = jsonObject.getInt("id")
        val name = jsonObject.getString("name")
        val image = jsonObject.getString("image")
        return SampleData(id, name, image)
    }

    private inline fun <reified T> JSONArray.map(transform: (Any) -> T): List<T> {
        return List(this.length()) { i ->
            transform(get(i))
        }
    }
}