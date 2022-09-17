package com.stefick.zeljade.core.api

import com.stefick.zeljade.core.models.CategoryItemResponse
import com.stefick.zeljade.core.network.base.ErrorResponse
import com.stefick.zeljade.core.network.base.NetworkFactory
import com.stefick.zeljade.core.network.base.NetworkResultBase

class CompendiumRemoteService() : NetworkFactory<CompendiumAPI>(CompendiumAPI::class.java),
    CompendiumRemoteDataSource {
    override suspend fun requestDataByEntry(entry: String): NetworkResultBase<CategoryItemResponse, ErrorResponse>? =
        api.requestDataByEntry(entry)

    override suspend fun requestDataByCategory(category: String): NetworkResultBase<CategoryItemResponse, ErrorResponse>? =
        api.requestDataByCategory(category)

    override suspend fun requestAllData(): NetworkResultBase<CategoryItemResponse, ErrorResponse>? =
        api.requestAllData()

}
