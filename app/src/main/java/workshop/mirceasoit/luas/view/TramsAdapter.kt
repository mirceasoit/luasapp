package workshop.mirceasoit.luas.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import workshop.mirceasoit.luas.R
import workshop.mirceasoit.luas.model.StopInfo
import workshop.mirceasoit.luas.model.Tram

class TramsAdapter(private val listener: OnClickListener)
    : ListAdapter<Displayable, TramBindViewHolder<Displayable, TramsAdapter.OnClickListener>>(
    DIFF_CALLBACK
) {

    companion object {
        private const val STATION_ITEM =
            R.layout.station_item
        private const val DIRECTION_ITEM =
            R.layout.direction_item
        private const val TRAM_ITEM = R.layout.tram_item
        private const val REFRESH_ITEM =
            R.layout.refresh_item

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Displayable>() {
            override fun areItemsTheSame(oldItem: Displayable, newItem: Displayable): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Displayable, newItem: Displayable): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TramBindViewHolder<Displayable, OnClickListener> {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(inflater, viewType, parent, false)
        return TramBindViewHolder(binding)
    }

    override fun getItemViewType(position: Int): Int {
        return currentList[position].type
    }

    override fun onBindViewHolder(holder: TramBindViewHolder<Displayable, OnClickListener>, position: Int) {
        val item = currentList[position]
        holder.bind(item, listener)
    }

    fun getSpanSize(position: Int): Int = when (getItemViewType(position)) {
        in arrayOf(
            STATION_ITEM,
            DIRECTION_ITEM
        ) -> 1
        else -> 2
    }

    fun submitData(stopInfo: StopInfo, direction: String) {
        val entries: MutableList<Displayable> = mutableListOf()
        entries.add(
            Displayable(
                type = STATION_ITEM,
                stationName = stopInfo.stop
            )
        )
        entries.add(
            Displayable(
                type = DIRECTION_ITEM,
                direction = direction
            )
        )
        stopInfo.directions.forEach { directionItem ->
            if (directionItem.name.equals(direction)) {
                directionItem.trams.forEach { tram ->
                    entries.add(
                        Displayable(
                            type = TRAM_ITEM,
                            tram = tram
                        )
                    )
                }
            }
        }
        entries.add(Displayable(REFRESH_ITEM))
        submitList(entries)
    }

    interface OnClickListener {
        fun onTramClick()
        fun onRefreshClick()
    }
}

data class Displayable(val type: Int,
                       val stationName:String? = null,
                       val direction:String? = null,
                       val tram: Tram? = null)