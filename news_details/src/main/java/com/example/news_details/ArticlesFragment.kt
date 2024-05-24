package com.example.news_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.news_details.databinding.FragmentArticlesBinding
import kotlinx.coroutines.launch

class ArticlesFragment : Fragment(R.layout.fragment_articles) {
    private val articlesViewModel: ArticlesViewModel by viewModels()
    private var articleAdapter: ArticleAdapter? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                articlesViewModel.articles.collect {
                    articleAdapter?.submitList(it)
                }
            }
        }

        val binding = FragmentArticlesBinding.bind(view)
        with(binding.list) {
            layoutManager = LinearLayoutManager(context)
            articleAdapter = ArticleAdapter()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        articleAdapter = null
    }
}