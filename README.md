# 🅿️ Parking Manager

Sistema de gerenciamento de vagas de estacionamento para condomínios, desenvolvido com **Java Spring Boot** e **Spring Security**.

![Java](https://img.shields.io/badge/Java-17-orange?style=flat-square&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=flat-square&logo=springboot)
![Spring Security](https://img.shields.io/badge/Spring_Security-6.x-6DB33F?style=flat-square&logo=springsecurity)
![License](https://img.shields.io/badge/license-MIT-blue?style=flat-square)

---

## 📋 Sobre o Projeto

O **Parking Manager** é uma API REST que permite o gerenciamento de vagas de estacionamento em condomínios. O sistema conta com dois perfis de acesso:

- **ADMIN / Síndico** — cadastra moradores, define blocos, apartamentos e vagas
- **MORADOR** — visualiza seus dados (apartamento, bloco, vaga) e gerencia seus veículos

---

## ✨ Funcionalidades

### Admin / Síndico
- ✅ Cadastrar moradores
- ✅ Atribuir apartamento, bloco e vaga ao morador
- ✅ Visualizar todos os moradores e veículos cadastrados
- ✅ Gerenciar vagas disponíveis

### Morador
- ✅ Visualizar seus dados (nº do apartamento, bloco e vaga)
- ✅ Adicionar veículos
- ✅ Visualizar seus veículos cadastrados

---

## 🛠️ Tecnologias

| Camada | Tecnologia |
|--------|-----------|
| Linguagem | Java 17 |
| Framework | Spring Boot 3.x |
| Segurança | Spring Security + JWT |
| Banco de Dados | PostgreSQL |
| ORM | Spring Data JPA / Hibernate |
| Build | Maven |
| Documentação | Springdoc OpenAPI (Swagger) |

---

## 🏗️ Estrutura do Projeto

```
parking-manager/
├── src/
│   ├── main/
│   │   ├── java/com/parking/
│   │   │   ├── config/
│   │   │   │   ├── SecurityConfig.java       # Configuração do Spring Security
│   │   │   │   └── OpenApiConfig.java        # Configuração do Swagger
│   │   │   ├── controller/
│   │   │   │   ├── AuthController.java       # Login e autenticação
│   │   │   │   ├── AdminController.java      # Endpoints do admin/síndico
│   │   │   │   └── MoradorController.java    # Endpoints do morador
│   │   │   ├── model/
│   │   │   │   ├── Usuario.java              # Entidade usuário
│   │   │   │   ├── Morador.java              # Entidade morador
│   │   │   │   └── Veiculo.java              # Entidade veículo
│   │   │   ├── repository/
│   │   │   │   ├── UsuarioRepository.java
│   │   │   │   ├── MoradorRepository.java
│   │   │   │   └── VeiculoRepository.java
│   │   │   ├── service/
│   │   │   │   ├── AuthService.java
│   │   │   │   ├── MoradorService.java
│   │   │   │   └── VeiculoService.java
│   │   │   ├── dto/
│   │   │   │   ├── MoradorRequestDTO.java
│   │   │   │   ├── VeiculoRequestDTO.java
│   │   │   │   └── LoginRequestDTO.java
│   │   │   └── security/
│   │   │       ├── JwtTokenProvider.java     # Geração e validação do JWT
│   │   │       └── JwtAuthFilter.java        # Filtro de autenticação
│   │   └── resources/
│   │       └── application.properties
│   └── test/
└── pom.xml
```

---

## ⚙️ Configuração

### Pré-requisitos

- Java 17+
- Maven 3.8+
- PostgreSQL 14+

### Variáveis de Ambiente

Crie um arquivo `.env` ou configure as variáveis diretamente no `application.properties`:

```properties
# application.properties

# Banco de dados
spring.datasource.url=jdbc:postgresql://localhost:5432/parking_manager
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

# JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# JWT
jwt.secret=sua_chave_secreta_muito_segura
jwt.expiration=86400000

# Porta
server.port=8080
```

---

## 🚀 Como Rodar

```bash
# 1. Clone o repositório
git clone https://github.com/seu-usuario/parking-manager.git
cd parking-manager

# 2. Configure o banco de dados PostgreSQL
createdb parking_manager

# 3. Atualize as credenciais em application.properties

# 4. Compile e rode
mvn spring-boot:run
```

A API estará disponível em `http://localhost:8080`

Acesse a documentação Swagger em `http://localhost:8080/swagger-ui.html`

---

## 🔐 Autenticação

O sistema utiliza **JWT (JSON Web Token)** com Spring Security.

### Login

```http
POST /api/auth/login
Content-Type: application/json

{
  "email": "admin@condominio.com",
  "senha": "123456"
}
```

**Resposta:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "tipo": "Bearer",
  "perfil": "ADMIN"
}
```

Inclua o token nos próximos requests:
```
Authorization: Bearer {token}
```

---

## 📡 Endpoints

### 🔒 Admin — requer perfil `ROLE_ADMIN`

| Método | Rota | Descrição |
|--------|------|-----------|
| `POST` | `/api/admin/moradores` | Cadastrar morador |
| `GET` | `/api/admin/moradores` | Listar todos os moradores |
| `GET` | `/api/admin/moradores/{id}` | Buscar morador por ID |
| `PUT` | `/api/admin/moradores/{id}` | Atualizar dados do morador |
| `DELETE` | `/api/admin/moradores/{id}` | Remover morador |
| `GET` | `/api/admin/veiculos` | Listar todos os veículos |
| `GET` | `/api/admin/vagas` | Listar vagas e disponibilidade |

### 🏠 Morador — requer perfil `ROLE_MORADOR`

| Método | Rota | Descrição |
|--------|------|-----------|
| `GET` | `/api/morador/perfil` | Ver dados do próprio perfil (apt, bloco, vaga) |
| `GET` | `/api/morador/veiculos` | Listar seus veículos |
| `POST` | `/api/morador/veiculos` | Adicionar veículo |
| `DELETE` | `/api/morador/veiculos/{id}` | Remover veículo |

### 🌐 Público

| Método | Rota | Descrição |
|--------|------|-----------|
| `POST` | `/api/auth/login` | Autenticar e obter token JWT |

---

## 📦 Exemplo de Payloads

### Cadastrar Morador (Admin)

```json
POST /api/admin/moradores

{
  "nome": "João Silva",
  "email": "joao@email.com",
  "senha": "senha123",
  "apartamento": "101",
  "bloco": "A",
  "vaga": "15"
}
```

### Adicionar Veículo (Morador)

```json
POST /api/morador/veiculos

{
  "placa": "ABC-1234",
  "modelo": "Fiat Uno",
  "cor": "Branco"
}
```

### Perfil do Morador

```json
GET /api/morador/perfil

{
  "nome": "João Silva",
  "apartamento": "101",
  "bloco": "A",
  "vaga": "15",
  "veiculos": [
    {
      "id": 1,
      "placa": "ABC-1234",
      "modelo": "Fiat Uno",
      "cor": "Branco"
    }
  ]
}
```

---

## 🗄️ Modelo de Dados

```
Usuario
  ├── id (Long)
  ├── nome (String)
  ├── email (String)
  ├── senha (String - BCrypt)
  └── perfil (Enum: ADMIN, MORADOR)

Morador (extends Usuario)
  ├── apartamento (String)
  ├── bloco (String)
  ├── vaga (String)
  └── veiculos (List<Veiculo>)

Veiculo
  ├── id (Long)
  ├── placa (String)
  ├── modelo (String)
  ├── cor (String)
  └── morador (Morador)
```

---

## 🤝 Contribuindo

1. Faça um fork do projeto
2. Crie uma branch: `git checkout -b feature/nova-funcionalidade`
3. Commit suas mudanças: `git commit -m 'feat: adiciona nova funcionalidade'`
4. Push para a branch: `git push origin feature/nova-funcionalidade`
5. Abra um Pull Request

---

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.
