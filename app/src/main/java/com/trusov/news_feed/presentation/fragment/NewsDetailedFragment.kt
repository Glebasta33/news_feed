package com.trusov.news_feed.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.trusov.news_feed.databinding.FragmentNewsDetailedBinding
import com.trusov.news_feed.di.App
import com.trusov.news_feed.domain.entity.News

class NewsDetailedFragment : Fragment() {

    private var _binding: FragmentNewsDetailedBinding? = null
    private val binding: FragmentNewsDetailedBinding
        get() = _binding ?: throw RuntimeException("FragmentNewsDetailedBinding == null")
    private lateinit var news: News

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as App).component.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            news = it.getParcelable<News>("News") ?: throw RuntimeException("News argument is null")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsDetailedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            tvTitleDetailed.text = news.title
            tvCreatedDetailed.text = news.created
            tvDescriptionDetailed.text = news.description
            Glide.with(requireActivity().applicationContext).load(news.imageUrl).into(ivImageDetailed)
        }
    }
}