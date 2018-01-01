package com.fruktlager;

import com.fruktlager.model.repositories.MemberRepository;
import com.fruktlager.repositories.CSVMemberRepository;
import com.fruktlager.ui.MenuScreen;
import com.fruktlager.ui.rest.LoginManager;
import com.fruktlager.ui.LoginScreen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.Scanner;

/**
 * Hello world!
 */
@Path("/index")
public class App extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(App.class);

    private final String REPOSITORY_DIRECTORY_PATH = "D:/smieci/kobietydokodu.pl/fruktlager/src/main/resources/";

    @GET
    public static void main(String[] args) {

        System.out.println("Hello in the Fruktlager System!");
        new App().start();

    }


    private void start() {
        Scanner scanner = new Scanner(System.in);

        MemberRepository memberRepository = new CSVMemberRepository(REPOSITORY_DIRECTORY_PATH);
        LoginManager loginManager = new LoginManager(memberRepository);
        LoginScreen loginScreen = new LoginScreen(scanner, loginManager);

        loginScreen.show();
        LOGGER.debug("Token for member: " + loginScreen.getLoginDataMap().get("token"));
        MenuScreen menuScreen = new MenuScreen(scanner);
        menuScreen.show();

        //        System.out.println(memberRepository.get(authenticationManager.getMemberNumber()));
//        System.out.println(memberRepository.get(113));
//        memberRepository.save(new Member(88, new Address("Zenek", "Testowy", "ul. Zabłocka 11", "", "Poland", "Gdańsk", "80-001", "+475555555"), MemberType.PRODUCER, "test@test.com", "hasło", new Area("aaaa"), "testowyLogin"));
    }
}
