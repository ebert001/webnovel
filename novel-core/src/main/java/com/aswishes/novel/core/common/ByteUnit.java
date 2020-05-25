package com.aswishes.novel.core.common;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

public enum ByteUnit {
	B(ByteUnit.ONE_B, "B"),
	KB(ByteUnit.ONE_KB, "KB"),
	MB(ByteUnit.ONE_MB, "MB"),
	GB(ByteUnit.ONE_GB, "GB"),
	TB(ByteUnit.ONE_TB, "TB"),
	PB(ByteUnit.ONE_PB, "PB"),
	EB(ByteUnit.ONE_EB, "EB"),
	;

	private long size;
	private String unit;
	private ByteUnit(long size, String unit) {
		this.size = size;
		this.unit = unit;
	}
	/**
     * The number of bytes in a kilobyte.
     */
    private static final long ONE_B = 1;
	/**
     * The number of bytes in a kilobyte.
     */
    private static final long ONE_KB = ONE_B << 10;
    /**
     * The number of bytes in a megabyte.
     */
    private static final long ONE_MB = ONE_KB << 10;
    /**
     * The number of bytes in a gigabyte.
     */
    private static final long ONE_GB = ONE_MB << 10;
    /**
     * The number of bytes in a terabyte.
     */
    private static final long ONE_TB = ONE_GB << 10;
    /**
     * The number of bytes in a petabyte.
     */
    private static final long ONE_PB = ONE_TB << 10;
    /**
     * The number of bytes in an exabyte.
     */
    private static final long ONE_EB = ONE_PB << 10;

    public long toBytes() {
    	return size;
    }

    public long toBytes(double num) {
    	return (long) (size * num);
    }

	public static double convert(double num, ByteUnit unit, ByteUnit tunit) {
		return num * unit.size / tunit.size;
	}

	public static long convert(double num, ByteUnit unit) {
		return (long) (num * unit.size);
	}

    public String display(long num) {
    	return display(num, 2);
    }

    public String display(long num, int scale) {
		return String.valueOf(divide(num, size, scale)) + " " + unit;
	}

    public static String display(long num, ByteUnit unit) {
    	return display(num, unit, 2);
    }

	public static String display(long num, ByteUnit unit, int scale) {
		return unit.display(num, scale);
	}

	public static String autoDisplay(long size) {
		return autoDisplay(size, 2);
	}

	public static String autoDisplay(long size, int scale) {
        if (size >= ONE_EB) {
            return ByteUnit.EB.display(size, scale);
        } else if (size >= ONE_PB) {
            return ByteUnit.PB.display(size, scale);
        } else if (size >= ONE_TB) {
            return ByteUnit.TB.display(size, scale);
        } else if (size >= ONE_GB) {
            return ByteUnit.GB.display(size, scale);
        } else if (size >= ONE_MB) {
            return ByteUnit.MB.display(size, scale);
        } else if (size >= ONE_KB) {
            return ByteUnit.KB.display(size, scale);
        } else {
            return ByteUnit.B.display(size, scale);
        }
    }

	private static BigDecimal divide(long a, long b, int scale) {
		return new BigDecimal(a).divide(new BigDecimal(b), scale, RoundingMode.DOWN);
	}
	
	public static ByteUnit toUnit(String unit) {
		if (unit.equals("B")) {
			return ByteUnit.B;
		}
		if (unit.equals("KB")) {
			return ByteUnit.KB;
		}
		if (unit.equals("MB")) {
			return ByteUnit.MB;
		}
		if (unit.equals("GB")) {
			return ByteUnit.GB;
		}
		if (unit.equals("TB")) {
			return ByteUnit.TB;
		}
		if (unit.equals("PB")) {
			return ByteUnit.PB;
		}
		if (unit.equals("EB")) {
			return ByteUnit.EB;
		}
		throw new UnsupportedOperationException("Unsupported unit. " + unit);
	}
	
	/**
	 * 1 byte (B) = 8 bit
	 * 1 KB = 1024 byte 
	 * @param display 1KB 1Kb 1MB 1Mb 1GB 1Gb
	 * @return bytes
	 */
	public static long toBytes(String display) {
		display = display.trim();
		if (display.isEmpty()) {
			throw new IllegalArgumentException("Bad format with diaplay argument.");
		}
		String[] ends = {"B", "KB", "MB", "GB", "TB", "PB", "EB"};
		if (!StringUtils.endsWithAny(display, ends)) {
			throw new IllegalArgumentException("Bad format with suffix. Should be: " + Arrays.asList(ends));
		}
		char[] cs = display.toCharArray();
		int offset = 0;
		for (int i = 0; i < cs.length; i++) {
			offset = i;
			if (Character.isDigit(cs[i]) || cs[i] == '.') {
				continue;
			}
			break;
		}
		String numStr = new String(cs, 0, offset);
		String unitStr = new String(cs, offset, cs.length - offset);
		return new BigDecimal(numStr.trim()).multiply(new BigDecimal(toUnit(unitStr.trim()).size)).longValue();
	}
}
