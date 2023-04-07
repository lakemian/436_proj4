package com.profjpbaugh.viewmodeldemo.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.profjpbaugh.viewmodeldemo.databinding.FragmentBottomBinding


class BottomFragment : Fragment() {

    companion object {
        fun newInstance() = BottomFragment()
    }

    private lateinit var viewModel: BottomViewModel

    private lateinit var binding : FragmentBottomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BottomViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentBottomBinding.inflate(inflater, container, false)
        return binding.root
    }}