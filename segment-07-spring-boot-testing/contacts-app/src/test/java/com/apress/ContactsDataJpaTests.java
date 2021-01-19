package com.apress;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(SpringRunner.class)
@DataJpaTest
class ContactsDataJpaTests {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private ContactRepository repository;

	@Test
	public void contactTests() throws Exception {
		Contact contact = new Contact();
		contact.setName("Dan");
		contact.setEmail("dan@email.com");
		contact.setPhone("1-800-CONTACTS");

		this.entityManager.persist(contact);
		Iterable<Contact> contacts = this.repository.findByNameContainsIgnoreCase("Da");

		Contact result = contacts.iterator().next();
		assertThat(result,notNullValue());
		assertThat(result.getName(),equalTo("Dan"));
	}

}
