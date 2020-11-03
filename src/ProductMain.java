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
			System.out.println("\n=====�޴� �����ϱ�=====");
			System.out.println("1.��� ��ǰ ��ȸ");
			System.out.println("2.Ư�� ��ǰ ��ȸ(id �̿�)");
			System.out.println("3.Ư�� ��ǰ ��ȸ(ī�װ� �̿�)");
			System.out.println("4.��ǰ ����");
			System.out.println("5.��ǰ ����");
			System.out.println("6.��ǰ �߰�");
			System.out.println("7.���α׷� ����");
			System.out.print("��ȣ �Է�>> "); 
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

			case 7: System.out.println("���α׷��� �����մϴ�.");
					System.exit(0);
					break;
					
			default:
				System.out.println("1~7������ ��ȣ�� �Է°����մϴ�.");

			}

		}
	}// init

	public void deleteData() {
		int id;
		System.out.print("������ id �Է� : ");
		id = sc.nextInt();
		
		int cnt = dao.deleteData(id);
		if(cnt>0)
			System.out.println("���� ����");
		else if(cnt == 0)
			System.out.println("���ǿ� �´� ������ ����");
		else
			System.out.println("���� ����");
	}
	
	
	public void updateData() {
		
		int id = 0, stock = 0, price = 0 ;
		String category = null, name = null, inputdate = null   ;
		
		System.out.print("id �Է� : ");
		id = sc.nextInt(); 
		
		System.out.print("��ǰ�� �Է� : ");
		name = sc.next(); 

		System.out.print("��� ���� �Է� : ");
		stock = sc.nextInt(); 

		System.out.print("�ܰ� �Է� : ");
		price = sc.nextInt(); 

		System.out.print("ī�װ� �Է� : ");
		category = sc.next(); 

		System.out.print("�԰� ���� �Է� : ");
		inputdate = sc.next(); 		
		
		ProductBean bean = 
				new ProductBean(id,name,stock,price,category,inputdate);
		
		int cnt = dao.updateData(bean);
		if(cnt>0)
			System.out.println("���� ����");
		else if(cnt == 0)
			System.out.println("���ǿ� �´� ������ ����");
		else
			System.out.println("���� ����");
		
	}
	
	public void insertData() {
		
		int stock = 0, price = 0 ;
		String category = null, name = null, inputdate = null;
		
		System.out.println("id�� �������� �ڵ� �Էµ˴ϴ�.(����)");
	
		System.out.print("��ǰ�� �Է� : ");
		name = sc.next(); 

		System.out.print("��� ���� �Է� : ");
		stock = sc.nextInt(); 

		System.out.print("�ܰ� �Է� : ");
		price = sc.nextInt(); 

		System.out.print("ī�װ� �Է� : ");
		category = sc.next(); 

		System.out.print("�԰� ���� �Է� : ");
		inputdate = sc.next(); 		
		
		ProductBean bean = 
				new ProductBean(0,name,stock,price,category,inputdate);
//		bean.setName(name);
//		bean.setStock(stock);
		
		int cnt = dao.insertData(bean);
		
	}
	
	public void getProductByCategory(){
		System.out.print("ã�� ī�װ� �Է� : ");
		String category = sc.next();
		ArrayList<ProductBean> lists = dao.getProductByCategory(category);

		if(lists.size()==0) {
			System.out.println("�ش� ī�װ� �����ϴ�.");
		}
		else {
			showProducts(lists);
		}
		
	}// getProductByCategory


	public void getProductById(){
		System.out.print("ã�� ���̵� �Է� : ");
		int id = sc.nextInt();
		ProductBean pb = dao.getProductById(id);

		if(pb == null) {
			System.out.println("�ش� ���̵� ã���� �����ϴ�.");
		}
		else {
			String title = "���̵�\t�̸�\t���\t�ܰ�\tī�װ�\t�԰�����\t";
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

		String title = "���̵�\t�̸�\t���\t�ܰ�\tī�װ�\t�԰�����\t";
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
