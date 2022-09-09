package vttp2022.day34.demo.models;

import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Registration {

    private String id; //optional
    private String name;
    private String email;

    public String getId() {return id;}

    public void setId(String id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public static Registration create(String payload) {
        JsonReader reader = Json.createReader(new StringReader(payload));
        JsonObject obj = reader.readObject();

        final Registration registration = new Registration();
        registration.setName(obj.getString("name"));
        registration.setEmail(obj.getString("email"));
        //since id is optional, check if id is in payload
        if (obj.containsKey("id")) {
            registration.setId(obj.getString("id"));
        }

        return registration;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
            .add("id", id)
            .add("name", name)
            .add("email", email)
            .build();
    }
}
