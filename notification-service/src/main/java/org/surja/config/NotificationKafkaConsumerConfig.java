package org.surja.config;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.surja.dto.UserCreatedPayload;

@Configuration
public class NotificationKafkaConsumerConfig {

    private static Logger LOGGER = LoggerFactory.getLogger(NotificationKafkaConsumerConfig.class);

    private static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${email.config.usernam}")
    private String mailSender ;

    @KafkaListener(topics = "${user.created.topic}", groupId = "email")
    public void consumeUserCreateTopic(ConsumerRecord payload) throws JsonProcessingException {

        UserCreatedPayload userCreatedPayload = OBJECT_MAPPER.readValue(payload.value().toString(), UserCreatedPayload.class);

        MDC.put("requestId", userCreatedPayload.getRequestId());
        LOGGER.info("Read from kafka : {}", userCreatedPayload);

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(mailSender);
        simpleMailMessage.setSubject("Welcome "+userCreatedPayload.getUserName()+" to Payment Wallet Service! \uD83C\uDF89\n" );
        simpleMailMessage.setText("Hi "+userCreatedPayload.getUserName() + "\n" +
                "Your wallet account has been successfully created.\n" +
                "You can now securely add money, make payments, and track your transactions with ease.\n" +
                "\n" +
                "\uD83D\uDCB3 Safe. Fast. Reliable.\n" +
                "\n" +
                "We’re glad to have you on board! \uD83D\uDE80");

        simpleMailMessage.setCc(mailSender);
        simpleMailMessage.setTo(userCreatedPayload.getUserEmail());
        javaMailSender.send(simpleMailMessage);
        MDC.clear();;
    }
}
