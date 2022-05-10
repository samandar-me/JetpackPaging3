package com.example.pagingthree

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pagingthree.adapter.RvAdapter
import com.example.pagingthree.databinding.ActivityMainBinding
import com.example.pagingthree.viewmodel.MainViewModel
import kotlinx.coroutines.flow.collectLatest

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var rvAdapter: RvAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()

    }
    private fun initViews() {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        rvAdapter = RvAdapter()
        setupRv()
        initViewModel()
    }

    private fun setupRv() =  binding.recyclerView.apply {
        val decoration = DividerItemDecoration(this@MainActivity, RecyclerView.VISIBLE)
        layoutManager = LinearLayoutManager(this@MainActivity)
        adapter = rvAdapter
        addItemDecoration(decoration)
    }
    private fun initViewModel() {
        lifecycleScope.launchWhenCreated {
            viewModel.getListData().collectLatest {
                rvAdapter.submitData(it)
            }
        }
    }
}