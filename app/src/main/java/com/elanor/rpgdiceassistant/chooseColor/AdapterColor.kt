package com.elanor.rpgdiceassistant.chooseColor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.elanor.rpgdiceassistant.databinding.ColorViewBinding
import com.elanor.rpgdiceassistant.model.DColor

class AdapterColor(
//    private val actionListener: ColorActionListener
    val changeFocus: (DColor) -> Unit
) : RecyclerView.Adapter<AdapterColor.ColorHolder>(), View.OnClickListener {

    inner class ColorHolder(val binding: ColorViewBinding) : RecyclerView.ViewHolder(binding.root)

    var dColors = emptyList<DColor>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ColorViewBinding.inflate(inflater, parent, false)
        binding.root.setOnClickListener(this)
        return ColorHolder(binding)
    }

    override fun onBindViewHolder(holder: ColorHolder, position: Int) {
        val dColor = dColors[position]
        holder.binding.root.tag = dColor
        holder.binding.colorChose.isVisible = dColor.isChecked
        holder.binding.colorView.setImageResource(dColor.backgroundColor)

    }

    override fun getItemCount(): Int = dColors.size

    override fun onClick(colorView: View) {
        changeFocus(colorView.tag as DColor)
    }

}