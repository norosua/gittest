package board;

public class Article {
	int no;
	String title;
	String writer;
	String body;
	String regDate;
	int like_it;
	
	Article(int no, String title, String writer, String body, String regDate, int like_it) {
		this.no = no;
		this.title = title;
		this.writer = writer;
		this.body = body;
		this.regDate = regDate;
		this.like_it = like_it;
	}
}