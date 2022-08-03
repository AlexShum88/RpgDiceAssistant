package com.elanor.rpgdiceassistant.chooseColor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.elanor.rpgdiceassistant.R
import com.elanor.rpgdiceassistant.TabAdapter
import com.elanor.rpgdiceassistant.databinding.FragmentChooseColorBinding
import com.elanor.rpgdiceassistant.factory

class ChooseColorFragment() :
    Fragment(R.layout.fragment_choose_color) {

    lateinit var pager: ViewPager2
    private lateinit var binding: FragmentChooseColorBinding
    private val viewModel: ChooseColorViewModel by viewModels { factory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.onCreate()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChooseColorBinding.inflate(inflater, container, false)
        val adapter by lazy {
            AdapterColor {
                viewModel.chooseColor(it)
                pager.setCurrentItem(TabAdapter.POSITION_OF_DICE_POOL, true)
            }
        }
        viewModel.colors.observe(viewLifecycleOwner) {
            adapter.dColors = it
        }
        binding.colorRecycler.adapter = adapter
        binding.colorRecycler.layoutManager = LinearLayoutManager(requireContext())
        pager = requireActivity().findViewById(R.id.pager)
        return binding.root
    }


    private fun setLayoutManager() {
//dynamic grid layout. dont create layout while dont roll dice (((
        binding.colorRecycler.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                binding.colorRecycler.viewTreeObserver.removeOnGlobalLayoutListener(this)
                val width = binding.colorRecycler.width
                val itemWidth = resources.getDimensionPixelSize(R.dimen.dice_size)
                val columns = width / itemWidth
                binding.colorRecycler.layoutManager = GridLayoutManager(requireContext(), columns)
            }
        })
    }

}