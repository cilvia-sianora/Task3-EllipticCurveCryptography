/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ECC;

/**
 * 
 * @author Cilvia
 */
public class ECCMain {
	/**
	 * @param args 
	 */
	public static void main(String[] args) {
		EllipticCurveCryptography ECC = new EllipticCurveCryptography();
		
		
		ECC.setPlaintext("ABC");
		ECC.init(-1,188,751,20);
		System.out.println("mau cari grup eliptik");
		ECC.findEllipticGroup();
		System.out.println("mau generate key");
		ECC.generateKey();
		ECC.encrypt();
		ECC.decrypt();
		System.out.println("-GET KEY FROM FILE-");
		ECC.getKeyFromFile("privateKey.txt","publicKey.txt");
		

	}
	
}
