package com.apress;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ContactRepository extends CrudRepository<Contact,String> {

    Iterable<Contact> findByNameContainsIgnoreCase(@Param("name") String name);

}
