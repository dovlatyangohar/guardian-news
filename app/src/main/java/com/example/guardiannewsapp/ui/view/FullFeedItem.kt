package com.example.guardiannewsapp.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.guardiannewsapp.R

class FullFeedItem : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_full_feed_item, container, false)
    }

    companion object {
        @JvmStatic
        fun resulnewInstance() =
            FullFeedItem()
    }
}