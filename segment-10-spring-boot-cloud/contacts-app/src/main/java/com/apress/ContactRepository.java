package com.apress;

import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact,String> {

    Iterable<Contact> findByNameContainsIgnoreCase(String name);

}
