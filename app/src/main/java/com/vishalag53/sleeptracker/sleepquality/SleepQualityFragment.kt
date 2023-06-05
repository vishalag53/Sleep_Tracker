package com.vishalag53.sleeptracker.sleepquality

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.vishalag53.sleeptracker.R
import com.vishalag53.sleeptracker.database.SleepDatabase
import com.vishalag53.sleeptracker.databinding.FragmentSleepQualityBinding
import com.vishalag53.sleeptracker.sleeptracker.SleepTrackerViewModelFactory

class SleepQualityFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding = DataBindingUtil.inflate<FragmentSleepQualityBinding>(
            inflater,
            R.layout.fragment_sleep_quality,
            container,
            false
        )

        val application = requireNotNull(this.activity).application

        val arguments = SleepQualityFragmentArgs.fromBundle(requireArguments())

        val dataSource = SleepDatabase.getInstance(application).sleepDatabaseDao

        val viewModelFactory = SleepQualityViewModelFactory(arguments.sleepNightKey,dataSource)

        val sleepQualityViewModel = ViewModelProvider(this,viewModelFactory)[SleepQualityViewModel::class.java]

        binding.sleepQualityViewModel = sleepQualityViewModel

        sleepQualityViewModel.navigateToSleepTracker.observe(this, Observer {
            if(it == true){
                this.findNavController().navigate(
                    SleepQualityFragmentDirections.actionSleepQualityFragmentToSleepTrackerFragment())
                sleepQualityViewModel.doneNavigating()
            }
        })

        return binding.root
    }

}