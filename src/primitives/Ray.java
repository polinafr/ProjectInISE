package primitives;
//import primitives.Point3D;
//import primitives.Vector;
import java.lang.Math;

/**
 * class Ray is basic representation of ray primitive
 * authors pollina Frolov Korogodsky and Tselia Tebol
 */
public class Ray {
    private Point3D start;
    private Vector direction;

    /**
     * constructs a ray by point an normalized vector
     * @param start point
     * @param direction vector
     */
    public Ray(Point3D start, Vector direction) {
        this.start = start;
        double lengthOfVector = Math.sqrt(Math.pow((direction.getEnd().getX()._coord), 2)+Math.pow((direction.getEnd().getY()._coord), 2)+Math.pow((direction.getEnd().getZ()._coord), 2));
        this.direction = direction;
        if(lengthOfVector!=1) {
            this.direction.normalize();//normalization
        }

    }

    /**
     * function returns start point
     * @return Point3D start
     */
    public Point3D getStart() {
        return start;
    }

    /**
     * function returns direction vector
     * @return direction vector
     */
    public Vector getDirection() {
        return direction;
    }

    /**
     * function makes string that describes a ray
     * @return string
     */
    @Override
    public String toString() {
        return "Ray:" +
                "start:" + start +
                ", direction:" + direction;
    }

    /**
     * compares between an object and current ray
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ray)) return false;

        Ray ray = (Ray) o;
        if (this.direction.equals(ray.direction)&&this.start.equals(ray.start))
            return true;
        return  false;
    }

    /**
     * function that returns the point on the ray on the distance t from the start of the ray
     * @param t
     * @return Point3D
     */
    public Point3D getPoint(double t){

        if (t>0)
            return new Point3D(start.add(direction.scale(t)));
        if(t==0)
            return this.start;
        else throw new IllegalArgumentException("The point is outside the ray");
    }

    /*/**
     * function checks whether some point belongs to the ray by checking whether the Vector from this point is opposite to the direction vector
     * Made to make polygon constructor checks easier (To check whether Three points are on the same ray
     * @param point
     * @return
     */
 /*   public boolean containsPoint3D(Point3D point){
        Vector fromPointToStartPoint = new Vector(point.subtract(start));
        if(!(fromPointToStartPoint.normalize().equals(direction.scale(-1))))
            return false;
        return true;
    }*/

   /* //**
     * Function checks whether afret some point two rays will coincide
     * @param other
     * @return
     //*/
/*    public boolean coincide(Ray other){
        if (this.direction!=other.direction||this.direction!=other.direction.scale(-1))
            return false;

    }*/

    /**
     * Constructs a ray that is moved a little bit from the received point
     * @param point
     * @param direction
     * @param n
     */
   public Ray(Point3D point, Vector direction, Vector n) {
       //point + normal.scale(±DELTA)
       direction = new Vector(direction);// no need to normalize the vector - if it's a ray's direction, it's already normalized

       double nDotV = n.dotProduct(direction);
        
       Vector normalDelta = n.scale((nDotV > 0 ? 0.1 : -0.1));
       this.start = point.add(normalDelta);
   }

}
