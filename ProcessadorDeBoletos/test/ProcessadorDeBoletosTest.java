import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProcessadorDeBoletosTest {
    private Fatura fatura, fatura2;
    private Boleto boleto, boleto2, boleto3;
    private List<Boleto> boletosValidos, boletosInsuficientes;
    private ProcessadorDeBoletosImpl processadorDeBoletos;

    @BeforeEach
    public void setUp() {
        boletosValidos = new ArrayList<Boleto>();
        boletosInsuficientes = new ArrayList<Boleto>();
        processadorDeBoletos = new ProcessadorDeBoletosImpl();
        boleto = new Boleto(1, new Date(), 100.0);
        boleto2 = new Boleto(2, new Date(), 400.0);
        boleto3 = new Boleto(3, new Date(), 1500.0);
        boletosValidos.add(boleto);
        boletosValidos.add(boleto2);
        boletosValidos.add(boleto3);
        fatura = new Fatura(new Date(), 2000, "Holliver");
        boletosInsuficientes.add(boleto2);
        boletosInsuficientes.add(boleto);
        fatura2 = new Fatura(new Date(), 1000, "Holliver");
    }
    @Test
    public void testProcessaFaturaPaga() {
        processadorDeBoletos.processa(boletosValidos, fatura);

        assertTrue(fatura.isPago());
        assertEquals(3, fatura.getPagamentos().size());
        assertEquals(TipoPagamento.BOLETO, fatura.getPagamentos().get(0).getTipo());
        assertEquals(TipoPagamento.BOLETO, fatura.getPagamentos().get(1).getTipo());
        assertEquals(TipoPagamento.BOLETO, fatura.getPagamentos().get(2).getTipo());
    }

    @Test
    public void testProcessaFaturaNaoPaga() {
        processadorDeBoletos.processa(boletosInsuficientes, fatura2);

        assertFalse(fatura2.isPago());
        assertEquals(2, fatura2.getPagamentos().size());
        assertEquals(TipoPagamento.BOLETO, fatura2.getPagamentos().get(0).getTipo());
        assertEquals(TipoPagamento.BOLETO, fatura2.getPagamentos().get(1).getTipo());
    }


}
