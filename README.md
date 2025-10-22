##  Introdução

O **PaymentStripe** é um sistema de integração de pagamentos online que demonstra a aplicação prática da **API do Stripe** em um ambiente **Java + Spring Boot**.  
Seu objetivo é explorar como realizar **transações financeiras seguras e automáticas** através de uma API moderna e confiável.

A aplicação foi desenvolvida como parte de um **projeto acadêmico**, com foco em **boas práticas de arquitetura**, **segurança**, **idempotência** e **uso de webhooks**.

---

##  Objetivos

- Implementar a criação e consulta de pagamentos via Stripe API.
- Evitar duplicidade de transações com **idempotência**.
- Atualizar status de pagamentos automaticamente via **webhooks**.
- Avaliar a robustez e facilidade de integração da Stripe API.
- Aplicar arquitetura limpa e escalável com **Spring Boot**.

---

##  Tecnologias

| Tecnologia | Descrição |
|-------------|------------|
| **Java 17** | Linguagem principal |
| **Spring Boot** | Framework para criação de APIs REST |
| **Stripe API** | Provedor de pagamentos |
| **Maven** | Gerenciador de dependências |
| **Postman** | Ferramenta para testes de requisições |
| **GitHub** | Controle de versão |

---

##  Arquitetura do Sistema

O projeto segue uma **arquitetura em camadas**:
Controller → Service → Repository → StripeClient

yaml
Copiar código

### Camadas:
- **Controller:** expõe endpoints REST.
- **Service:** contém a lógica de negócios (criação, consulta e atualização).
- **Repository:** gerencia persistência e logs locais.
- **StripeClient:** comunica-se diretamente com a API do Stripe.

---

##  Estrutura UML 

Payment
┣ id: Long
┣ amount: BigDecimal
┣ status: String
┣ createdAt: LocalDateTime
┣ updatedAt: LocalDateTime
┗ idempotencyKey: String

PaymentService
┣ createPayment(request)
┣ getPayment(id)
┗ updateStatus(event)

yaml
Copiar código

---

##  Endpoints 

| Método | Endpoint | Descrição |
|---------|-----------|------------|
| `POST` | `/payments` | Cria um pagamento |
| `GET` | `/payments/{id}` | Consulta o status de um pagamento |
| `POST` | `/webhook/stripe` | Recebe notificações automáticas do Stripe |

---

##  Idempotência

Cada pagamento criado recebe uma **chave única (Idempotency-Key)** para evitar a duplicação de transações.  
Se uma mesma requisição for reenviada, o Stripe identifica o ID e retorna o mesmo resultado, garantindo segurança e consistência.

---

##  Webhooks

Os **webhooks** permitem que o Stripe notifique o sistema sempre que o status de um pagamento mudar (ex: aprovado, falhado, reembolsado).  
Esses eventos são capturados e processados pelo endpoint `/webhook/stripe`.

---

##  Testes Realizados

Todos os testes foram feitos com **cartões de teste do Stripe**, validando:
- Criação de pagamentos  
- Consulta de status  
- Idempotência  
- Recebimento automático de webhooks  
---
## Resultados e Discussões

-Stripe demonstrou robustez, segurança e facilidade de integração.

-Spring Boot facilitou a organização e escalabilidade do código.

-Webhooks eliminaram a necessidade de consultas manuais de status.

Limitações:

-Apenas modo de teste (sem transações reais).

-Integração limitada a cartão de crédito.

Melhorias Futuras:

-Suporte a PIX, boletos e carteiras digitais.

-Painel de monitoramento visual.

-Geração automática de relatórios.

---

Gabriel Santos
💼 Desenvolvedor 
📧 gs895125@gmail.com

🔗 https://www.linkedin.com/in/gabriel-santosamorim/ 
