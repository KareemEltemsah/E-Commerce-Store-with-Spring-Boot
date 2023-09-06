package com.spring.ecommerce.service;

import com.spring.ecommerce.api.model.RegistrationBody;
import com.spring.ecommerce.exception.UserAlreadyExistException;
import com.spring.ecommerce.model.LocalUser;
import com.spring.ecommerce.model.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public LocalUser registerUser(RegistrationBody registrationBody) throws UserAlreadyExistException {
        if (userRepository.findByUsername(registrationBody.getUsername()).isPresent()
            || userRepository.findByEmail(registrationBody.getEmail()).isPresent()) {
            throw  new UserAlreadyExistException();
        }

        LocalUser user = new LocalUser();
        user.setUsername(registrationBody.getUsername());
        user.setEmail(registrationBody.getEmail());
        user.setFirstName(registrationBody.getFirstName());
        user.setLastName(registrationBody.getLastName());
        user.setPassword(registrationBody.getPassword());

        return userRepository.save(user);
    }
}
