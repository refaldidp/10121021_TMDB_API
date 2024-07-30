package com.movie.tmdb

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val apiKey = "87f4f06519255dbfa8cc016847346301"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        RetrofitInstance.api.getPopularMovies(apiKey).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    response.body()?.results?.let { movies ->
                        recyclerView.adapter = MovieAdapter(movies)
                    }
                } else {
                    Log.e("MainActivity", "Failed to get response")
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e("MainActivity", "Failed to call API", t)
            }
        })
    }
}
