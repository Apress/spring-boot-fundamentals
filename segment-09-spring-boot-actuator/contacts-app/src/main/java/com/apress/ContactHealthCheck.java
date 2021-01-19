package com.apress;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class ContactHealthCheck implements HealthIndicator {
    private String path;

    public ContactHealthCheck(@Value("${contact.path:/tmp}")String path){
        this.path = path;
    }

    @Override
    public Health health() {

        try {
            File file = new File(path);
            if(file.exists()){

                if(file.canWrite())
                    return Health.up().build();

                return Health.down().build();

            }else{
                return Health.outOfService().build();
            }
        }catch(Exception ex) {
            return Health.down(ex).build();
        }
    }
}
