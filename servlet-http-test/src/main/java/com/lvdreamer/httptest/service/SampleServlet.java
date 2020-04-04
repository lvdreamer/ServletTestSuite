package com.lvdreamer.httptest.service;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;

/**
 * Servlet implementation class SampleServlet
 */
@WebServlet("/sampleReq")
public class SampleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SampleServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String[]> paramMap = request.getParameterMap();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		System.out.println(MessageFormat.format("receive http-get message {0}", dateFormat.format(new Date())));
		if (null != paramMap) {
			paramMap.keySet().stream()
					.forEach(p -> System.out.println("\t" + p + ":" + Arrays.toString(paramMap.get(p))));
		}
		resp.setContentType("text/plain;charset=utf-8");
		// 获取输出流
		PrintWriter out = resp.getWriter();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String now = sdf.format(date);
		out.println(now + "=>请求成功");
		if (null != paramMap) {
			paramMap.keySet().stream().forEach(p -> out.println("\t" + p + ":" + Arrays.toString(paramMap.get(p))));
		}
		out.flush();
		out.close();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
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
		resp.setContentType(req.getContentType());
		resp.setCharacterEncoding("utf-8");
		ServletInputStream sis = req.getInputStream();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = sis.read(buffer)) != -1) {
			baos.write(buffer, 0, len);
		}
		byte[] dataByte = baos.toByteArray();
		String requestBody = new String(dataByte);
		System.out.println("request body:\n" + requestBody);
		PrintWriter out = resp.getWriter();
		out.print(requestBody);
		out.flush();
		out.close();

	}

}
