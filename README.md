# Mob2Con

### Resumo

Aplicação consiste em uma chamada a api, listando os produtos a serem apresentados, ao final da listagem é possível validar via QR code gerado no qr.io os produtos baseado no carregamento da lista de produtos.

#### O projeto possui algumas camadas sendo elas 
- Network:
  - Para tratar das configuracoes e chamadas da API.
- Data
  - Para a implementação dos contratos do repositório e o carregamento e persistência dos dados da aplicação.
- Domain
  - Camada responsável pela regra de negócio e contratos usados nas camadas Data e Presentation.
- Presentation
  - Para a apresentação dos dados junto a view que é renderizada para o Usuário.

### Bibliotecas Utilizadas

- Retrofit
- Jetpack Navigation
- Mockk
- Coroutines
- Dagger Hilt
- Code Scanner
- View Binding

