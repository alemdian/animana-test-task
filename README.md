# animana-test-task
This project is a technical test task for *Animania project*

## Getting started

### Install the dependencies
Make sure you have installed JDK1.8+

### Clone and run tests with default settings

- `git clone https://github.com/alemdian/animana-test-task.git`
- `cd animana-test-task`
- `mvnw clean test`

### Configuration

#### Default settings
- `browser.name=firefox - default browser to run tests (variants: ie, edge, chrome, firefox)` 
- `browser.maximized=true - start browser maximized`

#### System properties
- `-Dselenide.browser` - override browser name to run tests
- `-Dselenide.remote` - run tests on remote selenium host/hub(<url-to-remote-selenium-hub> should be specified)
- `-Dselenide.headless` - run tests in headless mode (best way to run with CI/CD)
- `-Dselenide.baseUrl` - specify base url of a website/service to run tests against (suitable for different environments/deployments)
- `-Dselenide.timeout` - set timeout to wait until element found/action executed

#### Example how to run tests localy
- `mvnw clean -Dselenide.browser=chrome -Dselenide.headless=true -Dselenide.timeout=10000 test`


#### Run test on remote selenium hub

These tests can be run using [BrowserStack!](https://www.browserstack.com)
Configuration already contains an necessary data to connet to the BrowserStack and run tests


![BrowserStack dashboard](assets/browserstack-dashboard.png/?raw=true "BrowserStack dashboard")

I tried to register and use [SauceLab!](https://saucelabs.com), but could not get account validated

![BrowserStack dashboard](assets/saucelab-error.png/?raw=true "SauceLab error")

#### Example how to run tests remotely
- `mvnw clean -Dselenide.remote=https://alemdianov1:stRXFQxepHA2kBrMiDeF@hub-cloud.browserstack.com/wd/hub -Dselenide.timeout=10000 test`


### Reporting

During test run a simple html report is being generated.  
Path to the report -  `target/html/index.html`

