package shelest.range;
public class Range {
    private double from1;
    private double to1;
    private double from2;
    private double to2;

    public Range(double from1, double to1, double from2, double to2) {
        this.from1 = from1;
        this.to1 = to1;
        this.from2 = from2;
        this.to2 = to2;
    }

    public void setFrom1(double from1) {
        this.from1 = from1;
    }

    public void setTo1(double to1) {
        this.to1 = to1;
    }

    public void setFrom2(double from2) {
        this.from2 = from2;
    }

    public void setTo2(double to2) {
        this.to2 = to2;
    }

    public double getLengthRange() {
        return Math.abs(to1 - from1);
    }

    public boolean isInside(double number) {
        return from1 < number & number < to1;
    }

    public double[] getIntersectionRanges() {
        double[] resultIntersection = new double[2];
        if (from1 > from2) {
            resultIntersection[0] = from1;
        } else {
            resultIntersection[0] = from2;
        }

        if (to1 < to2) {
            resultIntersection[1] = to1;
        } else {
            resultIntersection[1] = to2;
        }

        if (resultIntersection[0] > resultIntersection[1]) {
            return null;

        }
        return resultIntersection;
    }

    public double[] getUnionRange() {
        double[] resultUnion = new double[4];
        if (from1 <= from2) {
            if (to1 >= to2) {
                resultUnion[0] = from1;
                resultUnion[1] = to1;
            } else {
                if (to1 >= from2) {
                    resultUnion[0] = from1;
                    resultUnion[1] = to2;
                } else {
                    resultUnion[0] = from1;
                    resultUnion[1] = to1;
                    resultUnion[2] = from2;
                    resultUnion[3] = to2;
                }
            }
        } else {
            if (to2 >= to1) {
                resultUnion[0] = from2;
                resultUnion[1] = to2;
            } else {
                if (to2 >= from1) {
                    resultUnion[0] = from2;
                    resultUnion[1] = to1;
                } else {
                    resultUnion[0] = from2;
                    resultUnion[1] = to2;
                    resultUnion[2] = from1;
                    resultUnion[3] = to1;
                }
            }
        }
        return resultUnion;
    }

    public double[] getDifferenceRange() {
        double[] resultIntersection = getIntersectionRanges();
        double[] resultDifference = {from1, to1};
        if (resultIntersection == null) {
            return resultDifference;
        } else {
            if (resultIntersection[0] > resultDifference[0]) {
                resultDifference[1] = resultIntersection[0];
            } else {
                resultDifference[0] = resultIntersection[1];
            }
        }
        return resultDifference;
    }
}


