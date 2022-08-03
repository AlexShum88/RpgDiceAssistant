package com.elanor.rpgdiceassistant.dicepool

import android.graphics.drawable.VectorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.elanor.rpgdiceassistant.R
import com.elanor.rpgdiceassistant.databinding.DiceBinding
import com.elanor.rpgdiceassistant.model.Dice
import com.elanor.rpgdiceassistant.model.RollRegime
import java.io.FileNotFoundException

class DiceAdapter(
    private val fragment: DicePoolFragment,
    private val adapterActions: AdapterActions,
    private val dicePopUpAction: DicePopUpAction,
) : RecyclerView.Adapter<DiceAdapter.ViewHolder>(), View.OnClickListener, View.OnLongClickListener {


    class ViewHolder(val binding: DiceBinding) : RecyclerView.ViewHolder(binding.root)

    var dices = emptyList<Dice>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private var divider: Int = -1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DiceBinding.inflate(inflater, parent, false)
        binding.root.setOnClickListener(this)
        binding.root.setOnLongClickListener(this)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dice = dices[position]
        with(holder.binding) {
            root.tag = dice
            val drawableStream = try {
                fragment.requireContext().assets.open(dice.image)
            } catch (e: FileNotFoundException) {
                fragment.requireContext().assets.open(Dice.FILE_NOT_FOUND)
            }

            val drawable = VectorDrawable.createFromStream(drawableStream, null)
            diceImage.setImageDrawable(drawable)
            diceImage.setBackgroundColor(fragment.requireContext().getColor(dice.color))
            setDiceAlpha(position, root)
            rollAnim(position, root)
            if (position == dices.size - 1) divider = -1
//            DrawableCompat.setTint(diceImage.drawable, ContextCompat.getColor(fragment.requireContext(), dice.color))
        }
    }

    private fun rollAnim(position: Int, root: View) {
        if (RollRegime.rollAllPrevDIcesRegime) {
            if (divider >= 0 && divider < dices.size) {
                if (position <= divider) {
                    root.startAnimation(
                        AnimationUtils.loadAnimation(
                            fragment.requireContext(),
                            R.anim.dice_anim
                        )
                    )
                }
            }
        } else {
            if (position == divider)
                root.startAnimation(
                    AnimationUtils.loadAnimation(
                        fragment.requireContext(),
                        R.anim.dice_anim
                    )
                )
        }

    }

    private fun setDiceAlpha(position: Int, root: View) {
        if (RollRegime.rollAllPrevDIcesRegime) {
            if (divider >= 0) {
                if (position <= divider) {
                    root.alpha = MAX_ALPHA
                } else {
                    root.alpha = MIN_ALPHA
                }
            }
        } else {
            if (position == divider || divider < 0 || divider == dices.size) root.alpha = MAX_ALPHA
            else root.alpha = MIN_ALPHA
        }
    }


    override fun getItemCount(): Int = dices.size


    override fun onClick(diceView: View) {
        val dice = diceView.tag as Dice
        val index = dices.indexOfFirst { it === dice }
        if (index < 0) return
        divider = index
        adapterActions.onClickRoll(dice)
        divider = if (adapterActions.setChangeAlphaRegime()) dices.size
        else index
        println(dice.value)
    }

    override fun onLongClick(diceView: View): Boolean {
        PopUp(diceView, dicePopUpAction).createDicePop()
        return true
    }

    companion object {
        const val MAX_ALPHA = 1F
        const val MIN_ALPHA = 0.6F
    }
}