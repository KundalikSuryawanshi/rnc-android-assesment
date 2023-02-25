package com.kundalik.rncassesment

import android.app.Service
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kundalik.rncassesment.adapter.ProductAdapter
import com.kundalik.rncassesment.repository.Repository
import com.kundalik.rncassesment.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private val productAdapter by lazy { ProductAdapter(viewModel) }
    var connectivityManager: ConnectivityManager? = null
    var info: NetworkInfo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkInternetConnection()

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        viewModel.getProducts("maybelline")
        showProgressBar()
        viewModel.mResponse.observe(this, Observer { response ->
            shimmer.startShimmer()
            if (response.isSuccessful) {
                response.body()?.let {
                    productAdapter.setData(it)
                    setupRecyclerView()
                    hideProgressBar()
                    shimmer.stopShimmer()
                    shimmer_layout.visibility = View.INVISIBLE
                }
            } else {
                hideProgressBar()
                Toast.makeText(this, response.code(), Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.selectedItem.observe(this) { item ->

            val intent = Intent(this@MainActivity, ProductActivity::class.java)

            intent.putExtra("imageUrl", item.image_link)
            intent.putExtra("name", item.name)
            intent.putExtra("brand", item.brand)
            intent.putExtra("price", item.price)
            intent.putExtra("description", item.description)
            intent.putExtra("product_link", item.product_link)

            startActivity(intent)
        }

    }

    private fun checkInternetConnection() {
        connectivityManager =
            this.getSystemService(Service.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            info = connectivityManager?.activeNetworkInfo
            if (info != null) {
                if (info!!.state == NetworkInfo.State.DISCONNECTED) {
                    Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show()
                } else if (info!!.state == NetworkInfo.State.DISCONNECTING) {
                    Toast.makeText(this, "Disconnecting", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
        products_recyclerview.adapter = productAdapter
        products_recyclerview.layoutManager = LinearLayoutManager(this)

    }
}
