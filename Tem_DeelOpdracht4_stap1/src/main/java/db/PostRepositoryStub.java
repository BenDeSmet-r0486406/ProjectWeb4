package db;

import domain.Comment;
import domain.Post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PostRepositoryStub implements PostRepository {

	private static PostRepositoryStub instance;
	private static final Object lock = new Object();

	private List<Post> posts;

	public PostRepositoryStub(){
		posts = new ArrayList<>();
		Post p = new Post("projectweek","Was het een interessante projectweek?");
		p.addParagraph("Geef hier alle plus punten of minpunten van de project week");
		addPost(p);
		Post p2 = new Post("plannen","Wat ben je van plan vandaag te doen?");
		p2.addParagraph("Laat hier weten welke reizen je gaat maken in de vakantie");
		p2.addParagraph("of welke vakantie job je gaat doen");
		addPost(p2);
		Post p3 = new Post("muziek","Naar welke muziek luister je?");
		p3.addParagraph("in welke genres ben je geïnteresseerd");
		p3.addParagraph("wie zijn je favoriete artiesten?");
		addPost(p3);
		Post p4 = new Post("examenvragen","wat zijn de examenvragen voor Web 4?");
		p4.addParagraph("Help uw mede studenten uit om door dit extreem moeilijk vak te graken");
		p4.addParagraph("en post hier de examen vragen die je voor web 4 vorig jaar of vorig semester hebt gehad.");
		addPost(p4);
		Post p5 = new Post("geen","Er waren maar vier onderwerpen voor vijf toppics gegeven");
		p5.addParagraph("tja...");
		p5.addParagraph("dit is wat er gebeurd als je 4 topics geeft voor 5 blogposts");
		addPost(p5);

	}

	public static synchronized PostRepositoryStub getInstance(){
		if (instance == null) {
			return new PostRepositoryStub();
		}
		return instance;
	}

	@Override
	public String toString() {
		return "CommentRepositoryStub{" +
				"posts=" + posts +
				'}';
	}

	@Override
	public List<Post> getPosts() {
		return posts;
	}

	@Override
	public void addPost(Post post) {
		posts.add(post);
	}

	@Override
	public Post getPostById(String postid) {
		return posts.stream().filter(p -> p.getId().equals(postid)).findFirst().get();
	}
}
