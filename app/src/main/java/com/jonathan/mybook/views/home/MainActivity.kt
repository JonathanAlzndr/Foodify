package com.jonathan.mybook.views.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.jonathan.mybook.R
import com.jonathan.mybook.adapter.MealsAdapter
import com.jonathan.mybook.data.model.MealsItem
import com.jonathan.mybook.databinding.ActivityMainBinding
import com.jonathan.mybook.views.login.LoginActivity
import com.jonathan.mybook.views.profile.ProfileActivity


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var progressBar: LottieAnimationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        val layoutManager = LinearLayoutManager(this)
        binding.rvUsers.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvUsers.addItemDecoration(itemDecoration)

        val mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        progressBar = binding.progressBar
        progressBar.setAnimation(R.raw.loading)
        progressBar.repeatCount = LottieDrawable.INFINITE

        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { textView, actionId, event ->
                    searchBar.setText(searchView.text)
                    searchView.hide()
                    val search = searchBar.text.toString()
                    mainViewModel.searchMealsByFirstLetter(search)
                    false
                }
        }

        mainViewModel.mealsLiveData.observe(this) { listMeals ->
            setReviewData(listMeals)
        }

        mainViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when(menuItem.itemId) {
                R.id.menu_1 -> {
                    val intent = Intent(this@MainActivity, LoginActivity::class.java)
                    Log.d("MainActivity", intent.action.toString())
                    startActivity(intent)
                    true
                }
                R.id.menu_2 -> {
                    val intent = Intent(this@MainActivity, ProfileActivity::class.java)
                    Log.d("MainActivity", intent.action.toString())
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }

    }

    private fun setReviewData(consumerReviews: List<MealsItem?>?) {
        val adapter = MealsAdapter()
        adapter.submitList(consumerReviews)
        binding.rvUsers.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {

        if(isLoading) {
            binding.progressBar.visibility = LottieAnimationView.VISIBLE
            binding.progressBar.setAnimation(R.raw.loading)
            binding.progressBar.repeatCount = LottieDrawable.INFINITE
        } else {
            binding.progressBar.visibility = LottieAnimationView.GONE
        }
    }
}