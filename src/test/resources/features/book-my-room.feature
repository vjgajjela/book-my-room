Feature: Meeting Room Booking
   Scenario: Searching for meeting room
    Given User searching for meeting room
    When User provide input as "INVALID_TIME" for "failure" scenario
    Then Verify the HTTP status code is 400 for "failure" scenario
    And Verify the status is "400 BAD_REQUEST"
    
   Scenario: Searching for meeting room
    Given User searching for meeting room
    When User provide input as "PAST_DATES" for "failure" scenario
    Then Verify the HTTP status code is 400 for "failure" scenario
    And Verify the status is "400 BAD_REQUEST"
    And Error message is "The provided date or time range is in the past and cannot be accepted for booking."
    
   Scenario: Searching for meeting room
    Given User searching for meeting room
    When User provide input as "INCORRECT_TIME_RANGE" for "failure" scenario
    Then Verify the HTTP status code is 400 for "failure" scenario
    And Verify the status is "400 BAD_REQUEST"
    And Error message is "The end time should be greater than the start time."
    
   Scenario: Searching for meeting room
    Given User searching for meeting room
    When User provide input as "NOT_15_MIN_TIME_RANGE" for "failure" scenario
    Then Verify the HTTP status code is 400 for "failure" scenario
    And Verify the status is "400 BAD_REQUEST"
    And Error message is "The time range must be in increments of 15 minutes."
    
   Scenario: Searching for meeting room
    Given User searching for meeting room
    When User provide input as "MAINT_TIME_INCL" for "failure" scenario
    Then Verify the HTTP status code is 409 for "failure" scenario
    And Verify the status is "409 CONFLICT"
    And Error message is "Meeting rooms not available at this time due to scheduled maintenance."
    
   Scenario: Searching for meeting room
    Given User searching for meeting room
    When User provide input as "MAINT_TIME_EXCL" for "failure" scenario
    Then Verify the HTTP status code is 409 for "failure" scenario
    And Verify the status is "409 CONFLICT"
    And Error message is "Meeting rooms not available at this time due to scheduled maintenance."
    
   Scenario: Searching for meeting room
    Given User searching for meeting room
    When User provide input as "MAINT_TIME_START_EXCL" for "failure" scenario
    Then Verify the HTTP status code is 409 for "failure" scenario
    And Verify the status is "409 CONFLICT"
    And Error message is "Meeting rooms not available at this time due to scheduled maintenance."
    
   Scenario: Searching for meeting room
    Given User searching for meeting room
    When User provide input as "MAINT_TIME_END_EXCL" for "failure" scenario
    Then Verify the HTTP status code is 409 for "failure" scenario
    And Verify the status is "409 CONFLICT"
    And Error message is "Meeting rooms not available at this time due to scheduled maintenance."
    
   Scenario: Searching for meeting room
    Given User searching for meeting room
    When User provide input as "FUTURE_TIME" for "success" scenario
    Then Verify the HTTP status code is 200 for "success" scenario
    And Verify the result has meeting rooms
    
   Scenario: Booking meeting room
    Given User booking meeting room
    When User provide input as "PAST_DATES" and capacity 1 for "failure" scenario with key "43543567"
    Then Verify the HTTP status code is 400 for "failure" scenario
    And Verify the status is "400 BAD_REQUEST"
    And Error message is "Meetings can only be booked for today's date. Please ensure that the provided date is today's date."
    
   Scenario: Booking meeting room
    Given User booking meeting room
    When User provide input as "TODAYS_DATE_PAST_TIME" and capacity 1 for "failure" scenario with key "43543568"
    Then Verify the HTTP status code is 400 for "failure" scenario
    And Verify the status is "400 BAD_REQUEST"
    And Error message is "The provided date or time range is in the past and cannot be accepted for booking."
    
   Scenario: Booking meeting room
    Given User booking meeting room
    When User provide input as "INCORRECT_TIME_RANGE" and capacity 1 for "failure" scenario with key "43543569"
    Then Verify the HTTP status code is 400 for "failure" scenario
    And Verify the status is "400 BAD_REQUEST"
    And Error message is "The end time should be greater than the start time."
    
   Scenario: Booking meeting room
    Given User booking meeting room
    When User provide input as "NOT_15_MIN_TIME_RANGE" and capacity 1 for "failure" scenario with key "43543570"
    Then Verify the HTTP status code is 400 for "failure" scenario
    And Verify the status is "400 BAD_REQUEST"
    And Error message is "The time range must be in increments of 15 minutes."
    
   Scenario: Booking meeting room
    Given User booking meeting room
    When User provide input as "MAINT_TIME_INCL" and capacity 1 for "failure" scenario with key "43543571"
    Then Verify the HTTP status code is 409 for "failure" scenario
    And Verify the status is "409 CONFLICT"
    And Error message is "Meeting rooms not available at this time due to scheduled maintenance."
    
   Scenario: Booking meeting room
    Given User booking meeting room
    When User provide input as "MAINT_TIME_EXCL" and capacity 1 for "failure" scenario with key "43543572"
    Then Verify the HTTP status code is 409 for "failure" scenario
    And Verify the status is "409 CONFLICT"
    And Error message is "Meeting rooms not available at this time due to scheduled maintenance."
    
   Scenario: Booking meeting room
    Given User booking meeting room
    When User provide input as "FUTURE_TIME" and capacity 0 for "failure" scenario with key "43543573"
    Then Verify the HTTP status code is 400 for "failure" scenario
    And Verify the status is "400 BAD_REQUEST"
    And Error message is "Meetings can only be booked for today's date. Please ensure that the provided date is today's date."
    
   Scenario: Booking meeting room
    Given User booking meeting room
    When User provide input as "CURRENT_TIME" and capacity 0 for "failure" scenario with key "43543574"
    Then Verify the HTTP status code is 400 for "failure" scenario
    And Verify the status is "400 BAD_REQUEST"
    And Error message is "Meeting rooms can only be booked if the capacity to do so is greater than one."
	
   Scenario: Booking meeting room
    Given User booking meeting room
    When User provide input as "CURRENT_TIME" and capacity 21 for "failure" scenario with key "43543575"
    Then Verify the HTTP status code is 409 for "failure" scenario
    And Verify the status is "409 CONFLICT"
    And Error message is "No meeting rooms are available at this time, please try again later."
	
   Scenario: Booking meeting room
    Given User booking meeting room
    When User provide input as "CURRENT_TIME" and capacity 3 for "success" scenario with key "77997967"
    Then Verify the HTTP status code is 201 for "success" scenario
    And Verify booking details with capacity 3
	
   Scenario: Booking meeting room
    Given User booking meeting room
    When User provide input as "CURRENT_TIME" and capacity 3 for "failure" scenario with key "77997967"
    Then Verify the HTTP status code is 409 for "failure" scenario
    And Verify the status is "409 CONFLICT"
    And Error message is "Specified booking already exists"
	
   Scenario: Booking meeting room
    Given User booking meeting room
    When User provide input as "CURRENT_TIME" and capacity 7 for "success" scenario with key "77997968"
    Then Verify the HTTP status code is 201 for "success" scenario
    And Verify booking details with capacity 7
	
   Scenario: Booking meeting room
    Given User booking meeting room
    When User provide input as "CURRENT_TIME" and capacity 7 for "success" scenario with key "77997969"
    Then Verify the HTTP status code is 201 for "success" scenario
    And Verify booking details with capacity 7
	
   Scenario: Booking meeting room
    Given User booking meeting room
    When User provide input as "CURRENT_TIME" and capacity 7 for "success" scenario with key "77997970"
    Then Verify the HTTP status code is 201 for "success" scenario
    And Verify booking details with capacity 7
	
   Scenario: Booking meeting room
    Given User booking meeting room
    When User provide input as "CURRENT_TIME" and capacity 7 for "failure" scenario with key "77997971"
    Then Verify the HTTP status code is 409 for "failure" scenario
    And Verify the status is "409 CONFLICT"
    And Error message is "No meeting rooms are available at this time, please try again later."
    
   Scenario: Searching for meeting room
    Given User searching for meeting room
    When User provide input as "CURRENT_TIME" for "failure" scenario
    Then Verify the HTTP status code is 409 for "failure" scenario
    And Verify the status is "409 CONFLICT"
    And Error message is "No meeting rooms are available at this time, please try again later."
