package cn.itcast.itcaststore.web.servlet.client;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import cn.itcast.itcaststore.domain.User;
import cn.itcast.itcaststore.service.ProductService;
import cn.itcast.itcaststore.utils.FileUploadUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import cn.itcast.itcaststore.dao.UserDao;
import cn.itcast.itcaststore.domain.User;
import cn.itcast.itcaststore.service.UserService;
import cn.itcast.itcaststore.utils.FileUploadUtils;
public class EditUserServlet extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

				String usernameString="";
				String _usernameString = request.getParameter("username");
				if (_usernameString != null) {
					usernameString = _usernameString;
				}

				String passwordString="";
				String _passString = request.getParameter("password");
				if (_passString != null) {
					passwordString = _passString;
				}

				String sexString="";
				String _seString = request.getParameter("sex");
				if (_seString != null) {
					sexString = _seString;
				}

				String telephoneString="";
				String _telephoneString = request.getParameter("telephone");
				if (_telephoneString != null) {
					telephoneString = _telephoneString;
				}
				
				try {
				UserService service = new UserService();
				User bean = service.update(usernameString, passwordString, sexString, telephoneString);
				request.setAttribute("user", bean);
				request.getRequestDispatcher("/client/index.jsp").forward(request, response);
				return;
				}catch(Exception e) {
					e.printStackTrace();
					
				}

	}
}
