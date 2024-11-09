package br.univali.cc.poo.ex1.visao;

import br.univali.cc.poo.ex1.dominio.*;

import java.util.Scanner;

public class CaixaEletronico {
    
    Banco banco;
    
    private String lerValor(String rotulo) {
        System.out.print(rotulo+": ");
        Scanner leitor = new Scanner(System.in);
        return leitor.nextLine();
    }

    public CaixaEletronico(Banco banco) {
        this.banco = banco;
    }
    
    public void menu() {
        char opcao;
        do {
            System.out.println("1 - Criar conta normal");
            System.out.println("2 - Criar conta especial");
            System.out.println("3 - Depositar");
            System.out.println("4 - Sacar");
            System.out.println("5 - Transferir");
            System.out.println("6 - Emitir extrato");
            System.out.println("s - Sair");
            opcao = lerValor("Digite uma opção do menu").charAt(0);
            switch (opcao) {
                case '1': criarContaNormal(); break;
                case '2': criarContaEspecial(); break;
                case '3': depositar(); break;
                case '4': sacar(); break;
                case '5': transferir(); break;
                case '6': emitirExtrato();break;
                case 's': System.out.println("Fim!"); break;
            }
        } while (opcao != 's');
    }

    private void criarContaNormal() {
        double saldo = Double.parseDouble(lerValor("Saldo inicial"));
        boolean contaCriada = false;

        while (!contaCriada) {
            int numero = Integer.parseInt(lerValor("Número da conta"));
            try {
                banco.criarConta(saldo, numero);
                contaCriada = true;
                System.out.println("Conta criada com sucesso!");
            } catch (MesmoNumContasException e) {
                System.out.println("Número de conta já existe. Por favor, insira um novo número.");
            }
        }
    }

    private void criarContaEspecial() {
        double saldo = Double.parseDouble(lerValor("Saldo inicial"));
        double limite = Double.parseDouble(lerValor("Limite"));
        boolean contaCriada = false;

        while (!contaCriada) {
            int numero = Integer.parseInt(lerValor("Número"));
            try {
                banco.criarConta(saldo, numero, limite);
                contaCriada = true;
                System.out.println("Conta criada com sucesso!");
            } catch (MesmoNumContasException e) {
                System.out.println("Número de conta já existe. Por favor, insira um novo número.");
            }
        }
    }

    private void depositar() {
        double valor = Double.parseDouble(lerValor("Valor"));
        int numero = Integer.parseInt(lerValor("Digite o numero da conta"));
        while(true){
            try{
                banco.depositar(numero, valor);
                System.out.println("Depósito realizado com sucesso!");
                break;
            } catch (ContaInexistenteException e) {
                System.out.println("Conta inexistente. Verifique o número da conta.");
                numero = Integer.parseInt(lerValor("Digite o numero da conta"));
            } catch (ValorNegativoException e) {
                System.out.println("Valor negativo. Por favor, insira corretamente.");
                valor = Double.parseDouble(lerValor("Valor"));
            }
        }
    }

    private void sacar() {
        double valor = Double.parseDouble(lerValor("Valor"));
        int numero = Integer.parseInt(lerValor("Digite o numero da conta"));
        while (true) {
            try {
                banco.sacar(numero, valor);
                System.out.println("Saque realizado com sucesso!");
                break;
            } catch (ContaInexistenteException e) {
                System.out.println("Conta inexistente. Verifique o número da conta.");
                numero = Integer.parseInt(lerValor("Digite o numero da conta"));
            } catch (SaldoInsuficienteException e) {
                System.out.println("Saldo insuficiente.");
                valor = Double.parseDouble(lerValor("Digite um saldo suficiente"));
            } catch (ValorNegativoException e) {
                System.out.println("Valor negativo. Por favor, insira corretamente.");
                valor = Double.parseDouble(lerValor("Valor"));
            }
        }
    }

    private void transferir() {
        int origem = Integer.parseInt(lerValor("Digite o numero da conta de origem"));
        int destino = Integer.parseInt(lerValor("Digite o numero da conta de destino"));
        double valor = Double.parseDouble(lerValor("Valor"));
        while(true){
            try {
                banco.transferir(origem, destino, valor);
                System.out.println("Transferência realizada com sucesso!");
                break;
            } catch (ContaInexistenteException e) {
                System.out.println("Erro: Uma das contas informadas não existe. Verifique os números e tente novamente.");
                origem = Integer.parseInt(lerValor("Digite o numero da conta de origem"));
                destino = Integer.parseInt(lerValor("Digite o numero da conta de destino"));
            } catch (SaldoInsuficienteException e) {
                System.out.println("Saldo insuficiente");
                valor = Double.parseDouble(lerValor("Valor"));
            }
            catch (ValorNegativoException e){
                System.out.println("Valor negativo. Por favor, insira corretamente.");
                valor = Double.parseDouble(lerValor("Valor"));
            }
        }
    }

    private void emitirExtrato() {
        while(true){
            int numero = Integer.parseInt(lerValor("Digite o numero da conta"));
            try {
                System.out.println(banco.emitirExtrato(numero));
                break;
            }catch(ContaInexistenteException e){
                System.out.println("Conta inexistente. Verifique o número da conta.");
            }
        }
    }
    
    
}
