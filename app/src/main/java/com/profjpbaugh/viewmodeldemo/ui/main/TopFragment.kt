package com.profjpbaugh.viewmodeldemo.ui.main

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.profjpbaugh.viewmodeldemo.databinding.FragmentTopBinding


class TopFragment : Fragment() {

    companion object {
        fun newInstance() = TopFragment()
    }

    private lateinit var viewModel: TopViewModel
    private lateinit var binding : FragmentTopBinding
    private var activityCallBack : ButtonListener ?= null
    interface ButtonListener {
        fun onCatButtonClick()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(TopViewModel::class.java)
        // TODO: Use the ViewModel

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
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTopBinding.inflate(inflater, container, false)
        binding.btnGetCatData.setOnClickListener{
            onCatButtonClick(it)
        }
        return binding.root
    }

    private fun onCatButtonClick(view: View)
    {
        activityCallBack?.onCatButtonClick()
    }



}