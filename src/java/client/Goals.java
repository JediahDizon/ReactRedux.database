package client;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Database Persistence: SQLite
import com.google.gson.Gson;
import org.hibernate.Session;
import util.HibernateUtil;
import entity.Goal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/Goals"})
public class Goals extends HttpServlet {

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			response.setHeader("Access-Control-Allow-Origin", "*");
			
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			try {
				
					
				if("PUT".equals(request.getMethod())) {
					Goal toAdd = new Gson().fromJson(request.getReader().readLine(), Goal.class);
					
					if(toAdd != null && (toAdd.getTitle() != null || toAdd.getDescription() != null)) {
						Goal toReturn = (Goal) session.get(Goal.class, Long.parseLong(session.save(toAdd).toString()));
						session.getTransaction().commit();
						out.println(new Gson().toJson(toReturn));
					} else {
						out.println("{ 'status' :'error', 'message' : 'Please enter valid Goal title or description.' }");
					}
				} else if("GET".equals(request.getMethod())) {
					List<Goal> allGoals = new ArrayList();
					String[] parameterValues = request.getParameterValues("id");

					if(parameterValues != null && parameterValues.length != 0) {
						for(String parameterValue : parameterValues) {
							Goal toAdd = (Goal) session.get(Goal.class, Long.parseLong(parameterValue));
							if(toAdd != null)
								allGoals.add(toAdd);
						}
					} else {
						allGoals = session.createCriteria(Goal.class).list();
					}
					
					System.out.println(allGoals.toString());
					out.println(new Gson().toJson(allGoals));
				}
				session.close();
			} catch (Exception error) {
				session.getTransaction().rollback();
				error.printStackTrace();
				out.println(error.getMessage());
			}
		}
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}
	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
