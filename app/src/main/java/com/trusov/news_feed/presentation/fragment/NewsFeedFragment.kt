package com.trusov.news_feed.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.trusov.news_feed.R
import com.trusov.news_feed.databinding.FragmentNewsFeedBinding
import com.trusov.news_feed.di.App
import com.trusov.news_feed.di.ViewModelFactory
import com.trusov.news_feed.presentation.adapter.NewsAdapter
import com.trusov.news_feed.presentation.view_model.NewsFeedViewModel
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class NewsFeedFragment : Fragment() {

    private var _binding: FragmentNewsFeedBinding? = null
    private val binding: FragmentNewsFeedBinding
        get() = _binding ?: throw RuntimeException("FragmentNewsFeedBinding == null")

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[NewsFeedViewModel::class.java]
    }
    @Inject
    lateinit var newsAdapter: NewsAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as App).component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val linearLayoutManager = LinearLayoutManager(requireActivity().applicationContext)
        binding.rvNewsFeed.adapter = newsAdapter
        binding.rvNewsFeed.layoutManager = linearLayoutManager

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.news.collectLatest { pagingData ->
                newsAdapter.submitData(pagingData)
            }
        }

        newsAdapter.onNewsItemClickListener = {
            val newsArgs = Bundle().apply {
                putParcelable("News", it)
            }
            findNavController().navigate(R.id.action_newsFeedFragment_to_newsDetailedFragment, newsArgs)
        }
    }

}