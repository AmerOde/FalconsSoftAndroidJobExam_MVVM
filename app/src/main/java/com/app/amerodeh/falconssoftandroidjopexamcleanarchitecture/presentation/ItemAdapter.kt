package com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.data.model.ItemDisplay
import com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.databinding.ItemLayoutBinding

class ItemAdapter :RecyclerView.Adapter<ItemAdapter.ItemHolder> (){
    private  val itemList =ArrayList<ItemDisplay>()

    fun setList(items: List<ItemDisplay>){
        itemList.clear()
        itemList.addAll((items))
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(itemList[position])
    }


    class ItemHolder(private val itemBinding:ItemLayoutBinding):RecyclerView.ViewHolder(itemBinding.root){
            fun bind(item:ItemDisplay){
                itemBinding.tvName.text =item.itemName
                itemBinding.tvCategory.text =item.category
                itemBinding.tvQyn.text = item.qty.toString()

            }
    }
}
