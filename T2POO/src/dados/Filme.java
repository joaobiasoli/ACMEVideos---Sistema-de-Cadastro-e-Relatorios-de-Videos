package dados;

public class Filme extends Video {
    private String diretor;
    private double duracao;

    public Filme(int codigo, String titulo, String diretor, double duracao) {
        super(codigo, titulo);
        this.diretor = diretor;
        this.duracao = duracao;
    }

    //herda o geraTexto do video, e retorna o diretor e duração, separado por hifen.
    public String geraTexto() {
        return super.geraTexto() + "-" + diretor + "-" + String.format("%.2f", duracao) + "-" + String.format("%.2f", calculaCusto());
    }

    //calcula o custo do filme por minuto, R$0.30.
    public double calculaCusto() {
        return duracao * 0.30;
    }

    public String getDiretor() {
        return diretor;
    }

    public double getDuracao() {
        return duracao;
    }
}
