package org.wcs_cda.worms.board;

public interface IMovableVisitor {
	void visit(AbstractMovable ab);
	void visit(ARBEWithGravity arbewg);
}
