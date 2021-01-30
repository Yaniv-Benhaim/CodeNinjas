package tech.gamedev.codeninjas.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_choose_your_weapon.view.*
import tech.gamedev.codeninjas.R
import tech.gamedev.codeninjas.utils.getWeapons

class ChooseWeaponAdapter : RecyclerView.Adapter<ChooseWeaponAdapter.ChooseWeaponViewHolder> () {


    private var nextListener: NextClickedListener? = null
    private var backListener: BackClickedListener? = null

    inner class ChooseWeaponViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
        fun initialize() {
            itemView.btnBack.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    backListener?.onBackClicked()
                }
            }
            itemView.btnNext.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    nextListener?.onNextClicked()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChooseWeaponViewHolder {
        return ChooseWeaponViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_choose_your_weapon, parent, false))
    }

    override fun onBindViewHolder(holder: ChooseWeaponViewHolder, position: Int) {
        val weapon = getWeapons()[position]
        holder.itemView.tvWeapon.text = weapon
        holder.initialize()
        when(position) {
            0 -> holder.itemView.tvWeapon.setTextColor(Color.CYAN)
            1 -> holder.itemView.tvWeapon.setTextColor(Color.BLUE)
            2 -> holder.itemView.tvWeapon.setTextColor(Color.GREEN)
            3 -> holder.itemView.tvWeapon.setTextColor(Color.MAGENTA)
            4 -> holder.itemView.tvWeapon.setTextColor(Color.RED)
        }

    }

    override fun getItemCount() = getWeapons().size

    interface NextClickedListener {
        fun onNextClicked()
    }

    interface BackClickedListener {
        fun onBackClicked()
    }

    fun setOnBackClickedListener(listener: BackClickedListener) {
        backListener = listener
    }
    fun setOnNextClickedListener(listener: NextClickedListener) {
        nextListener = listener
    }
}
