package org.pitest.mutationtest.engine.gregor.mutators.extended.AOR;

import org.pitest.mutationtest.engine.gregor.AbstractInsnMutator;
import org.pitest.mutationtest.engine.gregor.InsnSubstitution;
import org.pitest.mutationtest.engine.gregor.ZeroOperandMutation;
import java.util.HashMap;
import java.util.Map;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;

public enum ReplaceMulMutator implements MethodMutatorFactory {
  REPLACE_MUL_MUTATOR;

  @Override
  public MethodVisitor create(final MutationContext context,
  final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
    return new ReplaceMulVisitor(this, methodInfo, context, methodVisitor);
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

class ReplaceMulVisitor extends AbstractInsnMutator {

  ReplaceMulVisitor(final MethodMutatorFactory factory,
      final MethodInfo methodInfo, final MutationContext context,
      final MethodVisitor writer) {
    super(factory, methodInfo, context, writer);
  }

  private static final Map<Integer, ZeroOperandMutation> MUTATIONS = new HashMap<Integer, ZeroOperandMutation>();

  static {
    MUTATIONS.put(Opcodes.IADD, new InsnSubstitution(Opcodes.IMUL, "Replaced int add with mul"));
    MUTATIONS.put(Opcodes.ISUB, new InsnSubstitution(Opcodes.IMUL, "Replaced int sub with mul"));
    MUTATIONS.put(Opcodes.IDIV, new InsnSubstitution(Opcodes.IMUL, "Replaced int div with mul"));
    MUTATIONS.put(Opcodes.IREM, new InsnSubstitution(Opcodes.IMUL, "Replaced int mod with mul"));
    MUTATIONS.put(Opcodes.LADD, new InsnSubstitution(Opcodes.LMUL, "Replaced long add with mul"));
    MUTATIONS.put(Opcodes.LSUB, new InsnSubstitution(Opcodes.LMUL, "Replaced long sub with mul"));
    MUTATIONS.put(Opcodes.LDIV, new InsnSubstitution(Opcodes.LMUL, "Replaced long div with mul"));
    MUTATIONS.put(Opcodes.LREM, new InsnSubstitution(Opcodes.LMUL, "Replaced long mod with mul"));
    MUTATIONS.put(Opcodes.FADD, new InsnSubstitution(Opcodes.FMUL, "Replaced float add with mul"));
    MUTATIONS.put(Opcodes.FSUB, new InsnSubstitution(Opcodes.FMUL, "Replaced float sub with mul"));
    MUTATIONS.put(Opcodes.FDIV, new InsnSubstitution(Opcodes.FMUL, "Replaced float div with mul"));
    MUTATIONS.put(Opcodes.FREM, new InsnSubstitution(Opcodes.FMUL, "Replaced float mod with mul"));
    MUTATIONS.put(Opcodes.DADD, new InsnSubstitution(Opcodes.DMUL, "Replaced double add with mul"));
    MUTATIONS.put(Opcodes.DSUB, new InsnSubstitution(Opcodes.DMUL, "Replaced double sub with mul"));
    MUTATIONS.put(Opcodes.DDIV, new InsnSubstitution(Opcodes.DMUL, "Replaced double div with mul"));
    MUTATIONS.put(Opcodes.DREM, new InsnSubstitution(Opcodes.DMUL, "Replaced double mod with mul"));
  }

  @Override
  protected Map<Integer, ZeroOperandMutation> getMutations() {
    return MUTATIONS;
  }
}
