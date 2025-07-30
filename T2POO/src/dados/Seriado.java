package dados;

public class Seriado extends Video {
    private int anoInicio;
    private int anoFim;
    private int numEpisodios;

    public Seriado(int codigo, String titulo, int anoInicio, int anoFim, int numEpisodios) {
        super(codigo, titulo);
        this.anoInicio = anoInicio;
        this.anoFim = anoFim;
        this.numEpisodios = numEpisodios;
    }


    //herda o geraTexto do Video, e retorna o ano de inicio e de fim do seriado, e o numero de ep.
    public String geraTexto() {
        return super.geraTexto() + "-" + anoInicio + "-" + anoFim + "-" + numEpisodios + "-" + String.format("%.2f", calculaCusto());
    }

    //calcula o valor de cada episódio, cada um custa R$0.50.
    public double calculaCusto() {
        return numEpisodios * 0.50;
    }

    public int getAnoInicio() {
        return anoInicio;
    }

    public int getAnoFim() {
        return anoFim;
    }
}
