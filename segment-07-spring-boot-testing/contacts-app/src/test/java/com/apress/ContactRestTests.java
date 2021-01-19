package com.apress;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContactRestTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void contactTest() {
        Contact[] contacts = this.restTemplate.getForObject("/contacts", Contact[].class);
        assertThat(contacts).isNotNull();
        assertThat(contacts[0].getPhone()).contains("1-800");
    }
}
