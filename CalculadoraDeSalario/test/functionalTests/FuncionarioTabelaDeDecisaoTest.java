import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class FuncionarioTabelaDeDecisaoTest {

    static Random random = new Random();

    private static final double SALARIO_MENOR_2K = 1999 * random.nextDouble();
    private static final double SALARIO_MAIOR_2K_MENOR_3K = 2000 + (2999-2000) * random.nextDouble();
    private static final double SALARIO_MAIOR_3K_MENOR_5K = 3000 + (4999-3000) * random.nextDouble();
    private static final double SALARIO_MAIOR_5K = 5000 + 5000 * random.nextDouble();
    private final double TAXA_SALARIO_DEV_MAIOR_QUE_LIMITE_3K = 0.8;
    private final double TAXA_SALARIO_DEV_MENOR_QUE_LIMITE_3K = 0.9;
    private final double TAXA_SALARIO_DBA_TESTADOR_MAIOR_QUE_LIMITE_2K = 0.75;
    private final double TAXA_SALARIO_DBA_TESTADOR_MENOR_QUE_LIMITE_2K = 0.85;
    private final double TAXA_SALARIO_GERENTE_MAIOR_QUE_LIMITE_5K = 0.7;
    private final double TAXA_SALARIO_GERENTE_MENOR_QUE_LIMITE_5K = 0.8;

    private Funcionario funcionario;
    private CalculadoraSalarioImpl calculadoraSalario = new CalculadoraSalarioImpl();

    @BeforeEach
    public void setUp(){
        funcionario = new Funcionario("Lucas brasileiro", "lucas@gmail.com", SALARIO_MENOR_2K, Cargo.DESENVOLVEDOR);

    }

    // TESTES PARA CARGO DEV
    @Test
    public void calculaSalarioCargoDevSalarioMenor2k(){
        Assertions.assertEquals(funcionario.getSalarioBase() * TAXA_SALARIO_DEV_MENOR_QUE_LIMITE_3K,
                calculadoraSalario.calculaSalario(funcionario));
        Assertions.assertEquals(SALARIO_MENOR_2K * TAXA_SALARIO_DEV_MENOR_QUE_LIMITE_3K,
                calculadoraSalario.calculaSalario(funcionario));
    }
    @Test
    public void calculaSalarioCargoDevSalarioMaior2kMenor3k(){
        funcionario.setSalarioBase(SALARIO_MAIOR_2K_MENOR_3K);
        Assertions.assertEquals(funcionario.getSalarioBase() * TAXA_SALARIO_DEV_MENOR_QUE_LIMITE_3K,
                calculadoraSalario.calculaSalario(funcionario));
        Assertions.assertEquals(SALARIO_MAIOR_2K_MENOR_3K * TAXA_SALARIO_DEV_MENOR_QUE_LIMITE_3K,
                calculadoraSalario.calculaSalario(funcionario));
    }
    @Test
    public void calculaSalarioCargoDevSalarioMaior3kMenor5k(){
        funcionario.setSalarioBase(SALARIO_MAIOR_3K_MENOR_5K);
        Assertions.assertEquals(funcionario.getSalarioBase() * TAXA_SALARIO_DEV_MAIOR_QUE_LIMITE_3K,
                calculadoraSalario.calculaSalario(funcionario));
        Assertions.assertEquals(SALARIO_MAIOR_3K_MENOR_5K * TAXA_SALARIO_DEV_MAIOR_QUE_LIMITE_3K,
                calculadoraSalario.calculaSalario(funcionario));
    }
    @Test
    public void calculaSalarioCargoDevSalarioMaior5k(){
        funcionario.setSalarioBase(SALARIO_MAIOR_5K);
        Assertions.assertEquals(funcionario.getSalarioBase() * TAXA_SALARIO_DEV_MAIOR_QUE_LIMITE_3K,
                calculadoraSalario.calculaSalario(funcionario));
        Assertions.assertEquals(SALARIO_MAIOR_5K * TAXA_SALARIO_DEV_MAIOR_QUE_LIMITE_3K,
                calculadoraSalario.calculaSalario(funcionario));
    }

    // TESTES PARA CARGO DBA
    @Test
    public void calculaSalarioCargoDBASalarioMenor2k(){
        funcionario.setCargo(Cargo.DBA);
        Assertions.assertEquals(funcionario.getSalarioBase() * TAXA_SALARIO_DBA_TESTADOR_MENOR_QUE_LIMITE_2K,
                calculadoraSalario.calculaSalario(funcionario));
        Assertions.assertEquals(SALARIO_MENOR_2K * TAXA_SALARIO_DBA_TESTADOR_MENOR_QUE_LIMITE_2K,
                calculadoraSalario.calculaSalario(funcionario));
    }
    @Test
    public void calculaSalarioCargoDBASalarioMaior2kMenor3k(){
        funcionario.setSalarioBase(SALARIO_MAIOR_2K_MENOR_3K);
        funcionario.setCargo(Cargo.DBA);
        Assertions.assertEquals(funcionario.getSalarioBase() * TAXA_SALARIO_DBA_TESTADOR_MAIOR_QUE_LIMITE_2K,
                calculadoraSalario.calculaSalario(funcionario));
        Assertions.assertEquals(SALARIO_MAIOR_2K_MENOR_3K * TAXA_SALARIO_DBA_TESTADOR_MAIOR_QUE_LIMITE_2K,
                calculadoraSalario.calculaSalario(funcionario));
    }
    @Test
    public void calculaSalarioCargoDBASalarioMaior3kMenor5k(){
        funcionario.setSalarioBase(SALARIO_MAIOR_3K_MENOR_5K);
        funcionario.setCargo(Cargo.DBA);
        Assertions.assertEquals(funcionario.getSalarioBase() * TAXA_SALARIO_DBA_TESTADOR_MAIOR_QUE_LIMITE_2K,
                calculadoraSalario.calculaSalario(funcionario));
        Assertions.assertEquals(SALARIO_MAIOR_3K_MENOR_5K * TAXA_SALARIO_DBA_TESTADOR_MAIOR_QUE_LIMITE_2K,
                calculadoraSalario.calculaSalario(funcionario));
    }
    @Test
    public void calculaSalarioCargoDBASalarioMaior5k(){
        funcionario.setSalarioBase(SALARIO_MAIOR_5K);
        funcionario.setCargo(Cargo.DBA);
        Assertions.assertEquals(funcionario.getSalarioBase() * TAXA_SALARIO_DBA_TESTADOR_MAIOR_QUE_LIMITE_2K,
                calculadoraSalario.calculaSalario(funcionario));
        Assertions.assertEquals(SALARIO_MAIOR_5K * TAXA_SALARIO_DBA_TESTADOR_MAIOR_QUE_LIMITE_2K,
                calculadoraSalario.calculaSalario(funcionario));
    }

    // TESTES PARA CARGO TESTADOR
    @Test
    public void calculaSalarioCargoTestadorSalarioMenor2k(){
        funcionario.setCargo(Cargo.TESTADOR);
        Assertions.assertEquals(funcionario.getSalarioBase() * TAXA_SALARIO_DBA_TESTADOR_MENOR_QUE_LIMITE_2K,
                calculadoraSalario.calculaSalario(funcionario));
        Assertions.assertEquals(SALARIO_MENOR_2K * TAXA_SALARIO_DBA_TESTADOR_MENOR_QUE_LIMITE_2K,
                calculadoraSalario.calculaSalario(funcionario));
    }
    @Test
    public void calculaSalarioCargoTestadorSalarioMaior2kMenor3k(){
        funcionario.setSalarioBase(SALARIO_MAIOR_2K_MENOR_3K);
        funcionario.setCargo(Cargo.TESTADOR);
        Assertions.assertEquals(funcionario.getSalarioBase() * TAXA_SALARIO_DBA_TESTADOR_MAIOR_QUE_LIMITE_2K,
                calculadoraSalario.calculaSalario(funcionario));
        Assertions.assertEquals(SALARIO_MAIOR_2K_MENOR_3K * TAXA_SALARIO_DBA_TESTADOR_MAIOR_QUE_LIMITE_2K,
                calculadoraSalario.calculaSalario(funcionario));
    }
    @Test
    public void calculaSalarioCargoTestadorSalarioMaior3kMenor5k(){
        funcionario.setSalarioBase(SALARIO_MAIOR_3K_MENOR_5K);
        funcionario.setCargo(Cargo.TESTADOR);
        Assertions.assertEquals(funcionario.getSalarioBase() * TAXA_SALARIO_DBA_TESTADOR_MAIOR_QUE_LIMITE_2K,
                calculadoraSalario.calculaSalario(funcionario));
        Assertions.assertEquals(SALARIO_MAIOR_3K_MENOR_5K * TAXA_SALARIO_DBA_TESTADOR_MAIOR_QUE_LIMITE_2K,
                calculadoraSalario.calculaSalario(funcionario));
    }
    @Test
    public void calculaSalarioCargoTestadorSalarioMaior5k(){
        funcionario.setSalarioBase(SALARIO_MAIOR_5K);
        funcionario.setCargo(Cargo.TESTADOR);
        Assertions.assertEquals(funcionario.getSalarioBase() * TAXA_SALARIO_DBA_TESTADOR_MAIOR_QUE_LIMITE_2K,
                calculadoraSalario.calculaSalario(funcionario));
        Assertions.assertEquals(SALARIO_MAIOR_5K * TAXA_SALARIO_DBA_TESTADOR_MAIOR_QUE_LIMITE_2K,
                calculadoraSalario.calculaSalario(funcionario));
    }

    // TESTES PARA CARGO GERENTE
    @Test
    public void calculaSalarioCargoGerenteSalarioMenor2k(){
        funcionario.setCargo(Cargo.GERENTE);
        Assertions.assertEquals(funcionario.getSalarioBase() * TAXA_SALARIO_GERENTE_MENOR_QUE_LIMITE_5K,
                calculadoraSalario.calculaSalario(funcionario));
        Assertions.assertEquals(SALARIO_MENOR_2K * TAXA_SALARIO_GERENTE_MENOR_QUE_LIMITE_5K,
                calculadoraSalario.calculaSalario(funcionario));
    }
    @Test
    public void calculaSalarioCargoGerenteSalarioMaior2kMenor3k(){
        funcionario.setSalarioBase(SALARIO_MAIOR_2K_MENOR_3K);
        funcionario.setCargo(Cargo.GERENTE);
        Assertions.assertEquals(funcionario.getSalarioBase() * TAXA_SALARIO_GERENTE_MENOR_QUE_LIMITE_5K,
                calculadoraSalario.calculaSalario(funcionario));
        Assertions.assertEquals(SALARIO_MAIOR_2K_MENOR_3K * TAXA_SALARIO_GERENTE_MENOR_QUE_LIMITE_5K,
                calculadoraSalario.calculaSalario(funcionario));
    }
    @Test
    public void calculaSalarioCargoGerenteSalarioMaior3kMenor5k(){
        funcionario.setSalarioBase(SALARIO_MAIOR_3K_MENOR_5K);
        funcionario.setCargo(Cargo.GERENTE);
        Assertions.assertEquals(funcionario.getSalarioBase() * TAXA_SALARIO_GERENTE_MENOR_QUE_LIMITE_5K,
                calculadoraSalario.calculaSalario(funcionario));
        Assertions.assertEquals(SALARIO_MAIOR_3K_MENOR_5K * TAXA_SALARIO_GERENTE_MENOR_QUE_LIMITE_5K,
                calculadoraSalario.calculaSalario(funcionario));
    }
    @Test
    public void calculaSalarioCargoGerenteSalarioMaior5k(){
        funcionario.setSalarioBase(SALARIO_MAIOR_5K);
        funcionario.setCargo(Cargo.GERENTE);
        Assertions.assertEquals(funcionario.getSalarioBase() * TAXA_SALARIO_GERENTE_MAIOR_QUE_LIMITE_5K,
                calculadoraSalario.calculaSalario(funcionario));
        Assertions.assertEquals(SALARIO_MAIOR_5K * TAXA_SALARIO_GERENTE_MAIOR_QUE_LIMITE_5K,
                calculadoraSalario.calculaSalario(funcionario));
    }

//    public static void main(String[] args){
//        System.out.println(SALARIO_MENOR_2K);
//        System.out.println(1999 * random.nextDouble());
//        System.out.println(1999 * random.nextDouble());
//        System.out.println(1999 * random.nextDouble());
//        System.out.println(1999 * random.nextDouble());
//        System.out.println(1999 * random.nextDouble());
//        System.out.println(1999 * random.nextDouble());
//        System.out.println("\nMAIOR Q 2K MENOR Q 3K\n");
//        System.out.println(2000 + (2999-2000) * random.nextDouble());
//        System.out.println(2000 + (2999-2000) * random.nextDouble());
//        System.out.println(2000 + (2999-2000) * random.nextDouble());
//        System.out.println(2000 + (2999-2000) * random.nextDouble());
//        System.out.println(2000 + (2999-2000) * random.nextDouble());
//        System.out.println(2000 + (2999-2000) * random.nextDouble());
//        System.out.println(2000 + (2999-2000) * random.nextDouble());
//        System.out.println("\nMAIOR Q 3K MENOR Q 5K\n");
//        System.out.println(3000 + (4999-3000) * random.nextDouble());
//        System.out.println(3000 + (4999-3000) * random.nextDouble());
//        System.out.println(3000 + (4999-3000) * random.nextDouble());
//        System.out.println(3000 + (4999-3000) * random.nextDouble());
//        System.out.println(3000 + (4999-3000) * random.nextDouble());
//        System.out.println(3000 + (4999-3000) * random.nextDouble());
//        System.out.println(3000 + (4999-3000) * random.nextDouble());
//        System.out.println(3000 + (4999-3000) * random.nextDouble());
//        System.out.println(3000 + (4999-3000) * random.nextDouble());
//        System.out.println(3000 + (4999-3000) * random.nextDouble());
//        System.out.println(3000 + (4999-3000) * random.nextDouble());
//        System.out.println(3000 + (4999-3000) * random.nextDouble());
//        System.out.println("\nMAIOR Q 5K\n");
//        System.out.println(5000 + 5000 * random.nextDouble());
//        System.out.println(5000 + 5000 * random.nextDouble());
//        System.out.println(5000 + 5000 * random.nextDouble());
//        System.out.println(5000 + 5000 * random.nextDouble());
//        System.out.println(5000 + 5000 * random.nextDouble());
//        System.out.println(5000 + 5000 * random.nextDouble());
//        System.out.println(5000 + 5000 * random.nextDouble());
//
//    }
}
