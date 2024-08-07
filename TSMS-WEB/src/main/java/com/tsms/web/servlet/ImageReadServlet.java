package com.tsms.web.servlet;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import  java.io.OutputStream;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ImageReadServlet")
public class ImageReadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ImageReadServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String imageUrl = request.getParameter("param");
		if(imageUrl !=null && imageUrl !="") {
			try {
				BufferedImage image = ImageIO.read(new File(imageUrl));
				response.setContentType("image/png");
				OutputStream os = response.getOutputStream();
				ImageIO.write(image, "png", os);
				os.close();
			} catch (Exception e) {
				System.out.println("Image Not present at this loc");
			}
		}
	}

}
