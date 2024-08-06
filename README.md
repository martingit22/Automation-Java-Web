<img align="center" src="skilloLogo.png" alt="Skilo Academy Logo" />


<div align="center">

# Test automation framework
</div>

# Automating iSkillo website

## Table of Contents
- [Overview](#overview)
- [Application / software under test]()
- [Installation](#installation)
- [Usage](#usage)
- [Bug report](#bug-report)
- [Contact](#contact)

## Overview
Java Maven project for automated testing with Selenium WebDriver and TestNG. Utilizes Page Object Model design pattern and Page Factory. Tests include login, navigation, and form submissions on Chrome browser. Screenshots captured on test failures.

## Test activities performed with Selenium 4+ and TestNG Java unit framework:
**Registration Feature**
- Verify user can register in the system with valid data

- Verify user cannot register in the system with invalid data

**Login Feature**

- Verify already registered user can successfully login in the system

- Verify already registered user can successfully login and navigate to edit profile

- Verify already registered user can NOT successfully login with wrong password

- Verify already registered user can NOT successfully login with wrong username

- Verify already registered user can NOT successfully login with no credentials

**Post Feature**
- Verify user can create and comment on a post

- Verify user can delete a post

- Verify post modal details

**Comment Feature**
- Verify user can create a new post and comment on it
- Verify user can delete a comment on a post

**Logout Feature**
- Verify user can successfully logout from the system

**Additional Features**
- Verify user can view their profile after logging in
- Verify user can upload a profile picture
- Verify user can update profile information
- Verify the home page displays user posts correctly
- Verify the login page handles incorrect username and password inputs correctly
- Verify the registration page handles already registered email correctly
## Installation

- Clone the repository
git clone myGitHub repo links lives here
- Make sure you have JAVA version 21and up
- Make sure you have Maven version 3.0.7 and up

## Usage

INSTALLATION:

Please visit the Test Automation Framework with linK:

Make sure that you can clone the repository. Follow the 3 different ways to do so:
 
Tip number 1:
- Go to the GitHub website with the link above and click on download button

Tip number 2:
- Go to the GitHub website copy the gitRepo HTTPS link and use git Bash 
  - Git clone " "
  - cd code repo src folder
  - Other git commands needed

Tip number 3:
- Use iIntelliJ Idea Community Edition v 21.+ and from the git menu clone the project
  - Please follow the steps explained over here:

    
CHECK FOLDER PATHS:

This is a steps that needs to be done for Windows OS users:
Go to SRC TEST RESOURCES folder and verify if the following folders are presented:
- There is a folder with name "reports"
- There is a folder with name "screenshots"
- There is a folder with name "upload"

IF NOT
When you build the project in src/test/java/gui you can find the folders created by the automation script.

RUNNING AUTOMATION

STEP 1:

Go with the terminal/shell/msPrompt to the folder of the project that POM.XML lives (exists).

STEP 2:
Run command:

mvn clean test

STEP 3:

Wait a bit the automation to start and after the test execution a report will be generated


## Bug report
If you find any bugs that you want to report, please do so with the bug report and liveCicle explained 

## Contact

- [Skillo Student](mailto:martin.panayotov.contact@gmail.com)
- Project Link: [TAF Selenium 4 TestNG 7 ](https://github.com/)

