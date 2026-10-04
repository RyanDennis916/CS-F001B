import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.engine.discovery.DiscoverySelectors;

import java.io.PrintWriter;

import static org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder.request;

@RunWith(Suite.class)
@Suite.SuiteClasses({ CardTest.class, HandTest.class })
public class TestSuite {
    public static void main(String[] args) {
        // Create a Launcher to execute tests
        Launcher launcher = LauncherFactory.create();

        // Listener to generate test execution summary
        SummaryGeneratingListener listener = new SummaryGeneratingListener();

        // Create a request to select all test classes
        LauncherDiscoveryRequest request = request()
                .selectors(DiscoverySelectors.selectClass(CardTest.class))
                .selectors(DiscoverySelectors.selectClass(HandTest.class))
                .build();

        // Execute the tests
        launcher.execute(request, listener);

        // Print the test results summary to the console
        try (PrintWriter writer = new PrintWriter(System.out)) {
            listener.getSummary().printTo(writer);
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            System.err.println(e.getStackTrace());
        }
        // Print the test failures to the error console
        try (PrintWriter errWriter = new PrintWriter(System.err)) {
            listener.getSummary().printFailuresTo(errWriter);
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            System.err.println(e.getStackTrace());
        }
    }
}