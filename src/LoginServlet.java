
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {
    private final UserDAO userDAO = new UserDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        String userId = request.getParameter("userId");
        String password = request.getParameter("password");

        User user = userDAO.authenticateUser(userId, password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", user);

            if (user.isAdmin()) {
                response.sendRedirect("adminHome.jsp");
            } else {
                response.sendRedirect("userHome.jsp");
            }
        } else {
            request.setAttribute("errorMessage", "Invalid User ID or Password.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}