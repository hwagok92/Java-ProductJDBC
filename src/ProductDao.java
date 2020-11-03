import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDao {

	String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:orcl";
	String username="jspid";
	String password="jsppw";

	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	ProductDao(){
		try {
			//1.
			Class.forName(driver) ;			
		} catch (ClassNotFoundException e) {
			System.out.println("클래스가 발견되지 않습니다(jar 파일 누락)"); 
			e.printStackTrace();		
		}
	} // 생성자


	public void getConnection(){
		try {
			//2.
			conn = DriverManager.getConnection(url, username, password) ;
		} catch (SQLException e) {
			System.out.println("커넥션 생성 오류");
			e.printStackTrace();
		}
	}

	public ArrayList<ProductBean> getAllProducts() {
		getConnection();

		String sql = "select * from products order by id asc";

		ArrayList<ProductBean> lists = new ArrayList<ProductBean>();
		try {
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();

			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int stock = rs.getInt("stock");
				int price = rs.getInt("price");
				String category = rs.getString("category");
				String inputdate = String.valueOf(rs.getDate("inputdate"));

				ProductBean bean = new ProductBean(); // 생성자호출
				bean.setId(id);
				bean.setName(name);
				bean.setStock(stock);
				bean.setPrice(price);
				bean.setCategory(category);
				bean.setInputdate(inputdate);

				lists.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null)
					rs.close();

				if(ps != null)
					ps.close();

				if(conn != null)
					conn.close();

			}catch(SQLException e){

			}
		} // finally

		return lists;



	}//

	public ProductBean getProductById(int id){

		getConnection();
		ProductBean bean = null;

		String sql = "select * from products where id=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1,id);

			rs = ps.executeQuery();

			if(rs.next()) {
				int id2 = rs.getInt("id");
				String name = rs.getString("name");
				int stock = rs.getInt("stock");
				int price = rs.getInt("price");
				String category = rs.getString("category");
				String inputdate = String.valueOf(rs.getDate("inputdate"));

				bean = new ProductBean();
				bean.setId(id2);
				bean.setName(name);
				bean.setStock(stock);
				bean.setPrice(price);
				bean.setCategory(category);
				bean.setInputdate(inputdate);
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				if(rs != null)
					rs.close();

				if(ps != null)
					ps.close();

				if(conn != null)
					conn.close();

			}catch(SQLException e){

			}
		} // finally

		return bean; 

	}//getProductById


	public ArrayList<ProductBean> getProductByCategory(String category){

		getConnection();
		ProductBean bean = null;

		String sql = "SELECT * from products where category=?";
		ArrayList<ProductBean> lists = new ArrayList<ProductBean>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1,category.toUpperCase());

			rs = ps.executeQuery(); 

			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int stock = rs.getInt("stock");
				int price = rs.getInt("price");
				String category2 = rs.getString("category");
				String inputdate = String.valueOf(rs.getDate("inputdate"));

				bean = new ProductBean();
				bean.setId(id);
				bean.setName(name);
				bean.setStock(stock);
				bean.setPrice(price);
				bean.setCategory(category2);
				bean.setInputdate(inputdate);

				lists.add(bean);
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				if(rs != null)
					rs.close();

				if(ps != null)
					ps.close();

				if(conn != null)
					conn.close();

			}catch(SQLException e){

			}
		} // finally

		return lists; 
	}// getProductByCategory


	public int insertData(ProductBean pb) {

		getConnection();
		int cnt = -1;
		String sql = "insert into products " +
				" values(prdseq.nextval,?,?,?,?,?)";

		System.out.println(sql);
		
		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1,pb.getName());
			ps.setInt(2,pb.getStock());
			ps.setInt(3,pb.getPrice());
			ps.setString(4, pb.getCategory());
			ps.setString(5,pb.getInputdate());

			cnt = ps.executeUpdate();
			System.out.println("insertData cnt:" + cnt);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps != null)
					ps.close();

				if(conn != null)
					conn.close();

			}catch(SQLException e) {

			}
		}

		return cnt;
	} // insertData

	public int updateData(ProductBean bean) {

		getConnection();
		String sql = "update products set name=?, stock=?, price=?, category=?, inputdate=? where id=?";
		int cnt = -1;

		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, bean.getName());
			ps.setInt(2, bean.getStock());
			ps.setInt(3, bean.getPrice());
			ps.setString(4, bean.getCategory());
			ps.setString(5, bean.getInputdate());
			ps.setInt(6, bean.getId());

			cnt = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps != null)
					ps.close();

				if(conn != null)
					conn.close();

			}catch(SQLException e) {

			}
		}

		return cnt;
	} // updateData
	
	public int deleteData(int id) {
		
		getConnection();
		
		String sql = "delete from products where id=?";
		int cnt = -1;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			cnt = ps.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			try {
				if(ps != null)
					ps.close();

				if(conn != null)
					conn.close();

			}catch(SQLException e) {

			}
		}
		
		return cnt;
	}// deleteData

}













