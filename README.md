# TesteSomapay
Sistema de pagamento feito em Spring boot pra realizar teste pra Somapay


## Funcionalidades obrigatórias:
- Cadastro de empresa
-  obter saldo da conta corrente da empresa
-  cadastro de funcionário
-  obter saldo da conta corrente do funcionário
-  transferir dinheiro da conta da empresa para a conta do funcionário


## API PRINCIPAIS
- BaseURL: `http://localhost:3000`
- `/enterprise`: Post  
- `/enterprise/${id_enterprise}`: GET
- `/enterprise/employee/${id_enterprise}`: POST
- `/enterprise/folha/${id_enterprise}`: POST
- `/enterprise/folha/employee/${id_employee}`: GET
