# Tiny Url
A simple implementation of tiny URL using the minimalistic approach, it shortening a long original URL into a shorter and manageable URL.

## User Stories

1. (MVP) As a user you can enter a URL to shorten in the system to get back a unique shortened URL. 
Details: The system should ensure that 2 URLs never map to the same shortened URL.

2. (Optional) As a user you can enter a URL to shorten and a custom string, so that The system returns shortened URL with the custom string as suffix as inputted by user
provided this shortened URL with the custom string as suffix is available. 

3. (MVP) As a user you can type the shortened URL into the browser to get redirected to the original URL.  

## Non-Functional Requirements

- The system should have very low latency 
- the system should be available even if the machine storing the URL mapping goes down due to Power outage, Hard Disk crashes etc.
- The system should stay consistent if a user have shortened a URL, given the shortened URL, 
the system should always return the original URL no matter when the URL is requested from the system. 
- the service should never go down. (No outage). The machines may die. 
the system should make sure your service is unaffected by these incidents (unless all of your machines die at once - that can happen very rarely). 

## APIs

1. Create short URL

```
POST /api/link HTTP/1.1
Host: urlshrtnr.taruntelang.repl.co
Content-Type: application/x-www-form-urlencoded
link= <URL>

Response: 
<shortURL>
```
for e.g. link = `https://www.linkedin.com/in/taruntelang/`
shortURL =  6U8Ahx9

2. Get Original URL

```
GET /<shortURL> HTTP/1.1
Host: urlshrtnr.taruntelang.repl.co
```

## Components
### Health Check System
### Load Balancer 
### Application Server
### Cache
### Database

## Design
shortURL consists of 7 character in base64 format. Characterset for shortURL [a-z A-Z 0-9 - _]  
