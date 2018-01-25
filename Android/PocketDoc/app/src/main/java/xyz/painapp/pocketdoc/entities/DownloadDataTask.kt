package xyz.painapp.pocketdoc.entities

import android.os.AsyncTask
import android.util.Log
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
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

            if (urlMethod.methodString == HTTPUrlMethod.POST) {
                myConnection.doOutput = true
                myConnection.setRequestProperty("Content-Type", "application/json")
                val outputStream = myConnection.outputStream
                outputStream.write(urlMethod.getDataAsBytes())
                myConnection.outputStream.close()


                if (myConnection.responseCode != 200) {
                    // TODO add some way of letting the user know the data is not available
                    Log.e("Response Code", myConnection.responseMessage)
                    return JSONObject()
                }
            }

            if (myConnection.responseCode == 200) {
                val inputStream = BufferedReader(InputStreamReader(myConnection.inputStream))
                results = readStream(inputStream)
            }
            myConnection.disconnect()

        } catch (e: Exception) {
            e.printStackTrace()

        } finally {
            myConnection?.disconnect()
        }


        return results
    }

    private fun readStream(inputStream: BufferedReader): JSONObject {

        return try {
            val strBuilder = StringBuilder()
            inputStream.forEachLine { s: String -> strBuilder.append(s) }

            JSONObject(strBuilder.toString())
        } catch (e: IOException) {
            e.printStackTrace()
            JSONObject()
        }
    }

}