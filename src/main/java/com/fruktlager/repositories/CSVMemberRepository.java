package com.fruktlager.repositories;

import com.fruktlager.model.*;
import com.fruktlager.model.repositories.CSVRepository;
import com.fruktlager.model.repositories.MemberRepository;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class CSVMemberRepository implements MemberRepository, CSVRepository {

    private final String repoDirectoryPath;
    private final String FILE_NAME = "members.csv";
    private String path;

    public CSVMemberRepository(String repoDirectoryPath) {
        this.repoDirectoryPath = repoDirectoryPath;
        this.path = repoDirectoryPath + FILE_NAME;
    }

    @Override
    public Member get(Integer number) {

//        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
//        FileInputStream fis = new FileInputStream("test.txt");
//        InputStreamReader in = new InputStreamReader(fis, "UTF-8");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"))) {
            return br
                    .lines()
                    .map(this::toObject)
                    .filter(c -> c.getNumber().equals(number))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException(String.format("No such client number %s", number)));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Member member) {
        Map<Integer, Member> members = new HashMap<>();
        if (fileExists(path))
            try (BufferedReader br = new BufferedReader(new FileReader(path))) {

                members = br.lines().map(this::toObject)
                        .collect(Collectors.toMap(Member::getNumber, m -> m));

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        members.put(member.getNumber(), member);
        System.out.println(member);

        try (OutputStream outputStream = new FileOutputStream(path, false);
             PrintStream printStream = new PrintStream(outputStream)) {
            for (Member cl : members.values()) {
                System.out.println(cl);
                printStream.println(String.join(",", toLine(cl)));
            }
            printStream.flush();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public Optional<Member> getByUsername(String username) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
//            System.out.println( br
//                    .lines()
//                    .map(this::toObject).filter(m->m.getUsername().equals(username)).findFirst().get().getUsername().toString());
            return br
                    .lines()
                    .map(this::toObject)
                    .filter(m -> m.getUsername().equals(username))
                    .findFirst();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public Optional<Member> getByLoginData(String email, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br
                    .lines()
                    .map(this::toObject)
                    .filter(m -> m.getEmail().equals(email))
                    .filter(m -> m.getPassword().equals(password))
                    .findFirst();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Member toObject(String CSVline) {
        String[] lineSplit = CSVline.split(",");
        String[] addressLines = lineSplit[1].split(";");
//        System.out.println(lineSplit[1]);
        String firstName = addressLines[0];
        String lastName = addressLines[1];
        String line1 = addressLines[2];
        String line2 = addressLines[3];
        String country = addressLines[4];
        String city = addressLines[5];
        String postalCode = addressLines[6];
        String phone = addressLines[7];


        Address address = new Address(firstName, lastName, line1, line2, country, city, postalCode, phone);
        if (lineSplit[2].equals("PRODUCER")) {

            return new Farmer(
                    Integer.parseInt(lineSplit[0]),
                    address,
                    MemberType.PRODUCER,
                    lineSplit[3],
                    lineSplit[4],
                    new Area(lineSplit[5]),
                    lineSplit[6]);
        } else {
            return new Admin(
                    Integer.parseInt(lineSplit[0]),
                    address,
                    MemberType.ADMIN,
                    lineSplit[3],
                    lineSplit[4],
                    new Area(lineSplit[5]),
                    lineSplit[6]);

        }
    }

    private String[] toLine(Member member) {


        String[] CSVLines = new String[]{
                member.getNumber().toString(),
                member.getAddress().toString(),
                member.getMemberType().toString(),
                member.getEmail(),
                member.getPassword(),
                member.getArea().toString(),
                member.getUsername()
        };

        return CSVLines;

    }
}
