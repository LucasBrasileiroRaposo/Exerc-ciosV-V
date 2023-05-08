import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProcessadorDeBoletosParticoesEquivalentesTest {
    private Fatura fatura;
    private Boleto boleto;
    private List<Boleto> boletos;
    private ProcessadorDeBoletosImpl processadorDeBoletos;

    @BeforeEach
    public void setUp() {
        processadorDeBoletos = new ProcessadorDeBoletosImpl();
    }
    @Test
    public void testFaturaMenorBoleto() {
        fatura = new Fatura(new Date(), 500, "Holliver");
        boleto = new Boleto(1, new Date(), 1000);
        boletos = new ArrayList<Boleto>();
        boletos.add(boleto);

        processadorDeBoletos.processa(boletos, fatura);

        assertTrue(fatura.isPago());
    }
    @Test
    public void testFaturaMaiorBoleto() {
        fatura = new Fatura(new Date(), 2000, "Holliver");
        boleto = new Boleto(1, new Date(), 500);
        boletos = new ArrayList<Boleto>();
        boletos.add(boleto);

        processadorDeBoletos.processa(boletos, fatura);

        assertFalse(fatura.isPago());
    }
}
