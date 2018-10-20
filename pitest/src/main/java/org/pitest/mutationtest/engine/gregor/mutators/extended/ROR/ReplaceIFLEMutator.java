package org.pitest.mutationtest.engine.gregor.mutators.extended.ROR;

import java.util.HashMap;
import java.util.Map;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.gregor.AbstractJumpMutator;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;

public enum ReplaceIFLEMutator implements MethodMutatorFactory {
  REPLACE_IFLE_MUTATOR;

  @Override
  public MethodVisitor create(final MutationContext context,
      final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
    return new ReplaceIFLEVisitor(this, context, methodVisitor);
  }

  @Override
  public String getGloballyUniqueId() {
    return this.getClass().getName();
  }

  @Override
  public String getName() {
    return name();
  }
}

class ReplaceIFLEVisitor extends AbstractJumpMutator {

  ReplaceIFLEVisitor(final MethodMutatorFactory factory,
                         final MutationContext context, final MethodVisitor delegateMethodVisitor) {
    super(factory, context, delegateMethodVisitor);
  }

  private static final Map<Integer, Substitution> MUTATIONS = new HashMap<Integer, Substitution>();

  static {
    MUTATIONS.put(Opcodes.IFLT, new Substitution(Opcodes.IFLE, "Replaced less than 0 with less than or equal"));
    MUTATIONS.put(Opcodes.IFGT, new Substitution(Opcodes.IFLE, "Replaced greater than 0 with less than or equal"));
    MUTATIONS.put(Opcodes.IFGE, new Substitution(Opcodes.IFLE, "Replaced greater than or equal 0 with less than or equal"));
    MUTATIONS.put(Opcodes.IFEQ, new Substitution(Opcodes.IFLE, "Replaced equal 0 with less than or equal"));
    MUTATIONS.put(Opcodes.IFNE, new Substitution(Opcodes.IFLE, "Replaced not equal 0 with less than or equal"));
  }

  @Override
  protected Map<Integer, Substitution> getMutations() {
    return MUTATIONS;
  }
}
