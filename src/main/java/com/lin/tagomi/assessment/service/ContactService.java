package com.lin.tagomi.assessment.service;

import com.lin.tagomi.assessment.data.structure.GenericTrie;
import com.lin.tagomi.assessment.entity.Contact;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ContactService {

    @Value("classpath:addresses.txt")
    private Resource res;

    private List<Contact> contactList;

    private GenericTrie<Character> firstNameTrie;
    private GenericTrie<Character> lastNameTrie;
    private GenericTrie<Integer> phoneTrie;

    @PostConstruct
    public void init() throws IOException {
        this.contactList = new ArrayList<>();
        Files.lines(Paths.get(res.getURI())).forEach(line -> {
            String[] tokens = line.split("\\t");
            if (tokens.length == 3) {
                String name = tokens[0];
                String phone = tokens[1];
                String address = tokens[2];
                Contact c = new Contact(name, phone, address);
                this.contactList.add(c);
            }
        });
        this.initTries();
    }

    public void initTries() {
        this.firstNameTrie = new GenericTrie<>();
        this.lastNameTrie = new GenericTrie<>();
        this.phoneTrie = new GenericTrie<>();
        for (int i = 0; i < this.contactList.size(); i++) {
            Contact contact = this.contactList.get(i);
            Collection<Character> firstNameCharList = contact.getFirstName().chars()
                    .mapToObj(e -> (char) e).collect(Collectors.toList());
            this.firstNameTrie.insert(firstNameCharList, i);
            Collection<Character> lastNameCharList = contact.getLastName().chars()
                    .mapToObj(e -> (char) e).collect(Collectors.toList());
            this.lastNameTrie.insert(lastNameCharList, i);
            Collection<Integer> phoneIntList = contact.getPhone().toString().chars()
                    .mapToObj(e -> Character.getNumericValue((char) e)).collect(Collectors.toList());
            this.phoneTrie.insert(phoneIntList, i);
        }
    }

    public void searchFirstName(String term) {
        Collection<Integer> matchIndices = this.searchTermInTrie(term, this.firstNameTrie);
        printResult(matchIndices);
    }

    public void searchLastName(String term) {
        Collection<Integer> matchIndices = this.searchTermInTrie(term, this.lastNameTrie);
        printResult(matchIndices);
    }

    public void searchPhone(Integer term) {
        Collection<Integer> matchIndices = this.searchPhoneInTrie(term, this.phoneTrie);
        printResult(matchIndices);
    }

    public Collection<Integer> searchTermInTrie(String str, GenericTrie searchTrie) {
        Collection<Character> strTermCharList = str.chars()
                .mapToObj(e -> (char) e).collect(Collectors.toList());
        return searchTrie.searchWithPrefix(strTermCharList);
    }

    private void printResult(Collection<Integer> matchIndices) {
        System.out.println("# of Match found: " + matchIndices.size());
        matchIndices.forEach(i -> System.out.println(this.contactList.get(i).toString()));
    }

    public Collection<Integer> searchPhoneInTrie(Integer num, GenericTrie searchTrie) {
        Collection<Integer> numTermCharList = num.toString().chars()
                .mapToObj(e -> Character.getNumericValue((char) e)).collect(Collectors.toList());
        return searchTrie.searchWithPrefix(numTermCharList);
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    public GenericTrie<Character> getFirstNameTrie() {
        return firstNameTrie;
    }

    public GenericTrie<Character> getLastNameTrie() {
        return lastNameTrie;
    }

    public GenericTrie<Integer> getPhoneTrie() {
        return phoneTrie;
    }
}
