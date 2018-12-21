package db;

import domain.Comment;
import domain.Post;

import java.util.List;
import java.util.Map;

public interface PostRepository {

	List<Post> getPosts();
	void addPost(Post post);
    Post getPostById(String postid);
}
