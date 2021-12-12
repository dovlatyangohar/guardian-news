package com.example.guardiannewsapp.ui.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.guardiannewsapp.MainActivity
import com.example.guardiannewsapp.R
import com.example.guardiannewsapp.network.models.Result
import com.example.guardiannewsapp.ui.viewmodels.FeedViewModel
import com.example.guardiannewsapp.ui.viewmodels.SharedViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.internal.Internal.instance


class FeedFragment : Fragment(), FeedAdapter.OnItemClickListener {
    private val model: SharedViewModel by activityViewModels()
    private lateinit var feedViewModel: FeedViewModel
    private lateinit var adapter: FeedAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        feedViewModel = ViewModelProvider(this)[FeedViewModel::class.java]

        val recyclerView:RecyclerView  = view.findViewById(R.id.rvCurrentFeed)

        recyclerView.layoutManager = LinearLayoutManager(context)
        feedViewModel.viewModelScope.launch(Dispatchers.Main) {
            feedViewModel.getNews().observe(this@FeedFragment) { news: List<Result> ->
                 adapter = FeedAdapter(news, this@FeedFragment)
                recyclerView.adapter = adapter
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            FeedFragment()
    }

    override fun onItemClick(result: Result) {
        val urlIntent = Intent(Intent.ACTION_VIEW)
        urlIntent.data = Uri.parse(result.webUrl)
        startActivity(urlIntent)
    }

    override fun addToDB(result: Result) {
        model.selected.postValue(result)
        adapter.notifyDataSetChanged()
        Toast.makeText(context, "ADDED TO DB", Toast.LENGTH_LONG).show()
    }
}