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

  public static long HexToLong(String hexadecimal){
    char[] chars;
    char c;
    long value;
    int i;
    byte b;

    if (hexadecimal == null)
      throw new IllegalArgumentException();

    chars = hexadecimal.toUpperCase().toCharArray();
    if (chars.length != 16){
        System.err.println("Fail!");
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
        System.err.println("Invalid hex character: " + c);
      }
    }

    return value;
  }

  public static String LongToHex(long value) {
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
}
