package str;

public class Sem {
    public String stringUtilsNoPreCheck( String arg ) {
        char[] chars = arg.toCharArray();

        int srcPos = 0;
        int destPos = 0;
        int length = 0;
        boolean newSegment = false;
        for (int i = 0; i < chars.length; ++i) {
            if (chars[i] != '1') {
                if (!newSegment) {
                    srcPos = i;
                }
                newSegment = true;
                ++length;
            } else {
                if (newSegment) {
                    System.arraycopy(chars, srcPos, chars, destPos, length);
                    destPos += length;
                    length = 0;
                    newSegment = false;
                }
            }
        }

        if (newSegment && arg.length() == length) {
            return "";
        }

        if (newSegment) {
            System.arraycopy(chars, srcPos, chars, destPos, length);
            return new String(chars, 0, destPos + length);
        } else {
            return new String(chars, 0, destPos);
        }
    }

    public String stringUtilsNoPreCheck2( String arg ) {
        char[] chars = arg.toCharArray();

        int length = chars.length;
        int rem = 0;
        for (int i = 0; i < length; i++) {
            if (chars[i] == '1') {
                int j = i+1;
                int newi = i;
                ++rem;
                boolean done = false;
                for (; j < length; j++) {
                    if (chars[j] == '1') {
                        newi = j;
                        System.arraycopy(chars, i+1, chars, i, j-i);
                        ++rem;
                        done = true;
                        break;
                    }
                }
                if (!done) {
                    System.arraycopy(chars, i+1, chars, i, length-i-1);
                    break;
                }
                i = newi;
            }
        }
       return new String(chars, 0, length-rem);
    }
}
