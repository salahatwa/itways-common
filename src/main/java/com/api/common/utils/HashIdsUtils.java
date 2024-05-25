package com.api.common.utils;

import java.util.Arrays;

import org.hashids.Hashids;

public class HashIdsUtils {
	public static final Hashids HASH_ID = new Hashids("secret", 10);
	
  public static String encode(Long id) {
    return HASH_ID.encode(id);
  }

  public static Long decode(String id) {
    return Arrays.stream(HASH_ID.decode(id))
      .boxed()
        .findFirst()
          .orElse(null);
  }
}
