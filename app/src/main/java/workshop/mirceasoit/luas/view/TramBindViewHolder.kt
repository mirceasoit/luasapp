package workshop.mirceasoit.luas.view

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import workshop.mirceasoit.luas.BR

class TramBindViewHolder <Data, Listener> internal constructor(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Data, listener: Listener) {
        binding.setVariable(BR.data, item)
        binding.setVariable(BR.listener, listener)
        binding.executePendingBindings()
    }
}