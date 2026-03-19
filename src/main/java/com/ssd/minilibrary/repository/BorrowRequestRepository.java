package com.ssd.minilibrary.repository;

import com.ssd.minilibrary.model.BorrowRequest;
import com.ssd.minilibrary.model.BorrowStatus;
import com.ssd.minilibrary.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRequestRepository extends JpaRepository<BorrowRequest, Long> {

    List<BorrowRequest> findByUser(User user);

    List<BorrowRequest> findByStatus(BorrowStatus status);
}
