// ReturnViewModelFactory.java
package com.example.saodahut;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ReturnViewModelFactory implements ViewModelProvider.Factory {

    private final ReturnRepository repository;

    public ReturnViewModelFactory(ReturnRepository repository) {
        this.repository = repository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ReturnViewModel.class)) {
            return (T) new ReturnViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
