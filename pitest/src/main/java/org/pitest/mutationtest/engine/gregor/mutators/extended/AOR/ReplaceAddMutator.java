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

public enum ReplaceAddMutator implements MethodMutatorFactory {
  REPLACE_ADD_MUTATOR;

  @Override
  public MethodVisitor create(final MutationContext context,
  final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
    return new ReplaceAddVisitor(this, methodInfo, context, methodVisitor);
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

class ReplaceAddVisitor extends AbstractInsnMutator {

  ReplaceAddVisitor(final MethodMutatorFactory factory,
      final MethodInfo methodInfo, final MutationContext context,
      final MethodVisitor writer) {
    super(factory, methodInfo, context, writer);
  }

  private static final Map<Integer, ZeroOperandMutation> MUTATIONS = new HashMap<Integer, ZeroOperandMutation>();

  static {
    MUTATIONS.put(Opcodes.ISUB, new InsnSubstitution(Opcodes.IADD, "Replaced int sub with add"));
    MUTATIONS.put(Opcodes.IMUL, new InsnSubstitution(Opcodes.IADD, "Replaced int mul with add"));
    MUTATIONS.put(Opcodes.IDIV, new InsnSubstitution(Opcodes.IADD, "Replaced int div with add"));
    MUTATIONS.put(Opcodes.IREM, new InsnSubstitution(Opcodes.IADD, "Replaced int mod with add"));
    MUTATIONS.put(Opcodes.LSUB, new InsnSubstitution(Opcodes.LADD, "Replaced long sub with add"));
    MUTATIONS.put(Opcodes.LMUL, new InsnSubstitution(Opcodes.LADD, "Replaced long mul with add"));
    MUTATIONS.put(Opcodes.LDIV, new InsnSubstitution(Opcodes.LADD, "Replaced long div with add"));
    MUTATIONS.put(Opcodes.LREM, new InsnSubstitution(Opcodes.LADD, "Replaced long mod with add"));
    MUTATIONS.put(Opcodes.FSUB, new InsnSubstitution(Opcodes.FADD, "Replaced float sub with add"));
    MUTATIONS.put(Opcodes.FMUL, new InsnSubstitution(Opcodes.FADD, "Replaced float mul with add"));
    MUTATIONS.put(Opcodes.FDIV, new InsnSubstitution(Opcodes.FADD, "Replaced float div with add"));
    MUTATIONS.put(Opcodes.FREM, new InsnSubstitution(Opcodes.FADD, "Replaced float mod with add"));
    MUTATIONS.put(Opcodes.DSUB, new InsnSubstitution(Opcodes.DADD, "Replaced double sub with add"));
    MUTATIONS.put(Opcodes.DMUL, new InsnSubstitution(Opcodes.DADD, "Replaced double mul with add"));
    MUTATIONS.put(Opcodes.DDIV, new InsnSubstitution(Opcodes.DADD, "Replaced double div with add"));
    MUTATIONS.put(Opcodes.DREM, new InsnSubstitution(Opcodes.DADD, "Replaced double mod with add"));
  }

  @Override
  protected Map<Integer, ZeroOperandMutation> getMutations() {
    return MUTATIONS;
  }
}
