package com.addressBookApp.service;

import com.addressBookApp.dto.AddressBookDTO;
import com.addressBookApp.model.AddressBook;

import java.util.List;

public interface IAddressBookService {
    AddressBook addEntry(AddressBookDTO dto);
    List<AddressBook> getAll();
    AddressBook getById(Long id);
    AddressBook updateEntry(Long id, AddressBookDTO dto);
    boolean deleteEntry(Long id);
}
