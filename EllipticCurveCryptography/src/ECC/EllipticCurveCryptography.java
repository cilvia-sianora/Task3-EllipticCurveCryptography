package ECC;

import java.lang.Math;
import java.util.Random;
/**
 *
 * @author Cilvia
 */
public class EllipticCurveCryptography {
	private String plaintext;
	private String ciphertext;
	private Point[] cipherpoint;
	private long k;	// angka random yang disepakati kedua pihak
	private Point G;	// titik basis yang disepakati kedua pihak
	private long privateKeyA, privateKeyB;
	private Point publicKeyA, publicKeyB;
	// persamaan ellips: y^2=x^3+ax+b mod p
	private long a;
	private long b;
	private long p;
	
	EllipticCurveCryptography(){
		cipherpoint = new Point[2];
		cipherpoint[0] = new Point();
		cipherpoint[1] = new Point();
		G = new Point();
		publicKeyA = new Point();
		publicKeyB = new Point();
		ciphertext = "";
		plaintext = "";
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
	
	/**
	 * Melakukan inisialisasi atribut G, k, a, b, p
	 * dengan ketentuan 4a^3+27b^2 != 0
	 */
	public void init(){
		a = -1;
		b = 188;
		p = 751;
		k = 20;
		
		// G harusnya diambil dari grup eliptik (list point di ellips)
		G.setX(50); 
		G.setY(100);
		
		privateKeyA = 2;
		privateKeyB = 3;
		publicKeyA = G.multiplicate(privateKeyA, p);
		publicKeyB = G.multiplicate(privateKeyB, p);
		System.out.println("publicKeyA="+publicKeyA);
		System.out.println("publicKeyB="+publicKeyB);
	}
	
	public void readKeyFromFile(){
	
	}
	
	/**
	 * Mencari y yang cocok untuk suatu x dalam metode Koblitz
	 * x = m . k + i, dengan mengiterasi i dari 1
	 * @param m integer representasi dari char plaintext
	 * @return hasil y yang cocok
	 */
	private long findY(long m){
		int i = 1;
		long x,y;
		do{
			x = m * k + i;
			y = isExistY(x);
			i++;
		} while(y == -1);
		return y;
	}
	
	/**
	 * Mengecek apakah ada y yang sesuai persamaan ellips dengan x input
	 * @param x x input
	 * @return value y jika ada, -1 jika y tidak ada
	 */
	private long isExistY(long x){
		//System.out.println("-isExistY-");
		long temp = (long) Math.pow(x,3) + a * x + b;
		temp = temp % p;
		
		long y = 1;
		long yKuadrat;
		boolean found = false;
		do{
			yKuadrat = (long)Math.pow(y,2);
			if((yKuadrat - temp) % p == 0){
				found = true;
			} else {
				y++;
			}
		}while(!found && yKuadrat < Integer.MAX_VALUE);
		
		if(!found)
			return -1;
		else
			return y;
	}
	
	/**
	 * Mengecek apakah sebuah bilangan merupakan kuadrat sempurna atau tidak
	 * @param num bilangan kuadrat yang akan dicek
	 * @return hasil akar kuadrat jika num kuadrat sempurna,
	 *			-1 jika num bukan kuadrat sempurna
	 */
	private long isPerfectSquare(long num){
		//System.out.println("-isPerfectSquare-");
		double sqr = Math.sqrt(num);
		double x = sqr - Math.floor(sqr);
		if(x>0){
			return -1;
		} else{
			return (long)sqr;
		}
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
		do{
			x = ascii * k + i;
			y = isExistY(x);
			i++;
		} while(y == -1);
		
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
	
	public void encrypt(){
		// TODO: 
		// Inisialisasi ka (angka random yang dibangkitan pihak pengenkrip)
		Random rand = new Random();
		long ka = rand.nextInt();
		System.out.println("ka="+ka);
		// Inisialisasi privateKeyA (kunci privat na pihak pengenkrip)
		
		String plain = "";
		Point pm = new Point();
		// Melakukan enkripsi dengan pengulangan
		for(int i=0;i<plaintext.length();i++){
			pm = encode(plaintext.charAt(i));
			System.out.println(pm.toString());
			cipherpoint[0] = G.multiplicate(ka, p);
			cipherpoint[1] = pm.add(publicKeyB.multiplicate(ka, p), p);
			//ciphertext += cipherpointToHexa();
			System.out.print(cipherpoint[0].toString());
			System.out.println(cipherpoint[1].toString());
			
			/* DECRYPT TEMPORARY */
			pm = cipherpoint[1].substract(cipherpoint[0].multiplicate(privateKeyB, p), p);
			plain += decode(pm);
		}
		System.out.println(plain);
	}
	
	
	
	public void decrypt(){
		// TODO:
		// Inisialisasi nb (kunci privat pihak pendekrip)
		
		// Melakukan dekripsi dengan pengulangan
	}
	
	private String cipherpointToHexa(){
		String result = "";
		return "";
	}
}
