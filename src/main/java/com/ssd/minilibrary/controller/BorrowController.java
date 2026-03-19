package com.ssd.minilibrary.controller;

import com.ssd.minilibrary.model.BorrowStatus;
import com.ssd.minilibrary.service.BorrowService;
import java.security.Principal;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/borrow")
public class BorrowController {

    private final BorrowService borrowService;

    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @PostMapping("/request/{bookId}")
    @PreAuthorize("hasRole('USER')")
    public String requestBorrow(@PathVariable Long bookId, Principal principal) {
        borrowService.requestBorrow(principal.getName(), bookId);
        return "redirect:/books?borrowSuccess";
    }

    @GetMapping("/my-requests")
    @PreAuthorize("hasRole('USER')")
    public String myRequests(Model model, Principal principal) {
        model.addAttribute("requests", borrowService.getMyRequests(principal.getName()));
        return "my-requests";
    }

    @GetMapping("/manage")
    @PreAuthorize("hasRole('LIBRARIAN')")
    public String manageRequests(Model model) {
        model.addAttribute("requests", borrowService.getPendingRequests());
        return "manage-requests";
    }

    @PostMapping("/approve/{id}")
    @PreAuthorize("hasRole('LIBRARIAN')")
    public String approveRequest(@PathVariable Long id) {
        borrowService.updateRequestStatus(id, BorrowStatus.APPROVED);
        return "redirect:/borrow/manage";
    }

    @PostMapping("/reject/{id}")
    @PreAuthorize("hasRole('LIBRARIAN')")
    public String rejectRequest(@PathVariable Long id) {
        borrowService.updateRequestStatus(id, BorrowStatus.REJECTED);
        return "redirect:/borrow/manage";
    }
}
