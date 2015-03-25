package ECC;

/**
 *
 * @author Cilvia
 */
public class Point {
	private int x;
	private int y;
	
	Point(){
		x = 0;
		y = 0;
	}
	
	private void setX(int x){
		this.x = x;
	}
	
	private void setY(int y){
		this.y = y;
	}
	
	private int getY(){
		return y;
	}
	
	private int getX(){
		return x;
	}
	
	/**
	 * Melakukan operasi pengurangan dengan Point lain
	 * Menggunakan rumus yang ada dalam slide kuliah (P + Q = R)
	 * @param q Point yang mengurangi
	 * @param p angka modulo
	 * @return Point hasil pengurangan
	 */
	public Point substract(Point q, int p){
		
		return null;
	}
	
	/**
	 * Melakukan operasi penjumlahan dengan Point lain
	 * Menggunakan rumus yang ada dalam slide kuliah (P - Q = R)
	 * @param q Point yang menambahkan
	 * @param p angka modulo
	 * @return Point hasil penjumlahan
	 */
	public Point add(Point q, int p){
		
		return null;
	}
	
	/**
	 * Melakukan penggandaan terhadap Point
	 * Menggunakan rumus yang ada dalam slide kuliah (2P = P + P = R)
	 * @param p angka modulo
	 * @return hasil penggandaan
	 */
	public Point doublePoint(int p){
		
		return null;
	}
	
	/**
	 * Melakukan perkalian dengan cara penjumlahan point sebanyak k kali
	 * Menggunakan rumus yang ada dalam slide kuliah (kP = R)
	 * @param k angka pengali
	 * @param p angka modulo
	 * @return Point hasil perkalian
	 */
	public Point multiplicate(int k, int p){
		
		return null;
	}
}
