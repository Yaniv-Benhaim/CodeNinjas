package tech.gamedev.codeninjas.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import kotlinx.android.synthetic.main.item_user.view.*
import tech.gamedev.codeninjas.R
import tech.gamedev.codeninjas.data.models.User

class UserBattleAdapter(val users: List<User>, val glide: RequestManager) : RecyclerView.Adapter<UserBattleAdapter.BattleViewHolder>() {

    var listener: OnUserClicked? = null

    inner class BattleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun initialize(position: Int) {
            itemView.setOnClickListener {
                listener?.userClicked(position)
                itemView.ivProfileImage.animate().apply {
                    duration = 200
                    rotationXBy(360f)
                }.start()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BattleViewHolder {
        return BattleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent , false))
    }

    override fun onBindViewHolder(holder: BattleViewHolder, position: Int) {
        val user = users[position]
        holder.itemView.apply {
            tvUserName.text = user.userName
            glide.load(user.profileImg).into(ivProfileImage)

        }
        holder.initialize(position)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    interface OnUserClicked {
        fun userClicked(position: Int)
    }
}