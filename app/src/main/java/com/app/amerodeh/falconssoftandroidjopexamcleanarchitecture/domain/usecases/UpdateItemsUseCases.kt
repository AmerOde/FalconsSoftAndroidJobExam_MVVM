package com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.domain.usecases

import com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.data.model.ItemDisplay
import com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.domain.repository.ItemRepository

class UpdateItemsUseCases(private val itemRepository: ItemRepository) {


    suspend fun execute():List<ItemDisplay>? = itemRepository.updateItems()


}
