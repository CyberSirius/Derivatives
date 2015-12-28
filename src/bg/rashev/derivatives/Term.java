package bg.rashev.derivatives;


public class Term {

    private int coefficient;
    private int power;

    public Term(int coefficient, int power) {
        this.coefficient = coefficient;
        this.power = power;
    }

    public Term(String term) {
        boolean hasCoefficient = term.contains("*");//has "*" symbol in term(bad name)
        boolean hasPower = term.contains("^");//has "^" symbol in term(bad name)
        boolean hasX = term.contains("x");
        if (!hasCoefficient && !hasPower && !hasX) { // term is c
            this.setCoefficient(Integer.parseInt(term));
            this.setPower(0);
        } else if (hasX) {
            if (!hasCoefficient && !hasPower) { //term is x
                this.setCoefficient(1);
                this.setPower(1);
            } else if (!hasCoefficient) { //term is x^n
                this.setCoefficient(1);
                this.setPower(Integer.parseInt(term.split("\\^")[1]));
            } else if (!hasPower) { //term is c*x
                this.setCoefficient(Integer.parseInt(term.split("\\*")[0]));
                this.setPower(1);
            } else { //term is c*x^n
                this.setCoefficient(Integer.parseInt(term.split("\\*")[0]));
                this.setPower(Integer.parseInt(term.split("\\^")[1]));
            }
        }

    }

    public int getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public Term derive() {
        return new Term(this.getCoefficient() * this.getPower(), this.getPower() == 0 ? 0 : this.getPower() - 1);
    }

    @Override
    public String toString() {
        if (this.getCoefficient() > 1 && this.getPower() > 1)
            return this.getCoefficient() + "*x^" + this.getPower();
        else if (this.getCoefficient() > 1 && this.getPower() == 1)
            return this.getCoefficient() + "*x";
        else if (this.getCoefficient() == 1 && this.getPower() == 1)
            return "x";
        else if (this.getCoefficient() == 1 && this.getPower() > 1)
            return "x^" + this.getPower();
        else return String.valueOf(this.getCoefficient());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Term term = (Term) o;

        return getCoefficient() == term.getCoefficient() && getPower() == term.getPower();

    }
}