package br.univali.cc.poo.ex1.dominio;

import java.util.ArrayList;
import java.util.List;

public class ContaCorrente {
    private boolean especial;
    private double limite;
    private int numero;
    private double saldo;
    private List<Movimentacao> movimentacoes = new ArrayList<>();

    public ContaCorrente(int numero, double saldoInicial, double limite) {
        this.especial = true;
        this.limite = limite;
        this.numero = numero;
        this.saldo = saldoInicial;
        this.criarMovimentacao("Depósito inicial", 'C', saldoInicial);
    }
    
    public ContaCorrente(int numero, double saldoInicial) {
        this.especial = false;
        this.limite = 0;
        this.numero = numero;
        this.saldo = saldoInicial;
        this.criarMovimentacao("Depósito inicial", 'C', saldoInicial);
    }    

    protected int getNumeroConta() {
        return this.numero;
    }


    public boolean depositar(double valor) {
        if (valor > 0) {
            this.saldo += valor;
            this.criarMovimentacao("Deposito", 'C', valor);
            return true;
        }
        return false;
    }

    public boolean sacar(double valor) throws SaldoInsuficienteException{
        double saldoDisponivel = this.especial ? this.saldo + this.limite : this.saldo;

        if (valor > 0 && saldoDisponivel >= valor) {
            this.saldo -= valor;
            this.criarMovimentacao("Saque", 'D', valor);
            return true;
        }
        throw new SaldoInsuficienteException();
    }
    
    private void criarMovimentacao(String descricao, char tipo, double valor) {
        Movimentacao movimentacao = new Movimentacao(descricao, tipo, valor);
        movimentacoes.add(movimentacao);
    }
    
    public double getSaldo() {
        return this.saldo;
    }
    public double getLimite(){
        return this.limite;
    }
    
    public String emitirExtrato() {
        StringBuilder extrato = new StringBuilder("Conta nº " + this.numero);

        for (Movimentacao movimentacao : movimentacoes) {
            extrato.append("\n ").append(movimentacao.getMovimentacao());
        }

        extrato.append("\n Saldo final: R$ ").append(this.getSaldo());
        return extrato.toString();
    }
    
}
