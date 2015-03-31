package ECC;

import static java.lang.Math.abs;
import static java.lang.Math.pow;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cilvia
 */
public class Point {
	private long x; // posisi x
	private long y; // posisi y
    private long p; // modulo
	
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

	public void setP(long p) {
		this.p = p;
	}

	public long getP() {
		return p;
	}

	private long Gradient(Point Q, String option){
		long temp = 0; boolean found = false; long val_1; long val_2 = 0;
		switch(option){
			case "+" : 
				val_1 = abs(Q.y - y);
				val_2 = abs(Q.x - x);
				for (int i = 0; i < p; i++) {
					if (!found){
						if (((val_2*i)-1) % p == 0){
							val_2 = i;
							found = true;
						}
					}
				}
				temp = (val_1*val_2) % p;
				break;
			case "-" :
				val_1 = y + Q.y;
				val_2 = x + Q.x;
				for (int i = 0; i < p; i++) {
					if (!found){
						if (((val_2*i)-1) % p == 0){
							val_2 = i;
							found = true;
						}
					}
				}
				temp = (val_1*val_2) % p;
				break;
		}
		System.out.println(val_2);
		return temp;
	}

	private long Gradient (long A){
		boolean found = false; long temp;
		long val_1 = 3*(long)pow(x,2)+A;
		long val_2 = 2*y;
		for (int i = 0; i < p; i++) {
			if (!found){
				if (((val_2*i)-1) % p == 0){
					val_2 = i;
					found = true;
				}
			}
		}
		System.out.println(p);
		temp = (val_1*val_2) % p;
		return temp;
	}
        
	/**
	 * Melakukan operasi pengurangan dengan Point lain
	 * Menggunakan rumus yang ada dalam slide kuliah (P - Q = R)
	 * @param q Point yang mengurangi
	 * @return Point hasil pengurangan
	 */
	public Point substract(Point q, long pInput){
		p = pInput;
		Point R = new Point();
		long lambda = Gradient(q, "-");
		R.x = ((long) pow(lambda,2.0) - x + q.x) % p; while (R.x<0) R.x += p;
		R.y = (lambda*(p-R.x) - y) % p; while (R.y<0) R.y += p;
		R.p = p;
		return R;
	}
	
	/**
	 * Melakukan operasi penjumlahan dengan Point lain
	 * Menggunakan rumus yang ada dalam slide kuliah (P + Q = R)
	 * @param q Point yang menambahkan
	 * @return Point hasil penjumlahan
	 */
	public Point add(Point q, long pInput){
		p = pInput;
		Point R = new Point();
		long lambda = Gradient(q, "+");
		System.out.println("lambda:" + lambda);
		R.x = ((long) pow(lambda,2) - x - q.x) % p; while (R.x<0) R.x += p;
		R.y = (lambda*(x-R.x) - y) % p; while (R.y<0) R.y += p;
		R.p = p;
		return R;
	}
	
	/**
	 * Melakukan penggandaan terhadap Point
	 * Menggunakan rumus yang ada dalam slide kuliah (2P = P + P = R)
     * @param A
	 * @param pInput
	 * @return hasil penggandaan
	 */
	public Point doublePoint(long A,long pInput){
		p = pInput;
		Point R = new Point();
		long lambda = Gradient(A);
		System.out.println("asdas " + lambda);

		R.x = ((long) pow(lambda,2) - 2*x) % p; if (R.x<0) R.x += p;
		R.y = (lambda*(x-R.x) - y) % p; if (R.y<0) R.y += p;
		R.p = p;
		System.out.println("double x: " + R.x);
		System.out.println("double y: " + R.y);
		return R;
	}
        
	/**
	 * Melakukan perkalian dengan cara penjumlahan point sebanyak k kali
	 * Menggunakan rumus yang ada dalam slide kuliah (kP = R)
		* @param R
		* @param i
		* @param A
		* @param pInput
	 * @return Point hasil perkalian
	 */
	public Point multiplicate(Point R, long i, long A, long pInput){
		p = pInput;
		Point r = new Point();
		r.p = R.p; r.x = R.x; r.y = R.y;
		boolean stop = false; int count_temp = 0;
		long k = i;
		if (i==1){
			return R;
		}
		while(!stop){
			if (k%2!=0){
				count_temp++;
			}
			k /= 2;
			R = R.doublePoint(A,p);
			System.out.println("oi: " + R.x);
			System.out.println("oi: " + R.y);
			if (k==1 || k==0){
				System.out.println("count_temp " + count_temp);
				stop = true;
			}
		}
		if (count_temp==0){
			return R;
		} else{
			System.out.println("last: " + R.x);
			System.out.println("last: " + R.y);
			for (int j = 0; j < count_temp; j++) {
				R = R.add(r,p);
			}
			return R;
		}
	}
	
	public String toString(){
		return "("+x+","+y+")";
	}

}
