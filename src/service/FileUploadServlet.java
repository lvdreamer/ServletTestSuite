package service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class SampleServlet
 */
@WebServlet("/fileUpload")
@MultipartConfig
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FileUploadServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest req, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
		System.out.println(MessageFormat.format("receive http-get message {0}", dateFormat.format(new Date())));
		System.out.println("请求方式==>" + req.getMethod());
		System.out.println("访问路径==>" + req.getServletPath());
		System.out.println("协议类型==>" + req.getProtocol());
		// 1.2读取消息头
		Enumeration<String> e = req.getHeaderNames();
		while (e.hasMoreElements()) {
			String key = e.nextElement();
			String value = req.getHeader(key);
			System.out.println(key + ":" + value);
		}
		try {
			// 获取上传的文件
			Part part = req.getPart("file");
			if (null != part) {

				// 获取请求的信息
				String name = part.getHeader("content-disposition");
				System.out.println("fileName:" + name);
				InputStream is = part.getInputStream();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = is.read(buffer)) != -1) {
					baos.write(buffer, 0, len);
				}
				byte[] dataByte = baos.toByteArray();
				System.out.println("fileSize:" + dataByte.length);
				resp.setContentType("text/plain;charset=utf-8");
				// 获取输出流
				PrintWriter out = resp.getWriter();
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
				String now = sdf.format(date);
				out.println(now + "==>请求成功");
				out.println("content-disposition:" + name);
				out.println("fileSize:" + dataByte.length / 1000 + "kb");
				out.flush();
				out.close();
			} else {
				// 获取输出流
				PrintWriter out = resp.getWriter();
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
				String now = sdf.format(date);
				out.println(now + "==>no file upload!!");
				out.flush();
				out.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
