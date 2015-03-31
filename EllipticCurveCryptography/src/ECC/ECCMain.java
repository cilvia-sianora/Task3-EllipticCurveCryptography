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
            Point p = new Point();
            Point q = new Point();
            Point r = new Point();
            p.setP(11); 
            p.setX(2); q.setX(5);
            p.setY(4); q.setY(9);
            
//            r = p.add(q);
//            System.out.println(r.getX() + " " + r.getY());
//            r = p.doublePoint(1);
//            System.out.println(r.getX() + " " + r.getY());
            r = p.multiplicate(p,5,1);
            System.out.println(r.getX() + " " + r.getY());
	}
	
}
