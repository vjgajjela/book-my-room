package com.booking.bookmyroom.utils;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Cache to store idempotent keys sent during booking meeting rooms.
 *
 */
@Component
public class BookingCache {

	@Autowired
	Set<String> idempotentKeys;

	public boolean checkIfKeyExistsInCache(String idempotencyKey) {
		return idempotentKeys.contains(idempotencyKey);
	}

	public void addKeyToCache(String idempotencyKey) {
		idempotentKeys.add(idempotencyKey);
    }

}
