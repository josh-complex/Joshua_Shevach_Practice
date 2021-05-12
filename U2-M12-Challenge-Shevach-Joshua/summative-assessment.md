# Summative Assessment: Game Store Front End

The purpose of this assessment is to demonstrate your ability to apply a front end to a Spring back end.

This assessment builds off of the Game Store Capstone Project that you completed in Unit 1. If you were unable to complete any of the features of that project, you must complete them before beginning this assessment. To complete this assessment, you will use HTML, CSS, and JavaScript to build an interactive web application front end for the Game Store inventory management service that you built for your Capstone project.

## General Requirements - 5%

1. Use CSS Bootstrap
2. The design must be responsive
3. Must produce valid HTML

## Web Application Features - 95%

1. Display all games (NOTE: You may want to adjust widths in the table since there are many fields to show.) - 40%
2. Add a game - 20%
3. Update a game (NOTE: Game Store RESTful API only offers `PUT` (not `PATCH`).) - 10%
4. Delete a game (NOTE: No response is returned (or for updates). Using `.json()` will cause error!) - 10%
5. Interactions Required
   1. Display all games
   2. Display games by studio - 5%
   3. Display games by ESRB rating - 5%
   4. Display games by the first letter of titles - 5%

**Hint:** You may want to use `map` to generate checkboxes to `filter` by studio etc. To get unique items,you can try creating a `Set` from an array and then spreading it back into another array `...[new Set()]`.

## Methodology

- You must manage your work in Jira
- You must create stories and epics
- You must estimate your work using story points


Note: The Game Store RESTful API provides errors. We can check for `status >= 400`, and create an error message as follows:

```javascript
const state = {
  data: [],
  error: null,
};

function render() {

   // Conditionally render error message info instead of the table
  if (state.error) {
    root.innerHTML = `<p class="text-danger">${state.error} 😞</p>
    <p class="text-info">Please refresh the page 📃.</p>
    `;
  } else {
    root.innerHTML = state.data.length
      ? `
  ${Table(state)}
  ${Form()}
  `
      : `<p>No games to display!</p>`;
  }
```

```javascript
api
      .create(newGame)
      .then((resp) => {
        if (resp.status >= 400) {

         // Turn `errors` into a string
          const errorMsg = resp.errors.join("\n");
          throw new Error(errorMsg);
        }
        state.data = [...state.data, resp];
        render();
      })
      .catch((error) => {
        state.error = error;

      // Error messages will render instead of the table
        render();
      });
```

## Stretch Goals

Complete the same features for consoles and t-shirts.

---

**Note:** This project may prove to be quite challenging. In a way, it sets the stage for why we may want to utilize something such as React to manage all of the state updates, etc.
