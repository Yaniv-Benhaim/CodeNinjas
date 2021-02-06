package tech.gamedev.codeninjas.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import kotlinx.android.synthetic.main.item_featured.view.*
import tech.gamedev.codeninjas.R
import tech.gamedev.codeninjas.utils.getFeaturedItems


class FeaturedItemsAdapter (val glide: RequestManager) : RecyclerView.Adapter<FeaturedItemsAdapter.FeaturedViewHolder> () {


    private var nextListener: ItemClickedListener? = null


    inner class FeaturedViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
        fun initialize() {

            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    nextListener?.onNextClicked(adapterPosition)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeaturedViewHolder {
        return FeaturedViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_featured, parent, false))
    }

    override fun onBindViewHolder(holder: FeaturedViewHolder, position: Int) {
        val item = getFeaturedItems()[position]
        holder.itemView.tvFeatured.text = item

        holder.initialize()
        when(position) {
            0 -> {
                holder.itemView.lottieFeatured.cancelAnimation()
                glide.load(R.drawable.img_ninja_battle).into(holder.itemView.ivFeaturedBg)
                holder.itemView.lottieFeatured.setAnimation(R.raw.tournament_lottie)
                holder.itemView.lottieFeatured.playAnimation()
            }
            1 -> {
                holder.itemView.lottieFeatured.cancelAnimation()
                glide.load(R.drawable.img_watch_phone).into(holder.itemView.ivFeaturedBg)
                holder.itemView.lottieFeatured.setAnimation(R.raw.watch_video2)
                holder.itemView.lottieFeatured.playAnimation()
            }
            2 -> {
                holder.itemView.lottieFeatured.cancelAnimation()
                glide.load(R.drawable.img_ask_questions).into(holder.itemView.ivFeaturedBg)
                holder.itemView.lottieFeatured.setAnimation(R.raw.chat_lottie)
                holder.itemView.lottieFeatured.playAnimation()
            }
            3 -> {
                holder.itemView.lottieFeatured.cancelAnimation()
                glide.load(R.drawable.img_rating).into(holder.itemView.ivFeaturedBg)
                holder.itemView.lottieFeatured.setAnimation(R.raw.rating_lottie)
                holder.itemView.lottieFeatured.playAnimation()
            }

        }

    }

    override fun getItemCount() = getFeaturedItems().size

    interface ItemClickedListener {
        fun onNextClicked(position: Int)
    }


    fun setOnNextClickedListener(listener: ItemClickedListener) {
        nextListener = listener
    }
}
