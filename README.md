# Policy Management System

# Insureity - Agent Portal 
An Web Portal that allows an Agent to do the following operations

    * Login
    * Creaty Policy
    * Issue Policy

## Agent Login Credentials

URL : 

  username: agent1
  password: password


# Microservices

## 1. Consumer Microservice
Consumer Module is a Middleware Microservice that performs following operations.

i) Create Consumer Business
  
  URL : 
  
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

  URL :

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

iii) View Consumer Business Property

  URL :  viewConsumer/1
  
    Response => Consumer Details

  
iV) Create Business Property 

  URL : addBusiness/1
  
  
  
## 2. Policy Microservice
Policy Module is a Middleware Microservice that performs the following operations

i) Create Policy

  URL : createPolicy
    
    consumerId : 1
    businessId : 2

ii) View Policy 

  URL : policyDetails
    
    consumerId : 1
    businessId : 2
    
iii) Issue Policy 

  URL : issuePolicy

    consumerUniqueId : 1

## 3. Qoutes Module
  Quotes Module is a Middleware Microservice that performs the following operations

i) Create Quotes

  URL : 
  
     businessValue : 2
     propertyValue : 3
     propertyType : private
     qoutes : 90,000 INR

ii) Get Quotes 

  URL : 
  
  Check for Quotes from the Policy Microservice









