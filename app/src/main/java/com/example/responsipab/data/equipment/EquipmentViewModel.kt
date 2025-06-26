package com.example.responsipab.data.equipment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EquipmentViewModel @Inject constructor(
    private val repository: EquipmentRepository
) : ViewModel() {

    private val _state = MutableStateFlow<EquipmentListState>(EquipmentListState.Loading)
    val state = _state.asStateFlow()

    init {
        fetchEquipments()
    }

    fun fetchEquipments() {
        viewModelScope.launch {
            _state.value = EquipmentListState.Loading
            repository.getEquipments()
                .onSuccess { response ->
                    _state.value = EquipmentListState.Success(
                        equipments = response.paginatedEquipments.equipmentList,
                        categories = response.categories
                    )
                }
                .onFailure { exception ->
                    _state.value = EquipmentListState.Error(exception.localizedMessage ?: "An unknown error occurred")
                }
        }
    }
}