/*
  This is the Geb configuration file.

  See: http://www.gebish.org/manual/current/#configuration
*/

import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver
import listeners.SessionIdHolder

// Allows for setting you baseurl in an environment variable.
// This is particularly handy for development and the pipeline
Map env = System.getenv()
baseUrl = env['BASE_URL']
if (!baseUrl) {
	baseUrl = "https://gebish.org/"
}

//can be set in CI environment scripts
USERNAME = env['BROWSERSTACK_USERNAME']
AUTOMATE_KEY = env['BROWSERSTACK_TOKEN']

if (!USERNAME || !AUTOMATE_KEY)
	throw RuntimeError('BROWSERSTACK_USERNAME and BROWSERSTACK_TOKEN are required');

waiting {
	timeout = 20
	retryInterval = 0.5
}

atCheckWaiting = [20, 0.5]

String buildId = SessionIdHolder.instance.buildId

//@changeme
String browserstackProjectName = 'Book of Geb'

environments {

	remoteFirefox {
		driver = {
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability("browser", "Firefox")
			caps.setCapability("browser_version", "67.0")
			caps.setCapability("os", "Windows")
			caps.setCapability("os_version", "10")
			caps.setCapability("resolution", "1920x1200")
			caps.setCapability("name", "Automated Test")
			caps.setCapability("project", "${browserstackProjectName}")
			caps.setCapability("build", "${buildId}:Firefox")

			String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub"

			driver = new RemoteWebDriver(new URL(URL), caps)

			return driver
		}
	}

	remoteEdge {
		driver = {
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability("browser", "Edge")
			caps.setCapability("os", "Windows")
			caps.setCapability("os_version", "10")
			caps.setCapability("resolution", "1920x1200")
			caps.setCapability("name", "Automated Test")
			caps.setCapability("project", "${browserstackProjectName}")
			caps.setCapability("build", "${buildId}:Edge")

			String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub"

			driver = new RemoteWebDriver(new URL(URL), caps)

			return driver
		}
	}


	remoteChrome {
		driver = {
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability("browser", "Chrome")
			caps.setCapability("os", "Windows")
			caps.setCapability("os_version", "10")
			caps.setCapability("resolution", "1920x1200")
			caps.setCapability("name", "Automated Test")
			caps.setCapability("project", "${browserstackProjectName}")
			caps.setCapability("build", "${buildId}:Chrome")

			String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub"

			driver = new RemoteWebDriver(new URL(URL), caps)

			return driver
		}
	}

	remoteAndroid {
		driver = {
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability("os_version", "10.0");
			caps.setCapability("device", "Google Pixel 3");
			caps.setCapability("real_mobile", "true");
			caps.setCapability("project", "Demo");
			caps.setCapability("build", "Build1");
			caps.setCapability("name", "GebDemo");
			caps.setCapability("browserstack.local", "false");
			caps.setCapability("browserstack.networkLogs", "true");

			String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub"

			driver = new RemoteWebDriver(new URL(URL), caps)

			return driver
		}
	}

}

// To run the tests with all browsers just run “./gradlew test”
baseNavigatorWaiting = true

autoClearCookies = false
autoClearWebStorage = false
cacheDriver = false
cacheDriverPerThread = false
quitCachedDriverOnShutdown = true
reportOnTestFailureOnly = false
reportsDir = 'build/reports/spock'
