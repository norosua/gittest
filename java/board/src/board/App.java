package board;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import board.GeneralMember;
import board.Member;
import board.SpecialMember;

public class App {

	// 변수 선언
	Scanner sc = new Scanner(System.in);
	ArrayList<Article> articles = new ArrayList<>();
	ArrayList<Member> members = new ArrayList<>();
	ArrayList<Reply> replies = new ArrayList<>();
	
	int articleNo = 1;
	Member loginedUser = null; // 로그인한 유저
	
	// 메서드 선언
	public void run() {
		makeTestData();

		while (true) {
			if(loginedUser == null) {
				System.out.print("명령어를 입력해주세요 : ");				
			} else {
				System.out.print("명령어를 입력해주세요 [" + loginedUser.getNickname() + "(" + loginedUser.getLoginId() + ")]: ");				
			}
			String command = sc.nextLine();

			if (command.equals("exit")) {
				System.out.println("프로그램을 종료합니다.");
				break;
			} else if (command.equals("help")) {
				printHelp();

			} else if (command.equals("add")) {
				
				if(loginCheck()) {
					add();					
				} 

			} else if (command.equals("list")) {
				list(articles);

			} else if (command.equals("update")) {
				update();

			} else if (command.equals("delete")) {
				delete();

			} else if (command.equals("search")) {
				search();

			} else if (command.equals("read")) {
				
				if(loginCheck()) {
					read();
				}
				
			} else if(command.equals("signup")) {
				signup();
				
			} else if(command.equals("login")) {
				login();
				
			} else if(command.equals("logout")) {
				logout();
				
			}
		}
	}

	private void logout() {
		
		loginedUser = null;
		System.out.println("로그아웃 되셨습니다.");
		
	}

	private void login() {
		boolean isSuccessLogin = false;
		
		System.out.print("아이디 : ");
		String loginId = sc.nextLine();
		System.out.print("비밀번호 : ");
		String loginPw = sc.nextLine();
		
		for(int i = 0; i < members.size(); i++) {
			Member m = members.get(i);
			if(m.getLoginId().equals(loginId)) {
				if(m.getLoginPw().equals(loginPw)) {
					loginedUser = m;
					m.welcome();
					isSuccessLogin = true;
				} 
			} 
		}
		
		if(!isSuccessLogin) {
			System.out.println("잘못된 회원정보 틀림");
		}
		
	}

	
	
	private void signup() {
		
		System.out.println("1. 우수회원, 2. 일반회원");
		int memberFlag = Integer.parseInt(sc.nextLine());
		
		System.out.print("아이디를 입력해주세요 : ");
		String loginId = sc.nextLine();
		System.out.print("비밀번호를 입력해주세요 : ");
		String loginPw = sc.nextLine();
		System.out.print("닉네임을 입력해주세요 : ");
		String nickname = sc.nextLine();
		
		Member m = null;
		
		if(memberFlag == 1) {
			 m = new SpecialMember(loginId, loginPw, nickname, 0);			
		} else {
			 m = new GeneralMember(loginId, loginPw, nickname);
		}
		
		members.add(m);
		System.out.println("회원가입이 완료되었습니다.");
	}

	public void read() {
		System.out.println("상세보기할 게시물 선택 : ");
		int no = Integer.parseInt(sc.nextLine());
		int index = getIndexByAritlceNo(no);
		
		if(index != -1) {
			Article a = articles.get(index);
			
			System.out.println("==== " + a.no +"번 게시물 ====");
			System.out.println("번호 : " + a.no);
			System.out.println("제목 : " + a.title);
			System.out.println("-------------------");
			System.out.println("내용 : " + a.body);
			System.out.println("-------------------");
			System.out.println("작성자 : 익명");
			System.out.println("등록날짜: " + a.regDate);
			System.out.println("좋아요 수: " + a.like_it);
			System.out.println("===================");		
			
			// 상세보기 기능
			readProcess(a);						
			
		} else {
			System.out.println("없는 게시물입니다.");
		}

	}

	private void readProcess(Article a) {
		
		while(true) {
			System.out.print("상세보기 기능을 선택해주세요(1. 댓글 등록, 2. 좋아요, 3. 수정, 4. 삭제, 5. 목록으로) : ");
			int rcmd = Integer.parseInt(sc.nextLine());
			
			if(rcmd == 1) {
				
				System.out.print("댓글 내용을 입력해주세요 : ");
				String replyBody = sc.nextLine();
				// 작성자
				String writer = loginedUser.getNickname();
				// 작성일
				String regDate = getCurrentData();
				// 어떤 게시물의 댓글?
				int parentNo = a.no;
				
				Reply reply = new Reply(parentNo, replyBody, writer, regDate);
				replies.add(reply);
				
				System.out.println("댓글이 등록되었습니다.");
				
				System.out.println("==== " + a.no +"번 게시물 ====");
				System.out.println("번호 : " + a.no);
				System.out.println("제목 : " + a.title);
				System.out.println("-------------------");
				System.out.println("내용 : " + a.body);
				System.out.println("-------------------");
				System.out.println("작성자 : 익명");
				System.out.println("등록날짜: " + a.regDate);
				System.out.println("좋아요 수: " + a.like_it);
				System.out.println("===================");
				System.out.println("======== 댓글 =======");
				for(int i = 0; i < replies.size(); i++) {
					Reply r = replies.get(i);
					System.out.println("내용 : " + r.getrBody());
					System.out.println("작성자 : " + r.getWriter());
					System.out.println("작성일 : " + r.getRegDate());
					System.out.println("=======================");
				}
				
				
			} else if(rcmd == 2) {
				//로그인한 유저가 해당 게시물에 좋아요 체크했는지 따진다.
				if() {
					System.out.println("[해당 게시물을 좋아합니다.]");
					a.like_it +=1;
					
				}
				else {
					System.out.println("[해당 게시물을 좋아요를 해제합니다.]");
					
				}
				
			} else if(rcmd == 3) {
				System.out.println("[수정]");
			} else if(rcmd == 4) {
				System.out.println("[삭제]");
			} else if(rcmd == 5) {
				break;
			}
		}
		
	}

	// 함수 -> 기능
	// 코드 재활용
	// 코드의 구조화 -> 집중
	// 코드가 깔끔 -> 가독성이 올라간다.
	// ===========================================================
	// 검색 키워드로 검색하기
	public void search() {
		System.out.println("검색 키워드 입력 : ");
		String keyword = sc.nextLine();

		ArrayList<Article> searchedList = new ArrayList<>();
		// 번호로 찾기
		for (int i = 0; i < articles.size(); i++) {
			Article a = articles.get(i);
			if (a.title.contains(keyword)) {
				searchedList.add(a);
			}
		}

		list(searchedList);
	}

	// ===========================================================
	// 번호로 게시물 인덱스 찾는 함수
	public int getIndexByAritlceNo(int no) {

		int index = -1; // 0이 아닌 이유 : 값이 없을 경우 없다는 것을 표현하기 위함. 0은 인덱스로서 의미를 가지니까

		for (int i = 0; i < articles.size(); i++) {

			Article a = articles.get(i);

			if (no == a.no) {
				index = i;
				break;
			}
		}

		return index;
	}

	// ===========================================================
	// 게시물을 삭제하는 함수
	public void delete() {
		System.out.println("삭제할 게시물 선택 : ");
		int no = Integer.parseInt(sc.nextLine());
		int index = getIndexByAritlceNo(no);

		if (index != -1) {
			articles.remove(index);
		} else {
			System.out.println("없는 게시물입니다.");
		}
	}

	// ===========================================================
	// 게시물을 수정해주는 함수
	public void update() {
		System.out.println("수정할 게시물 선택 : ");
		int no = Integer.parseInt(sc.nextLine());

		int index = getIndexByAritlceNo(no);

		if (index != -1) {
			System.out.print("새제목 : ");
			String title = sc.nextLine();
			System.out.print("새내용 : ");
			String body = sc.nextLine();

			Article a = articles.get(index);
			a.title = title;
			a.body = body;

			articles.set(index, a);

		} else {
			System.out.println("없는 게시물입니다.");
		}
	}

	// ===========================================================
	// 게시물 목록을 보여주는 함수
	public void list(ArrayList<Article> articleList) {

		for (int i = 0; i < articleList.size(); i++) {
			Article a = articleList.get(i);
			System.out.println("번호 : " + a.no);
			System.out.println("제목 : " + a.title);
			System.out.println("작성자 : " + a.writer);
			System.out.println("작성일 : " + a.regDate);
			System.out.println("조회수 : " + 0);
			System.out.println("===============");
		}

	}

	// ===========================================================
	// 게시물 추가하는 함수
	public void add() {

		System.out.print("제목을 입력해주세요 : ");
		String title = sc.nextLine();
		System.out.print("내용을 입력해주세요 : ");
		String body = sc.nextLine();

		// 현재 시간 구해서 등록.
		// 현재 날짜 구하기 (시스템 시계, 시스템 타임존)
		String regDate = getCurrentData();
		int like_it = 0;
		Article a = new Article(articleNo, title, loginedUser.getNickname(), body, regDate, like_it);
		articles.add(a);

		System.out.println("게시물이 등록되었습니다.");
		articleNo++;
	}

	// ===========================================================
	// 도움말 출력 함수
	public static void printHelp() {
		System.out.println("========================");
		System.out.println("help : 도움말");
		System.out.println("add : 데이터 추가");
		System.out.println("read : 데이터 조회");
		System.out.println("update : 데이터 수정");
		System.out.println("delete : 데이터 삭제");
		System.out.println("exit : 프로그램 종료");
		System.out.println("========================");
	}

	public void makeTestData() {
		setTestData("안녕하세요", "반갑습니다");
		setTestData("하이~", "냉무");
		setTestData("가입인사드립니다.", "잘부탁드립니다.");
	}

	public void setTestData(String title, String body) {
		int like_it = 0;

		String regDate = getCurrentData();
		Article a = new Article(articleNo, title, "홍길동", body, regDate, like_it);

		articles.add(a);
		System.out.println("게시물이 등록되었습니다.");
		articleNo++;
	}

	public String getCurrentData() {
		LocalDate now = LocalDate.now();

		// 포맷 정의
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
		// 포맷 적용
		String formatedNow = now.format(formatter);

		return formatedNow;

	}
	
	public boolean loginCheck() {
		
		if(loginedUser != null) {
			return true;
		} else {
			System.out.println("로그인이 필요한 기능입니다.");
			return false;
		}		
	}
}