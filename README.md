<h1 align="center">Bank Supera</h1>

<p align='center'> 
    <img src="https://img.shields.io/badge/Spring_Boot  V2.6.4-F2F4F9?style=for-the-badge&logo=spring-boot"/>
    <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white"/>  
</p>    

Projeto desenvolvido foi proposto pela Supera em seu processo seletivo, onde o objetivo e criar um API Rest simulando uma conta bancaria e suas transações.
Foi utilizando os padrões de arquitetura em camadas, onde e possível
realizada a consulta(get) para buscar todas as transações de uma conta, filtrando por data de inicio, fim, por operador transacionado e ainda a projeção do saldo total da conta e por período, e com o tratamento de suas exceções.



<h2>Veja o projeto</h2>

Experimente live demo completa
![Bakcend](https://i.imgur.com/hDCpoNL.gif)


<h2>Como criar e executar o Bank Supera localmente</h2>

Criar e executar o projeto em seu ambiente de desenvolvimento local é muito fácil. Certifique-se de ter o Gi e JDK11 instalados e siga as instruções abaixo. Precisa de informações adicionais? entre em contato no e-mail josecarloscjj@gmail.com 
(Estas instruções pressupõem que você esteja instalando como um usuário root.)

### Backend

1. Clone o código fonte
   ```bash
   git clone git@github.com:joosecj/BankSupera.git
   ```

2. Em sua IDE de preferência(utilizei Intellij), importe o **projeto** e faça o update das dependências do **maven**.

3. Ao executar o projeto, pode ser acessado um navegador da Web em http://localhost:8080/ 

4. Collections do postman para fazer as requisições GET/PUT/DELETE E UPDATE para criação da conta, lançar as transações e consultar por movimentações por conta. 

## Requisições (Endpoints)

#### Obs: Para testar as requisições, usar o URL local que e http://localhost:8080.

- *Todas as Conta Paginadas By * - **GET**

   ```bash
   http://localhost:8080/contas?size=10&page=0
   ```
   ##

- *Conta Extrato By Periodo* - **GET**

   ```bash
  http://localhost:8080/contas/2/extratos?minDate=2018-02-01&maxDate=2022-01-10&operador=&size=10&page=
   ```
   ##
- *Conta Extrato By Periodo* - **GET**

   ```bash
   http://localhost:8080/contas/1/saldo?minDate=2023-01-01&maxDate=2019-12-30&operador=
   ```
   ##

   <h2>Tecnologias utlizadas</h2>

   - [Java](https://docs.oracle.com/en/java/javase/11/)
   - [Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
   - [JPA](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
   - [Maven](https://maven.apache.org/guides/)
   - [H2 Database](https://www.h2database.com/html/main.html)
   - [Lombok](https://projectlombok.org/features/)
   - [Postman](https://www.postman.com/api-documentation-tool/)


   ##

   <div align="center">
   <h2>Autor: José Carlos</h2>
      <img align="center" alt="Jose-Js" height="190" width="190" src="https://avatars.githubusercontent.com/u/100246121?s=400&u=b15a545fb2c49f97f84e25aa0520b8b525631384&v=4"
   </div>
   </br> </br>
   <div align="center">
      <a href = "mailto:josecarloscjj@gmail.com"><img src="https://img.shields.io/badge/-Gmail-%23333?style=for-the-badge&logo=gmail&logoColor=white" target="_blank"></a>
      <a href="https://www.linkedin.com/in/jos%C3%A9-carlos-a79736a0/" target="_blank"><img src="https://img.shields.io/badge/-LinkedIn-%230077B5?style=for-the-badge&logo=linkedin&logoColor=white" target="_blank"></a> 
   </div>