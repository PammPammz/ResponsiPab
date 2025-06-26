package com.example.responsipab.data.equipment

import android.util.Log
import javax.inject.Inject

class EquipmentRepositoryImpl @Inject constructor(
    private val api: EquipmentApi
) : EquipmentRepository {

    override suspend fun getEquipments(): Result<EquipmentsResponse> {
        return try {
            val equipments = api.getEquipments()
            Log.d("DataFlow-Repo", "API Response Received. Equipment count: ${equipments.paginatedEquipments.equipmentList.size}")
            Result.success(equipments)
        } catch (e: Exception) {
            Log.e("DataFlow-Repo", "API Call Failed in Repository:", e)
            Result.failure(e)
        }
    }

    override suspend fun getEquipmentDetail(slug: String): Result<EquipmentDetailResponse> {
        return try {
            val response = api.getEquipmentDetail(slug)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}