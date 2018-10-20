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

public enum ReplaceRemMutator implements MethodMutatorFactory {
  REPLACE_REM_MUTATOR;

  @Override
  public MethodVisitor create(final MutationContext context,
  final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
    return new ReplaceRemVisitor(this, methodInfo, context, methodVisitor);
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

class ReplaceRemVisitor extends AbstractInsnMutator {

  ReplaceRemVisitor(final MethodMutatorFactory factory,
      final MethodInfo methodInfo, final MutationContext context,
      final MethodVisitor writer) {
    super(factory, methodInfo, context, writer);
  }

  private static final Map<Integer, ZeroOperandMutation> MUTATIONS = new HashMap<Integer, ZeroOperandMutation>();

  static {
    MUTATIONS.put(Opcodes.IADD, new InsnSubstitution(Opcodes.IREM, "Replaced int add with mod"));
    MUTATIONS.put(Opcodes.ISUB, new InsnSubstitution(Opcodes.IREM, "Replaced int sub with mod"));
    MUTATIONS.put(Opcodes.IMUL, new InsnSubstitution(Opcodes.IREM, "Replaced int mul with mod"));
    MUTATIONS.put(Opcodes.IDIV, new InsnSubstitution(Opcodes.IREM, "Replaced int div with mod"));
    MUTATIONS.put(Opcodes.LADD, new InsnSubstitution(Opcodes.LREM, "Replaced long add with mod"));
    MUTATIONS.put(Opcodes.LSUB, new InsnSubstitution(Opcodes.LREM, "Replaced long sub with mod"));
    MUTATIONS.put(Opcodes.LMUL, new InsnSubstitution(Opcodes.LREM, "Replaced long mul with mod"));
    MUTATIONS.put(Opcodes.LDIV, new InsnSubstitution(Opcodes.LREM, "Replaced long div with mod"));
    MUTATIONS.put(Opcodes.FADD, new InsnSubstitution(Opcodes.FREM, "Replaced float add with mod"));
    MUTATIONS.put(Opcodes.FSUB, new InsnSubstitution(Opcodes.FREM, "Replaced float sub with mod"));
    MUTATIONS.put(Opcodes.FMUL, new InsnSubstitution(Opcodes.FREM, "Replaced float mul with mod"));
    MUTATIONS.put(Opcodes.FDIV, new InsnSubstitution(Opcodes.FREM, "Replaced float div with mod"));
    MUTATIONS.put(Opcodes.DADD, new InsnSubstitution(Opcodes.DREM, "Replaced double add with mod"));
    MUTATIONS.put(Opcodes.DSUB, new InsnSubstitution(Opcodes.DREM, "Replaced double sub with mod"));
    MUTATIONS.put(Opcodes.DMUL, new InsnSubstitution(Opcodes.DREM, "Replaced double mul with mod"));
    MUTATIONS.put(Opcodes.DDIV, new InsnSubstitution(Opcodes.DREM, "Replaced double div with mod"));
  }

  @Override
  protected Map<Integer, ZeroOperandMutation> getMutations() {
    return MUTATIONS;
  }
}
