package com.elanor.rpgdiceassistant.statistic

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.allViews
import androidx.recyclerview.widget.RecyclerView
import com.elanor.rpgdiceassistant.R
import com.elanor.rpgdiceassistant.databinding.StatisticLineBinding
import com.elanor.rpgdiceassistant.model.Dice
import com.elanor.rpgdiceassistant.model.StatisticObject

class StatisticAdapter : RecyclerView.Adapter<StatisticAdapter.StatisticViewHolder>() {

    class StatisticViewHolder(val binding: StatisticLineBinding) :
        RecyclerView.ViewHolder(binding.root)

    var list = emptyList<StatisticObject>()
        set(value) {
            field = value.reversed()
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatisticViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = StatisticLineBinding.inflate(inflater, parent, false)
        return StatisticViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StatisticViewHolder, position: Int) {
        val item = list[position]
        holder.binding.dateTextView.text = item.date
        val statistic = createStatText(item.dices)
        val context = holder.itemView.context
        deletePreviousViews(holder)

        statistic.forEach {

            setGrainView(it, holder, context)

            setColorView(it, holder, context)
        }

    }

    private fun setGrainView(
        statTextObj: StatTextObj,
        holder: StatisticViewHolder,
        context: Context
    ) {
        val grain = TextView(holder.itemView.context)
        grain.tag = TAG_DICE_VIEW
        grain.textSize =
            context.resources.getDimension(R.dimen.text_size_in_generated_view_big) / TEXT_SIZE_CORRECTION_DIVIDER
        grain.textAlignment = View.TEXT_ALIGNMENT_TEXT_START

        grain.text =
            context.getString(
                R.string.statistic_grain_text,
                statTextObj.numOfDicesOfGrain,
                statTextObj.grain
            )
        holder.binding.layoutForDices.addView(grain)

    }

    private fun setColorView(
        statTextObj: StatTextObj,
        holder: StatisticViewHolder,
        context: Context
    ) {
        statTextObj.colorsAndResults
            .forEach { a ->
                val colorRow = TextView(holder.itemView.context)
                colorRow.tag = TAG_DICE_VIEW
                colorRow.setPadding(
                    context.resources.getDimension(R.dimen.text_margin).toInt(),
                    0,
                    0,
                    0
                )
                colorRow.textSize =
                    context.resources.getDimension(R.dimen.text_size_in_generated_view) / TEXT_SIZE_CORRECTION_DIVIDER
                colorRow.setTextColor(ContextCompat.getColor(holder.itemView.context, a.key))
                colorRow.textAlignment = View.TEXT_ALIGNMENT_TEXT_START
                colorRow.text = a.value
                holder.binding.layoutForDices.addView(colorRow)
            }
    }

    private fun deletePreviousViews(holder: StatisticViewHolder) {
        holder.binding.layoutForDices.allViews
            .filter { it.tag == TAG_DICE_VIEW }
            .toList()
            .forEach {
                holder.binding.layoutForDices.removeView(it)
            }
    }

    override fun getItemCount(): Int = list.size

    private fun sortByColor(dices: List<Dice>): Map<Int, List<Dice>> {
        val resultMap = mutableMapOf<Int, List<Dice>>()
        dices.map { it.color }.map { a ->
            resultMap[a] = dices.filter {
                it.color == a
            }.toList()
        }
        return resultMap
    }

    private fun sortByGrain(dices: List<Dice>): Map<Int, List<Dice>> {
        val resultMap = mutableMapOf<Int, List<Dice>>()
        dices.map { it.grain }.map { a ->
            resultMap[a] = dices.filter {
                it.grain == a
            }.toList()
        }
        return resultMap
    }


    private fun result(dices: List<Dice>): String {
        val e = StringBuilder()
        val res = dices
            .sortedBy { it.value }
            .map {
            e.append("${it.value} ")
            it.value
        }.reduce { a, b ->
            a + b
        }
        e.append("($res)")
        return e.toString()
    }


    private fun createStatText(dices: List<Dice>): List<StatTextObj> {
        val statTextList = mutableListOf<StatTextObj>()
        val grainSort = sortByGrain(dices)
        grainSort.forEach {
            val statText = StatTextObj(
                grain = it.key.toString(),
                numOfDicesOfGrain = it.value.size,
                colorsAndResults = sortByColor(it.value).mapValues { a -> result(a.value) }
            )
            statTextList.add(statText)
        }
        return statTextList
    }

    private data class StatTextObj(
        val grain: String,
        val numOfDicesOfGrain: Int,
        val colorsAndResults: Map<Int, String>,
    )

    companion object {
        const val TAG_DICE_VIEW = "diceView"
        const val TEXT_SIZE_CORRECTION_DIVIDER = 4
    }
}