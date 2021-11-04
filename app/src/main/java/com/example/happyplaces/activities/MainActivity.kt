package com.example.happyplaces.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.happyplaces.R
import com.example.happyplaces.adapters.HappyPlacesAdapter
import com.example.happyplaces.database.DatabaseHandler
import com.example.happyplaces.models.HappyPlaceModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        var ADD_PLACE_ACTIVITY_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fabAddHappyPlace.setOnClickListener {
            val intent = Intent(this, AddHappyPlaceActivity::class.java)
            startActivityForResult(intent, ADD_PLACE_ACTIVITY_REQUEST_CODE)
        }

        getPlaceListFromLocal()

    }

    private fun setupHappyPlacesRecyclerView(happyPlaceList: ArrayList<HappyPlaceModel>){
        happyPlacesList.layoutManager = LinearLayoutManager(this)
        happyPlacesList.setHasFixedSize(true)

        val placesAdapter = HappyPlacesAdapter(this, happyPlaceList)
        happyPlacesList.adapter = placesAdapter
    }

    private fun getPlaceListFromLocal() {
        val dbHandler = DatabaseHandler(this)
        val happyPlaceList: ArrayList<HappyPlaceModel> = dbHandler.getHappyPlaceList()

        if(happyPlaceList.size > 0) {
            happyPlacesList.visibility = View.VISIBLE
            noHappyPlace.visibility = View.GONE
            setupHappyPlacesRecyclerView(happyPlaceList)
        } else {
            happyPlacesList.visibility = View.GONE
            noHappyPlace.visibility = View.VISIBLE
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == ADD_PLACE_ACTIVITY_REQUEST_CODE) {
            if(resultCode == Activity.RESULT_OK) {
                getPlaceListFromLocal()
            }
        }
    }

}