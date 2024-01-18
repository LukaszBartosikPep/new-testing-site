package com.automatedtest.poc;

import static com.automatedtest.core.config.environments.ParametersManager.environment;

/**
 * This class contains enums the same as parameters in /config/environments.csv
 * Allows to get specific parameters for current environment specified by -Denv=YOUR_ENVIRONMENT_NAME
 **/
public enum EnvironmentEnums {

    User,Password, Backpack, Name, LastName, Postal,PerformanceGlitchUser, ProblemUserLogin, StandardUser, CheckoutStepOneUrl,CheckoutStepTwoUrl,HomePageUrl;

    public String getAddress() {
        String name = this.name();
        String serviceAddress = environment().getServiceAddress(name);
        if (serviceAddress == null || serviceAddress.isEmpty()) {
            throw new RuntimeException("Environment variable " + name + " was not found in environments.csv");
        }
        return serviceAddress;
    }

}
