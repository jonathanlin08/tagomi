package com.lin.tagomi.assessment.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContactServiceTest {

    @Autowired
    private ContactService contactService;

    @Test
    public void load_addresses_txt_file_test() {
        Assert.assertTrue(contactService.getContactList().size() > 0);
    }

    @Test
    public void search_phone_test() {
        Collection<Integer> result = contactService.searchPhoneInTrie(433, contactService.getPhoneTrie());
        Assert.assertTrue(result.size() > 0);
    }

    @Test
    public void search_name_test() {
        Collection<Integer> result = contactService.searchTermInTrie("Jon", contactService.getFirstNameTrie());
        Assert.assertTrue(result.size() > 0);
    }

}