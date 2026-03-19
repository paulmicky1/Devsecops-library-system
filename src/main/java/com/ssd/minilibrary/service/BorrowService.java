package com.ssd.minilibrary.service;

import com.ssd.minilibrary.model.Book;
import com.ssd.minilibrary.model.BorrowRequest;
import com.ssd.minilibrary.model.BorrowStatus;
import com.ssd.minilibrary.model.User;
import com.ssd.minilibrary.repository.BookRepository;
import com.ssd.minilibrary.repository.BorrowRequestRepository;
import com.ssd.minilibrary.repository.UserRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BorrowService {

    private final BorrowRequestRepository borrowRequestRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public BorrowService(
        BorrowRequestRepository borrowRequestRepository,
        BookRepository bookRepository,
        UserRepository userRepository
    ) {
        this.borrowRequestRepository = borrowRequestRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public BorrowRequest requestBorrow(String username, Long bookId) {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new IllegalArgumentException("User not found: " + username));
        Book book = bookRepository.findById(bookId)
            .orElseThrow(() -> new IllegalArgumentException("Book not found with id: " + bookId));

        BorrowRequest borrowRequest = new BorrowRequest();
        borrowRequest.setUser(user);
        borrowRequest.setBook(book);
        borrowRequest.setStatus(BorrowStatus.PENDING);

        return borrowRequestRepository.save(borrowRequest);
    }

    public List<BorrowRequest> getMyRequests(String username) {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new IllegalArgumentException("User not found: " + username));
        return borrowRequestRepository.findByUser(user);
    }

    public List<BorrowRequest> getPendingRequests() {
        return borrowRequestRepository.findByStatus(BorrowStatus.PENDING);
    }

    public void updateRequestStatus(Long requestId, BorrowStatus newStatus) {
        BorrowRequest borrowRequest = borrowRequestRepository.findById(requestId)
            .orElseThrow(() -> new IllegalArgumentException("Borrow request not found with id: " + requestId));
        borrowRequest.setStatus(newStatus);
        borrowRequestRepository.save(borrowRequest);
    }
}
