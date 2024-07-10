# PetControl System (Repositório Back-end)

Neste repositório contém todo o Backend do sistema PetControl. O PetControl System consiste em fornecer uma solução eficiente, confiável e segura para os donos de pet shops gerenciarem seus negócios de forma mais simples e organizada. O sistema possui módulos de gestão de clientes e seus animais, produtos, agendamentos, serviços prestados e históricos de vendas e agendamentos.

## Apresentação das telas

Abaixo, algumas telas de como ficará o projeto depois de pronto. 

<p align="center">
  <img src="images/home.png" alt="Captura de tela 1" width="300"/>
  <img src="images/agenda.png" alt="Captura de tela 2" width="300"/>
  <img src="images/services.png" alt="Captura de tela 1" width="300"/>
  <img src="images/new-appointment.png" alt="Captura de tela 2" width="300"/>
</p>

Para ver todas as telas segue o link para o projeto no site do Canva. https://tinyurl.com/4e6z47ys

## Tecnologias utilizadas

Neste projeto foi utilizado as seguintes Tecnologias Backend:

- Java
- Spring Boot 
- Spring Data JPA
- Spring Security 
- Bean Validation
- JavaMailSender
- Java JWT (OAtuh 0)
- H2 Database (test)
- PostgreSQL (prod)
- JUnit 5
- Mockito

## Arquitetura

O sistema contempla a arquitetura em camadas contendo as seguintes camadas:

- Controladores
- Models
- Repositories
- Infra (config, security)
- Services

## Configuração do projeto

Atualmente o projeto tem os seguintes arquivos e classes configurações:
- application.properties - Define qual perfil o projeto está rodando (prod ou test)
- application-test.properties - Define configurações do banco de dados H2
- application-prod.properties - Define configurações do banco de dados PostgreSQL e host para envio do email (SMTP Gmail)
- Classes de configuração dentro do pacote (Infra) definem configurações de segurança, CORS e host teste para envio de email.

## Segurança

A parte de segurança da aplicação conta com a criação de tokens JWT. Todas as requisições passam por um filtro personalizado onde esse filtro trata os tokens.

<img src="images/token.png" alt="Captura de tela 1" width="500"/>

Os únicos endpoints que são públicos, são:

- /auth/login : Página de login
- /auth/register: Página de registro de um novo usuário
- /auth/passwordRecover: Página de recuperação de senha
- /auth/confirmCode: Página de confirmação do código recebido no email para recuperação de senha.

## Testes

Este projeto utiliza JUnit 5 para os testes unitários e testes de integração. A cobertura de testes abrange todas as camadas do projeto, incluindo controladores, serviços e repositórios.

<img src="images/tests.png" alt="Captura de tela 1" width="1000" height="550"/>
<img src="images/testes-2.png" alt="Captura de tela 1" width="1000" height="550"/>

Para executar os testes, você pode usar o seguinte comando no terminal:

```bash
mvn test # caso tenha o maven instalado
```
ou diretamente na pasta raiz do projeto com o comando:
```bash
./mvnw test # caso nao tenha o maven instalado
```

## Instalação 

OBS: Antes de rodar o projeto, certifique-se de configurar as variáveis de ambiente no SO ou na IDE, que configuram o host de email (test e prod) e o token. 

Para rodar o projeto em sua máquina há duas formas:

1. Clonar o repositório: Certifique-se de ter o Java 21 instalado e utilize alguma IDE de sua preferência. Clone este repositório para alguma pasta de seu computador utilizando os seguintes comandos:
 ```bash
git clone https://github.com/mateusf-prog/petcontrol-system.git.
```
Depois de clonado, abra sua IDE e execute o arquivo "PetcontrolsystemApplication.java" neste diretório:
```java
src/main/java/com/mateus/petcontrolsystem/PetcontrolsystemApplication.java
```

2. Ambiente Docker: ainda indisponível

## Autores

- [Mateus Fonseca - GitHub](https://github.com/mateusf-prog)
- [Mateus Fonseca - LinkedIn](https://www.linkedin.com/in/mateus-fprog)