package com.example.responsipab.data.equipment

interface EquipmentRepository {
    suspend fun getEquipments(): Result<EquipmentsResponse>
}