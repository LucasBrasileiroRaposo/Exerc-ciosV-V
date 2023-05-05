import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FuncionarioParticoesEquivalentesTest {

    // PARTIÇÕES SALARIO DE DEVS
    private final double SALARIO_DEV_MAIOR_3K = 6000;
    private final double SALARIO_DEV_MENOR_3K = 1300;
    private final double TAXA_SALARIO_DEV_MAIOR_QUE_LIMITE = 0.8;
    private final double TAXA_SALARIO_DEV_MENOR_QUE_LIMITE = 0.9;

    //PARTIÇÕES SALARIO DE DBAs E TESTADORES
    private final double SALARIO_DBA_TESTADOR_MAIOR_2K = 3700;
    private final double SALARIO_DBA_TESTADOR_MENOR_2K = 1450;
    private final double TAXA_SALARIO_DBA_TESTADOR_MAIOR_QUE_LIMITE = 0.75;
    private final double TAXA_SALARIO_DBA_TESTADOR_MENOR_QUE_LIMITE = 0.85;

    //PARTIÇÕES SALARIO GERENTES
    private final double SALARIO_GERENTE_MAIOR_5K = 8000;
    private final double SALARIO_GERENTE_MENOR_5K = 3400;
    private final double TAXA_SALARIO_GERENTE_MAIOR_QUE_LIMITE = 0.7;
    private final double TAXA_SALARIO_GERENTE_MENOR_QUE_LIMITE = 0.8;

    private Funcionario funcionario;
    private CalculadoraSalarioImpl calculadoraSalario = new CalculadoraSalarioImpl();

    @BeforeEach
    public void setUp() {
        funcionario = new Funcionario("Lucas brasileiro", "lucas@gmail.com", SALARIO_DEV_MAIOR_3K, Cargo.DESENVOLVEDOR);
    }

    // TESTES PARA CARGO DEV
    @Test
    public void calculaSalarioDevMaior3k() {
        Assertions.assertEquals(4800,calculadoraSalario.calculaSalario(funcionario));
        Assertions.assertEquals(SALARIO_DEV_MAIOR_3K * TAXA_SALARIO_DEV_MAIOR_QUE_LIMITE,
                calculadoraSalario.calculaSalario(funcionario));
    }
    @Test
    public void calculaSalarioDevMenor3k() {
        funcionario.setSalarioBase(SALARIO_DEV_MENOR_3K);
        Assertions.assertEquals(1170,calculadoraSalario.calculaSalario(funcionario));
        Assertions.assertEquals(SALARIO_DEV_MENOR_3K * TAXA_SALARIO_DEV_MENOR_QUE_LIMITE,
                calculadoraSalario.calculaSalario(funcionario));
    }

    // TESTES PARA CARGO DBA
    @Test
    public void calculaSalarioDBAMaior2k() {
        funcionario.setSalarioBase(SALARIO_DBA_TESTADOR_MAIOR_2K);
        funcionario.setCargo(Cargo.DBA);
        Assertions.assertEquals(SALARIO_DBA_TESTADOR_MAIOR_2K * TAXA_SALARIO_DBA_TESTADOR_MAIOR_QUE_LIMITE,
                calculadoraSalario.calculaSalario(funcionario));
        Assertions.assertEquals(2775,calculadoraSalario.calculaSalario(funcionario));
    }
    @Test
    public void calculaSalarioDBAMenor2k() {
        funcionario.setSalarioBase(SALARIO_DBA_TESTADOR_MENOR_2K);
        funcionario.setCargo(Cargo.DBA);
        Assertions.assertEquals(SALARIO_DBA_TESTADOR_MENOR_2K * TAXA_SALARIO_DBA_TESTADOR_MENOR_QUE_LIMITE,
                calculadoraSalario.calculaSalario(funcionario));
        Assertions.assertEquals(1232.5,calculadoraSalario.calculaSalario(funcionario));
    }

    // TESTES PARA CARGO TESTADOR
    @Test
    public void calculaSalarioTestadorMaior2k() {
        funcionario.setSalarioBase(SALARIO_DBA_TESTADOR_MAIOR_2K);
        funcionario.setCargo(Cargo.TESTADOR);
        Assertions.assertEquals(SALARIO_DBA_TESTADOR_MAIOR_2K * TAXA_SALARIO_DBA_TESTADOR_MAIOR_QUE_LIMITE,
                calculadoraSalario.calculaSalario(funcionario));
        Assertions.assertEquals(2775,calculadoraSalario.calculaSalario(funcionario));
    }
    @Test
    public void calculaSalarioTestadorMenor2k() {
        funcionario.setSalarioBase(SALARIO_DBA_TESTADOR_MENOR_2K);
        funcionario.setCargo(Cargo.TESTADOR);
        Assertions.assertEquals(SALARIO_DBA_TESTADOR_MENOR_2K * TAXA_SALARIO_DBA_TESTADOR_MENOR_QUE_LIMITE,
                calculadoraSalario.calculaSalario(funcionario));
        Assertions.assertEquals(1232.5,calculadoraSalario.calculaSalario(funcionario));
    }

    // TESTES PARA CARGO GERENTE
    @Test
    public void calculaSalarioGerenteMaior5k() {
        funcionario.setSalarioBase(SALARIO_GERENTE_MAIOR_5K);
        funcionario.setCargo(Cargo.GERENTE);
        Assertions.assertEquals(SALARIO_GERENTE_MAIOR_5K * TAXA_SALARIO_GERENTE_MAIOR_QUE_LIMITE,
                calculadoraSalario.calculaSalario(funcionario));
        Assertions.assertEquals(5600,calculadoraSalario.calculaSalario(funcionario));
    }
    @Test
    public void calculaSalarioGerenteMenor5k() {
        funcionario.setSalarioBase(SALARIO_GERENTE_MENOR_5K);
        funcionario.setCargo(Cargo.GERENTE);
        Assertions.assertEquals(SALARIO_GERENTE_MENOR_5K * TAXA_SALARIO_GERENTE_MENOR_QUE_LIMITE,
                calculadoraSalario.calculaSalario(funcionario));
        Assertions.assertEquals(2720,calculadoraSalario.calculaSalario(funcionario));
    }
}
