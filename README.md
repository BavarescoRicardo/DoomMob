# DoomMobRba 🎮

DoomMobRba é um jogo inspirado no clássico **Doom**, desenvolvido em **Java** utilizando o framework [libGDX](https://libgdx.com/). O projeto busca recriar a experiência de exploração e combate em primeira pessoa, com foco em aprendizado e desenvolvimento técnico.

---

## 🚀 Funcionalidades

- **Renderização 3D Simples**: Raycasting para simular gráficos em primeira pessoa.
- **Movimentação Realista**: Controle preciso do jogador com sistema de colisão.
- **Mapas Dinâmicos**: Estrutura baseada em grid com paredes e áreas transitáveis.
- **Compatibilidade Multiplataforma**: Suporte para desktop e Android.

---

## 🛠️ Estrutura do Projeto

Este projeto é modular, dividido nas seguintes plataformas:

### **Core**
Contém toda a lógica principal do jogo, compartilhada entre as plataformas.

### **LWJGL3**
Plataforma de desktop que utiliza o LWJGL3 para execução do jogo em computadores.

### **Android**
Plataforma móvel para execução do jogo em dispositivos Android. Requer o **Android SDK** para build.

---

## ⚙️ Gradle

O projeto utiliza [Gradle](https://gradle.org/) para gerenciamento de dependências e build. Use o wrapper do Gradle (`gradlew.bat` no Windows ou `./gradlew` no Linux/Mac) para executar as seguintes tarefas:

### Tarefas Comuns

- **`build`**: Compila os fontes e cria os arquivos executáveis.
- **`clean`**: Remove os diretórios de build.
- **`lwjgl3:run`**: Inicia o jogo na plataforma desktop.
- **`lwjgl3:jar`**: Gera um arquivo JAR executável para desktop.
- **`android:lint`**: Realiza validações do projeto Android.
- **`test`**: Executa os testes unitários (se houver).

### Flags Úteis

- `--continue`: Continua a execução das tarefas mesmo em caso de erro.
- `--daemon`: Usa o Gradle Daemon para melhorar a performance.
- `--offline`: Utiliza dependências armazenadas em cache.
- `--refresh-dependencies`: Revalida todas as dependências.

---

## 🗺️ Mapa

O mapa é estruturado em uma grade (`grid`), onde:

- `0`: Espaço vazio (transitável).
- `1`: Paredes (bloqueiam movimento).

Exemplo de mapa no código:

```java
int[][] grid = {
    {1, 1, 1, 1, 1},
    {1, 0, 0, 0, 1},
    {1, 0, 1, 0, 1},
    {1, 0, 0, 0, 1},
    {1, 1, 1, 1, 1}
};

Aqui está uma versão revisada e estilizada do arquivo README.md para o seu projeto DoomMobRba:

# DoomMobRba 🎮

DoomMobRba é um jogo inspirado no clássico **Doom**, desenvolvido em **Java** utilizando o framework [libGDX](https://libgdx.com/). O projeto busca recriar a experiência de exploração e combate em primeira pessoa, com foco em aprendizado e desenvolvimento técnico.

---

## 🚀 Funcionalidades

- **Renderização 3D Simples**: Raycasting para simular gráficos em primeira pessoa.
- **Movimentação Realista**: Controle preciso do jogador com sistema de colisão.
- **Mapas Dinâmicos**: Estrutura baseada em grid com paredes e áreas transitáveis.
- **Compatibilidade Multiplataforma**: Suporte para desktop e Android.

---

## 🛠️ Estrutura do Projeto

Este projeto é modular, dividido nas seguintes plataformas:

### **Core** 
Contém toda a lógica principal do jogo, compartilhada entre as plataformas.

### **LWJGL3** 
Plataforma de desktop que utiliza o LWJGL3 para execução do jogo em computadores.

### **Android** 
Plataforma móvel para execução do jogo em dispositivos Android. Requer o **Android SDK** para build.

---

## ⚙️ Gradle

O projeto utiliza [Gradle](https://gradle.org/) para gerenciamento de dependências e build. Use o wrapper do Gradle (`gradlew.bat` no Windows ou `./gradlew` no Linux/Mac) para executar as seguintes tarefas:

### Tarefas Comuns

- **`build`**: Compila os fontes e cria os arquivos executáveis.
- **`clean`**: Remove os diretórios de build.
- **`lwjgl3:run`**: Inicia o jogo na plataforma desktop.
- **`lwjgl3:jar`**: Gera um arquivo JAR executável para desktop.
- **`android:lint`**: Realiza validações do projeto Android.
- **`test`**: Executa os testes unitários (se houver).

### Flags Úteis

- `--continue`: Continua a execução das tarefas mesmo em caso de erro.
- `--daemon`: Usa o Gradle Daemon para melhorar a performance.
- `--offline`: Utiliza dependências armazenadas em cache.
- `--refresh-dependencies`: Revalida todas as dependências.

---

## 🗺️ Mapa

O mapa é estruturado em uma grade (`grid`), onde:

- `0`: Espaço vazio (transitável).
- `1`: Paredes (bloqueiam movimento).

Exemplo de mapa no código:

```java
int[][] grid = {
    {1, 1, 1, 1, 1},
    {1, 0, 0, 0, 1},
    {1, 0, 1, 0, 1},
    {1, 0, 0, 0, 1},
    {1, 1, 1, 1, 1}
};

📦 Instalação

    Clone o repositório:

git clone https://github.com/seu-usuario/DoomMobRba.git

Navegue até o diretório do projeto:

cd DoomMobRba

Execute o jogo (Desktop):

    ./gradlew lwjgl3:run

🖼️ Screenshots (Futuro)

Adicione aqui capturas de tela do jogo para ilustrar sua funcionalidade e aparência.
🧑‍💻 Contribuições

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou enviar pull requests.
📜 Licença

Este projeto é distribuído sob a licença MIT.
🎉 Agradecimentos

Este projeto foi criado com a ajuda do gdx-liftoff, um gerador de projetos para libGDX.

./gradlew lwjgl3:run

