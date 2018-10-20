package org.pitest.mutationtest.engine.gregor.mutators.extended.ROR;

import java.util.HashMap;
import java.util.Map;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.gregor.AbstractJumpMutator;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;

public enum ReplaceIFNEMutator implements MethodMutatorFactory {
  REPLACE_IFNE_MUTATOR;

  @Override
  public MethodVisitor create(final MutationContext context,
      final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
    return new ReplaceIFNEVisitor(this, context, methodVisitor);
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

class ReplaceIFNEVisitor extends AbstractJumpMutator {

  ReplaceIFNEVisitor(final MethodMutatorFactory factory,
                         final MutationContext context, final MethodVisitor delegateMethodVisitor) {
    super(factory, context, delegateMethodVisitor);
  }

  private static final Map<Integer, Substitution> MUTATIONS = new HashMap<Integer, Substitution>();

  static {
    MUTATIONS.put(Opcodes.IFLT, new Substitution(Opcodes.IFNE, "Replaced less than 0 with not equal"));
    MUTATIONS.put(Opcodes.IFGT, new Substitution(Opcodes.IFNE, "Replaced greater than 0 with not equal"));
    MUTATIONS.put(Opcodes.IFLE, new Substitution(Opcodes.IFNE, "Replaced less than or equal 0 with not equal"));
    MUTATIONS.put(Opcodes.IFGE, new Substitution(Opcodes.IFNE, "Replaced greater than or equal 0 with not equal"));
    MUTATIONS.put(Opcodes.IFEQ, new Substitution(Opcodes.IFNE, "Replaced equal 0 with not equal"));
  }

  @Override
  protected Map<Integer, Substitution> getMutations() {
    return MUTATIONS;
  }
}
