package com.example.responsipab.data.equipment

sealed interface EquipmentDetailState {
    object Loading : EquipmentDetailState
    data class Success(val equipment: Equipment, val isInCart: Boolean) : EquipmentDetailState
    data class Error(val message: String) : EquipmentDetailState
}