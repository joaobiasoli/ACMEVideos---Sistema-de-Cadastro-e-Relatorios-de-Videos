package app;

import dados.Acervo;
import dados.Filme;
import dados.Seriado;
import dados.Video;

import java.io.*;
import java.nio.BufferOverflowException;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Locale;
import java.util.Scanner;

public class ACMEVideos {
    private Scanner entrada = new Scanner(System.in);
    private PrintStream saidaPadrao = System.out;
    private final String nomeArquivoEntrada = "dadosentrada.txt";
    private final String nomeArquivoSaida = "relatorio.txt";


    private Acervo acervo;

    public ACMEVideos() {
        this.acervo = new Acervo();
    }

    //metodo pra verificar se a string pode ser transformada em int.
    private boolean eNumero(String str) {
        try {
            Integer.parseInt(str.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void processar() throws IOException {
        redirecionaEntrada();
        redirecionaSaida();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("relatorio.txt"))) {
            while (entrada.hasNextLine()) {

//1- lê do arquivo de dados de entrada todos os dados de cada vídeo e, se o código não for repetido no sistema, cadastra-o no sistema. Para cada vídeo cadastrado
// com sucesso no sistema, mostra os dados da vídeo no formato: 1: seguido do texto gerado pelo método geraTexto()
//Se o código do vídeo for repetido mostra a mensagem no formato: 1:Erro - codigo de video repetido.

                String linha = entrada.nextLine();
                String[] dados = linha.split(";");
                if (dados.length < 4) {
                    continue;
                }
                if (!eNumero(dados[0]) || !eNumero(dados[1])) {
                    continue;
                }
                // cadastra 1 pra filme e 2 pra seriado e o cod do video:
                int tipo = Integer.parseInt(dados[0].trim());
                int codigo = Integer.parseInt(dados[1].trim());

                //verifica se o codigo já existe.
                if (tipo == 1 && dados.length < 5) {
                    continue;
                } else if (tipo == 2 && dados.length < 6) {
                    continue;
                }
                Video video;
                // tipo 1 é para filme.
                if (tipo == 1) {
                    String titulo = dados[2];
                    String diretor = dados[3];
                    // para verificar se a string ta se encaixando na expressao ideal
                    if (!dados[4].matches("\\d+(\\.\\d+)?")) {
                        continue;
                    }
                    double duracao = Double.parseDouble(dados[4].replace(",", "."));
                    video = new Filme(codigo, titulo, diretor, duracao);
                    //tipo 2 é para seriados.
                } else if (tipo == 2) {
                    String titulo = dados[2];
                    if (!eNumero(dados[3]) || !eNumero(dados[4]) || !eNumero(dados[5])) {
                        continue;
                    }
                    int anoInicio = Integer.parseInt(dados[3].trim());
                    int anoFim = Integer.parseInt(dados[4].trim());
                    int numEpisodios = Integer.parseInt(dados[5].trim());
                    video = new Seriado(codigo, titulo, anoInicio, anoFim, numEpisodios);
                } else {
                    continue;
                }

                if (!acervo.existeCodigo(codigo)) {
                    acervo.addVideo(video);
                    double custo = video.calculaCusto();
                    bw.write("1:" + video.geraTexto());
                    bw.newLine();
                } else {
                    bw.write("1: Erro - codigo de video repetido");
                    bw.newLine();
                }
            }


            //metodo 2
            acervo.tituloMaisLongo(bw);

            //metodo 3
            acervo.videoMaisBarato(bw);

            //metodo 4
            acervo.maiorPeriodoExib(bw);

            //metodo 5
            acervo.diretorComMaisFilmes(bw);

            //metodo 6
            acervo.desvioPadrao(bw);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }




    // Redireciona Saida para arquivos
    private void redirecionaEntrada() {
        try {
            BufferedReader streamEntrada = new BufferedReader(new FileReader(nomeArquivoEntrada));
            entrada = new Scanner(streamEntrada);
        } catch (Exception e) {
            System.out.println(e);
        }
        Locale.setDefault(Locale.ENGLISH);
        entrada.useLocale(Locale.ENGLISH);
    }

    private void redirecionaSaida() {
        try {
            PrintStream streamSaida = new PrintStream(new File(nomeArquivoSaida), Charset.forName("UTF-8"));
            System.setOut(streamSaida);
        } catch (Exception e) {
            System.out.println(e);
        }
        Locale.setDefault(Locale.ENGLISH);
    }

    private void restauraEntrada() {
        entrada = new Scanner(System.in);
    }

    private void restauraSaida() {
        System.setOut(saidaPadrao);
    }

}

