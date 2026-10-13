# Laboratório de Análises Clínicas

## Descrição

Aplicação web desenvolvida em Java para a gestão de pacientes e pedidos de análises clínicas.

O projeto utiliza uma arquitetura Controller-Service-Repository (CSR), uma API REST e uma base de dados MySQL para guardar os registos.

## Tecnologias utilizadas

- Java 21
- Jakarta REST (JAX-RS)
- Jakarta Persistence (JPA)
- Jakarta CDI
- Apache TomEE 10
- Maven
- MySQL 8
- HTML, CSS e JavaScript
- Git e GitHub

## Funcionalidades

**Gestão de pacientes**
- Registar pacientes.
- Listar pacientes.
- Consultar pacientes pelo ID.

**Gestão de análises clínicas**
- Criar pedidos de análises.
- Associar análises a pacientes.
- Consultar análises registadas.
- Consultar análises pelo ID.
- Indicar o estado e o resultado de uma análise no momento do registo.

**Interface gráfica**
- Formulário de registo de pacientes.
- Tabela de pacientes registados.
- Formulário de pedidos de análises.
- Tabela de análises clínicas.

## Estrutura do projeto

O código principal encontra-se em `demo/src/main/java/com/analise/`.

As classes estão organizadas em entidades, controladores, serviços e repositórios.

A interface gráfica encontra-se em `demo/src/main/webapp/index.html`.

## Requisitos

- JDK 21
- Apache Maven
- MySQL 8
- Apache TomEE 10, iniciado através do Maven

## Configuração da base de dados

Criar a base de dados no MySQL:

```sql
CREATE DATABASE IF NOT EXISTS laboratorio_clinico;
```

Criar o utilizador `laboratorio_app`, com uma palavra-passe própria, e conceder-lhe permissões sobre `laboratorio_clinico`.

A configuração encontra-se no ficheiro:

`demo/src/main/tomee/conf/tomee.xml`

A palavra-passe é obtida através da variável `LAB_DB_PASSWORD` e não está incluída no repositório.

**Preparação inicial:** a configuração JPA está definida com `database.action=none`, para não recriar as tabelas existentes. Numa base de dados nova e vazia, é necessário criar as tabelas compatíveis com as entidades ou alterar temporariamente esta opção para `create`. Depois da criação, voltar a utilizar `none`.

## Instalação e execução

Abrir o PowerShell na pasta do repositório e entrar no módulo:

```powershell
cd demo
```

Configurar a palavra-passe do MySQL:

```powershell
$env:LAB_DB_PASSWORD = Read-Host "Palavra-passe do MySQL" -MaskInput
$env:JAVA_TOOL_OPTIONS = "-DLAB_DB_PASSWORD=$env:LAB_DB_PASSWORD"
```

**Atenção:** esta configuração destina-se apenas a testes locais. `JAVA_TOOL_OPTIONS` pode expor a palavra-passe em registos ou argumentos de processos. Não partilhar esses registos.

O controlador JDBC MySQL Connector/J 8.4.0 deve estar disponível nas bibliotecas do TomEE. O projeto declara a dependência no Maven; na configuração local utilizada, o ficheiro JAR foi colocado em `demo/src/main/tomee/lib/`.

Compilar o projeto:

```powershell
mvn package
```

Iniciar o TomEE:

```powershell
mvn tomee:run
```

Depois de o servidor iniciar, abrir no navegador:

http://localhost:8080/

## Endpoints REST

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/api/pacientes` | Listar pacientes |
| GET | `/api/pacientes/{id}` | Consultar paciente pelo ID |
| POST | `/api/pacientes` | Registar paciente |
| GET | `/api/analises` | Listar análises |
| GET | `/api/analises/{id}` | Consultar análise pelo ID |
| POST | `/api/analises` | Registar análise |

### Exemplo de paciente

```json
{
  "nome": "João Silva",
  "dataNascimento": "1990-05-15",
  "sexo": "Masculino",
  "telefone": "912345678",
  "email": "joao.teste@example.com",
  "numeroUtente": "123456789"
}
```

### Exemplo de análise clínica

```json
{
  "tipoAnalise": "Hemograma",
  "dataPedido": "2026-10-13",
  "estado": "Pendente",
  "resultado": "",
  "paciente": {
    "id": 1
  }
}
```

O paciente com ID 1 deve estar previamente registado.

## Testes realizados

Foram testados com sucesso:

- Arranque do servidor Apache TomEE.
- Consulta das APIs de pacientes e análises com resposta HTTP 200.
- Registo de um paciente pela interface gráfica.
- Registo de um pedido de hemograma associado ao paciente.
- Confirmação dos registos nas tabelas MySQL `paciente` e `analise`.

## Limitações

Esta é uma aplicação académica de demonstração. Ainda não inclui autenticação, gestão de permissões, edição e eliminação de registos pela interface ou mecanismos completos de proteção de dados pessoais.

Devem ser utilizados apenas dados fictícios nos testes.

## Repositório GitHub

https://github.com/Dgmar100/laboratorio-analises-clinicas