// ReturnDamagedGoodsActivity.java
package com.example.saodahut;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.saodahut.ReturnViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class ReturnDamagedGoodsActivity extends AppCompatActivity {

    private ReturnViewModel returnViewModel;
    private EditText productIdInput;
    private EditText returnReasonInput;
    private TextView statusMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_damaged_goods);

        // Initialize UI components
        productIdInput = findViewById(R.id.productIdInput);
        returnReasonInput = findViewById(R.id.returnReasonInput);
        statusMessage = findViewById(R.id.statusMessage);
        Button confirmReturnButton = findViewById(R.id.confirmReturnButton);

        // Initialize Repository and ViewModel with Factory
        ReturnRepository repository = new ReturnRepositoryImpl();
        ReturnViewModelFactory factory = new ReturnViewModelFactory(repository);
        returnViewModel = new ViewModelProvider(this, factory).get(ReturnViewModel.class);

        // Observe LiveData from ViewModel
        returnViewModel.getStatusMessage().observe(this, message -> {
            statusMessage.setText(message);
        });

        // Set up button click listener to trigger return process
        confirmReturnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productId = productIdInput.getText().toString().trim();
                String reason = returnReasonInput.getText().toString().trim();
                returnViewModel.validateAndReturnProduct(productId, reason);
            }
        });
    }
}
