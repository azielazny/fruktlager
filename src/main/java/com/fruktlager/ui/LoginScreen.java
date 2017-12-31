package com.fruktlager.ui;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class LoginScreen {

    private static final Logger LOGGER = LogManager.getLogger(LoginScreen.class);


    private Scanner scanner;
    private LoginManager loginManager;
    private String restUri = "http://localhost:8080/rest/login";
    private Map<String, Object> loginDataMap;


    public Map<String, Object> getLoginDataMap() {
        return loginDataMap;
    }

    public LoginScreen(Scanner scanner, LoginManager loginManager) {
        this.scanner = scanner;
        this.loginManager = loginManager;
    }

    public void show() {
        while (true) {
            System.out.print("Podaj email: ");
            String email = scanner.nextLine();
            System.out.print("Podaj hasło: ");
            String password = scanner.nextLine();

            String respFromApi = sendLoginData(email, password);
            LOGGER.debug("Response from API: " + respFromApi);

            createMapFromJson(respFromApi);
            if (loginDataMap.get("token") != null) {
                LOGGER.debug("Token for member: " + loginDataMap.get("token"));
                return;
            }
            System.out.print(loginDataMap.get("error"));
            System.out.println(" Try again...");
        }
    }

    private void createMapFromJson(String respFromApi) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Map<String, Map> decodeJson = mapper.readValue(respFromApi, new TypeReference<Map<String, Map>>() {
            });
            loginDataMap = decodeJson.get("data");
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("Problem with creating a map with JSON.");
        }
    }

    private String sendLoginData(String email, String password) {
        Client restClient = Client.create();

        WebResource webResource = restClient.resource(restUri);

        MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
        formData.add("email", email);
        formData.add("password", password);

        ClientResponse resp = webResource.accept("application/json;charset=UTF-8")
                .type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
                .post(ClientResponse.class, formData);

        if (resp.getStatus() != 200) {
            System.out.println(
                    "{ \"error\": {\"code\": 503,\"message\": \"Unable to connect to the server\" }}");
            LOGGER.error("Unable to connect to the server");
        }

        return resp.getEntity(String.class);

    }
}
