package hellocucumber;

import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;

import java.nio.file.Path;
import java.nio.file.Paths;


public class StepDefinitions {
    private MoodleActuator studentMoodle;
    private MoodleActuator teacherMoodle;
    private String STUDENT_USERNAME="miki_student1";
    private String STUDENT_PASS="123456Aa!";
    private String TEACHER_USERNAME="nadia";
    private String TEACHER_PASS="123456Aa!";
    private String COURSE_NAME = "Introduction to Hindi in c++";
    private boolean found = false;
    Path currentRelativePath = Paths.get("");
    String s = currentRelativePath.toAbsolutePath().toString();
    private String webDriver = "webdriver.edge.driver";
    private String path = s +"\\edgedriver_win64\\msedgedriver.exe";
    public void studentMoodleInit() {
        System.out.println("--------------- INITIALIZING Student Moodle - OPENING WEBPAGE ---------------");
        if(studentMoodle != null){
            studentMoodle.terminateDriver();
        }
        studentMoodle = new MoodleActuator();
        studentMoodle.initSession(webDriver,path);
    }
    public void teacherMoodleInit() {
        System.out.println("--------------- INITIALIZING Teacher Moodle - OPENING WEBPAGE ---------------");
        if(teacherMoodle != null){
            teacherMoodle.terminateDriver();
        }
        teacherMoodle = new MoodleActuator();
        teacherMoodle.initSession(webDriver,path);
    }
    public void beforeAll(){
        teacherMoodleInit();
        teacherMoodle.loginSequence(TEACHER_USERNAME,TEACHER_PASS);
        teacherMoodle.myCoursesTab();
        teacherMoodle.goToCourse(COURSE_NAME);
        teacherMoodle.openForum();
    }
    @Given("a student is in homepage")
    public void aStudentIsInHomepage() {
        beforeAll();
        studentMoodleInit();
    }

    @When("student navigates to login page")
    public void studentNavigatesToLoginPage() {
        studentMoodle.goToLogin();
    }

    @And("student enters username and password")
    public void studentEntersUsernameAndPassword() {
        studentMoodle.enterLoginInfo(STUDENT_USERNAME,STUDENT_PASS);
    }

    @And("Welcome back message is displayed")
    public void welcomeBackMessageIsDisplayed() {
        studentMoodle.WelcomeMessage();
    }

    @And("Teacher logs in as-well")
    public void teacherLogsInAsWell() {
        teacherMoodleInit();
        teacherMoodle.loginSequence(TEACHER_USERNAME,TEACHER_PASS);
    }

    @And("student navigates to MyCourses")
    public void studentNavigatesToMyCourses() {
        studentMoodle.myCoursesTab();
    }

    @And("teacher navigates to courses")
    public void teacherNavigatesToCourses() {
        teacherMoodle.myCoursesTab();
    }

    @And("teacher deletes forum")
    public void teacherDeletesForum() {
        teacherMoodle.editModeOn();
        teacherMoodle.removeForum();
    }

    @And("student enters course")
    public void studentEntersCourse() {
        studentMoodle.goToCourse(COURSE_NAME);
    }

    @And("Student trys to adds a comment on a forum")
    public void studentTrysToAddsACommentOnAForum() {
        found = studentMoodle.CommentOnForum();
    }

    @Then("Forum doesnt exist")
    public void forumDoesntExist() {
        Assertions.assertFalse(found);
        //teacherMoodle.openForum();
    }

    @Given("a teacher is in homepage")
    public void aTeacherIsInHomepage() {
        beforeAll();
        teacherMoodleInit();
    }

    @When("teacher navigates to login page")
    public void teacherNavigatesToLoginPage() {
        teacherMoodle.goToLogin();
    }

    @And("teacher enters username and password")
    public void teacherEntersUsernameAndPassword() {
        teacherMoodle.enterLoginInfo(TEACHER_USERNAME,TEACHER_PASS);
    }

    @And("Student logs in as-well")
    public void studentLogsInAsWell() {
        studentMoodleInit();
        studentMoodle.loginSequence(STUDENT_USERNAME,STUDENT_PASS);
    }

    @And("teacher enters course")
    public void teacherEntersCourse() {
        teacherMoodle.goToCourse(COURSE_NAME);
    }

    @And("student adds a comment on a forum")
    public void studentAddsACommentOnAForum() {
        studentMoodle.CommentOnForum();
    }

    @And("comment exists")
    public void commentExists() {
        studentMoodle.goToForum();
        studentMoodle.checkIfCommentExists();
    }

//    @And("teacher navigates to Forum")
//    public void teacherNavigatesToForum() {
//        teacherMoodle.goToForum();
//    }

//    @And("teacher deletes the forum")
//    public void teacherDeletesTheForum() {
//        teacherMoodle.removeForum();
//    }

    @Then("forum no longer appears")
    public void forumNoLongerAppears() {
        found = teacherMoodle.goToForum();
        Assertions.assertFalse(found);
    }

    @And("Welcome back message is displayed for teacher")
    public void welcomeBackMessageIsDisplayedForTeacher() {
        teacherMoodle.WelcomeMessage();
    }
}
