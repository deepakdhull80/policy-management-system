# Policy Management System

# Insureity - Agent Portal 
An Web Portal that allows an Agent to do the following operations

    * Login
    * Creaty Policy
    * Issue Policy

## Agent Login Credentials

   URL : http://52.39.75.240:8085/

     username: agent1
     password: password


# Microservices

## 1. Consumer Microservice
Consumer Module is a Middleware Microservice that performs following operations.

i) Create Consumer Business
  Method: POST
  URI : 35.84.112.206:8989/consumer-service/consumer
  Request Header:
     Authorization: Bearer <valid-token>
  Request Body:
    name: xyz
    dob: 12/01/2021
    panDetails : 12334345
    phone : 9876543210
    email : sada@gmail.com
    agentName : abc
    business : [
           businessCategory : Permissible 
           businessType : Institute 
           businessTurnOver : 12345
           capitalInvested" : 56789
           totalEmployees : 1000
           businessValue : 0
           businessAge :10
           property : [
                   propertyType : private
                   buildingSqft : abc
                   buildingType : institute
                   buildingStoreys : abc
                   buildingAge : 15
                   propertyValue : 0
                   costOfTheAsset : 12345
                   usefulLifeOfTheAsset :54345
                   salvageValue : 34543
           ]

     ]

ii) Update Consumer Business Property

  Method: PUT
  URI : 35.84.112.206:8989/consumer-service/consumers/{consumerId}
  Request Header:
     Authorization: Bearer <valid-token>
  Request Body:
   
    name: xyz
    dob: 12/01/2021
    panDetails : 12334345
    phone : 9876543210
    email : sada@gmail.com
    agentName : abc
    business : [
           businessCategory : Permissible 
           businessType : Institute 
           businessTurnOver : 12345
           capitalInvested" : 56789
           totalEmployees : 1000
           businessValue : 0
           businessAge :10
           property : [
                   propertyType : private
                   buildingSqft : abc
                   buildingType : institute
                   buildingStoreys : abc
                   buildingAge : 15
                   propertyValue : 0
                   costOfTheAsset : 12345
                   usefulLifeOfTheAsset :54345
                   salvageValue : 34543
           ]
     ]
     
     Response => Updated ConsumerDetail Object Or Invalid Request

iii) View Consumer Business Property

  Method: GET
  URI : 35.84.112.206:8989/consumer-service/getconsumers/{consumerId}
  Request Header:
       Authorization: Bearer <valid-token>
  
    Response => Consumer Details

  
iV) Create Business Property 

  Method:POST
  URI : 35.84.112.206:8989/consumer-service/createBusinessProperty/{cid}
  Request Header:
      Authorization: Bearer <valid-token>
  
  Request Body:
  
      businessCategory : Permissible
      businessType : Institute
      businessTurnover : 100000
      capitalInvestment : 50000
      totalEmployee : 150
      businessAge : 10
      propertyType : private
      buildingSquareFeet : abc
      buildingType : Institute
      buildingStoreys : abc
      buildingAge : 15
      costOfTheAssets : 1000
      usefulLiseOfTheAssets : 200
      salvageValue : 123
      
      Response => Message Either Success or Failure
  
## 2. Policy Microservice
Policy Module is a Middleware Microservice that performs the following operations

i) Create Policy

  Method:POST
  URI : 35.84.112.206:8989/policy-service/createPolicy
  Request Header:
      Authorization: Bearer <valid-token>
    
    consumerId : 1
    businessId : 2
    
    Response => ConsumerDetails Object

ii) View Policy 

  Method:GET
  URI : 35.84.112.206:8989/policy-service/viewPolicy/{cid}/{pid}
  Request Header:
      Authorization: Bearer <valid-token>
    
    Response => ConsumerPolicy Object
    
iii) Issue Policy 

  Method:GET
  URI : 35.84.112.206:8989/policy-service/issuePolicy
  Request Header:
      Authorization: Bearer <valid-token>

    policyUniqueId : 1
    
    Response => ConsumerPolicy Object

## 3. Qoutes Microservice
  Quotes Module is a Middleware Microservice that performs the following operations

i) Create Quotes

   Method:POST
   URI : 35.84.112.206:8989/quotes-service/addQuote
   Request Header:
      Authorization: Bearer <valid-token>
   Request Body:
     {  
        businessValue : 2,
        propertyValue : 3,
        propertyType : private,
        qoutes : 90,000 INR,
     }
     
   Response => True/False

ii) Get Quotes 
Check for Quotes from the Policy Microservice

  Method:POST
  URI : 35.84.112.206:8989/quotes-service/getQuotesForPolicy/{businessValue}/{propertyValue}/{propertyType}
  Request Header:
     Authorization: Bearer <valid-token>
         
  Response => quotesValue

