package eu.ampersand.objectapp.network

import eu.ampersand.objectapp.model.PhoneObjectItem
import retrofit2.http.GET

/*
 * File: ApiService.kt
 * ------------------------------
 * Owner: Esther
 * © 2024 Ampersand. All rights reserved.
 */

/**
 * ApiService interface defines the API endpoints for network operations
 * related to phone objects. It uses Retrofit annotations to facilitate
 * the HTTP requests to the server.
 */

interface ApiService {
    @GET("objects")
    suspend fun getObjects(): List<PhoneObjectItem>
}