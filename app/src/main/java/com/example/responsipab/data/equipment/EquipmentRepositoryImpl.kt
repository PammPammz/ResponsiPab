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
}