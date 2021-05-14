# U2 M13 Summative Assessment

## Extended Paranormal Investigator

Build a React application that tracks paranormal encounters and investigators.

## Setup

1. Open the `paranormal-investigator-api-no-jwt` project from `starter-code`. It's an IntelliJ and Maven project. It's your back-end data source.

    `paranormal-investigator-api-no-jwt` is slightly different than the original `paranormal-investigator-api`:
    
        - Encounters include investigators. 
        - Investigators can be read, created, updated, and deleted. 

2. Run it. It's listening at http://localhost:8080.

    You are not allowed to change the Java code unless your instructor approves.

2. Create a new React project with `create-react-app` named `paranormal-investigator-no-jwt` OR `paranormal-investigator-summative`. Determine which dependencies are needed as you work through the requirements.

    You are allowed to copy Paranormal Investigator components from the earlier activity, but you are also required to create a new React application from scratch.

## The API

Restarting the API will reset the initial data. That can be helpful if you've deleted all encounters and have no way to add a new one.

### `Investigator` JSON

```js
{
    "id":1,
    "firstName":"Koo",
    "lastName":"Artaern"
}
```

### `Encounter` JSON

```js
{ 
    "id": 1, 
    "brief": "Tingling sensation", 
    "details": "I swear something is near me. I just know it.", 
    "dateTime": "19-Feb-2021 11:35AM", 
    "imageUrl": "http://example.com/path/to/an/image.png",
    "investigators":[
        {"id":1,"firstName":"Koo","lastName":"Artaern"},
        {"id":3,"firstName":"Monto","lastName":"Caarew"}
    ]
}
```

### Endpoints

| URL                                         | Method | Request Body | Response Body  | Success Status |
| ------------------------------------------- | ------ | ------------ | -------------- | -------------- |
| http://localhost:8080/api/investigator      | GET    | -            | Investigator[] | 200            |
| http://localhost:8080/api/investigator      | POST   | Investigator | Investigator   | 201            |
| http://localhost:8080/api/investigator/{id} | PUT    | Investigator | -              | 204            |
| http://localhost:8080/api/investigator/{id} | DELETE | -            | -              | 204            |
| http://localhost:8080/api/encounter         | GET    | -            | Encounter[]    | 200            |
| http://localhost:8080/api/encounter         | POST   | Encounter    | Encounter      | 201            |
| http://localhost:8080/api/encounter/{id}    | PUT    | Encounter    | -              | 204            |
| http://localhost:8080/api/encounter/{id}    | DELETE | -            | -              | 204            |

Use Postman to explore the API.

### Gotchas

The encounter's `dateTime` property requires the format: `dd-MMM-yyyy hh:mma`.

Examples:

- 10-Mar-2019 10:13AM
- 07-Jul-2020 08:47PM
- 24-Feb-2021 03:33AM

Any deviation from the format will result in a `null` dateTime.

## Requirements

The application user must be able to:

- View all encounters.- 20%
- Create a new encounter. - 10%
- Update an existing encounter. - 10%
- Delete an encounter. - 10%
- View all investigators. - 10%
- Create a new investigator. - 10%
- Update an existing investigator. (stretch)
- Delete an investigator. (stretch)

The **Create a new encounter** and **Update an existing encounter** are slightly different than the original Paranormal Investigator. They should allow a user to choose any investigator from a list of available investigators. Hint: you will need to fetch all investigators as well as the investigators in the encounter to you have a set of investigators to choose from.

## Technical Requirements

### CSS - 10%

Use Bootstrap. You can decide between standard imported CSS, the React Bootstrap project, or some combination of the two.

### Routing - 10%

Use the React Router. Route-driven navigation should include:

- **Encounters**: the home view. Displays all encounters and options to add, edit, or delete.
- **Investigators**: Displays all investigators and an option to add.


### Testing - 10%

Create at least one sensible tests for each component. You don't have to practice full TDD since React development is largely UI-driven and exploratory.

## Recommendations

You're free to choose a development strategy, but consider the following sequence to avoid rework.

1. Copy your existing Paranormal Investigator components into the new project. They should work with small tweaks. For example, you can pass an empty array for the encounter's `investigators` property to start. You'll want to add investigators to these components, but not yet. Don't worry if they're incomplete. Use what you have and avoid the urge to finish them (for now).

1. Implement the React Router. Add placeholder content for high-level navigation that isn't implemented (**Investigators**). The reason we implement the router before finishing components is that it too may have a larger-than-expected impact on overall design. 

1. Finish encounter components.

1. Finish investigator components.

## Methodology

- Manage your work in Jira.
- Create stories and epics.
- Estimate your work using story points.

## Stretch Goals

Complete investigator CRUD components.

- Update an existing investigator.
- Delete an investigator.
- Use JWT Security
    - Use the paranormal-investigator-api-jwt java project for this.
    - If you decide to do this, ask Dan for the updated requirements.
