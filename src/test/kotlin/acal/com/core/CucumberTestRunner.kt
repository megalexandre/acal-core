package acal.com.core

import org.junit.platform.suite.api.ExcludeTags
import org.junit.platform.suite.api.IncludeEngines
import org.junit.platform.suite.api.SelectClasspathResource
import org.junit.platform.suite.api.Suite

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ExcludeTags("ignore")
class CucumberTestRunner
