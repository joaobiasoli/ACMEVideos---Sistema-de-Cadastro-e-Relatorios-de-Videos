# ACMEVideos - Sistema de Cadastro e Relatórios de Vídeos

Este projeto foi desenvolvido como atividade acadêmica para a disciplina de **Programação Orientada a Objetos**.

## 📄 Descrição

O sistema realiza o **cadastro e análise de vídeos** (filmes e seriados) a partir da leitura de um arquivo de entrada (`dadosentrada.txt`) e gera um **relatório automatizado** com estatísticas em um arquivo de saída (`relatorio.txt`).

### ✅ Funcionalidades principais

- Leitura de dados estruturados a partir de arquivo texto;
- Cadastro de vídeos:
  - Filmes: com título, diretor e duração;
  - Seriados: com título, ano de início, ano de fim e número de episódios;
- Verificação automática de duplicidade de código;
- Geração de relatório contendo:
  - Vídeo com o título mais longo;
  - Vídeo com o menor custo;
  - Seriado com maior período de exibição;
  - Diretor com mais filmes cadastrados;
  - Vídeo com custo mais próximo da média geral (menor desvio padrão).

## 💻 Tecnologias usadas

- Java
- Programação Orientada a Objetos
- Leitura e escrita em arquivos (`Scanner`, `BufferedWriter`, `FileReader`)
- Manipulação de coleções (`ArrayList`, `Iterator`)

## ▶️ Como executar

1. Compile o projeto Java:
   - Certifique-se de que os arquivos `.java` estão organizados nos pacotes corretos (`app` e `dados`);
2. Crie o arquivo `dadosentrada.txt` com os dados dos vídeos a serem processados;
3. Execute a classe `Main` (localizada no pacote raiz);
4. O sistema irá gerar automaticamente o arquivo `relatorio.txt` com os resultados da análise.

## 📁 Arquivo de entrada (dadosentrada.txt)

Cada linha representa um vídeo:

- Filme:
1;codigo;titulo;diretor;duracao
- Seriado:
2;codigo;titulo;anoInicio;anoFim;numEpisodios

### Exemplo:
1;101;Oppenheimer;Christopher Nolan;180
2;102;Breaking Bad;2008;2013;62

## 👤 Autor

**João Biasoli**

Projeto desenvolvido para estudo prático dos conceitos de POO aplicados à manipulação de dados, estruturas de decisão e geração de relatórios automatizados.
