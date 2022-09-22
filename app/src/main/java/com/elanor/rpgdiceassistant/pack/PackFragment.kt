package com.elanor.rpgdiceassistant.pack

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.elanor.rpgdiceassistant.MainActivity
import com.elanor.rpgdiceassistant.R
import com.elanor.rpgdiceassistant.TabAdapter
import com.elanor.rpgdiceassistant.databinding.FragmentPackListBinding
import com.elanor.rpgdiceassistant.model.DiceRepository
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView

class PackFragment() : Fragment() {
//    lateinit var mAdView: AdView
    lateinit var pager: ViewPager2
    val viewModel: PackViewModel by viewModels()
    lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preferences = this.requireActivity()
            .getSharedPreferences(MainActivity.APP_PREFERENCES, Context.MODE_PRIVATE)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentPackListBinding.inflate(inflater, container, false)
        val adapter by lazy {
            PackAdapter(this)
            {
                preferences.edit()
                    .putString(MainActivity.CURRENT_DICE_PACK, it)
                    .apply()
                DiceRepository.changeDicePack(it)
                pager.setCurrentItem(TabAdapter.POSITION_OF_DICE_POOL, false)
            }
        }
        adapter.packs = viewModel.packsName
        binding.list.adapter = adapter
        binding.list.layoutManager = LinearLayoutManager(requireContext())
        pager = requireActivity().findViewById(R.id.pager)

//        mAdView = binding.adView
//        val adRequest = AdRequest.Builder().build()
//        mAdView.loadAd(adRequest)
        return binding.root
    }


}