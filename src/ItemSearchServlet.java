
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class ItemSearchServlet extends HttpServlet {
    private final ItemDAO itemDAO = new ItemDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        User user = (User) request.getSession().getAttribute("currentUser");
        if (user == null) { response.sendRedirect("login.jsp"); return; }

        String bookName = request.getParameter("bookName");
        String author = request.getParameter("author");

        if ((bookName == null || bookName.trim().isEmpty()) && 
            (author == null || author.trim().isEmpty())) {
            
            request.setAttribute("errorMessage", "Error: Please enter a Book Name or an Author to search.");
            request.getRequestDispatcher("bookAvailable.jsp").forward(request, response);
            return;
        }

        List<Item> searchResults = itemDAO.searchItems(bookName, author);

        request.setAttribute("searchResults", searchResults);
        request.getRequestDispatcher("searchResults.jsp").forward(request, response);
    }
}