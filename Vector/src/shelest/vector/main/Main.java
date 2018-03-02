package shelest.vector.main;

import shelest.vector.operations.Vector;

public class Main {
    public static void main(String[] args) {
        double[] proba3 = {12, 23, 12, 11, 45, 67, 765};
        double[] proba4 = {5, 7, 8, 5, 12};
        Vector vector3 = new Vector(proba3);
        Vector vector4 = new Vector(proba4);

        double scalar = 2;

        System.out.println(vector3.toString());
        //System.out.println(vector4.toString());

        //System.out.println(vector3.getAddition(vector4).toString());
        //System.out.println(vector3.getSubtraction(vector4).toString());
        //System.out.println(vector3.getMultiplicationByScalar(scalar).toString());
        //System.out.println(vector3.getTurn().toString());
        //System.out.println(vector3.getLength());
        System.out.println(vector3.getInstallationComponent(2,654));
    }
}
