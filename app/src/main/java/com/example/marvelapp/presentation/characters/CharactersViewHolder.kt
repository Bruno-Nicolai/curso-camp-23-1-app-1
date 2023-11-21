package com.example.marvelapp.presentation.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import bruno.nicolai.core.domain.model.Character
import com.bumptech.glide.Glide
import com.example.marvelapp.util.OnCharacterItemClick
import com.example.marvelapp.R
import com.example.marvelapp.databinding.ItemCharacterBinding

class CharactersViewHolder(
    itemCharacterBinding: ItemCharacterBinding,
    private val onItemClick: /*(character: Character, view: View) -> Unit*/OnCharacterItemClick
) : RecyclerView.ViewHolder(itemCharacterBinding.root) {

    private val textName = itemCharacterBinding.tvName
    private val imageCharacter = itemCharacterBinding.ivCharacter

    fun bind(character: Character) {
        textName.text = character.name
        Glide.with(itemView)
            .load(character.imageUrl)
            .fallback(R.drawable.ic_img_loading_error)
            .into(imageCharacter)

        itemView.findViewById<ImageView>(R.id.iv_character)
            .setOnClickListener {
                onItemClick.invoke(character, imageCharacter)
            }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            onItemClick: /*(character: Character, view: View) -> Unit*/OnCharacterItemClick
        ): CharactersViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = ItemCharacterBinding.inflate(inflater, parent, false)
            return CharactersViewHolder(itemBinding, onItemClick)
        }
    }

}