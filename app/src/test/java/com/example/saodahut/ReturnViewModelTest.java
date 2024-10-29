package com.example.saodahut;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ReturnViewModelTest {

    private ReturnViewModel viewModel;

    @Mock
    private ReturnRepository mockRepository;

    @Mock
    private Observer<String> statusObserver;

    // Rule to execute LiveData instantly in the same thread for testing purposes
    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        // Initialize mocks and set up the ViewModel with the mock repository
        MockitoAnnotations.initMocks(this);
        viewModel = new ReturnViewModel(mockRepository);

        // Observe LiveData to capture changes
        viewModel.getStatusMessage().observeForever(statusObserver);
    }

    @Test
    public void getStatusMessage_initialState() {
        // Verify that statusMessage is initially null
        assertEquals(null, viewModel.getStatusMessage().getValue());
    }

    @Test
    public void validateAndReturnProduct_BothFieldsEmpty() {
        // Arrange: Set up the expected behavior for mock repository
        when(mockRepository.processReturn("", "")).thenReturn("Please fill all fields.");

        // Act: Call validateAndReturnProduct with both fields empty
        viewModel.validateAndReturnProduct("", "");

        // Assert: Check if LiveData updates with the expected message
        verify(statusObserver).onChanged("Please fill all fields.");
        assertEquals("Please fill all fields.", viewModel.getStatusMessage().getValue());
    }

    @Test
    public void validateAndReturnProduct_ProductIdEmpty() {
        // Arrange
        when(mockRepository.processReturn("", "Damaged")).thenReturn("Please fill all fields.");

        // Act
        viewModel.validateAndReturnProduct("", "Damaged");

        // Assert
        verify(statusObserver).onChanged("Please fill all fields.");
        assertEquals("Please fill all fields.", viewModel.getStatusMessage().getValue());
    }

    @Test
    public void validateAndReturnProduct_ReturnReasonEmpty() {
        // Arrange
        when(mockRepository.processReturn("12345", "")).thenReturn("Please fill all fields.");

        // Act
        viewModel.validateAndReturnProduct("12345", "");

        // Assert
        verify(statusObserver).onChanged("Please fill all fields.");
        assertEquals("Please fill all fields.", viewModel.getStatusMessage().getValue());
    }

    @Test
    public void validateAndReturnProduct_ValidInput() {
        // Arrange
        when(mockRepository.processReturn("12345", "Damaged")).thenReturn("Return Initiated");

        // Act
        viewModel.validateAndReturnProduct("12345", "Damaged");

        // Assert
        verify(statusObserver).onChanged("Return Initiated");
        assertEquals("Return Initiated", viewModel.getStatusMessage().getValue());
    }
}
