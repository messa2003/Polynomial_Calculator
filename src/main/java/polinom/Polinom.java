package polinom;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Polinom {
    public Map<Integer, Double> termeni;
    public Boolean integrat;

    public Polinom() {
        this.integrat=false;
        this.termeni = new HashMap<>();
    }

    public void adaugaTermen(int grad, double coeficient) {
        termeni.put(grad, coeficient);
    }

    public void citirePolinom(String polinomStr) {
        // impartim polinomul in monoame folosind semnele "+" și "-" ca separatori
        String[] monoame = polinomStr.trim().split("(?=[-+])");

        for (String monom : monoame) {

            double coeficient;
            int grad = 0;

            if (!monom.isEmpty()) {
                // Eliminam semnul
                monom = monom.replace("+", "");

                boolean esteNegativ = false;

                // Dacă monomul incepe cu "-", avem un coeficient negativ
                if (monom.startsWith("-")) {
                    esteNegativ = true;
                    monom = monom.substring(1);
                }

                String[] parts = monom.split("x\\^?");
                if (parts.length == 0) {
                    // Daca monomul este gol, consideram ca avem un coeficient implicit de 1
                    coeficient = 1.0;
                } else {
                    coeficient = Double.parseDouble(parts[0].isEmpty() ? "1.0" : parts[0]);
                }

                // Daca coeficientul este negativ, il inmulțim cu -1
                if (esteNegativ) {
                    coeficient *= -1;
                }

                // La fel și la grad
                if (parts.length > 1 && !parts[1].isEmpty()) {
                    grad = Integer.parseInt(parts[1]);
                } else if (monom.contains("x")) {
                    grad = 1;
                }

                try {
                    // adaugă termenul
                    adaugaTermen(grad, coeficient);
                } catch (NumberFormatException ex) {
                    throw new NumberFormatException("Polinom introdus greșit");
                }
            }
        }
    }


    public int gradPolinom() {
        int gradMaxim = 0;
        for (int grad : termeni.keySet()) {
            if (grad > gradMaxim) {
                gradMaxim = grad;
            }
        }
        return gradMaxim;
    }

    public String prettyPrint() {
        //sortam termenii descrescator pt a putea incepe cu cel mai mare grad

        List<Integer> sortedGrades = new ArrayList<>(termeni.keySet());
        sortedGrades.sort(Collections.reverseOrder());

        StringBuilder result = new StringBuilder();

        for (int grad : sortedGrades) {
            double coeficient = termeni.get(grad);

            //verificam coeficientul
            if (coeficient != 0) {
                //verificam daca coeficientul este pozitiv sau negativ
                if (!result.isEmpty()) {
                    if (coeficient > 0) {
                        result.append(" + ");
                    } else {
                        result.append(" - ");
                    }
                } else {
                    if (coeficient < 0) {
                        result.append("-");
                    }
                }

                //verificam daca gradul e 0, ca sa nu mai adaugam x dupa coeficient
                //vrem sa afisam x, nu 1x
                double absCoef = Math.abs(coeficient);
                if (grad == 0 || absCoef != 1) {
                    result.append(absCoef);
                }
                if (grad > 0) {
                    result.append("x");
                    if (grad > 1) {
                        result.append("^").append(grad);
                    }
                }
            }
        }

        // Verificam daca polinomul este gol
        if (result.isEmpty()) {
            result = new StringBuilder("0");
        }
        //verificam daca polinomul a fost integrat
        // Verificam daca polinomul este gol


        if (integrat) {
            result.append(" + C");
        }
        return result.toString();
    }
}