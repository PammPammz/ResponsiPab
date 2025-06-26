package com.example.responsipab.data.equipment

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EquipmentDetailViewModel @Inject constructor(
    private val repository: EquipmentRepository,
    savedStateHandle: SavedStateHandle // Hilt provides this to access navigation arguments
) : ViewModel() {

    private val _state = MutableStateFlow<EquipmentDetailState>(EquipmentDetailState.Loading)
    val state = _state.asStateFlow()

    init {
        // Get the 'slug' (String) from the navigation arguments
        val equipmentSlug: String? = savedStateHandle.get<String>("slug")

        if (equipmentSlug != null) {
            fetchEquipmentDetail(equipmentSlug)
        } else {
            _state.value = EquipmentDetailState.Error("Equipment slug not found.")
        }
    }

    private fun fetchEquipmentDetail(slug: String) {
        viewModelScope.launch {
            _state.value = EquipmentDetailState.Loading
            repository.getEquipmentDetail(slug)
                .onSuccess { response ->
                    _state.value = EquipmentDetailState.Success(
                        equipment = response.equipment,
                        isInCart = response.inCart
                    )
                }
                .onFailure { exception ->
                    _state.value = EquipmentDetailState.Error(exception.localizedMessage ?: "An unknown error occurred")
                }
        }
    }
}