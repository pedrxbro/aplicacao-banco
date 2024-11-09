package br.univali.cc.poo.ex1.visao;

import br.univali.cc.poo.ex1.dominio.Banco;
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
            System.out.println("1 - criar conta normal");
            System.out.println("2 - criar conta especial");
            System.out.println("3 - depositar");
            System.out.println("4 - sacar");
            System.out.println("5 - transferir");
            System.out.println("6 - emitir extrato");
            System.out.println("s - sair");
            opcao = lerValor("Digite uma opçao do menu").charAt(0);
            switch (opcao) {
                case '1': criarContaNormal(); break;
                case '2': criarContaEspecial(); break;
                case '3': depositar(); break;
                case '4': sacar(); break;
                case '5': transferir(); break;
                case '6': emitirExtrato();break;
                case 's': System.out.println("adios"); break;
            }
        } while (opcao != 's');
    }

    private void criarContaNormal() {
        double saldo = Double.parseDouble(lerValor("saldo"));
        banco.criarConta(saldo);
    }

    private void criarContaEspecial() {
        double saldo = Double.parseDouble(lerValor("saldo"));
        double limite = Double.parseDouble(lerValor("limite"));
        banco.criarConta(saldo, limite);
    }

    private void depositar() {
        int numero = Integer.parseInt(lerValor("Digite o numero da conta"));
        double valor = Double.parseDouble(lerValor("valor"));
        banco.depositar(numero, valor);
    }

    private void sacar() {
        int numero = Integer.parseInt(lerValor("Digite o numero da conta"));
        double valor = Double.parseDouble(lerValor("valor"));
        banco.sacar(numero, valor);
    }

    private void transferir() {
        int origem = Integer.parseInt(lerValor("Digite o numero da conta de origem"));
        int destino = Integer.parseInt(lerValor("Digite o numero da conta de destino"));
        double valor = Double.parseDouble(lerValor("valor"));
        banco.transferir(origem, destino, valor);

    }

    private void emitirExtrato() {
        int numero = Integer.parseInt(lerValor("Digite o numero da conta"));
        System.out.println(banco.emitirExtrato(numero));
    }
    
    
}
