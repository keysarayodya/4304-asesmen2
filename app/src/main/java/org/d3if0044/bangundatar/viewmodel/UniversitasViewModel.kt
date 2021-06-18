package org.d3if0044.bangundatar.viewmodel

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import org.d3if0044.bangundatar.data.network.ApiConfig
import org.d3if0044.bangundatar.data.network.NetworkService
import org.d3if0044.bangundatar.data.network.UniversityEntity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UniversitasViewModel : ViewModel(){

    private val _data = MediatorLiveData<UniversityEntity>()
    private val _loading = MediatorLiveData<Boolean>()
//    private val data = MutableLiveData<List<UniversityEntity>>()

    val data: LiveData<UniversityEntity>
        get() = _data

    val loading: LiveData<Boolean>
        get() = _loading
//    init {
//        data.value = initData()
//        retrieveData()
//    }

    fun retrieveData() {
        viewModelScope.launch {
            val retrofit = ApiConfig.retrofitService()
            val api = retrofit.create(NetworkService::class.java)
            _loading.postValue(true)
            api.getUniversity().enqueue(object : Callback<UniversityEntity> {
                override fun onFailure(call: Call<UniversityEntity>, t: Throwable) {
                    _data.postValue(null)
                    _loading.postValue(false)
                    Log.d("UniversitasViewModel", "Failure: ${t.message}")
                }

                override fun onResponse(
                    call: Call<UniversityEntity>,
                    response: Response<UniversityEntity>
                ) {
                    _data.postValue(response.body())
                    _loading.postValue(false)
                    Log.d("UniversitasViewModel", "Success: $response")
                }
            })
        }
    }
}