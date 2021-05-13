import { gameApi } from "../api/index.js";
import render from "../index.js";

const importantProperties = ["studio", "esrbRating", "manufacturer", "size", "color", "description"]

function renderInventoryRows({ data }) {
  if(data.length) {
    let rowCount = data.length % 4 === 0 ? data.length/4 : Math.ceil(data.length/4);
    let htmlInsert = ``;
    for(let i = 0; i < rowCount; i++) {
      htmlInsert = htmlInsert.concat(
        `
        <div class="item-card-row" id="item-grid-row-${i}"></div>
        `
      );
    }
    return htmlInsert;
  }
}

function getImportantPropertyValues(data) {
  let htmlInsert = "";
  let props = Object.getOwnPropertyNames(data);
  props.forEach( x => {
    if(importantProperties.includes(x)) {
      htmlInsert = htmlInsert.concat(
        `
        <p class="card-text" name="${x}" id="${x}">${data[x]}</p>
        `
      );
    }
  });
  return htmlInsert;
}

function getItemTypeId({itemType}) {
  switch (itemType) {
    case "Games":
      return "gameId";
    case "Consoles":
      return "consoleId";
    case "T-shirts":
      return "tshirtId";
  }
}

function displayDeletePrompt(ev) {
  let targetParent = document.querySelector(`#${ev.target.id}`).parentElement.firstElementChild;
  let data = JSON.parse(targetParent.getAttribute("data-item"));
  let id = data[getItemTypeId(data)];

  let main = document.querySelector("#delete-modal-content");

  let title = main.querySelector("#delete-modal-title");
  title.innerHTML = `Delete ${data.name}`;

  let body = main.querySelector("#delete-modal-body");
  body.innerHTML = 
  `
  Are you sure?
  `;

  let submitButton = main.querySelector(".btn-danger");
  submitButton.addEventListener('click', () => {
    gameApi.deleteGame(id);
  });
}

function displayUpdatePrompt(ev) {
  let targetParent = document.getElementById(ev.target.id).parentElement.firstElementChild;
  let data = JSON.parse(targetParent.getAttribute("data-item"));

  let objectId = data[`${getItemTypeId(data)}`];
  let htmlId = `${data.name.replace(/\s/g, "-")}-${objectId}`;

  let main = document.querySelector("#update-modal-content");
  
  let title = main.querySelector("#update-modal-title");
  title.innerHTML = `Update ${data.itemType.slice(0, -1)}`;

  let body = main.querySelector("#update-modal-body");
  body.innerHTML = 
  `
  <form id="update-form">
  </form>
  `;
  let form = body.querySelector("#update-form");
  
  Object.getOwnPropertyNames(data).forEach(x => 
    form.innerHTML = form.innerHTML.concat(
      `
      <div class="mb-3">
        <label for="${htmlId}-${x}" class="form-label">
          <strong>
            ${(x.charAt(0).toUpperCase() + x.slice(1)).replace(/([A-Z])/g, ' $1').trim()}
          </strong>
        </label>
        <input 
          type="text" 
          class="form-control" 
          id="${htmlId}-${x}" 
          value="${data[x]}" 
          ${x === "itemType" || x === "image" || x === `${getItemTypeId(data)}` ? 'disabled' : ''}>
      </div>
      `
  ));

  let submitButton = main.querySelector(".btn-primary");
  submitButton.addEventListener('click', () => {
    let updateData = {};
    Object.getOwnPropertyNames(data).forEach(x => {
      updateData[x] = main.querySelector(`#${htmlId}-${x}`).value;
    })
    gameApi.updateGame(updateData);
    setTimeout(() => {
      location.reload();
    }, 500);
  });
}

function renderInventoryContent(state) {
  if(state.data.length) {

    let data = state.data;
    if(state.availabilityFilter) 
      data = data.filter(x => x.quantity > 0);
    if(state.itemTypeFilter.length)
      data = data.filter(x => state.itemTypeFilter.includes(x.itemType));
    let pricefilter = state.priceFilter;
    data = data.filter(x => x.price > pricefilter.min && x.price < pricefilter.max);


    let htmlInsert = "";
    let rowCount = data.length % 4 === 0 ? data.length/4 : Math.ceil(data.length/4);
    
    for(let i = 0; i < rowCount; i++) {
      let rowDom = document.querySelector(`#item-grid-row-${i}`)
      for(let j = 0; j < 4; j++) {
        let current = data[(i*4) + j];
        if(current) {
          let currentName = current.name.toLowerCase().replace(/\s/g, "-");
          let currentDomId = `${current.itemType.toLowerCase()}-${(i*4) + j}`;
          rowDom.innerHTML = rowDom.innerHTML.concat(
            `
            <a href="#" id="${currentDomId}" data-item='${JSON.stringify(current)}'>
              <div class="item-card-container">
                <div class="card">
                  <img 
                    name="${currentName}-image" 
                    id="${currentName}-image" 
                    class="card-img-top" 
                    src="${current.image}" 
                    alt="${current.name}">
                  <div class="card-body px-0">
                    ${!current.quantity ? 
                      `
                      <h5 
                        name="${currentName}-availability" 
                        id="${currentName}-availability" 
                        class="card-title" style="font-weight: bold; color: red;"><strong>SOLD OUT</strong></h5>
                      ` : ""}
                    <h5 
                      name="${currentName}-price" 
                      id="${currentName}-price" 
                      class="card-title" style="font-weight: bold;">$${current.price}</h5>
                    <p 
                      name="${currentName}-name" 
                      id="${currentName}-name"
                      class="card-title" 
                      style="font-weight: bold;">${current.name}</p>
                    ${getImportantPropertyValues(current)}
                    <div class="modal fade" id="update-modal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="update-modal-title" aria-hidden="true">
                      <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-md">
                        <div class="modal-content" id="update-modal-content">
                          <div class="modal-header">
                            <h5 class="modal-title" id="update-modal-title">Modal title</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                          </div>
                          <div class="modal-body" id="update-modal-body">
                            Loading...
                          </div>
                          <div class="modal-footer" id="update-modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal"><strong style="pointer-events: none;">Nevermind</strong></button>
                            <button type="button" class="btn btn-primary" data-bs-dismiss="modal"><strong style="pointer-events: none;">UPDATE</strong></button>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="modal fade" id="delete-modal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="delete-modal-title" aria-hidden="true">
                      <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-md">
                        <div class="modal-content" id="delete-modal-content">
                          <div class="modal-header">
                            <h5 class="modal-title" id="delete-modal-title">Modal title</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                          </div>
                          <div class="modal-body" id="delete-modal-body">
                            Loading...
                          </div>
                          <div class="modal-footer" id="delete-modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal"><strong style="pointer-events: none;">Nevermind</strong></button>
                            <button type="button" class="btn btn-danger" data-bs-dismiss="modal"><strong style="pointer-events: none;">DELETE</strong></button>
                          </div>
                        </div>
                      </div>
                    </div>
                    <a id="update-${currentDomId}" class="btn btn-primary update-button" data-bs-toggle="modal" data-bs-target="#update-modal"><strong style="pointer-events: none;">UPDATE</strong></a>
                    <a id="delete-${currentDomId}" class="btn btn-danger delete-button" data-bs-toggle="modal" data-bs-target="#delete-modal"><strong style="pointer-events: none;">DELETE</strong></a>
                  </div>
                </div>
              </div>
            </a>
            `
          );
        }
      }
    }

    const updateButtons = document.querySelectorAll(".update-button");
    updateButtons.forEach(x => x.addEventListener("click", displayUpdatePrompt));

    const deleteButtons = document.querySelectorAll(".delete-button");
    deleteButtons.forEach(x => { 
      x.addEventListener("click", displayDeletePrompt);
    });

    return htmlInsert;
  }
  return "No results";
}

export { renderInventoryRows, renderInventoryContent };
