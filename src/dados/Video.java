package dados;


public abstract class Video implements Imprimivel {

    private int codigo;

    private String titulo;

    /**
     *
     */
    public Video(int codigo, String titulo) {
        this.codigo = codigo;
        this.titulo = titulo;
    }


    /**
     * @see Imprimivel#geraTexto()
     */
    // retorna o codigo e o titulo do video, separado pelo hifen.
    public String geraTexto() {
        return codigo + "-" + titulo;
    }


    /**
     * @see Imprimivel#calculaCusto()
     */
    //declarei como abstrato
    public abstract double calculaCusto();

    public int getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }
}
