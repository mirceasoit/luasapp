package workshop.mirceasoit.luas.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import workshop.mirceasoit.luas.R
import workshop.mirceasoit.luas.databinding.ActivityTramsBinding
import workshop.mirceasoit.luas.viewmodel.TramsViewModel
import java.util.*


class TramsActivity : AppCompatActivity() {

    private lateinit var adapter: TramsAdapter
    private lateinit var viewModel: TramsViewModel
    private lateinit var binding: ActivityTramsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = TramsAdapter(clickListener)
        viewModel = ViewModelProvider(this).get(TramsViewModel::class.java)
        viewModel.stopInfo.observe(this, Observer { result ->
            when (result) {
                is TramsViewModel.State.Loading -> {
                }
                is TramsViewModel.State.Success -> {
                    adapter.submitData(result.stopInfo, viewModel.getDirection(getCurrentHour()))
                    adapter.notifyDataSetChanged()
                }
                is TramsViewModel.State.Error -> {
                    binding.error.text = result.error
                }
            }
        })
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_trams
        )
        binding.let {
            it.viewmodel = viewModel
            it.lifecycleOwner = this
            it.content.layoutManager = GridLayoutManager(this, 2).also { layoutManager ->
                layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return adapter.getSpanSize(position)
                    }
                }
            }
            it.content.adapter = adapter
        }
        loadData()
    }

    private fun loadData() {
        viewModel.getData(getCurrentHour())
    }

    private fun getCurrentHour(): Int {
        val rightNow: Calendar = Calendar.getInstance()
        return rightNow[Calendar.HOUR_OF_DAY]
    }

    private val clickListener = object : TramsAdapter.OnClickListener {
        override fun onTramClick() {
            Toast.makeText(
                this@TramsActivity,
                getString(R.string.tram_click_message),
                Toast.LENGTH_LONG
            ).show()
        }

        override fun onRefreshClick() {
            loadData()
        }

    }
}