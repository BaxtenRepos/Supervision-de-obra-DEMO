package prueba_paquete;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csgit.cao.business.Demo_Bus;

public class Demo_MVC extends HttpServlet{
@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	//super.doGet(request, response);
		Demo_Bus buss = new Demo_Bus();
		 String username = request.getParameter("Usuario");
	        String password = request.getParameter("Pass");
	        PrintWriter out = response.getWriter();
			if(buss.datos_nulo(username, password));
			{
	        out.println (
	                  "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" +" +
	                      "http://www.w3.org/TR/html4/loose.dtd\">\n" +
	                  "<html> \n" +
	                    "<head> \n" +
	                      "<meta http-equiv=\"Content-Type\" content=\"text/html; " +
	                        "charset=ISO-8859-1\"> \n" +
	                      "<title> Crunchify.com JSP Servlet Example  </title> \n" +
	                    "</head> \n" +
	                    "<body> <div align='center'> \n" +
	                      "<style= \"font-size=\"12px\" color='black'\"" + "\">" +
	                        "Username: " + username + " <br> " + 
	                        "Password: " + password +
	                    "</font></body> \n" +
	                  "</html>" 
	                );      
	
			}
			
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}




}
