package ECC;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.Math;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Kelas yang melakukan enkripsi dan dekripsi berdasarkan metode Elliptic Curve Cryptography El Gamal
 * dengan encoding dan decoding antar string dan Point menggunakan metode Koblitz
 * @author Cilvia
 */
public class EllipticCurveCryptography {
	private String plaintext;
	private String ciphertext;
	
	private long k;	// angka random yang disepakati kedua pihak, k<=10
	private Point G;	// titik basis yang disepakati kedua pihak
	private long privateKeyA, privateKeyB;
	private Point publicKeyA, publicKeyB;
	private List<Point> ellipticGroup;
	// persamaan ellips: y^2=x^3+ax+b mod p
	private long a;
	private long b;
	private long p; // bilangan prima input user, p >= 2560
	
	EllipticCurveCryptography(){
		
		G = new Point();
		publicKeyA = new Point();
		publicKeyB = new Point();
		ciphertext = "";
		plaintext = "";
		ellipticGroup = new ArrayList<>();
	}
	
	public void setPlaintext(String s){
		plaintext = s;
	}
	
	public void setCiphertext(String s){
		ciphertext = s;
	}
	
	public String getPlaintext(){
		return plaintext;
	}
	
	public String getCiphertext(){
		return ciphertext;
	}
	
	public List<Point> getEllipticGroup(){
		return ellipticGroup;
	}
	
	/**
	 * Melakukan inisialisasi atribut k, a, b, p
	 * dengan ketentuan 4a^3+27b^2 != 0
	 * Prosedur ini harus dilakukan paling pertama 
	 */
	public void init(long a, long b, long p, long k){
		//this.a = -1;
		//this.b = 188;
		//p = 751;
		//this.k = 20;
		
		this.a = a; // input user
		this.b = b; // input user
		this.k = k; // input user
		this.p = p; // input user
		
		// G harusnya diambil dari grup eliptik (list point di ellips)
//		G.setX(50); 
//		G.setY(100);
		//G = ellipticGroup.get(4);
		
//		privateKeyA = 2;
//		privateKeyB = 3;
//		publicKeyA = G.multiplicate(privateKeyA, p);
//		publicKeyB = G.multiplicate(privateKeyB, p);
	}
	
	/**
	 * Mencari seluruh titik dalam kurva eliptik dan memasukkan ke list ellipticGroup
	 * Precondition: harus melakukan prosedur init() terlebih dahulu
	 */
	public void findEllipticGroup(){
		//System.out.println("p="+p);
		long y;
		Point pTemp = new Point();
		for(long x=0;x<p;x++){
			//System.out.println("x="+x);
			y = isExistY(x);
			if(y != -1){ // ada y yang memenuhi persamaan ellips
				pTemp.setX(x);
				pTemp.setY(y);
				ellipticGroup.add(pTemp);
				pTemp.setY(p-y);
				ellipticGroup.add(pTemp);
			}
		}
		System.out.println(ellipticGroup.size());
		G = ellipticGroup.get(4);
	}
	
	/**
	 * Melakukan assign ke titik basis G berdasarkan pilihan user dari dalam ellipticGroup
	 * Precondition: harus melakukan prosedur findEllipticGroup() terlebih dahulu
	 * @param x x titik basis G
	 * @param y y titik basis G
	 */
	public void setBasePoint(int x, int y){
		G.setX(x);
		G.setY(y);
	}
	
	/**
	 * Membangkitkan kunci publik dan kunci privat, serta menuliskannya ke file eksternal
	 * Precondition: harus melakukan prosedur init() terlebih dahulu
	 */
	public void generateKey(){
		Random random = new Random();
		long minRange = 0; // Batas bawah random
		long maxRange = ellipticGroup.size(); // Batas atas random
		
		// Membangkitkan private key
		privateKeyA = minRange + (long)(random.nextDouble()*(maxRange - minRange));
		privateKeyB = minRange + (long)(random.nextDouble()*(maxRange - minRange));
		
		// Membangkitkan public key
		publicKeyA = G.multiplicate(G,privateKeyA,a,p);
		publicKeyB = G.multiplicate(G,privateKeyB,a,p);
		
		// Tulis di file eksternal
		PrintWriter writer = null;
		try{
			// Menulis kunci privat
			writer = new PrintWriter("privateKey.txt", "UTF-8");
			writer.println(privateKeyA);
			writer.println(privateKeyB);
			writer.close();
			
			// Menulis kunci publik
			writer = new PrintWriter("publicKey.txt", "UTF-8");
			writer.println(publicKeyA);
			writer.println(publicKeyB);
			writer.close();
		} catch (Exception e){
		
		}
	}
	
	/**
	 * Mengambil key dari file eksternal
	 * @param privateLocation lokasi file dari private key
	 * @param publicLocation  lokasi file dari public key
	 */
	public void getKeyFromFile(String privateLocation, String publicLocation){
		Scanner scan = null;
		try{
			// Mengambil private key
			scan = new Scanner(new File(privateLocation));
			while(scan.hasNextLong()){
			   privateKeyA = scan.nextLong();
			   privateKeyB = scan.nextLong();
			}
			// Mengambil public key
			scan = new Scanner(new File(publicLocation));
			while(scan.hasNextLong()){
				System.out.println(scan.nextLong());
//			   publicKeyA.setX(scan.nextLong());
//			   publicKeyB = scan.nextLong();
			}
		} catch(Exception e){
		}
	}
	
	/**
	 * Mengecek apakah ada y yang sesuai persamaan ellips dengan x input
	 * @param x x input
	 * @return value y jika ada, -1 jika y tidak ada
	 */
	private long isExistY(long x){
		long temp = (long) Math.pow(x,3) + a * x + b;
		temp = temp % p;
		
		long y = 1;
		long yKuadrat;
		boolean found = false;
		// Mencari y^2 yang memenuhi persamaan kurva elips
		do{
			yKuadrat = (long)Math.pow(y,2);
			if((yKuadrat - temp) % p == 0){ 
				found = true;
			} else {
				y++;
			}
		}while(!found && yKuadrat < Integer.MAX_VALUE); // selama belum ketemu dan belum mencapai max
		
		if(!found)
			return -1;
		else
			return y;
	}
	
	/**
	 * Mengubah suatu huruf menjadi Point dengan metode Koblitz
	 * @param c huruf yang akan dirubah
	 * @return Point hasil rubahan
	 */
	private Point encode(char c){
		Point p = new Point();
		long ascii = (long) c;
		
		long i = 1;
		long x,y;
		
		// Mencari x dengan mengiterasi i pada x=mk+i yang memiliki y
		do{
			x = ascii * k + i;
			y = isExistY(x);
			i++;
		} while(y == -1); // selama y tidak memenuhi
		
		p.setX(x);
		p.setY(y);
		
		return p;
	}
	
	/**
	 * Mengubah Point kembali menjadi huruf dengan metode Koblitz
	 * @param q Point yang akan dirubah
	 * @return huruf hasil rubahan
	 */
	public char decode(Point q){
		long m = (q.getX() - 1) / k;
		return (char) m;
	}
	
	/**
	 * Melakukan enkripsi berdasarkan Elliptic Curve Cryptography El Gamal
	 */
	public void encrypt(){
		System.out.println("-ENCRYPT-");
		ciphertext = "";
		Point pm = new Point(); // Point plaintext hasil encoding
		Point[] cipherpoint = new Point[2]; // pasangan Point hasil enkripsi
		cipherpoint[0] = new Point();
		cipherpoint[1] = new Point();
		
		// Melakukan random untuk angka acak yang milih peng-enkrip
		long minRange = 0; // Batas bawah random
		long maxRange = p-1; // Batas atas random
		Random random = new Random();
		long ka = minRange + (long)(random.nextDouble()*(maxRange - minRange));
		
		// Melakukan enkripsi dengan pengulangan
		for(int i=0;i<plaintext.length();i++){
			// Encoding plaintext
			pm = encode(plaintext.charAt(i));
			
			// Melakukan enkripsi
			cipherpoint[0] = G.multiplicate(G,ka,a,p);
			cipherpoint[1] = pm.add(publicKeyB.multiplicate(pm,ka,a,p), p);
			
			// Memasukkan ke ciphertext
			if(i == 0){ // Point pertama selalu sama, jadi ditaruh sekali di awal ciphertext
				ciphertext += Hex.LongToHex(cipherpoint[0].getX());
				ciphertext += Hex.LongToHex(cipherpoint[0].getY());
			}
			ciphertext += Hex.LongToHex(cipherpoint[1].getX());
			ciphertext += Hex.LongToHex(cipherpoint[1].getY());	
		}
		System.out.println("ciphertext="+ciphertext);
	}
	
	
	/**
	 * Melakukan dekripsi berdasarkan Elliptic Curve Crytography El Gamal
	 */
	public void decrypt(){
		plaintext = "";
		
		Point p2 = new Point(); // Point kedua dalam pasangan cipherpoint
		Point pm = new Point(); // Point hasil dekripsi
		Point p1 = new Point(); // Point pertama dalam pasangan cipherpoint
		
		// Mengambil Point pertama cipherpoint dalam ciphertext hexadecimal
		p1.setX(Hex.HexToLong(ciphertext.substring(0,16)));
		p1.setY(Hex.HexToLong(ciphertext.substring(16,32)));
		ciphertext = ciphertext.substring(32);
		
		// Melakukan dekripsi selama ciphertext belum habis
		while(!ciphertext.isEmpty()){
			// Mengambil Point kedua cipherpoint dalam ciphertext hexadecimal
			p2.setX(Hex.HexToLong(ciphertext.substring(0,16)));
			p2.setY(Hex.HexToLong(ciphertext.substring(16,32)));
			ciphertext = ciphertext.substring(32);
			
			// Melakukan perhitungan dekripsi  
			pm = p2.substract(p1.multiplicate(p1,privateKeyB,a,p), p);
			
			// Men-decode Point hasil dekripsi
			plaintext += decode(pm);
		}
		System.out.println(plaintext);
	}
	
}
