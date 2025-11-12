package mk.ukim.finki.wp.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/bookReservation")
public class BookReservationController {

    public final BookReservationService bookReservationService;

    public BookReservationController(BookReservationService bookReservationService) {
        this.bookReservationService = bookReservationService;
    }

    @PostMapping
    public String createBookReservation(
            @RequestParam("readerName") String readerName,
            @RequestParam("books") String bookTitle,
            @RequestParam("numCopies") String copiesString,
            HttpServletRequest request,
            Model model) {
        String ipAddress = request.getRemoteAddr();

        bookReservationService.placeReservation(bookTitle, readerName, ipAddress, Long.valueOf(copiesString));

        model.addAttribute("name", readerName);
        model.addAttribute("address", ipAddress);
        model.addAttribute("title", bookTitle);
        model.addAttribute("copies", Long.valueOf(copiesString));

        return "reservationConfirmation";
    }
}
