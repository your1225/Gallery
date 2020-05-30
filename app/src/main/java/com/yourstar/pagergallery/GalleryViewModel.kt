package com.yourstar.pagergallery

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject

class GalleryViewModel(application: Application) : AndroidViewModel(application) {
    private val _photoListLive = MutableLiveData<List<PhotoItem>>()

    val photoListLive: LiveData<List<PhotoItem>>
        get() = _photoListLive

    fun fetchData() {
        val stringRequest = object :StringRequest(
            Request.Method.GET,
            getUrl(),
            Response.Listener {
                _photoListLive.value = Gson().fromJson(it, Pixabay::class.java).hits.toList()
            },
            Response.ErrorListener {
                Log.d("hello", it.toString())
            }
        ){
            override fun getParams(): MutableMap<String, String> {
                return super.getParams()
            }

            override fun getBody(): ByteArray {
                return super.getBody()
            }

            override fun getBodyContentType(): String {
                return super.getBodyContentType()
            }
        }

//        var payload:JSONObject = JSONObject()
//        var vaArrar:JSONArray = JSONArray()
//
//        payload.put("ff", 1)
//        payload.put("f2", "fff")
//        payload.put("f3", true)
//
//
//
//        val jsonObjectRequest = JsonObjectRequest(
//            Request.Method.POST,
//            getUrl(),
//            payload,
//            Response.Listener<JSONObject> {
//
//            },
//            Response.ErrorListener {
//
//            }
//        )

        VolleySingleton.getInstance(getApplication()).requestQueue.add(stringRequest)
    }

    private fun getUrl(): String {
        return "https://pixabay.com/api/?key=15445606-cc07e08ffb6af0d5b83772f7e&q=${keyWords.random()}&per_page=100"
    }

    private val keyWords = arrayOf("cat", "dog", "car","beauty","phone", "computer", "flower", "animal")
}