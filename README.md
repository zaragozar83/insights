# insights project for comun
## This project is following hexagonal architecture to decouple the components and keep the domain and logic decouple of the infrastructure

### This project is using H2 database for simplicity

### To start the application follows commands
1 - on root of the project execute
'' mvn clean install
2 - cd launch-insights-app/
'' mvn spring-boot:run

### This should start the application locally on localhost:8080

### The most important endpoints are below
 
GET: /transactions
example
http://localhost:8080/transactionstransactions
 `` To get all transactions

POST: /transactions
example
http://localhost:8080/transactions
`` This endpoint allows us to create new transactions, so we can play with our application to find the top card grouping by category and find by customerID
body json template below, I will explain small example case:

1 - First request will insert a new transaction for customerId 1 and merchantId 2
{
    "customerId":1,
    "merchantId": 2,
    "amountCents": 102.02
}

2 -
{
    "customerId":1,
    "merchantId": 2,
    "amountCents": 202.02
}

3 -
{
    "customerId":1,
    "merchantId": 3,
    "amountCents": 102.02
}

GET: transactions/insights/{customerId}
example
http://localhost:8080/transactions/insights/1
`` This endpoint should return the insight top cards grouping by category for the customer we send in the request, they are two optional request params I could implement for the time 

Base in the example before where we insert three transactions, we should receive below response here

[
{
"category": 2,
"amount": 304.0400000000001
},
{
"category": 3,
"amount": 102.02
}
]