package cn.uitl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.entity.Evaluate;

public class DBUtils {

	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://192.168.20.47:3306/tm_info?characterEncoding=UTF-8&useUnicode=true";
	String username = "root";
	String password = "123456";

	Connection con = null;// 连接对象
	PreparedStatement pstmt = null;// 语句对象
	ResultSet rs = null;// 结果集对象

	/**
	 * 获得连接对象
	 * 
	 * @return 连接对象
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public synchronized Connection getConnection()
			throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		con = DriverManager.getConnection(url, username, password);
		return con;
	}

	public void close(ResultSet rs, PreparedStatement pstmt, Connection con) {

		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void saveInfo(Evaluate evaluate, String itemid) {
		try {
			Connection con = getConnection();
			String sql = "insert into pinglun(productid,username,ratecontent,auctionsku,cmssource,tradeendtime,ratedate,createtime,itemid) values(?,?,?,?,?,?,?,?,?)";
			PreparedStatement statement = con.prepareStatement(sql);
			SimpleDateFormat dateFormater = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			statement.setString(1, evaluate.getId());
			statement.setString(2, evaluate.getName());
			statement.setString(3, evaluate.getRateContent());
			statement.setString(4, evaluate.getAuctionSku());
			statement.setString(5, evaluate.getCmsSource());
			statement.setString(6, evaluate.getTradeEndTime());
			statement.setString(7, dateFormater.format(evaluate.getRatedate()));
			statement.setString(8,
					dateFormater.format(evaluate.getCreatetime()));
			statement.setString(9, itemid);
			int count = statement.executeUpdate();
			close(null, statement, con);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveProductDetail(Map<String, String> map) {
		try {
			Connection con = getConnection();
			String sql = "insert into detail(productName,productShppePrice,productPromotionPrice,productMonthlySales,productAppraise,productIntegral,productStock,productDetail,website,itemid) values(?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, map.get("productName"));
			statement.setString(2, map.get("productShppePrice"));
			statement.setString(3, map.get("productPromotionPrice"));
			statement.setString(4, map.get("productMonthlySales"));
			statement.setString(5, map.get("productAppraise"));
			statement.setString(6, map.get("productIntegral"));
			statement.setString(7, map.get("productStock"));
			statement.setString(8, map.get("productDetail"));
			statement.setString(9, map.get("website"));
			statement.setString(10, map.get("itemid"));
			int count = statement.executeUpdate();
			close(null, statement, con);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveProductUrl(Map<String, String> map) {
		try {
			Connection con = getConnection();
			String sql = "insert into producturl(link,website,pagenum) values(?,?,?)";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, map.get("link"));
			statement.setString(2, map.get("website"));
			statement.setString(3, map.get("pagenum"));
			int count = statement.executeUpdate();
			close(null, statement, con);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveFailProduct(Map<String, String> map) {
		try {
			Connection con = getConnection();
			String sql = "insert into f_product(url,flag,website) values(?,?,?)";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, map.get("url"));
			statement.setString(2, map.get("flag"));
			statement.setString(3, map.get("website"));
			int count = statement.executeUpdate();
			close(null, statement, con);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<String> findByNumber(int num,String website) {
		List<String> urlList = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			conn = this.getConnection();
			String sql = "select link from producturl where pagenum=? AND website=?";
			stat = conn.prepareStatement(sql);
			stat.setInt(1, num);
			stat.setString(2, website);
			rs = stat.executeQuery();
			while (rs.next()) {
				String url = rs.getString(1);
				urlList.add(url);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close(rs, stat, conn);
		}
		return urlList;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
