# Assignment 3: Software Quality Engineering
This is a repository for assignment 3 of the Software Quality Engineering course at the [Ben-Gurion University](https://in.bgu.ac.il/), Israel.

## Assignment Description
In this assignment, we tested an open-source software called [moodle](http://localhost/moodle/my/ please notice that its the url of -MY- project), (this is the address of the open source in git - https://moodle.org).

the purpose of the software is to help educators like teachers for example and students by giving them platform to create onlice course and manage their delivery to students.
teachers can use moodle to create and organize their courses's content plus other activities like create quizes assignments and also monitoring their student progress.

## Installation
1. please follow this guide to install moodle and create admin account etc. https://www.techruzz.com/how-to/how-to-install-moodle-on-windows-11-10-pc-using-xampp *assuming you are using windows
2. make sure to download the other necessary programs provengo, selenium and make sure your work enviorment uses cucumber
3. download web driver (we used edge web driver you can of course use other web drivers depending on your personal taste) 

## What we tested
we tested the forum module that allows adding,editing and deleting commments. We chose to test the following user stories:

User story (1) - As a student i would like to comment on a forum in a course i participate in 

*Preconditions:* The student enrolled to the course and the teacher made it open to everyone so the student could indeed comment on the forum. ofcourse the forum should exist in the first place

*Expected outcome:* new comment from the above student should appear in the forum

User story (2) - As a teacher i should delete a forum from a course im incharge of (the same forum our student commented on above)

*Preconditions:* - the teacher incharge of the course and capable of deleting the forum from his course

*Expected outcome:* the teacher deleted the forum(the forum no longer exists in the course page).



## How we tested
We used two different testing methods:
1. [Cucumber](https://cucumber.io/), a BDD testing framework.
2. [Provengo](https://provengo.tech/), a story-based testing framework.

Each of the testing methods is elaborated in its own directory. 


