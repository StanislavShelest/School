package shelest.range.operations;

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

    public double getFrom() {
        return from;
    }

    public double getTo() {
        return to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return from <= number & number <= to;
    }

    public Range getIntersection(Range range) {
        double fromIntersection;
        double toIntersection;
        fromIntersection = Math.max(this.from, range.from);
        toIntersection = Math.min(this.to, range.to);
        if (fromIntersection >= toIntersection) {
            return null;
        }
        return new Range(fromIntersection, toIntersection);
    }

    public Range[] getUnion(Range range) {
        double fromUnion1;
        double toUnion1;
        double fromUnion2;
        double toUnion2;
        if (this.from <= range.from) {
            if (this.to >= range.to) {
                fromUnion1 = this.from;
                toUnion1 = this.to;
            } else {
                if (this.to >= range.from) {
                    fromUnion1 = this.from;
                    toUnion1 = range.to;
                } else {
                    fromUnion1 = this.from;
                    toUnion1 = this.to;
                    fromUnion2 = range.from;
                    toUnion2 = range.to;
                    Range resultUnion1 = new Range(fromUnion1, toUnion1);
                    Range resultUnion2 = new Range(fromUnion2, toUnion2);
                    return new Range[]{resultUnion1, resultUnion2};
                }
            }
        } else {
            if (range.to >= this.to) {
                fromUnion1 = range.from;
                toUnion1 = range.to;
            } else {
                if (range.to >= this.from) {
                    fromUnion1 = range.from;
                    toUnion1 = this.to;
                } else {
                    fromUnion1 = range.from;
                    toUnion1 = range.to;
                    fromUnion2 = this.from;
                    toUnion2 = this.to;
                    Range resultUnion1 = new Range(fromUnion1, toUnion1);
                    Range resultUnion2 = new Range(fromUnion2, toUnion2);
                    return new Range[]{resultUnion1, resultUnion2};
                }
            }
        }
        Range resultUnion1 = new Range(fromUnion1, toUnion1);
        return new Range[]{resultUnion1};

    }

    public Range[] getDifference(Range range) {
        double fromIntersection;
        double toIntersection;
        double fromDifference1 = 0;
        double toDifference1 = 0;
        double fromDifference2;
        double toDifference2;

        fromIntersection = Math.max(this.from, range.from);
        toIntersection = Math.min(this.to, range.to);

        if (fromIntersection >= toIntersection) {
            fromDifference1 = this.from;
            toDifference1 = this.to;
            Range resultDifference1 = new Range(fromDifference1, toDifference1);
            return new Range[]{resultDifference1};
        }

        if (this.from == fromIntersection & this.to == toIntersection){
            return new Range[0];
        }

        if (this.from < fromIntersection) {
            if (this.to < toIntersection) {
                fromDifference1 = this.from;
                toDifference1 = fromIntersection;
            } else {
                fromDifference1 = this.from;
                toDifference1 = fromIntersection;
                fromDifference2 = toIntersection;
                toDifference2 = this.to;
                Range resultDifference1 = new Range(fromDifference1, toDifference1);
                Range resultDifference2 = new Range(fromDifference2, toDifference2);
                return new Range[]{resultDifference1, resultDifference2};
            }
        } else {
            if (toIntersection < this.to) {
                fromDifference1 = toIntersection;
                toDifference1 = this.to;
            }
        }
        Range resultDifference1 = new Range(fromDifference1, toDifference1);
        return new Range[]{resultDifference1};
    }

}


