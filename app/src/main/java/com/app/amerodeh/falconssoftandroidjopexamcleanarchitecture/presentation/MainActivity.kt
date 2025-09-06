package com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.presentation

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.R
import com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.data.ItemRepositoryImpl
import com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.data.api.ItemService
import com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.data.api.RetrofitInstance
import com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.data.datasourceimpl.ItemCacheDataSourceImpl
import com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.data.datasourceimpl.ItemLocalDataSourceImpl
import com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.data.datasourceimpl.ItemRemoteDataSourceImpl
import com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.data.roomdb.ItemDao
import com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.data.roomdb.ItemDataBase
import com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.databinding.ActivityMainBinding
import com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.domain.usecases.GetItemsUseCases
import com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.domain.usecases.SearchItemUseCases
import com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.domain.usecases.UpdateItemsUseCases

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var itemViewModel: ItemViewModel
    private lateinit var itemViewModelFactory: ItemViewModelFactory
    private lateinit var adapter :ItemAdapter
    private lateinit var getItemsUseCases: GetItemsUseCases
    private lateinit var searchItemsUseCases: SearchItemUseCases
    private lateinit var updateItemsUseCases: UpdateItemsUseCases
    private lateinit var itemCacheDataSourceImpl: ItemCacheDataSourceImpl
    private lateinit var itemLocalDataSourceImpl: ItemLocalDataSourceImpl

    private lateinit var itemRemoteDataSourceImpl: ItemRemoteDataSourceImpl
    private lateinit var itemService: ItemService
    private lateinit var itemRepositoryImpl: ItemRepositoryImpl

    private lateinit var itemDao: ItemDao


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       // enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        adapter =ItemAdapter()
        itemService =RetrofitInstance.getRetrofitInstance().create(ItemService::class.java)
        itemCacheDataSourceImpl = ItemCacheDataSourceImpl()
        itemDao =ItemDataBase.getInstance(application).itemDao()
        itemLocalDataSourceImpl = ItemLocalDataSourceImpl(itemDao)
        itemRemoteDataSourceImpl =ItemRemoteDataSourceImpl(itemService,290,1)

        itemRepositoryImpl = ItemRepositoryImpl(itemRemoteDataSourceImpl,itemLocalDataSourceImpl,itemCacheDataSourceImpl)
        getItemsUseCases = GetItemsUseCases(itemRepositoryImpl)
        updateItemsUseCases = UpdateItemsUseCases(itemRepositoryImpl)
        searchItemsUseCases =SearchItemUseCases(itemRepositoryImpl)
        //view model
        itemViewModelFactory = ItemViewModelFactory(getItemsUseCases,updateItemsUseCases,searchItemsUseCases)
        itemViewModel =ViewModelProvider(this,itemViewModelFactory)[ItemViewModel::class.java]

        binding.rvMain.adapter =adapter

        initRecyclerView()



        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    searchItems(query)

                }
                return true
                }


            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    searchItems(it)
                }
                return true
            }


        })
        binding.swipeRefresh.setOnRefreshListener {
            updateItem()

        }


    }



    private fun searchItems(query: String) {


        if (query.isEmpty()) {
          displayItems()

        } else {
            // البحث حسب النص
            itemViewModel.searchItems(query).observe(this) { list ->
                if (list != null) {
                    adapter.setList(list)
                    adapter.notifyDataSetChanged()
                }
            }
        }

        }

    fun initRecyclerView(){
        binding.rvMain.adapter =adapter
        binding.rvMain.layoutManager = GridLayoutManager(this,2)

        displayItems()


    }
    fun displayItems(){
        binding.progressBar.visibility = View.VISIBLE
        binding.progressBar.isIndeterminate = true

        itemViewModel.getItems().observe(this, Observer{
            if (it != null) {
                adapter.setList(it)
                adapter.notifyDataSetChanged()
                Log.d("MainActivity", "Items received: ${it?.size}")

                binding.progressBar.visibility = View.GONE




            }
            else{

                Toast.makeText(this,"No Data ",Toast.LENGTH_SHORT).show()

            }
        })


    }
    fun updateItem() {
        if (isNetworkAvailable(binding.swipeRefresh.context)) {

            itemViewModel.updateItems().observe(this, Observer { items ->
                if (items != null && items.isNotEmpty()) {
                    adapter.setList(items)
                    adapter.notifyDataSetChanged()
                    Log.d("MainActivity", "Items received: ${items.size}")

                    binding.swipeRefresh.isRefreshing = false
                    Toast.makeText(this, "Data has been updated", Toast.LENGTH_SHORT).show()
                } else {
                    binding.swipeRefresh.isRefreshing = false
                    Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show()
                }
            })

        } else {
            binding.swipeRefresh.isRefreshing = false
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show()
        }
    }

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val actNw = connectivityManager.getNetworkCapabilities(network) ?: return false
        return when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }


}