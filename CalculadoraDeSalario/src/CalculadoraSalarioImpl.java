public class CalculadoraSalarioImpl implements CalculadoraSalario{

    /*
    classe responsável por fazer o cálculo do salário liquido do funcionário, que em um sistema de
    maior escala poderia ser um service ou um exporter. criada para evitar um possível método calculaSalario
    implementado na classe modelo Funcionário a fim de manter o encapsulamento e mantar a função de modelo.
    para os cargos DBA e TESTADOR a lógica atual é a mesma, mas caso haja qualquer mudança na lógica do
    calculo desses salarios preferi por deixar os métodos em condicionais separados facilitando para possíveis
    futuras alterações
     */
    @Override
    public double calculaSalario(Funcionario funcionario) {
        Enum cargo = funcionario.getCargo();
        double salarioBase = funcionario.getSalarioBase();
        double salarioLiquido = 0;
        if (cargo.equals(Cargo.DESENVOLVEDOR)) {
            salarioLiquido = (salarioBase >= 3000) ? salarioBase *0.8 : salarioBase*0.9;
        } else if (cargo.equals(Cargo.DBA)) {
            salarioLiquido = (salarioBase >= 2000) ? salarioBase*0.75 : salarioBase*0.85;
        } else if (cargo.equals(Cargo.TESTADOR)) {
            salarioLiquido = (salarioBase >= 2000) ? salarioBase*0.75 : salarioBase*0.85;
        } else if (cargo.equals(Cargo.GERENTE)) {
            salarioLiquido = (salarioBase >= 5000) ? salarioBase*0.70 : salarioBase*0.80;
        }
        return salarioLiquido;
    }
}
