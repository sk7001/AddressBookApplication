package com.addressBookApp.service;

import com.addressBookApp.dto.AddressBookDTO;
import com.addressBookApp.exception.AddressBookException;
import com.addressBookApp.model.AddressBook;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
                .orElseThrow(() -> new AddressBookException("Address Book entry with ID " + id + " not found"));
    }

    @Override
    public AddressBook updateEntry(Long id, AddressBookDTO dto) {
        AddressBook entry = getById(id);
        entry.setName(dto.getName());
        entry.setPhone(dto.getPhone());
        return entry;
    }

    @Override
    public boolean deleteEntry(Long id) {
        AddressBook entry = getById(id);
        return addressBooks.remove(entry);
    }
}
