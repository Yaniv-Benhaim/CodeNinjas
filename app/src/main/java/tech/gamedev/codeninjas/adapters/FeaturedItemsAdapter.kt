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


    private var nextListener: NextClickedListener? = null
    private var backListener: BackClickedListener? = null

    inner class FeaturedViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
        fun initialize() {
            /*itemView.btnBack.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    backListener?.onBackClicked()
                }
            }
            itemView.btnNext.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    nextListener?.onNextClicked()
                }
            }*/
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
            0 -> glide.load(R.drawable.ninja_one).into(holder.itemView.ivFeaturedBg)
            1 -> glide.load(R.drawable.ninja2).into(holder.itemView.ivFeaturedBg)
            2 -> glide.load(R.drawable.ninja3).into(holder.itemView.ivFeaturedBg)
            3 -> glide.load(R.drawable.ninja4).into(holder.itemView.ivFeaturedBg)

        }

    }

    override fun getItemCount() = getFeaturedItems().size

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
