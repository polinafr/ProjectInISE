package primitives;

/**
 * class Material is a basic representation of material defined by degrees of shininess and two attenuation coefficients
 * Authors: Polina Frolov and Tselia Tebol
 */
public class Material {
    private double _kD;
    private double _kS;
    private int _nShininess;
    private double kTransparency;
    private double kReflectance;

    /**
     * Constructs a new Material object by three received parameters
     * @param _kD
     * @param _kS
     * @param _nShininess
     */
    public Material(double _kD, double _kS, int _nShininess) {
        this(_kD, _kS, _nShininess, 0d, 0d);
    }

    /**
     * constructor that receives all 5 parameters for fields initializing
     * @param _kD
     * @param _kS
     * @param _nShininess
     * @param kTransparency
     * @param kReflectance
     */
    public Material(double _kD, double _kS, int _nShininess, double kTransparency, double kReflectance) {
        this._kD = _kD;
        this._kS = _kS;
        this._nShininess = _nShininess;
        this.kTransparency = kTransparency;
        this.kReflectance = kReflectance;
    }

    /**
     * Getter method for _kD field
     * @return double _kD
     */
    public double getKD() {
        return _kD;
    }
    /**
     * Getter method for _kS field
     * @return double _kS
     */
    public double getKS() {
        return _kS;
    }
    /**
     * Getter method for _nShininess field
     * @return int _nShininess
     */
    public int getNShininess() {
        return _nShininess;
    }

    /**
     * getter method for transparency coefficient field
     * @return
     */
    public double getKTransparency() {
        return kTransparency;
    }

    /**
     * getter method for reflectance coefficient field
     * @return
     */
    public double getKReflectance() {
        return kReflectance;
    }
}
