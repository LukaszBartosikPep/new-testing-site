def createStep(tag,environmentName) {
    stage(tag.toString()) {
        echo tag
        def r = bat(returnStatus: true,
                script: "java -Drun.tags=${tag} " +
                        "-Denv=${environmentName} " +
                        "-Dmaven.clean.skip=true " +
                        "-Dseleniumenv=${environmentName} " +
                        "-Dbrowser=firefox " +
                        "-Dfile.encoding=ISO8859-1 " +
                        "-Dos=windows " +
                        "-Dlang=pl " +
                        "-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=0 "+
                        "-jar target/standalone/web-demo-testing-tests.jar "


        )
        if (r != 0) {
            unstable(message: "Failed: ${STAGE_NAME} => return UNSTABLE status")
        }
    }
}

pipeline {
    agent any

    parameters {
        choice(name: 'env',
                choices: '163\n161\n164\n51\n221\n154\n162\n62\n236\n111\n113\n116\n108\n150\n235\n220\n123\n107\n29',
                description: 'Available environments:'
        )
        string(name: 'test',
                defaultValue: "@pl",
                description: 'Type type and the enviroments (E.g. "@pl-163;@LT-195" launches @pl on 163 environment, @LT on 165 parallelly):'
        )
        string(name: 'recipient',
                defaultValue: " ",
                description: 'Report recipient:'
        )
    }
    environment {

        M2_HOME = 'C:\\Users\\Administrator\\.m2'
        JAR_SH = "-Dmaven.clean.skip=true -Denv=${params.env} -Dseleniumenv=${params.env} -Dbrowser=firefox -Dfile.encoding=ISO8859-1 -Dos=windows -jar target/standalone/web-demo-testing-tests.jar"
    }
    stages {
        stage('COMPILE TEST FRAMEWORK') {
            steps {
                bat 'mvn clean package -PcreateJar'
            }
        }
        stage('CLEAN') {
            steps {
                script {
                    if (fileExists('Report.zip')) {
                        dir('Report.zip') {
                            deleteDir()
                        }
                    }
                }
            }
        }
        stage('TESTING') {
            steps {
                script {
                    def tests = params.test.split(';')
                    def branches = [:]
                    for (int i = 0; i < tests.length; i++) {
                        def stageName= tests[i].split('-')[0]
                        def environmentName = tests[i].split('-')[1]
                        branches[stageName] = {
                            script {
                                stage(stageName) {
                                    createStep(stageName,environmentName)
                                }
                            }
                        }
                    }
                    parallel branches
                }
            }
        }

        stage('REPORT CREATION') {
            steps {
                bat 'mvn validate -Preports'
            }
        }
        stage('REPORT GENERATION') {
            parallel {
                stage('Clucumber') {
                    steps {
                        publishHTML(allowMissing: true,
                                alwaysLinkToLastBuild: false,
                                keepAll: true,
                                reportDir: 'target/cucumber-html-report-2',
                                reportFiles: 'index.html',
                                reportName: 'Cluecumber reports')
                    }
                }
                stage('Masterthought') {
                    steps {
                        publishHTML(allowMissing: true,
                                alwaysLinkToLastBuild: false,
                                keepAll: true,
                                reportDir: 'target/cucumber-html-reports',
                                reportFiles: 'overview-features.html',
                                reportName: 'Masterthought reports')
                    }
                }
            }
        }
        stage('TEST CHECKING') {
            steps {
                junit '**/junitreports/*.xml'
            }
        }
        stage("REPORT COMPRESSING") {
            steps {
                script {
                    zip archive: true, dir: 'target/cucumber-html-report-2', zipFile: 'Report.zip'
                }
            }
        }
        stage('SENDING REPORT MAIL') {
            steps {
                emailext(
                        //  attachmentsPattern: 'Report.zip',
                        body: "${currentBuild.currentResult}: Job ${env.JOB_NAME} build ${env.BUILD_NUMBER}\nMore info at: ${env.RUN_DISPLAY_URL}",
                        subject: "Jenkins Build ${currentBuild.currentResult}: Job ${env.JOB_NAME}",
                        to: "${params.recipient}"
                )
            }
        }
    }
}
