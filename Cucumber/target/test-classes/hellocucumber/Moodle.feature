Feature: ForumOps

  Scenario: Test Student Trys To Comment on a Forum that doesn't exist
    Given a student is in homepage
    When student navigates to login page
    And student enters username and password
    And Welcome back message is displayed
    And Teacher logs in as-well
    And student navigates to MyCourses
    And teacher navigates to courses
    And teacher enters course
    And teacher deletes forum
    And student enters course
    And Student trys to adds a comment on a forum
    Then Forum doesnt exist

  Scenario: Test Teacher deletes a forum After Student Comments
    Given a teacher is in homepage
    When teacher navigates to login page
    And teacher enters username and password
    And Welcome back message is displayed for teacher
    And Student logs in as-well
    And student navigates to MyCourses
    And student enters course
    And teacher navigates to courses
    And teacher enters course
    And student adds a comment on a forum
    And comment exists
    And teacher deletes forum
    Then forum no longer appears


    #  Scenario: Test Student Comment on a Forum inside a course
#    Given a student is in homepage
#    When student navigates to login page
#    And student enters username and password
#    And Welcome back message is displayed
#    And student navigates to MyCourses
#    And student enters course
#    And student adds a comment on a forum
#    Then comments appears in forum
#
#    Scenario: Test Teacher deletes a forum
#      Given a teacher is in homepage
#      When teacher navigates to login page
#      And teacher enters username and password
#      And Welcome back message is displayed
#      And teacher navigates to courses
#      And teacher enters course
#      And teacher navigates to Forum
#      And teacher deletes the forum
#      Then forum no longer appears