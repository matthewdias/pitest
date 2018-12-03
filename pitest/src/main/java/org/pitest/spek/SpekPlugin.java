package org.pitest.spek;

import java.util.Collection;

import org.pitest.classinfo.ClassByteArraySource;
import org.pitest.testapi.Configuration;
import org.pitest.testapi.TestGroupConfig;
import org.pitest.testapi.TestPluginFactory;

public class SpekPlugin implements TestPluginFactory {
  @Override
  public String description() {
    return "Spek plugin";
  }

  @Override
  public Configuration createTestFrameworkConfiguration(TestGroupConfig config,
                                                        ClassByteArraySource source,
                                                        Collection<String> excludedRunner,
                                                        Collection<String> includedTestMethods) {
    return new SpekConfiguration(config, includedTestMethods);
  }

  @Override
  public String name() {
    return "spek";
  }
}
