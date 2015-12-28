package bg.rashev.derivatives;

class Main {

    public static void main(String[] args) {
        Polynomial poly = new Polynomial(args[0]);
        System.out.println("The derivative of f(x)=" + poly.simplify() + " is:");
        System.out.println("f'(x) = " + poly.derive());
    }
}
