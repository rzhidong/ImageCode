package com.servlet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.glass.ui.Size;

public class ImageServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7753438141219422812L;

	/**
	 * Constructor of the object.
	 */
	public ImageServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
        //这个方法实现验证码的生成
		//画布长度
		int width = 60;
		//画布宽度
		int height = 22;
		//验证码个数
		int codeNum = 4;
		
        BufferedImage bi=new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);//创建图像缓冲区
         Graphics g=bi.getGraphics(); //通过缓冲区创建一个画布
         Color c=new Color(200,150,255); //创建颜色
         /*根据背景画了一个矩形框
          */
         g.setColor(c);//为画布创建背景颜色
         g.fillRect(0, 0, width,height); //fillRect:填充指定的矩形
         
         char[] ch="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();//转化为字符型的数组
         Random r=new Random();
         int len=ch.length;
         int index; //index用于存放随机数字
         StringBuffer sb=new StringBuffer();
         for(int i=0;i<codeNum;i++)
         {
             index=r.nextInt(len);//产生随机数字
             g.setColor(new Color(r.nextInt(88),r.nextInt(188),r.nextInt(255)));  //设置颜色
             
             g.drawString(ch[index]+"",(i*15)+3, height/2+6);//画数字以及数字的位置
             sb.append(ch[index]);
         }
         request.getSession().setAttribute("piccode",sb.toString()); //将数字保留在session中，便于后续的使用
         ImageIO.write(bi, "JPG", response.getOutputStream()); 
    }
	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
