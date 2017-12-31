package com.fruktlager.ui;

import java.util.Scanner;

public class AuthenticationScreen {
    private Scanner scanner;
    private AuthenticationManager authenticationManager;

    public AuthenticationScreen(Scanner scanner, AuthenticationManager authenticationManager) {
        this.scanner = scanner;
        this.authenticationManager = authenticationManager;
    }

    public void show() {
        while (true) {
            System.out.print("Podaj email: ");
            String email = scanner.nextLine();
            System.out.print("Podaj hasło: ");
            String password = scanner.nextLine();

//                String url = "http://localhost:8080/rest/login";
//                Client restClient = Client.create();
//
//                WebResource webResource = restClient.resource(url);
//
//                Map<String, Object> postBody = new HashMap<>();
//                postBody.put("email", email);
//                postBody.put("password", password);
//
//                ClientResponse resp = webResource.accept("application/json;charset=UTF-8")
//                        .type("application/json")
//                        .post(ClientResponse.class, new JSONObject(postBody));
//
//                if (resp.getStatus() != 200) {
//                    System.out.println("Unable to connect to the server");
//                }
//
//            String output = resp.getEntity(String.class);
//            System.out.println("response: " + output);

            if (authenticationManager.authenticate(email)) {
                System.out.println(authenticationManager.getMemeber().getAddress().toString());
                return;
            } else
                System.out.println("Nieprawidłowy username");
        }
    }
}
