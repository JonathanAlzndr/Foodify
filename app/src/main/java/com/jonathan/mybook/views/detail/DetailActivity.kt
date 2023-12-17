package com.jonathan.mybook.views.detail

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.jonathan.mybook.data.model.MealsItem
import com.jonathan.mybook.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val meal = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_MEAL, MealsItem::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_MEAL)
        }
        if (meal != null) {
            Glide.with(binding.root.context)
                .load(meal.strMealThumb)
                .into(binding.ivImage)

            binding.tvName.text = meal.strMeal
            binding.tvDetail.text = meal.strInstructions
        }
    }

    companion object {
        const val EXTRA_MEAL = "EXTRA_MEAL"
    }
}