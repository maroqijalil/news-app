package com.maroqi.newsapplication.modules.bookmark.presentation.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.maroqi.newsapplication.databinding.FragmentBookmarkBinding
import com.maroqi.newsapplication.modules.bookmark.presentation.viewmodels.BookmarkViewModel
import com.maroqi.newsapplication.modules.bookmark.presentation.views.adapters.BookmarkListAdapter
import com.maroqi.newsapplication.modules.news.presentation.screens.NewsListFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookmarkFragment : Fragment() {
    private var binding: FragmentBookmarkBinding? = null

    private val viewModel: BookmarkViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookmarkBinding.inflate(inflater)

        initList()

        return binding?.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getBookmarks()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (binding != null) {
            binding = null
        }
    }

    private fun initList() {
        if (binding != null) {
            val bookmarkListAdapter = BookmarkListAdapter(listOf(), { item ->
                val action = NewsListFragmentDirections.toDetail(item)
                findNavController().navigate(action)
            }, { viewModel.bookmark(it) })

            binding!!.rvNews.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context)
                adapter = bookmarkListAdapter
            }

            viewModel.resetBookmarks()
            viewModel.bookmarks.observe(viewLifecycleOwner) {
                bookmarkListAdapter.changeList(it)
            }
        }
    }
}
