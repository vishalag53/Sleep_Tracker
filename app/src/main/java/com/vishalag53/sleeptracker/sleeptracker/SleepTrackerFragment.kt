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
import com.vishalag53.sleeptracker.databinding.FragmentSleepTrackerBinding


class SleepTrackerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentSleepTrackerBinding>(inflater,R.layout.fragment_sleep_tracker,container,false)

        val application = requireNotNull(this.activity).application

        val dataSource = SleepDatabase.getInstance(application).sleepDatabaseDao

        val viewModelFactory = SleepTrackerViewModelFactory(dataSource,application)

        val sleepTrackerViewModel = ViewModelProvider(this,viewModelFactory)[SleepTrackerViewModel::class.java]

        binding.sleepTrackerViewModel = sleepTrackerViewModel

        binding.lifecycleOwner = this
        return binding.root
    }


}