package se.redmind.rmtest.cucumber.parameterized;

import cucumber.api.CucumberOptions;
import cucumber.runtime.ParameterizableRuntime.CompositionType;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import se.redmind.rmtest.runners.ParameterizedCucumberRunnerFactory;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Jeremy Comte
 */
@RunWith(Parameterized.class)
@Parameterized.UseParametersRunnerFactory(ParameterizedCucumberRunnerFactory.class)
@CucumberOptions(plugin = {"pretty", "json:target/ParameterizedStepsTest-json-report.json", "html:target/ParameterizedStepsTest-hmtl-report"}, tags = {"@tag1,@tag2,@tag3,@ignore"})
public class ParameterizedStepsTest {

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> getParameters() {
        return Stream.of(CompositionType.replace)
            .peek(value -> System.setProperty("cucumber.compositionType", value.name()))
            .map(value -> new Object[]{"compositionType: " + value}).collect(Collectors.toList());
    }
}
