package com.stefick.zeljade.core.api

import com.stefick.zeljade.core.models.CategoryItemResponse
import com.stefick.zeljade.core.network.base.NetworkResultBase

interface CompendiumRemoteDataSource {

    suspend fun requestDataByEntry(entry: String): NetworkResultBase<CategoryItemResponse, Any>?

    suspend fun requestDataByCategory(category: String): NetworkResultBase<CategoryItemResponse, Any>?

    suspend fun requestAllData(): NetworkResultBase<CategoryItemResponse, Any>?

}