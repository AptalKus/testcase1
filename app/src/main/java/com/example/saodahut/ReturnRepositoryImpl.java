// ReturnRepositoryImpl.java
package com.example.saodahut;

public class ReturnRepositoryImpl implements ReturnRepository {

    @Override
    public String processReturn(String productId, String reason) {
        if (productId.isEmpty() || reason.isEmpty()) {
            return "Please fill all fields.";
        } else {
            return "Return Initiated";
        }
    }
}
