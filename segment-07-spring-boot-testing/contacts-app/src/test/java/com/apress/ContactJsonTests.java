package com.apress;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@JsonTest
public class ContactJsonTests {

    @Autowired
    private JacksonTester<Contact> json;

    @Test
    public void contactSerializeTest() throws Exception {
        Contact contact = new Contact();
        contact.setName("Trevor");
        contact.setEmail("trevor@email.com");
        contact.setPhone("1-800-CONTACTS");

        // Assert against a `.json` file in the same package as the test: resource/com/apress/contact.json
        assertThat(this.json.write(contact)).isEqualToJson("contact.json");
        assertThat(this.json.write(contact)).hasJsonPathStringValue("@.name");
        assertThat(this.json.write(contact)).extractingJsonPathStringValue("@.email")
                .isEqualTo("trevor@email.com");
    }

    @Test
    public void contactDeserializeTest() throws Exception {
        Contact contact = new Contact();
        contact.setName("Don");
        contact.setEmail("don@email.com");
        contact.setPhone("1-800-CONTACTS");


        String jsonString = "{\"name\":\"Don\",\"email\": \"don@email.com\", \"phone\":\"1-800-CONTACTS\" }";
        assertThat(this.json.parse(jsonString))
                .isEqualTo(contact);
        assertThat(this.json.parseObject(jsonString).getName()).isEqualTo("Don");
    }

}
