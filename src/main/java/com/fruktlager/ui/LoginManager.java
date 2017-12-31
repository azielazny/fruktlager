package com.fruktlager.ui;

import com.fruktlager.application.AppConfig;
import com.fruktlager.model.Member;
import com.fruktlager.model.repositories.MemberRepository;
import com.fruktlager.repositories.CSVMemberRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Path("/login")
public class LoginManager {

    private static final Logger LOGGER = LogManager.getLogger(LoginManager.class);

    private MemberRepository memberRepository = new CSVMemberRepository(new AppConfig().getRepositoryDirectoryPath());
    private Member member;
    private final String SECRET_KEY = new AppConfig().getApiSecretKey();

    public LoginManager(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public LoginManager() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Object getEmptyJson() {
        LOGGER.error("Bad request for Login by GET method.");
        return "{ \"error\": {\"code\": 400,\"message\": \"Bad Request\" }}";

    }

    @POST
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Object getAuthenticationData(@FormParam("email") String email, @FormParam("password") String password) throws UnsupportedEncodingException {

        Optional<Member> memberOptional = memberRepository.getByLoginData(email, password);
        System.out.println(memberOptional.toString());
        if (!memberOptional.isPresent()) return "{\"data\":{\"error\":\"Incorrect email or password.\"}}";

        member = memberOptional.get();

        String jwtToken = buildJwtHash();


        return "{\"data\":{ \"username\" : \"" + member.getUsername() + "\" , " +
                "\"id\" : \"" + member.getNumber() + "\" , " +
                "\"accountType\" : \"" + member.getMemberType() + "\" , " +
                "\"token\" : \"" + jwtToken + "\" }}";
    }

    private String buildJwtHash() throws UnsupportedEncodingException {

        Map jwtClaims = new HashMap<>();
        jwtClaims.put("iss", "fruktlager SC");
        jwtClaims.put("sub", "frukt");
        jwtClaims.put("id", member.getNumber());
        jwtClaims.put("username", member.getUsername());
        jwtClaims.put("firstname", member.getAddress().getFirstName());
        jwtClaims.put("lastname", member.getAddress().getLastName());
        jwtClaims.put("accountType", member.getMemberType());

        return Jwts.builder()
                .setClaims(jwtClaims)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes("UTF-8"))
                .compact();
    }


}
