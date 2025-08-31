package org.surja.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.surja.dto.UserDto;
import org.surja.entity.User;
import org.surja.repo.UserRepo;

@Service
public class UserService {

    private static Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserRepo userRepo;

    @Transactional
    public Long createUser(UserDto userDto){

        User user = User.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .phone(userDto.getPhone())
                .kycNumber(userDto.getKycNumber())
                .build();
        userRepo.save(user);



        return user.getId();

    }
}
