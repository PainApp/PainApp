package xyz.painapp.pocketdoc.entities

import org.json.JSONObject
import java.net.URL

/**
 * Created by keyur on 1/23/18.
 * Package: xyz.painapp.pocketdoc.entities as part of PocketDoc
 */
class HTTPUrlMethod(var url: URL, var methodString: String, var dataList: JSONObject?) {
    companion object {
        const val POST = "POST"
        const val GET = "GET"
        const val RESPONSE_CODE_STR = "response_code"

        private const val SERVER_ADDRESS = "155.246.89.120:8080"
        const val BASE_URL: String = "http://$SERVER_ADDRESS/PocketDoc"
        val BODY_REGION_URL: URL = URL("$BASE_URL/body_regions")
        val SPECIFIC_REGION_URL: URL = URL("$BASE_URL/specific_regions")
    }

    fun getDataAsBytes() : ByteArray? {
        if (dataList != null) {
            return dataList.toString().toByteArray()
        }
        return null
    }


    init {
        if (this.methodString != HTTPUrlMethod.POST && this.methodString != HTTPUrlMethod.GET)
            throw IllegalArgumentException("Method string must be either GET or POST Type")

        if (this.methodString == HTTPUrlMethod.GET && dataList != null) {
            throw IllegalArgumentException("Cannot provide parameters for GET method, perhaps you meant to use POST")
        }

    }
}