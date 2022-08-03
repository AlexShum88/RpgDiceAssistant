package com.elanor.rpgdiceassistant.statistic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.elanor.rpgdiceassistant.databinding.FragmentStatisticBinding

class StatisticFragment : Fragment() {

    private val viewModel: StatisticViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.create()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val adapter = StatisticAdapter()
        val binding = FragmentStatisticBinding.inflate(inflater, container, false)
        viewModel.statistic.observe(viewLifecycleOwner) {
            adapter.list = it
        }
        binding.statisticRecycle.adapter = adapter
        binding.statisticRecycle.layoutManager = LinearLayoutManager(requireContext())
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
//        viewModel.destroy()
    }


}