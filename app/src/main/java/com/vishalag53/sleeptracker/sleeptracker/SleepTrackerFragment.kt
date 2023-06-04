package com.vishalag53.sleeptracker.sleeptracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.vishalag53.sleeptracker.R
import com.vishalag53.sleeptracker.database.SleepDatabase
import com.vishalag53.sleeptracker.database.SleepDatabaseDao
import com.vishalag53.sleeptracker.databinding.FragmentSleepTrackerBinding


class SleepTrackerFragment : Fragment() {

    lateinit var sleepTrackerViewModel: SleepTrackerViewModel

    lateinit var viewModelFactory: SleepTrackerViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentSleepTrackerBinding>(inflater,R.layout.fragment_sleep_tracker,container,false)

        val application = requireNotNull(this.activity).application

        val dataSource: SleepDatabaseDao = SleepDatabase.getInstance(application).sleepDatabaseDao
        viewModelFactory = SleepTrackerViewModelFactory(dataSource,application)

        sleepTrackerViewModel = ViewModelProvider(this,viewModelFactory)[SleepTrackerViewModel::class.java]

        binding.lifecycleOwner = this

        binding.sleepTrackerViewModel = sleepTrackerViewModel

        return binding.root
    }


}