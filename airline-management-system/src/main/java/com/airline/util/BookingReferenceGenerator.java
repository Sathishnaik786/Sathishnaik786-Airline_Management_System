package com.airline.util;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class BookingReferenceGenerator {
    private static final SecureRandom random = new SecureRandom();

    public String generate() {
        return IntStream.range(0, Constants.BOOKING_REFERENCE_LENGTH)
                .mapToObj(i -> String.valueOf(
                        Constants.BOOKING_REFERENCE_CHARS.charAt(
                                random.nextInt(Constants.BOOKING_REFERENCE_CHARS.length()))))
                .collect(Collectors.joining());
    }
}