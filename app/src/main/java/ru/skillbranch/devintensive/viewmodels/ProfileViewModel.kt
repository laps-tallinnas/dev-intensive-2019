package ru.skillbranch.devintensive.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.skillbranch.devintensive.models.Profile
import ru.skillbranch.devintensive.repository.PreferencesRepository

class ProfileViewModel :ViewModel() {
    val TAG:String ="ViewModel"

    private val repository: PreferencesRepository = PreferencesRepository
    private val profileData = MutableLiveData<Profile>()
    init{
        Log.d("TAG", "init view model")
        profileData.value = repository.getProfile()
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("TAG", "cleared view model")

    }

    fun getProfileData():LiveData<Profile> = profileData

    fun saveProfileData(profile:Profile){
        repository.saveProfile(profile)
        profileData.value=profile
    }
}