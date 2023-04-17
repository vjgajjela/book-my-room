package com.booking.bookmyroom;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/book-my-room.feature", glue = {
		 "com.booking.bookmyroom" }, plugin = { "pretty", "html:target/cucumber-reports" })
public class CucumberIntegrationTest {

}
