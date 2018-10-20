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

public enum ReplaceSubMutator implements MethodMutatorFactory {
  REPLACE_SUB_MUTATOR;

  @Override
  public MethodVisitor create(final MutationContext context,
  final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
    return new ReplaceSubVisitor(this, methodInfo, context, methodVisitor);
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

class ReplaceSubVisitor extends AbstractInsnMutator {

  ReplaceSubVisitor(final MethodMutatorFactory factory,
      final MethodInfo methodInfo, final MutationContext context,
      final MethodVisitor writer) {
    super(factory, methodInfo, context, writer);
  }

  private static final Map<Integer, ZeroOperandMutation> MUTATIONS = new HashMap<Integer, ZeroOperandMutation>();

  static {
    MUTATIONS.put(Opcodes.IADD, new InsnSubstitution(Opcodes.ISUB, "Replaced int add with sub"));
    MUTATIONS.put(Opcodes.IMUL, new InsnSubstitution(Opcodes.ISUB, "Replaced int mul with sub"));
    MUTATIONS.put(Opcodes.IDIV, new InsnSubstitution(Opcodes.ISUB, "Replaced int div with sub"));
    MUTATIONS.put(Opcodes.IREM, new InsnSubstitution(Opcodes.ISUB, "Replaced int mod with sub"));
    MUTATIONS.put(Opcodes.LADD, new InsnSubstitution(Opcodes.LSUB, "Replaced long add with sub"));
    MUTATIONS.put(Opcodes.LMUL, new InsnSubstitution(Opcodes.LSUB, "Replaced long mul with sub"));
    MUTATIONS.put(Opcodes.LDIV, new InsnSubstitution(Opcodes.LSUB, "Replaced long div with sub"));
    MUTATIONS.put(Opcodes.LREM, new InsnSubstitution(Opcodes.LSUB, "Replaced long mod with sub"));
    MUTATIONS.put(Opcodes.FADD, new InsnSubstitution(Opcodes.FSUB, "Replaced float add with sub"));
    MUTATIONS.put(Opcodes.FMUL, new InsnSubstitution(Opcodes.FSUB, "Replaced float mul with sub"));
    MUTATIONS.put(Opcodes.FDIV, new InsnSubstitution(Opcodes.FSUB, "Replaced float div with sub"));
    MUTATIONS.put(Opcodes.FREM, new InsnSubstitution(Opcodes.FSUB, "Replaced float mod with sub"));
    MUTATIONS.put(Opcodes.DADD, new InsnSubstitution(Opcodes.DSUB, "Replaced double add with sub"));
    MUTATIONS.put(Opcodes.DMUL, new InsnSubstitution(Opcodes.DSUB, "Replaced double mul with sub"));
    MUTATIONS.put(Opcodes.DDIV, new InsnSubstitution(Opcodes.DSUB, "Replaced double div with sub"));
    MUTATIONS.put(Opcodes.DREM, new InsnSubstitution(Opcodes.DSUB, "Replaced double mod with sub"));
  }

  @Override
  protected Map<Integer, ZeroOperandMutation> getMutations() {
    return MUTATIONS;
  }
}
