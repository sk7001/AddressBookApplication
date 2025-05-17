package com.addressBookApp.service;

import com.addressBookApp.dto.AddressBookDTO;
import com.addressBookApp.model.AddressBook;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class AddressBookService implements IAddressBookService {
    private final List<AddressBook> addressBooks = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    @Override
    public AddressBook addEntry(AddressBookDTO dto) {
        AddressBook entry = new AddressBook(idCounter.getAndIncrement(), dto.getName(), dto.getPhone());
        addressBooks.add(entry);
        return entry;
    }

    @Override
    public List<AddressBook> getAll() {
        return addressBooks;
    }

    @Override
    public AddressBook getById(Long id) {
        return addressBooks.stream()
                .filter(entry -> entry.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public AddressBook updateEntry(Long id, AddressBookDTO dto) {
        AddressBook entry = getById(id);
        if (entry != null) {
            entry.setName(dto.getName());
            entry.setPhone(dto.getPhone());
        }
        return entry;
    }

    @Override
    public boolean deleteEntry(Long id) {
        return addressBooks.removeIf(entry -> entry.getId().equals(id));
    }
}
