package bg.rashev.derivatives;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


public class Polynomial {
    private List<Term> terms;

    private Polynomial() {
        terms = new ArrayList<>();
    }

    public Polynomial(String polynomial) {
        String[] stringTerms = polynomial.replaceAll("\\s", "").split("\\+");
        this.terms = new ArrayList<>();
        for (String term : stringTerms) {
            this.terms.add(new Term(term));
        }
    }

    public Polynomial simplify() {
        Map<Integer, List<Term>> map = this.getTerms().stream()
                .sorted((o1, o2) -> o2.getPower() - o1.getPower())
                .collect(Collectors.groupingBy(Term::getPower));
        List<Term> simplifiedTerms = new ArrayList<>();
        int newCoefficient;
        for (Map.Entry<Integer, List<Term>> mapEntry : map.entrySet()) {
            newCoefficient = 0;
            for (Term term : mapEntry.getValue()) {
                newCoefficient += term.getCoefficient();
            }
            simplifiedTerms.add(new Term(newCoefficient, mapEntry.getKey()));//double check
        }
        Polynomial simplifiedPolynomial = new Polynomial();
        for (int i = simplifiedTerms.size() - 1; i >= 0; i--) {//
            simplifiedPolynomial.addTerm(simplifiedTerms.get(i));
        }
        return simplifiedPolynomial;
    }

    public Polynomial derive() {
        Polynomial derivative = new Polynomial();
        this.simplify().getTerms().forEach((term) -> derivative.addTerm(term.derive()));
        return derivative;
    }

    private List<Term> getTerms() {
        return terms;
    }

    private void addTerm(Term term) {
        this.getTerms().add(term);
    }

    @Override
    public String toString() {
        StringBuilder stringPoly = new StringBuilder();
        if (this.getTerms().isEmpty() || (this.getTerms().size() == 1 && Objects.equals(this.getTerms().get(0).toString(), "0")))
            return "0";
        else {
            for (Term term : this.getTerms()) {
                if (term.toString().equals("0"))
                    continue;
                stringPoly.append(term.toString());
                stringPoly.append('+');
            }
        }
        if (stringPoly.charAt(stringPoly.length() - 1) == '+' && stringPoly.toString().contains("+"))
            stringPoly.deleteCharAt(stringPoly.length() - 1);
        return stringPoly.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Polynomial that = (Polynomial) o;

        return this.getTerms() != null ? this.getTerms().equals(that.getTerms()) : that.getTerms() == null;

    }
}