package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "BookReservationServlet", urlPatterns = "/bookReservation")
public class BookReservationServlet extends HttpServlet {

    private final BookReservationService bookReservationService;
    private final SpringTemplateEngine springTemplateEngine;

    public BookReservationServlet(BookReservationService bookReservationService, SpringTemplateEngine springTemplateEngine) {
        this.bookReservationService = bookReservationService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        String readerName = req.getParameter("readerName");
        String ipAddress = req.getRemoteAddr();
        String bookTitle = req.getParameter("books");
        String copiesString = req.getParameter("numCopies");
        Long copies = (long) Integer.parseInt(copiesString);

        bookReservationService.placeReservation(bookTitle, readerName, ipAddress, copies);

        context.setVariable("name", readerName);
        context.setVariable("address", ipAddress);
        context.setVariable("title", bookTitle);
        context.setVariable("copies", copies);

        springTemplateEngine.process("reservationConfirmation.html", context, resp.getWriter());

    }
}
