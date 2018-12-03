package org.pitest.spek;

import org.junit.internal.builders.AllDefaultPossibilitiesBuilder;
import org.junit.runner.Runner;
import org.junit.runners.model.RunnerBuilder;
import org.pitest.junit.adapter.CustomRunnerExecutor;
import org.pitest.testapi.AbstractTestUnit;
import org.pitest.testapi.Description;
import org.pitest.testapi.ResultCollector;
import org.pitest.testapi.TestGroupConfig;
import org.pitest.util.Log;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.pitest.util.Unchecked.translateCheckedException;

public class SpekTestUnit extends AbstractTestUnit {
  private static final Logger LOG = Log.getLogger();
  private final Class<?> clazz;
  private final TestGroupConfig config;
  private final Collection<String> includedTestMethods;

  public SpekTestUnit(final Class<?> clazz, final TestGroupConfig config, Collection<String> includedTestMethods) {
    super(new Description("_", clazz));
    this.clazz = clazz;
    this.config = config;
    this.includedTestMethods = includedTestMethods;
  }

  @Override
  public void execute(final ResultCollector rc) {
    RunnerBuilder builder = new AllDefaultPossibilitiesBuilder(true);
    Runner runner;
    try {
      runner = builder.runnerForClass(clazz);
    } catch (Throwable e) {
      LOG.log(Level.SEVERE, "Error while creating runner for " + clazz, e);
      throw translateCheckedException(e);
    }

    try {
      new CustomRunnerExecutor(
              this.getDescription(),
              runner,
              rc
      ).run();
    } catch (Exception e) {
      LOG.log(Level.SEVERE, "Error while running adapter JUnit fixture " + this.clazz, e);
      throw translateCheckedException(e);
    }
  }
}
