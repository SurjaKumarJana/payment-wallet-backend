package org.surja.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.surja.dto.UserCreatedPayload;
import org.surja.dto.UserDto;
import org.surja.entity.User;
import org.surja.repo.UserRepo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
public class UserService {

    private static Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private KafkaTemplate kafkaTemplate;
    @Value("${user.created.topic}")
    private String userCreatedTopic;

    @Transactional
    public Long createUser(UserDto userDto) throws ExecutionException, InterruptedException {

        User user = User.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .phone(userDto.getPhone())
                .kycNumber(userDto.getKycNumber())
                .build();
        userRepo.save(user);

        UserCreatedPayload userCreatedPayload = UserCreatedPayload.builder()
                .userId(user.getId())
                .userEmail(user.getEmail())
                .userName(user.getName())
                .requestId(MDC.get("requestId"))
                .build();

        Future<SendResult<String,Object>> future  = kafkaTemplate
                .send(userCreatedTopic, userCreatedPayload.getUserEmail(),userCreatedPayload);

        LOGGER.info("Pushed userCreatedPayload to kafka: {}",future.get());

        return user.getId();

    }
}
