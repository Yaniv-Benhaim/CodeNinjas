package tech.gamedev.codeninjas.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import kotlinx.android.synthetic.main.item_change_weapon.view.*
import kotlinx.android.synthetic.main.item_choose_your_weapon.view.*
import tech.gamedev.codeninjas.R
import tech.gamedev.codeninjas.utils.getWeapons

class ChangeWeaponAdapter(val glide: RequestManager) : RecyclerView.Adapter<ChangeWeaponAdapter.ChooseWeaponViewHolder> () {


    private var newWeaponListener: NewWeaponClickedListener? = null


    inner class ChooseWeaponViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
        fun initialize() {
            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    newWeaponListener?.onWeaponClicked(getWeapons()[adapterPosition])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChooseWeaponViewHolder {
        return ChooseWeaponViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_change_weapon, parent, false))
    }

    override fun onBindViewHolder(holder: ChooseWeaponViewHolder, position: Int) {
        val weapon = getWeapons()[position]
        holder.itemView.tvNewWeapon.text = weapon
        holder.initialize()
        when(position) {
            0 -> glide.load(R.drawable.kotlin_logo).into(holder.itemView.ivWeaponLogo)
            1 -> glide.load(R.drawable.java_logo).into(holder.itemView.ivWeaponLogo)
            2 -> glide.load(R.drawable.cplusplus_logo).into(holder.itemView.ivWeaponLogo)
            3 -> glide.load(R.drawable.swift_logo).into(holder.itemView.ivWeaponLogo)
            4 -> glide.load(R.drawable.javascript_logo).into(holder.itemView.ivWeaponLogo)
        }

    }

    override fun getItemCount() = getWeapons().size

    interface NewWeaponClickedListener {
        fun onWeaponClicked(newWeapon: String)
    }


    fun setOnWeaponClickedListener(listener: NewWeaponClickedListener) {
        newWeaponListener = listener
    }

}
