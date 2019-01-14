public class DataConverter {

    final static char[] hexCode = "0123456789ABCDEF".toCharArray();

    public static String[] binaryToHex(String[] bin) {
        String[] result = new String[bin.length];
        for (int i = 0; i < bin.length; i++) {
            result[i] = Integer.toHexString(Integer.parseInt(bin[i], 2));
        }
        //return Integer.toHexString(Integer.parseInt(bin[0], 2));
        return result;
    }

    public static byte[] parseHexBinary(String s) {
        int len = s.length();
        if (len % 2 != 0) {
            throw new IllegalArgumentException("hexBinary needs to be even-length: " + s);
        } else {
            byte[] out = new byte[len / 2];

            for(int i = 0; i < len; i += 2) {
                int h = hexToBin(s.charAt(i));
                int l = hexToBin(s.charAt(i + 1));
                if (h == -1 || l == -1) {
                    throw new IllegalArgumentException("contains illegal character for hexBinary: " + s);
                }

                out[i / 2] = (byte)(h * 16 + l);
            }

            return out;
        }
    }

    private static int hexToBin(char ch) {
        if ('0' <= ch && ch <= '9') {
            return ch - 48;
        } else if ('A' <= ch && ch <= 'F') {
            return ch - 65 + 10;
        } else {
            return 'a' <= ch && ch <= 'f' ? ch - 97 + 10 : -1;
        }
    }

    public static String printHexBinary(byte[] data) {
        StringBuilder r = new StringBuilder(data.length * 2);
        byte[] arr$ = data;
        int len$ = data.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            byte b = arr$[i$];
            r.append(hexCode[b >> 4 & 15]);
            r.append(hexCode[b & 15]);
        }

        return r.toString();
    }
}
