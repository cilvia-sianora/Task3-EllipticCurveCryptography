package ECC;

/**
 *
 * @author Cilvia
 */
public class EllipticCurveCryptography {
	private String plaintext;
	private String ciphertext;
	private int k;	// angka random yang disepakati kedua pihak
	private Point G;	// titik basis yang disepakati kedua pihak
	// persamaan ellips: y^2=x^3+ax+b mod p
	private int a;
	private int b;
	private int p;
	
	EllipticCurveCryptography(){
	
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
	 */
	private void init(){
	
	}
	
	/**
	 * Mencari y yang cocok untuk suatu x dalam metode ...
	 * x = m . k + i, dengan mengiterasi i dari 1
	 * @param m integer representasi dari char plaintext
	 * @return hasil y yang cocok
	 */
	private int findY(int m){
		
		return 0;
	}
	
	/**
	 * Mengecek apakah ada y yang sesuai persamaan ellips dengan x input
	 * @param x x input
	 * @return value y jika ada, -1 jika y tidak ada
	 */
	private int isExistY(int x){
		
		return -1;
	}
	
	/**
	 * Mengubah suatu huruf menjadi Point dengan metode ...
	 * @param c huruf yang akan dirubah
	 * @return Point hasil rubahan
	 */
	private Point encode(char c){
		
		return null;
	}
	
	/**
	 * Mengubah Point kembali menjadi huruf dengan metode ...
	 * @param q Point yang akan dirubah
	 * @return huruf hasil rubahan
	 */
	private char decode(Point q){
	
		return '0';
	}
	
	public void encrypt(){
		// TODO: 
		// Inisialisasi ka (angka random yang dibangkitan pihak pengenkrip)
		// Inisialisasi na (kunci privat pihak pengenkrip)
		
		// Melakukan enkripsi dengan pengulangan
	}
	
	public void decrypt(){
		// TODO:
		// Inisialisasi nb (kunci privat pihak pendekrip)
		
		// Melakukan dekripsi dengan pengulangan
	}
}
