# Introduction

## Functions

- store/search comments via HTTP RESTful interfaces
- search comments (via embed front end)
- manage users (via embed front end)
    - block user
    - change user password(reset)
- manage allowed domain (via embed front end) 



## Data structures


### comment


### user



### allowed domain




## Interfaces



- get /user

- post /user {username, password} | 200 0 token: token or 200 1 msg: auth failure or 200 2 msg: blocked

- get /comment?parent=&user=&createBefore=&createAfter=&modifyAfter=&modifyBefore=&reply=&reply_user=

- post /comment {parent, content, reply, token}

- put /comment {id, content, token}


### __API鉴权__ 


# Use



## Deploy




## Config



## Manage





# Design




# Contribute

If you have any idea to improve this project, feel free to open a issue.