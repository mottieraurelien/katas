package jbehave;

import de.codecentric.jbehave.junit.monitoring.JUnitReportingRunner;
import jbehave.steps.TreasuresMapSteps;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

@RunWith(JUnitReportingRunner.class)
public class JBehaveMain extends JUnitStories {

    public JBehaveMain() {
        super();
    }

    @Override
    public Configuration configuration() {
        return new MostUsefulConfiguration()
                // Where stories are placed :
                .useStoryLoader(new LoadFromClasspath(this.getClass()))
                .useStoryReporterBuilder(new StoryReporterBuilder().withDefaultFormats()
                        .withFormats(Format.CONSOLE, Format.TXT));
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(), new TreasuresMapSteps());
    }

    @Override
    protected List<String> storyPaths() {
        List<String> stories = new ArrayList<>();
        stories.add("stories/TreasuresMap.story");
        return stories;
    }

}
