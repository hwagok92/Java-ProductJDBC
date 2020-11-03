import java.util.ArrayList;
import java.util.Scanner;

// console 
public class ProductMain {

	ProductDao dao = new ProductDao();
	Scanner sc = new Scanner(System.in);

	ProductMain(){
		init();
	}

	public void init() {

		while(true) {
			System.out.println("\n=====메뉴 선택하기=====");
			System.out.println("1.모든 상품 조회");
			System.out.println("2.특정 상품 조회(id 이용)");
			System.out.println("3.특정 상품 조회(카테고리 이용)");
			System.out.println("4.상품 수정");
			System.out.println("5.상품 삭제");
			System.out.println("6.상품 추가");
			System.out.println("7.프로그램 종료");
			System.out.print("번호 입력>> "); 
			int menu = sc.nextInt();

			switch(menu) {
			case 1: ArrayList<ProductBean> lists = dao.getAllProducts();
					showProducts(lists);
					break;

			case 2: getProductById();
					break;

			case 3:getProductByCategory();
					break;

			case 4: updateData();
					break;

			case 5: deleteData();
					break;

			case 6: insertData();
					break;

			case 7: System.out.println("프로그램을 종료합니다.");
					System.exit(0);
					break;
					
			default:
				System.out.println("1~7사이의 번호만 입력가능합니다.");

			}

		}
	}// init

	public void deleteData() {
		int id;
		System.out.print("삭제할 id 입력 : ");
		id = sc.nextInt();
		
		int cnt = dao.deleteData(id);
		if(cnt>0)
			System.out.println("삭제 성공");
		else if(cnt == 0)
			System.out.println("조건에 맞는 데이터 없음");
		else
			System.out.println("삭제 실패");
	}
	
	
	public void updateData() {
		
		int id = 0, stock = 0, price = 0 ;
		String category = null, name = null, inputdate = null   ;
		
		System.out.print("id 입력 : ");
		id = sc.nextInt(); 
		
		System.out.print("상품명 입력 : ");
		name = sc.next(); 

		System.out.print("재고 수량 입력 : ");
		stock = sc.nextInt(); 

		System.out.print("단가 입력 : ");
		price = sc.nextInt(); 

		System.out.print("카테고리 입력 : ");
		category = sc.next(); 

		System.out.print("입고 일자 입력 : ");
		inputdate = sc.next(); 		
		
		ProductBean bean = 
				new ProductBean(id,name,stock,price,category,inputdate);
		
		int cnt = dao.updateData(bean);
		if(cnt>0)
			System.out.println("수정 성공");
		else if(cnt == 0)
			System.out.println("조건에 맞는 데이터 없음");
		else
			System.out.println("수정 실패");
		
	}
	
	public void insertData() {
		
		int stock = 0, price = 0 ;
		String category = null, name = null, inputdate = null;
		
		System.out.println("id는 시퀀스로 자동 입력됩니다.(생략)");
	
		System.out.print("상품명 입력 : ");
		name = sc.next(); 

		System.out.print("재고 수량 입력 : ");
		stock = sc.nextInt(); 

		System.out.print("단가 입력 : ");
		price = sc.nextInt(); 

		System.out.print("카테고리 입력 : ");
		category = sc.next(); 

		System.out.print("입고 일자 입력 : ");
		inputdate = sc.next(); 		
		
		ProductBean bean = 
				new ProductBean(0,name,stock,price,category,inputdate);
//		bean.setName(name);
//		bean.setStock(stock);
		
		int cnt = dao.insertData(bean);
		
	}
	
	public void getProductByCategory(){
		System.out.print("찾는 카테고리 입력 : ");
		String category = sc.next();
		ArrayList<ProductBean> lists = dao.getProductByCategory(category);

		if(lists.size()==0) {
			System.out.println("해당 카테고리 없습니다.");
		}
		else {
			showProducts(lists);
		}
		
	}// getProductByCategory


	public void getProductById(){
		System.out.print("찾는 아이디 입력 : ");
		int id = sc.nextInt();
		ProductBean pb = dao.getProductById(id);

		if(pb == null) {
			System.out.println("해당 아이디를 찾을수 없습니다.");
		}
		else {
			String title = "아이디\t이름\t재고\t단가\t카테고리\t입고일자\t";
			System.out.println(title);

			String result = pb.getId()+"\t" + 
					pb.getName()+"\t" + 
					pb.getStock()+"\t" + 
					pb.getPrice()+"\t" + 
					pb.getCategory()+"\t" + 
					pb.getInputdate();

			System.out.println(result);
		}

	}// getProductById




	public void showProducts(ArrayList<ProductBean> lists){

		String title = "아이디\t이름\t재고\t단가\t카테고리\t입고일자\t";
		System.out.println(title);

		//		for(int i=0;i<lists.size();i++)

		for( ProductBean pb  : lists ) {

			String result = pb.getId()+"\t" + 
					pb.getName()+"\t" + 
					pb.getStock()+"\t" + 
					pb.getPrice()+"\t" + 
					pb.getCategory()+"\t" + 
					pb.getInputdate();

			System.out.println(result);
		}


	}// showProducts



	public static void main(String[] args) {
		new ProductMain();
	}

}
