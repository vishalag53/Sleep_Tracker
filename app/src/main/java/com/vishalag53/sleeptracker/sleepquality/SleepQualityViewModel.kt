package com.vishalag53.sleeptracker.sleepquality

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vishalag53.sleeptracker.database.SleepDatabaseDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SleepQualityViewModel(
        private val sleepNightKey: Long = 0L,
        val database: SleepDatabaseDao): ViewModel() {

    private val viewModelJob = Job()
    //private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _navigateToSleepTracker = MutableLiveData<Boolean?>()

    val navigateToSleepTracker: LiveData<Boolean?>
        get() = _navigateToSleepTracker

    fun doneNavigating(){
        _navigateToSleepTracker.value = null
    }

    fun onSetSleepQuality(quality: Int){
        viewModelScope.launch {
                val tonight = database.get(sleepNightKey)?: return@launch
                tonight.sleepQuality = quality
                database.update(tonight)
            _navigateToSleepTracker.value = true
        }
    }
}