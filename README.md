# Pokédex Multiplatform - Trabalho M1

Projeto ajustado para o enunciado do Trabalho M1 da disciplina **Programação para Dispositivos Móveis II**, UNIVALI.

## O que foi implementado

- `Scaffold` com `topBar` de título dinâmico e `bottomBar` para alternar entre **Pokédex** e **Meu time**.
- Navegação tipada com `@Serializable`.
- Fluxo de telas:
  - `HomeScreen`
  - `PokedexGridScreen`
  - `PokemonDetailScreen`
  - `TeamBuilderScreen`
- Repositório mockado com **12 Pokémons**.
- Grid com `LazyVerticalGrid` exibindo número, nome e tipos.
- Tela de detalhes com descrição, atributos e botão **Adicionar ao time**.
- Gerenciamento de estado do time em memória, permitindo adicionar e remover Pokémons.
- Diferenciação visual de plataforma usando `expect/actual`:
  - `androidMain`: cards com visual Material
  - `iosMain`: composição mais limpa, inspirada em estética Apple

## Estrutura relevante

- `composeApp/src/commonMain/.../data/PokemonRepository.kt`
- `composeApp/src/commonMain/.../navigation/Routes.kt`
- `composeApp/src/commonMain/.../ui/HomeScreen.kt`
- `composeApp/src/commonMain/.../ui/PokedexGridScreen.kt`
- `composeApp/src/commonMain/.../ui/PokemonDetailScreen.kt`
- `composeApp/src/commonMain/.../ui/TeamBuilderScreen.kt`
- `composeApp/src/commonMain/.../ui/TeamBuilderPlatform.kt`
- `composeApp/src/androidMain/.../ui/TeamBuilderPlatform.android.kt`
- `composeApp/src/iosMain/.../ui/TeamBuilderPlatform.ios.kt`

## Como executar

### Android
```bash
./gradlew :composeApp:assembleDebug
```

### iOS
Abra a pasta `iosApp` no Xcode e execute o app pelo simulador.

## Observação

As imagens de referência foram usadas como base para modernizar a interface e aproximar a identidade visual do que o trabalho pede.
