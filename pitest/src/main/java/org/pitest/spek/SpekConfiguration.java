package org.pitest.spek;

import java.util.Collection;
import java.util.Optional;

import org.pitest.extension.common.NoTestSuiteFinder;
import org.pitest.help.PitHelpError;
import org.pitest.testapi.Configuration;
import org.pitest.testapi.TestGroupConfig;
import org.pitest.testapi.TestSuiteFinder;
import org.pitest.testapi.TestUnitFinder;

public class SpekConfiguration implements Configuration {
  private final TestGroupConfig config;
  private final Collection<String> includedTestMethods;

  public SpekConfiguration(final TestGroupConfig config, final Collection<String> includedTestMethods) {
    this.config = config;
    this.includedTestMethods = includedTestMethods;
  }

  @Override
  public TestUnitFinder testUnitFinder() {
    return new SpekTestUnitFinder(this.config, this.includedTestMethods);
  }

  @Override
  public TestSuiteFinder testSuiteFinder() {
    return new NoTestSuiteFinder();
  }

  @Override
  public Optional<PitHelpError> verifyEnvironment() {
    return Optional.empty();
  }
}
