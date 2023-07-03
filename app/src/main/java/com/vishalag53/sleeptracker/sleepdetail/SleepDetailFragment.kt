package com.vishalag53.sleeptracker.sleepdetail

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
import com.vishalag53.sleeptracker.databinding.FragmentSleepDetailBinding

class SleepDetailFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentSleepDetailBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_sleep_detail,container,false)

        val application = requireNotNull(this.activity).application
        val arguments = SleepDetailFragmentArgs.fromBundle(requireArguments())

        val dataSource = SleepDatabase.getInstance(application).sleepDatabaseDao
        val viewModelFactory = SleepDetailViewModelFactory(arguments.sleepNightKey, dataSource)

        val sleepDetailViewModel = ViewModelProvider(this,viewModelFactory)[SleepDetailViewModel::class.java]

        binding.sleepDetailViewModel = sleepDetailViewModel

        binding.lifecycleOwner = this

        sleepDetailViewModel.navigateToSleepTracker.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                this.findNavController().navigate(
                    SleepDetailFragmentDirections.actionSleepDetailFragmentToSleepTrackerFragment())
                sleepDetailViewModel.doneNavigating()
            }
        })

        return binding.root
    }

}