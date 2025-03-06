package operations;

import polinom.Polinom;

import java.util.HashMap;
import java.util.Map;

public class Operatii {
    public static Polinom adunare(Polinom p1, Polinom p2) {
        Polinom suma = new Polinom();

        // adauga termenii lui p1
        for (Map.Entry<Integer, Double> entry : p1.termeni.entrySet()) {
            int grad = entry.getKey();
            double coeficient = entry.getValue();
            suma.adaugaTermen(grad, coeficient);
        }

        // adauga termenii lui p2
        for (Map.Entry<Integer, Double> entry : p2.termeni.entrySet()) {
            int grad = entry.getKey();
            double coeficient = entry.getValue();

            // Daca gradul exista, se aduna coef
            if (suma.termeni.containsKey(grad)) {
                double coeficientExistent = suma.termeni.get(grad);
                suma.termeni.put(grad, coeficientExistent + coeficient);
            } else {
                // Altfel, adaugam termenul cu grad si coef
                suma.adaugaTermen(grad, coeficient);
            }
        }

        return suma;
    }

    public static Polinom scadere(Polinom p1, Polinom p2) {
        Polinom diferenta = new Polinom();

        // adauga termenii din p1
        for (Map.Entry<Integer, Double> entry : p1.termeni.entrySet()) {
            int grad = entry.getKey();
            double coeficient = entry.getValue();
            diferenta.adaugaTermen(grad, coeficient);
        }

        // Scad termenii din p2
        for (Map.Entry<Integer, Double> entry : p2.termeni.entrySet()) {
            int grad = entry.getKey();
            double coeficient = entry.getValue();

            // Daca gradul exista, se face diferența dintre coef
            if (diferenta.termeni.containsKey(grad)) {
                double coeficientExistent = diferenta.termeni.get(grad);
                diferenta.termeni.put(grad, coeficientExistent - coeficient);
            } else {
                // Altfel, adaugam coef cu minus in fata
                diferenta.adaugaTermen(grad, -coeficient);
            }
        }

        return diferenta;
    }
    public static Polinom inmultire(Polinom p1, Polinom p2) {
        Polinom produs = new Polinom();

        // adauga termenii lui p1
        for (Map.Entry<Integer, Double> entry1 : p1.termeni.entrySet()) {
            int grad1 = entry1.getKey();
            double coeficient1 = entry1.getValue();

            // adauga termenii lui p2
            for (Map.Entry<Integer, Double> entry2 : p2.termeni.entrySet()) {
                int grad2 = entry2.getKey();
                double coeficient2 = entry2.getValue();

                int gradProd = grad1 + grad2;
                double coefProd = coeficient1 * coeficient2;

                if (produs.termeni.containsKey(gradProd)) {
                    // Daca exista deja un termen cu același grad, adaugam coef la cel existent
                    double coeficientExistent = produs.termeni.get(gradProd);
                    coefProd += coeficientExistent;
                }

                // adaugam sau actualizam termenul in produs
                produs.adaugaTermen(gradProd, coefProd);
            }
        }

        return produs;
    }

    public static Polinom derivare(Polinom p) {
        Polinom derivata = new Polinom();

        for (Map.Entry<Integer, Double> entry : p.termeni.entrySet()) {
            int grad = entry.getKey();
            double coeficient = entry.getValue();

            if (grad != 0) {
                double coeficientDerivat = coeficient * grad; // Regula de derivare
                int gradDerivat = grad - 1; // Gradul este redus cu 1

                derivata.adaugaTermen(gradDerivat, coeficientDerivat);
            }
        }

        return derivata;
    }

    public static Polinom integrare(Polinom p) {
        Polinom integrala = new Polinom();

        for (Map.Entry<Integer, Double> entry : p.termeni.entrySet()) {
            int grad = entry.getKey();
            double coeficient = entry.getValue();

            double coeficientIntegral = coeficient / (grad + 1); // Regula de integrare
            int gradIntegral = grad + 1; // Gradul este marit cu 1

            integrala.adaugaTermen(gradIntegral, coeficientIntegral);
        }

        // adauga constanta de integrare
        integrala.adaugaTermen(0, 0.0); // Constanta de integrare este 0
        integrala.integrat = true;
        return integrala;
    }

    public static String impartire(Polinom p1, Polinom p2) {
        // Verificam daca primul polinom are gradul mai mic decat cel de-al doilea
        int gradPolinom1 = p1.gradPolinom();
        int gradPolinom2 = p2.gradPolinom();

        if (gradPolinom1 < gradPolinom2) {
            return "Gradul primului polinom este mai mic";
        }

        if (gradPolinom2 == 0 && p2.termeni.size() == 1 && p2.termeni.get(0) == 0.0) {
            return "Nu se poate face impartirea cu 0";
        }

        // Inițializam catul și restul cu polinomul curent
        Polinom cat = new Polinom();
        Polinom rest = new Polinom();
        rest.termeni = new HashMap<>(p1.termeni);

        // Incepem imparțirea
        while (rest.gradPolinom() >= gradPolinom2) {
            // Obținem coeficientul pentru imparțitor (celălalt polinom)
            Double coeficientImpartitor = p2.termeni.get(gradPolinom2);

            // Verificam daca gradul exista in rest.termeni si daca coeficientImpartitor este diferit de null
            if (rest.termeni.containsKey(rest.gradPolinom()) && coeficientImpartitor != null) {
                double coeficient = rest.termeni.get(rest.gradPolinom()) / coeficientImpartitor;

                // Obținem noul termen care trebuie să fie adăugat la cât
                Polinom termen = new Polinom();
                termen.termeni.put(rest.gradPolinom() - gradPolinom2, coeficient);

                // Adăugam termenul la cât
                cat = Operatii.adunare(cat, termen);

                // Scadem imparțitorul înmulțit cu termenul adaugat la cat din rest
                Polinom temp = Operatii.inmultire(p2, termen);
                rest = Operatii.scadere(rest, temp);

                // Actualizam gradul polinomului rest si eliminam termenii cu coeficient 0
                Map<Integer, Double> newTermeni = new HashMap<>();
                for (Map.Entry<Integer, Double> entry : rest.termeni.entrySet()) {
                    int grad = entry.getKey();
                    double coeficientRest = entry.getValue();
                    if (coeficientRest != 0) {
                        newTermeni.put(grad, coeficientRest);
                    }
                }
                rest.termeni = newTermeni;
            } else {
                // Daca gradul nu exista in rest.termeni sau coeficientul impartitor este null, iesim din while loop
                break;
            }
        }

        // Construim string-ul pentru rezultat
        return "Cat: " + cat.prettyPrint() + ", Rest: " + rest.prettyPrint();
    }


}