/* @provengo summon selenium */
// @provengo summon constraints


story('BlockingStories',function (){
  block(Any({eventName:"login"}), function () {
    waitFor(Any({eventName:"navigateToLogin"}))
  })
  block(Any({eventName:"goToCourse"}), function () {
    waitFor(Any({eventName:"myCourses"}))
  })
})

/**
 * This story opens a new browser, logs in as a student, navigates to the Forum inside of the course,
 * finally the student leaves a comment inside the forum
 */
story('Student Comments On Forum',function (){
  // let s =new
  with(new SeleniumSession().start('http://localhost/moodle')) {
    navigateToLogin();
    login({username: 'miki_student1',password:'123456Aa!',expectedWelcome:'Welcome back, miki! 👋'});
    myCourses();
    goToCourse({courseName:'Introduction to Hindi in c++'});
    goToForum();
    commentOnForum({comment:'MyComment',commentDesc:'MyComment Description'});
  }
})
/**
 * This story opens a new browser, logs in as a teacher, navigates to the course and deletes the Forum.
 */


story('Teacher Deletes Forum',function (){
  with(new SeleniumSession().start('http://localhost/moodle')){
    navigateToLogin();
    login({username:'nadia',password:'123456Aa!',expectedWelcome:'Welcome back, Nadia! 👋'});
    myCourses();
    goToCourse({courseName:'Introduction to Hindi in c++'});
    waitFor(Any({eventName: "commentOnForum"}))
    removeForum();
    assertForumDoesntExist({string:""});
  }
})
