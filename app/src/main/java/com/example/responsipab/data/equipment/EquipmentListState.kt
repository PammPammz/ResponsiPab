package com.example.responsipab.data.equipment

sealed interface EquipmentListState {
    object Loading : EquipmentListState
    data class Success(
        val equipments: List<Equipment>,
        val categories: List<Category>
    ) : EquipmentListState
    data class Error(val message: String) : EquipmentListState
}