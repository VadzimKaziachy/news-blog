# API

[Русский](../ru/README.md) | **English**

This tutorial will describe how the API works.

**Содержание**

- [home](#home)
  - GET
    - /
  - POST
    - /
- [auth](#auth)
  - POST
    - /oauth/token
- [article](#article)
  - GET
    - /articles
    - /articles/{id}
  - POST
    - /articles
- [users](#users)
  - GET
    - /users
    - /users/articles
    - /users/profile
  - POST
      - /users
      - /users/registration
      

## Home
### GET
#### /
```json
{
    "title": "Home page",
    "textButton": "Text button on home page."
}
```
### POST
#### /
Headers

Authorization - Bearer access_token

Body:

```json
{
    "title": "Home page",
    "textButton": "Text button on home page."
}
```

## Auth
### POST
#### /oauth/token
Body -> from-data:
- grant_type = password;
- username = username;
- password = password;

```json
{
    "access_token": "xxxxxxxxxxxxxxxxxxxxxxxxxxxxx",
    "token_type": "bearer",
    "refresh_token": "xxxxxxxxxxxxxxxxxxxxxxxxxxxxx",
    "expires_in": "xxxx",
    "scope": "read write global"
}
```

## Article
### GET
#### /articles
```json
[
    {
        "id": 1,
        "tags": "tags",
        "title": "title",
        "imageName": "image",
        "fullDescription": "fullDescription",
        "shortDescription": "shortDescription"
    },
    {
        "id": 2,
        "tags": "tags",
        "title": "title2",
        "imageName": "image",
        "fullDescription": "fullDescription",
        "shortDescription": "shortDescription"
    },
    {
        "id": 3,
        "tags": "tags",
        "title": "title3",
        "imageName": "image",
        "fullDescription": "fullDescription",
        "shortDescription": "shortDescription"
    },
    {
        "id": 4,
        "tags": "tags",
        "title": "title4",
        "imageName": "image",
        "fullDescription": "fullDescription",
        "shortDescription": "shortDescription"
    }
]
```

#### /articles/{id}
```json
{
    "id": 1,
    "tags": "tags",
    "title": "title",
    "imageName": "image",
    "fullDescription": "fullDescription",
    "shortDescription": "shortDescription"
}
```

### POST
#### /articles
Body: 
```json
{
	"tags":"tags",
	"title": "title",
	"imageName": "image",
	"fullDescription": "fullDescription",
	"shortDescription": "shortDescription"
}
```

## Users
### GET
#### /users
```json
[
    {
        "login": "Kata",
        "role": "USER",
        "lastName": null,
        "firstName": null,
        "accessToken": null
    },
    {
        "login": "Ola",
        "role": "USER",
        "lastName": null,
        "firstName": null,
        "accessToken": null
    }
]
```
#### /users/articles
```json
[
    {
        "id": 1,
        "tags": "tags",
        "title": "title",
        "imageName": "image",
        "fullDescription": "fullDescription",
        "shortDescription": "shortDescription"
    },
    {
        "id": 2,
        "tags": "tags",
        "title": "title2",
        "imageName": "image",
        "fullDescription": "fullDescription",
        "shortDescription": "shortDescription"
    },
    {
        "id": 3,
        "tags": "tags",
        "title": "title3",
        "imageName": "image",
        "fullDescription": "fullDescription",
        "shortDescription": "shortDescription"
    },
    {
        "id": 5,
        "tags": "tags",
        "title": "title",
        "imageName": "image",
        "fullDescription": "fullDescription",
        "shortDescription": "shortDescription"
    }
]
```
#### /users/profile
```json
{
    "login": "vadim",
    "role": "ADMIN",
    "lastName": null,
    "firstName": null,
    "accessToken": null
}
```
### POST
#### /users
Body:
```json
{
	"lastName": "Xxxxx",
	"firstName": "Xxxxx"
}
```
#### /users/registration
Body:
```json
{
	"login": "xxxx",
	"password": "xxxx"
}
```