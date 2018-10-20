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

public enum ReplaceDivMutator implements MethodMutatorFactory {
  REPLACE_DIV_MUTATOR;

  @Override
  public MethodVisitor create(final MutationContext context,
  final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
    return new ReplaceDivVisitor(this, methodInfo, context, methodVisitor);
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

class ReplaceDivVisitor extends AbstractInsnMutator {

  ReplaceDivVisitor(final MethodMutatorFactory factory,
      final MethodInfo methodInfo, final MutationContext context,
      final MethodVisitor writer) {
    super(factory, methodInfo, context, writer);
  }

  private static final Map<Integer, ZeroOperandMutation> MUTATIONS = new HashMap<Integer, ZeroOperandMutation>();

  static {
    MUTATIONS.put(Opcodes.IADD, new InsnSubstitution(Opcodes.IDIV, "Replaced int add with div"));
    MUTATIONS.put(Opcodes.ISUB, new InsnSubstitution(Opcodes.IDIV, "Replaced int sub with div"));
    MUTATIONS.put(Opcodes.IMUL, new InsnSubstitution(Opcodes.IDIV, "Replaced int mul with div"));
    MUTATIONS.put(Opcodes.IREM, new InsnSubstitution(Opcodes.IDIV, "Replaced int mod with div"));
    MUTATIONS.put(Opcodes.LADD, new InsnSubstitution(Opcodes.LDIV, "Replaced long add with div"));
    MUTATIONS.put(Opcodes.LSUB, new InsnSubstitution(Opcodes.LDIV, "Replaced long sub with div"));
    MUTATIONS.put(Opcodes.LMUL, new InsnSubstitution(Opcodes.LDIV, "Replaced long mul with div"));
    MUTATIONS.put(Opcodes.LREM, new InsnSubstitution(Opcodes.LDIV, "Replaced long mod with div"));
    MUTATIONS.put(Opcodes.FADD, new InsnSubstitution(Opcodes.FDIV, "Replaced float add with div"));
    MUTATIONS.put(Opcodes.FSUB, new InsnSubstitution(Opcodes.FDIV, "Replaced float sub with div"));
    MUTATIONS.put(Opcodes.FMUL, new InsnSubstitution(Opcodes.FDIV, "Replaced float mul with div"));
    MUTATIONS.put(Opcodes.FREM, new InsnSubstitution(Opcodes.FDIV, "Replaced float mod with div"));
    MUTATIONS.put(Opcodes.DADD, new InsnSubstitution(Opcodes.DDIV, "Replaced double add with div"));
    MUTATIONS.put(Opcodes.DSUB, new InsnSubstitution(Opcodes.DDIV, "Replaced double sub with div"));
    MUTATIONS.put(Opcodes.DMUL, new InsnSubstitution(Opcodes.DDIV, "Replaced double mul with div"));
    MUTATIONS.put(Opcodes.DREM, new InsnSubstitution(Opcodes.DDIV, "Replaced double mod with div"));
  }

  @Override
  protected Map<Integer, ZeroOperandMutation> getMutations() {
    return MUTATIONS;
  }
}
