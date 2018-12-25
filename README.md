# TDAT: TEQ Data Aggregation Tool

![build badge](https://api.travis-ci.com/CSCC01/Team17.svg?token=zqaFXskyNjZGfENjo1zH&branch=master)

**TDAT**, or the TEQ Data Aggregation Tool, is a program that aggregates agency data and generates reports for the TEQ. All files under this repository is developed as a project for the CSCC01 course.

*Learn more about the project [here](../../tree/master/resources/Scarborough_Newcomers_Software_Engineering_Project.pdf "Project Presentation").*

## Video Demo
[View the Video Demo](https://www.youtube.com/watch?v=gIpR2qcagFU "View the Video")<br><br/>
[![Video Demo](https://img.youtube.com/vi/gIpR2qcagFU/0.jpg)](https://www.youtube.com/watch?v=gIpR2qcagFU)

## Instructions (shell)
1. In the shell, make sure you are in root of the repo
2. Start the web app with ```cd chartjs``` then ``` $ node index.js```
3. In separate shell, from root, execute jar file with ```$ mvn exec:java -D exec.mainClass=com.tdat.app.App```
4. To test out the program, we recommend using the test files in ```./src/test/resources```
5. Generated reports are located in ```./reports```

## Testing 
#### Unit/Component Tests
1. In the shell, make sure you are in root of the repo
2. Use the command  ```$ mvn test```

#### Acceptance/GUI Tests
1. Start the app (follow the steps in **Instructions (shell)**)
2. From the root of the repo, navigate to ```./deliverables/5/```
2. Open ```Acceptance Tests.pdf``` then follow the instructions

## Repository Contents
Follow the folder structure outlined below to easy navigation through the repository.
```bash
root
  └── chartjs
  └── deliverables
  |             └── 1
  |             └── 2
  |             └── 3
  |             └── 4
  |             └── 5
  └── reports
  └── src
  |     └── main 
  |            └── java
  |                   └── com
  |                         └── tdat
  |                                └── app
  |                                └── data
  |                                └── feeder
  |                                └── gui
  |                                     └── publicData
  |                                     └── reports
  |                                └── report
  |                                      └── chart
  |                                            └── templates
  |            └── resources 
  |     └── test
  |            └── java
  |                 └── com
  |                       └── tdat
  |                              └── data
  |                              └── feeder
  |                              └── query.handler
  |            └── resources 
  |
  └── README.md
  └── .gitignore
  └── .travis.yml
  └── pom.xml

```
## TDAT Query Languange
When creating graphs to put on a report, users can optionally use a text based system rather than the GUI to generate graphs to be added.
For fields such as titles, column names, and entry names,  use ```-``` to represent a space.
#### Plotting data onto a Distribution graph
```DISTRIBUTION OF [COLUMN] WITH [TITLE;XTITLE;YTITLE] AS [LINE/TABLE/BAR]```

This command will create a distribution graph for the column in ```[COLUMN]```, with the titles in ```[TITLE;XTITLE;YTITLE]```.
The graph will be in the style specified by ```[LINE/TABLE/BAR]```.

Ex. ```distribution of children with dist-of-childrem;num;value as bar```

#### Plotting data onto a Trend graph (unimplemented)
```TREND OF [ENTRY;ENTRY;ENTRY] FROM [COLUMN;COLUMN;COLUMN] WITH [title;x-axis;y-axis] AS [LINE/TABLE/BAR]```

This command will create a trend graph for the entries in ```[ENTRY;ENTRY;ENTRY]```, corresponding to their respective columns in ```[COLUMN;COLUMN;COLUMN]```, over all years, with the titles in ```[TITLE;XTITLE;YTITLE]```.
The graph will be in the style specified by ```[LINE/TABLE/BAR]```.


Ex. ```trend of 1;k2g5z4 from children;postal-code with children-vs-area;num;time as bar```
## Change Log
This change log is in order from the top for the latest changes in the master branch.

Date | Change
--- | ---
2018-11-25 | <ul><li>Added deliverable 5 content</li><li>Added template feature, and new graphs to address TEQ questions</li><li>Cleaned up code base</li></ul>
2018-11-12 | <ul><li>Added deliverable 4 content</li><li>Merged SQL, conflict check, public data, and graph creation branches</li></ul>
2018-10-29 | <ul><li>Pushed product and sprint backlogs for deliverable 3</li><li>Added the TableDataCreator class (and its tests) which customizes a TableData object with provided data as well as constraints about columns and years.</li></ul>
2018-10-28 | <ul><li>Created class to provide basic info on a given TableData object, along with its unit tests</li><li>Added jUnit to dependancy list</li></ul>
2018-10-23 | <ul><li>Completed feeder directory, all needs to be done is testing</li><li>Made significant changes to the folder structure to be more consistant and follow the Maven structure</li><li>Changed up the README.md file to be more friendly to new readers</li></ul>
2018-10-22 | <ul><li>Pushed the first version of the configuration file, pom.xml file into the master repository</li></ul>
2018-10-21 | <ul><li>Pushed first rendition of the GUI into the deliverables/3/src folder.</li><li>Created data holder classes with basic functions in the data package.</li></ul>
2018-10-14 | <ul><li>Pushed deliverable 2 into the deliverables/2/ folder.</li></ul>
2018-09-30 | <ul><li>Pushed deliverable 1 into the deliverables/1/ folder.</li></ul>

## Authors and Contributors
TDAT is developed by team **BRAW**, which is an acronym for all developers of the team. Here is a list of all developers in the team and any other contributors.

* **Bekzod Tursunov** - Team Player/Developer - [Github](https://github.com/Bekzod13 "Bekzod's Github Page")
* **Radu Laudat** - Team Player/Developer - [Github](https://github.com/radulaudat "Radu's Github Page")
* **Alvin Tang** - Team Player/Developer - [Github](https://github.com/alvintangz "Alvin's Github Page")
* **Wesley Ma** - Team Player/Developer - [Github](https://github.com/WesleyMa2 "Wesley's Github Page")
* **Thierry Sans** - Professor - [Github](https://github.com/ThierrySans "Thierry's Github Page")

*Learn about the BRAW team agreement [here](../../tree/master/deliverables/1/Deliverable-1.pdf "BRAW Team Agreement").*
