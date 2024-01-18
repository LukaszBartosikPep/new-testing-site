training project for new TAS 

**Compilation:** the framework is compiled using Java 1.8 and Maven 3.6.0 <br/>
_'mvn clean compile -DskipTests'_ compiles framework without running tests

**IDE test execution:** to run test from IDE change the @CucumberOptions name parameter in TestExecutor.java, set desired environment as JVM option -Denv=YOUR_ENV_NAME <br/>
Parameters for environment are described in _environments.csv_ <br/>

**Maven test execution:** tests are triggered via maven surfire plugin. <br/>
To run tests use -Ptests maven profile <br/>
To generate HTML reports use -Preports maven pfofile. All reports are generated in .target/cucumber-html-report*/* directory <br/>

**Tagging tests to execute:** while maven test execution the one can control which tests are to be executed using JVM parameter -Drun.tags <br/>

**Run tags:** <br/>
 _--tags @tag1,@tag2_ - runs all scenarios tagged with @tag1 or @tag2 <br/>
 _--tags @tag1, --tags @tag2_ - runs all scenarios tagged with @tag1 and @tag2 <br/>
 _--tags @tag1, --tags ~@tag2_ - runs all scenarios tagged with @tag1 but not tagged with @tag2 <br/>
 _--tags ~@tag2_ - runs all scenarios without @tag2 <br/>
 
 **Build:** <br/>
 mvn clean package -PcreateJar <br/>
 java -Drun.tags="@mock or @mock2" -Drun.names="second mock" -jar target/standalone/web-demo-testing-tests.jar <br/>

 mvn validate -Preports
