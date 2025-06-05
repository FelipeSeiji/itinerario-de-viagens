# 🌍 Sistema de Gerenciamento de Itinerários de Viagens

Este projeto é um sistema web desenvolvido com **Spring Boot** e **Thymeleaf** para o gerenciamento de itinerários de viagens. Ele permite aos usuários cadastrarem, editarem, visualizarem e excluírem itinerários de forma prática e segura.

## ✈️ Funcionalidades

- Cadastro de itinerários com destino, data de ida e volta e descrição
- Listagem de itinerários por usuário autenticado
- Edição e exclusão de itinerários
- Autenticação de usuários
- Interface responsiva com Thymeleaf
- Aplicação de padrões de projeto (Factory, Singleton, Facade)

## 🛠️ Tecnologias Utilizadas

- Java 17
- Spring Boot
- Thymeleaf
- Spring Security (básico)
- Banco de dados H2 (em memória)
- Maven

## 🎯 Padrões de Projeto Aplicados

- **Factory Method**: utilizado para criar instâncias de itinerários com validações.
- **Singleton**: aplicado ao repositório para garantir uma única instância de acesso aos dados.
- **Facade**: usado para centralizar a lógica de negócio e facilitar o uso no controller.

bash
Copiar
Editar

## 💡 Como Executar

1. Clone o repositório:

```bash
git clone https://github.com/seuusuario/gerenciador-itinerarios.git
cd gerenciador-itinerarios
Compile o projeto:

bash
Copiar
Editar
./mvnw clean install
Rode a aplicação:

bash
Copiar
Editar
./mvnw spring-boot:run
Acesse no navegador:

arduino
Copiar
Editar
http://localhost:8080
✅ Requisitos
Java 17+

Maven
