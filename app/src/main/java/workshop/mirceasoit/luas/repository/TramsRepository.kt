package workshop.mirceasoit.luas.repository

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import workshop.mirceasoit.luas.api.TramsApi
import workshop.mirceasoit.luas.model.StopInfo

class TramsRepository {

    interface DataResponseCallback {
        fun onSuccess(info: StopInfo)
        fun onError(error: String)
    }

    fun getData(stopCode: String, dataResponseCallback: DataResponseCallback) {
        TramsApi.retrofitService.getResponse(stopCode).enqueue(object : Callback<StopInfo> {
            override fun onResponse(call: Call<StopInfo>, response: Response<StopInfo>) {
                val data: StopInfo = response.body()!!
                dataResponseCallback.onSuccess(data)
            }

            override fun onFailure(call: Call<StopInfo>, t: Throwable?) {
                dataResponseCallback.onError(t!!.message!!)
            }
        })
    }
}