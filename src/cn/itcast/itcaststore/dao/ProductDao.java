package cn.itcast.itcaststore.dao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
//import com.sun.faces.taglib.jsf_core.MaxMinValidatorTag;
import cn.itcast.itcaststore.domain.Order;
import cn.itcast.itcaststore.domain.OrderItem;
import cn.itcast.itcaststore.domain.Product;
import cn.itcast.itcaststore.utils.DataSourceUtils;

public class ProductDao {

	public void addProduct(Product p) throws SQLException {

		String sql = "insert into products values(?,?,?,?,?,?,?)";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, p.getId(), p.getName(), p.getPrice(),
				p.getCategory(), p.getPnum(), p.getImgurl(), p.getDescription());
	}

	public List<Product> listAll() throws SQLException {
		String sql = "select * from products";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<Product>(Product.class));
	}

	public int findAllCount(String category) throws SQLException {
		String sql = "select count(*) from products";

		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

		if (!"all".equals(category)) {
			sql += " where category=?";

			Long count = (Long) runner.query(sql, new ScalarHandler(), category);
			return count.intValue();
		} else {
			Long count = (Long) runner.query(sql, new ScalarHandler());

			return count.intValue();
		}
	}
	// 鑾峰彇褰撳墠椤垫暟鎹�
	public List<Product> findByPage(int currentPage, int currentCount,
			String category) throws SQLException {
		// 瑕佹墽琛岀殑sql璇彞
		String sql = null;
		// 鍙傛暟
		Object[] obj = null;
		// 濡傛灉category涓嶄负null,浠ｈ〃鏄寜鍒嗙被鏌ユ壘
		if (!"all".equals(category)) {
			sql = "select * from products  where category=? limit ?,?";
			obj = new Object[] { category, (currentPage - 1) * currentCount,
					currentCount, };
		} else {
			sql = "select * from products  limit ?,?";
			obj = new Object[] { (currentPage - 1) * currentCount,
					currentCount, };
		}
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<Product>(Product.class),
				obj);
	}

	// 鏍规嵁id鏌ユ壘鍟嗗搧
	public Product findProductById(String id) throws SQLException {
		String sql = "select * from products where id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<Product>(Product.class), id);
	}

	// 鐢熸垚璁㈠崟鏃讹紝灏嗗晢鍝佹暟閲忓噺灏�
	public void changeProductNum(Order order) throws SQLException {
		String sql = "update products set pnum=pnum-? where id=?";
		QueryRunner runner = new QueryRunner();
		List<OrderItem> items = order.getOrderItems();
		Object[][] params = new Object[items.size()][2];

		for (int i = 0; i < params.length; i++) {
			params[i][0] = items.get(i).getBuynum();
			params[i][1] = items.get(i).getP().getId();
		}

		runner.batch(DataSourceUtils.getConnection(), sql, params);
	}

	//销售榜单
	public List<Object[]> salesList(String year, String month)
			throws SQLException {
		String sql = "SELECT products.name,SUM(orderitem.buynum) totalsalnum FROM orders,products,orderItem WHERE orders.id=orderItem.order_id AND products.id=orderItem.product_id AND orders.paystate=1 and year(ordertime)=? and month(ordertime)=? GROUP BY products.name ORDER BY totalsalnum DESC";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new ArrayListHandler(), year, month);
	}

	//多条件查询
	public List<Product> findProductByManyCondition(String id, String name,
			String category, String minprice, String maxprice)
			throws SQLException {
		List<Object> list = new ArrayList<Object>();
		String sql = "select * from products where 1=1 ";

		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

		if (id != null && id.trim().length() > 0) {
			sql += " and id=?";
			list.add(id);
		}

		if (name != null && name.trim().length() > 0) {
			sql += " and name=?";
			list.add(name);
		}
		if (category != null && category.trim().length() > 0) {
			sql += " and category=?";
			list.add(category);
		}
		if (minprice != null && maxprice != null
				&& minprice.trim().length() > 0 && maxprice.trim().length() > 0) {
			sql += " and price between ? and ?";
			list.add(minprice);
			list.add(maxprice);
		}

		Object[] params = list.toArray();

		return runner.query(sql, new BeanListHandler<Product>(Product.class),
				params);
	}
	//修改商品信息
	public void editProduct(Product p) throws SQLException {
		//1.创建集合并将商品信息添加到集合中
		List<Object> obj = new ArrayList<Object>();
		obj.add(p.getName());
		obj.add(p.getPrice());
		obj.add(p.getCategory());
		obj.add(p.getPnum());
		obj.add(p.getDescription());
		//2.创建sql语句，并拼接sql
		String sql  = "update products " +
				      "set  name=?,price=?,category=?,pnum=?,description=? ";
		//判断是否有图片
		if (p.getImgurl() != null && p.getImgurl().trim().length() > 0) {
			sql += " ,imgurl=?";
			obj.add(p.getImgurl());
		}
		sql += " where id=?";
		obj.add(p.getId());		
		System.out.println(sql);		
		System.out.println(obj);
		//3.鍒涘缓QueryRunner瀵硅薄
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		//4.浣跨敤QueryRunner瀵硅薄鐨剈pdate()鏂规硶鏇存柊鏁版嵁
		runner.update(sql, obj.toArray());
	}
	//删除订单时，修改商品数量
	public void updateProductNum(List<OrderItem> items) throws SQLException {
		
		String sql = "update products set pnum=pnum+? where id=?";
		QueryRunner runner = new QueryRunner();
		
		Object[][] params = new Object[items.size()][2];

		for (int i = 0; i < params.length; i++) {
			params[i][0] = items.get(i).getBuynum();
			params[i][1] = items.get(i).getP().getId();
		}

		runner.batch(DataSourceUtils.getConnection(), sql, params);
	}

	//前台，获取本周热销商品
	public List<Object[]> getWeekHotProduct() throws SQLException {
		String sql = "SELECT products.id,products.name, "+
                             " products.imgurl,SUM(orderitem.buynum) totalsalnum "+
                     " FROM orderitem,orders,products "+
                     " WHERE orderitem.order_id = orders.id "+
                             " AND products.id = orderitem.product_id "+
                             " AND orders.paystate=1 "+
                             " AND orders.ordertime > DATE_SUB(NOW(), INTERVAL 7 DAY) "+
                     " GROUP BY products.id,products.name,products.imgurl "+
                     " ORDER BY totalsalnum DESC "+
                     " LIMIT 0,2 ";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new ArrayListHandler());
	}

	//前台，用于搜索框根据书名来模糊查询相应的图书
	public List<Product> findBookByName(int currentPage, int currentCount,
			String searchfield) throws SQLException {
		//鏍规嵁鍚嶅瓧妯＄硦鏌ヨ鍥句功
		String sql = "SELECT * FROM products WHERE name LIKE '%"+searchfield+"%' LIMIT ?,?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
//		//鐢ㄤ簬鍒嗛〉鏌ヨ鐨勬暟鎹�
//		Object obj = new Object[] { (currentPage - 1) * currentCount, currentCount };
		return runner.query(sql, 
				new BeanListHandler<Product>(Product.class),currentPage-1,currentCount);
	}

	//前台搜索框，根据书名模糊查询出的图书总数量
	public int findBookByNameAllCount(String searchfield) throws SQLException {
		String sql = "SELECT COUNT(*) FROM products WHERE name LIKE '%"+searchfield+"%'";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		//鏌ヨ鍑烘弧瓒虫潯浠剁殑鎬绘暟閲忥紝涓簂ong绫诲瀷
		Long count = (Long)runner.query(sql, new ScalarHandler());
		return count.intValue();
	}

	//后台系统，根据id删除商品信息
	public void deleteProduct(String id) throws SQLException {
		String sql = "DELETE FROM products WHERE id = ?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, id);
	}
}
