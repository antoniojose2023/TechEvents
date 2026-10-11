<div align="center">

# 📅 Tech Events

**Encontre os melhores eventos de tecnologia em um só lugar.**

Aplicativo Android nativo para descobrir, filtrar e consultar eventos de tecnologia (presenciais, online e híbridos), consumindo uma API mock hospedada no GitHub Pages.

![Android](https://img.shields.io/badge/Android-API%2024%2B-3DDC84?logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-100%25-7F52FF?logo=kotlin&logoColor=white)
![Retrofit](https://img.shields.io/badge/Retrofit-3.0.0-48B983)
![Material](https://img.shields.io/badge/Material%20Design-3-6750A4?logo=materialdesign&logoColor=white)
![Versão](https://img.shields.io/badge/vers%C3%A3o-1.0-blue)

</div>

---

## 📖 Sobre o projeto

O **Tech Events** é um app que reúne eventos de tecnologia do Brasil em uma lista fácil de navegar. O usuário pode buscar um evento pelo nome, filtrar por **categoria** e por **tipo de evento** e abrir uma tela de detalhes com data, horário, local, endereço, organizador e descrição.

Os dados vêm de uma **API mock** estática (arquivo JSON no GitHub Pages), o que permite desenvolver e testar toda a camada de rede, listagem e filtros sem precisar de um backend próprio.

## 📱 Screenshots

| Splash | Lista de eventos | Filtros aplicados | Detalhes do evento |
|:---:|:---:|:---:|:---:|
| <img width="1080" height="2400" alt="Image" src="https://github.com/user-attachments/assets/4e3f0de2-d9ff-48c2-82a2-7fa2bc5fc07d" /> | <img width="1080" height="2400" alt="Image" src="https://github.com/user-attachments/assets/aeed6d52-5af9-4969-b04f-4ac36de1df56" /> | <img width="1080" height="2400" alt="Image" src="https://github.com/user-attachments/assets/2eb6f3df-8de4-4bfb-8f61-92940a9c04a3" /> | <img width="1080" height="2400" alt="Image" src="https://github.com/user-attachments/assets/2427727b-a203-4355-a0f1-f7e348cf9df5" /> |

> 💡 Salve os prints na pasta `docs/screenshots/` com os nomes acima (ou ajuste os caminhos da tabela).

## ✨ Funcionalidades

- 🚀 **Tela de splash** com nome, slogan e versão do app.
- 📋 **Listagem de eventos** em cards com categoria, título, data, horário, local, cidade/UF, preço (`grátis` / `pago`) e tipo do evento.
- 🔎 **Busca por nome** do evento.
- 🏷️ **Filtro por categoria** (Android, Kotlin, Flutter), com chips de seleção.
- 🧭 **Filtro por tipo de evento** (Presencial, Online, Híbrido), também com chips.
- 🎨 **Cores por categoria**: cada categoria tem sua cor de destaque nos cards.
- 📄 **Tela de detalhes** com imagem de capa, título, cidade, categoria, tipo, data, horário de início e fim, local, endereço completo, organizador e descrição.
- ✅ Botão **Participar Evento** na tela de detalhes.

## 🛠️ Tecnologias e bibliotecas

| Tecnologia | Uso |
|---|---|
| **Kotlin** | Linguagem principal |
| **Android SDK** (minSdk 24 · targetSdk/compileSdk 37) | Plataforma |
| **ViewBinding** | Acesso seguro às views dos layouts XML |
| **ViewModel + LiveData** (Lifecycle 2.11.0) | Estado da UI reativo e resistente a mudanças de configuração |
| **Retrofit 3.0.0** | Cliente HTTP para consumir a API |
| **Converter Gson** | Conversão do JSON em objetos Kotlin |
| **Coil 2.6.0** | Carregamento de imagens (capa dos eventos) |
| **RecyclerView** | Lista de eventos |
| **Material Components 1.14.0** | Chips, cards, botões e tema Material |
| **ConstraintLayout** | Layout das telas |
| **AppCompat / Activity KTX / Core KTX** | Componentes base do AndroidX |
| **JUnit / Espresso** | Testes unitários e instrumentados |

Build com **Gradle Kotlin DSL**, **Version Catalog** (`gradle/libs.versions.toml`) e **Android Gradle Plugin 9.4.1**.

## 🌐 API Mock

O app consome uma API mock servida pelo **GitHub Pages**. Não há servidor, banco de dados ou autenticação: é um único arquivo JSON estático, versionado em um repositório próprio.

**Endpoint**

```
GET https://antoniojose2023.github.io/api_tech_events/tech_events.json
```

### Por que uma API mock?

- ✅ **Gratuita e sempre disponível**, hospedada no GitHub Pages.
- ✅ **Zero configuração**: sem backend, sem chave de API.
- ✅ **Dados controlados**: fácil de editar para testar casos específicos (evento online sem endereço, evento esgotado, etc.).
- ✅ **Ideal para estudo** de Retrofit, parsing JSON e filtros locais.
- ⚠️ **Somente leitura**: não aceita `POST`, `PUT` ou `DELETE`. A ação "Participar Evento" não persiste dados.

### Estrutura de um evento

A resposta é um **array JSON** com 15 eventos. Exemplo:

```json
{
  "id": 3,
  "title": "Flutter Conf Brasil 2026",
  "description": "Dois dias de conteúdo sobre Flutter, Dart, performance, animações e boas práticas para apps em produção.",
  "category": "Flutter",
  "type": "PRESENCIAL",
  "date": "28 de outubro de 2026",
  "startTime": "09:00",
  "endTime": "18:00",
  "localeSpec": "Expo Center Norte",
  "address": "R. José Bernardo Pinto, 333 - Vila Guilherme",
  "city": "São Paulo",
  "state": "SP",
  "organizer": "Flutter Brasil",
  "price": "pago",
  "totalSeats": 400,
  "registeredParticipants": 265,
  "eventUrl": "https://images.unsplash.com/photo-1587825140708-dfaf72ae4b04?auto=format&fit=crop&w=1200&q=80"
}
```

### Campos

| Campo | Tipo | Descrição |
|---|---|---|
| `id` | `Int` | Identificador único do evento |
| `title` | `String` | Nome do evento |
| `description` | `String` | Descrição exibida na tela de detalhes |
| `category` | `String` | Categoria: `Android`, `Kotlin` ou `Flutter` |
| `type` | `String` | Tipo: `PRESENCIAL`, `ONLINE` ou `HIBRIDO` |
| `date` | `String` | Data por extenso (ex.: `15 de novembro de 2026`) |
| `startTime` / `endTime` | `String` | Horários de início e fim (`HH:mm`) |
| `localeSpec` | `String` | Nome do local ou plataforma de transmissão |
| `address` | `String?` | Endereço completo (`null` em eventos online) |
| `city` / `state` | `String?` | Cidade e UF (`null` em eventos online) |
| `organizer` | `String` | Comunidade ou empresa organizadora |
| `price` | `String` | `grátis` ou `pago` |
| `totalSeats` | `Int` | Total de vagas |
| `registeredParticipants` | `Int` | Participantes já inscritos |
| `eventUrl` | `String` | URL da **imagem de capa** do evento (Unsplash) |

> ℹ️ Apesar do nome, o campo `eventUrl` guarda o link da **imagem** do evento, que o app carrega com o Coil.
>
> ℹ️ Em eventos `ONLINE`, os campos `address`, `city` e `state` vêm `null`. O app trata esses casos, por isso o card online não exibe linha de cidade/UF.

### Exemplo de integração com Retrofit

```kotlin
interface TechEventsApi {
    @GET("tech_events.json")
    suspend fun getEvents(): List<EventResponse>
}

val retrofit = Retrofit.Builder()
    .baseUrl("https://antoniojose2023.github.io/api_tech_events/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val api = retrofit.create(TechEventsApi::class.java)
```

## 🏗️ Arquitetura

O projeto usa **ViewModel** e **LiveData** do Jetpack para separar a lógica de apresentação da interface:

```
API (GitHub Pages)  →  Retrofit  →  ViewModel  →  LiveData  →  Activity/Fragment  →  RecyclerView
```

- **Retrofit** busca e converte o JSON em objetos.
- **ViewModel** guarda a lista de eventos e aplica os filtros (busca por nome, categoria e tipo).
- **LiveData** notifica a UI quando a lista muda.
- **RecyclerView** exibe os cards; **Coil** carrega as imagens.

Pacote base: `br.com.devmobileaj.techevents`

## 🚀 Como executar

### Pré-requisitos

- [Android Studio](https://developer.android.com/studio) em versão recente, com suporte ao **AGP 9.4.1**
- **JDK 11** ou superior
- **SDK Android 37** instalado
- Dispositivo ou emulador com **Android 7.0 (API 24)** ou superior
- Conexão com a internet (os dados e as imagens são remotos)

### Passo a passo

```bash
# 1. Clone o repositório
git clone https://github.com/antoniojose2023/TechEvents.git

# 2. Entre na pasta do projeto
cd TechEvents
```

3. Abra a pasta no **Android Studio** e aguarde a sincronização do Gradle.
4. Selecione um emulador ou dispositivo físico.
5. Clique em **Run ▶️** ou execute pelo terminal:

```bash
./gradlew installDebug
```

### Gerar APK de debug

```bash
./gradlew assembleDebug
# APK em: app/build/outputs/apk/debug/
```

### Executar os testes

```bash
./gradlew test                 # testes unitários
./gradlew connectedAndroidTest # testes instrumentados (requer dispositivo/emulador)
```

## 🗂️ Estrutura do repositório

```
TechEvents/
├── app/                    # Módulo do aplicativo Android
├── gradle/
│   └── libs.versions.toml  # Version Catalog (dependências e versões)
├── build.gradle.kts
├── settings.gradle.kts
├── gradle.properties
├── gradlew / gradlew.bat
└── README.md
```

## 🔮 Próximos passos

- [ ] Persistir a inscrição em eventos ("Participar Evento")
- [ ] Indicador de vagas restantes (`totalSeats` − `registeredParticipants`)
- [ ] Cache offline com Room
- [ ] Estados de carregamento, erro e lista vazia
- [ ] Ordenação e filtro por data, cidade e preço
- [ ] Migração para Jetpack Compose
- [ ] Testes unitários para ViewModel e filtros

## 🤝 Contribuindo

Contribuições são bem-vindas!

1. Faça um **fork** do projeto
2. Crie uma branch: `git checkout -b feature/minha-feature`
3. Commit suas mudanças: `git commit -m "feat: minha feature"`
4. Envie para o seu fork: `git push origin feature/minha-feature`
5. Abra um **Pull Request**

## 👨‍💻 Autor

**Antonio José**
GitHub: [@antoniojose2023](https://github.com/antoniojose2023)

---

<div align="center">

Feito com ☕ e Kotlin

</div>
