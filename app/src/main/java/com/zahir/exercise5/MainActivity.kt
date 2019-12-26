package com.zahir.exercise5

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: CounterViewModel
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(CounterViewModel::class.java)
        sharedPreferences = getPreferences(Context.MODE_PRIVATE)

        viewModel.up.observe(this, Observer { newUp ->
            textViewUpCtr.text = newUp.toString()
        })

        viewModel.down.observe(this, Observer { newDown ->
            textViewDownCtr.text = newDown.toString()
        })

        imageViewThumbsDown.setOnClickListener{
            viewModel.onDown()
        }

        imageViewThumbsUp.setOnClickListener{
            viewModel.onUp()
        }

        buttonReset.setOnClickListener{
            viewModel.onClear()
        }
    }

    override fun onResume() {
        viewModel.setUp(sharedPreferences.getInt("like", 0))
        viewModel.setDown(sharedPreferences.getInt("dislike", 0))
        textViewUpCtr.text = viewModel.getUp().toString()
        textViewDownCtr.text = viewModel.getDown().toString()
        super.onResume()
    }

    override fun onPause() {
        with(sharedPreferences.edit()){
            putInt("like", viewModel.getUp()!!)
            putInt("dislike", viewModel.getDown()!!)
            commit()
        }
        super.onPause()

        //79 03 91 8-10
        //24 46 98 10-12
    }
}
