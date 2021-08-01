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
  
  URL : http://52.39.75.240:8085/dashboard
  
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

  URL : http://52.39.75.240:8085/editConsumer/1

    Request => cosnsumerId : 1
      
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
     
     Response => Message Either Success or Failure

iii) View Consumer Business Property

  URL :  http://52.39.75.240:8085/viewConsumer/1
  
    Request => cosnsumerId : 1
  
    Response => Consumer Details

  
iV) Create Business Property 

  URL : http://52.39.75.240:8085/addBusiness/1
  
      Request => cosnsumerId : 1
  
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

  URL : http://52.39.75.240:8085/createPolicy
    
    consumerId : 1
    businessId : 2
    
    Response => Message Either Success or Failure

ii) View Policy 

  URL : http://52.39.75.240:8085/policyDetails
    
    consumerId : 1
    businessId : 2
    
    Response => Message Either Success or Failure
    
iii) Issue Policy 

  URL : http://52.39.75.240:8085/issuePolicy

    consumerUniqueId : 1
    
    Response => Message Either Success or Failure

## 3. Qoutes Microservice
  Quotes Module is a Middleware Microservice that performs the following operations

i) Create Quotes

  URL : http://52.39.75.240:8085/dashboard
  
     businessValue : 2
     propertyValue : 3
     propertyType : private
     qoutes : 90,000 INR
     
     Response => Message Either Success or Failure

ii) Get Quotes 
Check for Quotes from the Policy Microservice

  URL : http://52.39.75.240:8085/getQuotes/1/2/private
   
     Request
         businessValue = 1
         propertyValue = 2
         propertyType = private
         
     Response => quotesValue

