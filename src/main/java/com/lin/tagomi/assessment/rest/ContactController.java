package com.lin.tagomi.assessment.rest;

import com.lin.tagomi.assessment.entity.Contact;
import com.lin.tagomi.assessment.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ContactController {
    @Autowired
    private ContactService contactService;

    @GetMapping("/count/total")
    public ResponseEntity<Integer> getAllEntries() {
        return ResponseEntity.ok(contactService.getContactList().size());
    }

    @GetMapping("/count/name/{name}")
    public ResponseEntity<Integer> countNameMatch(@PathVariable String name) {
        return ResponseEntity.ok(this.searchContactByName(name).getBody().size());
    }

    @GetMapping("/count/phone/{areacode}")
    public ResponseEntity<Integer> countNameMatch(@PathVariable Integer areacode) {
        return ResponseEntity.ok(this.searchContactByPhoneTerm(areacode).getBody().size());
    }

    @PostMapping("/search/phone/{areacode}")
    public ResponseEntity<List<Contact>> searchContactByPhoneTerm(@PathVariable Integer areacode) {
        List<Contact> results = contactService.searchPhone(areacode);
        return ResponseEntity.ok(results);
    }

    @PostMapping("/search/name/{term}")
    public ResponseEntity<List<Contact>> searchContactByName(@PathVariable String term) {
        List<Contact> results = new ArrayList<>();
        results.addAll(contactService.searchFirstName(term));
        results.addAll(contactService.searchLastName(term));
        return ResponseEntity.ok(results);
    }

}
