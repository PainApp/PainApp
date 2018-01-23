package xyz.painapp.pocketdoc.entities

import android.os.AsyncTask
import android.util.Log
import org.json.JSONArray
import java.io.BufferedInputStream
import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
/**
 * Created by keyur on 1/23/18.
 * Package: xyz.painapp.pocketdoc.entities as part of PocketDoc
 */

class DownloadDataTask : AsyncTask<HTTPUrlMethod, Int, JSONArray>() {
    override fun doInBackground(vararg urlList: HTTPUrlMethod?): JSONArray {
        var results = JSONArray()
        val urlMethod = urlList[0]
        var myConnection: HttpURLConnection? = null

        try {
            val url = urlMethod!!.url
            myConnection = url.openConnection() as HttpURLConnection
            myConnection.requestMethod = urlMethod.methodString
            myConnection.doInput = true

            if (urlMethod.methodString == HTTPUrlMethod.POST) {
                myConnection.doOutput = true
                val bufferedWriter = BufferedWriter(OutputStreamWriter(myConnection.outputStream))
                bufferedWriter.write(urlMethod.getDataAsString())

                if (myConnection.responseCode != 200) {
                    // TODO add some way of letting the user know the data is not available
                    Log.e("Response Code", Integer.toString(myConnection.responseCode))
                }
                myConnection.outputStream.close()
            }

            if (myConnection.responseCode == 200) {
                val inputStream = BufferedInputStream(myConnection.inputStream)

                // TODO figure out how to read input as JSON
            }
            myConnection.disconnect()

        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            myConnection?.disconnect()
        }


        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}