import org.junit.*;
import polinom.Polinom;
import operations.Operatii;
import static org.junit.Assert.assertEquals;
public class OperationsTest {
    private static Integer nrTeste=0;
    private static Integer nrTesteTrecute=0;

    @BeforeClass
    public static void afisare(){
        System.out.println("Initializare calculator");
    }

    @AfterClass
    public static void fM(){
        System.out.println("Din "+nrTeste+" teste, au trecut "+nrTesteTrecute+" teste");
    }

    @Before
    public void incrementare(){
        nrTeste++;
    }

    @Test
    public void adunare(){
        Polinom polinom1 = new Polinom();
        polinom1.adaugaTermen(2,2.0);
        polinom1.adaugaTermen(1,3.0);
        polinom1.adaugaTermen(0,4.0);
        //  2x^2+3x+4

        Polinom polinom2 = new Polinom();
        polinom2.adaugaTermen(2,3.0);
        polinom2.adaugaTermen(1,2.0);
        polinom2.adaugaTermen(0,1.0);
        //   3x^2+2x+1

        Polinom rezultat = Operatii.adunare(polinom1,polinom2);

        Polinom rezultatDorit= new Polinom();
        rezultatDorit.adaugaTermen(2,5.0);
        rezultatDorit.adaugaTermen(1,5.0);
        rezultatDorit.adaugaTermen(0,5.0);
        //   5x^2+5x+5

        assertEquals(rezultat.prettyPrint(),rezultatDorit.prettyPrint());
        System.out.println("Testul de adunare s-a incheiat cu succes");
        nrTesteTrecute++;
    }

    @Test
    public void scadere(){
        Polinom polinom1 = new Polinom();
        polinom1.adaugaTermen(2,2.0);
        polinom1.adaugaTermen(1,3.0);
        polinom1.adaugaTermen(0,4.0);
        //  2x^2+3x+4

        Polinom polinom2 = new Polinom();
        polinom2.adaugaTermen(2,3.0);
        polinom2.adaugaTermen(1,2.0);
        polinom2.adaugaTermen(0,1.0);
        //   3x^2+2x+1

        Polinom rezultat = Operatii.scadere(polinom1,polinom2);

        Polinom rezultatDorit= new Polinom();
        rezultatDorit.adaugaTermen(2,-1.0);
        rezultatDorit.adaugaTermen(1,1.0);
        rezultatDorit.adaugaTermen(0,3.0);
        //-x^2+x+3

        assertEquals(rezultat.prettyPrint(),rezultatDorit.prettyPrint());
        System.out.println("Testul de scadere s-a incheiat cu succes");
        nrTesteTrecute++;
    }

    @Test
    public void inmultire(){
        Polinom polinom1 = new Polinom();
        polinom1.adaugaTermen(2,2.0);
        polinom1.adaugaTermen(1,3.0);
        //  2x^2+3x

        Polinom polinom2 = new Polinom();
        polinom2.adaugaTermen(2,3.0);
        polinom2.adaugaTermen(1,2.0);
        //   3x^2+2x

        Polinom rezultat = Operatii.inmultire(polinom1,polinom2);

        Polinom rezultatDorit= new Polinom();
        rezultatDorit.adaugaTermen(4,6.0);
        rezultatDorit.adaugaTermen(3,13.0);
        rezultatDorit.adaugaTermen(2,6.0);
        //   6x^4+13x^3+6x^2

        assertEquals(rezultat.prettyPrint(),rezultatDorit.prettyPrint());
        System.out.println("Testul de inmultire s-a incheiat cu succes");
        nrTesteTrecute++;
    }

    @Test
    public void impartire(){
        Polinom polinom1 = new Polinom();
        polinom1.adaugaTermen(3,1.0);
        polinom1.adaugaTermen(2,-2.0);
        polinom1.adaugaTermen(1,6.0);
        polinom1.adaugaTermen(0,-5.0);
        //  x^3-2x^2+6x-5

        Polinom polinom2 = new Polinom();
        polinom2.adaugaTermen(2,1.0);
        polinom2.adaugaTermen(0,-1.0);
        //   x^2-1

        Polinom polinom3 = new Polinom();
        polinom3.adaugaTermen(4,1.0);
        polinom3.adaugaTermen(0,-1.0);
        //   x^4-1

        Polinom polinom4 = new Polinom();
        polinom4.adaugaTermen(0,0.0);
        //   0

        String rezultat1 = Operatii.impartire(polinom1,polinom2);
        String rezultat2 = Operatii.impartire(polinom1,polinom3);
        String rezultat3 = Operatii.impartire(polinom1,polinom4);

        String rezultatDorit1="Cat: x - 2.0, Rest: 7.0x - 7.0";
        String rezultatDorit2="Gradul primului polinom este mai mic";
        String rezultatDorit3="Nu se poate face impartirea cu 0";

        assertEquals(rezultat1,rezultatDorit1);
        System.out.println("Testul de impartire s-a incheiat cu succes");
        assertEquals(rezultat2,rezultatDorit2);
        System.out.println("Testul de impartire cu polinom de grad mai mare s-a incheiat cu succes");
        assertEquals(rezultat3,rezultatDorit3);
        System.out.println("Testul de impartire cu 0 s-a incheiat cu succes");
        nrTesteTrecute++;
    }

    @Test
    public void integrare(){
        Polinom polinom1 = new Polinom();
        polinom1.adaugaTermen(2,3.0);
        polinom1.adaugaTermen(1,2.0);
        polinom1.adaugaTermen(0,1.0);
        //  3x^2+2x+1

        Polinom rezultat = Operatii.integrare(polinom1);

        Polinom rezultatDorit= new Polinom();
        rezultatDorit.adaugaTermen(3,1.0);
        rezultatDorit.adaugaTermen(2,1.0);
        rezultatDorit.adaugaTermen(1,1.0);
        //   x^3+x^2+x

        assertEquals(rezultat.prettyPrint(),rezultatDorit.prettyPrint()+" + C");
        System.out.println("Testul de integrare s-a incheiat cu succes");
        nrTesteTrecute++;
    }

    @Test
    public void derivare(){
        Polinom polinom1 = new Polinom();
        polinom1.adaugaTermen(2,3.0);
        polinom1.adaugaTermen(1,2.0);
        polinom1.adaugaTermen(0,1.0);
        //  3x^2+2x+1

        Polinom rezultat = Operatii.derivare(polinom1);

        Polinom rezultatDorit= new Polinom();
        rezultatDorit.adaugaTermen(1,6.0);
        rezultatDorit.adaugaTermen(0,2.0);
        //   6x+2

        assertEquals(rezultat.prettyPrint(),rezultatDorit.prettyPrint());
        System.out.println("Testul de derivare s-a incheiat cu succes");
        nrTesteTrecute++;
    }
}
