package cn.itcast.itcaststore.web.servlet.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.itcast.itcaststore.domain.PageBean;
import cn.itcast.itcaststore.service.ProductService;
//鍒嗛〉鏄剧ず鏁版嵁
public class ShowProductByPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.瀹氫箟褰撳墠椤电爜锛岄粯璁や负1
		int currentPage = 1;
		String _currentPage = request.getParameter("currentPage");
		if (_currentPage != null) {
			currentPage = Integer.parseInt(_currentPage);
		}
		// 2.瀹氫箟姣忛〉鏄剧ず鏉℃暟,榛樿涓�4
		int currentCount = 4;
		String _currentCount = request.getParameter("currentCount");
		if (_currentCount != null) {
			currentCount = Integer.parseInt(_currentCount);
		}
		// 3.鑾峰彇鏌ユ壘鐨勫垎绫�
		String category = "all";
		String _category = request.getParameter("category");
		if (_category != null) {
			category = _category;
		}
		// 4.璋冪敤service锛屽畬鎴愯幏鍙栧綋鍓嶉〉鍒嗛〉Bean鏁版嵁.
		ProductService service = new ProductService();
		PageBean bean = service.findProductByPage(currentPage, currentCount,
				category);
		// 灏嗘暟鎹瓨鍌ㄥ埌request鑼冨洿锛岃烦杞埌product_list.jsp椤甸潰灞曠ず
		request.setAttribute("bean", bean);
		request.getRequestDispatcher("/client/product_list.jsp").forward(request, response);
		return;
	}
}
