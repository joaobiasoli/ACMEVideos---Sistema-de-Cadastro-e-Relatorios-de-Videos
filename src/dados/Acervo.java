package dados;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Acervo {

    private Collection<Video> video;

    public Acervo() {
        video = new ArrayList<>();
    }

    public boolean addVideo(Video v) {
        return video.add(v);
    }

    //verifica se existe o codigo
    public boolean existeCodigo(int codigo) {
        ArrayList<Video> listaVideos = new ArrayList<>(video);
        for (int i = 0; i < listaVideos.size(); i++) {
            Video video = listaVideos.get(i);
            if (video.getCodigo() == codigo) {
                return true;
            }
        }
        return false;
    }


    public Collection<Video> getVideo() {
        return video;
    }

    //2:   //Mostra os dados do vídeo com título mais longo, no formato: 2:codigo,titulo. Se não houver nenhum vídeo cadastrado mostra a mensagem no formato: 2:Erro
    //    //– nenhum vídeo cadastrado.
    public void tituloMaisLongo(BufferedWriter bw) throws IOException {
        if (video.isEmpty()) {
            bw.write("2: Erro - nenhum vídeo cadastrado");
            bw.newLine();
            return;
        }
        Video titMaisLongo = null;

        //obs: iterator percorre os elementos, trabalha praticamente igual o for quando percorre os elementos de uma coleção.
        //importante: hasnext(): retorna true se tiver mais elementos
        // apenas next() retorna o PRÓXIMO elemento

        Iterator<Video> iterator = video.iterator();

        while (iterator.hasNext()) {
            Video video = iterator.next();
            if (titMaisLongo == null || video.getTitulo().length() > titMaisLongo.getTitulo().length()) {
                titMaisLongo = video;
            }
        }
        bw.write("2: " + titMaisLongo.getCodigo() + "," + titMaisLongo.getTitulo());
        bw.newLine();
    }


    //Mostra os dados do vídeo com cálculo de custo mais baixo, no formato: 3:codigo,titulo,custo Se não houver nenhum vídeo cadastrado mostra a mensagem no formato:
    // 3:Erro – nenhum video cadastrado.
    public void videoMaisBarato(BufferedWriter bw) throws IOException {
        Video videoBarato = null;
        if (video.isEmpty()) {
            bw.write("3: Erro - nenhum video cadastrado");
            bw.newLine();
            return;
        }
        Iterator<Video> iterator = video.iterator();

        while (iterator.hasNext()) {
            Video video = iterator.next();
            if (videoBarato == null || video.calculaCusto() < videoBarato.calculaCusto()) {
                videoBarato = video;
            }
        }
        bw.write("3: " + videoBarato.getCodigo() + ", " + videoBarato.getTitulo() +
                ", " + videoBarato.calculaCusto());
        bw.newLine();
    }

    //Mostra os dados do seriado de maior período de exibição no formato: 4:codigo,titulo,período em anosSe não houver nenhum seriado cadastrado
    // mostra a mensagem no formato: 4:Erro - nenhum seriado cadastrado.

    public void maiorPeriodoExib(BufferedWriter bw) throws IOException {
        boolean existeUmaSerie = false;
        Seriado seriadoComMaisTemp = null;
        Iterator<Video> iterator = video.iterator();
        while (iterator.hasNext()) {
            Video video = iterator.next();
            if (video instanceof Seriado) {
                existeUmaSerie = true;
                Seriado seriado = (Seriado) video;

                int tempoAtual = seriado.getAnoFim() - seriado.getAnoInicio();
                if (seriadoComMaisTemp == null || tempoAtual > (seriadoComMaisTemp.getAnoFim() -
                        seriadoComMaisTemp.getAnoInicio())) {
                    seriadoComMaisTemp = seriado;
                }
            }
        }
        if (!existeUmaSerie) {
            bw.write("4: Erro - nenhum seriado cadastrado");
        } else {
            int resultado = seriadoComMaisTemp.getAnoFim() - seriadoComMaisTemp.getAnoInicio() + 1;
            bw.write("4: " + seriadoComMaisTemp.getCodigo() + ", " + seriadoComMaisTemp.getTitulo() +
                    ", " + resultado);
        }
        bw.newLine();
    }

    //Mostra o diretor com mais filmes no formato: 5:diretor,quantidade de filmes Se não houver nenhum filme cadastrado mostra a mensagem no formato:
    // 5:Erro – nenhum filme cadastrado.

    public void diretorComMaisFilmes(BufferedWriter bw) throws IOException {
        String dirComMaisFilmes = null;
        int maximoFilmes = 0;
        String numDiretor = null;
        int numFilmes = 0;

        if (video.isEmpty()) {
            bw.write("5: Erro - nenhum filme cadastrado");
            bw.newLine();
            return;
        }
        Iterator<Video> iterator = video.iterator();

        while (iterator.hasNext()) {
            Video video = iterator.next();
            if (video instanceof Filme) {
                Filme filme = (Filme) video;
                String diretor = filme.getDiretor();
                if (numDiretor == null || !numDiretor.equals(diretor)) {
                    if (numFilmes > maximoFilmes) {
                        maximoFilmes = numFilmes;
                        dirComMaisFilmes = numDiretor;
                    }
                    numDiretor = diretor;
                    numFilmes = 1;
                } else {
                    numFilmes++;
                }
            }
        }
        if (numFilmes > maximoFilmes) {
            maximoFilmes = numFilmes;
            dirComMaisFilmes = numDiretor;
        }
        if (dirComMaisFilmes != null) {
            bw.write("5:" + dirComMaisFilmes + ", " + maximoFilmes);
        } else {
            bw.write("5: Erro - nenhum filme cadastrado");
        }
        bw.newLine();
    }

    //Mostrar os dados do vídeo com menor desvio padrão do cálculo de custo: calcula a média dos cálculos de custos dos vídeos cadastradas e localiza o vídeo com
    //cálculo de custo mais próximo da média calculada, no formato: 6:média calculada, texto gerado pelo método geraTexto()

    public void desvioPadrao(BufferedWriter bw) throws IOException {
        if (video.isEmpty()) {
            bw.write("6: Erro - nenhum vídeo cadastrado");
            bw.newLine();
            return;
        }

        double somaDosValores = 0;
        int numVideos = 0;

        Iterator<Video> iterator = video.iterator();
        while (iterator.hasNext()) {
            Video video = iterator.next();
            somaDosValores += video.calculaCusto();
            numVideos++;
        }
        double media = somaDosValores / numVideos;
        Video videoComMenorDesvio = null;
        double menorDesvio = -1;

        iterator = video.iterator();
        while (iterator.hasNext()) {
            Video video = iterator.next();
            double preco = video.calculaCusto();
            double desvio = Math.abs(preco - media);

            if (videoComMenorDesvio == null || desvio < menorDesvio || menorDesvio == -1) {
                menorDesvio = desvio;
                videoComMenorDesvio = video;
            }
        }

        double custoMenorDesvio = videoComMenorDesvio.calculaCusto();
        bw.write("6: " + String.format("%.2f", media) + " " + videoComMenorDesvio.geraTexto());
        bw.newLine();
    }


}

