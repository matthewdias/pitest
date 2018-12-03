package org.pitest.spek;

import org.pitest.testapi.TestGroupConfig;
import org.pitest.testapi.TestUnit;
import org.pitest.testapi.TestUnitFinder;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SpekTestUnitFinder implements TestUnitFinder {
  private final TestGroupConfig config;
  private final Collection<String> includedTestMethods;

  public SpekTestUnitFinder(final TestGroupConfig config, final Collection<String> includedTestMethods) {
    this.config = config;
    this.includedTestMethods = includedTestMethods;
  }

  @Override
  public List<TestUnit> findTestUnits(final Class<?> clazz) {
    if (clazz.getSuperclass().getCanonicalName().equals("org.spekframework.spek2.Spek")) {
      return Collections.singletonList(new SpekTestUnit(clazz, this.config, this.includedTestMethods));
    }
    return Collections.emptyList();
  }
}
