package xyz.painapp.pocketdoc.entities

import android.os.AsyncTask
import android.util.Log
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.ConnectException
import java.net.HttpURLConnection
/**
 * Created by keyur on 1/23/18.
 * Package: xyz.painapp.pocketdoc.entities as part of PocketDoc
 */

abstract class DownloadDataTask : AsyncTask<HTTPUrlMethod, Int, JSONObject>() {
    abstract override fun onPreExecute()
    abstract override fun onPostExecute(result: JSONObject?)
    override fun doInBackground(vararg urlList: HTTPUrlMethod?): JSONObject {
        var results = JSONObject("{}")
        val urlMethod = urlList[0]
        var myConnection: HttpURLConnection? = null

        try {
            Log.i("Url", urlMethod!!.url.toString())
            val url = urlMethod.url
            myConnection = url.openConnection() as HttpURLConnection
            myConnection.requestMethod = urlMethod.methodString
            myConnection.doInput = true

            if (urlMethod.methodString == HTTPUrlMethod.POST && urlMethod.dataList != null) {
                myConnection.doOutput = true
                myConnection.setRequestProperty("Content-Type", "application/json")
                val outputStream = myConnection.outputStream
                outputStream.write(urlMethod.getDataAsBytes())
                myConnection.outputStream.close()


                if (myConnection.responseCode != 200) {
                    // TODO add some way of letting the user know the data is not available
                    Log.e("Response Code", myConnection.responseMessage)
                    return errorCodeJSON(myConnection.responseCode)
                }
            }

            if (myConnection.responseCode == 200) {
                val inputStream = BufferedReader(InputStreamReader(myConnection.inputStream))
                results = readStream(inputStream)
            }
            myConnection.disconnect()

        } catch (e: Exception) {
            e.printStackTrace()
            results = if (e is ConnectException) {
                ERROR_404
            } else {
                ERROR_500
            }

        } finally {
            myConnection?.disconnect()
        }

        return results
    }

    private fun readStream(inputStream: BufferedReader): JSONObject {

        return try {
            val retStr = StringBuilder(inputStream.readText()).toString()
            JSONObject(retStr)
        } catch (e: IOException) {
            e.printStackTrace()
            if (e is ConnectException) {
                ERROR_404
            } else {
                ERROR_500
            }
        }
    }
    companion object {
        fun errorCodeJSON(errorCode: Int): JSONObject {
            return JSONObject("{\"" + HTTPUrlMethod.RESPONSE_CODE_STR + "\":" + errorCode + "}")
        }
        val ERROR_404 = errorCodeJSON(404)
        val ERROR_500 = errorCodeJSON(500)
    }


}