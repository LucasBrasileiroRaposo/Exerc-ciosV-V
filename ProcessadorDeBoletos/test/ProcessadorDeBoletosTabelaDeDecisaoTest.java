import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProcessadorDeBoletosTabelaDeDecisaoTest {
    private Fatura fatura;
    private Boleto boleto;
    private List<Boleto> boletos;
    private ProcessadorDeBoletosImpl processadorDeBoletos;

    @BeforeEach
    public void setUp() {
        processadorDeBoletos = new ProcessadorDeBoletosImpl();
    }
    @Test
    public void testFaturaIgualBoleto() {
        fatura = new Fatura(new Date(), 2000, "Holliver");
        boleto = new Boleto(1, new Date(), 2000);
        boletos = new ArrayList<Boleto>();
        boletos.add(boleto);

        processadorDeBoletos.processa(boletos, fatura);

        assertTrue(fatura.isPago());
    }
    @Test
    public void testFaturaMenorBoleto() {
        fatura = new Fatura(new Date(), 1500, "Holliver");
        boleto = new Boleto(1, new Date(), 3000);
        boletos = new ArrayList<Boleto>();
        boletos.add(boleto);

        processadorDeBoletos.processa(boletos, fatura);

        assertTrue(fatura.isPago());
    }
    @Test
    public void testFaturaMaiorBoleto() {
        fatura = new Fatura(new Date(), 1000, "Holliver");
        boleto = new Boleto(1, new Date(), 500);
        boletos = new ArrayList<Boleto>();
        boletos.add(boleto);

        processadorDeBoletos.processa(boletos, fatura);

        assertFalse(fatura.isPago());
    }
    @Test
    public void testFaturaMaiorLimiteBoleto() {
        fatura = new Fatura(new Date(), 2000, "Holliver");
        boleto = new Boleto(1, new Date(), 1999);
        boletos = new ArrayList<Boleto>();
        boletos.add(boleto);

        processadorDeBoletos.processa(boletos, fatura);

        assertFalse(fatura.isPago());
    }
    @Test
    public void testFaturaMenorLimiteBoleto() {
        fatura = new Fatura(new Date(), 2000, "Holliver");
        boleto = new Boleto(1, new Date(), 2001);
        boletos = new ArrayList<Boleto>();
        boletos.add(boleto);

        processadorDeBoletos.processa(boletos, fatura);

        assertTrue(fatura.isPago());
    }
    @Test
    public void testOutroTipoPagamento() {
        fatura = new Fatura(new Date(), 2000, "Holliver");
        cartao = new Cartao(1, new Date(), 2001);
        cartoes = new ArrayList<Cartao>();
        cartoes.add(cartao);

        processadorDeBoletos.processa(cartoes, fatura);

        assertFalse(fatura.isPago());
    }
}
