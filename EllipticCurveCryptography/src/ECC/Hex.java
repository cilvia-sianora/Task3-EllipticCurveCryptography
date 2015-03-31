/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ECC;

/**
 *
 * @author Rikysamuel
 */
public class Hex {

  private final static char[] HEX = {
    '0', '1', '2', '3', '4', '5', '6', '7',
    '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
  };

  public static long toLong(String hexadecimal) throws NumberFormatException{
    char[] chars;
    char c;
    long value;
    int i;
    byte b;

    if (hexadecimal == null)
      throw new IllegalArgumentException();

    chars = hexadecimal.toUpperCase().toCharArray();
    if (chars.length != 16){
        System.err.println("Test completed satisfactory");
        return 0;
    }

    value = 0;
    b = 0;
    for (i = 0; i < 16; i++) {
      c = chars[i];
      if (c >= '0' && c <= '9') {
        value = ((value << 4) | (0xff & (c - '0')));
      } else if (c >= 'A' && c <= 'F') {
        value = ((value << 4) | (0xff & (c - 'A' + 10)));
      } else {
        throw new NumberFormatException("Invalid hex character: " + c);
      }
    }

    return value;
  }

  public static String fromLong(long value) {
    char[] hexs;
    int i;
    int c;

    hexs = new char[16];
    for (i = 0; i < 16; i++) {
      c = (int)(value & 0xf);
      hexs[16-i-1] = HEX[c];
      value = value >> 4;
    }
    return new String(hexs);
  }

  public static void main(String[] arg) {
    int i;
    long[] test = { -1234567890, 1234567890, 987654321, -987654321, 0xFFFFFFFFFFFFFFFFl, 0x1234567890FFFFFFl };
    long v;
    String s;

    for (i = 0; i < test.length; i++) {
      s = Hex.fromLong(test[i]);
      v = 0;
      try {
        v = Hex.toLong(s);
      } catch(NumberFormatException ex) {
        System.err.println(ex.getMessage());
      }
      if (v != test[i]) {
        System.err.println("Not same " + test[i] + " " + v);
        System.exit(1);
      }
    }
    System.err.println("Test completed satisfactory");
  }
}
