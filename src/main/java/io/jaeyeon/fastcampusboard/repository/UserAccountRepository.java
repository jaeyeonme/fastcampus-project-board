package io.jaeyeon.fastcampusboard.repository;

import io.jaeyeon.fastcampusboard.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, String> {
}
