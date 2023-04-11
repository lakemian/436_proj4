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
import org.json.JSONException
import org.json.JSONObject

//API KEY: live_6j9bzI8mJIozy4aIKa6pnSAy4AA2ymCTgVSOiNrdG1sNX02KJozuh3vZca4g2ZBt

class MainActivity : AppCompatActivity(), TopFragment.ButtonListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCatButtonClick(catName: String) {
        val bottomFragment = supportFragmentManager.findFragmentById(R.id.bottomFragment) as BottomFragment

        var catUrl = "https://api.thecatapi.com/v1/breeds" + "?api_key=live_6j9bzI8mJIozy4aIKa6pnSAy4AA2ymCTgVSOiNrdG1sNX02KJozuh3vZca4g2ZBt"
        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(
            Request.Method.GET, catUrl,
            { response ->
                var catsArray = JSONArray(response)
                for(i in 0 until catsArray.length())
                {
                    var theCat : JSONObject = catsArray.getJSONObject(i)
                    if(theCat.getString("name").equals(catName))
                    {
                        try
                        {
                            bottomFragment.catInfo(theCat.getString("name"), theCat.getString("temperament"), theCat.getString("origin"), theCat.getJSONObject("image").getString("url") )
                        }
                        catch(e: JSONException)
                        {
                            bottomFragment.catInfo(theCat.getString("name"), theCat.getString("temperament"), theCat.getString("origin"), "https://thumbs.dreamstime.com/b/no-image-available-icon-flat-vector-no-image-available-icon-flat-vector-illustration-132482953.jpg" )
                        }
                        break
                    }
                }
            },
            {
                Log.i("TopFragment", "That didn't work!")
            })
        queue.add(stringRequest)
    }
}