package aps.gustavodariano.appcadastro;

public class Funcionario {
    public int id;
    public String nome;
    public String endereco;
    public String telefone;

    public Funcionario() {

    }

    public Funcionario(String nome, String endereco, String telefone) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "NAME:" + nome +" "+ "|" +" "+
                "ADRESS:" + endereco +" "+ "|" +" "+
                "PHONE:" + telefone;
    }
}
