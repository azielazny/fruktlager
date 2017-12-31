package com.fruktlager;

import com.fruktlager.model.Address;
import com.fruktlager.model.Area;
import com.fruktlager.model.Member;
import com.fruktlager.model.MemberType;
import com.fruktlager.model.repositories.MemberRepository;
import com.fruktlager.repositories.CSVMemberRepository;
import com.fruktlager.ui.AuthenticationManager;
import com.fruktlager.ui.AuthenticationScreen;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{


    private final String REPOSITORY_DIRECTORY_PATH="D:/smieci/kobietydokodu.pl/fruktlager/src/main/resources/";

    public static void main(String[] args )
    {

        System.out.println( "Hello World!" );
        new App().start();

    }
    public void start() {
        Scanner scanner = new Scanner(System.in);

        MemberRepository memberRepository = new CSVMemberRepository(REPOSITORY_DIRECTORY_PATH);
        AuthenticationManager authenticationManager = new AuthenticationManager(memberRepository);
        AuthenticationScreen authenticationScreen = new AuthenticationScreen(scanner, authenticationManager);

authenticationScreen.show();
        System.out.println(memberRepository.get(authenticationManager.getMemberNumber()));
        System.out.println(memberRepository.get(113));
        memberRepository.save(new Member(88, new Address("Zenek", "Testowy", "ul. Zabłocka 11", "", "Poland", "Gdańsk", "80-001", "+475555555"), MemberType.PRODUCER, "test@test.com", "hasło", new Area("aaaa"), "testowyLogin"));
    }
}
