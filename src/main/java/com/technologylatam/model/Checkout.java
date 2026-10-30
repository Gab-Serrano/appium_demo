package com.technologylatam.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Checkout {
    private User user;
    private String firstName;
    private String lastName;
    private String postalCode;
}
