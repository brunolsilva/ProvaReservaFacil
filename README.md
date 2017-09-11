# Prova Reserva Fácil

Aplicação REST utilizando Java 8, Spring Boot, Spring Data JPA, banco de dados H2 em memória (não necessitando de instalação) e maven como ferramenta de build.

Cada cálculo de taxa tem uma classe e sua utilização está ligada ao tipo.

## Para rodar a aplicação:
Após clonar este repositório, ir até até o diretório criado e rodar o seguinte comando: <code>mvn spring-boot:run</code>

## API

GET http://localhost:8080/agendamento
lista todos os agendamentos

POST http://localhost:8080/agendamento cria o agendamento passado

Exemplo de requisição:
```
{
    "contaOrigem": "123456",
    "contaDestino": "12345A",
    "valor": 1000,
    "dataTransferencia": "2017-09-30",
    "tipoTransacao": "C"
}
```
Resposta:
```
{
    "id": 1,
    "contaOrigem": "123456",
    "contaDestino": "12345A",
    "valor": 1000,
    "taxa": 82,
    "dataTransferencia": "2017-09-30",
    "dataAgendamento": "2017-09-10",
    "tipoTransacao": "C"
}
```

Tipos de Transação: A, B, C, D<br>
Formato do envio da data de transferencia: yyyy-MM-dd

Caso não seja encontrada uma taxa aplicável, a resposta é a seguinte: 
```
[
    {
        "message": "Não foi encontrada uma taxa aplicável"
    }
]
```
