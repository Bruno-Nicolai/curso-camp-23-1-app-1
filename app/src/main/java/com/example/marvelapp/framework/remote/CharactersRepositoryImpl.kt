package com.example.marvelapp.framework.remote

import androidx.paging.PagingSource
import bruno.nicolai.core.data.repository.CharactersRemoteDataSource
import bruno.nicolai.core.data.repository.CharactersRepository
import bruno.nicolai.core.domain.model.Character
import com.example.marvelapp.framework.network.response.DataWrapperResponse
import com.example.marvelapp.framework.paging.CharactersPagingSource
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val remoteDataSource: CharactersRemoteDataSource<DataWrapperResponse>
) : CharactersRepository {

    override fun getCharacters(query: String): PagingSource<Int, Character> {
        return CharactersPagingSource(remoteDataSource, query)
    }

}