package eu.ampersand.objectapp.repository

import eu.ampersand.objectapp.network.ApiService
import eu.ampersand.objectapp.utils.NetworkResult
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Named

/*
 * File: PhoneObjectsRepository.kt
 * ------------------------------
 * Owner: Esther
 * © 2024 Ampersand. All rights reserved.
 */

/**
 * PhoneObjectsRepository class is responsible for handling data operations
 * related to phone objects. It communicates with the API service to fetch
 * phone object data and provides a flow of network results.
 *
 * @param apiService An instance of ApiService to perform network operations.
 */
class PhoneObjectsRepository @Inject constructor(@Named("default") private val apiService: ApiService){
    fun getPhoneObjects() = flow {
        emit(NetworkResult.Loading(true))
        val response = apiService.getObjects()
        emit(NetworkResult.Success(response))
    }.catch { exception ->
        exception.message?.let { emit(NetworkResult.Failure(it)) }
    }
}