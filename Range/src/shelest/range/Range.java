package shelest.range;
public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLengthRange() {
        return Math.abs(to - from);
    }

    public boolean isInside(double number) {
        return from < number & number < to;
    }

    public double[] getIntersectionRanges(double from1, double to1, double from2, double to2) {
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

    public double[] getUnionRange(double from1, double to1, double from2, double to2) {
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

    public double[] getDifferenceRange(double from1, double to1, double from2, double to2) {
        double[] resultIntersection = getIntersectionRanges(from1, to1, from2, to2);
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


