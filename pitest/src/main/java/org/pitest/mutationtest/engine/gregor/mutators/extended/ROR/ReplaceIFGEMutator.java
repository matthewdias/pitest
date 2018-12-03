package org.pitest.mutationtest.engine.gregor.mutators.extended.ROR;

import java.util.HashMap;
import java.util.Map;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.gregor.AbstractJumpMutator;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;

public enum ReplaceIFGEMutator implements MethodMutatorFactory {
  REPLACE_IFGE_MUTATOR;

  @Override
  public MethodVisitor create(final MutationContext context,
      final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
    return new ReplaceIFGEVisitor(this, context, methodVisitor);
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

class ReplaceIFGEVisitor extends AbstractJumpMutator {

  ReplaceIFGEVisitor(final MethodMutatorFactory factory,
                         final MutationContext context, final MethodVisitor delegateMethodVisitor) {
    super(factory, context, delegateMethodVisitor);
  }

  private static final Map<Integer, Substitution> MUTATIONS = new HashMap<Integer, Substitution>();

  static {
    MUTATIONS.put(Opcodes.IFLT, new Substitution(Opcodes.IFGE, "Replaced less than 0 with greater than or equal"));
    MUTATIONS.put(Opcodes.IFGT, new Substitution(Opcodes.IFGE, "Replaced greater than 0 with greater than or equal"));
    MUTATIONS.put(Opcodes.IFLE, new Substitution(Opcodes.IFGE, "Replaced less than or equal 0 with greater than or equal"));
    MUTATIONS.put(Opcodes.IFEQ, new Substitution(Opcodes.IFGE, "Replaced equal 0 with greater than or equal"));
    MUTATIONS.put(Opcodes.IFNE, new Substitution(Opcodes.IFGE, "Replaced not equal 0 with greater than or equal"));
  }

  @Override
  protected Map<Integer, Substitution> getMutations() {
    return MUTATIONS;
  }
}