package com.vishalag53.sleeptracker.sleeptracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
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
        val binding = DataBindingUtil.inflate<FragmentSleepTrackerBinding>(
            inflater,
            R.layout.fragment_sleep_tracker,
            container,
            false
        )

        val application = requireNotNull(this.activity).application

        val dataSource: SleepDatabaseDao = SleepDatabase.getInstance(application).sleepDatabaseDao
        viewModelFactory = SleepTrackerViewModelFactory(dataSource, application)

        sleepTrackerViewModel =
            ViewModelProvider(this, viewModelFactory)[SleepTrackerViewModel::class.java]

        binding.lifecycleOwner = this

        binding.sleepTrackerViewModel = sleepTrackerViewModel

        // Gird Layout
        val manager = GridLayoutManager(activity, 3)
        binding.sleepList.layoutManager = manager


        val adapter = SleepNightAdapter(SleepNightListener { nightId ->
            sleepTrackerViewModel.onSleepNightClicked(nightId)
        })
        binding.sleepList.adapter = adapter

        sleepTrackerViewModel.navigateToSleepDataQuality.observe(
            viewLifecycleOwner,
            Observer { night ->
                night?.let {
                    this.findNavController().navigate(
                        SleepTrackerFragmentDirections.actionSleepTrackerFragmentToSleepDetailFragment(
                            night
                        )
                    )
                    sleepTrackerViewModel.onSleepDataQualityNavigated()
                }
            })

        sleepTrackerViewModel.nights.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        sleepTrackerViewModel.navigateToSleepQuality.observe(viewLifecycleOwner, Observer { night ->
            night?.let {
                this.findNavController().navigate(
                    SleepTrackerFragmentDirections
                        .actionSleepTrackerFragmentToSleepQualityFragment(night.nightId)
                )
                sleepTrackerViewModel.doneNavigating()
            }
        })

        sleepTrackerViewModel.showSnackBarEvent.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                Snackbar.make(
                    requireActivity().findViewById(android.R.id.content),
                    getString(R.string.cleared_message),
                    Snackbar.LENGTH_SHORT
                ).show()
                sleepTrackerViewModel.doneShowingSnackBar()
            }
        })

        return binding.root
    }


}