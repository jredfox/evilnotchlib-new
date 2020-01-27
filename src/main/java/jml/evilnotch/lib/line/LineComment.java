package jml.evilnotch.lib.line;

import java.util.ArrayList;
import java.util.List;

import jml.evilnotch.lib.line.comment.ICommentAttatch;
import jml.evilnotch.lib.line.comment.ICommentStorage;

public abstract class LineComment implements ILine,ICommentStorage{
	
	/**
	 * list of comments attached to the list
	 */
	public List<ICommentAttatch> comments = new ArrayList<ICommentAttatch>();
	
	@Override
	public void addComment(ICommentAttatch c) {
		this.comments.add(c);
	}

	@Override
	public void removeComment(ICommentAttatch c) {
		this.comments.remove(c);
	}

	@Override
	public List<ICommentAttatch> getComments() {
		return this.comments;
	}

}
