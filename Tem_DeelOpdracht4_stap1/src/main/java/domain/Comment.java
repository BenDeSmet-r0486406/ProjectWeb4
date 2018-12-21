package domain;

public class Comment {

	@Override
	public String toString() {
		return "Comment{" +
				"name='" + name + '\'' +
				", text='" + text + '\'' +
				", rating=" + rating +
				'}';
	}

	private String name;
	private String text;
	private int rating;

	public Comment() {

	}

	public Comment(String name, String text, int rating) {
		this.name = name;
		this.text = text;
		this.rating = rating;
	}

    public Comment(String name, String text, String rating) {
		this.name = name;
		this.text = text;
		try{
			int r = Integer.valueOf(rating);
			this.setRating(r);
		}catch (Exception e){
			throw new DomainException(e);
		}
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		if(rating < 0 || rating > 10){
			throw new DomainException("Rating must be between 0 and 10");
		}
		this.rating = rating;
	}

}
