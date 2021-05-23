# game-store

## Install and Run

1. Execute `sql/schema.sql` in MySQL Workbench. This drops and then creates a database with appropriate schema.

2. Execute `sql/date.sql` in MySQL Workbench. This inserts one of each type of record: game, console, t_shirt, and invoice.

3. Edit `src/main/resources/application.properties`:

    ```
    # update port, db name, etc. if needed
    spring.datasource.url: jdbc:mysql://localhost:3306/game_store?useSSL=false&serverTimezone=US/Eastern&allowPublicKeyRetrieval=true
    spring.datasource.username: <Your username>
    spring.datasource.password: <Your password>
    ```

## API

### JSON

#### game

```js
{
    "id": 1,
    "title": "World of Goo",
    "esrbRating": "PG",
    "description": "puzzle video game",
    "price": 12.99,
    "studio": "2D Boy",
    "quantity": 25
}
```

#### console

```js
{
    "id": 1,
    "model": "Nintendo Switch",
    "manufacturer": "Nintendo",
    "memoryAmount": "4GB",
    "processor": "ARM 4 Cortex-A57",
    "price": 299.99,
    "quantity": 7
}
```

#### tshirt

```js
{
    "id": 1,
    "size": "Med",
    "color": "Yellow",
    "description": "Science",
    "price": 14.95,
    "quantity": 0
}
```

#### invoice

```js
{
    "id": 1,
    "name": "Eng",
    "street": null,
    "city": "Eagan",
    "state": "MN",
    "zipcode": "55555",
    "itemType": "Console",
    "itemId": 1,
    "unitPrice": 249.99,
    "quantity": 1,
    "subtotal": 249.99,
    "tax": 10.00,
    "processingFee": 2.00,
    "total": 261.99
}
```

### Endpoints

| URL | Method | Request Body | Response Body | Success Status |
| --- | --- | --- | --- | --- |
| /game | GET | --- | game[] | 200 |
| /game/{id} | GET | --- | game | 200 |
| /game | POST | game | game | 201 |
| /game | PUT | game | --- | 204 |
| /game/{id} | DELETE | --- | --- | 204 |
| /game/title/{title} | GET | --- | game[] | 200 |
| /game/esrbrating/{esrb} | GET | --- | game[] | 200 |
| /game/studio/{studio} | GET | --- | game[] | 200 |
| /console | GET | --- | console[] | 200 |
| /console/{id} | GET | --- | console | 200 |
| /console | POST | console | console | 201 |
| /console | PUT | console | --- | 204 |
| /console/{id} | DELETE | --- | --- | 204 |
| /console/manufacturer/{manufacturer} | GET | --- | console[] | 200 |
| /tshirt | GET | --- | tshirt[] | 200 |
| /tshirt/{id} | GET | --- | tshirt | 200 |
| /tshirt | POST | tshirt | tshirt | 201 |
| /tshirt | PUT | tshirt | --- | 204 |
| /tshirt/{id} | DELETE | --- | --- | 204 |
| /tshirt/size/{size} | GET | --- | tshirt[] | 200 |
| /tshirt/color/{color} | GET | --- | tshirt[] | 200 |
| /invoice | GET | --- | invoice[] | 200 |
| /invoice/{id} | GET | --- | invoice | 200 |
| /invoice | POST | invoice | invoice | 201 |
| /invoice/cname/{name} | GET | --- | invoice[] | 200 |