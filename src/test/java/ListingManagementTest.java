import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class ListingManagementTest {

    ListingManagement list;
    FileManagement f = new FileManagement();
    MatrixManagement matriz = new MatrixManagement(f);


    @BeforeEach
    void setUp() {
        f.fileReader();
        list = new ListingManagement(matriz, f);

    }

    @Test
    void listaCompletaValida_LM1() {
        String[] array2rqfae = {"ficheiro1", "ficheiro2", "ficheiro3", "ficheiro4", "ficheiro5"};
        list.setArrayFicheiros(array2rqfae);
        double[] graustest = new double[]{1, 2, 3, 4, 5};
        list.setArrayGraus(graustest);
        list.max = 5;
        assertEquals(5, list.listaCompleta().size(),"Era esperado 5 elementos");
    }

    @Test
    public void listaCompletamax0test_LM2() {
        list.max = 0;
        assertEquals(0, list.listaCompleta().size(), "A lista deveria esta vazia");
    }

    @Test
    public void listaCompletaOvertest_LM3() {
        String[] array2rqfae = {"ficheiro1", "ficheiro2", "ficheiro3", "ficheiro4", "ficheiro5"};
        list.setArrayFicheiros(array2rqfae);
        double[] graustest = new double[]{1, 2, 3, 4, 5};
        list.setArrayGraus(graustest);
        list.max = 3;
        assertTrue(list.listaCompleta().size() < 4,"O tamanho nao é igual");
    }
    @Test
    public void listaCompletaValidtest_LM4(){
        ArrayList alist = new ArrayList();
        alist.add("Ficheiro: ficheiro1; Grau de Semelhança: 1.0\n");
        alist.add("Ficheiro: ficheiro2; Grau de Semelhança: 2.0\n");
        alist.add("Ficheiro: ficheiro3; Grau de Semelhança: 3.0\n");

        String[] array2rqfae = {"ficheiro1", "ficheiro2", "ficheiro3", "ficheiro4", "ficheiro5"};
        list.setArrayFicheiros(array2rqfae);
        double[] graustest = new double[]{1, 2, 3, 4, 5};
        list.setArrayGraus(graustest);
        list.max = 3;

        assertEquals(alist,list.listaCompleta(),"A listá não está completa");
    }

    @Test
    public void listaMaximoValidtest_LM5() {
        String[] array2rqfae = {"ficheiro1", "ficheiro2", "ficheiro3", "ficheiro4", "ficheiro5"};
        list.setArrayFicheiros(array2rqfae);
        double[] graustest = new double[]{1, 2, 3, 4, 5};
        list.setArrayGraus(graustest);

        ArrayList alist = new ArrayList();
        alist.add("Ficheiro: ficheiro1; Grau de Semelhança: 1.0\n");
        alist.add("Ficheiro: ficheiro2; Grau de Semelhança: 2.0\n");

        assertEquals(alist,list.listaMaximo(3),"A lista naõ está a filtrar os maximos");
    }

    @Test
    void listaMinimoValidtest_LM6() {
        String[] array2rqfae = {"ficheiro1", "ficheiro2", "ficheiro3", "ficheiro4", "ficheiro5"};
        list.setArrayFicheiros(array2rqfae);
        double[] graustest = new double[]{1, 2, 3, 4, 5};
        list.setArrayGraus(graustest);

        ArrayList alist = new ArrayList();
        alist.add("Ficheiro: ficheiro4; Grau de Semelhança: 4.0\n");
        alist.add("Ficheiro: ficheiro5; Grau de Semelhança: 5.0\n");

        assertEquals(alist,list.listaMinimo(3),"As listas nao estão devidamente validadas_Minimo");
        assertTrue(alist.equals(list.listaMinimo(3)),"Assert de teste");
    }

    @Test
    void listaTotalMax() {
    }
}