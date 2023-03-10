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
- `/enterprise/employee/${id_employee}`: GET
- `/enterprise/employee/list/${id_enterprise}`: GET
- `/enterprise/folha/${id_enterprise}`: POST
- `/enterprise/folha/employee/${id_employee}`: GET

### API SECUNDARIAS
- `/enterprise/increase/${id_enterprise}`: POST
- `/enterprise/balance/${id_enterprise}`: GET
- `/enterprise/list`: GET

### JSON EXEMPLOS

####  Cadastro de empresa
  {
		"nome": "FixPay Ltda",
		"cnpj": "13332371230193",
		"balance": 100000,
		"data": "2022-10-23",
		"horario": "12:30"
	}
  
####  Cadastro Employee(Funcionário)
{
	"nome": "Amanda",
	"email": "amanda@gmail.com",
	"cpf": "61098513022",
	"departamento": "Contas a pagar",
	"post": "Desenvolvedor pleno",
	"salary": 6000,
	"horario": "08:00",
	"data": "2022-03-09"
}

####  Cadastro FolhaPagamento
###### Esse método serve pra realizar o pagamento do funcionário e debita no valor do saldo da empresa 
{
	"employeeId": 1,
	"salary": 5000,
	"data": "2022-12-13",
	"horario": "10:30"
}

#### IncreaseInBalance
###### API vai receber informações para aumentar o valor do saldo da empresa
{
	"origin": "matercard",
	"valor": 1500,
	"data": "2023-06-12",
	"horario": "10:00"
}
