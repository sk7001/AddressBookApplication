package com.addressBookApp.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressBook {
    private Long id;
    private String name;
    private String phone;
}
