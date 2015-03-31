/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ECC;

/**
 *
 * @author Anggi
 */
public class ECCMain {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		EllipticCurveCryptography ECC = new EllipticCurveCryptography();
		
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Long.MAX_VALUE);
		
		ECC.setPlaintext("ABC");
		ECC.init();
		ECC.encrypt();
		
//		Point p = new Point();
//		p.setX(1342);
//		p.setY(104);
//		System.out.println(ECC.decode(p));
		
//		ECC.setPlaintext("ABC");
//		ECC.init();
//		ECC.encrypt();
		//System.out.println(ECC.findY(11));
		
//		char c = 'A';
//		long ascii = (long) c;
//		System.out.println(ascii);
		
		//System.out.println(Long.toHexString(2147483648));
		
	}
	
}
