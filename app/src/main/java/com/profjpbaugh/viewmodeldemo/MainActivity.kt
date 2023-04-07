package com.profjpbaugh.viewmodeldemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.profjpbaugh.viewmodeldemo.ui.main.TopFragment
import com.profjpbaugh.viewmodeldemo.ui.main.BottomFragment
import org.json.JSONArray
import org.json.JSONObject

//API KEY: live_6j9bzI8mJIozy4aIKa6pnSAy4AA2ymCTgVSOiNrdG1sNX02KJozuh3vZca4g2ZBt

class MainActivity : AppCompatActivity(), TopFragment.ButtonListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    override fun onCatButtonClick() {
        var catUrl = "https://api.thecatapi.com/v1/breeds" + "?api_key=live_6j9bzI8mJIozy4aIKa6pnSAy4AA2ymCTgVSOiNrdG1sNX02KJozuh3vZca4g2ZBt"
        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(
            Request.Method.GET, catUrl,
            { response ->
                var catsArray : JSONArray = JSONArray(response)
                for(i in 0 until catsArray.length())
                {
                    var theCat : JSONObject = catsArray.getJSONObject(i)
                    Log.i("TopFragment", "Cat Name: ${theCat.getString("name")}")
                    Log.i("TopFragment", "Cat Description: ${theCat.getString("description")}")
                }
            },
            {
                Log.i("TopFragment", "That didn't work!")
            })
        queue.add(stringRequest)
    }
}