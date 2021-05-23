# Cloud Native Game Store Project

## Starter Code Overview

Your company has decided to move forward with  a Game Store API. The implementation works well and uses a cloud-native architecture.

You can familiarize yourself with that code [here](./starter-code/game-store/src/main/java/com/company/gamestore).

**You will not need to modify this application.**

Note that the project is set up to accept requests from an application running on localhost port 3000 (using the @CrossOrigin annotation). If you need to run your React application on a different port, you'll need to update the CORS configuration on every controllers.

The project was initialized with `start.spring.io`. It has been built with Spring Boot and Spring MVC.

The design incorporates many elements of a modern cloud-native architecture, including:

* The REST API uses services to allow **any** user to:
  - Perform standard CRUD operations for Games
  - Search for Games by Studio
  - Search for Games by ESRB Rating
  - Search for Games by Title
  - Perform standard CRUD operations for Consoles
  - Search for Consoles by Manufacturer
  - Perform standard CRUD operations for T-Shirts
  - Search for T-Shirts by Color
  - Search for T-Shirts by Size

The database structure is as follows:

```sql
create schema if not exists game_store;
use game_store;

create table if not exists game (
	game_id int(11) not null auto_increment primary key,
    title varchar(50) not null,
    ersb_rating varchar(50) not null,
    description varchar(255) not null,
    price decimal(5, 2) not null,
    studio varchar(50) not null,
    quantity int(11)
);

create table if not exists console (
	console_id int(11) not null auto_increment primary key,
    model varchar(50) not null,
    manufacturer varchar(50) not null,
    memory_amount varchar(20),
    processor varchar(20),
    price decimal(5, 2) not null,
    quantity int(11) not null
);

create table if not exists t_shirt (
	t_shirt_id int(11) not null auto_increment primary key,
    size varchar(20) not null,
    color varchar(20) not null,
    description varchar(255) not null,
    price decimal(5,2) not null,
    quantity int(11) not null
);
```

## Game Store Front End (React)

Your company has elected to use React to build out the front end of this project. Specifically, you will utilize functional components with hooks along with React Router. Each components should include at least one reasonable test.

### React Starter Code

1. Run `npm install`.
2. React Bootstrap has been included in [the starter code](./starter-code/react-game-store), however you may choose to just use the Bootstrap package, and not the React Bootstrap components.
3. Implement React Router for any/all routing. The package was included when you ran `npm install`.
4. [README.md](./react-game-store/README.md)

### User Selection Page - 10%

Your company will implement login functionality later, *but not as a part of this project deliverable.*

Instead, for this project deliverable, the app should start on this page. Because there is no login functionality, a user will need to choose whether they are a `customer` or an `admin` by clicking on a button that says `Customer` or a button that says `Admin`.

### Overview

#### Games - 20%
* Uses `useEffect` and `useState` to retrieve and update the games catalog. This component will render the games either as a grid of cardlike components or as a table using `map`.
* Should have header/navigation component(s) that:
  * Conditionally show a "Change User Type" button once a user type has been chosen.
  * Conditionally show a button to finalize an order if the current user type is `customer` **and** if the user has added some items to the order
* Should include a search bar for `Games`.
  * The search bar should utilize a handler that will filter the products from state before `map`ping to the game components.
* Should be filterable by Studio and ESRB. These would probably use checkboxes and hide/show items in our display based on what was checked.

#### Consoles - 5%
* Should perform the same functionality for Consoles as the Games functionalty with the exception of filtering (below)
* Should be filterable by Manufacturer. These would probably use checkboxes and hide/show items in our display based on what was checked.

#### Shirts - 5%
* Should perform the same functionality for Shirts as the Games functionalty with the exception of filtering (below)
* Should be filterable by Size and Color. These would probably use checkboxes and hide/show items in our display based on what was checked.


### Product Tables&mdash;Customers

In addition to showing the product details in a table or as cards, each display should have a button or other mechanism that will allow a customer to add a product (game, tshirt, or console) to their order. They should be able to change the quantity of any row and then click a button to add that product and quantity to their order.

Customers cannot make any edits to anything or add new products.

Once a customer has selected a product to order, the header/navigation should conditionally render a "Place Order" button.

Customers should not be able to order more than the total inventory in stock for any product.

#### Grading breakdown:

Games - 15%

Consoles - 2.5%

Tshirts - 2.5%

### Product Tables&mdash;Admins

If the user type is `admin`, the tables that they see should have a "Delete" button that allow admins to delete an individual row.

Each row in this `<table>` should have `<td>`s that wrap `<input>`s to allow editing. These `<input>`s will use change handlers.

Each product component should also contain a form to allow the addition of a new product (game, console, or shirt as appropriate). Again, this will be a controlled component with a submit handler.

#### Grading breakdown:

Games - 15%

Consoles - 2.5%

Tshirts - 2.5%

### Finalize Order&mdash;Customers - 10%

Upon clicking an appropriate button in the `Navbar`, customers should be taken to another `table` that lists their entire order.

This component should allow the customer to edit item quantities for their order.

Another button should be present that will place the order, update inventories in the database, and let the user know the total cost of their order. *For the current project, **no invoice will be created.** There is no need to use any `/invoice` endpoint from the java API.* 

### Testing - 10%

Create as many tests of your various components as possible within the allotted time frame. Here are some suggestions:

* "Order"/"Delete" buttons are rendering correctly based on `role`.
* The final order page displays the correct total.
* `Games`, `Consoles`, and `Shirts` render the correct number of table rows.

You do not need to worry about accounting for authentication when writing tests&mdash;just `role`.

© 2021 Trilogy Education Services
