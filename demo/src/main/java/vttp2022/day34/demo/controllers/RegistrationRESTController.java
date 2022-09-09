package vttp2022.day34.demo.controllers;

import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vttp2022.day34.demo.models.Registration;
import vttp2022.day34.demo.models.Response;

@RestController
@RequestMapping(path="/api/", produces = MediaType.APPLICATION_JSON_VALUE)
public class RegistrationRESTController {

    private Logger logger = Logger.getLogger(RegistrationRESTController.class.getName());
    
    @PostMapping(path="registration", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postRegistration(@RequestBody String payload) {
        logger.info("Payload: %s".formatted(payload));

        Response resp;

        // Read payload and save the data to db
        String id = UUID.randomUUID().toString().substring(0,8);
        Registration registration;
        try {

            registration  = Registration.create(payload);
            registration.setId(id);

        } catch (Exception e) {

            resp = new Response();
            resp.setCode(400);
            resp.setMessage(e.getMessage());

            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(resp.toJson().toString());
        }

        // Save to db...

        resp = new Response();
        // 201 is created
        resp.setCode(201);
        resp.setMessage(id);
        resp.setData(registration.toJson().toString());

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(resp.toJson().toString());
    }
}
