package br.univali.cc.poo.ex1.dominio;

public class Banco {
    private String nome;
    private int numero;
    private ContaCorrente[] contas;
    
    private int proximoNumero = 0;

    public Banco(String nome, int numero) {
        this.nome = nome;
        this.numero = numero;
        this.contas = new ContaCorrente[999];
    }
    
    public void criarConta(double saldoInicial) {
        int numero = this.proximoNumero+1;
        ContaCorrente conta = new ContaCorrente(numero, saldoInicial);
        this.contas[this.proximoNumero++] = conta;
    }
    
    public void criarConta(double saldoInicial, double limite) {
        int numero = this.proximoNumero+1;
        ContaCorrente conta = new ContaCorrente(numero, saldoInicial, limite);
        this.contas[this.proximoNumero++] = conta;
    }
    
    public void depositar(int numeroConta, double valor) {
        ContaCorrente conta = this.localizarConta(numeroConta);
        if (conta != null) {
            conta.depositar(valor);
        }
    }
    
    public void transferir(int numeroContaOrigem, int numeroContaDestino, double valor) {
        ContaCorrente contaOrigem = this.localizarConta(numeroContaOrigem);
        ContaCorrente contaDestino = this.localizarConta(numeroContaDestino);
        if (contaOrigem != null && contaDestino != null) {
            if (contaOrigem.sacar(valor)) {
                contaDestino.depositar(valor);
            }
        }
    }
    
    public void sacar(int numeroConta, double valor) {
        ContaCorrente conta = this.localizarConta(numeroConta);
        if (conta != null) {
            conta.sacar(valor);
        }
    }

    private ContaCorrente localizarConta(int numeroConta) {
        for (ContaCorrente conta:contas) {
            if (conta != null && conta.getNumeroConta() == numeroConta){
                return conta;
            }
        }
        return null;
    }
    
    public String emitirExtrato(int numeroConta) {
        ContaCorrente conta = this.localizarConta(numeroConta);
        if (conta != null) {
            return conta.emitirExtrato();
        }
        return "Conta não encontrada";
    }
    
    
}
