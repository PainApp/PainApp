package xyz.painapp.pocketdoc.entities

import java.net.URL

/**
 * Created by keyur on 1/23/18.
 * Package: xyz.painapp.pocketdoc.entities as part of PocketDoc
 */
class HTTPUrlMethod(var url: URL, var methodString: String, var dataList: List<String>?) {
    companion object {
        val POST = "POST"
        val GET = "GET"
    }

    fun getDataAsString() : String {
        var result = ""

        if (dataList != null) {
            for (i in dataList!!.indices) {
                result += dataList!![i]
                if (i != dataList!!.size - 1) {
                    result += "&"
                }
            }
        }

        return result
    }


    init {
        if (this.methodString != HTTPUrlMethod.POST || this.methodString != HTTPUrlMethod.GET)
            throw IllegalArgumentException("Method string must be of HTTPMethod Type")

        if (this.methodString == HTTPUrlMethod.GET && dataList != null) {
            throw IllegalArgumentException("Cannot provide parameters for GET method, perhaps you meant to use POST")
        }
    }
}