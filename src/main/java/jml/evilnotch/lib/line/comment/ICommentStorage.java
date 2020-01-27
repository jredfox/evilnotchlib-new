package jml.evilnotch.lib.line.comment;

import java.util.List;

public interface ICommentStorage {
	
	public void addComment(ICommentAttatch c);
	public void removeComment(ICommentAttatch c);
	public List<ICommentAttatch> getComments();

}
