package com.pranab.security.auth

import com.pranab.security.config.JWTService
import com.pranab.security.user.Role
import com.pranab.security.user.User
import com.pranab.security.user.UserRepository
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.crypto.password.PasswordEncoder
import spock.lang.Specification
import spock.lang.Subject

class AuthenticationServiceTest extends Specification {

    UserRepository repository = Mock();
    PasswordEncoder passwordEncoder = Mock();
    def jwtService = Stub(JWTService);
    AuthenticationManager authenticationManager = Mock();

    @Subject
    AuthenticationService authenticationService = new AuthenticationService(repository, passwordEncoder, jwtService, authenticationManager);

    var jwtToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzb21ldGhpbmdAZ21haWwuY29tIiwiaWF0IjoxNjc5ODg5MDQ0LCJleHAiOjE2Nzk4OTA0ODR9.WFmlsZdLNJhV_5v-RWovu4NlhDvWceLmY7OMHXM31cg"


    def "test register"(){
        given :

        RegisterRequest request = new RegisterRequest();
        request.firstName = "test"
        request.lastName ="test"
        request.email="something@gmail.com"
        request.password="password"

        User user = new User();
        repository.save() >> null
        passwordEncoder.encode(_) >> "password"
        jwtService.generateToken(_) >> jwtToken
        when:
         def authenticationResponse = authenticationService.register(request);

        then:
        authenticationResponse.getToken() == jwtToken;
        1 * repository.save(_)

    }

}