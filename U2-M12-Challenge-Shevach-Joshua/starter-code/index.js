import * as Inventory from "./components/Inventory.js";
import api from "./api/index.js";

const heading = document.querySelector("h1");
heading.style.fontFamily = "Fjalla One, sans-serif";
heading.style.fontWeight = "600";

const grid = document.querySelector("#item-grid");

const state = {
  data: [],
  availabilityFilter: false,
  itemTypeFilter: [],
  priceFilter: {
    min: 0, 
    max: 99999
  }
};

let initialized = false;

function render() {
  if (state.data.length) {

    let availableFilter = document.querySelector("#availability-filter-option");
    let itemTypeFilters = document.querySelectorAll(".item-type-filter-option");
    let priceMinMaxFilters = document.querySelectorAll(".price-filter-minmax-values");

    if(!initialized) {
      availableFilter.addEventListener('click', (event) => {
        event.preventDefault();

        let icon = event.target.querySelector(".availability-filter-option-icon");
        let inactive = icon.classList.contains("bi-check-circle");
        event.target.classList.toggle("font-weight-bold", inactive);
        icon.classList.toggle("bi-check-circle-fill", inactive);
        icon.classList.toggle("bi-check-circle", !inactive);

        state.availabilityFilter = !state.availabilityFilter;
        console.log(state.availabilityFilter);
        render();

        event.stopPropagation();
      });

      itemTypeFilters.forEach(x => {
        x.addEventListener('click', (event) => {
          event.preventDefault();

          let icon = event.target.querySelector(".item-type-filter-option-icon");
          let inactive = icon.classList.contains("bi-check-circle");
          icon.classList.toggle("bi-check-circle-fill", inactive);
          icon.classList.toggle("bi-check-circle", !inactive);

          let index = state.itemTypeFilter.indexOf(x.getAttribute('data-type'))
          if(index !== -1) state.itemTypeFilter.splice(index, 1);
          else state.itemTypeFilter.push(x.getAttribute('data-type'));

          console.log(state.itemTypeFilter);
          render();
          event.stopPropagation();
        });
      });

      priceMinMaxFilters.forEach(x => {
        x.addEventListener('change', (event) => {
          event.preventDefault();

          if(event.target.getAttribute('data-type') === "min") 
            state.priceFilter.min = event.target.valueAsNumber;
          else state.priceFilter.max = event.target.valueAsNumber;

          console.log(state.priceFilter);
          render();
          event.stopPropagation();

        });
      });
      initialized = true;
    }
    


    grid.innerHTML = "";

    grid.innerHTML = grid.innerHTML.concat(
      Inventory.renderInventoryRows(state)
    );

    Inventory.renderInventoryContent(state);
  
  } else { grid.innerHTML = "No inventory to display" };
}

(async () => {
  state.data = await api.indexAll();
  console.log(state.data);
  render();
})();

render();

export default render;