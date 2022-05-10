package com.example.pagingthree.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.pagingthree.model.CharacterData
import com.example.pagingthree.network.RetroInstance
import com.example.pagingthree.network.RetroService
import com.example.pagingthree.source.CharacterPagingSource
import kotlinx.coroutines.flow.Flow

class MainViewModel : ViewModel() {

    private val retroService: RetroService =
        RetroInstance.getRetroInstance().create(RetroService::class.java)

    fun getListData(): Flow<PagingData<CharacterData>> {
        return Pager(config = PagingConfig(pageSize = 34, maxSize = 200),
            pagingSourceFactory = { CharacterPagingSource(retroService) }).flow.cachedIn(
            viewModelScope
        )
    }
}