package workshop.mirceasoit.luas.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import workshop.mirceasoit.luas.model.StopInfo
import workshop.mirceasoit.luas.repository.TramsRepository

class TramsViewModel : ViewModel() {

    companion object {
        private const val OUTBOUND_STATION = "mar"
        private const val OUTBOUND_DIRECTION = "Outbound"
        private const val INBOUND_STATION = "sti"
        private const val INBOUND_DIRECTION = "Inbound"
    }


    private var _stopInfo = MutableLiveData<State>()
    var stopInfo: LiveData<State> = _stopInfo

    private val dataResponseCallback: TramsRepository.DataResponseCallback =
        object : TramsRepository.DataResponseCallback {
            override fun onSuccess(info: StopInfo) {
                _stopInfo.postValue(
                    State.Success(
                        info
                    )
                )
            }

            override fun onError(error: String) {
                _stopInfo.postValue(
                    State.Error(
                        error
                    )
                )
            }
        }

    fun getData(hour: Int) {
        _stopInfo.value = State.Loading
        val tramsRepository = TramsRepository()
        tramsRepository.getData(getStopCode(hour), dataResponseCallback)
    }

    private fun getStopCode(hour: Int): String {
        return if (hour in 0..11) {
            OUTBOUND_STATION
        } else {
            INBOUND_STATION
        }
    }

    fun getDirection(hour: Int): String {
        return if (hour in 0..11) {
            OUTBOUND_DIRECTION
        } else {
            INBOUND_DIRECTION
        }
    }

    sealed class State {
        object Loading : State()
        data class Success(val stopInfo: StopInfo) : State()
        data class Error(val error: String) : State()

        fun isLoading() = this is Loading
        fun isSuccess() = this is Success
        fun isError() = this is Error
    }

}

