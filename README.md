# PostViewer

**Aluna:** Mariana Almeida
**Disciplina:** Programação para Dispositivos Móveis

----

## 📝 Descrição

Aplicativo Android que consome a API pública [JSONPlaceholder](https://jsonplaceholder.typicode.com/) para exibir uma lista de posts e os comentários de cada post.

## ✅ Requisitos atendidos

- **Tela de Lista de Posts**: carrega `GET /posts` e exibe o título de cada post. Ao tocar em um item, o app navega para a tela de detalhes do post correspondente.
- **Tela de Detalhes do Post**: carrega `GET /posts/{id}/comments` e exibe o título do post junto com a lista de comentários da API.
- **Estados de carregamento e erro**: ambas as telas exibem um indicador de carregamento enquanto a requisição está em andamento e uma mensagem de erro caso a chamada falhe.

## ▶️ Como executar o projeto localmente

1. Clone o repositório.
2. Abra a pasta do projeto no Android Studio (Hedgehog ou superior).
3. Aguarde a sincronização do Gradle (as dependências são resolvidas automaticamente).
4. Conecte um dispositivo físico ou inicie um emulador com Android 8.0 (API 26) ou superior.
5. Execute o app pelo botão **Run ▶**.

Não é necessária nenhuma configuração adicional (chave de API, variáveis de ambiente, etc.) — o app consome a API pública do JSONPlaceholder diretamente.

## 🛠️ Tecnologias e bibliotecas utilizadas

- **Kotlin**
- **Jetpack Compose** — construção da interface
- **Navigation Compose** — navegação entre a lista de posts e os detalhes
- **Retrofit + Gson Converter** — consumo da API REST
- **ViewModel + StateFlow** — gerenciamento de estado (posts, comentários, carregamento e erro)

## 💡 Decisões de design

- **Retrofit + Gson**: bibliotecas padrão de mercado para consumo de API REST em Android, com integração simples ao Kotlin/coroutines.

- **Navigation Compose**: navegação declarativa integrada ao Compose, evitando misturar Fragments/Activities com a UI declarativa.

- **ViewModel + StateFlow**: mantém o estado da tela (posts, comentários, carregamento, erro) sobrevivendo a recomposições e mudanças de configuração, sem acoplar lógica de
  negócio à UI.

- **Tratamento de loading e erro nas telas**: exigido pela especificação do trabalho; evita assumir que toda chamada à API terá sucesso, melhorando a experiência do usuário em
  caso de falha de rede.

## 📸 Capturas de tela

As capturas de tela estão na pasta [`docs/`](docs/):

- `PostsScreen.jpg`: tela de lista de posts com dados carregados da API.
- `DetailsScreen.jpg`: tela de detalhes de um post com os comentários da API visíveis.


