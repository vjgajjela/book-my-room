package com.booking.bookmyroom;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@ActiveProfiles("test")
@SpringBootTest(classes = { BookMyRoomApplication.class }, webEnvironment = WebEnvironment.DEFINED_PORT)
public class CucumberSpringConfiguration {

}
