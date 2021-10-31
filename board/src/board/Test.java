package board;

public class Test {
	public static void main(String[] args) {
	int no = 1;
	String title = "제목";
	String body  = "내용";
	String regDate = "2021.10.16";
	int hit = 0;
	
	int no2 = 2;
	String title2 = "제목2";
    String body2 = "내용2"	;
	String regDate2 = "2021.10.17";
	int hit2 = 1;
	
	 printArticle(no,title,body,regDate,hit);
	 printArticle(no2,title2,body2,regDate2,hit2);
	 
	 Article a = new Article();
	 Article b = new Article();
	 
	 
	
	
	}
	public void printArticle(int no, String title, String body, String regDate, int hit) {
	System.out.println("번호 : " + no);
	System.out.println("제목 : " + title);
	System.out.println("내용 : " + body);
	System.out.println("조회수 : " + regDate);
	System.out.println("작성일: " + hit);
	
	}

	public static void printArticle2(Article a)
	{
	System.out.println("번호 : " + a.no);
	System.out.println("제목 : " + a.title);
	System.out.println("내용 : " + a.body);
	System.out.println("조회수 : " + a.regDate);
	System.out.println("작성일: " + a.hit);
		
		
	}
	
	class Article{
	int no = 1;
	String title = "제목";
	String body = "내용";
	String regDate = "2021.10.16";
	int hit = 0 ;
		
	}
	}
	
	
	
	
}
