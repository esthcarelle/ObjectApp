package eu.ampersand.objectapp.ui.transform

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.ampersand.objectapp.model.PhoneObjectItem
import eu.ampersand.objectapp.repository.PhoneObjectsRepository
import eu.ampersand.objectapp.utils.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhoneObjectsViewModel @Inject constructor(
    private val repository: PhoneObjectsRepository
) : ViewModel() {

    private val _phoneObjects =
        MutableStateFlow<NetworkResult<List<PhoneObjectItem>>>(NetworkResult.Loading(true))
    val phoneObject: StateFlow<NetworkResult<List<PhoneObjectItem>>> = _phoneObjects

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        getPhoneObjects()
    }

    fun getPhoneObjects() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                repository.getPhoneObjects().collect { value ->
                    _phoneObjects.value = value
                }
            } catch (e: Exception) {
                println("Error: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }
}