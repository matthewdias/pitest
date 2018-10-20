package org.pitest.mutationtest.engine.gregor.mutators.extended.ROR;

import java.util.HashMap;
import java.util.Map;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.gregor.AbstractJumpMutator;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;

public enum ReplaceIFLTMutator implements MethodMutatorFactory {
  REPLACE_IFLT_MUTATOR;

  @Override
  public MethodVisitor create(final MutationContext context,
      final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
    return new ReplaceIFLTVisitor(this, context, methodVisitor);
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

class ReplaceIFLTVisitor extends AbstractJumpMutator {

  ReplaceIFLTVisitor(final MethodMutatorFactory factory,
                         final MutationContext context, final MethodVisitor delegateMethodVisitor) {
    super(factory, context, delegateMethodVisitor);
  }

  private static final Map<Integer, Substitution> MUTATIONS = new HashMap<Integer, Substitution>();

  static {
    MUTATIONS.put(Opcodes.IFGT, new Substitution(Opcodes.IFLT, "Replaced greater than 0 with less than"));
    MUTATIONS.put(Opcodes.IFLE, new Substitution(Opcodes.IFLT, "Replaced less than or equal 0 with less than"));
    MUTATIONS.put(Opcodes.IFGE, new Substitution(Opcodes.IFLT, "Replaced greater than or equal 0 with less than"));
    MUTATIONS.put(Opcodes.IFEQ, new Substitution(Opcodes.IFLT, "Replaced equal 0 with less than"));
    MUTATIONS.put(Opcodes.IFNE, new Substitution(Opcodes.IFLT, "Replaced not equal 0 with less than"));
  }

  @Override
  protected Map<Integer, Substitution> getMutations() {
    return MUTATIONS;
  }
}
