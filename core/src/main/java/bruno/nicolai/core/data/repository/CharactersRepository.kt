package bruno.nicolai.core.data.repository

import androidx.paging.PagingSource
import bruno.nicolai.core.domain.model.Character

interface CharactersRepository {

    fun getCharacters(query: String): PagingSource<Int, Character>

}