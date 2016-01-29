package com.zp.common;

/**
 * 
 * @author liu
 *
 */
public class UniqueStringGenerator

{

	private UniqueStringGenerator()

	{

	}

	public static synchronized String getUniqueString()

	{

		if (generateCount > 99999)

			generateCount = 0;

		String uniqueNumber = Long.toString(System.currentTimeMillis())
				+ Integer.toString(generateCount);

		generateCount++;

		return uniqueNumber;

	}

	private static final int MAX_GENERATE_COUNT = 99999;

	private static int generateCount = 0;

}