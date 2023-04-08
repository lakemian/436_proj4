package com.profjpbaugh.viewmodeldemo.ui.main

import android.R
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.profjpbaugh.viewmodeldemo.databinding.FragmentTopBinding
import org.json.JSONArray
import org.json.JSONObject


class TopFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var binding : FragmentTopBinding
    private var activityCallBack : ButtonListener ?= null

    interface ButtonListener {
        fun onCatButtonClick(catType: String)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try{
            activityCallBack = context as ButtonListener
        }
        catch(e : ClassCastException){
            throw ClassCastException(context.toString() + "must implement ButtonListener")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val catUrl = "https://api.thecatapi.com/v1/breeds" + "?api_key=live_6j9bzI8mJIozy4aIKa6pnSAy4AA2ymCTgVSOiNrdG1sNX02KJozuh3vZca4g2ZBt"
        val queue = Volley.newRequestQueue(requireContext().applicationContext)
        val catNames = ArrayList<String>()
        catNames.add("Select Cat Breed")
        val stringRequest = StringRequest(
            Request.Method.GET, catUrl,
            { response ->
                val catsArray = JSONArray(response)
                for(i in 0 until catsArray.length())
                {
                    val theCat : JSONObject = catsArray.getJSONObject(i)
                    catNames.add(theCat.getString("name"))
                }
            },
            {
                Log.i("TopFragment", "That didn't work!")
            })
        queue.add(stringRequest)
        val catSpinner = ArrayAdapter(
            requireContext().applicationContext,
            R.layout.simple_spinner_dropdown_item,
            catNames)

        binding.spinner.adapter = catSpinner
        binding.spinner.onItemSelectedListener = this
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTopBinding.inflate(inflater, container, false)
        binding.spinner.onItemSelectedListener = this
        binding.btnGetCatData.setOnClickListener{
            onCatButtonClick()
        }
        return binding.root
    }

    private fun onCatButtonClick()
    {
        activityCallBack?.onCatButtonClick(binding.spinner.selectedItem.toString())
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }


}