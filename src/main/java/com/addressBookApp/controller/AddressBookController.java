package com.addressBookApp.controller;

import com.addressBookApp.dto.AddressBookDTO;
import com.addressBookApp.model.AddressBook;
import com.addressBookApp.service.IAddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addressbook")
public class AddressBookController {

    @Autowired
    private IAddressBookService service;

    @PostMapping("/create")
    public ResponseEntity<AddressBook> create(@RequestBody AddressBookDTO dto) {
        AddressBook entry = service.addEntry(dto);
        return ResponseEntity.ok(entry);
    }

    @GetMapping("/")
    public ResponseEntity<List<AddressBook>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<AddressBook> getById(@PathVariable Long id) {
        AddressBook entry = service.getById(id);
        if (entry != null) {
            return ResponseEntity.ok(entry);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AddressBook> update(@PathVariable Long id, @RequestBody AddressBookDTO dto) {
        AddressBook entry = service.updateEntry(id, dto);
        if (entry != null) {
            return ResponseEntity.ok(entry);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        boolean removed = service.deleteEntry(id);
        if (removed) {
            return ResponseEntity.ok("Deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
