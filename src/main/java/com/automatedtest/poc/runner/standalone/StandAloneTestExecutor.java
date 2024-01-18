//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.automatedtest.poc.runner.standalone;

import com.automatedtest.core.logger.BFLogger;
import com.automatedtest.poc.config.SeleniumDriver;
import cucumber.api.cli.Main;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StandAloneTestExecutor {
    public StandAloneTestExecutor() {
    }

    public static void main(String[] args) {
        long runId = System.currentTimeMillis() + (new Random()).nextLong();
        List<String> params = (List) Stream.of("--glue",
                "com.automatedtest.poc.stepdefs", "--plugin", "json:target/" + runId + ".json",
                "--plugin", "junit:test-output/junitreports/" + runId + ".xml",
                "src/test/java/features").collect(Collectors.toList());
        String runNames = System.getProperty("run.names");
        if (runNames != null && !runNames.isEmpty()) {
            String[] names = runNames.split(",");
            String[] var6 = names;
            int var7 = names.length;

            for(int var8 = 0; var8 < var7; ++var8) {
                String name = var6[var8];
                params.add("-n");
                params.add(name);
            }
        }

        String tags = System.getProperty("run.tags");
        if (tags != null && !tags.isEmpty()) {
            params.add("--tags");
            params.add(tags);
        }

        byte exitStatus = Main.run((String[])params.toArray(new String[0]), Thread.currentThread().getContextClassLoader());
        SeleniumDriver.closeDriver();
        BFLogger.logInfo("Returning status:" + exitStatus);
        System.exit(exitStatus);
    }
}
