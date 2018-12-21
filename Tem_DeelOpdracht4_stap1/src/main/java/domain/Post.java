package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Post {

	private String id;
	private String title;

	private List<String> paragraphs;
	private List<Comment> comments;

	public Post(String id, String title){
		this.setId(id);
		this.setTitle(title);
		paragraphs = new ArrayList<>();
		comments = new ArrayList<>();
	}

	public String getId() {
		return id;
	}

	private void setId(String id) {
		this.id = id;
	}

	public List<String> getParagraphs() {
		return paragraphs;
	}

	public void setParagraphs(List<String> paragraphs) {
		this.paragraphs = paragraphs;
	}

	public void addParagraph(String paragraph){
		getParagraphs().add(paragraph);
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public void addComment(Comment comment){
		getComments().add(comment);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Post post = (Post) o;
		return Objects.equals(id, post.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "Post{" +
				"id='" + id + '\'' +
				", title='" + title + '\'' +
				'}';
	}

}
