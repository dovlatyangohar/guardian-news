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
import com.example.guardiannewsapp.R
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.guardiannewsapp.ui.viewmodels.FavNewsViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.guardiannewsapp.network.models.Result
import com.example.guardiannewsapp.ui.viewmodels.SharedViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class FavNewsFragment : Fragment(), FeedAdapter.OnItemClickListener {
    private lateinit var favNewsViewModel: FavNewsViewModel
    private val model: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(
            R.layout.fragment_fav_news,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favNewsViewModel = ViewModelProvider(this)[FavNewsViewModel::class.java]

        val recyclerView: RecyclerView = view.findViewById(R.id.rvFavorites)

        recyclerView.layoutManager = LinearLayoutManager(context)

        favNewsViewModel.viewModelScope.launch(Dispatchers.Main) {
            model.selected.value?.let {
                favNewsViewModel.insertResultToDB(it)
            }
        }

        favNewsViewModel.getAllFavNews().observe(this) { news ->
            val adapter = FeedAdapter(news, this@FavNewsFragment)
            recyclerView.adapter = adapter
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            FavNewsFragment()
    }

    override fun onItemClick(result: Result) {
        val urlIntent = Intent(Intent.ACTION_VIEW)
        urlIntent.data = Uri.parse(result.webUrl)
        startActivity(urlIntent)
    }

    override fun addToDB(result: Result) {
        Toast.makeText(context, "item has already been added to offline usage", Toast.LENGTH_LONG)
            .show()
    }
}