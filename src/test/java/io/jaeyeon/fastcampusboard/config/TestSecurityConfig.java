package io.jaeyeon.fastcampusboard.config;

import io.jaeyeon.fastcampusboard.domain.UserAccount;
import io.jaeyeon.fastcampusboard.repository.UserAccountRepository;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@Import(SecurityConfig.class)
public class TestSecurityConfig {

    @MockBean private UserAccountRepository userAccountRepository;

    @BeforeTestMethod
    public void securitySetUp() {
        given(userAccountRepository.findById(anyString())).willReturn(Optional.of(UserAccount.of(
                "jaeyeonTest",
                "pw",
                "cjyeon1022@email.com",
                "jaeyeon-test",
                "test memo"
        )));
    }
}
