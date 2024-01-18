package com.automatedtest.poc.runner.standalone;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StandAloneReporter {

    /**
     * Creates cucumber html report in ${baseDir}/target/cucumber-html-report/
     */
    public static void createReport() {
        firstHtmlPlugin();
        secondHtmlPlugin();
        File sourceDir = new File("target");
        File destDir = new File("target_" + System.currentTimeMillis());
        try {
            FileUtils.copyDirectory(sourceDir, destDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void firstHtmlPlugin() {
        File reportOutputDirectory = new File("target");
        List<String> jsonFiles = new ArrayList<>();
        jsonFiles.add("target/cucumber.json");

        String projectName = "poc_tests";
        boolean runWithJenkins = false;

        Configuration configuration = new Configuration(reportOutputDirectory, projectName);
        configuration.setRunWithJenkins(runWithJenkins);

        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();
    }

    //temporary disabled because of API changes
    public static void secondHtmlPlugin() {

//        CluecumberLogger logger = new CluecumberLogger();
//        PropertyManager propertyManager = new PropertyManager(logger);
//
//        FileSystemManager fileSystemManager = new FileSystemManager();
//        ReportPostProcessor reportPostProcessor = new ReportPostProcessor();
//        FileIO fileIO = new FileIO();
//        ElementPostProcessor elementPostProcessor = new ElementPostProcessor(propertyManager, fileIO, logger);
//        JsonPojoConverter jsonPojoConverter = new JsonPojoConverter(reportPostProcessor, elementPostProcessor);
//
//        TemplateConfiguration templateConfiguration = new TemplateConfiguration();
//        ChartJsonConverter chartJsonConverter = new ChartJsonConverter();
//        AllFeaturesPageRenderer allFeaturesPageRenderer = new AllFeaturesPageRenderer(chartJsonConverter);
//        AllTagsPageRenderer allTagsPageRenderer = new AllTagsPageRenderer(chartJsonConverter);
//        ScenarioDetailsPageRenderer scenarioDetailsPageRenderer = new ScenarioDetailsPageRenderer(chartJsonConverter);
//        AllScenariosPageRenderer allScenariosPageRenderer = new AllScenariosPageRenderer(chartJsonConverter, propertyManager);
//        TemplateEngine templateEngine = new TemplateEngine(templateConfiguration, allFeaturesPageRenderer, allTagsPageRenderer, scenarioDetailsPageRenderer, allScenariosPageRenderer);
//        ReportGenerator reportGenerator = new ReportGenerator(templateEngine, fileIO, propertyManager, fileSystemManager);
//
//        CluecumberReportPlugin cluecumberReportPlugin = new CluecumberReportPlugin(logger, propertyManager, fileSystemManager, fileIO, jsonPojoConverter, reportGenerator);
//        try {
//            Field sourceJsonReportDirectory = cluecumberReportPlugin.getClass().getDeclaredField("sourceJsonReportDirectory");
//            Field generatedHtmlReportDirectory = cluecumberReportPlugin.getClass().getDeclaredField("generatedHtmlReportDirectory");
//
//            sourceJsonReportDirectory.setAccessible(true);
//            generatedHtmlReportDirectory.setAccessible(true);
//
//            sourceJsonReportDirectory.set(cluecumberReportPlugin, "./target/cucumber.json");
//            //TODO: create new folder each time
//            generatedHtmlReportDirectory.set(cluecumberReportPlugin, "./target/cucumber-html-report-2");
//
//            cluecumberReportPlugin.execute();
//        } catch (CluecumberPluginException | NoSuchFieldException | IllegalAccessException e) {
//            BFLogger.logError(stackTraceToString(e));
//        }
    }
}
