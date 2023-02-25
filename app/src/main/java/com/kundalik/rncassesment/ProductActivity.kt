package com.kundalik.rncassesment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_product.*

class ProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        setupIntentData()

        btn_goto_website.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://makeup-api.herokuapp.com")
            startActivity(intent)
        }

    }

    private fun setupIntentData() {
        val intent = intent
        val imageUrl = intent.getStringExtra("imageUrl")
        Glide.with(this).load(imageUrl).into(iv_detail_pimage)
        tv_detail_pName.text = intent.getStringExtra("name")
        tv_detail_pBrand.text = intent.getStringExtra("brand")
        tv_detail_pPrice.text = intent.getStringExtra("price")
        tv_detail_pDesc.text = intent.getStringExtra("description")
        val productLink = intent.getStringExtra("product_link")

        iv_detail_pimage.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(productLink)
            startActivity(intent)
        }

    }

}