package com.eazybytes.accounts.service.impl;

import com.eazybytes.accounts.repository.AccountRepository;
import com.eazybytes.accounts.service.AccountNumberGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

/**
 * Generates a 10-digit account number and guarantees uniqueness
 * by checking against the database.
 * Uses {@link SecureRandom} so account numbers are not predictable
 * from previously issued ones.
 * Author: john
 * Date: 8/22/26
 */
@Component
@RequiredArgsConstructor
public class RandomAccountNumberGenerator implements AccountNumberGenerator {

    private static final long MIN_ACCOUNT_NUMBER = 1_000_000_000L; // 10 digits, no leading zero
    private static final long MAX_ACCOUNT_NUMBER = 9_999_999_999L;
    private static final long RANGE = MAX_ACCOUNT_NUMBER - MIN_ACCOUNT_NUMBER + 1;
    private static final int MAX_ATTEMPTS = 10;

    // SecureRandom is thread-safe; a single shared instance is fine and preferred.
    private static final SecureRandom RANDOM = new SecureRandom();

    private final AccountRepository accountRepository;

    @Override
    public Long generate() {
        for (int attempt = 0; attempt < MAX_ATTEMPTS; attempt++) {
            long candidate = MIN_ACCOUNT_NUMBER + (Math.abs(RANDOM.nextLong()) % RANGE);
            if (!accountRepository.existsById(candidate)) {
                return candidate;
            }
        }
        throw new IllegalStateException(
                "Unable to generate a unique account number after " + MAX_ATTEMPTS + " attempts");
    }
}
