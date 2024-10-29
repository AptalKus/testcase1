// ReturnViewModel.java
package com.example.saodahut;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ReturnViewModel extends ViewModel {

    private final ReturnRepository returnRepository;
    private final MutableLiveData<String> statusMessage = new MutableLiveData<>();

    // Constructor
    public ReturnViewModel(ReturnRepository returnRepository) {
        this.returnRepository = returnRepository;
    }

    // LiveData for observing status messages
    public LiveData<String> getStatusMessage() {
        return statusMessage;
    }

    // Method to validate and process the return
    public void validateAndReturnProduct(String productId, String reason) {
        String result = returnRepository.processReturn(productId, reason);
        statusMessage.setValue(result);
    }
}
