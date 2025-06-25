package com.example.responsipab.data.order

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.responsipab.data.cart.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import javax.inject.Inject

data class CheckoutFormState(
    val fullName: String = "",
    val email: String = "",
    val phone: String = "",
    val address: String = "",
    val notes: String = "",
    val purpose: String = "",
    val deliveryMethod: String = "delivery", // 'delivery' or 'pickup'
    val startDate: String = "", // "YYYY-MM-DD"
    val endDate: String = "" // "YYYY-MM-DD"
)

sealed interface CheckoutEvent {
    data object SubmitOrder : CheckoutEvent
    data class ShowToast(val message: String) : CheckoutEvent
    data object NavigateToOrderSuccess : CheckoutEvent
}

@HiltViewModel
class CheckoutViewModel @Inject constructor(
    private val orderRepository: OrderRepository,
    private val cartRepository: CartRepository
) : ViewModel() {

    var formState by mutableStateOf(CheckoutFormState())
        private set

    var isSubmitting by mutableStateOf(false)
        private set

    private val _eventChannel = Channel<CheckoutEvent>()
    val eventFlow = _eventChannel.receiveAsFlow()

    fun onFullNameChange(value: String) { formState = formState.copy(fullName = value) }
    fun onEmailChange(value: String) { formState = formState.copy(email = value) }
    fun onPhoneChange(value: String) { formState = formState.copy(phone = value) }
    fun onAddressChange(value: String) { formState = formState.copy(address = value) }
    fun onNotesChange(value: String) { formState = formState.copy(notes = value) }
    fun onPurposeChange(value: String) { formState = formState.copy(purpose = value) }
    fun onDeliveryMethodChange(value: String) { formState = formState.copy(deliveryMethod = value) }
    fun onStartDateChange(value: String) { formState = formState.copy(startDate = value) }
    fun onEndDateChange(value: String) { formState = formState.copy(endDate = value) }

    fun submitOrder() {
        viewModelScope.launch {
            if (isSubmitting) return@launch
            isSubmitting = true

            val request = CheckoutRequest(
                fullName = formState.fullName,
                email = formState.email,
                phone = formState.phone,
                address = formState.address.takeIf { it.isNotBlank() },
                notes = formState.notes.takeIf { it.isNotBlank() },
                purpose = formState.purpose.takeIf { it.isNotBlank() },
                deliveryMethod = formState.deliveryMethod,
                rentalPeriod = RentalPeriodRequest(
                    from = formState.startDate,
                    to = formState.endDate
                )
            )

            orderRepository.placeOrder(request)
                .onSuccess {
                    _eventChannel.send(CheckoutEvent.ShowToast("Order placed successfully!"))
                    _eventChannel.send(CheckoutEvent.NavigateToOrderSuccess)
                }
                .onFailure {
                    _eventChannel.send(CheckoutEvent.ShowToast("Error: ${it.message}"))
                }

            isSubmitting = false
        }
    }
}