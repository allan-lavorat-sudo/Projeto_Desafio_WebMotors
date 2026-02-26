package com.webmotors.tests;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = { "pretty" },
        features = "./features",
        glue = { "com.webmotors.tests" }
)
public class RunCucumberTest {
}
