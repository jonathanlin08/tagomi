package com.lin.tagomi.assessment.rest;

import com.lin.tagomi.assessment.entity.Contact;
import com.lin.tagomi.assessment.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {
    @Autowired
    private ContactService contactService;

    @GetMapping("getTotalCount")
    public ResponseEntity<Integer> getAllAppointments() {
        return ResponseEntity.ok(contactService.getContactList().size());
    }

    @PostMapping("/search/number/{term}")
    public ResponseEntity<Iterable<Contact>> searchContactByPhoneTerm(@PathVariable Integer phoneTerm) {
        contactService.searchPhone(phoneTerm);
        return null;
    }

}
