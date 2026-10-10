📊 Aplicativo IBGE - Pesquisas e Coleta de Dados

Um aplicativo Android nativo desenvolvido em Kotlin para gerenciar a aplicação de questionários, pesquisas de campo e coleta estruturada de dados.

🚀 Funcionalidades

O aplicativo possui os seguintes fluxos principais:

🔐 Autenticação: Sistema de acesso com telas de Login e Registro para pesquisadores e usuários.

🏠 Painel Principal: Telas de navegação central (MainActivity e HomeActivity) para acesso rápido às tarefas do dia.

📝 Módulo de Pesquisas (Survey): Ambiente dedicado à aplicação e preenchimento de questionários.

📋 Justificativas e Motivos (Reason): Tela específica para registrar observações, detalhamentos ou motivos de recusa durante a coleta de dados.

🛠️ Tecnologias e Arquitetura

Este projeto foi construído utilizando as ferramentas oficiais mais recentes do ecossistema Android:

Linguagem: Kotlin

Construção de Interface (Híbrida):

Jetpack Compose: Utilizado para criar componentes modernos e declarativos (ex: LoginScreen, SurveyScreen) e gerenciar o Design System (Theme, Color, Type).

XML Layouts: Integração com o sistema tradicional de views do Android para a estruturação das Activities.

Build System: Gradle utilizando Kotlin DSL (build.gradle.kts).

CI/CD: Configuração básica de automação de fluxo de trabalho através do GitHub Actions.

📁 Estrutura de Diretórios em Destaque

app/src/main/java/com/example/ibge/: Contém as regras de negócio e as Activities (controladores de tela).

app/src/main/java/com/example/ibge/ui/: Organização dos componentes em Jetpack Compose separados por domínios (login, register, survey, theme).

app/src/main/res/layout/: Arquivos de design de interface visual estruturados em XML.

⚙️ Como executar localmente

Para rodar este projeto na sua máquina, você precisará do Android Studio instalado.

Clone o repositório:

git clone https://github.com/Joacazzz/ibge.git


Abra o diretório do projeto no Android Studio.

Aguarde o download das dependências e a sincronização do Gradle.

Conecte o seu smartphone Android com o "Modo de Depuração USB" ativo ou inicie um Emulador (AVD).

Clique no botão de Run (ou pressione Shift + F10) para compilar, instalar e abrir o app no dispositivo.

