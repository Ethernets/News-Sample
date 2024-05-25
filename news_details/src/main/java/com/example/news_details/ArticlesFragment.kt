package com.example.news_details

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.news_details.databinding.FragmentArticlesBinding
import dagger.Lazy
import kotlinx.coroutines.launch
import javax.inject.Inject

class ArticlesFragment : Fragment(R.layout.fragment_articles) {
    @Inject lateinit var articleViewModelFactory: Lazy<ArticlesViewModel.Factory>
    private val articlesViewModel: ArticlesViewModel by viewModels {articleViewModelFactory.get()}
    private val componentViewModel: NewsComponentViewModel by viewModels()
    private var articleAdapter: ArticleAdapter? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        componentViewModel.newsDetailsComponent.inject(this)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        articleAdapter = ArticleAdapter()

        val binding = FragmentArticlesBinding.bind(view)
        with(binding.list) {
            layoutManager = LinearLayoutManager(context)
            adapter = articleAdapter
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                articlesViewModel.articles.collect {
                    articleAdapter?.submitList(it)
                }
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        articleAdapter = null
    }
}