package com.vishalag53.sleeptracker.sleepquality

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.vishalag53.sleeptracker.R
import com.vishalag53.sleeptracker.databinding.FragmentSleepQualityBinding

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



        return binding.root
    }

}