package ECC;

/**
 *
 * @author Cilvia
 */
public class Point {
	private long x;
	private long y;
	
	Point(){
		x = 0;
		y = 0;
	}
	
	public void setX(long x){
		this.x = x;
	}
	
	public void setY(long y){
		this.y = y;
	}
	
	public long getY(){
		return y;
	}
	
	public long getX(){
		return x;
	}
	
	/**
	 * Melakukan operasi pengurangan dengan Point lain
	 * Menggunakan rumus yang ada dalam slide kuliah (P - Q = R)
	 * @param q Point yang mengurangi
	 * @param p angka modulo
	 * @return Point hasil pengurangan
	 */
	public Point substract(Point q, long p){
		
		Point point = new Point();
		point.setX(x-q.getX());
		point.setY(y-q.getY());
		return point;
	}
	
	/**
	 * Melakukan operasi penjumlahan dengan Point lain
	 * Menggunakan rumus yang ada dalam slide kuliah (P + Q = R)
	 * @param q Point yang menambahkan
	 * @param p angka modulo
	 * @return Point hasil penjumlahan
	 */
	public Point add(Point q, long p){
		
		Point point = new Point();
		point.setX(x+q.getX());
		point.setY(y+q.getY());
		return point;
	}
	
	/**
	 * Melakukan penggandaan terhadap Point
	 * Menggunakan rumus yang ada dalam slide kuliah (2P = P + P = R)
	 * @param p angka modulo
	 * @return hasil penggandaan
	 */
	public Point doublePoint(long p){
		
		Point point = new Point();
		point.setX(2*x);
		point.setY(2*y);
		return point;
	}
	
	/**
	 * Melakukan perkalian dengan cara penjumlahan point sebanyak k kali
	 * Menggunakan rumus yang ada dalam slide kuliah (kP = R)
	 * @param k angka pengali
	 * @param p angka modulo
	 * @return Point hasil perkalian
	 */
	public Point multiplicate(long k, long p){
		
		Point point = new Point();
		point.setX(x*k);
		point.setY(y*k);
		return point;
	}
	
	public String toString(){
		return "("+x+","+y+")";
	}
}
