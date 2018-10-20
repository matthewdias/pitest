package org.pitest.mutationtest.engine.gregor.mutators.extended.AOD;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;

public class RemoveRightMathOpMutator implements MethodMutatorFactory {
  @Override
  public MethodVisitor create(final MutationContext context,
  final MethodInfo methodInfo, final MethodVisitor methodVisitor)  {
    return new RemoveRightMathOpVisitor(this, context, methodInfo, methodVisitor);
  }

  @Override
  public String getGloballyUniqueId()  {
    return this.getClass().getName();
  }

  @Override
  public String getName() {
    return "REMOVE_RIGHT_MATH_OP_MUTATOR";
  }
}

class RemoveRightMathOpVisitor extends MethodVisitor  {
  private final MethodMutatorFactory factory;
  private final MutationContext context;

  RemoveRightMathOpVisitor(final MethodMutatorFactory factory,
  final MutationContext context, final MethodInfo methodInfo, final MethodVisitor delegateMethodVisitor)  {
    super(Opcodes.ASM5, delegateMethodVisitor);
    this.factory = factory;
    this.context = context;
  }

  private void visitIntOrFloat(int oldOpcode) {
    if (this.context.shouldMutate(this.context.registerMutation(this.factory, "Replaced math expression with right operand"))) {
      mv.visitInsn(Opcodes.SWAP);
      mv.visitInsn(Opcodes.POP);
    } else {
      mv.visitInsn(oldOpcode);
    }
  }

  private void visitLongOrDouble(int oldOpcode) {
    if (this.context.shouldMutate(this.context.registerMutation(this.factory, "Replaced math expression with right operand"))) {
      mv.visitInsn(Opcodes.DUP2_X2);
      mv.visitInsn(Opcodes.POP2);
      mv.visitInsn(Opcodes.POP2);
    } else {
      mv.visitInsn(oldOpcode);
    }
  }

  @Override
  public void visitInsn(int oldOpcode) {
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
        visitIntOrFloat(oldOpcode);
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
        visitLongOrDouble(oldOpcode);
    }
  }
}
