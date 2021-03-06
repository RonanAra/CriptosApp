package com.example.coinbase.base

import com.example.coinbase.utils.ResponseApi
import retrofit2.Response
import java.lang.Exception

open class BaseRepository {
    suspend fun <T:Any> safeApiCall(call: suspend () -> Response<T>) = safeApiResult (call)

    private suspend fun <T:Any> safeApiResult(call: suspend () -> Response<T>): ResponseApi {
        try {
            val response = call.invoke()

            return if(response.isSuccessful){
                ResponseApi.Success(response.body())
            }else{
                    ResponseApi.Error("Erro desconhecido, tente novamente")
            }
        } catch (error:Exception){
            return ResponseApi.Error("Erro desconhecido, tente novamente")
        }
    }
}