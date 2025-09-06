package com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.domain.usecases

import com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.data.model.ItemDisplay
import com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.domain.repository.ItemRepository

class SearchItemUseCases(private  val itemRepository: ItemRepository) {

    suspend fun execute(query :String):List<ItemDisplay>? = itemRepository.searchItems(query)

}