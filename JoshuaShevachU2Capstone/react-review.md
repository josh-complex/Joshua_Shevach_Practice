# React Review

## React - What Is It?

Library for creating HTML, CSS, and JavaScript user interfaces
AND NOT a framework - this means that React allows us to make a lot of our own decisions, whereas a framework makes decisions for us (about how to use it!)

Created by Facebook - we can see the result of React's application on the Facebook page and app because of the multitude of repeated components.

With React, we write Single-Page Applications (SPAs). That means that we have a single HTML page, and dynamically change the contents (the Document Object Model) of that page using JavaScript.

React provides the technical details, and not the domain code.

## Components

A way to organize your code. Also, our applications is made out of these components. They are the building blocks of our app.

There are class-based components (old, but still supported), and **FUNCTIONAL COMPONENTS**

### Functional Components

A functional component is a single function that returns a chunk of JSX (see below).

A functional component always has the following setup. For example, for the Funkytown component, in the file Funkytown.js we would have this:

```javascript
... imports

function Funkytown({props destructured}) {
    ... the code that actually does the things for Funkytown

    return (
        {/*some jsx*/}
    )
}

export default Funkytown;  // because this is an ES Module

```

Components always start with a capital letter.

## JSX

It looks like HTML in your JS file. The most obvious difference is you say “className” instead of “class” when defining your elements. If you ever see someone not using JSX, they’re crazy.

Every functional component either returns JSX or null.

### Syntax rules
1. Multi-line JSX must be wrapped in parentheses.

1. Functional components can only return a single "top-level" component. So, if there are multiple componets, they have to be wrapped in a single outer component.

    Bad:

    ```javascript
    return (
        <h1>I am a chunk of HTML</h1>
        <Funkytown>WOn't you take me to funkytown.</Funkytown>
    )
    ```

    Fixed:
    ```javascript
    return (
        <div>
            <h1>I am a chunk of HTML</h1>
            <Funkytown>WOn't you take me to funkytown.</Funkytown>
        </div>
    )
    ```

    Now, with a JSX syntax shortcut:
    ```javascript
    return (
        <>
            <h1>I am a chunk of HTML</h1>
            <Funkytown>WOn't you take me to funkytown.</Funkytown>
        </>
    )
    ```
1. Javascript expressions are evaluated in JSX when they are wrapped in curly brackets.

    ```javascript
        const myName = "Alvin Seville";

        function calculateYears(singer) {
            // some logic
            // ...
            //

            return theNumberOfYearsThatTheSingerHasBeenSinging;
        }

        return (
            <>
                <h1>My name is {myName}. ANd now I will serenade you with Funkytown.</h1>
                <Funkytown singer={myName}/>
                <h2>I have been singing for {calculateYears(myName)} years.</h2>
            </h1>
        )

    ```

1. It is very common to use array prototype methods like `.map` and `.filter` (and maybe `.some` and `.forEach` and `.reduce`) within JSX.

    ```JSX

        const [games, setGames] = useState([]);

        // somehow get a list of the games

        return (
            <>
                games.map(game => <Game title={game.title} quantity={game.quantity}/>)
            </>
        )

1. JSX fragments (in other words, a chunk of JSX) can be treated like a variable

    ```JSX
        const myButton = <button>Push here!</button>;

        const myTable = (
            <table>
                <tr>
                    <td>Michael J. Jordan</td>
                    <td>Chicago Bulls</td>
                    <td>GOAT</td>
                </tr>
            </table>
        )

        //...

        return (
            <>
                {myButton}
                {myTable}
            </>
        )
    ```


## Props

Short for properties.

How you pass things down from a parent class to a child class.

Passing props to a child component:

1. In the *parent* component's JSX, it looks like this:

    ```JSX
        function myFunkFunction(increaseAmount) {
            console.log("Increasing the Funkytown funkiness by " + increaseAmount);
            setFunkiness(increaseAmount);
        }

        function getPop() {
            return funkytownPopulation;
        }

        return (
            <>
                <h1 className="bootstrappy" id="title-of-page">Hello. Welcome to...</h1>
                <Funkytown residents={people} mayor={adam} population={getPop()} increaseFunk={myFunkFunction} />
            </>
        )
    ```

1. In the *child* component, props look like 1 of 2 things. Here's the first:

    ```JSX
    function Funkytown({residents, increaseFunk, mayor, population}) {
        //...
    }
    ```

    The other way is to *not* destructure the props, and get an object instead:

    ```JSX
    function Funktown(props) {
        const mayor = props.mayor;
    }
    ```

    As a reminder, here's more destructuring syntax:

    ```JSX
    function Funkytown(props) {
        const {increaseFunk, population, mayor, residents} = props;
        //... the rest of my functional component
    }
    ```

## State - the useState Hook

There can be only one absolute truth.

State is a combination of all the properties in our app. It's like a bunch of java *instance variables* for a single class.

State makes our apps interactive. It allows them to change.

Changing state (via the useState hook) forces a Component to re-render. In fact, it forces a functional component to re-run the entire function.

`useState` is a hook. It is a function that returns a two-element array. The first element is a variable and the second element is the function used to set that variable. This function **triggers a state change**. This causes our functional component to re-run, and as a result, re-"draw" our JSX. The useState function takes one parameter: the initial value for the state variable.

```JSX
function Funkytown() {
    const [beatsPerMinute, setBeatsPerMinute] = useState(72);
    const [funkyFormObject, setFunkyFormObject] = useState({});

    function updateFromForm(evt) {
        const copyOfFunkyForm = {...funkyFormObject};

        copyOfFunkyForm[evt.target.name] = evt.target.value;

        setFunkyFormObject(copyOfFunkyForm); // when this function (updateFromForm) completes, 
                                             // Funkytown() will re-run because we called this function
                                             // which came from the useState() hook

        console.log("SUCCESS! GREAT NEWS!");

        // 100 more lines
    }
    // This could also look like this:
    // const myStateArray = useState(72);
    // const myBPM = myStateArray[0];
    // const mySettingFunction = myStateArray[1];

    function updateFunkytownRhythm(newBeats) {
        console.log("Look out, cool cats, we're about to have a rhythm change!!!");
        // The call below causes the whole Funkytown function to rerun, starting with the first line
        setBeatsPerMinute(newBeats);
    }

    return (
        <>
            <h3>Welcome to Funkytown!</h3>
            {/*MORE IS HAPPENING IN FUNKYTOWN*/}
        </>
    )
}
```

## Side Effects - the useEffect Hook

A side effect of a function is anything that happens in a function that is not directly related to turning the parameters into the return value. Logging is a side effect. Doing something that has nothing to do with the purpose (also, name) of the function is a side effect.

Aside: A function that has no side effects is called a *pure function*.

React wants us to avoid interacting with the DOM or the Browser's Web API. But, this is sometimes necessary, so React has provided the `useEffect` hook to allow us to load data into our component the first time it loads.

```Javascript
calculateDistance(pointA, pointB) {

    // logging is a side effect
    console.log("Wow, that's pretty far.");
    // this is a side effect. hat size has nothing to do with calculating distance
    setHatSize(14);

    return pointA.position - pointB.position;
}
```

Here's what `useEffect` looks like:

```javascript
function Funkytown(props) {
    const [motorcycles, setMotorcycles] = useState([]);

    useEffect(() => {
        fetch(`http://localhost:8080/motorcycles`)
            .then(response => response.json())
            .then(result => setMotorcycles(result));
    }, []);
}
```
The second parameter of useEffect indicates how many times we will call the first parameter of useEffect, which is a function.
* If the second parameter is an empty array, then the function gets called only when the component loads the first time.
* If the second parameter has values in it, the function runs when the component initially loads, and whenever any value in that array changes.
* If we don't pass a second parameter at all, the function runs every time the component loads (in other words, every time a useState function is called).



## NPM

Node Package Manager - We use this to manage our dependencies. It's like maven, except for javascript.

`npm run xyzabc` looks in package.json in the `scripts` object for a listing for `xyzabc` and executes the command there.

`npm install bootstrap` adds the latest bootstrap package to your project. It goes to npmjs which is where all these modules are available, downloads the `bootstrap` module, and puts the source code in your `node_modules` folder.

`npm init` asks a bunch of questions so that it can make a skeleton of a package.json file.

`npm install` is the same as `npm i` looks at the package.json file and install all of the dependencies in the `dependencies` object.

## Bootstrap Components and Third Party Components in General

React Bootstrap is an NPM package that provides React components for Bootstrap elements (technically, Bootstrap Components). For example, if you want a bootstrap styled button with bootstrap animations, you can use the React Bootstrap `<Button>`

`react-router-dom` provides us with Routing components that interact with the browser's history API so that our Single-Page-App looks like a regular old multi-page site.

Many other libraries provide pre-built, reliable, and tested components so that we don't have to write them ourselves.