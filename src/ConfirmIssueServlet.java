 // Simplified import
import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class ConfirmIssueServlet extends HttpServlet {
    private final TransactionDAO transactionDAO = new TransactionDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        User user = (User) request.getSession().getAttribute("currentUser");
        if (user == null) { response.sendRedirect("login.jsp"); return; }
        
        String serialNo = request.getParameter("serialNo");
        String memberIdStr = request.getParameter("memberId");
        String issueDateStr = request.getParameter("issueDate");
        
        if (serialNo == null || memberIdStr == null || issueDateStr == null || 
            serialNo.isEmpty() || memberIdStr.isEmpty() || issueDateStr.isEmpty()) {
            
            request.setAttribute("errorMessage", "Error: Missing serial number, member ID, or issue date.");
            request.getRequestDispatcher("bookIssueForm.jsp").forward(request, response);
            return;
        }
        
        LocalDate issueDate = LocalDate.parse(issueDateStr);
        if (issueDate.isBefore(LocalDate.now())) {
            request.setAttribute("errorMessage", "Error: Issue Date cannot be in the past.");
            request.getRequestDispatcher("bookIssueForm.jsp").forward(request, response);
            return;
        }

        LocalDate returnDate = issueDate.plusDays(15);
        
        boolean success = transactionDAO.issueBook(serialNo, Integer.parseInt(memberIdStr), issueDate, returnDate);

        if (success) {
            request.setAttribute("message", "Transaction completed successfully for item " + serialNo + ".");
            request.getRequestDispatcher("confirmation.jsp").forward(request, response);
        } else {
            request.setAttribute("message", "Transaction cancelled due to a system error or database failure.");
            request.getRequestDispatcher("cancel.jsp").forward(request, response);
        }
    }
}
