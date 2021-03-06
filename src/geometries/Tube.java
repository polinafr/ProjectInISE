package geometries;
import primitives.*;

import java.util.List;

/**
 * Class Tube is a basic abstract representation of Tube Geometry, contains Radius and the Axe ray
 * radial geometrical objects
 * author@ Polina Frolov Korogodsky and Tselia Tebol
 */
public class Tube extends RadialGeometry {
    Ray axe;

    /**
     * Constructs a ray by radius, axe ray, emission color and material
     *
     * @param _radius
     * @param axe
     */
    public Tube(Color _emission, Material _mat, double _radius, Ray axe) {
        super(_emission, _mat, _radius);
        try{this.axe = axe;}
        catch (Exception ex)// if Ray's constructor has thrown exception <- if vector was null vector
        {
            throw ex;
        }
    }

    /**
     * Constructor with Color parameter
     * @param _emission
     * @param _radius
     * @param axe
     */
    public Tube(Color _emission, double _radius, Ray axe) {
        /*super(_emission, _radius);
        try{this.axe = axe;}
        catch (Exception ex)// if Ray's constructor has thrown exception <- if vector was null vector
        {
            throw ex;
        }*/
        this(_emission, new Material(0, 0,0), _radius, axe);
    }

    /**
     * Constructor without color and material
     * @param _radius
     * @param axe
     */
    public Tube(double _radius, Ray axe) {
        /*super(_radius);
        try{this.axe = axe;}
        catch (Exception ex)// if Ray's constructor has thrown exception <- if vector was null vector
        {
            throw ex;
        }*/
        this(Color.BLACK, new Material(0, 0,0), _radius, axe);
    }


    /**
     * returns null because by the exercise instructions should not yet be implemented; should return normal vector
     *
     * @param point
     * @return
     */
    @Override
    public Vector getNormal(Point3D point) {
        //Plane surface = new Plane(this.axe.getStart(), this.axe.getDirection());

/*            // Search of the orthogonal vector to the tube's axe
            Vector orthogonal;
            if (this.axe.getDirection().getEnd().getZ().get()!=0) {
                orthogonal = new Vector(1, 1, -(this.axe.getDirection().getEnd().getX().get()+this.axe.getDirection().getEnd().getY().get())/this.axe.getDirection().getEnd().getZ().get());
            }
            else if (this.axe.getDirection().getEnd().getY().get()!=0)
                orthogonal = new Vector(1, -(this.axe.getDirection().getEnd().getX().get()+this.axe.getDirection().getEnd().getZ().get())/this.axe.getDirection().getEnd().getY().get(),1);
            else -(this.axe.getDirection().getEnd().getZ().get()+this.axe.getDirection().getEnd().getY().get())/this.axe.getDirection().getEnd().getX().get());
            Plane tangential = new Plane(point, orthogonal);*/
        double straightDistance = this.axe.getDirection().dotProduct(point.subtract(this.axe.getStart()));
        Point3D intersectionPointOfOrthogonalWithRay = new Point3D(this.axe.getStart().add(this.axe.getDirection().scale(straightDistance)));
        try {
            if (intersectionPointOfOrthogonalWithRay.subtract(point).length() != this._radius)
                throw new IllegalArgumentException("The point is not on the object's surface");
        }catch (IllegalArgumentException ex){//if the points are the same
            throw new IllegalArgumentException("The point is not on the object's surface");
        }
        return point.subtract(intersectionPointOfOrthogonalWithRay).normalized();

    }



    /**
     * Returns axe ray
     * @return
     */
    public Ray getAxe() {
        return axe;
    }

    /**
     * returns a string description of the tube
     * @return
     */
    @Override
    public String toString() {
        return "Tube{" +
                "axe=" + axe +
                " " + super.toString() + "} ";
    }
    @Override
    public List<GeoPoint> findIntersections(Ray ray) {
        return null;
    }
}