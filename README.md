# Projeto Tgid Java Developer

## Descrição do Projeto
Esse projeto desenvolve um sistema que permite transações de saque e depósito às empresas, implementando os seguintes casos de uso:
- Adicionar taxa de transação
- Criar conta de empresa
- Criar conta de cliente
- Recarregar carteira do cliente
- Depositar em uma empresa
- Sacar de uma empresa

## Tecnologias e Conceitos Utilizados
- Spring Boot
- MySQL
- OpenAPI
- Flyway
- Princípios SOLID
- Webhook
- Envio de emails

## Propostas de Melhorias
1. **Implementação de Retentativas**: Como ressaltado nos requisitos, a comunicação via webhook com sistemas de terceiros pode ser instável e apresentar falhas, portanto seria razoável a implementação de um sistema de retentativas caso o POST ao endpoint disponibilizado pelas Empresas retornar qualquer código de status HTTP diferente de 200, representando o status OK, tornando essa comunicação mais resiliente a falhas.
2. **Arquitetura**: O sistema foi projetado tendo como um dos principais focos a manutenibilidade, seguindo os princípios SOLID, com traços da Clean Architecture e desacoplando a maior parte dos componentes, principalmente com relação ao domínio e aos casos de uso, buscando evitar o modelo anêmico de domínio e trazendo para as entidades os comportamentos cabíveis a elas, e não apenas dados.
3. **Performance**: Estratégias que tragam assíncronicidade, como uso de programação reativa ou a utilização de filas poderiam agilizar a resposta dos casos de uso que utilizam do serviço de envio de email, tendo em vista que dependendo da configuração e do provedor, esse serviço pode levar um tempo considerável para retornar uma resposta.
4. **Testes**: Testes automatizados também são essenciais para garantir o bom funcionamento do código, e que definitivamente seriam prioritários na evolução desse sistema.
