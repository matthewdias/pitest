package org.pitest.mutationtest.engine.gregor.mutators.extended.AOD;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;

public class RemoveLeftMathOpMutator implements MethodMutatorFactory {
  @Override
  public MethodVisitor create(final MutationContext context,
  final MethodInfo methodInfo, final MethodVisitor methodVisitor)  {
    return new RemoveLeftMathOpVisitor(this, context, methodInfo, methodVisitor);
  }

  @Override
  public String getGloballyUniqueId()  {
    return this.getClass().getName();
  }

  @Override
  public String getName() {
    return "REMOVE_LEFT_MATH_OP_MUTATOR";
  }
}

class RemoveLeftMathOpVisitor extends MethodVisitor  {
  private final MethodMutatorFactory factory;
  private final MutationContext context;

  RemoveLeftMathOpVisitor(final MethodMutatorFactory factory,
  final MutationContext context, final MethodInfo methodInfo, final MethodVisitor delegateMethodVisitor)  {
    super(Opcodes.ASM5, delegateMethodVisitor);
    this.factory = factory;
    this.context = context;
  }

  @Override
  public void visitInsn(int oldOpcode) {
    int newOpcode = oldOpcode;
    switch (oldOpcode) {
      case Opcodes.IADD:
      case Opcodes.ISUB:
      case Opcodes.IMUL:
      case Opcodes.IDIV:
      case Opcodes.IREM:
      case Opcodes.FADD:
      case Opcodes.FSUB:
      case Opcodes.FMUL:
      case Opcodes.FDIV:
      case Opcodes.FREM:
        newOpcode = Opcodes.POP;
        break;
      case Opcodes.LADD:
      case Opcodes.LSUB:
      case Opcodes.LMUL:
      case Opcodes.LDIV:
      case Opcodes.LREM:
      case Opcodes.DADD:
      case Opcodes.DSUB:
      case Opcodes.DMUL:
      case Opcodes.DDIV:
      case Opcodes.DREM:
        newOpcode = Opcodes.POP2;
    }

    if (this.context.shouldMutate(this.context.registerMutation(this.factory, "Replaced math expression with left operand"))) {
      mv.visitInsn(newOpcode);
    } else {
      mv.visitInsn(oldOpcode);
    }
  }
}
