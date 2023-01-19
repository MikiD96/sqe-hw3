/* @provengo summon selenium */
// @provengo summon ctrl
/**
 * This event is used to navigate to login from homepage
 */
defineEvent(SeleniumSession,"navigateToLogin",function(session,e){
  // session.click("//*[contains(text(),'Log in')]");
})
/**
 * This event does the actual login by entering <username> and <password>.
 * finally it checks for the "Welcome back,<username>" message after login
 */
defineEvent(SeleniumSession, "login", function(session,e){
  with(session){
    // bp.log.info(e);
    writeText("//*[@id='username']",e.username);
    writeText("//*[@name='password' and @type='password']",e.password);
    click("//*[@id='loginbtn']");

  if(e.expectedWelcome)
    waitForVisibility("//*[contains(text(),'Welcome back,')]");
    assertText("//*[contains(text(),'Welcome back,')]", e.expectedWelcome)
    // assertEqual(e.expectedWelcome,getText("contains(text(),'Welcome back,')]"))
  }
})

/**
 * This event navigates to myCourses
 */
defineEvent(SeleniumSession,"myCourses",function (session,e){
  session.click("//*[contains(text(),'My courses') and @role='menuitem']");
})
/**
 * This event navigates to a specific course
 */
defineEvent(SeleniumSession,"goToCourse",function (session,e){
  session.click("//*[@class='multiline' and contains(text(),'" + e.courseName+"')]");
})
defineEvent(SeleniumSession,"assertForumDoesntExist",function (session,e){
  // waitForVisibility("//*[@id='yui_3_17_2_1_1673993555570_70']");
  // assertEqual(e.string,getText("//*[@id='yui_3_17_2_1_1673993555570_70']"))
})
/**
 * This event navigates to the specific forum
 */
defineEvent(SeleniumSession,"goToForum",function (session,e) {
  with(session){
    waitForClickability("//*[contains(text(),'MyForum')]",5000);
    click("//*[contains(text(),'MyForum')]");
  }
})

/**
 * This event leaves a comment in the Forum specified in previous events
 */
defineEvent(SeleniumSession,"commentOnForum",function(session,e){
  with(session){
    click("//*[contains(text(),'Add discussion topic')]");
    waitForVisibility("//*[@name='subject']");
    writeText("//*[@name='subject']",e.comment);
    writeText("//*[@id='id_messageeditable']",e.commentDesc);
    click("//*[@id='id_submitbutton']");
 }
})

/**
 * This event removes a forum
 */
defineEvent(SeleniumSession,"removeForum",function (session,e){
  with(session){
    click("//*[@type='checkbox' and @name='setmode']");
    waitForVisibility("//*[@id='action-menu-toggle-2']");
    click("//*[@id='action-menu-toggle-2']");
    waitForVisibility("//*[contains(text(),'Delete')]");
    click("//*[contains(text(),'Delete')]");
    waitForVisibility("//*[contains(text(),'Yes') and @class='btn btn-primary']");
    click("//*[contains(text(),'Yes') and @class='btn btn-primary']");
  }
})

