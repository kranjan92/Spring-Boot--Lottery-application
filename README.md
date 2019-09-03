# Spring-Boot--Lottery-application
  
    Implementing the Simple Lottery System by creating RestFul interfaces/Services using Spring Boot Framework.
  
## Lottery Rules

    You have a series of lines on a ticket with 3 numbers, each of which has a value of 0, 1, or 2.
    For each ticket if the sum of the values on a line is 2, the result for that line is 10. Otherwise
    if they are all the same, the result is 5. Otherwise so long as both 2nd and 3rd numbers are
    different from the 1st, the result is 1. Otherwise the result is 0.
    
## Implementation

    Implement a REST interface to generate a ticket with n lines. Additionally we will need to
    have the ability to check the status of lines on a ticket. We would like the lines sorted into
    outcomes before being returned. It should be possible for a ticket to be amended with n
    additional lines. Once the status of a ticket has been checked it should not be possible to
    amend.
    
## Potential Solution

    /ticket POST Create a ticket
    /ticket GET Return list of tickets
    /ticket/{id} GET Get individual ticket
    /ticket/{id} PUT Amend ticket lines
    /status/{id} PUT Retrieve status of ticket


## REST URLs --- Ticket REST Services

### URL For Generating the ticket with N-lines (Post Request) 

    URL: http://localhost:8080/ticket?lines=5

### URL Fetching the Tickets (Get Request)

    URL : http://localhost:8080/ticket
   
       Output: [
        {
            "id": 1,
            "status": "CHECKED",
            "linesCount": 2
        },
        {
            "id": 2,
            "status": "NEW",
            "linesCount": 5
        }
    ]
   ### URL Update/Add the Lines in Tickets ( Put Request )
   
      URL : http://localhost:8080/ticket/2?lines=6
      
  ### Status of Ticket ( Get Request )
  
      URL : http://localhost:8080/status/2
   
      Output:
          {
        "id": 1,
        "status": "CHECKED",
        "lines": [
            {
                "id": 2,
                "values": [
                    0,
                    1,
                    1
                ],
                "outcome": 10
            },
            {
                "id": 1,
                "values": [
                    0,
                    1,
                    2
                ],
                "outcome": 1
            }
        ],
        "linesCount": 2
    }
